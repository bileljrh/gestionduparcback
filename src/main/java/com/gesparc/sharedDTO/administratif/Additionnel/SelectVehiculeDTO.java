package com.gesparc.sharedDTO.administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectVehiculeDTO implements Serializable 
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
