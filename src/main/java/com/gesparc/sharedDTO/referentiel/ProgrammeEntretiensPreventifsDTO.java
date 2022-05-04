package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammeEntretiensPreventifsDTO implements Serializable 
{
    private Long id;

    private String code;
    
    private String cycle;
    
    private String designation;
    
    private MarqueVehiculeDTO marque;
    
    private TypeVehiculeDTO type;
    
    private EnergieDTO energie;
}
