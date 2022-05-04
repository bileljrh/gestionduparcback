package com.gesparc.responses.Achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.referentiel.ArticleResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeArticleResponse implements Serializable {
	private Long id;

	private ArticleResponse article;
	
	private int quantiteCommandee;
	
	private int quantiteLivree;
	
	private boolean accepted;
	
	private float prix ;
	
	private LocalDate dateDemandeArticle;
	
	@JsonIgnore
	private BonCommandeResponse bonCommande;
}
