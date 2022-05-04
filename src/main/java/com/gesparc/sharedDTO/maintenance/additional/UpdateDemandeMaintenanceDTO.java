package com.gesparc.sharedDTO.maintenance.additional;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.Achat.MarcheDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDemandeMaintenanceDTO 
{

    private Long id;

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
    private BonTravailDTO bonTravail;
    
    @JsonIgnore
    private VehiculeDTO vehicule;
}
