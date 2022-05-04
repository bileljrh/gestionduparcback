package com.gesparc.entities.referentiel;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.sharedDTO.referentiel.FamilleArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sous_famille_article")
public class SousFamilleArticleEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "sousFamille", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ArticleEntity> articles = new ArrayList<>();
   
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String code;
    
    private String sousFamille;
    
    private LocalDate dateAjout;
    
    @ManyToOne()
    @JoinColumn(name = "famille_id")
    private FamilleArticleEntity famille;
}
