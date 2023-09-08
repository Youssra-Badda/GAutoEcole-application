package com.cours.controllers;

import java.util.ArrayList;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import com.cours.entities.SeancePratique;
import com.cours.entities.IdRequest;

import com.cours.Services.ICoursService;
import com.cours.Services.ITypeService;
import com.cours.dao.ISeancePratique;
import com.cours.entities.SeanceTheorique;
import com.cours.entities.Type;
import com.cours.entities.TypePratique;


import jakarta.validation.Valid;

import com.cours.dto.*;

@RestController
@RequestMapping("/cours")
public class CoursController {
     @Autowired
     private ICoursService coursService;
     
     
     @Autowired
     private ITypeService typeService;
     
     @Autowired
     private RestTemplate restTemplate;

     @Value("${condidat-service.url}")
     private String condidatServiceUrl;
     
     
     
     
     @GetMapping("/")
     public ResponseEntity<String> getHelloMessageFromCondidatService() {
        return ResponseEntity.ok("Message from Condidat Service");
//         String condidatUrl = condidatServiceUrl + "/";
//         ResponseEntity<String> response = restTemplate.exchange(
//             condidatUrl,
//             HttpMethod.GET,
//             null,
//             String.class
//         );
//
//         if (response.getStatusCode() == HttpStatus.OK) {
//             String helloMessage = response.getBody();
//             return ResponseEntity.ok("Message from Condidat Service: " + helloMessage);
//         } else {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//         }
     }
     
  //****************COMMUNICATION AVEC AUTRE SERVICE************************
     
     @GetMapping("/getCondidat")
     public CondidatDTO getCondidatFromCondidatService(@RequestAttribute Long idA, @RequestBody IdRequest request) {
    	 return coursService.getCondidatFromServiceCondidat(idA,request.getIdc());
     }
     @GetMapping("/getVehicule")
     public VehiculeDTO getVehiculeFromServiceVehicule(@RequestAttribute Long idA ,@RequestBody IdRequest request) {
    	 return coursService.getVehiculeFromServiceVehicule(idA, request.getIdv());
     }
     @GetMapping("/moniteurPratique")
     public MoniteurDTO moniteurPratique(@RequestAttribute Long idA ,@RequestBody IdRequest request) {
    	 return coursService.getMoniteurPratiqueFromServiceEmploye(request.getIdm(), idA);
     }
     @GetMapping("/moniteurTheorique")
     public MoniteurDTO moniteurTheorique(@RequestAttribute Long idA ,@RequestBody IdRequest request) {
    	 return coursService.getMoniteurTheoriqueFromServiceEmploye(request.getIdm(), idA);
     }
     
 //*****************************************************************************   
    
     
    @PostMapping("/AddCoursTheorique") 
    public  ResponseEntity<?> SeanceTheorique( @Valid @RequestBody SeanceTheorique st,@RequestAttribute Long idA ) {
  
    	return coursService.ajouterCour(st,idA);
    }
    
    @PutMapping("/EditSeanceTheorique")
    public  ResponseEntity<?>  modifierSeance(@RequestAttribute Long idA ,@Valid  @RequestBody SeanceTheorique st ) {
        Long idS=st.getId();
    	return coursService.updateSeanceTheorique(idS, idA, st);
    }
    
    
    @GetMapping("/AllCoursTheorique")
     public List<SeanceTheorique>  afficherCours(@RequestAttribute Long idA ){
    	return coursService.getAllCours(idA);
    }
    
    @GetMapping("/Cours")//autoecole id
    public ResponseEntity<?> getseancebyId(@RequestBody IdRequest request,@RequestAttribute Long idA ,@RequestParam boolean isPratique){
    	Long idS=request.getIds();
    	return coursService.getSeanceById(idS,idA, isPratique);
    }
    
    @PostMapping("/listCoursTheorique")
    public ResponseEntity<List<SeanceTheorique>> getListCondidat(@RequestAttribute Long idA,@RequestBody IdRequest request){
    	List<Long> seance=request.getSeances();
    	System.out.println(seance);
        return  ResponseEntity.status(HttpStatus.CREATED).body(coursService.getGroupOfCours(seance,idA));
    			
    }
    
    
    @PostMapping("/AddTypeTheorique")
    public Type ajouterType(@RequestBody Type t,@RequestAttribute Long idA ) {
    	//t.setAuto_ecole(idA);
    	return typeService.AjouterType(t,idA);
    		
    }
    
