package com.gesparc.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueDesaffectationCarteJockerRequest 
{
    private Long id;
    
    private Long idCarte;
    
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private String numeroCarte;
    
    private LocalDate dateAffectation;
    
    private LocalDate dateDemandeAffectation;
    
    private LocalDate dateDemandeDesaffectation;
    
    private LocalDate dateConfirmationDesaffectation;
    
    private int soldeAffectation;
    
    private int soldeDesaffectation;
    
    private int nombreAffectation;
    
    private String numeroPlaque;
    
    private String structure;
    
    private String typeCarburant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String note;
}
