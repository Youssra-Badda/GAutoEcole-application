package com.cours.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cours.dto.MoniteurDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Seance {
	
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
      private  Long id;
      
	  @NotNull(message = "La date ne doit pas être nulle")
	  @Future(message = "La date dde seance  doit être dans le future")
      private LocalDate  dateSeance;
      
	  @NotNull(message = "L'heure ne doit pas être nulle")
      private LocalTime heure_debut;
	  
	  @NotNull(message = "L'heure ne doit pas être nulle")
      private LocalTime heure_fin;
	  
	  @NotBlank(message = "This field is required")
      private String permis;
      
      //************les relations**************
//      @ManyToOne(cascade=CascadeType.MERGE)
//      @JoinColumn(name="id_Moniteur") 
      private Long moniteur;
      
//      @JsonManagedReference
//      @OneToMany( cascade = CascadeType.MERGE)
      private List<Long> condidat = new ArrayList<>();
      
      @JsonManagedReference
      @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Presence> presences = new ArrayList<>(); 
      
} 
