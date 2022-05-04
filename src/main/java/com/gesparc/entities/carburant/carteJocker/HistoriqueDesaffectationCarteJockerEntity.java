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
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "historique_desaffectation_carte_jocker")
public class HistoriqueDesaffectationCarteJockerEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long idCarte;
    
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private String numeroCarte;
    
    private LocalDate dateAffectation;
    
    private LocalDate dateDemandeAffectation;
    
    private LocalDate dateDemandeDesaffectation;
    
    private Date dateConfirmationDesaffectation;
    
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
