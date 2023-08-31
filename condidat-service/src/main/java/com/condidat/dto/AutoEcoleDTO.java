package com.condidat.dto;

import java.util.Date;
import java.util.List;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoEcoleDTO {
	 
      private Long id;
      
      
   
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
    
      private GerantDTO gerant;
       
}
