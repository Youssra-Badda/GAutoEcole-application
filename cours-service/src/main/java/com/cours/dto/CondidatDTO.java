package com.cours.dto;
import java.util.Date;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondidatDTO {
    private Long id;
    private String cin;
    private Date date_inscription;
    private int num_contrat;
    private String ref_web;
    private String nom_frenc;
    private String nom_arab;
    private String prenom_frenc;
    private String prenom_arab;
    private Date date_naissance;
    private String lieu_nais;
    private String adrs_frenc;
    private String adrs_arab;
    private String tel;
    private String email;
    private String profession;
    private String image_cin;
    private Date date_fin_contrat;
    private String observation;
    private String profile;
    private String etat = "Active";
    private int montant;
    private int nmbr_heure_pr;
    private int nmbr_heure_th;
    private boolean possede_permis;
    private int montant_paye;
    private int reste;
    private List<PermisDTO> permis;
    private CategorieDTO categorie;
    private AutoEcoleDTO auto_ecole;
    // Getters and setters
}
