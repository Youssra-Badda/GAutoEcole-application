package com.cours.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cours.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Presence {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    
	
	    @JsonBackReference
	    @ManyToOne
	    @JoinColumn(name = "seance_id")
	    private Seance seance;

//	    @ManyToOne
//	    @JoinColumn(name = "condidat_id")
	    private Long condidat;

	    private boolean est_present;


}
