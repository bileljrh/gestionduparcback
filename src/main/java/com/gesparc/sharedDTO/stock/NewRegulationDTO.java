package com.gesparc.sharedDTO.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleForRegulationRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewRegulationDTO  extends AbstractBaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	 private String observation;
	 
	 private int quantite_modifier;
	 
	 private String   type_mouvement;
	
	 private String codeArticle;
	 
		private Long idArticle;

	
}
