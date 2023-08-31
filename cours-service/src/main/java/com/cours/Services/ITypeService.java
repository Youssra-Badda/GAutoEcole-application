package com.cours.Services;

import java.util.List;

import com.cours.entities.Type;
import com.cours.entities.TypePratique;

public interface ITypeService {
     public Type AjouterType(Type type,Long idA);
     public List<Type> GetTypes(Long idA);
     public Type getTypeByLibelle(String libelle,Long idA);
     
     //*******************pratique***************
     public TypePratique AjouterTypePratique(TypePratique type,Long idA);
     public List<TypePratique> GetTypesPratique(Long idA);
     public TypePratique getTypePratiqueByLibelle(String libelle,Long idA);
}
