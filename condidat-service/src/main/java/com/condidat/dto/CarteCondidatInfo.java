package com.condidat.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteCondidatInfo {
       
	  private String nom;
	  private String prenom;
	  private String cin;
	  private int num_contrat;
	  private String adress;
	  private String tel;
	  private String email;
	  private Date date_naissance;
	  private String profession ;
	  private String categorie;
	  private Date date_inscription;
	  private Date date_fin_contrat;
	  private int montant;
	  private String nom_moniteur_theorique;
	  private String prenom_moniteur_theorique;
}
