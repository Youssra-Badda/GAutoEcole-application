package com.condidat.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condidat.dao.IAutoEcoleRepository;
import com.condidat.entities.AutoEcole;


@Service 
@Transactional
public class AutoEcoleServiceImpl  implements IAutoEcoleService{
    
	@Autowired
	private IAutoEcoleRepository AutoEcoleDao; 
	
//	@Override
//	public AutoEcole getAutoEcole(Long id) {
//		
//		return AutoEcoleDao.findById(id).get();
//	}
    
}
