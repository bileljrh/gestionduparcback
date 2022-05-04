package com.gesparc.sharedDTO.administratif.Additionnel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeTableDataDTO
{
    private Long id;
    
    private String numeroPlaque;
    
    private String genre;
    
    private String marque;
    
    private String type;
    
    private String energie;
    
    private String natureAffectation;
    
    private String structure;
    
    private String beneficiaire;
    
    private String etatVehicule;
}
