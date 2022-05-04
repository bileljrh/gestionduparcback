package com.gesparc.sharedDTO.administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTableDataDTO implements Serializable 
{
    private Long id;
    
    private String name;
    
    private String adresse;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String structure;
}
