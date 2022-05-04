package com.gesparc.sharedDTO.referentiel;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDTO implements Serializable 
{
	@JsonIgnore
	List<BonCommandeDTO> bonCommandes;
	
	@JsonIgnore
	List<VehiculeDTO> vehicule;
	
	private Long id;
	
	private String code;
	
	private String designation;
	
	private String activite;
	
	private LocalDate dateAjout;
}