package com.gesparc.entities.achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.ArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "demande_article")
public class DemandeArticleEntity extends AbstractBaseEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "article_id")
	ArticleEntity article;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "bon_commande_id")
	BonCommandeEntity bonCommande;
	
	private int quantiteCommandee;
	
	private int quantiteLivree;
	
	private boolean accepted;
	
	private LocalDate dateDemandeArticle;
}