    @GetMapping("/TypesTheorique")
    public List<Type> getAllTypes(@RequestAttribute Long idA ){
    	return typeService.GetTypes(idA);
    }
    
    

   

     
     @DeleteMapping("/deleteCoursTheorique")
     public  ResponseEntity<String> deleteSeance(@RequestBody IdRequest request,@RequestAttribute Long idA) {
        	Long idS=request.getIds();
    	return  coursService.deleteSeance(idS,idA);
    	// System.out.println("la supression est effectue");
     }
     @DeleteMapping("/CoursTheorique/deleteAll")
     public void deleteSeance(@RequestAttribute Long idA) {
    	 coursService.deleteAllSeance(idA);
     }
     
//     @PostMapping("/candidats/seances")
//     public ResponseEntity<?> getSeancesByCondidatId(@RequestBody IdRequest request,@RequestAttribute Long idA ,@RequestParam Boolean isPratique) {
//    	 Long condidatId=request.getIdc();
//    	 if(isPratique==true) {
//    		 List<SeanceTheorique> seances = coursService.getSeanceTheoriqueByCondidat(condidatId,idA);
//             return ResponseEntity.ok(seances);
//    	 }else {
//    		 List<SeancePratique> seances = coursService.getSeancePratiqueByCondidat(condidatId,idA);
//             return ResponseEntity.ok(seances);
//    	 }
//         
//     }
     
     /********************************************************************** 
           **********************Cours Pratique**********************
     **********************************************************************/ 
     
     @PostMapping("/AddTypePratique")
     public TypePratique ajouterType(@Valid @RequestBody TypePratique t,@RequestAttribute Long idA) {
     	return typeService.AjouterTypePratique(t,idA);
     		
     }
     
     @GetMapping("/TypesPratique")
     public List<TypePratique> getAllTypesPra(@RequestAttribute Long idA){
     	return typeService.GetTypesPratique(idA);
     }
     
     @PostMapping("/AddCoursPratique") //autoEcole
     public  ResponseEntity<?> SeancePratique(@RequestBody SeancePratique sp,@RequestAttribute Long idA ) {
   
     	return coursService.ajouterCourPratique(sp,idA);

     }
     
     @PutMapping("/EditSeancePratique")
     public  ResponseEntity<?>  modifierSeancePratique(Long idS,@RequestAttribute Long idA,@Valid @RequestBody SeancePratique sp) {
    	 idS=sp.getId();
    	 return coursService.updateSeancePratique(idS, idA, sp);
    	// return coursService.modifierSeancePratique(idS,idA,sp);
     }
     
     @GetMapping("/CoursPratique")
     public List<SeancePratique>  afficherCoursPratique(@RequestAttribute Long idA){
    	return coursService.getAllCoursPratique(idA);
     }
     @PostMapping("/listCoursPratique")
     public ResponseEntity<List<SeancePratique>> getListCondidat2(@RequestAttribute Long idA,@RequestBody IdRequest request){
     	List<Long> seance=request.getSeances();
     	System.out.println(seance);
         return  ResponseEntity.status(HttpStatus.CREATED).body(coursService.getGroupOfCoursPratique(seance,idA));
     			
     }

     
     
     @DeleteMapping("/delete/coursPratique")
     public ResponseEntity<String> deleteSeancePratique( @RequestBody IdRequest request,@RequestAttribute Long idA) {
     	Long idS=request.getIds();
    	return  coursService.deleteSeancePratique(idS, idA);
    	// System.out.println("la supression est effectue");
     }
     
     @DeleteMapping("CoursPratique/deleteAll")
     public void deleteSeancePratique(@RequestAttribute Long idA) {
    	 coursService.deleteAllSeancePratique(idA);
     } 
     
     
   
 /////////////////////Gestion Presence/////////////////////////   
     
     
     
    
    
    @RequestMapping("/MarquerPresence")
    public ResponseEntity<String> marquerPresence( @RequestBody IdRequest request,@RequestAttribute Long idA,@RequestParam boolean isPresent ,@RequestParam boolean isPratique ) {
    	Long idS=request.getIds();
    	Long idC=request.getIdc();
    	return coursService.marquerPresence( idS,idA, idC,  isPresent,  isPratique);
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body(coursService.marquerPresence( idS,  idC,  isPresent,  isPratique));

    }
     
// seances/12/candidats/presents?isPratique=true

    @GetMapping("/seances/candidats/presents")
    public  ResponseEntity<?>  getCandidatsPresents(@RequestBody IdRequest request ,@RequestAttribute Long idA,@RequestParam boolean isPratique) {
    	Long idS=request.getIds();
    	ResponseEntity<?>  candidatsPresents = coursService.getCandidatsPresentsBySeance(idS,idA,isPratique);
        return ResponseEntity.ok(candidatsPresents);
    }
    
    @GetMapping("/presence/seances")
    public List<?> getSeanceAvecPresence(@RequestAttribute Long idA,@RequestParam boolean isPratique){
    	return coursService.getSeanceavecPresence(idA,isPratique);
    }
     
     
}
