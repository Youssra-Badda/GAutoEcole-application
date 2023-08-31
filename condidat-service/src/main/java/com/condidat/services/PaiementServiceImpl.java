package com.condidat.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.condidat.dao.IPaiementRepository;
import com.condidat.entities.Condidat;
import com.condidat.entities.Paiement;


@Service
@Transactional
public class PaiementServiceImpl implements IPaiementService{

	@Autowired
	 private IPaiementRepository  daoPaiement;
	
	
	@Override
	@Transactional
	public Paiement AddPaiement(Paiement p) {
		
		return daoPaiement.save(p);
	}

	@Override
	public Paiement EditPaiement(Paiement p) {
		
		return daoPaiement.save(p);
	}

	@Override
	public void delPaiement(Long id) {
		daoPaiement.deleteById(id);
		
	}

	@Override
	public Paiement getPaiement(Long idP,Long idC,Long idA) {
		
		return daoPaiement.findByIdAndCondidatIdAndAutoEcoleId(idP, idC, idA);
	}
    
	@Override
	public List<Paiement> getGroupOfPaiment( List<Long> idp,Long idC,Long idAuto) {
		List<Paiement > payments = new ArrayList<>();
		idp.forEach(id -> {
			Paiement p = daoPaiement.findByIdAndCondidatIdAndAutoEcoleId(id, idC, idAuto);
			if(p != null) {
				payments.add(p);
			}	
		});
		if(payments.size() == idp.size()) {
			return payments;
		}else {
			throw new EntityNotFoundException("Les paiement n'a pas été tous trouvé !");
		}

	}
	
	
	
//	@Override
//	public Paiement getPaimentByCondidat(Long id) {
//		
//		return daoPaiement.findByIdCondidat(id);
//	}
	@Override
	public List<Paiement> getAllPaiement() {
		
		return daoPaiement.findAll();
	}

	
//	public List<Paiement> getPaiementsByCondidatId(Long idCondidat,Long id_Autoecole) {
//		return daoPaiement.findByCondidatIdAndAutoEcoleId(idCondidat,id_Autoecole);
//	}

	@Override
	public Paiement getByCondidatIdAndNumero(Long id_Condidat, int numero) {
          return daoPaiement.findByCondidatIdAndNumero(id_Condidat, numero);
	}

	@Override
	public List<Paiement> getPaiementsByCondidatId(Long idCondidat, Long id_Autoecole) {
		// TODO Auto-generated method stub
		return daoPaiement.findByCondidatIdAndAutoEcoleId(idCondidat,id_Autoecole);
	}

	
	

}
