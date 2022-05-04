package com.gesparc.responses.referentiel;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.Achat.DemandeArticleResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEtatStockResponse implements Serializable 
{
    private Long id;
    
    private String designation;
   
}