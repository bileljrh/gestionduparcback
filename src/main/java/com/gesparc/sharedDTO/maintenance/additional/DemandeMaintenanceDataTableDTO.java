package com.gesparc.sharedDTO.maintenance.additional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeMaintenanceDataTableDTO implements Serializable 
{

	private Long id;

	private Long idVehicule;

	private String numeroDemande;
	
	private String numeroSerie;
	
	private Long idBeneficiaire;
	
	private String nomBeneficiaire;
	
    private boolean annulation;
	
	private String matriculeBeneficiaire;
		
	private String structure;
	
	private String personnel;
	
	private String ugp;
	
	private String ugpReparation;
	
	private String demandeur;
	
	private Date dateDemande;
	
	private int indexKm;
	
	private String descriptionIntervention;
	
	private String status;
	
	private Date dateRDV;
	
	private String observation;
	
	private String marque;
	 
}
