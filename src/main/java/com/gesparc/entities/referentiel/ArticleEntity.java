package com.gesparc.entities.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.achat.DemandeArticleEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;
import com.gesparc.entities.stock.MagasinVirtuelEntity;
import com.gesparc.entities.stock.ParcTransfertArticleEntity;
import com.gesparc.entities.stock.RetourStructure;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "article")
public class ArticleEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    List<DemandeArticleEntity> demandeArticles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    List<MagasinVirtuelArticleEntity> magasinVirtuelArticle = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    List<ParcTransfertArticleEntity> parcTransfertArticle = new ArrayList<>();
   
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.MERGE)
    List<EtatStockEntity> etatsStock = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articles", cascade = CascadeType.MERGE)
    private Set<BonTravailArticleEntity> bonTravailArticle = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "externes", cascade = CascadeType.MERGE)
    private Set<BonTravailArticleExterneEntity> bonTravailArticleExterne= new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    List<ArticlePricesEntity> articlesPrices = new ArrayList<>();
    
    
    @Id
      @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    
    private String designation;
    
    @Column(unique = true)
    private String codeArticle;
    
    @Column(nullable = true)
    private int quantiteStock ;
    
    @Column(nullable = true)
    private int quantiteInventaire  ;
    
    private double prix;
    
    @ManyToOne()
    @JoinColumn(name = "ugp_id")
    private UgpEntity ugp;
    
    private LocalDate dateAjout;
    
    private int quantiteLivree;
    
    private float tva;
    
    private float remise;
    
    @ManyToOne()
    @JoinColumn(name = "marque_id")
    private MarqueVehiculeEntity marqueVehicule;
    
    @ManyToOne()
    @JoinColumn(name = "type_id")
    private TypeVehiculeEntity typeVehicule;
    
    @ManyToOne()
    @JoinColumn(name = "genre_id")
    private GenreVehiculeEntity genreVehicule;
    
    @ManyToOne()
    @JoinColumn(name = "sous_famille_id")
    private SousFamilleArticleEntity sousFamille;
 
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "inventaireStock_id")
    private InventaireStock inventaireStock ; 
    

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "retour_id")
    private RetourStructure retourStructure ; 
    
    private int alertStock ;


	public ArticleEntity(String designation, String codeArticle, int quantiteStock) {
		this.designation = designation;
		this.codeArticle = codeArticle;
		this.quantiteStock = quantiteStock;
	}


	public ArticleEntity( String codeArticle) {
		this.codeArticle = codeArticle;
	}
    
    
    
    
}
