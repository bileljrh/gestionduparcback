package com.gesparc.requests.referentiel.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleRequest
{
    private Long id;
    
    private String designation;
    
    private int quantiteStock;
    
    private double prix;
    
    private int quantiteLivree;
    
    private float tva;
    
    private float remise;
    
    private String codeArticle;
    
    private LocalDate dateAjout;
    
    private Long idUgp;
    
    private Long idSousFamille;
    
    private Long idMarqueVehicule;
    
    private Long idTypeVehicule;
    
    private Long idGenreVehicule;
    
    private int alertStock ;

}
