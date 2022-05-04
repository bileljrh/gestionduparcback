package com.gesparc.sharedDTO.maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.sharedDTO.Achat.NewDemandeArticleDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailArticleDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonTravailDTO 
{
    private Long id;

    private Date dateEntree;
    
    private Date dateSortiePrevue;
    
    private String natureTravaux;
    
    private String mode;
    
    private int indexKM;
    
    private String observation;
    
    private DemandeMaintenanceDTO demandeMaintenance;
    
    List<UpdateBonTravailArticleDTO> bonTravailArticle;
}
