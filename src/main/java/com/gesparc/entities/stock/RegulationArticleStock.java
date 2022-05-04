package com.gesparc.entities.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.entities.referentiel.ArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RegulationArticleStock")
public class RegulationArticleStock extends AbstractBaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String observation;

	private int quantite_modifier;

	private String type_mouvement;

	private String codeArticle;

	private Long idArticle;


}


