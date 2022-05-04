package com.gesparc.responses.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.gesparc.responses.CartePlafondResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceResponse 
{

    private String article;
    
    private Date date_sortie;
    
    private int index_maintenance;
    
    private String capacite_exploit;
    

    @JsonIgnore
    private VehiculeResponse vehicule;

}
