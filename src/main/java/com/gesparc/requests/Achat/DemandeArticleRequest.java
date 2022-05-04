package com.gesparc.requests.Achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.ArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeArticleRequest implements Serializable {
	private Long id;

	private ArticleRequest article;
	
	private int quantiteCommandee;
	
	private int quantiteLivree;
	
	private boolean accepted;
	
	private LocalDate dateDemandeArticle;
	
	@JsonIgnore
	private BonCommandeRequest bonCommande;
}
