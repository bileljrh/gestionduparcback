package com.gesparc.sharedDTO;

import java.time.LocalDate;

import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeAnnulationCarteAgilisCashDTO 
{
    private Long id;
    
    private Long idCard;
    
    private String nomDeclarant;
    
    private String prenomDeclarant;
    
    private LocalDate dateNaissanceDeclarant;
    
    private String lieuNaissanceDeclarant;
    
    private String numeroCINDeclarant;
    
    private String sexeDeclarant;
    
    private String typeDeclarant;
    
    private String numeroPlaque;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String structure;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int soldeRestant;
    
    private String causeAnnulation;
    
    private LocalDate dateDemande;
    
    private boolean confirmed;

   @Override
    public String toString() {
        return "DemandeAnnulationCarteAgilisCashDTO{" +
                "id=" + id +
                ", nomDeclarant='" + nomDeclarant + '\'' +
                ", prenomDeclarant='" + prenomDeclarant + '\'' +
                ", dateNaissanceDeclarant=" + dateNaissanceDeclarant +
                ", lieuNaissanceDeclarant='" + lieuNaissanceDeclarant + '\'' +
                ", numeroCINDeclarant='" + numeroCINDeclarant + '\'' +
                ", sexeDeclarant='" + sexeDeclarant + '\'' +
                ", typeDeclarant='" + typeDeclarant + '\'' +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", structure='" + structure + '\'' +
                ", numeroCarte='" + numeroCarte + '\'' +
                ", typeCarburant='" + typeCarburant + '\'' +
                ", soldeRestant=" + soldeRestant +
                ", causeAnnulation='" + causeAnnulation + '\'' +
                ", dateDemande=" + dateDemande +
                ", confirmed=" + confirmed +
                '}';
    }
}
