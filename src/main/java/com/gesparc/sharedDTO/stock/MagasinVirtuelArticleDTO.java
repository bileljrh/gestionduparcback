package com.gesparc.sharedDTO.stock;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.referentiel.ArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinVirtuelArticleDTO 
{
	private ArticleDTO article;

	private Long id;
	
	private String qteTransferer;
	
	@JsonIgnore
	private MagasinVirtuelDTO magasinVirtuel;

}


