package com.condidat.controllers;

import java.util.ArrayList;




import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.util.EnumUtils;
import com.condidat.dao.ICondidatRepository;
import com.condidat.dto.CarteCondidatInfo;
import com.condidat.dto.CertificatInfoDTO;
import com.condidat.dto.ContractInfoDTO;
import com.condidat.dto.MoniteurDTO;
import com.condidat.dto.VehiculeDTO;
import com.condidat.entities.AutoEcole;
import com.condidat.entities.Condidat;
import com.condidat.entities.Contrat;
import com.condidat.entities.IdRequest;
import com.condidat.entities.TypeFormation;
import com.condidat.services.IAutoEcoleService;
import com.condidat.services.ICondidatService;





@RestController
@RequestMapping("/Condidat")
public class CondidatController {
	
	@Autowired
	private ICondidatService service;


    
    
	@GetMapping("/")
	public String sayhello() {
		return "salam youssra zwina lfena wli mabghak ymot ";
	}
	
	@RequestMapping("/AddCondidat") 
	public ResponseEntity<?> AddCondidat(@Valid @RequestBody Condidat c,@RequestAttribute Long idA) {
		Condidat existingCondidat=service.getCondidatByCin(idA,c.getCin());
		if(c.getAuto_ecole().getId()!=idA) {
	        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id auto ecole pas valide !");

		}
		if (existingCondidat != null) {
			
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Le candidat existe déjà"); 
	    }else {
	    	
           if (c.isPossede_permis()) {
		        
		        if (c.getPermis() == null || c.getPermis().isEmpty()) {
		            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("l'un des champ de permis est null");  //422
		        }
		        return  ResponseEntity.status(HttpStatus.CREATED).body(service.AjouterCondidat(c));
		    } else {
		        return  ResponseEntity.status(HttpStatus.CREATED).body(service.AjouterCondidat(c));
		    }
	    }
		   
    }
	 
	
	
	
	@PutMapping("/EditCondidat")
	public ResponseEntity<?> modifierCondidat(@RequestAttribute Long idA, @RequestBody Condidat condidat) {
		try {
			
			Long idc=condidat.getId();
//		    Categorie newCategorie = categorieRepository.findById(condidat.getCatgorie().getId())
//		    		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie non trouvée avec l'ID: " + condidat.getCatgorie().getId()));
//	

		    Condidat existingCondidat=service.getCondidatByIdAndAutoEcoleId(idc, idA);
	        
	        if (existingCondidat == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Condidat non trouvé avec l'ID: " + idc);
	        }
	
	        
		    existingCondidat.setDate_inscription(condidat.getDate_inscription());
		    existingCondidat.setRef_web(condidat.getRef_web());
		    existingCondidat.setNom_frenc(condidat.getNom_frenc());
		    existingCondidat.setNom_arab(condidat.getNom_arab());
		    existingCondidat.setPrenom_frenc(condidat.getPrenom_frenc());
		    existingCondidat.setPrenom_arab(condidat.getPrenom_arab());
		    existingCondidat.setDate_naissance(condidat.getDate_naissance());
		    existingCondidat.setLieu_nais(condidat.getLieu_nais());
		    existingCondidat.setTel(condidat.getTel());
		    existingCondidat.setEmail(condidat.getEmail());
		    existingCondidat.setProfession(condidat.getProfession());
		    existingCondidat.setImage_cin(condidat.getImage_cin());
		    existingCondidat.setDate_fin_contrat(condidat.getDate_fin_contrat());
		    existingCondidat.setProfile(condidat.getProfile());
		    existingCondidat.setMontant(condidat.getMontant());
		    existingCondidat.setNmbr_heure_pr(condidat.getNmbr_heure_pr());
		    existingCondidat.setNmbr_heure_th(condidat.getNmbr_heure_th());
		    existingCondidat.setType(condidat.getType());
		    existingCondidat.setPermis(condidat.getPermis());
		    existingCondidat.setPossede_permis(condidat.isPossede_permis());
		    existingCondidat.setCin(condidat.getCin());
		    existingCondidat.setNum_contrat(condidat.getNum_contrat());
		    existingCondidat.setAdrs_arab(condidat.getAdrs_arab());
		    existingCondidat.setAdrs_frenc(condidat.getAdrs_frenc());
		    existingCondidat.setObservation(condidat.getObservation());
		    existingCondidat.setMoniteur_pratique(condidat.getMoniteur_pratique());
		    existingCondidat.setMoniteur_theorique(condidat.getMoniteur_theorique());
		    existingCondidat.setVoiture(condidat.getVoiture());
	
		    existingCondidat.setCatgorie(condidat.getCatgorie()) ;
	
		    //return AddCondidat(existingCondidat,idA);
		    if (existingCondidat.isPossede_permis()) {
		        
		        if (existingCondidat.getPermis() == null || existingCondidat.getPermis().isEmpty()) {
		            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("l'un des champ de permis est null");  //422
		        }
		        return  ResponseEntity.ok(service.AjouterCondidat(existingCondidat));
		    } else {
		        return  ResponseEntity.ok(service.AjouterCondidat(existingCondidat));
		    }
		    
	    
	    
	    
		}catch (ResponseStatusException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	    //return ResponseEntity.ok(service.AjouterCondidat(existingCondidat));
	}

	
	
	
	@GetMapping("/condidats-by-etat")
	public ResponseEntity<List<Condidat>> GetAllCondidat(@RequestAttribute Long idA,@RequestParam String etat){
		//List<Condidat> AllCondidat=service.getAllCondidat();
	
		List<Condidat> AllCondidat=service.getCandidatsByAutoEcoleId(idA);
		
		List<Condidat> condidatActuell=new ArrayList<>();
		for(Condidat  c:AllCondidat) {
			if(c.getEtat().equals(etat)) {
				condidatActuell.add(c);
			}
		}
		return  ResponseEntity.ok(condidatActuell);
	
		
		
	}
	
	


	@GetMapping("/AllCondidats")//tester etat type
	public ResponseEntity<?> getAllCondidats(@RequestAttribute Long idA, @RequestParam(required = false) TypeFormation type, @RequestParam(required = false) String etat) {


	    List<Condidat> condidats = service.getCandidatsByAutoEcoleId(idA);

	    List<Condidat> filteredCondidats = new ArrayList<>();
	    for (Condidat c : condidats) {
	        if ((type == null || c.getType() == type) && (etat == null || c.getEtat().equals(etat))) {
	            filteredCondidats.add(c);
	        }
	    }

	    if (filteredCondidats.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(filteredCondidats);
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(filteredCondidats);
	    }
	}
	
	

	
	@GetMapping("/CondidatsById")
	public  ResponseEntity<?> GetCondidat2(@RequestAttribute Long idA, @RequestBody IdRequest request) {

	    Long idC=request.getIdC();	
		Condidat condidat = service.getCondidatByIdAndAutoEcoleId(idC, idA);
	    if (condidat != null) {
	        return ResponseEntity.ok(condidat);
	    } else {
	    	 String errorMessage = "Condidat not found with ID: " + idC;
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	    }
		
	}
	
	@GetMapping("/Condidats/{idC}")
	public  ResponseEntity<?> GetCondidat(@RequestAttribute Long idA, @PathVariable Long idC) {

	  	
		Condidat condidat = service.getCondidatByIdAndAutoEcoleId(idC, idA);
	    if (condidat != null) {
	    	
//	            Long moniteurTheoriqueId = condidat.getMoniteur_theorique();
//	            if (moniteurTheoriqueId != null) {
//	                MoniteurDTO moniteurTheorique = service.getMoniteurTheoriqueFromServiceEmploye(moniteurTheoriqueId, idA) ;
//	                condidat.setMoniteurinfos(moniteurTheorique);
//	            }
	        
	        return ResponseEntity.ok(condidat);
	    } else {
	    	 String errorMessage = "Condidat not found with ID: " + idC;
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	    }
		
	}
	
	@GetMapping("/categorie/{libelle}/condidats")
	public ResponseEntity<?> GetCondidatByCategorieLibelle(@PathVariable String libelle ,@RequestAttribute Long idA){

			List<Condidat> condidats = service.getcondidatByCategorieLibelle(libelle, idA);
		    if (condidats != null) {
		        return ResponseEntity.ok(condidats);
		    } else {
		    	 String errorMessage = "condidats not found with libelle: " + libelle;
			        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		    }
		
		
	}
	
	
	
    @PostMapping("/listCondidat")
    public ResponseEntity<List<Condidat>> getListCondidat(@RequestAttribute Long idA,@RequestBody IdRequest request){
    	List<Long> condidatId=request.getCondidatId();
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.getGroupOfCondidat(idA, condidatId));
    			
    }
	
	
	@DeleteMapping("/ArchiveCondidat")
    public ResponseEntity<String> archiveCondidat(@RequestAttribute Long idA,@RequestBody IdRequest request) {
		Long id=request.getIdC();
        ResponseEntity<List<Condidat>> condidatsResponse = GetAllCondidat(idA, "Active");
        Condidat condidat = service.getCondidat(id);
        List<Condidat> condidats = condidatsResponse.getBody();
        if (condidat != null && condidats.contains(condidat)) {
            condidat.setEtat("Archive");
            service.AjouterCondidat(condidat);
            System.out.println("Le condidat est archivé");
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("le condidat est archive"); 

        } else {
            System.out.println("Le condidat n'existe pas ou est déjà archivé ou supprimé");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le condidat n'existe pas ou est déjà archivé ou supprimé"); 

        }
	}
	

	
	@RequestMapping("/disarchiveCondidat")
	public ResponseEntity<?> Disarchive(@RequestAttribute Long idA,@RequestBody IdRequest request)
	{
		Long id=request.getIdC();
		ResponseEntity<List<Condidat>> condidatsResponse=GetAllCondidat(idA,"Archive");
		Condidat c=service.getCondidat(id);
		 List<Condidat> condidats = condidatsResponse.getBody();
		if(condidats.contains(c)) {
			c.setEtat("Active");
			return  ResponseEntity.ok(service.AjouterCondidat(c));
		}else {
			System.out.println("le condidat n'existe pas dans l'archive ");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("le condidat n'existe pas dans l'archive "); 

		}
		
		
	}
	
