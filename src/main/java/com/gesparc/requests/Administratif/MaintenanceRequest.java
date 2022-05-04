package com.gesparc.requests.Administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest 
{
    private String article;
    
    private Date date_sortie;
    
    private int index_maintenance;
    
    private String capacite_exploit;
    

    @JsonIgnore
    private VehiculeRequest vehicule;

}
