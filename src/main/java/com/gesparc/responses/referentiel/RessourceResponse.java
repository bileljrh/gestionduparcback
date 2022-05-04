package com.gesparc.responses.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RessourceResponse implements Serializable 
{
    List<SectionResponse> sections;
    
    private Long id;
    
    private String numero;
    
    private String designation;
    
    private String prixUnitaire;
    
    private int nombrePersonnels;
    
    @JsonIgnore
    private UgpResponse ugp;
    
    private boolean collapsed;
    
    private boolean square;

}
