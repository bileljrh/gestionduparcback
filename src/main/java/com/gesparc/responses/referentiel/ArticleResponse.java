package com.gesparc.responses.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.Achat.DemandeArticleResponse;

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
public class ArticleResponse implements Serializable
{
    @JsonIgnore
    List<DemandeArticleResponse> demandeArticles = new ArrayList<>();
    
    @JsonIgnore
    List<EtatStockResponse> etatsStock;
    
    private Long id;
    
    private String designation;
    
    private String codeArticle;
    
    private int quantiteStock;
    
    private int prix;
    
    private UgpResponse ugp;
    
    private LocalDate dateAjout;
    
    private int quantiteLivree;
    
    private float tva;
    
    private float remise;
    
    private MarqueVehiculeResponse marqueVehicule;
    
    private TypeVehiculeResponse typeVehicule;
    
    private GenreVehiculeResponse genreVehicule;
    
    private SousFamilleArticleResponse sousFamille;

}
