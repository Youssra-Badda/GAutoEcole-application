package com.cours.dao;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cours.entities.Presence;
import com.cours.entities.Seance;

import com.cours.dto.CondidatDTO;


public interface IPresenceRepository extends JpaRepository<Presence,Long> {
	
	 
	 public Presence findBySeanceAndCondidat( Seance seance,Long condidat);
	
	
	 @Query("SELECT p FROM Presence p " +
		       "WHERE p.seance.id = :seanceId " +
		       
		       "AND p.est_present = true")
		List<Presence> findBySeanceIdAndEstPresentTrue(@Param("seanceId") Long seanceId);

}
