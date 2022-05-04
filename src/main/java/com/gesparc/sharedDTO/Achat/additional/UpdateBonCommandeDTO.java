package com.gesparc.sharedDTO.Achat.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gesparc.sharedDTO.Achat.MarcheDTO;
import com.gesparc.sharedDTO.referentiel.FournisseurDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBonCommandeDTO {
    List<UpdateDemandeArticleDTO> updateDemandesArticle;
    private Long id;
    //private String achat;
    private String numePiece;
    private String parc;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateReglement;
    private String referenceReglement;
    private MarcheDTO marche;
    private FournisseurDTO fournisseur;
}
