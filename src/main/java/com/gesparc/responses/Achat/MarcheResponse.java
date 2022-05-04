package com.gesparc.responses.Achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcheResponse implements Serializable {
	private Long id;

	@JsonIgnore
	List<BonCommandeResponse> bonCommandes;
	
	private String nomMarche;
	
	private String referenceMarche;
	
	private int budget;
}
