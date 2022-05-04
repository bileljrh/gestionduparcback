package com.gesparc.sharedDTO;


import com.gesparc.sharedDTO.administratif.BeneficiaireDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.administratif.BeneficiaireDTO;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribution2FonctionDTO 
{
    private Long id;
    
    private int quota;
    
    private LocalDate moisDistribution;
    
    private int nombre2Bons;
    
    private String quantiteCarburant;
    
    private String sourceCarburant;

    @JsonIgnore
    private BeneficiaireDTO beneficiaire;

    @JsonIgnore
    private Details2DistributionDTO details2distributions;

     @Override
    public String toString() {
        return "Distribution2FonctionDTO{" +
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
