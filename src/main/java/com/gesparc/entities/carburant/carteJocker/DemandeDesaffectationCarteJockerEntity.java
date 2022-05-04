package com.gesparc.entities.carburant.carteJocker;

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
@Entity(name = "demande_desaffectation_carte_jocker")
public class DemandeDesaffectationCarteJockerEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long idCard;
    
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private String numeroCarte;
    
    private LocalDate dateAffectation;
    
    private LocalDate dateDemandeAffectation;
    
    private LocalDate dateDemandeDesaffectation;
    
    private int soldeAffectation;
    
    private int soldeDesaffectation;
    
    private int nombreAffectation;
    
    private String numeroPlaque;
    
    private String structure;
    
    private String typeCarburant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String note;
}
