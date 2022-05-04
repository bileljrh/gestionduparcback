package com.gesparc.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeAnnulationCartePlafondResponse
{
    private Long id;
    
    private String nomDeclarant;
    
    private String prenomDeclarant;
    
    private LocalDate dateNaissanceDeclarant;
    
    private String lieuNaissanceDeclarant;
    
    private String numeroCINDeclarant;
    
    private String sexeDeclarant;
    
    private String typeDeclarant;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int montant;
    
    private String causeAnnulation;
    
    private LocalDate dateDemande;
    
    private boolean confirmed;
}
