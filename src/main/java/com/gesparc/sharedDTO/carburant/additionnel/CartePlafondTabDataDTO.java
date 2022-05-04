package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartePlafondTabDataDTO implements Serializable 
{
    private Long id;

    private Long idVehicule;
    
    private int montant;
    
    private LocalDate dateValiditee;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private boolean activated;
    
    private String numeroPlaque;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
       
    private String status;
}
