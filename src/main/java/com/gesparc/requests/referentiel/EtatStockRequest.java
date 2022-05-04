package com.gesparc.requests.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatStockRequest implements Serializable 
{
    private Long id;
    
    private String chapitre;
    
    private String time;
    
    private ArticleRequest article;
    
    private String paragraphe;
    
    private String region;
    
    private String sousParagraphe;
}
