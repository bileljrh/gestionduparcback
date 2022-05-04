package com.gesparc.requests.Maintenance;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.sharedDTO.referentiel.UpdateArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturationRequest 
{
	private Long id;
	
	private Date dateCommande;
	
	private String numeroFacturation;
	
	private Date dateFacturation;
	
	private String montantFacturation;
	
	private String numeroReglement;
	
	private Date dateReglement;
	
	private String montantReglement;
	
	@JsonIgnore
	BonTravailEntity bonTravail;
}
