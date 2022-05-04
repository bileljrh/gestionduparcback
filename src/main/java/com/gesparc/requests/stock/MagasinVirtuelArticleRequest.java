package com.gesparc.requests.stock;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.ArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinVirtuelArticleRequest 
{
	private ArticleRequest article;
	
    private Long id;
    
    private String qteTransferer;
    
    @JsonIgnore
    private MagasinVirtuelRequest magasinVirtuelRequest;
	
}
