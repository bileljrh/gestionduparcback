package com.gesparc.requests.Administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisiteTechniqueRequest 
{
    @JsonIgnore
    VehiculeRequest vehicule;
    
    private Long id;
    
    private Date datePMC;
    
    private int nombrePlaces;
    
    private int puissanceFiscale;
    
    private int montantVisiteTechnique;
    
    private Date dateDebutValidite;
    
    private Date dateFinValidite;
}
