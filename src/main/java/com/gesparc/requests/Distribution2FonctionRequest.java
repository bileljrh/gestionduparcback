package com.gesparc.requests;

import com.gesparc.requests.Administratif.BeneficiaireRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.BeneficiaireRequest;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribution2FonctionRequest 
{
    private Long id;
    
    private int quota;
    
    private LocalDate moisDistribution;
    
    private int nombre2Bons;
    
    private int quantiteCarburant;
    
    private String sourceCarburant;


    @JsonIgnore
    private BeneficiaireRequest beneficiaire;

    @JsonIgnore
    private Details2DistributionRequest details2distributions;

    @Override
    public String toString() {
        return "Distribution2FonctionRequest{" +
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
