package com.gesparc.responses.carburant;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListBeneficiairesExternesResponse
{
    private Long idBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;

     @Override
    public String toString() {
        return "ListBeneficiairesExternesResponse{" +
                "idBeneficiaire=" + idBeneficiaire +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                '}';
    }
}
