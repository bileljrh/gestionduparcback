package com.gesparc.sharedDTO.administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.gesparc.entities.administratif.VehiculeEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisiteTechniqueTableDataDTO 
{
    private Long id;

    private Date datePMC;
    
    private int nombrePlaces;
    
    private int puissanceFiscale;
    
    private int montantVisiteTechnique;
    
    private int prixAchat;
    
    private Date dateDebutValidite;
    
    private Date dateFinValidite;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String Structure;
    
    //private VehiculeEntity vehicule;
}
