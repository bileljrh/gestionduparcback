package com.gesparc.sharedDTO.administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomSearchingTaxeCirculationDTO 
{
    private int poidsMin;
    
    private int poidsMax;
    
    private boolean parPlace;
    
    private boolean parPoids;
    
    private int nombrePlacesMin;
    
    private int nombrePlacesMax;
    
    private LocalDate dateDebutCirculationMin;
    
    private LocalDate dateFinCirculationMax;
    
    private int montantMin;
    
    private int montantMax;
    
    private LocalDate dateFinValiditeMin;
    
    private LocalDate dateFinValiditeMax;
}
