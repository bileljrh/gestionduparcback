package com.gesparc.responses.referentiel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatStockListResponse implements Serializable 
{
    private Long id;
    
    private String chapitre;
    
    private String time;
    
    private ArticleEtatStockResponse article;
    
    private String paragraphe;
    
    private String region;
    
    private String sousParagraphe;
    
}
