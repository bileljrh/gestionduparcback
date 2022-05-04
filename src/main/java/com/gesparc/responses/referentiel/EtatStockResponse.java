package com.gesparc.responses.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatStockResponse implements Serializable
{
    private Long id;
    
    private String chapitre;
    
    private String time;
    
    private ArticleResponse article;
    
    private String paragraphe;
    
    private String region;
    
    private String sousParagraphe;
    
}
