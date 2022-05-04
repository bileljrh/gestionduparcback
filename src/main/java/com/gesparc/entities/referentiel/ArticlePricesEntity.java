package com.gesparc.entities.referentiel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "article_prices")
public class ArticlePricesEntity {

    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
    
    
    
    @JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "article_id")
	private ArticleEntity article;
    
    private int qte ;
    
    private float prix ;
    
    private Date dateArticle ;
    
}
