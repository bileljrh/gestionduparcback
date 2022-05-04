package com.gesparc.sharedDTO.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.security.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinDTO 
{
    List<SectionDTO> sections;
    
    @JsonIgnore
    List<UserDTO> users;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private UgpDTO ugp;
    
    private boolean collapsed;
    
    private boolean square;
}
