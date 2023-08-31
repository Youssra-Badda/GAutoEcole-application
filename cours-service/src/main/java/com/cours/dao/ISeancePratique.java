package com.cours.dao;

import java.time.LocalDate;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cours.dto.MoniteurDTO;

import com.cours.entities.SeancePratique;
import com.cours.entities.SeanceTheorique;


public interface ISeancePratique extends JpaRepository<SeancePratique,Long> {
	//List<SeancePratique> findByDateSeanceAndMoniteurAndVehicule(LocalDate date, Moniteur m,Vehicule v);
	
	
    @Query("SELECT st FROM SeancePratique st WHERE st.dateSeance = :dateSeance AND st.moniteur = :moniteur")
	List<SeancePratique> findByDateSeanceAndMoniteurAndAutoEcoleId(LocalDate dateSeance , Long moniteur);

//    
//    @Query("SELECT st FROM SeancePratique st  WHERE st.moniteur.autoEcole.id = :autoEcoleId")
//    List<SeancePratique> findByAutoEcoleId(@Param("autoEcoleId") Long autoEcoleId);
//    
//    @Query("SELECT st FROM SeancePratique st  WHERE st.id = :seanceId AND st.moniteur.autoEcole.id = :autoEcoleId")
//    SeancePratique findByIdAndAutoEcoleId(@Param("seanceId") Long seanceId, @Param("autoEcoleId") Long autoEcoleId);
//
//    @Query("SELECT s FROM SeancePratique s  WHERE :condidatId IN (s.condidat) AND st.moniteur.autoEcole.id = :autoEcoleId")
//    List<SeancePratique> findByCondidatId(Long condidatId,Long autoEcoleId);
    
	
	
	// Récupérer toutes les séances associées à une auto-école spécifique par son ID
//    @Query("SELECT s FROM SeancePratique s WHERE s.moniteur IN (SELECT m.id FROM Moniteur m WHERE m.auto_ecole.id = :autoEcoleId)")
//    List<SeancePratique> findByAutoEcoleId(@Param("autoEcoleId") Long autoEcoleId);
//    
//    // Récupérer une séance spécifique par son ID et l'ID de l'auto-école de son moniteur associé
//    @Query("SELECT s FROM SeancePratique s WHERE s.id = :seanceId AND s.moniteur IN (SELECT m.id FROM Moniteur m WHERE m.auto_ecole.id = :autoEcoleId)")
//    SeancePratique findByIdAndAutoEcoleId(@Param("seanceId") Long seanceId, @Param("autoEcoleId") Long autoEcoleId);
//
//    // Récupérer toutes les séances d'un condidat spécifique par son ID et l'ID de l'auto-école du moniteur associé
//    @Query("SELECT s FROM SeancePratique s WHERE :condidatId MEMBER OF s.condidat AND s.moniteur IN (SELECT m.id FROM Moniteur m WHERE m.auto_ecole.id = :autoEcoleId)")
//    List<SeancePratique> findByCondidatIdAndAutoEcoleId(@Param("condidatId") Long condidatId, @Param("autoEcoleId") Long autoEcoleId);
//	
	

//    @Query("SELECT s FROM SeancePratique s WHERE :condidatId IN (s.condidat) AND s.moniteur.autoEcole.id = :autoEcoleId")
//    List<SeancePratique> findByCondidatIdAndAutoEcoleId(@Param("condidatId") Long condidatId, @Param("autoEcoleId") Long autoEcoleId);


}
