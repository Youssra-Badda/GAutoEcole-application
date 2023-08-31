package com.condidat.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractInfoDTO {
    private String autoEcoleNom;
    private String autoEcoleAdresse;
    private int autoEcoleAgrement;
    private String autoEcoleIdFiscale;
    private int autoEcoleNumRegistreCom;
    private String autoEcoleVille;
    private String autoEcoleTel;
    private String autoEcoleFax;
    private String condidatCategorie;
    private String condidatNom;
    private String condidatPrenom;
    private String condidatLieuNaissance;
    private Date condidatDateNaissance;
    private String condidatAdrsFrenc;
    private Date condidatDateInscription;

    // Getters and setters (you can generate them automatically)
}

