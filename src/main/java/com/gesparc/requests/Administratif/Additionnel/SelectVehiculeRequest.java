package com.gesparc.requests.Administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectVehiculeRequest implements Serializable 
{
    private Long id;
    
    private String numeroPlaque;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private int agePermis;
}
