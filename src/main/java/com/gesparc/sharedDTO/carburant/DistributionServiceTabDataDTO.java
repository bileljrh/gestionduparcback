package com.gesparc.sharedDTO.carburant;

import java.time.LocalDate;

import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionServiceTabDataDTO
{
    private Long idDistribution;
    
    private Long idVehicule;
    
    private Long idEtatMensuel;
    
    private String numeroPlaque;
    
    private String structure;
    
    private LocalDate moisEtat;
    
    private String typeCarburant;
    
    private int quantiteCarburant;
    
    private int quantiteAccordee;
    
    private String sourceCarburant;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;

    @Override
    public String toString() {
        return "DistributionServiceTabDataDTO{" +
                "idVehicule=" + idVehicule +
                ", idEtatMensuel=" + idEtatMensuel +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", structure='" + structure + '\'' +
                ", moisEtat=" + moisEtat +
                ", typeCarburant='" + typeCarburant + '\'' +
                ", quantiteCarburant=" + quantiteCarburant +
                ", quantiteAccordee=" + quantiteAccordee +
                ", sourceCarburant='" + sourceCarburant + '\'' +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                '}';
    }
}
