package com.gesparc.requests.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammeEntretiensPreventifsRequest implements Serializable 
{
    private Long id;
    
    private String code;
    
    private String cycle;
    
    private String designation;
    
    private MarqueVehiculeRequest marque;
    
    private TypeVehiculeRequest type;
    
    private EnergieRequest energie;
}
