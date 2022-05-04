package com.gesparc.entities.maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.AtelierEntity;
import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bon_travail")

public class BonTravailEntity extends  AbstractBaseEntity
{
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "demande_maintenance_id")
	DemandeMaintenanceEntity demandeMaintenance;
	
	@OneToOne(mappedBy = "bonTravail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	FacturationEntity facturation;
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date dateEntree;
	
	private Date dateSortie;
	
	private Date dateSortiePrevue;
	
	private String natureTravaux;
	
	private String mode;
	
	private int indexKM;
	
	private boolean cloturer;
	
	private String observation;
	
	private String observatioMode;
	
	private Long prixArticleLivree ;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bonTravail", cascade = { CascadeType.ALL })
	private List<BonTravailArticleEntity> bonTravailArticle;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bonTravail", cascade = { CascadeType.ALL })
	private List<BonTravailArticleExterneEntity> bonTravailArticleExterne;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bonTravail", cascade = CascadeType.REMOVE)
	private List<BonTravailOperationEntity> bonTravailOperation;
	
	@JsonIgnore
	@ManyToOne
	private AtelierEntity atelier;
	
	@JsonIgnore
	@ManyToOne
	private MagasinEntity magasin;

}
