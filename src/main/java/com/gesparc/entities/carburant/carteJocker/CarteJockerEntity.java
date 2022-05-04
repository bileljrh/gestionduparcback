package com.gesparc.entities.carburant.carteJocker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.achat.DemandeArticleEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.DeclarationPerteCartePlafondEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "carte_jocker")
public class CarteJockerEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "vehicule_id")
    VehiculeEntity vehicule;
   
    
    @JsonIgnore
    @OneToMany(mappedBy = "carteJocker", cascade = CascadeType.ALL)
    List<HistoriqueAffectationCarteJockerEntity> historiqueAffectationCarteJocker;
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String numeroCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateFinValiditee;
    
    private int nombreAffectation;
    
    private int solde;
    
    private boolean affected;
    
    @JsonIgnore
    @OneToMany(mappedBy = "cartejocker",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    List<DeclarationPerteCarteJockerEntity> DeclarationPerteCarteJockerEntity;
}
