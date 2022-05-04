package com.gesparc.responses.carburant;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListBeneficiairesInternesResponse 
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
        return "ListBeneficiairesInternesResponse{" +
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
