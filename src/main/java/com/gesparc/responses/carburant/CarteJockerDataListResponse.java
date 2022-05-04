package com.gesparc.responses.carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteJockerDataListResponse 
{
    private Long id;
    
    private Long idVehicule;
    
    private String structure;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroPlaque;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDemande;
    
    private int nombreAffectation;
    
    private int solde;
    
    private boolean affected;
}
