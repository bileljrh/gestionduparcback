package com.gesparc.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationPerteCarteAgilisCashRequest 
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
    
    private LocalDate datePerte;
    
    private LocalDate dateConfirmation;
    
    private String lieuPerte;
    
    private String circonstances;
    
    private boolean confirmed;
    
    private Long  idCarteagilis;
    
    private Long  idUser;

}
