package com.gesparc.requests.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.gesparc.requests.Achat.DemandeArticleRequest;
import com.gesparc.requests.stock.MagasinVirtuelArticleRequest;
import com.gesparc.requests.stock.ParcTransfertArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest implements Serializable 
{
    @JsonIgnore
    List<DemandeArticleRequest> demandeArticles = new ArrayList<>();
    
    @JsonIgnore
    List<MagasinVirtuelArticleRequest> magasinVirtuelArticle = new ArrayList<>();
    
    @JsonIgnore
    List<ParcTransfertArticleRequest> parcTransfertArticle = new ArrayList<>();
    
    @JsonIgnore
    List<EtatStockRequest> etatsStock;

    private Long id;
    
    private String designation;
    
    private int quantiteStock;
    
    private int prix;
    
    private UgpRequest ugp;
    
    private String codeArticle;
    
    private LocalDate dateAjout;
    
    private int quantiteLivree;
    
    private float tva;
    
    private float remise;
    
    private SousFamilleArticleRequest sousFamille;
    
    private MarqueVehiculeRequest marqueVehicule;
    
    private TypeVehiculeRequest typeVehicule;
    
    private GenreVehiculeRequest genreVehicule;

}
