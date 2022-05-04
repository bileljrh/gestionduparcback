package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleDTO 
{
    private Long id;

    private String designation;
    
    private int quantiteStock;
    
    private double prix;
    
    private String codeArticle;
    
    private LocalDate dateAjout;
    
    private Long idUgp;
    
    private Long idSousFamille;
    
    private Long idMarqueVehicule;
    
    private Long idTypeVehicule;
    
    private Long idGenreVehicule;
    
    private int quantiteLivree;
    
    private float tva;
    
    private float remise;
    
    private int alertStock ;

}
