package com.gesparc.requests.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtelierRequest implements Serializable
{
    List<SectionRequest> sections;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private UgpRequest ugp;
    
    private boolean collapsed;
    
    private boolean square;
}
