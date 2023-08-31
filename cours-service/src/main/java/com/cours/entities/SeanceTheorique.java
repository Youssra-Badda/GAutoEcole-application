package com.cours.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeanceTheorique extends Seance {
	
	
	@ManyToOne(cascade =CascadeType.MERGE)
	@JoinColumn(name="type_id")
     private Type type;
}
