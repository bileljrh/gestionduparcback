package com.gesparc.requests.Administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssuranceRequest implements Serializable {
	private Long id;
	private LocalDate datePMC;
	private int nombreplaces;
	private int puissanceFiscale;
	private int montantAssurance;
	private LocalDate dateDebutValidite;
	private LocalDate dateFinValidite;
	private String assuranceSP;
	private String numeroContrat;
	private String compagnieAssurance;
	@JsonIgnore
	List<VehiculeRequest> vehicules;
}
