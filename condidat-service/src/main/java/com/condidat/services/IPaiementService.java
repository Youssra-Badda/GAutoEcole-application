package com.condidat.services;

import java.util.List;

import com.condidat.entities.Paiement;

public interface IPaiementService  {
     public Paiement AddPaiement(Paiement p);
     public Paiement EditPaiement(Paiement p);
     public void delPaiement(Long id);
  //   public Paiement getPaiement(Long id);
     public List<Paiement> getAllPaiement() ;
     public List<Paiement> getPaiementsByCondidatId(Long idCondidat,Long id_Autoecole);
     public Paiement getByCondidatIdAndNumero(Long id_Condidat,int numero);
     public Paiement getPaiement(Long idp,Long idC,Long idA);
     public List<Paiement> getGroupOfPaiment( List<Long> idp,Long idC,Long idAuto);
     
}
