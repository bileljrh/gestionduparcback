package com.gesparc.sharedDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.gesparc.sharedDTO.administratif.VehiculeDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteJockerDTO 
{
    VehiculeDTO vehicule;
    
    private Long id;
    
    private String numeroCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDemande;
    
    private LocalDate dateFinValiditee;
    
    private int nombreAffectation;
    
    private int solde;
    
    private boolean affected;
    
    private boolean demandDesafectation;
    
    private boolean requested;
    
}
