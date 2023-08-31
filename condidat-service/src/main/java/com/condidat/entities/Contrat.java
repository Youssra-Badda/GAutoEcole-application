package com.condidat.entities;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data 
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contrat {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
    private String type;
	
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="id_AutoEcole")
    private AutoEcole auto_ecole;
    
    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="id_Condidat")
    private Condidat condidat;
	
}
