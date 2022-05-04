package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationReparationDTO 
{
    private Long id;

    private String code;
    
    private String designation;
    
    private FamilleOperationReparationDTO familleOperations;
}
