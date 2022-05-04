package com.gesparc.responses.maintenance.additional;

import com.gesparc.responses.maintenance.FacturationResponse;
import com.gesparc.responses.referentiel.FournisseurResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturationTestDTO {
	private Long id;
	private FournisseurResponse fournisseur;

}
