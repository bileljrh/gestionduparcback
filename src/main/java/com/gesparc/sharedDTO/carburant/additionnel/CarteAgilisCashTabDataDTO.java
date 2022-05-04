package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteAgilisCashTabDataDTO 
{
    private Long id;

    private Long idVehicule;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int montant;
    
    private LocalDate dateFinValidite;
    
    private String numeroPlaque;
    
    private String codeStructure;

    private String designationStructure;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
}

