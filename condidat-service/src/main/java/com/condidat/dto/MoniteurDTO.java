package com.condidat.dto;

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
	private String nom_emp;
	private String nom_emp_arb;
	private String prenom_emp;
	private String prenom_emp_arb;
	private String CIN;
	private LocalDate date_naiss_emp;
	private String lieu_naiss_emp;
	private String email_emp;
	private String tel;
	private LocalDate date_embauche;
	private String capn_emp;
	private String npc_emp;
	private String addr_emp;
	private String etat;
	private Long typeEmp; //TypeEmploieDTO
	private String Observations;
	private AutoEcoleDTO auto_ecole;
	private List<Long> absences;
	private String type_moniteur;//TypeMoniteur enum
	private String carte_moniteur;
	private LocalDate expir_carte_monit;
	private List<Long> categories = new ArrayList<>();
	


}

