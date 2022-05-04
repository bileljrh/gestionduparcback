package com.gesparc.responses.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationReparationResponse 
{
    private Long id;
    
    private String code;
    
    private String designation;
    
    private FamilleOperationReparationResponse familleOperations;
    
}
