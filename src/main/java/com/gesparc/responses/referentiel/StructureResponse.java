package com.gesparc.responses.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.security.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureResponse implements Serializable 
{
    @JsonIgnore
    List<UserResponse> users;
    
    private Long id;
    
    private String code;
    
    private String typeStructure;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    private StructureResponse structureMere;
    
    private List<StructureResponse> structureFilles;
    
    private UgpResponse ugp;
    
    @JsonIgnore
    List<PersonnelResponse> personnels;
    
}
