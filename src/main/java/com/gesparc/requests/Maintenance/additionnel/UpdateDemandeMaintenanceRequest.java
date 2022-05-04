package com.gesparc.requests.Maintenance.additionnel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.VehiculeRequest;
import com.gesparc.requests.Maintenance.BonTravailRequest;
import com.gesparc.requests.Maintenance.DemandeMaintenanceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDemandeMaintenanceRequest 
{
	    private Long id;
	    
	    private String numeroDemande;
	    
	    private Long idVehicule;
	    
	    private String numeroSerie;
	    
	    private Long idBeneficiaire;
	    
	    private String nomBeneficiaire;
	    
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
	    
	    private String dateRDV;
	    
	    private String observation;
	    
	    @JsonIgnore
	    private BonTravailRequest bonTravail;
	    
	    @JsonIgnore
	    private VehiculeRequest vehicule;

}
