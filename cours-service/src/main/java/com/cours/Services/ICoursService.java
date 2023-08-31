package com.cours.Services;

import java.util.List;



import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cours.dto.CondidatDTO;
import com.cours.dto.MoniteurDTO;
import com.cours.dto.VehiculeDTO;
import com.cours.entities.Seance;
import com.cours.entities.SeancePratique;
import com.cours.entities.SeanceTheorique;




public interface ICoursService {
	
	 public CondidatDTO getCondidatFromServiceCondidat(Long idA, Long idc);
	 public VehiculeDTO getVehiculeFromServiceVehicule(Long idA , Long idV);
	 public MoniteurDTO getMoniteurTheoriqueFromServiceEmploye(Long idM ,Long idA);
	 public MoniteurDTO getMoniteurPratiqueFromServiceEmploye(Long idM ,Long idA);
	
	
	 public  ResponseEntity<?> ajouterCour(SeanceTheorique s,Long idA);
	
	 public List<SeanceTheorique> getAllCours(Long idA);
	// public Categorie getByLibelle(String libelle);
	 
	 
	 public  ResponseEntity<String> deleteSeance(Long id_seance,Long id_AutoEcole);
	 public void deleteAllSeance(Long id_AutoEcole);
	 public  ResponseEntity<?>  updateSeanceTheorique(Long ids,Long idA,SeanceTheorique updatedSeanceTheorique);

	 public  ResponseEntity<?>  getSeanceById(Long ids,Long idA,boolean isPratique);
	 public List<SeanceTheorique> getGroupOfCours( List<Long> ids,Long idAuto);
	 // public List<SeanceTheorique> getSeanceTheoriqueByCondidat(Long idC,Long idA);
	 ////////////////////////////
	 public ResponseEntity<?> ajouterCourPratique(SeancePratique st,Long idA);
	 public List<SeancePratique>  getAllCoursPratique(Long idA);
	
	 public void deleteAllSeancePratique(Long id_AutoEcole ) ;
	 public ResponseEntity<String> deleteSeancePratique(Long id_seance, Long id_AutoEcole);
	 public ResponseEntity<?> updateSeancePratique(Long ids,Long idA,SeancePratique updatedSeanceTheorique);
	 public List<SeancePratique> getGroupOfCoursPratique( List<Long> idSeance,Long idAuto);
	// public  List<SeancePratique> getSeancePratiqueByCondidat(Long condidatId,Long idA);
	 /////////////////////////////
	 public ResponseEntity<String> marquerPresence(Long seanceId,Long idA, Long condidatId, boolean estPresent,boolean isPratique) ;
	 public  ResponseEntity<?>  getCandidatsPresentsBySeance(Long seanceId,Long idA,boolean isPratique);
	 public List<?> getSeanceavecPresence(Long idA,boolean isPratique);
	 
}
