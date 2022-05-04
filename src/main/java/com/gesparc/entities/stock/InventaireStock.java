package com.gesparc.entities.stock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.achat.DemandeArticleEntity;
import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.EtatStockEntity;
import com.gesparc.entities.referentiel.GenreVehiculeEntity;
import com.gesparc.entities.referentiel.MarqueVehiculeEntity;
import com.gesparc.entities.referentiel.SousFamilleArticleEntity;
import com.gesparc.entities.referentiel.TypeVehiculeEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleForRegulationRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "InventaireStock")
public class InventaireStock extends  AbstractBaseEntity{

	 @Id
	 @GeneratedValue
	 private Long id;
	 private Date dateInventaire; 
	 private String tMouvement;
	 private String magasin ; 
	    @JsonIgnore
	    @OneToMany(mappedBy = "inventaireStock", cascade = CascadeType.ALL)
	    List<ArticleEntity> articles = new ArrayList<>();
	  
	 
	
	
}
