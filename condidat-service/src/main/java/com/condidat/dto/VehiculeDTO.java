package com.condidat.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeDTO {
	
    private Long id;
	
    private String img_carte_grise; //image
    private String img_assurance; //image 
    private String img_vignette;
    private String img_visite_tech;
    private String model;
    private int matricule;
    private String fornisseur;
    private String marque;
    

    private String categorie_permis;  ////////
     
    private Date date_p_visete_technique;
    private Date date_vidange;
    private Date date_p_vidange;
    private Date date_assurance;
    private Date date_e_assurance;
    
   
    private AutoEcoleDTO auto_ecole;
    
}
