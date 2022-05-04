package com.gesparc.sharedDTO.stock.additionnel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMagasinVirtuelArticleDTO
{	
	private Long id;
	
	private UpdateArticleRequest updateArticle;
	
	private int qteTransferer;


}

