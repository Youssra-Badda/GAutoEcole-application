package com.cours.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class TypePratique {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;
          
          private String libelle;
          
      	@ManyToOne(cascade =CascadeType.MERGE)
          private AutoEcole auto_ecole;
}
