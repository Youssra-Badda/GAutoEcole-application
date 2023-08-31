package com.condidat.entities;



import java.util.Date;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.condidat.dto.MoniteurDTO;
import com.condidat.entities.AutoEcole;
import com.condidat.entities.Autre_Permis;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Condidat {
	  @Id
	     @GeneratedValue(strategy = GenerationType.IDENTITY)
	     private Long id;
	     
	     @NotBlank(message = "This field is required")
	     @Pattern(regexp = "^[A-Z]{2}\\d{6}$", message = "Le champ doit contenir 2 lettres majuscules suivies de 6 chiffres")
	     private String cin ;
	     
	 
	     @DateTimeFormat(pattern = "yyyy-MM-dd") // Spécifier le format de la date
	     @PastOrPresent(message = "La date d'inscription doit être anférieure ou égale à la date actuelle")
	     private Date date_inscription;
	     
	     @NotNull(message = "num contrat is required")
	     private int num_contrat;
	     
	     @NotBlank(message = "reference web is required")
	     private String ref_web;
	     
	     @NotBlank(message = "nom francais is required")
	     private String nom_frenc;
	     @NotBlank(message = "nom arab is required")
	     private String nom_arab;
	     @NotBlank(message = "prenom frenc is required")
	     private String prenom_frenc;
	     @NotBlank(message = "prenom is required")
	     private String prenom_arab;
	     
	   
	     @PastOrPresent(message = "La date doit être inférieure ou égale à la date actuelle")
	     private Date date_naissance;                    //au moins 14 ans 
	     
	     @NotBlank(message = "lieu de naissance is required")
	     private String lieu_nais;
	     @NotBlank(message = "adres Francais is required")
	     private String adrs_frenc;
	     @NotBlank(message = "adress arab is required")
	     private String adrs_arab;
	     
	     @NotBlank(message = "tel is required")
	     @Size(min = 10, max = 10, message = "Le numéro doit contenir 10 chiffres")
	     private String tel;
	     
	     @Email(message = "L'adresse email doit être valide")
	     @NotBlank(message = "This field is required")
	     private String email;
	     
	     @NotBlank(message = "profession is required")
	     private String profession;
	     
	     @NotBlank(message = "image cin  is required")
	     private String image_cin;  //image
	     
	     
	    //@NotNull(message="la date fin de contrat is required") 
	     private Date date_fin_contrat;
	   
	     private String observation;
	     @NotBlank(message = "profile is required")
	     private String profile;  //image
	     private String etat="Active";  //Active Archive supprime
	     
	     @NotNull(message = "montant is required")
	     private int montant;
	     @NotNull(message = "nombre heure pratique is required")
	     private int nmbr_heure_pr;
	     @NotNull(message = "nombre heure theorique is required")
	     private int nmbr_heure_th;
	     private boolean possede_permis; //si le condidat deja possede un permis ou non 
	     @Enumerated(EnumType.STRING)
	     private TypeFormation type;
	     
	     private int montant_paye; 
	     private int reste;
	     private String catgorie;
	     
	     private Long moniteur_theorique;
	     private Long moniteur_pratique;
	     private Long Voiture;
	  //   private MoniteurDTO moniteurinfos;
	     //**************Les Relations***************
	     
//	     private List<Long> permis;
//	     private Long categorie;
//	     private Long auto_ecole;
	     
	     @OneToMany(cascade=CascadeType.ALL)
	     @JoinColumn(name="id_permis")
	     private List<Autre_Permis> permis;
	     
//	     @ManyToOne(cascade=CascadeType.MERGE)
//	     @JoinColumn(name="id_cat")  
//	     private Categorie catgorie ;    
//	     
//	     //khas moniteur 
//	     
	     @ManyToOne(cascade=CascadeType.MERGE)
	     @JoinColumn(name="id_AutoE")
	     private AutoEcole auto_ecole;
	     
	    
     
     
     
     
     
}
