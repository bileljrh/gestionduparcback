package com.gesparc.entities.maintenance;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "bon_travail_article_externe", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "bon_travail_id", "article_id" }) })
public class BonTravailArticleExterneEntity extends  AbstractBaseEntity
{

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private int quantite;
	private int quantiteLivree;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "bon_travail_id")
	private BonTravailEntity bonTravail;

	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	private ArticleEntity externes;
}
