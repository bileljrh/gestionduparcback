package com.gesparc.responses;

import java.time.LocalDate;

import com.gesparc.requests.Administratif.BeneficiaireRequest;

import com.gesparc.requests.Administratif.BeneficiaireRequest;
import com.gesparc.responses.administratif.VehiculeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribution2FonctionResponse 
{

    private Long id;
    
    private int quota;
    
    private LocalDate moisDistribution;
    
    private int nombre2Bons;
    
    private int quantiteCarburant;
    
    private String sourceCarburant;
    
    private BeneficiaireRequest beneficiaire;
    
    private Details2DistributionResponse details2distributions;
    
     @Override
    public String toString() {
        return "Distribution2FonctionResponse{" +
                "id=" + id +
                ", quota=" + quota +
                ", moisDistribution=" + moisDistribution +
                ", nombre2Bons=" + nombre2Bons +
                ", quantiteCarburant='" + quantiteCarburant + '\'' +
                ", sourceCarburant='" + sourceCarburant + '\'' +
                ", beneficiaire=" + beneficiaire +
                ", details2distributions=" + details2distributions +
                '}';
    }
}
