package com.condidat.entities;

import java.util.List;

import lombok.Data;

@Data
public class IdRequest {
       private Long idc;
       private Long idp;
       private List<Long> condidat_id;
       private List<Long> payement_id;
       private Long idv;
       private Long idm;
       }
