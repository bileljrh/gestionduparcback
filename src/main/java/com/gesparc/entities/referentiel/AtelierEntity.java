package com.gesparc.entities.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "atelier")
public class AtelierEntity extends  AbstractBaseEntity
{

    @OneToMany(mappedBy = "atelier", cascade = CascadeType.ALL)
    List<SectionEntity> sections;
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    @ManyToOne
    private UgpEntity ugp;
    
    @JsonIgnore
    @OneToMany(mappedBy = "atelier", cascade = CascadeType.ALL)
    List<BonTravailEntity> bonTravail=new ArrayList<>();

}
