package com.gesparc.responses.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.security.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinResponse 
{
    List<SectionResponse> sections;
    
    @JsonIgnore
    List<UserResponse> users;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private UgpResponse ugp;
    
    private boolean collapsed;
    
    private boolean square;
    
}
