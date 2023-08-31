package com.condidat.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data 
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Paiement {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date_paiement;
	
	
	@Enumerated(EnumType.STRING)
	private TypePaiement type;
	
	@NotBlank(message = "This field is required")
	private String remarque;
	@NotNull(message = "This field is required")
	private int montant;   //le montant paye
	                      
	
	
	///////////////////////
	
    @NotBlank(message = "This field is required", groups = NotEspece.class)
	private String bank;
	
    @NotBlank(message = "This field is required", groups = NotEspece.class)
	private String image;
	
    @NotNull(message = "This field is required", groups = NotEspece.class)
	private int numero;
	
	///////////////////////
	
	
	@OneToOne(cascade =CascadeType.MERGE)
	@JoinColumn(name="id_condidat")
	private Condidat condidat ;
	
}
