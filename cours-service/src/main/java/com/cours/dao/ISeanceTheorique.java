package com.cours.dao;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cours.dto.MoniteurDTO;

import com.cours.entities.SeancePratique;
import com.cours.entities.SeanceTheorique;

public interface ISeanceTheorique extends JpaRepository<SeanceTheorique, Long> {

	
    @Query("SELECT st FROM SeanceTheorique st WHERE st.dateSeance = :dateSeance AND st.moniteur = :moniteur")
    List<SeanceTheorique> findByDateSeanceAndMoniteurAndAutoEcoleId(@Param("dateSeance") LocalDate dateSeance, @Param("moniteur") Long moniteur);
    
//    @Query("SELECT st FROM SeanceTheorique st  WHERE st.moniteur.autoEcole.id = :autoEcoleId")
//    List<SeanceTheorique> findByAutoEcoleId(@Param("autoEcoleId") Long autoEcoleId);
//    
//    @Query("SELECT st FROM SeanceTheorique st  WHERE st.id = :seanceId AND st.moniteur.autoEcole.id = :autoEcoleId")
//    SeanceTheorique findByIdAndAutoEcoleId(@Param("seanceId") Long seanceId, @Param("autoEcoleId") Long autoEcoleId);
//    
//    
//    @Query("SELECT s FROM SeanceTheorique s  WHERE :condidatId IN (s.condidat) AND st.moniteur.autoEcole.id = :autoEcoleId")
//    List<SeanceTheorique> findByCondidatId(Long condidatId,Long autoEcoleId);
	
	   
        
	    
	    // Récupérer une séance spécifique par l'ID de l'auto-école, la date de séance et l'ID du moniteur
//	    @Query("SELECT s FROM SeanceTheorique s WHERE s.dateSeance = :dateSeance AND s.moniteur IN (SELECT m.id FROM Moniteur m WHERE m.auto_ecole.id = :autoEcoleId) AND s.moniteur = :moniteurId")
//	    List<SeanceTheorique> findByDateSeanceAndMoniteurAndAutoEcoleId(@Param("dateSeance") LocalDate dateSeance, @Param("autoEcoleId") Long autoEcoleId,  Long moniteurId); 
//		 
//		 // Récupérer toutes les séances associées à une auto-école spécifique par son ID
//	    @Query("SELECT s FROM SeanceTheorique s WHERE s.moniteur IN (SELECT m.id FROM Moniteur m WHERE m.auto_ecole.id = :autoEcoleId)")
//	    List<SeanceTheorique> findByAutoEcoleId(@Param("autoEcoleId") Long autoEcoleId);
//	    
//	    // Récupérer une séance spécifique par son ID et l'ID de l'auto-école de son moniteur associé
//	    @Query("SELECT s FROM SeanceTheorique s WHERE s.id = :seanceId AND s.moniteur IN (SELECT m.id FROM Moniteur m WHERE m.auto_ecole.id = :autoEcoleId)")
//	    SeanceTheorique findByIdAndAutoEcoleId(@Param("seanceId") Long seanceId, @Param("autoEcoleId") Long autoEcoleId);

	    // Récupérer toutes les séances d'un condidat spécifique par son ID et l'ID de l'auto-école du moniteur associé
//	    @Query("SELECT s FROM SeanceTheorique s WHERE :condidatId MEMBER OF s.condidat AND s.moniteur IN (SELECT m.id FROM Moniteur m WHERE m.auto_ecole.id = :autoEcoleId)")
//	    List<SeanceTheorique> findByCondidatIdAndAutoEcoleId(@Param("condidatId") Long condidatId, @Param("autoEcoleId") Long autoEcoleId);
}


