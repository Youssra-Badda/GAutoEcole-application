package com.condidat.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data 
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Gerant {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String email;
     private String nom;
     private String prenom;
     private String tel;
     private String adress;
}
