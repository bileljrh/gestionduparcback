package com.gesparc.sharedDTO.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisiteTechniqueDTO implements Serializable 
{
    @JsonIgnore
    VehiculeDTO vehicule;
    
    private Long id;
    
    private Date datePMC;
    
    private int nombrePlaces;
    
    private int puissanceFiscale;
    
    private int prixAchat;
    
    private int montantVisiteTechnique;
    
    private Date dateDebutValidite;
    
    private Date dateFinValidite;
    
}
