package com.gesparc.entities.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.ArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RetourStructure")
public class RetourStructure extends  AbstractBaseEntity
{
	
	 @Id
	 @GeneratedValue
	 private Long id;
	 
	 private Date dateSortie; 
	 
	 private String magasin;
	 
	 private String structure ; 
	 
	    @JsonIgnore
	    @OneToMany(mappedBy = "retourStructure", cascade = CascadeType.ALL)
	    List<ArticleEntity> articles = new ArrayList<>();
	  
}
