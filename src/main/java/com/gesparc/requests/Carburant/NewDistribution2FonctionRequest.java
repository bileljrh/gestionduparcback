package com.gesparc.requests.Carburant;

import java.time.LocalDate;

import com.gesparc.requests.Details2DistributionRequest;
import com.gesparc.requests.Details2DistributionRequest;
import com.gesparc.requests.Administratif.GpsRequest;
import com.gesparc.requests.Administratif.VehiculeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDistribution2FonctionRequest 
{
    private Long idBeneficiaire;
    
    private LocalDate moisDistribution;
    
    private int nombre2Bons;
    
    private int quantiteCarburant;
    
    private int quota;
    
    private String sourceCarburant;
    
    private Details2DistributionRequest details2Distribution;

     @Override
    public String toString() {
        return "NewDistribution2FonctionRequest{" +
                "idBeneficiaire=" + idBeneficiaire +
                ", moisDistribution=" + moisDistribution +
                ", nombre2Bons=" + nombre2Bons +
                ", quantiteCarburant=" + quantiteCarburant +
                ", quota=" + quota +
                ", sourceCarburant='" + sourceCarburant + '\'' +
                ", details2Distribution=" + details2Distribution +
                '}';
    }
}
