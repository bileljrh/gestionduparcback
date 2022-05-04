package com.gesparc.entities.carburant.carteAgilis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;

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
@Entity(name = "carte_agilis_cash")
public class CarteAgilisCashEntity extends  AbstractBaseEntity
{

	
    @Id
    @GeneratedValue
    private Long id;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int soldeRestant;
    
    private int montant;
    
    private boolean recharged;
    
    private boolean confirmed;
    
    private boolean validated;
    
    private boolean activated;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereDemandeAffectation;
    
    private LocalDate dateDerniereDemandeDesaffectation;

    private LocalDate dateFinValidite;

    @OneToOne()
    @JoinColumn(name = "vehicule_id")
    private VehiculeEntity vehicule;

    @OneToMany(mappedBy = "carteAgilisCash", cascade = CascadeType.ALL)
    List<RechargeCarteAgilisCashEntity> rechargeCarteAgilisCash = new ArrayList<>();
   
    @JsonIgnore
    @OneToMany(mappedBy = "carteagilis",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    List<DeclarationPerteCarteAgilisCashEntity> DeclarationPerteCarteJockerEntity;
    
} 

