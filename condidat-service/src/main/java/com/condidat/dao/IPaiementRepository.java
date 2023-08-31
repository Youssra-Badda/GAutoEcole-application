package com.condidat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.condidat.entities.Paiement;


@RepositoryRestResource
public interface IPaiementRepository extends JpaRepository<Paiement,Long>{
	public List<Paiement>findByCondidatId(Long idCondidat);
	public Paiement findByCondidatIdAndNumero(Long id_Condidat,int numero);
	
    @Query("SELECT p FROM Paiement p WHERE p.condidat.id = :id AND p.condidat.auto_ecole.id = :autoEcoleId AND p.condidat.etat = 'Active'")
	public List<Paiement> findByCondidatIdAndAutoEcoleId(Long id,Long autoEcoleId );
    
    
    @Query("SELECT p FROM Paiement p WHERE p.id = :idp AND p.condidat.id = :idC AND p.condidat.auto_ecole.id = :autoEcoleId AND p.condidat.etat = 'Active'")
    public Paiement findByIdAndCondidatIdAndAutoEcoleId(Long idp, Long idC, Long autoEcoleId);
}
