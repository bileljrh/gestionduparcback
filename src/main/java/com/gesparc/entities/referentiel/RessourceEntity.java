package com.gesparc.entities.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ressource")
public class RessourceEntity extends  AbstractBaseEntity
{

    @OneToMany(mappedBy = "ressource", cascade = CascadeType.ALL)
    List<SectionEntity> sections;
    
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numero;
    
    private String designation;
    
    private String prixUnitaire;
    
    private int nombrePersonnels;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    @ManyToOne
    private UgpEntity ugp;
}
