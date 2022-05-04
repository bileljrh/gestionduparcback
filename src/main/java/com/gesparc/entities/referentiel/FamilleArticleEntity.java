package com.gesparc.entities.referentiel;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gesparc.entities.AbstractBaseEntity;

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
@Entity(name = "famille_article")
public class FamilleArticleEntity extends  AbstractBaseEntity
{
    @OneToMany(mappedBy = "famille", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SousFamilleArticleEntity> sousFamilles = new ArrayList<>();

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String famille;
    
    @Column(unique = true)
    private String code;
    
    private LocalDate dateAjout;
}
