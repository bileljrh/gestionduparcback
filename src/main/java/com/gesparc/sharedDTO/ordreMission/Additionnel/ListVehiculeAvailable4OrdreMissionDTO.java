package com.gesparc.sharedDTO.ordreMission.Additionnel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListVehiculeAvailable4OrdreMissionDTO 
{
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private String numeroSerie;
    
    private String typeCarburant;
    
    private String structure;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;
    
}
