package com.gesparc.responses.referentiel;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gesparc.responses.referentiel.additionnel.FamilleArticleResponseDataTable;
import com.gesparc.sharedDTO.referentiel.ArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SousFamilleArticleResponseData implements Serializable 
{
	private Long id;
	
    private String code;
    
    private String sousFamille;
    
    private FamilleArticleResponseDataTable famille;

}
