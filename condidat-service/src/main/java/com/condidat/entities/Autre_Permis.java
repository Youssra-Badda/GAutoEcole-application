package com.condidat.entities;


import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data 
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Autre_Permis {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date_obtnetion;
	
	@NotBlank(message = "This field is required")
	private String lieu_obtention;
	
	@NotBlank(message = "This field is required")
	private String pcn;
}

