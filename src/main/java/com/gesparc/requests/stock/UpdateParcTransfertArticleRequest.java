package com.gesparc.requests.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.requests.stock.additionnel.UpdateMagasinVirtuelArticleRequest;
import com.gesparc.requests.stock.additionnel.UpdateMagasinVirtuelRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParcTransfertArticleRequest {

private UpdateArticleRequest updateArticle;
	
    private Long id;
    
    private String qteTransferer;
    
    private String magasinDestinataire;
    
    @JsonIgnore
    private UpdateParcTransfertRequest UpdateParcTransfertRequest;
}
