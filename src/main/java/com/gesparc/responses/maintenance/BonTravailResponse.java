package com.gesparc.responses.maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.gesparc.responses.Achat.DemandeArticleResponse;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonTravailResponse 
{
    private Long id;
    
    private Date dateEntree;
    
    private Date dateSortiePrevue;
    
    private String natureTravaux;
    
    private String mode;
    
    private int indexKM;
    
    private String observation;
    
    private DemandeMaintenanceResponse demandeMaintenance;
    
    
    Set<BonTravailArticleResponse> bonTravailArticleResponse;
    
}
