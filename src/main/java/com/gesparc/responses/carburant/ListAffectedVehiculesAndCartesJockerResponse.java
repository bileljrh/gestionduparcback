package com.gesparc.responses.carburant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListAffectedVehiculesAndCartesJockerResponse 
{
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private String numeroPlaque;
    
    private String structure;
    
    private String typeCarburant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private Long idCarte;
    
    private String numeroCarte;
    
    private LocalDate dateAffectation;
    
    private LocalDate dateDemandeAffectation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateFinValiditee;
    
    private int solde;
    
    private int nombreAffectation;
}
