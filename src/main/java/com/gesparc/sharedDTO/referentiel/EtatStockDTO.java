package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatStockDTO implements Serializable 
{
    private Long id;

    private String chapitre;
    
    private String time;
    
    private String paragraphe;
    
    private String region;
    
    private String sousParagraphe;
    
    private ArticleDTO article;
}
