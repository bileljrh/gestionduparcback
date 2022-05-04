package com.gesparc.sharedDTO.maintenance.additional;

import java.util.Date;

import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.referentiel.NewArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBonTravailArticleDTO 
{	
	private Long id ;
	
	private int quantite ;
	
	private int quantiteLivree;
	
	private UpdateArticleRequest articles;
	
	private NewArticleDTO article;
	
	
	private BonTravailDTO bonTravailDto;

}
