package com.gesparc.responses.administratif.additionnel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpsTableDataResponse 
{
    private Long id;
    
    private String codeIMEI;
    
    private String lien;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String Structure;
}
