package com.condidat.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.condidat.dto.MoniteurDTO;
import com.condidat.dto.VehiculeDTO;
import com.condidat.entities.Condidat;
import com.condidat.entities.TypeFormation;

public interface  ICondidatService {
       public Condidat AjouterCondidat(Condidat c);
       public List<Condidat> getAllCondidat();
       public Condidat getCondidat(Long id );
       public void deleteCondidat(Long id );
       public void deleteAll(Long Id_autoEcole);
       public Condidat modifierCondidat(Condidat c );
       public List<Condidat> getCondidatByType(TypeFormation type,Long id_AutoEcole);
       public List<Condidat> getCandidatsByAutoEcoleId(Long autoEcoleId);
       
       
       public Condidat getCondidatByCin(Long id_autoecole,String cin);
       
       public Condidat getCondidatByIdAndAutoEcoleId(Long id_AutoEcole,Long id) ;
       
       public List<Condidat> getGroupOfCondidat(Long idAuto, List<Long> ids);
       
       public List<Condidat> getcondidatByCategorieLibelle(String libelle ,Long idA);
       
       ///////////////////////AUTRE SERVICES/////////////////////////////
       public VehiculeDTO getVehiculeFromServiceVehicule(Long idA , Long idV);
       public MoniteurDTO getMoniteurTheoriqueFromServiceEmploye(Long idM, Long idA);
       public MoniteurDTO getMoniteurPratiqueFromServiceEmploye(Long idM, Long idA);
       
}
