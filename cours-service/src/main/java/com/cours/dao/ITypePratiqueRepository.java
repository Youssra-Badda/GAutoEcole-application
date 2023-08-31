package com.cours.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cours.entities.Type;
import com.cours.entities.TypePratique;

public interface ITypePratiqueRepository extends JpaRepository<TypePratique,Long> {
	@Query("SELECT t FROM TypePratique t " +
		       "WHERE t.libelle = :libelle " +
		       "AND t.auto_ecole.id = :autoEcoleId ")
		public TypePratique findByLibelle(@Param("libelle") String libelle, @Param("autoEcoleId") Long autoEcoleId);

		@Query("SELECT t FROM TypePratique t WHERE t.auto_ecole.id = :autoEcoleId")
		public List<TypePratique> findAllByAutoEcoleId(@Param("autoEcoleId") Long autoEcoleId);
}
