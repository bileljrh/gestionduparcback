package com.gesparc.responses.administratif.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisiteTechniqueTableDataResponse 
{
    private Long id;
    
    private Date datePMC;
    
    private int nombrePlaces;
    
    private int puissanceFiscale;
    
    private int montantVisiteTechnique;
    
    private int prixAchat;
    
    private Date dateDebutValidite;
    
    private Date dateFinValidite;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String Structure;
    
}
