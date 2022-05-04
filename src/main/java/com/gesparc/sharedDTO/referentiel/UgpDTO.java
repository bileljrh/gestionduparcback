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
public class UgpDTO implements Serializable 
{
    @JsonIgnore
    List<StructureDTO> structures;

    List<MagasinDTO> magasins;
    
    List<RessourceDTO> ressources;
    
    List<AtelierDTO> ateliers;
    
    @JsonIgnore
    List<UserDTO> users;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    List<ArticleDTO> articles;
}
