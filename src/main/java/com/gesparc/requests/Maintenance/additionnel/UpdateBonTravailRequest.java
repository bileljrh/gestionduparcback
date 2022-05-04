package com.gesparc.requests.Maintenance.additionnel;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.maintenance.FacturationEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.requests.Administratif.VehiculeRequest;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.requests.Maintenance.BonTravailRequest;
import com.gesparc.requests.Maintenance.DemandeMaintenanceRequest;
import com.gesparc.sharedDTO.referentiel.UpdateArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBonTravailRequest 
{
	
    private Long id;
    
    private String observationMode;
    
    private DemandeMaintenanceRequest demandeMaintenance;
    
    public List<ArticleEntity> articles;
    
    public List<ArticleEntity> articleExterne;
    
    public Set<UpdateOperationReparationRequest> operations;
    
    List<BonTravailArticleEntity> bonTravailArticle;
    
    private List<UpdateArticleDTO> updateArticle;
    
    private FournisseurEntity fournisseur;
    
    private FacturationEntity facturation;
    
    List<UpdateBonTravailOperationRequest> bonTravailOperationEntity;
    
    List<BonTravailArticleExterneEntity> bonTravailArticleExterne;
    
}
