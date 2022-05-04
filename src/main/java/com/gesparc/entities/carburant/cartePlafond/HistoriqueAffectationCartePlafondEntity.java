package com.gesparc.entities.carburant.cartePlafond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "historique_affectation_carte_plafond")
public class HistoriqueAffectationCartePlafondEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long idVehicule;
    
    private String structure;
    
    private LocalDate dateAffectation;
    
    private String numeroCarte;
    
    private int montant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroPlaque;
    
    private String typeCarburant;
    
    private boolean confirmed;
    
    private boolean validated;
    
    @ManyToOne()
    @JoinColumn(name = "carte_plafond_id")
    CartePlafondEntity cartePlafond;
}
