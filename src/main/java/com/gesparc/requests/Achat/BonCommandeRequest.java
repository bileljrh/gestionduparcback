package com.gesparc.requests.Achat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.FournisseurRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonCommandeRequest implements Serializable {
	private Long id;

	List<DemandeArticleRequest> demandesArticle;
	
	private String achat;
	
	private String parc;
	
	private String status;
	
	private Date dateDemande;
	
	private Boolean receipt;
	
	private int commandeHTBrut;
	
	private int commandeHTNet;
	
	private int commandeTVA;
	
	private int commandeTimbreFix;
	
	private int commandeTTC;
	
	private int livraisonHTBrut;
	
	private int livraisonHTNet;
	
	private int livraisonTVA;
	
	private int livraisonTimbreFix;
	
	private int livraisonTTC;
	
	private int montantLivre;
	
	private int montantFacture;
	
	private Date dateFacture;
	
	private String referenceFacture;
	
	private int montantReglement;
	
	private FournisseurRequest fournisseur;
	
	private String referenceReglement;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateReglement;
	
	@JsonIgnore
	private MarcheRequest marche;
	
}
