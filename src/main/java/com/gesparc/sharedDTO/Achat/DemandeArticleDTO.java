package com.gesparc.sharedDTO.Achat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.gesparc.sharedDTO.referentiel.ArticleDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeArticleDTO {
	private Long id;

	private ArticleDTO article;
	
	private int quantiteCommandee;
	
	private int quantiteLivree;
	
	private boolean accepted;
	
	private LocalDate dateDemandeArticle;
}
