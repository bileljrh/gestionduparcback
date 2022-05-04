package com.gesparc.sharedDTO.maintenance.additional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.gesparc.sharedDTO.referentiel.MagasinDTO;
import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.Achat.NewDemandeArticleDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.referentiel.MagasinDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonTravailDataTableDTO 
{
      
    private Long id;

    private Date dateEntree;
    
	private boolean cloturer;

    private Date dateSortie;
    
    private Date dateSortiePrevue;
    
    private String natureTravaux;
    
    private String mode;
    
    private String structure;
    
    private String serieVehicule;
    
    private String nomBeneficiaire;
    
    private int indexKM;
    
    private Long idVehicule;
    
    private String observation;
    
    private String observatioMode;
    
    private Long idFacturation;
    
    private Long idMagasin ;
    
    private Long idAtelier;
       
    private String nomAtelier;
    
    private String numSerieVoiture ;
    
    
    private String nomMagasin;
    
    private Long idDemandeMaintenance;
    
    List<UpdateBonTravailArticleDTO> bonTravailArticle;
    
    
    

}
