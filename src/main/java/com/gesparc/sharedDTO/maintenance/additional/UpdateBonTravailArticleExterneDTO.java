package com.gesparc.sharedDTO.maintenance.additional;

import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBonTravailArticleExterneDTO 
{	
	private Long id ;
	
	private int quantite ;
	
	private int quantiteLivree;
	
	private UpdateArticleRequest externes;
}
