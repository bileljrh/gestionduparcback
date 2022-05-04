package com.gesparc.responses.referentiel;

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
public class FamilleOperationReparationResponse implements Serializable 
{
    @JsonIgnore
    List<OperationReparationResponse> operationsReparation = new ArrayList<>();
    
    private Long id;
    
    private String code;
    
    private String designation;
    
}
