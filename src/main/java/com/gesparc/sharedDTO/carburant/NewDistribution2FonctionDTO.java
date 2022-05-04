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
public class NewDistribution2FonctionDTO
{

    private Long idBeneficiaire;
    private LocalDate moisDistribution;
    
    private int nombre2Bons;
    
    private int quantiteCarburant;
    
    private int quota;
    
    private String sourceCarburant;
    
    private Details2DistributionDTO details2Distribution;

     @Override
    public String toString() {
        return "NewDistribution2FonctionDTO{" +
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
