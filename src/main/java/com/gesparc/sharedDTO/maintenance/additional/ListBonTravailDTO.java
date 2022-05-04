package com.gesparc.sharedDTO.maintenance.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListBonTravailDTO 
{
	private long id;
	
	private AtelierBonTravailDTO atelier;
	
	private DemandeMaintenanceBonTravailDTO demandeBonTravail;
}
