package com.gesparc.responses;

import java.time.LocalDate;

import com.gesparc.responses.administratif.VehiculeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationPerteCartePlafondResponse
{
    private Long id;
    
    private String nomDeclarant;
    
    private String prenomDeclarant;
    
    private LocalDate dateNaissanceDeclarant;
    
    private String lieuNaissanceDeclarant;
    
    private String numeroCINDeclarant;
    
    private String sexeDeclarant;
    
    private String typeDeclarant;
    
    private String typeDeclaration;
    
    private String numeroPlaque;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String structure;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private LocalDate datePerte;
    
    private String lieuPerte;
    
    private int montant;
    
    private String circonstances;
    
    private boolean confirmed;
    
    @Override
    public String toString() {
        return "DeclarationPerteCartePlafondResponse{" +
                "id=" + id +
                ", nomDeclarant='" + nomDeclarant + '\'' +
                ", prenomDeclarant='" + prenomDeclarant + '\'' +
                ", dateNaissanceDeclarant=" + dateNaissanceDeclarant +
                ", lieuNaissanceDeclarant='" + lieuNaissanceDeclarant + '\'' +
                ", numeroCINDeclarant='" + numeroCINDeclarant + '\'' +
                ", sexeDeclarant='" + sexeDeclarant + '\'' +
                ", typeDeclarant='" + typeDeclarant + '\'' +
                ", typeDeclaration='" + typeDeclaration + '\'' +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", structure='" + structure + '\'' +
                ", numeroCarte='" + numeroCarte + '\'' +
                ", typeCarburant='" + typeCarburant + '\'' +
                ", datePerte=" + datePerte +
                ", lieuPerte='" + lieuPerte + '\'' +
                ", montant=" + montant +
                ", circonstances='" + circonstances + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }
}
