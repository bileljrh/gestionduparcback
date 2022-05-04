package com.gesparc.sharedDTO.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleForInventaireRequest;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleForRegulationRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListInventaireStockDTO 
{

	@Id
	@GeneratedValue
	private Long id;

	private Date dateInventaire;

	private String tMouvement;

	private String magasin;

	@JsonIgnore
	@OneToMany(mappedBy = "inventaireStock", cascade = CascadeType.ALL)
	List<ArticleEntity> articles = new ArrayList<>();
	
}
