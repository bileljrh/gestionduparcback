package com.gesparc.sharedDTO.maintenance.additional;

import java.util.Date;
import java.util.List;

import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.maintenance.DemandeMaintenanceDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBonTravailDTO {

    private Long id;
    
    private Date dateEntree;
    
    private Date dateSortiePrevue;
    
    private String natureTravaux;
    
    private String mode;
    
    private int indexKM;
    
    private String observation;
    
    private UpdateArticleRequest articles;
}

