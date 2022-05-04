package com.gesparc.sharedDTO.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.requests.referentiel.Additionnel.UpdateArticleForRegulationRequest;
import com.gesparc.requests.referentiel.Additionnel.UpdateForRetourRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewRetourDTO 
{
	
	@Id
	@GeneratedValue
	private Long id;

	private Date dateSortie;

	private String magasin;

	private String structure;

	List<UpdateForRetourRequest> articles = new ArrayList<>();
}
