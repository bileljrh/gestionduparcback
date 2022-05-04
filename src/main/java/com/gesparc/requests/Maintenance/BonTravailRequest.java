package com.gesparc.requests.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.maintenance.FacturationEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.AtelierEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.sharedDTO.Achat.additional.UpdateDemandeArticleDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailArticleDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailArticleExterneDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailOperationDTO;
import com.gesparc.sharedDTO.referentiel.FournisseurDTO;
import com.gesparc.sharedDTO.referentiel.UpdateArticleDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonTravailRequest {
    private Long id;
    private Date dateEntree;
    private Date dateSortie;
    private Date dateSortiePrevue;
    private String natureTravaux;
    private String mode;
    private int indexKM;
    private int quantite;
    private int quantiteLivree;
    private String observation;
    private String observatioMode;
    private DemandeMaintenanceRequest demandeMaintenance;
    public List<ArticleEntity> articless;
    public List<ArticleEntity> articleExterne;
    public Set<UpdateBonTravailOperationDTO> operations;
    List<UpdateBonTravailArticleDTO> articles;
    List<UpdateBonTravailArticleExterneDTO> externes;
    private List<UpdateArticleDTO> updateArticle;
    private FournisseurEntity fournisseur;
    private FacturationEntity facturation;
    private AtelierEntity atelier;
    private MagasinEntity magasin;
    List<BonTravailOperationEntity> bonTravailOperationEntity;
    List<BonTravailArticleExterneEntity> bonTravailArticleExterne;
}
