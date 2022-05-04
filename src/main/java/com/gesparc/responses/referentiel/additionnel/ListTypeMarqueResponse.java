package com.gesparc.responses.referentiel.additionnel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListTypeMarqueResponse 
{
    private Long idType;
    
    private String codeType;
    
    private String designationType;
    
    private Long idMarque;
    
    private String codeMarque;
    
    private String designationMarque;
    
}
