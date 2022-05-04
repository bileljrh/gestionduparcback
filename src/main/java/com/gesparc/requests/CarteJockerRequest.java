package com.gesparc.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.VehiculeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteJockerRequest 
{
    @JsonIgnore
    VehiculeRequest vehicule;
    
    private Long id;
    
    private String numeroCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDemande;
    
    private boolean requested;
    
    private int nombreAffectation;
    
    private int solde;
    
    private boolean affected;
    
    private boolean demandDesafectation;
}
