package com.gesparc.responses.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammeEntretiensPreventifsResponse implements Serializable 
{
    private Long id;
    
    private String code;
    
    private String cycle;
    
    private String designation;
    
    private MarqueVehiculeResponse marque;
    
    private TypeVehiculeResponse type;
    
    private EnergieResponse energie;

}
