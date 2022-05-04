package com.gesparc.responses.Achat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gesparc.responses.referentiel.FournisseurResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonCommandeResponse implements Serializable {
	private Long id;

	List<DemandeArticleResponse> demandesArticle;
	
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
	
	private Date dateReglement;
	
	private String referenceReglement;
	
	private MarcheResponse marche;
	
	private FournisseurResponse fournisseur;
}
