package com.gesparc.requests.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.security.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinRequest 
{
    List<SectionRequest> sections;
    
    @JsonIgnore
    List<UserRequest> users;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private UgpRequest ugp;
    
    private boolean collapsed;
    
    private boolean square;
}
