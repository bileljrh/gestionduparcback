package com.gesparc.responses.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueAffectationCarteJockerTabDataResponse 
{
    private Long id;
    
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private Long idCarte;
    
    private String numeroPlaque;
    
    private LocalDate dateAffectation;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private String typeCarburant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private int nombreAffectation;
    
    private int solde;
    
    private LocalDate dateFinValidite;
    
    private boolean confirmed;
    
    private boolean validated;
}
