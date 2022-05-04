package com.gesparc.requests.Administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomSearchingTaxeCirculationRequest 
{
    private int poidsMin;
    
    private int poidsMax;
    
    private boolean parPlace;
    
    private boolean parPoids;
    
    private int nombrePlacesMin;
    
    private int nombrePlacesMax;
    
    private LocalDate dateDebutCirculationMin;
    
    private LocalDate dateDebutCirculationMax;
    
    private LocalDate dateFinCirculationMin;
    
    private LocalDate dateFinCirculationMax;
    
    private int montantMin;
    
    private int montantMax;
    
    private LocalDate dateFinValiditeMin;
    
    private LocalDate dateFinValiditeMax;
}
