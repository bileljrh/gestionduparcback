package com.gesparc.entities.carburant.carteAgilis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "recharge_carte_agilis_cash")
public class RechargeCarteAgilisCashEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int quantiteCarburantReservoir;
    
    private Boolean confirmed;
    
    private Boolean validated;
    
    private int montantConfirmee;
    
    private int montantAccordee;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "carte_agilis_cash_id")
    private CarteAgilisCashEntity carteAgilisCash;

}
