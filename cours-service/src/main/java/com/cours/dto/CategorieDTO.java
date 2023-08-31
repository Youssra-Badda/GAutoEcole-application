package com.cours.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieDTO {
	
	private Long id;
	private int  note_requise;
	private int nbr_qst;
	private String libelle;
	
}
