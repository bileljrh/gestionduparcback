package com.gesparc.sharedDTO.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtelierDTO implements Serializable 
{
    List<SectionDTO> sections;

    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private UgpDTO ugp;
    
    private boolean collapsed;
    
    private boolean square;

}
