package com.cours.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoniteurDTO {
	private Long id;
	private String nomEmp;
	private String nomEmpArb;
	private String prenomEmp;
	private String prenomEmpArb;
	private String CIN;
	private LocalDate dateNaissEmp;
	private String lieuNaissEmp;
	private String emailEmp;
	private String tel;
	private LocalDate dateEmbauche;
	private String capnEmp;
	private String npcEmp;
	private String addrEmp;
	private String etat;
	private Long typeEmp; //TypeEmploieDTO
	private String Observations;
	private AutoEcoleDTO autoEcole;
	private List<Long> absences;
	private String typeMoniteur;//TypeMoniteur enum
	private String carteMoniteur;
	private LocalDate expirCarteMonit;
	private List<Long> categories = new ArrayList<>();

}
