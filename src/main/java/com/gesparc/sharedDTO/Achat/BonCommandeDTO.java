package com.gesparc.sharedDTO.Achat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gesparc.sharedDTO.referentiel.FournisseurListDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonCommandeDTO implements Serializable {
	private Long id;

	List<NewDemandeArticleDTO> demandesArticle;
	
	private String parc;
	
	private String status;
	
	private Date dateDemande;
	
	private FournisseurListDTO fournisseur;
}
