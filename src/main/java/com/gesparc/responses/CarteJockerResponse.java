package com.gesparc.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.gesparc.responses.administratif.VehiculeResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteJockerResponse
{
    VehiculeResponse vehicule;
    
    private Long id;
    
    private String numeroCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDemande;
    
    private int nombreAffectation;
    
    private int solde;
    
    private boolean confirmed;
    
    private LocalDate dateFinValiditee;
    
    private boolean affected;
    
    private boolean demandDesafectation;
    
    private boolean requested;
}
