package com.cours.Services;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cours.dao.IPresenceRepository;
import com.cours.dao.ISeancePratique;
import com.cours.dao.ISeanceTheorique;
import com.cours.dto.CondidatDTO;
import com.cours.dto.MoniteurDTO;
import com.cours.dto.VehiculeDTO;
import com.cours.entities.Presence;
import com.cours.entities.Seance;
import com.cours.entities.SeancePratique;
import com.cours.entities.SeanceTheorique;
import com.cours.entities.Type;
import com.cours.entities.TypePratique;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;







@Service
@Transactional
public class CoursServiceImpl implements ICoursService{
     

       
       @Autowired
       private ISeanceTheorique Stheorique;
       
       @Autowired
       private ITypeService typeservice;
       
//       @Autowired
//       private ICategorieRepository categorie;
  
       @Autowired
       private ISeancePratique Spratique;
      
       @Autowired
       private IPresenceRepository presenceRepository;
      
       @Autowired
       private RestTemplate restTemplate;
       
       
       @Value("${condidat-service.url}") // L'URL du microservice condidat
       private String condidatServiceUrl;
       
       @Value("${vehicule-service.url}")
       private String VehiculeServiceUrl;
       
       @Value("${employe-service,url}")
       private String employeServiceUrl;
       
       public CondidatDTO getCondidatFromServiceCondidat(Long idA, Long idc) {
         
           String condidatUrl = condidatServiceUrl + "/Condidats/"+idc;
            // Assurez-vous que l'idA est inclus dans la requête
          
           ResponseEntity<CondidatDTO> response = restTemplate.exchange(
               condidatUrl,
               HttpMethod.GET,
               null, // Inclure le corps de la requête
               CondidatDTO.class
           );
       
           if (response.getStatusCode() == HttpStatus.OK) {
               return response.getBody();
           } else {
               // Gérez les autres cas de réponse (erreur, non trouvé, etc.)
               return null;
           }
       }

       public VehiculeDTO getVehiculeFromServiceVehicule(Long idA , Long idV) {
    	   String VehiculeUrl=VehiculeServiceUrl + "/vehicles/show-vehicle-by-id/"+idV; 
    	   ResponseEntity<VehiculeDTO> response = restTemplate.exchange(
    			   VehiculeUrl,
                   HttpMethod.GET,
                   null, 
                   VehiculeDTO.class
               );
    	   if (response.getStatusCode() == HttpStatus.OK) {
               return response.getBody();
           } else {
               
               return null;
           }
       }
       
	   	@Override
	   	public MoniteurDTO getMoniteurTheoriqueFromServiceEmploye(Long idM, Long idA) {
	   	   String MoniteurUrl=employeServiceUrl + "/moniteurTheorique/"+idM; //zidi url
		   ResponseEntity<MoniteurDTO> response = restTemplate.exchange(
				   MoniteurUrl,
	             HttpMethod.GET,
	             null, 
	             MoniteurDTO.class
	         );
		   if (response.getStatusCode() == HttpStatus.OK) {
	         return response.getBody();
	       } else {
	         
	         return null;
	       }
	   	}

	   	@Override
	   	public MoniteurDTO getMoniteurPratiqueFromServiceEmploye(Long idM, Long idA) {
	   	 String MoniteurUrl=employeServiceUrl + "/moniteurPratique/"+idM; //zidi url
		   ResponseEntity<MoniteurDTO> response = restTemplate.exchange(
				   MoniteurUrl,
	           HttpMethod.GET,
	           null, 
	           MoniteurDTO.class
	       );
		   if (response.getStatusCode() == HttpStatus.OK) {
	       return response.getBody();
	     } else {
	       
	       return null;
	     }
		   
	   	}
       


