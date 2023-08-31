package com.condidat.entities;

import java.util.List;

import lombok.Data;

@Data
public class IdRequest {
       private Long idC;
       private Long idP;
       private List<Long> condidatId;
       private List<Long> payementId;
       private Long idv;
       private Long idm;
       }
