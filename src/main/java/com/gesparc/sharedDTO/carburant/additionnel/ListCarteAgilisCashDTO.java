package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCarteAgilisCashDTO 
{
    private Long idCarte;
   
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int soldeRestant;
    
    private int montant;
    
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String numeroPlaque;
    
    private String designationStructure;
    
    private String codeStructure;
    
    private LocalDate dateFinValidite;
}

