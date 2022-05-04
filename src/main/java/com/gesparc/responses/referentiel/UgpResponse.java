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
public class UgpResponse implements Serializable 
{
    @JsonIgnore
    List<StructureResponse> structures;
    
    List<MagasinResponse> magasins;
    
    List<RessourceResponse> ressources;
    
    List<AtelierResponse> ateliers;
    
    @JsonIgnore
    List<UserResponse> users;
    
    @JsonIgnore
    List<ArticleResponse> articles;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
}
