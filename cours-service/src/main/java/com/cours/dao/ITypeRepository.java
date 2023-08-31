package com.cours.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cours.entities.Type;

public interface ITypeRepository extends JpaRepository<Type,Long> {
	
	@Query("SELECT t FROM Type t " +
		       "WHERE t.libelle = :libelle " +
		       "AND t.auto_ecole.id = :autoEcoleId ")
		public Type findByLibelle(@Param("libelle") String libelle, @Param("autoEcoleId") Long autoEcoleId);

		@Query("SELECT t FROM Type t WHERE t.auto_ecole.id = :autoEcoleId")
		public List<Type> findAllByAutoEcoleId(@Param("autoEcoleId") Long autoEcoleId);
		
//		@Modifying
//	    @Query("INSERT INTO Type (libelle, auto_ecole_id) VALUES (:libelle, :autoEcoleId)")
//	    void insertWithTypeAndAutoEcoleId(@Param("libelle") String libelle, @Param("autoEcoleId") Long autoEcoleId);


}
