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
public class UgpRequest implements Serializable 
{
    @JsonIgnore
    List<StructureRequest> structures;
    
    List<MagasinRequest> magasins;
    
    List<RessourceRequest> ressources;
    
    List<AtelierRequest> ateliers;
    
    @JsonIgnore
    List<UserRequest> users;
    
    @JsonIgnore
    List<ArticleRequest> articles;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;

}
