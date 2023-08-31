package com.condidat.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.condidat.entities.Gerant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AutoEcole {
    
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
   // @NotBlank(message = "This field is required")
    private String nom;
    
    private String logo;
    private Date date_ouverture;
    private int num_registre_com;
    private String id_fiscale;
    private Date date_autorisation;
    private int agrement;
    private int n_patente;
    private String image_rc;
    private String image_agrement;
    private int num_cnss;
    private String ice;
    private int num_cmpt;
    private Double tva;
    private String pays;
    private String tel;
    private String ville;
    private String adresse;
    private String fax;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="id_gerant")
    private Gerant gerant;
      
}
