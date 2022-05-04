package com.gesparc.sharedDTO.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.security.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureDTO implements Serializable
{
    @JsonIgnore
    List<UserDTO> users;

    private Long id;
    
    private String code;
    
    private String typeStructure;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    private StructureDTO structureMere;
    
    private List<StructureDTO> structureFilles;
    
    private UgpDTO ugp;
    
    @JsonIgnore
    List<PersonnelDTO> personnels;
}
