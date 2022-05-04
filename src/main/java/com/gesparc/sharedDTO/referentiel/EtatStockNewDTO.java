package com.gesparc.sharedDTO.referentiel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatStockNewDTO implements Serializable 
{
    private Long id;

    private String chapitre;
    
    private String time;
    
    private String paragraphe;
    
    private String region;
    
    private String sousParagraphe;
    
    private ArticleEtatStockDTO article;
}
