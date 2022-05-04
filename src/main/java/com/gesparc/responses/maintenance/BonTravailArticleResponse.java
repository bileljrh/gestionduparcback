package com.gesparc.responses.maintenance;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.responses.maintenance.additional.BonTravailDataTableResponse;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.responses.Achat.BonCommandeResponse;
import com.gesparc.responses.maintenance.additional.BonTravailDataTableResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonTravailArticleResponse
{
	private Long id ;
	
	private int quantite;
	
	private int quantiteLivree ;
	//private UpdateArticleRequest updateArticle;
	
    @JsonIgnore
    private BonTravailResponse bonTravailResponse;
	
}
