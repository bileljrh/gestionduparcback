package com.gesparc.entities.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "etat_stock")
public class EtatStockEntity extends  AbstractBaseEntity
{
    @ManyToOne()
    @JoinColumn(name = "article_id")
    ArticleEntity article;
    
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String chapitre;
    
    private String time;
    
    private String paragraphe;
    
    private String region;
    
    private String sousParagraphe;
}
