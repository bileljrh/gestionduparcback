package com.gesparc.requests.referentiel.Additionnel;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gesparc.entities.referentiel.MarqueVehiculeEntity;
import com.gesparc.entities.stock.RegulationArticleStock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleForRegulationRequest 
{

	 private Long id;
	 
	 private int quantiteStock;
	 
	 private String codeArticle;
	 
	 private Long regulationStock;

}