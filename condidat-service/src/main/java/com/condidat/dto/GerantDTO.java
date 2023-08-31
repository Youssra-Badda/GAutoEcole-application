package com.condidat.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor

public class GerantDTO {

		
	     private Long id;
	     private String email;
	     private String nom;
	     private String prenom;
	     private String tel;
	     private String adress;
	
}
