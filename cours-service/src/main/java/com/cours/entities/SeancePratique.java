package com.cours.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeancePratique extends Seance{
       
		@ManyToOne(cascade=CascadeType.MERGE)
	    @JoinColumn(name="id_typePratique")  
		private TypePratique type; 
	
//	@ManyToOne(cascade=CascadeType.MERGE)
//    @JoinColumn(name="id_Vehicule") 
	private Long vehicule;
	
}
