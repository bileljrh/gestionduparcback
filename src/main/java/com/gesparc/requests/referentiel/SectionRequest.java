package com.gesparc.requests.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequest implements Serializable 
{
    private Long id;
    
    private String code;
    
    private String designation;
    
    private int nombrePersonnels;
    
    @JsonIgnore
    private MagasinRequest magasin;
    
    @JsonIgnore
    private AtelierRequest atelier;
    
    @JsonIgnore
    private RessourceRequest ressource;
    
    private boolean collapsed;
    
    private boolean square;

}
