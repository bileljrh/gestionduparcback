package com.gesparc.sharedDTO.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO implements Serializable 
{
    private Long id;

    private String code;
    
    private String designation;
    
    private int nombrePersonnels;
    
    @JsonIgnore
    private MagasinDTO magasin;
    
    @JsonIgnore
    private AtelierDTO atelier;
    
    @JsonIgnore
    private RessourceDTO ressource;
   
    private boolean collapsed;
    
    private boolean square;

}
