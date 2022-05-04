package com.gesparc.requests.Achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcheRequest implements Serializable {
	private Long id;

	@JsonIgnore
	List<BonCommandeRequest> bonCommandes;
	
	private String nomMarche;
	
	private String referenceMarche;
	
	private int budget;
}
