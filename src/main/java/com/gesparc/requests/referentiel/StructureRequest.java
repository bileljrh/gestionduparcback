package com.gesparc.requests.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.security.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureRequest implements Serializable
{
    @JsonIgnore
    List<UserRequest> users;
    
    private Long id;
    
    private String code;
    
    private String typeStructure;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    private StructureRequest structureMere;
    
    private List<StructureRequest> structureFilles;
    
    @JsonIgnore
    private UgpRequest ugp;
    
    @JsonIgnore
    List<PersonnelRequest> personnels;
}
