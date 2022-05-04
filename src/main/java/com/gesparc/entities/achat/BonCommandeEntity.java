package com.gesparc.entities.achat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "bon_commande")
public class BonCommandeEntity extends AbstractBaseEntity {
	
	@OneToMany(mappedBy = "bonCommande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<DemandeArticleEntity> demandesArticle;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "fournisseur_id")
	@JsonBackReference
	FournisseurEntity fournisseur;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateFacture;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateReglement;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "marche_id")
	@JsonBackReference
	private MarcheEntity marche;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateDemande;
	
	private String referenceFacture;
	
	private int montantReglement;
	
	private String referenceReglement;

	private String numePiece;
	
	private String parc;
	
	private String status;
	
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
}
