package com.gesparc.sharedDTO.maintenance.additional;

import java.util.Date;

import com.gesparc.requests.Maintenance.additionnel.UpdateOperationReparationRequest;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data@NoArgsConstructor@AllArgsConstructor
public class UpdateBonTravailOperationDTO 
{
	private Long id;
	
	private UpdateOperationReparationRequest operations;
	
	private double prix ;
	
	private String code ;
	
	private String designation;
	
	private Date dateDebut;
}