	@DeleteMapping("/deleteCondidat") //passer au hestorique
	public ResponseEntity<String> supprimerCondidat(@RequestAttribute Long idA,@RequestBody IdRequest request) { //suprimer transferer dans l'historique 
		Long id=request.getIdC();
		ResponseEntity<List<Condidat>> condidatsResponse=GetAllCondidat(idA,"Active");
		Condidat condidat= service.getCondidat(id);
		 List<Condidat> condidats = condidatsResponse.getBody();
		if(condidat!=null && condidats.contains(condidat)) {
				 condidat.setEtat("Supprime");
				 service.AjouterCondidat(condidat);
				 System.out.println("le condidat supprimee");
			     return ResponseEntity.status(HttpStatus.NO_CONTENT).body("le condidat supprimee "); 

		}else {
			System.out.println("le condidat n'existe pas ,voir dans l'archive ");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("le condidat n'existe pas ,voir dans l'archive ");
		}
		 
	}

	
	
		
	@DeleteMapping("/delCondidats")//suppresion normal
	public void delAllCondidats(@RequestAttribute Long idA) {
		 service.deleteAll(idA);
		 
	}
	
	@DeleteMapping("/delCondidat") //suppresion normal
	public void delCondidats(@RequestAttribute Long idA,@RequestBody IdRequest request) {
		Long id=request.getIdC();
		ResponseEntity<List<Condidat>> condidatsResponse=GetAllCondidat(idA,"Active");
		Condidat condidat= service.getCondidat(id);
		List<Condidat> condidats = condidatsResponse.getBody();
		if(condidat!=null && condidats.contains(condidat)) {
			service.deleteCondidat(id);
			 
			 System.out.println("le condidat est  supprime definitevement");
		}else {
			System.out.println("le condidat n'existe pas ou deja archive ou supprime ");
		}
	}
	
	

	 
	
