package com.gesparc.sharedDTO.Achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcheDTO {
	private Long id;

	@JsonIgnore
	List<BonCommandeDTO> bonCommandes;
	
	private String nomMarche;
	
	private String referenceMarche;
	
	private int budget;
}
