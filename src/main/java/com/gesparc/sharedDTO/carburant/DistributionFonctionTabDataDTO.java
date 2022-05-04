package com.gesparc.sharedDTO.carburant;

import java.time.LocalDate;
import com.gesparc.sharedDTO.Details2DistributionDTO;
import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.Details2DistributionDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionFonctionTabDataDTO 
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
    
    private Details2DistributionDTO Details2Distribution;

     @Override
    public String toString() {
        return "DistributionFonctionTabDataDTO{" +
                "idVehicule=" + idVehicule +
                ", idBeneficiaire=" + idBeneficiaire +
                ", idDistribution=" + idDistribution +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", quantiteCarburant=" + quantiteCarburant +
                ", nombre2Bons=" + nombre2Bons +
                ", quota=" + quota +
                ", moisDistribution='" + moisDistribution + '\'' +
                ", Details2Distribution=" + Details2Distribution +
                '}';
    }
}
