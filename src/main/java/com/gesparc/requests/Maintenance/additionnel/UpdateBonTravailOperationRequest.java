package com.gesparc.requests.Maintenance.additionnel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.VehiculeRequest;
import com.gesparc.requests.Maintenance.BonTravailRequest;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBonTravailOperationRequest {
	
	
	private UpdateArticleRequest updateArticle;


}
