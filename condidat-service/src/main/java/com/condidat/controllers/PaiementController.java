package com.condidat.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.condidat.dto.RecuInfoDTO;
import com.condidat.entities.Condidat;
import com.condidat.entities.IdRequest;
import com.condidat.entities.NotEspece;
import com.condidat.entities.Paiement;
import com.condidat.entities.TypePaiement;
import com.condidat.services.ICondidatService;
import com.condidat.services.IPaiementService;

@RestController
@RequestMapping("/Payement")
public class PaiementController {
	@Autowired
	private IPaiementService Spaiement;
	@Autowired
	private ICondidatService SCondidat;

	
	

	@PostMapping("/makePayment")
	public ResponseEntity<?> makePayment(@RequestAttribute Long idA, @RequestBody Paiement paiement) {
	  //  Condidat condidat = SCondidat.getCondidat(idCondidat);
	   Long idCondidat=paiement.getCondidat().getId();
	  
		Condidat condidat = SCondidat.getCondidatByIdAndAutoEcoleId(idCondidat,idA);
	    if (condidat == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le condidat n'existe pas"); // 409 Conflict
	    }
	    Paiement existingpaim=Spaiement.getByCondidatIdAndNumero(idCondidat,paiement.getNumero());
		if (existingpaim != null) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Le Paiement existe déjà"); // 409 Conflict
	    }
	    
	    
	    
   //ResponseEntity<Condidat> condidat=CondidatController.GetCondidat(ida,idCondidat);
	    int montantPaye = condidat.getMontant_paye() + paiement.getMontant();
	    int reste = condidat.getMontant() - montantPaye;

