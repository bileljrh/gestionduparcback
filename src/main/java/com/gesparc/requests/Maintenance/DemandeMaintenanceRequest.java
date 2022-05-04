package com.gesparc.requests.Maintenance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.requests.Administratif.VehiculeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeMaintenanceRequest 
{
    private Long id;
    
    private String numeroDemande;
    
    private VehiculeEntity vehicule;
    
    private BonTravailEntity bonTravail;
    
    private String numeroSerie;
    
    private Long idBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String structure;
    
    private String personnel;
    
    private String ugp;
    
    private String ugpReparation;
    
    private String demandeur;
    
    @JsonFormat(pattern="YYYY-MM-DD",shape = JsonFormat.Shape.STRING)
    private Date dateDemande;
    
    private int indexKm;
    
    private String descriptionIntervention;
    
    private String status;
    
    private Date dateRDV;
    
    private String observation;
   
}
