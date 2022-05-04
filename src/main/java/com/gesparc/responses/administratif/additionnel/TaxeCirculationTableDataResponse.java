package com.gesparc.responses.administratif.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxeCirculationTableDataResponse 
{
    private Long id;
    
    private String numeroQuittance;
    
    private boolean parPoids;
    
    private int poids;
    
    private boolean parPlace;
    
    private int nombrePlaces;
    
    private LocalDate dateDebutCirculation;
    
    private LocalDate dateFinCirculation;
    
    private int montant;
    
    private LocalDate dateFinValidite;
    
    private Long idVehicule;
    
    private String numeroPlaque;
}
