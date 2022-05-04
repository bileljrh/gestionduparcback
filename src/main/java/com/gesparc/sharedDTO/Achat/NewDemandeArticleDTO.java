
package com.gesparc.sharedDTO.Achat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.gesparc.sharedDTO.referentiel.NewArticleDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDemandeArticleDTO {
	private Long id;

	private NewArticleDTO article;
	
	private int quantiteCommandee;
	
	private int quantiteLivree;
	
	private float prix ;
	
	private boolean accepted;
	
	private LocalDate dateDemandeArticle;
}
