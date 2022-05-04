package com.gesparc.responses.carburant;

import java.time.LocalDate;
import com.gesparc.responses.Details2DistributionResponse;
import com.gesparc.responses.Details2DistributionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionFonctionTabDataResponse 
{
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private Long idDistribution;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String numeroPlaque;
    
    private int quantiteCarburant;
    
    private int nombre2Bons;
    
    private int quota;
    
    private LocalDate moisDistribution;
    
    private String sourceCarburant;
    
    private Details2DistributionResponse Details2Distribution;
    
    @Override
    public String toString() {
        return "DistributionFonctionTabDataResponse{" +
                "idVehicule=" + idVehicule +
                ", idBeneficiaire=" + idBeneficiaire +
                ", idDistribution=" + idDistribution +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", quantiteCarburant=" + quantiteCarburant +
                ", nombre2Bons=" + nombre2Bons +
                ", quota=" + quota +
                ", moisDistribution=" + moisDistribution +
                ", sourceCarburant='" + sourceCarburant + '\'' +
                ", Details2Distribution=" + Details2Distribution +
                '}';
    }
}
