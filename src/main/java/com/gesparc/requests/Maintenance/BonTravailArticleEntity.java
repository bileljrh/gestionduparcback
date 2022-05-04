package com.gesparc.requests.Maintenance;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.referentiel.ArticleEntity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "bon_travail_article", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "bon_travail_id", "article_id" }) })
public class BonTravailArticleEntity 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long  id;
	    
	    private int quantite ;
	    
	    private int quantiteLivree ;

	   // private int quantiteRestante ;
	    @JsonIgnore
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @ManyToOne(cascade = CascadeType.MERGE,fetch =  FetchType.LAZY)
	    @JoinColumn(name = "bon_travail_id")
	    private BonTravailEntity bonTravail;
	    
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	    @JoinColumn(name = "article_id")
	    private ArticleEntity articles;

}
