package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteJockerTabDataDTO implements Serializable 
{
    private Long id;
 
    private String numeroCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private int nombreAffectation;
    
    private int solde;
    
    private boolean affected;
    
    private LocalDate dateFinValidite;
    
    private String numeroPlaque;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
}

