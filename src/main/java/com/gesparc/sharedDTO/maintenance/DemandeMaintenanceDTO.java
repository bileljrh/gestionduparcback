package com.gesparc.sharedDTO.maintenance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeMaintenanceDTO 
{	
    private Long id;
 
    private VehiculeEntity vehicule;
    
    private String numeroDemande;

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
    private VehiculeDTO vehiculedto;
   
    @JsonIgnore
    private BonTravailDataTableDTO bonTravail;
}
