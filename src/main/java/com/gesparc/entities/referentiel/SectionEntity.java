package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "section")
public class SectionEntity extends  AbstractBaseEntity
{

    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;
    
    private int nombrePersonnels;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    @ManyToOne
    private MagasinEntity magasin;

    @JsonIgnore
    @ManyToOne
    private AtelierEntity atelier;

    @JsonIgnore
    @ManyToOne
    private RessourceEntity ressource;

}
