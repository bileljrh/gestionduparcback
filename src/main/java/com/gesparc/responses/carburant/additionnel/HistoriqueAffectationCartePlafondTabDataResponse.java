package com.gesparc.responses.carburant.additionnel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueAffectationCartePlafondTabDataResponse 
{
	private String id;
	
	private Long idCarte;
	
	private Long idVehicule;
	
	private String structure;
	
	private LocalDate dateAffectation;
	
	private String numeroCarte;
	
	private int montant;
	
	private String nomBeneficiaire;
	
	private String matriculeBeneficiaire;
	
	private String numeroPlaque;
	
	private String typeCarburant;
	
	private boolean confirmed;
	
	private boolean validated;

}
