package com.condidat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.condidat.entities.Condidat;
import com.condidat.entities.TypeFormation;

@RepositoryRestResource
public interface ICondidatRepository extends JpaRepository<Condidat,Long>
{
	   @Query("SELECT c FROM Condidat c WHERE c.type = :type AND c.auto_ecole.id = :autoEcoleId")
	    List<Condidat> findByTypeAndAutoEcoleId(@Param("type") TypeFormation type, @Param("autoEcoleId") Long autoEcoleId);

	    @Query("SELECT c FROM Condidat c WHERE c.auto_ecole.id = :autoEcoleId")
	    List<Condidat> findByAutoEcoleId(Long autoEcoleId);

	    @Modifying
	    @Query("DELETE FROM Condidat c WHERE c.auto_ecole.id = :autoEcoleId")
	    void deleteByAutoEcoleId(Long autoEcoleId);
	
	
	
	    @Query("SELECT c FROM Condidat c WHERE c.auto_ecole.id = :autoEcoleId AND c.cin = :cin")
	    Condidat findByAutoEcoleIdAndCIN(@Param("autoEcoleId") Long autoEcoleId, @Param("cin") String cin);
	    
       
        
       @Query("SELECT c FROM Condidat c WHERE c.id = :id AND c.auto_ecole.id = :autoEcoleId AND etat = 'Active'")
        public Condidat findByIdAndAutoEcoleId(Long id,Long autoEcoleId);
       
     //  @Query("SELECT c FROM Condidat c WHERE  c.catgorie.libelle = :libelle And c.auto_ecole.id = :autoEcoleId AND c.etat = 'Active'")
       @Query("SELECT c FROM Condidat c WHERE  c.catgorie= :libelle And c.auto_ecole.id = :autoEcoleId AND c.etat = 'Active'")
       public List<Condidat> findByCategoireLibelleAndAutoEcoleId(String libelle ,Long autoEcoleId );

       
}
