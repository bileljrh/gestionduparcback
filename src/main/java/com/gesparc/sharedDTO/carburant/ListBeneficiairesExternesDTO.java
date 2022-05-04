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
public class ListBeneficiairesExternesDTO 
{
    private Long idBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;

 @Override
    public String toString() {
        return "ListBeneficiairesExternesDTO{" +
                "idBeneficiaire=" + idBeneficiaire +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                '}';
    }

   
}
