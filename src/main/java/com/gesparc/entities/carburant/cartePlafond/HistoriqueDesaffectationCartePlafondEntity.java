package com.gesparc.entities.carburant.cartePlafond;

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
@Entity(name = "historique_desaffectation_carte_plafond")
public class HistoriqueDesaffectationCartePlafondEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long idVehicule;
    
    private String structure;
    
    private LocalDate dateDesaffectation;
    
    private String numeroCarte;
    
    private int montant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroPlaque;
    
    private String typeCarburant;
    
}
