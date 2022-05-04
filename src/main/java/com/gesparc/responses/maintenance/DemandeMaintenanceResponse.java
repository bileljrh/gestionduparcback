package com.gesparc.responses.maintenance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.maintenance.additional.BonTravailDataTableResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeMaintenanceResponse 
{
    private Long id;
    
    private Long idVehicule;

    private String structure;

    private String ugp;

}
