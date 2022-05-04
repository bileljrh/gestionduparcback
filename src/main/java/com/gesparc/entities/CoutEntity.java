package com.gesparc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.VehiculeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "cout")
public class CoutEntity extends  AbstractBaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    
    private String operation;
    
    private Date date_operation;
    
    private int montant;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "vehicule_id")
    private VehiculeEntity vehicule;

    public CoutEntity() {
    }


    public CoutEntity(Long id, String operation, Date date_operation, int montant, VehiculeEntity vehicule) 
    {
        this.id = id;
        this.operation = operation;
        this.date_operation = date_operation;
        this.montant = montant;
        this.vehicule = vehicule;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getOperation() 
    {
        return operation;
    }

    public void setOperation(String operation) 
    {
        this.operation = operation;
    }

    public Date getDate_operation() 
    {
        return date_operation;
    }

    public void setDate_operation(Date date_operation) 
    {
        this.date_operation = date_operation;
    }

    public int getMontant() 
    {
        return montant;
    }

    public void setMontant(int montant) 
    {
        this.montant = montant;
    }

    public VehiculeEntity getVehicule() 
    {
        return vehicule;
    }

    public void setVehicule(VehiculeEntity vehicule) 
    {
        this.vehicule = vehicule;
    }
}
