package com.gesparc.responses.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionResponse implements Serializable 
{
    private Long id;
    
    private String code;
    
    private String designation;
    
    private int nombrePersonnels;
    
    @JsonIgnore
    private MagasinResponse magasin;
    
    @JsonIgnore
    private AtelierResponse atelier;
    
    @JsonIgnore
    private RessourceResponse ressource;
    
    private boolean collapsed;
    
    private boolean square;

}
