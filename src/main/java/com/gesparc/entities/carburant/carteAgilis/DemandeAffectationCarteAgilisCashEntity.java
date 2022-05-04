package com.gesparc.entities.carburant.carteAgilis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "demande_affectation_carte_agilis_cash")
public class DemandeAffectationCarteAgilisCashEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long idCarte;
    
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int montantDemandee;
    
    private int quantiteCarburantReservoir;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String structure;
    
    private Long idBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private LocalDate dateDemandeAffectation;
    
    private int soldeRestant;
    
    private boolean confirmed;
    
}
