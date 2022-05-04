package com.gesparc.requests.stock;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.stock.ParcTransfertArticleEntity;
import com.gesparc.entities.stock.ParcTransfertEntity;
import com.gesparc.requests.referentiel.ArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcTransfertArticleRequest {

private ArticleRequest article;
	
    private Long id;
    
    private String qteTransferer;
    
    private String magasinDestinataire;
    
    @JsonIgnore
    private ParcTransfertRequest parcTransfertRequest;
}
