package com.gesparc.requests.referentiel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gesparc.entities.referentiel.FamilleArticleEntity;

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
public class SousFamilleArticleRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    List<ArticleRequest> articles;
	
    private Long id;
    
    private String code;
    
    private String sousFamille;
    
    private LocalDate dateAjout;
    
    private FamilleArticleEntity famille;
}
