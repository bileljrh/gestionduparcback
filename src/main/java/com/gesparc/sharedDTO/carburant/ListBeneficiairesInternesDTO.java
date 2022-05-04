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
public class ListBeneficiairesInternesDTO 
{
    private Long idBeneficiaire;

    private Long idVehicule;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String numeroPlaque;
    
    private String energie;
    
    private String structure;
    
    private Float pourcentageVehicule;
    
    private String typeCarburant;

     @Override
    public String toString() {
        return "ListBeneficiairesInternesDTO{" +
                "idBeneficiaire=" + idBeneficiaire +
                ", idVehicule=" + idVehicule +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", energie='" + energie + '\'' +
                ", structure='" + structure + '\'' +
                ", pourcentageVehicule=" + pourcentageVehicule +
                ", typeCarburant='" + typeCarburant + '\'' +
                '}';
    }
}
