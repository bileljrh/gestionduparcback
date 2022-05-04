package com.gesparc.requests.Achat.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.requests.Achat.MarcheRequest;
import com.gesparc.requests.referentiel.FournisseurRequest;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBonCommandeRequest implements Serializable {
    List<UpdateDemandeArticleRequest> updateDemandesArticle;
    private Long id;
  //  private String achat;
    
    private String status;
    private Date dateDemande;
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
    @JsonFormat(pattern="YYYY-MM-DD",shape = JsonFormat.Shape.STRING)
    private Date dateReglement;
    private String referenceReglement;
    private FournisseurRequest fournisseur;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "parcs")
    private UgpEntity parc;
}
