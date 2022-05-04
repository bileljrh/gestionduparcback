package com.gesparc.requests.referentiel.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleForInventaireRequest 
{

	 private Long id;
	 
	 private int quantiteInventaire;
	 
	 private String codeArticle;
	 
	 private Long inventaireStock;
}