	    if (reste >= 0) {
	        condidat.setMontant_paye(montantPaye);

	        // Vérifier si le type de paiement est différent de "ESPECE"
	        if (!paiement.getType().equals(TypePaiement.ESPECE)) {
	            // Vérifier si les attributs bank, image et numero ne sont pas null
	            if (paiement.getBank() == null || paiement.getImage() == null || paiement.getNumero() == 0) {
	               // throw new IllegalArgumentException("Les champs bank, image et numero sont obligatoires pour un paiement autre que ESPECE.");
	            	return ResponseEntity.unprocessableEntity().body("Les champs bank, image et numero sont obligatoires pour un paiement autre que ESPECE.");
	            }
	        }

	        // Enregistrer le paiement
	        Paiement newPaiement = Spaiement.AddPaiement(paiement);

	        // Mettre à jour le condidat avec le nouveau montant payé et reste
	        condidat.setReste(reste);
	        SCondidat.modifierCondidat(condidat);

	        return ResponseEntity.ok(newPaiement);
	    } else {
	    	return ResponseEntity.badRequest().body("Le montant du paiement dépasse le montant restant à payer.");	    
	    	}
	    
	}



	
	@PutMapping("/EditPayment")
	public ResponseEntity<?> modifierPaiement(@RequestAttribute Long idA, @RequestBody Paiement paiement) {
	   // Condidat condidat = SCondidat.getCondidat(idC);
		Long idP=paiement.getId();
		Long idC=paiement.getCondidat().getId();
		System.out.println("idc"+idC);
	    Condidat condidat = SCondidat.getCondidatByIdAndAutoEcoleId(idC,idA);
	    if (condidat == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le condidat n'existe pas"); // 409 Conflict
	    }
	    Paiement existingPaiement = Spaiement.getPaiement(idP,idC,idA);

	    int oldMontant = existingPaiement.getMontant();
	    int newMontant = paiement.getMontant();
	    int newReste = condidat.getReste() ;
        int newMontantPaye = condidat.getMontant_paye() ;

	    
	    
//	    int montantPaye = condidat.getMontantPaye() + (newMontant - oldMontant);
//	    int reste = condidat.getMontant() - montantPaye;

	    if (  oldMontant != newMontant) {
//	        int newReste = condidat.getReste() ;//???
//	        int newMontantPaye = condidat.getMontantPaye() ;

	        if (oldMontant > newMontant) {
	            // Le montant existant est supérieur au montant saisi
	            newReste += oldMontant - newMontant;
	            newMontantPaye -= oldMontant - newMontant;
	        } else {
	            // Le montant saisi est supérieur au montant existant
	            newReste -= newMontant - oldMontant;
	            newMontantPaye += newMontant - oldMontant;
	        }
	    }
	        condidat.setReste(newReste);
	        condidat.setMontant_paye(newMontantPaye);

	        existingPaiement.setDate_paiement(paiement.getDate_paiement());
	        existingPaiement.setType(paiement.getType());
	        existingPaiement.setRemarque(paiement.getRemarque());
	        existingPaiement.setMontant(newMontant);
	        existingPaiement.setCondidat(condidat);

	        if (!paiement.getType().equals(TypePaiement.ESPECE)) {
	            if (paiement.getBank() == null || paiement.getImage() == null || paiement.getNumero() == 0) {
	            	return ResponseEntity.unprocessableEntity().body("Les champs bank, image et numero sont obligatoires pour un paiement autre que ESPECE.");
	            }
	            existingPaiement.setBank(paiement.getBank());
	            existingPaiement.setImage(paiement.getImage());
	            existingPaiement.setNumero(paiement.getNumero());
	        }

	        // Enregistrer les modifications dans la base de données
	        Paiement updatedPaiement = Spaiement.AddPaiement(existingPaiement);

	        // Mettre à jour le condidat avec le nouveau montant payé et reste
	        SCondidat.modifierCondidat(condidat);

	        return ResponseEntity.ok(updatedPaiement);
//	    } else {
//	        return ResponseEntity.ok(existingPaiement) ;
//	    }
	}


	
	@DeleteMapping("/deletePayment")
	public ResponseEntity<?> deletePayment(@RequestBody IdRequest request,@RequestAttribute Long idA) {
		
		Long idCondidat=request.getIdC();
		Long idPayment=request.getIdP();
	   // Condidat condidat = SCondidat.getCondidat(idCondidat);
	    Condidat condidat = SCondidat.getCondidatByIdAndAutoEcoleId(idCondidat,idA);
	    if (condidat == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le condidat n'existe pas"); // 409 Conflict
	    }
	    List<Paiement> listPayment=Spaiement.getPaiementsByCondidatId(idCondidat, idA) ;//getPaiementsByCondidatId(idCondidat);
	    Paiement payment=Spaiement.getPaiement(idPayment,idCondidat,idA);//afficherPaiement(idPayment);
	   
	    if(listPayment.contains(payment)) {
	   
	    	int montantPaye = condidat.getMontant_paye() - payment.getMontant();
	    	int reste=condidat.getReste();
		        reste = reste + payment.getMontant();
		  //  if (reste >= condidat.getMontantPaye()) {
		        
		        condidat.setMontant_paye(montantPaye);

		        
		        // Mettre à jour le condidat avec le nouveau montant payé et reste
		        condidat.setReste(reste);
		        SCondidat.modifierCondidat(condidat);
		        // suprimer le paiement
		        SupprimerPaiement(idPayment);
		        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("la supression effectue"); 

		          	
	    }else {
	    	
	    	// throw new IllegalArgumentException("le paiement n'est pas associe a ce condidat");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le paiement n'est pas associe a ce condidat");

	    }
	     
	    
	}


	


	
	
	
	@GetMapping("/paiement")
	public Paiement afficherPaiement( @RequestBody IdRequest request,@RequestAttribute Long idA) {
		Long idC=request.getIdC();
		Long idP=request.getIdP();
		return Spaiement.getPaiement(idP, idC, idA);
		
	}
    @PostMapping("/listpayment/condidat")
    public ResponseEntity<List<Paiement>> getListCondidat(@RequestAttribute Long idA,@RequestBody IdRequest request){
    	List<Long> payementId=request.getPayementId();
    	Long idCondidat=request.getIdC();
        return  ResponseEntity.status(HttpStatus.CREATED).body(Spaiement.getGroupOfPaiment(payementId, idCondidat, idA));
    }
    			
    

	@DeleteMapping("/supprimerpaiement/{id}")
	public void SupprimerPaiement(@PathVariable Long id) {
		 Spaiement.delPaiement(id);
		
	}

	
	  @GetMapping("/condidats/paiements")  
	  public ResponseEntity<?> getPaiementsByCondidatId(@RequestBody IdRequest request, @RequestAttribute Long idA) {
		    Long idCondidat=request.getIdC();      
	        List<Paiement> paiements = Spaiement.getPaiementsByCondidatId(idCondidat, idA);
            Condidat condidat = SCondidat.getCondidatByIdAndAutoEcoleId(idCondidat, idA);
            if (condidat == null) {
    	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le condidat n'existe pas"); // 409 Conflict
    	    }
	        if (paiements.isEmpty()) {
	            // Aucun paiement trouvé pour cet identifiant de condidat et idA
	            String message = "Aucun paiement trouvé pour l'identifiant de condidat " + idCondidat + " et idA " + idA;
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	        } else {
	            // Renvoyer la liste des paiements
	            return ResponseEntity.ok(paiements);
	        }
	  }
	
	
	  @GetMapping("/recu")
	  public ResponseEntity<?> RecuInfo(@RequestBody IdRequest request ,@RequestAttribute Long idA ) {
		  Long idC=request.getIdC();
		  Long idP=request.getIdP();
		  Paiement paiement=Spaiement.getPaiement(idP, idC, idA);
//		  if(paiement==null) {
//			  String errorMessage = "Payment not found with ID: " + idP;
//		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//		  }
		  
		  RecuInfoDTO recu=new RecuInfoDTO();
		  
		  recu.setAuto_ecole_nom(paiement.getCondidat().getAuto_ecole().getNom());
		  recu.setProfile(paiement.getCondidat().getAuto_ecole().getLogo());
		  recu.setAuto_ecole_adresse(paiement.getCondidat().getAuto_ecole().getAdresse());
		  recu.setAuto_ecole_id_fiscale(paiement.getCondidat().getAuto_ecole().getId_fiscale());
		  recu.setAuto_ecole_tel(paiement.getCondidat().getAuto_ecole().getTel());
		  recu.setEmail(paiement.getCondidat().getAuto_ecole().getGerant().getEmail());
		  recu.setNum_patent(paiement.getCondidat().getAuto_ecole().getN_patente());
		  recu.setCondidat_categorie(paiement.getCondidat().getCatgorie());
		  recu.setCondidat_montant(paiement.getCondidat().getMontant());
		  recu.setCondidat_nom(paiement.getCondidat().getNom_frenc());
		  recu.setCondidat_prenom(paiement.getCondidat().getPrenom_frenc());
		  recu.setType_paiement(paiement.getType());
		  recu.setRef_web(paiement.getCondidat().getRef_web());
		  recu.setDate_paiement(paiement.getDate_paiement());
		  recu.setMontant_paye(paiement.getMontant());
		  recu.setReste(paiement.getCondidat().getReste());
		  
		  
		  
		  
		  return  ResponseEntity.ok(recu);
	  }
	
	
}
