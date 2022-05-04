package com.gesparc.sharedDTO.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRegulationArticleDTO extends AbstractBaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	 private String observation;
	 
	 private int quantite_modifier;
	 
	 private String   type_mouvement;
	 
	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idArticle")
	private ArticleEntity article;
	*/
	
	 private String codeArticle;
	 
		private Long idArticle;
}


