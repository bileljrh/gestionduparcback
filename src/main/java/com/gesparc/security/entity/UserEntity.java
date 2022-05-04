package com.gesparc.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administration.MessageEntity;
import com.gesparc.entities.administration.TracabiliteEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.cartePlafond.DeclarationPerteCartePlafondEntity;
import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.UgpEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_application")
public class UserEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    
    private String prenom;

    @Column(unique = true)
    private String matricule;
    
    private int ordre;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String mot2passe;
    
    private String email;
    
    private String typeCompte;
    
    private Date dateDerniereConnexion;
    
    private Date dateAjout;

    private boolean isActive;
    
    private boolean isNotLocked;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<RoleEntity> roles = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "authority_user", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    List<AuthorityEntity> authorities = new ArrayList<>();

   
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "structure_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "structure_id")})
    private List<StructureEntity> structures = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ugp_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "ugp_id")})
    private List<UgpEntity> ugps = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "magasin_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "magasin_id")})
    private List<MagasinEntity> magasins = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade =  CascadeType.MERGE)
    List<TracabiliteEntity> tracabilites = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade =  CascadeType.MERGE)
    List<MessageEntity> messages = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade =  CascadeType.MERGE,orphanRemoval = true)
    List<DeclarationPerteCarteJockerEntity> DeclarationPerteCarteJockerEntity;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.MERGE,orphanRemoval = true)
    List<DeclarationPerteCartePlafondEntity> DeclarationPerteCartePlafondEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade =  CascadeType.MERGE,orphanRemoval = true)
    List<com.gesparc.entities.carburant.carteAgilis.DeclarationPerteCarteAgilisCashEntity> DeclarationPerteCarteAgilisCashEntity;
          
}