       ///////////////////
       public boolean EstMemeAutoEcole(SeanceTheorique st,Long idA) {
    	   List<Long> condidats=st.getCondidat();
    	  
    	 
    	  // System.out.println("moniteur " +m.getAuto_ecole().getId() );

    	   for(Long c:condidats ) {
    		   CondidatDTO con=getCondidatFromServiceCondidat(idA, c);
    		   System.out.println(con);
    		   if(con==null) {
    			   return false;
    		   }
    	   }
    	   return true;
       }
      
       
       public ResponseEntity<?> ajouterCour(SeanceTheorique st,Long idA) {
           try {
        	   List<Long> condidats=st.getCondidat();
        	   Long moniteur=st.getMoniteur();
        	   MoniteurDTO m=getMoniteurTheoriqueFromServiceEmploye(moniteur,  idA)  ;
        	   System.out.println(m);
        	   if(condidats!= null && !condidats.isEmpty() && moniteur!=null  ) {
        		   System.out.println("9bel");
            	   if( EstMemeAutoEcole(st,idA)==true ) {// n9est hadchi m.getAutoEcole().getId()==idA &&
        	   System.out.println("be3d");
		               Type existingType = typeservice.getTypeByLibelle(st.getType().getLibelle(),idA);
		               
		               if (existingType == null) {
		                   Type newType = new Type();
		                   newType.setLibelle(st.getType().getLibelle());
		                   typeservice.AjouterType(newType,idA);
		                   st.setType(newType);
		                   
		               } else {
		                   st.setType(existingType);
		               }
		
		               
		               if (hasTimeConflict(st,idA)) {
		            	   System.out.println("hasTimeConflict");
		                   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conflicting seance already exists.");
		               }
		
		               
		               SeanceTheorique savedSeance = Stheorique.save(st);
		               return ResponseEntity.status(HttpStatus.CREATED).body(savedSeance);
               
            	   }else {
            		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id auto ecole n'est pas valide" );     
            	   }
        	   }else {
        		  
        		   return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("les champs Condidats ou moniteur est vide.");
        	   }
           } catch (Exception e) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error inserting seance theorique : " + e.getMessage());
           }
           
       }

       private boolean hasTimeConflict(SeanceTheorique newSeance,Long idA) {
           List<SeanceTheorique> existingSeances = Stheorique.findByDateSeanceAndMoniteurAndAutoEcoleId(newSeance.getDateSeance(), newSeance.getMoniteur());
             
           for (SeanceTheorique existingSeance : existingSeances) {
               if (!checkTimeConflict(existingSeance, newSeance)) {
                   return true; // Conflict detected
               }
           }
           return false; // No conflict
       }

       private boolean checkTimeConflict(SeanceTheorique existingSeance, SeanceTheorique newSeance) {
    	   LocalTime startTimeExisting = existingSeance.getHeure_debut();
    	    LocalTime endTimeExisting = existingSeance.getHeure_fin();
    	    LocalTime startTimeNew = newSeance.getHeure_debut();
    	    LocalTime endTimeNew = newSeance.getHeure_fin();

    	    // Check for overlap
    	    if ((startTimeNew.isBefore(endTimeExisting) && startTimeNew.isAfter(startTimeExisting)) ||
    	        (endTimeNew.isBefore(endTimeExisting) && endTimeNew.isAfter(startTimeExisting)) ||
    	        (startTimeExisting.isBefore(endTimeNew) && startTimeExisting.isAfter(startTimeNew)) ||
    	        (endTimeExisting.isBefore(endTimeNew) && endTimeExisting.isAfter(startTimeNew))) {
    	        return true; 
    	       }

    	    // No conflict
    	    return false; 
       }
   

       
       ///////////////////
       



   	@Override
   	public ResponseEntity<?> updateSeanceTheorique(Long ids, Long idA, SeanceTheorique updatedSeanceTheorique) {
   	    try {
   	        // Fetch the existing SeanceTheorique entity from the database
   	        SeanceTheorique existingSeanceTheorique = Stheorique.findById(ids).orElse(null);
   	       
   	        if (existingSeanceTheorique != null) {
   	            // Update the fields of the existing entity with the values from the updatedSeanceTheorique
   	            existingSeanceTheorique.setDateSeance(updatedSeanceTheorique.getDateSeance());
   	            existingSeanceTheorique.setHeure_debut(updatedSeanceTheorique.getHeure_debut());
   	            existingSeanceTheorique.setHeure_fin(updatedSeanceTheorique.getHeure_fin());
   	            existingSeanceTheorique.setPermis(updatedSeanceTheorique.getPermis());
   	            
   	            // Update the moniteur relationship
   	         Long updatedMoniteur = updatedSeanceTheorique.getMoniteur();
   	            if (updatedMoniteur != null) {
   	                MoniteurDTO existingMoniteur =getMoniteurTheoriqueFromServiceEmploye(updatedMoniteur,idA);
   	                if(existingMoniteur!=null) {
   	                	existingSeanceTheorique.setMoniteur(updatedMoniteur);
   	                }
   	                
   	            }
   	            
   	            // Update the condidat relationship
   	            List<Long> updatedCondidats = updatedSeanceTheorique.getCondidat();
   	            if (updatedCondidats != null) {
   	                List<Long> existingCondidats = new ArrayList<>();
   	                for (Long updatedCondidat : updatedCondidats) {
   	                    CondidatDTO existingCondidat = getCondidatFromServiceCondidat(idA, updatedCondidat);
   	                    if(existingCondidat !=null) {
   	                    	existingCondidats.add(updatedCondidat);
   	                    }
   	                    
   	                }
   	                existingSeanceTheorique.setCondidat(existingCondidats);
   	            }
   	            
   	            // Update the type
   	            Type existingType = typeservice.GetTypes(idA).stream()
   	                    .filter(t -> t.getLibelle().equals(updatedSeanceTheorique.getType().getLibelle()))
   	                    .findFirst().orElse(null);

   	            if (existingType != null) {
   	                existingSeanceTheorique.setType(existingType);
   	            } else {
   	                Type newType = new Type();
   	                newType.setLibelle(updatedSeanceTheorique.getType().getLibelle());
   	                typeservice.AjouterType(newType,idA);
   	                existingSeanceTheorique.setType(newType);
   	            }

   	            // Save the updated entity back to the database
   	            SeanceTheorique updatedSeance = Stheorique.save(existingSeanceTheorique);
   	            return ResponseEntity.ok(updatedSeance);
   	        } else {
   	            // Handle the case when the SeanceTheorique entity with the given ID is not found
   	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SeanceTheorique with ID " + ids + " not found.");
   	        }
   	    } catch (Exception e) {
   	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating seance theorique : " + e.getMessage());
   	    }
   	}

	@Override
	public  ResponseEntity<?>  getSeanceById(Long ids ,Long idA,boolean isPratique) {
		// TODO Auto-generated method stub
		
		if(isPratique) {
			System.out.println("hii");
			SeancePratique seance =Spratique.findById(ids).get() ;
			if(seance!=null && getMoniteurPratiqueFromServiceEmploye(seance.getMoniteur(),idA)!=null)
			{
				return ResponseEntity.ok(seance);
			}
			
		}
		else {
			SeanceTheorique seance =Stheorique.findById(ids).get();
			if(seance!=null && getMoniteurTheoriqueFromServiceEmploye(seance.getMoniteur(),idA)!=null) {
	    		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seancetheorique with ID " + ids + " not found.");
				return ResponseEntity.ok(seance);  
	    	}
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seance with ID " + ids + " not found.");

	}
	
	@Override
	public List<SeanceTheorique> getGroupOfCours( List<Long> idSeance,Long idAuto) {
		List<SeanceTheorique > cours = new ArrayList<>();
		System.out.println(idSeance);
		idSeance.forEach(id -> {
			SeanceTheorique p = Stheorique.findById(id).get();
			if(p != null && getMoniteurTheoriqueFromServiceEmploye(p.getMoniteur(),idAuto)!=null) {
				cours.add(p);
			}	
		});
		if(cours.size() == idSeance.size()) {
			return cours;
		}else {
			throw new EntityNotFoundException("Les seances n'a pas été tous trouvé !");
		}

	}
	
	
	
	
	

	


	@Override
	public List<SeanceTheorique> getAllCours(Long idA) {
		
		List<SeanceTheorique> seances=Stheorique.findAll();
		List<SeanceTheorique> seancesTheoriques=new ArrayList<>();
		for(SeanceTheorique s:seances) {
			System.out.println("id de seance"+s.getId()+"id moniteur"+s.getMoniteur());
			if(getMoniteurTheoriqueFromServiceEmploye(s.getMoniteur(),idA)!=null) { //si resultat different de null alors seance appartient a l'autoecole connecte
				seancesTheoriques.add(s);
			}
		}

		return seancesTheoriques;
	
	}
	

 

	@Override
	public ResponseEntity<String> deleteSeance(Long id_seance, Long id_A) {
		 SeanceTheorique seance=Stheorique.findById(id_seance).get();
		 Long moniteur=seance.getMoniteur();
		 MoniteurDTO isExiste=getMoniteurTheoriqueFromServiceEmploye(moniteur,id_A);
		 if(isExiste != null) {
			 Stheorique.deleteById(id_seance);
		     return ResponseEntity.status(HttpStatus.NO_CONTENT).body("la suppression essfectuee"); 

		 }else {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id auto ecole n'est pas valide ."); 

		 }
	}

	@Override
	public void deleteAllSeance(Long id_AutoEcole ) {
		List<SeanceTheorique> listSeances=getAllCours(id_AutoEcole);
		for(SeanceTheorique s :listSeances) {
			Stheorique.deleteById(s.getId());
		}
	}
	
	
	//***************cours pratique *****************

	  public boolean EstMemeAutoEcole2(SeancePratique st,Long idA) {
   	   List<Long> condidats=st.getCondidat();
   	   

   	   for(Long c:condidats ) {
   		   CondidatDTO con=getCondidatFromServiceCondidat(idA, c);
   		   if(con==null) {
   			   return false;
   		   }
   	   }
   	   return true;
      }
     
   	 
	
	
	public ResponseEntity<?> ajouterCourPratique(SeancePratique sp,Long idA) {
        try {
        	
            List<Long> condidats=sp.getCondidat();
            Long moniteur=sp.getMoniteur();
      	   Long vehicule=sp.getVehicule();                 //
      	   MoniteurDTO m=getMoniteurPratiqueFromServiceEmploye(moniteur, idA) ;
      	   if(condidats!= null && !condidats.isEmpty() && moniteur!=0L && vehicule!=null ) {
      		 if( EstMemeAutoEcole2(sp,idA)==true ) {   //m.getAutoEcole().getId()==idA
      			 
      		 
        	
        	  TypePratique existingType = typeservice.GetTypesPratique(idA).stream()
                      .filter(t -> t.getLibelle().equals(sp.getType().getLibelle()))
                      .findFirst().orElse(null);            
        	  if (existingType == null) {
        		  TypePratique newType = new TypePratique();
                newType.setLibelle(sp.getType().getLibelle());
                typeservice.AjouterTypePratique(newType,idA);
                sp.setType(newType);
            } else {
                sp.setType(existingType);
            }

            // Check for conflicts with existing seances
            if (hasTimeConflict2(sp,idA)) {
            	 System.out.println("hasTimeConflict");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conflicting seance already exists.");
            }

            // Save the new seance
            SeancePratique savedSeance = Spratique.save(sp);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSeance);
	      	 }else {
	  		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id auto ecole n'est pas valide" );     
	  	   }
      	 }else {
     		  
    		   return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("l'un des champs Condidats , moniteur ou vehicule est vide.");
    	   }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error inserting seance theorique : " + e.getMessage());
        }
    }

    private boolean hasTimeConflict2(SeancePratique newSeance,Long idA) {
        List<SeancePratique> existingSeances = Spratique.findByDateSeanceAndMoniteurAndAutoEcoleId(newSeance.getDateSeance(),newSeance.getMoniteur());
         // System.out.println("hasTimeConflict");
        for (SeancePratique existingSeance : existingSeances) {
            if (!checkTimeConflict2(existingSeance, newSeance)  ) {
                return true; // Conflict detected
                
            }
        	      
        }
      
        return false; // No conflict
    }

    private boolean checkTimeConflict2(SeancePratique existingSeance, SeancePratique newSeance) {
 	   LocalTime startTimeExisting = existingSeance.getHeure_debut();
 	    LocalTime endTimeExisting = existingSeance.getHeure_fin();
 	    LocalTime startTimeNew = newSeance.getHeure_debut();
 	    LocalTime endTimeNew = newSeance.getHeure_fin();

 	    // Check for overlap
 	    if ((startTimeNew.isBefore(endTimeExisting) && startTimeNew.isAfter(startTimeExisting)) ||
 	        (endTimeNew.isBefore(endTimeExisting) && endTimeNew.isAfter(startTimeExisting)) ||
 	        (startTimeExisting.isBefore(endTimeNew) && startTimeExisting.isAfter(startTimeNew)) ||
 	        (endTimeExisting.isBefore(endTimeNew) && endTimeExisting.isAfter(startTimeNew))) {
 	        return true; 
 	       }
        System.out.println("lire");
 	    // No conflict
 	    return false; 
    }
	
	



	@Override
	public List<SeancePratique> getAllCoursPratique(Long idA) {
		// TODO Auto-generated method stub

		List<SeancePratique> seances=Spratique.findAll();
		List<SeancePratique> seancesPratiques=new ArrayList<>();
		
		for(SeancePratique s:seances) {
			System.out.println("id de seance"+s.getId()+"id moniteur"+s.getMoniteur());
			if(getMoniteurPratiqueFromServiceEmploye(s.getMoniteur(),idA)!=null) { //si resultat different de null alors seance appartient a l'autoecole connecte
				seancesPratiques.add(s);
			}
		}

		return seancesPratiques;
	}
   
	@Override
	public List<SeancePratique> getGroupOfCoursPratique( List<Long> idSeance,Long idAuto) {
		List<SeancePratique > cours = new ArrayList<>();
		//System.out.println(idSeance);
		idSeance.forEach(id -> {
			SeancePratique p = Spratique.findById(id).get();
			if(p != null && getMoniteurPratiqueFromServiceEmploye(p.getMoniteur(),idAuto) !=null) {
				cours.add(p);
			}	
		});
		if(cours.size() == idSeance.size()) {
			return cours;
		}else {
			throw new EntityNotFoundException("Les seances n'a pas été tous trouvé !");
		}

	}


	//msht get vehicule
	
	@Override
	public ResponseEntity<String> deleteSeancePratique(Long id_seance, Long id_AutoEcole) {
		 SeancePratique seance=Spratique.findById(id_seance).get();
		 Long moniteur=seance.getMoniteur();
		 MoniteurDTO isExiste=getMoniteurPratiqueFromServiceEmploye(moniteur,id_AutoEcole);
		 //System.out.println(idAutoEcole);
		 if(isExiste!=null) {
			 Spratique.deleteById(id_seance);
		     return ResponseEntity.status(HttpStatus.NO_CONTENT).body("la suppression essfectuee"); 

		 }else {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id auto ecole n'est pas valide ."); 

		 }
	}

	@Override
	public void deleteAllSeancePratique(Long id_AutoEcole ) {
		List<SeancePratique> listSeances=getAllCoursPratique(id_AutoEcole);
		for(SeancePratique s :listSeances) {
			Spratique.deleteById(s.getId());
		}
	}
	
	
	
	
	
	
	
	
	@Override
    public ResponseEntity<?>  updateSeancePratique(Long ids,Long idA,SeancePratique updatedSeancePratique) {
         
		 try {
	   	        // Fetch the existing SeanceTheorique entity from the database
	   	        SeancePratique existingSeancePratique = Spratique.findById(ids).orElse(null);
	   	        
	   	        if (existingSeancePratique != null) {
	   	            // Update the fields of the existing entity with the values from the updatedSeanceTheorique
	   	        	existingSeancePratique.setDateSeance(updatedSeancePratique.getDateSeance());
	   	        	existingSeancePratique.setHeure_debut(updatedSeancePratique.getHeure_debut());
	   	        	existingSeancePratique.setHeure_fin(updatedSeancePratique.getHeure_fin());
	   	        	existingSeancePratique.setPermis(updatedSeancePratique.getPermis());
	   	           // existingSeancePratique.setVehicule(updatedSeancePratique.getVehicule());
	   	            Long updatedVehicule = updatedSeancePratique.getVehicule();
	   	            if (updatedVehicule != null) {
	   	            	VehiculeDTO existingVehicule = getVehiculeFromServiceVehicule( idA ,  updatedVehicule);
	   	               if(existingVehicule!=null) {
	   	            	existingSeancePratique.setVehicule(updatedVehicule);
	   	               }
	   	            	
	   	            }
	   	            // Update the moniteur relationship
	   	         Long updatedMoniteur = updatedSeancePratique.getMoniteur();
	   	            if (updatedMoniteur != null) {
	   	                MoniteurDTO existingMoniteur = getMoniteurTheoriqueFromServiceEmploye(updatedMoniteur, idA) ;
	   	                if(existingMoniteur!=null) {
	   	                   existingSeancePratique.setMoniteur(existingMoniteur.getId());
	   	                }
	   	              
	   	            }
	   	            
	   	            // Update the condidat relationship
	   	            List<Long> updatedCondidats = updatedSeancePratique.getCondidat();
	   	            if (updatedCondidats != null) {
	   	                List<Long> existingCondidats = new ArrayList<>();
	   	                for (Long updatedCondidat : updatedCondidats) {
	   	                    CondidatDTO existingCondidat = getCondidatFromServiceCondidat(idA, updatedCondidat);
	   	                    if(existingCondidat !=null) {
	   	                    	existingCondidats.add(existingCondidat.getId());
	   	                    }
	   	                    
	   	                }
	   	             existingSeancePratique.setCondidat(existingCondidats);
	   	            }
	   	            
	   	            // Update the type
	   	            TypePratique existingType = typeservice.GetTypesPratique(idA).stream()
	   	                    .filter(t -> t.getLibelle().equals(updatedSeancePratique.getType().getLibelle()))
	   	                    .findFirst().orElse(null);

	   	            if (existingType != null) {
	   	            	existingSeancePratique.setType(existingType);
	   	            } else {
	   	            	TypePratique newType = new TypePratique();
	   	                newType.setLibelle(updatedSeancePratique.getType().getLibelle());
	   	                typeservice.AjouterTypePratique(newType,idA);
	   	                existingSeancePratique.setType(newType);
	   	            }

	   	            // Save the updated entity back to the database
	   	            SeancePratique updatedSeance = Spratique.save(existingSeancePratique);
	   	            return ResponseEntity.ok(updatedSeance);
	   	        } else {
	   	            // Handle the case when the SeanceTheorique entity with the given ID is not found
	   	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SeancePratique with ID " + ids + " not found.");
	   	        }
	   	    } catch (Exception e) {
	   	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating seance theorique : " + e.getMessage());
	   	    }
		 
   }
	
	
	//////////////Gestion de presence//////////////////


	@Override
	public ResponseEntity<String> marquerPresence(Long seanceId,Long idA, Long condidatId, boolean estPresent, boolean isPratique) {
	    Seance seance = null;
	    if (isPratique) {
	       SeancePratique seance1 = Spratique.findById(seanceId).get();
	        if(getMoniteurPratiqueFromServiceEmploye(seance1.getMoniteur(),idA) !=null)
	        	seance=seance1;
	        
	    } else {
	       SeanceTheorique seance2 = Stheorique.findById(seanceId).get();
	       if(getMoniteurTheoriqueFromServiceEmploye(seance2.getMoniteur(),idA) !=null)
	        	seance=seance2;
	    }
	    System.out.println("9bel function");
	    CondidatDTO condidat = getCondidatFromServiceCondidat(idA, condidatId);
	    System.out.println("be3d lfunction");

	    if (seance != null && condidat != null) {
	        List<Long> condidats = seance.getCondidat();
	        if (condidats.contains(condidatId)) {
	            Presence presence = presenceRepository.findBySeanceAndCondidat(seance, condidatId);
	            if (presence == null) {                   
	                presence = new Presence();
	                presence.setSeance(seance);
	                presence.setCondidat(condidatId);
	            }
	            presence.setEst_present(estPresent);
	            presenceRepository.save(presence);
	        } else {
	           // System.out.println("Le condidat n'est pas associé à la séance");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le condidat n'est pas associé à la séance.");
	        }
	    } else {
	        //System.out.println("L'ID de séance ou le condidat n'existe pas");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'ID de séance ou le condidat n'existe pas");

	    }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("presence bien enregistree.");

		
	}
	
	
	@Override
	public ResponseEntity<?> getCandidatsPresentsBySeance(Long seanceId,Long idA, boolean isPratique) {
	    Seance seance = null;
	    if (isPratique) {
	        seance = Spratique.findById(seanceId).orElse(null);
	    } else {
	        seance = Stheorique.findById(seanceId).orElse(null);
	    }

	    if (seance != null) {
	        List<Presence> liste = presenceRepository.findBySeanceIdAndEstPresentTrue(seanceId);
	        List<Presence> listePresences =new ArrayList<>();
	        for(Presence p:liste) {
	        	if(getSeanceById(p.getSeance().getId(),idA,isPratique)!=null) {
	        	     listePresences.add(p);
	        	}
	        }
	        List<Long> candidatsPresents = listePresences.stream()
	                .filter(Presence::isEst_present)
	                .map(Presence::getCondidat)
	                .collect(Collectors.toList());
	        
	        if (!candidatsPresents.isEmpty()) {
	            return ResponseEntity.ok(candidatsPresents);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun candidat présent trouvé pour cette séance.");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'ID de séance n'existe pas.");
	    }
	}

	
	
	
    @Override
	public List<?> getSeanceavecPresence(Long idA ,boolean isPratique) {
   	   List<?> seancesAvecPresences=new ArrayList<>();
  	    if (isPratique) {
  	    	
  	    	   List<SeancePratique> listeSeances =getAllCoursPratique(idA);
  	     
 	           seancesAvecPresences = listeSeances.stream()
 	    		.filter(seance -> seance.getPresences() != null) // Filtre les séances avec la liste de présences non nulle
 	  	        .filter(seance -> seance.getPresences().stream().anyMatch(Presence::isEst_present)) // Filtre les séances avec au moins une présence estPresent=true
 	  	        .collect(Collectors.toList());
 	   
 	    
 	    
  	    } else {
  	    	List<SeanceTheorique> listeSeances =getAllCours(idA);
 	          seancesAvecPresences = listeSeances.stream()
 	        		.filter(seance -> seance.getPresences() != null) // Filtre les séances avec la liste de présences non nulle
 	    	        .filter(seance -> seance.getPresences().stream().anyMatch(Presence::isEst_present)) // Filtre les séances avec au moins une présence estPresent=true
 	    	        .collect(Collectors.toList());
  	    }
        
	   		return seancesAvecPresences;
	   	}







	





	







       
       
}
