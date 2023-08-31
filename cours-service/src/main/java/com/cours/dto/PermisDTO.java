package com.cours.dto;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermisDTO {
	
	private Long id;
	private Date Date_obtnetion;
	private String Lieu_obtention;
	private String pcn;
}
