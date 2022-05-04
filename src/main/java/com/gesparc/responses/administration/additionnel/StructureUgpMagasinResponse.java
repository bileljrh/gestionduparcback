package com.gesparc.responses.administration.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureUgpMagasinResponse implements Serializable 
{
    private Long idStructure;
    
    private String designationStructure;
    
    private String codeStructure;
    
    private Long idUgp;
    
    private String designationUgp;
    
    private String codeUgp;
    
    private List<MagasinUGPResponse> magasins;
    
}
