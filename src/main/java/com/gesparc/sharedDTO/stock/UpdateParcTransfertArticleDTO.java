package com.gesparc.sharedDTO.stock;

import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParcTransfertArticleDTO {

   private Long id;
	
	private UpdateArticleRequest updateArticle;
	
	private int qteTransferer;
	
	private String magasinDestinataire;
}
