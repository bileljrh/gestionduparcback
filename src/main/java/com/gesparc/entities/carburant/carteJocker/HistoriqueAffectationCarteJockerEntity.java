package com.gesparc.entities.carburant.carteJocker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "historique_affectation_carte_jocker")
public class HistoriqueAffectationCarteJockerEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private String numeroPlaque;
    
    private LocalDate dateAffectation;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private String typeCarburant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroCarte;
    
    private boolean confirmed;
    
    private boolean validated;
    
    @ManyToOne()
    @JoinColumn(name = "carte_jocker_id")
    CarteJockerEntity carteJocker;
}
