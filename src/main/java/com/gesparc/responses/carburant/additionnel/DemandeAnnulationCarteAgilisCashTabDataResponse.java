package com.gesparc.responses.carburant.additionnel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeAnnulationCarteAgilisCashTabDataResponse 
{
    private Long id;
    
    private String nomDeclarant;
    
    private String prenomDeclarant;
    
    private LocalDate dateNaissanceDeclarant;
    
    private String lieuNaissanceDeclarant;
    
    private String numeroCINDeclarant;
    
    private String sexeDeclarant;
    
    private String typeDeclarant;
    
    private String numeroPlaque;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String structure;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int soldeRestant;
    
    private String causeAnnulation;
    
    private LocalDate dateDemande;
    
    private boolean confirmed;
    

}
