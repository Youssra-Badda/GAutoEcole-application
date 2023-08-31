package com.condidat.services;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.condidat.dao.ICondidatRepository;
import com.condidat.dto.MoniteurDTO;
import com.condidat.dto.VehiculeDTO;
import com.condidat.entities.Condidat;
import com.condidat.entities.TypeFormation;






@Service
@Transactional
public class CondidatServiceImpl implements ICondidatService {
    

	@Autowired
	private ICondidatRepository dao;
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	@Value("${vehicule-service.url}")
    private String VehiculeServiceUrl;
      
    @Value("${employe-service,url}")
    private String employeServiceUrl;
	
	
	
	@Override
	public Condidat AjouterCondidat(Condidat c) {
		// TODO Auto-generated method stub
		return dao.save(c);
	}

	@Override
	public List<Condidat> getAllCondidat() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Condidat getCondidat(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public void deleteCondidat(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public void deleteAll(Long idAutoE) {
		// TODO Auto-generated method stub
		dao.deleteByAutoEcoleId(idAutoE);
	}

	@Override
	public Condidat modifierCondidat( Condidat c) {
		// TODO Auto-generated method stub
		return dao.save(c);
	}

	@Override
	public List<Condidat> getCondidatByType(TypeFormation type ,Long id_AutoEcole) {
		// TODO Auto-generated method stub
		return dao.findByTypeAndAutoEcoleId(type ,id_AutoEcole);
	}
     
	public List<Condidat> getCandidatsByAutoEcoleId(Long autoEcoleId) {
	        return dao.findByAutoEcoleId(autoEcoleId);
	 }
	
	public Condidat getCondidatByIdAndAutoEcoleId(Long id,Long id_AutoEcole) {
		return dao.findByIdAndAutoEcoleId(id, id_AutoEcole);
	}

	@Override
	public Condidat getCondidatByCin(Long id_autoecole, String cin) {
		// TODO Auto-generated method stub
		return dao.findByAutoEcoleIdAndCIN(id_autoecole, cin);
	}
	
	@Override
	public List<Condidat> getGroupOfCondidat(Long idAuto, List<Long> ids) {
		List<Condidat > condidats = new ArrayList<>();
		ids.forEach(id -> {
			Condidat emp = dao.findByIdAndAutoEcoleId(id,idAuto);
			if(emp != null) {
				condidats.add(emp);
			}	
		});
		if(condidats.size() == ids.size()) {
			return condidats;
		}else {
			throw new EntityNotFoundException("Les condidat n'a pas été tous trouvé !");
		}

	}
	
	public List<Condidat> getcondidatByCategorieLibelle(String libelle ,Long idA) {
		return dao.findByCategoireLibelleAndAutoEcoleId(libelle, idA) ;

	    }
		
	

///****************************AUTRE SERVICE*************************************


public VehiculeDTO getVehiculeFromServiceVehicule(Long idA , Long idV) {
	   String VehiculeUrl=VehiculeServiceUrl + "/vehicles/show-vehicle-by-id/"+idV; //zidi url
	   ResponseEntity<VehiculeDTO> response = restTemplate.exchange(
			   VehiculeUrl,
            HttpMethod.GET,
            null, 
            VehiculeDTO.class
        );
	   if (response.getStatusCode() == HttpStatus.OK) {
        return response.getBody();
    } else {
        
        return null;
    }
}

@Override
public MoniteurDTO getMoniteurTheoriqueFromServiceEmploye(Long idM, Long idA) {
	   String MoniteurUrl=employeServiceUrl + "/moniteurTheorique/"+idM; //zidi url
	ResponseEntity<MoniteurDTO> response = restTemplate.exchange(
			   MoniteurUrl,
	      HttpMethod.GET,
	      null, 
	      MoniteurDTO.class
	  );
	if (response.getStatusCode() == HttpStatus.OK) {
	  return response.getBody();
	} else {
	  
	  return null;
}
}

@Override
public MoniteurDTO getMoniteurPratiqueFromServiceEmploye(Long idM, Long idA) {
	String MoniteurUrl=employeServiceUrl + "/moniteurPratique/"+idM; //zidi url
	ResponseEntity<MoniteurDTO> response = restTemplate.exchange(
			    MoniteurUrl,
			    HttpMethod.GET,
			    null, 
			    MoniteurDTO.class
	);
	if (response.getStatusCode() == HttpStatus.OK) {
	   return response.getBody();
	}else {
	
	  return null;
	}

}
}

	
	

