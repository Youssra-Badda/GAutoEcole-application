package com.cours.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdRequest {
       private Long ids;
       private boolean is_Pratique;
       private Long idc;
       private List<Long> seances;
       private Long idv;
       private Long idm;
}
