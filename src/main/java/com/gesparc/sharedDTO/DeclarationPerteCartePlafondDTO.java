package com.gesparc.sharedDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationPerteCartePlafondDTO 
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
    
    private LocalDate datePerte;
    
    private Long  idCarteplafond;
    
    private Long  idUser;
    
    private String lieuPerte;
    
    private String circonstances;
    
    private String numeroCarte;
    
    private boolean confirmed;
    
}
