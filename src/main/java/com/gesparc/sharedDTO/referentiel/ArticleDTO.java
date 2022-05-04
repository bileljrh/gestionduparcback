package com.gesparc.sharedDTO.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.sharedDTO.Achat.DemandeArticleDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelArticleDTO;
import com.gesparc.sharedDTO.stock.ParcTransfertArticleDTO;

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
public class ArticleDTO implements Serializable 
{
    @JsonIgnore
    List<DemandeArticleDTO> demandeArticles = new ArrayList<>();
   
    @JsonIgnore
    List<MagasinVirtuelArticleDTO> magasinVirtuelAricle = new ArrayList<>();
    
    @JsonIgnore
    List<ParcTransfertArticleDTO> parcTransfertAricle = new ArrayList<>();
   
    @JsonIgnore
    List<EtatStockDTO> etatsStock;
    
    private Long id;
    
    private String designation;
    
    private int quantiteStock;
    
    private double prix;
    
    private UgpDTO ugp;
    
    private String codeArticle;
    
    private LocalDate dateAjout;
    
    private int quantiteLivree;
    
    private float tva;
    
    private float remise;
    
    private MarqueVehiculeDTO marqueVehicule;
    
    private TypeVehiculeDTO typeVehicule;
    
    private GenreVehiculeDTO genreVehicule;
    
    private SousFamilleArticleDTO sousFamille;
}
