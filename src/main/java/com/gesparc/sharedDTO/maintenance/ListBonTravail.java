package com.gesparc.sharedDTO.maintenance;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.sharedDTO.referentiel.AtelierDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListBonTravail 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;
	
	private AtelierDTO AtelierDTO;
	
	private DemandeMaintenanceDTO demandeMaintenance;
	}
  

