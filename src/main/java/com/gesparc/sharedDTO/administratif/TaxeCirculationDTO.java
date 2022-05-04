package com.gesparc.sharedDTO.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxeCirculationDTO implements Serializable 
{
    @JsonIgnore
    VehiculeDTO vehicule;
    
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
    
}
