package com.gesparc.requests.stock.additionnel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.ArticleRequest;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMagasinVirtuelArticleRequest 
{
	private UpdateArticleRequest updateArticle;
	
    private Long id;
    
    private String qteTransferer;
    
    @JsonIgnore
    private UpdateMagasinVirtuelRequest UpdateMagasinVirtuelRequest;

}
