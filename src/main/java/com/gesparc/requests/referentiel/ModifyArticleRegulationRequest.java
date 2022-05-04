package com.gesparc.requests.referentiel;

import com.gesparc.sharedDTO.referentiel.ModifyArticleRegulationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyArticleRegulationRequest
{
	 private Long id;
	 
	 private int quantiteStock;
}