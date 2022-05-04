package com.gesparc.responses.referentiel.additionnel;

import java.util.List;

import com.gesparc.responses.referentiel.UgpResponse;

public class ListStructureResponse 
{
    private Long id;
    
    private String code;
    
    private String typeStructure;
    
    private String designation;
    
    private ListStructureResponse structureMere;
    
    private UgpResponse ugp;
    
    private boolean collapsed;
    
    private boolean square;
    
    private List<ListStructureResponse> structureFilles;
    
}
