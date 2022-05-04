package com.gesparc.sharedDTO.maintenance.additional;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.requests.referentiel.ArticleRequest;
import com.gesparc.requests.stock.MagasinVirtuelArticleRequest;
import com.gesparc.requests.stock.MagasinVirtuelRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeMaintenanceBonTravailDTO 
{
	private long id;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "vehicule_id")
	@JsonBackReference
	VehiculeEntity vehicule;

}
