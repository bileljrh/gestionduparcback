package com.gesparc.sharedDTO.referentiel;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SousFamilleArticleDataDTO implements Serializable {
	
	
	private Long id;
	
	private String code;
	
	private String sousFamille;
	
	private FamilleArticleDataDTO famille;

}
