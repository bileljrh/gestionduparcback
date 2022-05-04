package com.gesparc.entities.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.security.entity.UserEntity;

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
@Entity(name = "unite_gestion_parc")
public class UgpEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "ugp", cascade = CascadeType.ALL)
    List<StructureEntity> structures=new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "ugp", cascade = CascadeType.ALL)
    List<VehiculeEntity> vehicules = new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ugps")
    List<UserEntity> users=new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "ugp", cascade = CascadeType.ALL)
    List<MagasinEntity> magasins=new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "ugp", cascade = CascadeType.ALL)
    List<RessourceEntity> ressources=new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "ugp", cascade = CascadeType.ALL)
    List<AtelierEntity> ateliers=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "ugp", cascade = CascadeType.ALL)
    List<ArticleEntity> articles=new ArrayList<>();
    
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
}
