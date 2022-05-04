package com.gesparc.entities.stock;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.ArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pt_article")
public class ParcTransfertArticleEntity extends  AbstractBaseEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int qteTransferer;
	
	private String magasinDestinataire;
	
	@JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "parc_transfert_id")
	ParcTransfertEntity parcTransfert;
	
	@ManyToOne()
	@JoinColumn(name = "article_id")
	ArticleEntity article;

}
