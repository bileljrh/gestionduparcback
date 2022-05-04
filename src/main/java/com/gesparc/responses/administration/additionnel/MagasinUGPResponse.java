package com.gesparc.responses.administration.additionnel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinUGPResponse 
{
    private Long idMagasin;
    
    private String codeMagasin;
    
    private String designationMagasin;
    
}
