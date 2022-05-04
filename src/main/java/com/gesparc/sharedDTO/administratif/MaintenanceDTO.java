package com.gesparc.sharedDTO.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.gesparc.sharedDTO.CarteJockerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDTO implements Serializable {


    private String article;
    
    private Date date_sortie;
    
    private int index_maintenance;
    
    private String capacite_exploit;

    @JsonIgnore
    private VehiculeDTO vehicule;

   
}
