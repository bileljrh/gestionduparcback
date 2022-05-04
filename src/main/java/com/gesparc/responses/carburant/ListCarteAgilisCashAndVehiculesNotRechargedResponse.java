package com.gesparc.responses.carburant;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCarteAgilisCashAndVehiculesNotRechargedResponse
{
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private Long idCarte;
    
    private String numeroPlaque;
    
    private String structure;
    
    private String typeCarburantVehicule;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroCarte;
    
    private String typeCarburantCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private int soldeRestant;
    
    @Override
    public String toString() {
        return "ListCarteAgilisCashAndVehiculesNotRechargedResponse{" +
                "idVehicule=" + idVehicule +
                ", idBeneficiaire=" + idBeneficiaire +
                ", idCarte=" + idCarte +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", structure='" + structure + '\'' +
                ", typeCarburantVehicule='" + typeCarburantVehicule + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", numeroCarte='" + numeroCarte + '\'' +
                ", typeCarburantCarte='" + typeCarburantCarte + '\'' +
                ", dateDebutUtilisation=" + dateDebutUtilisation +
                ", soldeRestant=" + soldeRestant +
                '}';
    }
}
