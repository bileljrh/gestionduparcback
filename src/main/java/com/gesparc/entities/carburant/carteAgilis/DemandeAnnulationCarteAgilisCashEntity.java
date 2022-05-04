package com.gesparc.entities.carburant.carteAgilis;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.carburant.Details2DistributionEntity;
import com.gesparc.entities.carburant.Distribution2FonctionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "demande_annulation_carte_agilis_cash")
public class DemandeAnnulationCarteAgilisCashEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
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
        return "DemandeAnnulationCarteAgilisCashEntity{" +
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
