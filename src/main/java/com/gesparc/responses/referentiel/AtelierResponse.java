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
public class AtelierResponse implements Serializable 
{
    List<SectionResponse> sections;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private UgpResponse ugp;
    
    private boolean collapsed;
    
    private boolean square;
}
