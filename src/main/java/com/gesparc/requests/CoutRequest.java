package com.gesparc.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.VehiculeRequest;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;


public class CoutRequest 
{

    private String operation;
    
    private Date date_operation;
    
    private int montant;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "vehicule_id")
    private VehiculeRequest vehicule;

    public CoutRequest() {
    }

    public CoutRequest(String operation, Date date_operation, int montant, VehiculeRequest vehicule) {
        this.operation = operation;
        this.date_operation = date_operation;
        this.montant = montant;
        this.vehicule = vehicule;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDate_operation() {
        return date_operation;
    }

    public void setDate_operation(Date date_operation) {
        this.date_operation = date_operation;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public VehiculeRequest getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeRequest vehicule) {
        this.vehicule = vehicule;
    }
}