	@GetMapping("/Contrat") 
	public ResponseEntity<?> getContratbyId(@RequestAttribute Long idA,@RequestBody IdRequest request) {
//		Long id=request.getIdC();
//	    Contrat contratinfo = contratService.getContratbyCondidat(idA, id);
//	    if (contratinfo == null) {
//	        String errorMessage = "Contrat not found with ID: " + id;
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	    }
		Long id=request.getIdC();
		Condidat condidat=service.getCondidatByIdAndAutoEcoleId(id, idA);
		//Condidat condidat=condidatResponse.getBody();
		if (condidat == null) {
	        String errorMessage = "Condidat not found with ID: " + id;
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	    }

	    ContractInfoDTO contractInfoDTO = new ContractInfoDTO();
	    contractInfoDTO.setAutoEcoleNom(condidat.getAuto_ecole().getNom());
	    contractInfoDTO.setAutoEcoleAdresse(condidat.getAuto_ecole().getAdresse());
	    contractInfoDTO.setAutoEcoleAgrement(condidat.getAuto_ecole().getAgrement());
	    contractInfoDTO.setAutoEcoleIdFiscale(condidat.getAuto_ecole().getId_fiscale());
	    contractInfoDTO.setAutoEcoleNumRegistreCom(condidat.getAuto_ecole().getNum_registre_com());
	    contractInfoDTO.setAutoEcoleVille(condidat.getAuto_ecole().getVille());
	    contractInfoDTO.setAutoEcoleTel(condidat.getAuto_ecole().getTel());
	    contractInfoDTO.setAutoEcoleFax(condidat.getAuto_ecole().getFax());
	    contractInfoDTO.setCondidatCategorie(condidat.getCatgorie());
	    contractInfoDTO.setCondidatNom(condidat.getAdrs_frenc());
	    contractInfoDTO.setCondidatPrenom(condidat.getNom_frenc());
	    contractInfoDTO.setCondidatLieuNaissance(condidat.getLieu_nais());
	    contractInfoDTO.setCondidatDateNaissance(condidat.getDate_naissance());
	    contractInfoDTO.setCondidatAdrsFrenc(condidat.getAdrs_frenc());
	    contractInfoDTO.setCondidatDateInscription(condidat.getDate_inscription());

