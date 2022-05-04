package com.gesparc.sharedDTO.administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpsTableDataDTO 
{
    private Long id;
    
    private String codeIMEI;
    
    private String lien;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String Structure;
}
