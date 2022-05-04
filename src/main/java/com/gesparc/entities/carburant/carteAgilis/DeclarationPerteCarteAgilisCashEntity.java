package com.gesparc.entities.carburant.carteAgilis;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.security.entity.UserEntity;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "declaration_perte_carte_agilis_cash")
public class DeclarationPerteCarteAgilisCashEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private String nomDeclarant;
    
    private String prenomDeclarant;
    
    private LocalDate dateNaissanceDeclarant;
    
    private String lieuNaissanceDeclarant;
    
    private String numeroCINDeclarant;
    
    private String sexeDeclarant;
    
    private String typeDeclarant;
    
    private String typeDeclaration;
    
    private String numeroPlaque;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String structure;
    
    private LocalDate datePerte;
    
    private LocalDate dateConfirmation;
    
    private String lieuPerte;
    
    private String circonstances;
    
    private boolean confirmed;
    
    @ManyToOne()
    @JoinColumn(name = "carteagilis_id")
    CarteAgilisCashEntity carteagilis;
    
    @ManyToOne()
    @JoinColumn(name = "user_id")
    UserEntity user;
    
}