	    return ResponseEntity.ok(contractInfoDTO);
	}
     
	@GetMapping("/Certificat")
	public ResponseEntity<?> getCertificat(@RequestAttribute Long idA,@RequestBody IdRequest request){
		Long id=request.getIdC();
		Condidat condidat=service.getCondidatByIdAndAutoEcoleId(id, idA);
		//Condidat condidat=condidatResponse.getBody();
		if (condidat == null) {
	        String errorMessage = "Condidat not found with ID: " + id;
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	    }
		CertificatInfoDTO certificatInfo= new CertificatInfoDTO();
		
		certificatInfo.setAuto_ecole_nom(condidat.getAuto_ecole().getNom());
		certificatInfo.setAuto_ecole_adresse(condidat.getAuto_ecole().getAdresse());
		certificatInfo.setAuto_ecole_agrement(condidat.getAuto_ecole().getAgrement());
		certificatInfo.setAuto_ecole_id_fiscale(condidat.getAuto_ecole().getId_fiscale());
		certificatInfo.setAuto_ecole_num_registre_com(condidat.getAuto_ecole().getNum_registre_com());
		certificatInfo.setAuto_ecole_ville(condidat.getAuto_ecole().getVille());
		certificatInfo.setAuto_ecole_tel(condidat.getAuto_ecole().getTel());
		certificatInfo.setAuto_ecole_fax(condidat.getAuto_ecole().getFax());
		certificatInfo.setAuto_ecole_num_patent(condidat.getAuto_ecole().getN_patente());
		certificatInfo.setGerant_nom(condidat.getAuto_ecole().getGerant().getNom());
		certificatInfo.setGerant_prenom(condidat.getAuto_ecole().getGerant().getPrenom());
		certificatInfo.setGerant_tel(condidat.getAuto_ecole().getGerant().getTel());
		certificatInfo.setGrant_adress(condidat.getAuto_ecole().getGerant().getAdress());
		certificatInfo.setCondidat_categorie(condidat.getCatgorie());
		certificatInfo.setCondidat_nom(condidat.getAdrs_frenc());
		certificatInfo.setCondidat_prenom(condidat.getNom_frenc());
		certificatInfo.setCin(condidat.getCin());
		certificatInfo.setCondidat_date_inscription(condidat.getDate_inscription());
		certificatInfo.setMon_pratique_nom(service.getMoniteurPratiqueFromServiceEmploye(condidat.getMoniteur_pratique(), idA).getNomEmp());
		certificatInfo.setMon_pratique_prenom(service.getMoniteurPratiqueFromServiceEmploye(condidat.getMoniteur_pratique(), idA).getPrenomEmp());
		certificatInfo.setMon_pratique_npc(service.getMoniteurPratiqueFromServiceEmploye(condidat.getMoniteur_pratique(), idA).getNpcEmp());
		certificatInfo.setMon_theorique_nom(service.getMoniteurTheoriqueFromServiceEmploye(condidat.getMoniteur_theorique(), idA).getNomEmp());
		certificatInfo.setMon_theorique_prenom(service.getMoniteurTheoriqueFromServiceEmploye(condidat.getMoniteur_theorique(), idA).getPrenomEmp());
		certificatInfo.setMon_theorique_npc(service.getMoniteurTheoriqueFromServiceEmploye(condidat.getMoniteur_theorique(), idA).getNpcEmp());
	    
		
		
		
		
		return ResponseEntity.ok(certificatInfo);
	}

	@GetMapping("/CarteCondidat")
	public ResponseEntity<?> GetCarteCondidat(@RequestAttribute Long idA,@RequestBody IdRequest request) {
//		ResponseEntity<Condidat> condidatResponse=GetCondidat(idA,id);
//		Condidat condidat=condidatResponse.getBody();
		Long id=request.getIdC();
		Condidat condidat=service.getCondidatByIdAndAutoEcoleId(id, idA);
		if (condidat == null) {
	        String errorMessage = "Condidat not found with ID: " + id;
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	    }
		CarteCondidatInfo carteCondidat =new CarteCondidatInfo();
		carteCondidat.setNom(condidat.getAdrs_frenc());
		carteCondidat.setPrenom(condidat.getNom_frenc());
		carteCondidat.setCin(condidat.getCin());
		carteCondidat.setNum_contrat(condidat.getNum_contrat());
		carteCondidat.setAdress(condidat.getAdrs_frenc());
		carteCondidat.setTel(condidat.getTel());
		carteCondidat.setEmail(condidat.getEmail());
		carteCondidat.setDate_naissance(condidat.getDate_naissance());
		carteCondidat.setProfession(condidat.getProfession());
		carteCondidat.setCategorie(condidat.getCatgorie());
		carteCondidat.setDate_inscription(condidat.getDate_inscription());
		carteCondidat.setDate_fin_contrat(condidat.getDate_fin_contrat());
		carteCondidat.setMontant(condidat.getMontant());
		
		carteCondidat.setNom_moniteur_theorique(service.getMoniteurTheoriqueFromServiceEmploye(condidat.getMoniteur_theorique(), idA).getNomEmp());
		carteCondidat.setPrenom_moniteur_theorique(service.getMoniteurTheoriqueFromServiceEmploye(condidat.getMoniteur_theorique(), idA).getPrenomEmp());

		
		return ResponseEntity.ok(carteCondidat);
	}
	    
	
	  //*********AUTRE SERVICE **********************
	 @GetMapping("/getVehicule")
     public VehiculeDTO getVehiculeFromServiceVehicule(@RequestAttribute Long idA ,@RequestBody IdRequest request) {
    	 return service.getVehiculeFromServiceVehicule(idA, request.getIdv());
     }
     @GetMapping("/moniteurPratique")
     public MoniteurDTO moniteurPratique(@RequestAttribute Long idA ,@RequestBody IdRequest request) {
    	 return service.getMoniteurPratiqueFromServiceEmploye(request.getIdm(), idA);
     }
     @GetMapping("/moniteurTheorique")
     public MoniteurDTO moniteurTheorique(@RequestAttribute Long idA ,@RequestBody IdRequest request) {
    	 return service.getMoniteurTheoriqueFromServiceEmploye(request.getIdm(), idA);
     }
	    
	    
	
}











