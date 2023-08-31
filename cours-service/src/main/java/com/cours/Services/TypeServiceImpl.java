package com.cours.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cours.dao.IAutoEcoleRepository;
import com.cours.dao.ITypePratiqueRepository;
import com.cours.dao.ITypeRepository;
import com.cours.entities.Type;
import com.cours.entities.TypePratique;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TypeServiceImpl implements  ITypeService{
     
	@Autowired 
	private ITypeRepository typeDao;//typtheorique
	
	@Autowired
	private ITypePratiqueRepository typePraDao;
	@Autowired
	private IAutoEcoleRepository autoecoleDao;
	
	
	@Override
	public Type AjouterType(Type type,Long idA) {
		Type t=typeDao.findByLibelle(type.getLibelle(),idA);
		if(t==null) {
			type.setAuto_ecole(autoecoleDao.findById(idA).get());
			return typeDao.save(type);
		}else {
			System.out.println("le type deja existe");
			return null;
		}
		
	}

	@Override
	public List<Type> GetTypes(Long idA) {
		// TODO Auto-generated method stub
		return typeDao.findAllByAutoEcoleId(idA);
	}
	
	public Type getTypeByLibelle(String libelle,Long idA) {
		return typeDao.findByLibelle(libelle,idA);
	}

	
	
	
	/////////////////////////////////////////////////////////////////
	@Override
	public TypePratique AjouterTypePratique(TypePratique type,Long idA) {
		TypePratique t=typePraDao.findByLibelle(type.getLibelle(),idA);
		if(t==null) {
			type.setAuto_ecole(autoecoleDao.findById(idA).get());
			return typePraDao.save(type);
		}else {
			System.out.println("le type deja existe");
			return null;
		}
	}

	@Override
	public List<TypePratique> GetTypesPratique(Long idA) {
		// TODO Auto-generated method stub
		return typePraDao.findAllByAutoEcoleId(idA);
	}

	@Override
	public TypePratique getTypePratiqueByLibelle(String libelle,Long idA) {
		// TODO Auto-generated method stub
		return typePraDao.findByLibelle(libelle,idA);
	}
	


}
