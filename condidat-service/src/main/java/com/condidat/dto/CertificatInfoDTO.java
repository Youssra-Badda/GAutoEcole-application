package com.condidat.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificatInfoDTO {
    private String auto_ecole_nom;
    private String auto_ecole_adresse;//
    private int auto_ecole_agrement;
    private String auto_ecole_id_fiscale;
    private int auto_ecole_num_registre_com;
    private String auto_ecole_ville;
    private String auto_ecole_tel;
    private String auto_ecole_fax;
    private int auto_ecole_num_patent;
    private String gerant_nom;
    private String  gerant_prenom;
    private String grant_adress;
    private String gerant_tel;
    
    private String condidat_categorie;
    private String condidat_nom;
    private String condidat_prenom;
    private String cin;
    
    private Date condidat_date_inscription;
    private String mon_theorique_nom;//moniteur
    private String mon_theorique_prenom;
    private String mon_theorique_npc;
    
    private String mon_pratique_nom;
    private String mon_pratique_prenom;
    private String mon_pratique_npc;

}
