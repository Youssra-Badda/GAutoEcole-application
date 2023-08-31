package com.condidat.dto;

import java.util.Date;

import com.condidat.entities.TypePaiement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecuInfoDTO {
	private String auto_ecole_nom;
    private String auto_ecole_adresse;
    private String auto_ecole_id_fiscale;
    private String auto_ecole_tel;
    private String  email;
    private int num_patent;
    private String profile;
    private String site_web;
    
    
    
    
    private String condidat_categorie;
    private String condidat_nom;
    private String condidat_prenom;
    private int condidat_montant;
    private String ref_web;
    
    
    private TypePaiement type_paiement;
    private Date date_paiement;
    private int montant_paye;
    private int reste;
}
