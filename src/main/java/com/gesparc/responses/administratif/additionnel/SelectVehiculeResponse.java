package com.gesparc.responses.administratif.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectVehiculeResponse implements Serializable 
{
    private Long id;
    
    private String numeroPlaque;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private Long idBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private int agePermis;
    
    private String typeCarburant;
}
