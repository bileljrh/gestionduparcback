package com.gesparc.responses.referentiel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SousFamilleArticleResponse implements Serializable 
{
    private Long id;
    
    private String code;
    
    private String sousFamille;
    
    private LocalDate dateAjout;
    
    private FamilleArticleResponse famille;
    
    @JsonIgnore
    private List<ArticleResponse> articles;
    
}
