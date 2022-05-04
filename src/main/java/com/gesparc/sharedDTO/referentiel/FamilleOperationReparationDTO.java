package com.gesparc.sharedDTO.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilleOperationReparationDTO implements Serializable 
{
    @JsonIgnore
    List<OperationReparationDTO> operationsReparation = new ArrayList<>();
   
    private Long id;
    
    private String code;
    
    private String designation;
}
