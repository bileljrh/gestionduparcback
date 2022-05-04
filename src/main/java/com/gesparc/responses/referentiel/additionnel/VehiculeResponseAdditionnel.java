package com.gesparc.responses.referentiel.additionnel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.maintenance.additional.DemandeMaintenanceDataTableResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeResponseAdditionnel 
{
	private Long id;
	
    private String marque;

}
