package com.gesparc.responses;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class DeclarationPerteCarteJockerResponse 
{
	
	private Long id;
	
	private String nomDeclarant;
	
    private String prenomDeclarant;
    
    private LocalDate dateNaissanceDeclarant;
    
    private String lieuNaissanceDeclarant;
    
    private String numeroCINDeclarant;
    
    private String sexeDeclarant;
    
    private String typeDeclarant;
    
    private String typeDeclaration;
    
    private String numeroPlaque;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String structure;
    
    private String numeroCarte;
    
    private LocalDate dateFinValiditee;
    
    private LocalDate datePerte;
    
    private String lieuPerte;
    
    private int solde;
    
    private String circonstances;
    
    private boolean confirmed;
    
}