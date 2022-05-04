package com.gesparc.responses.carburant;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatMensuelTable 
{
    private Long idBeneficiaire;
    
    private Long idEtatMensuel;
    
    private Long idVehicule;
    
    private String structure;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroPlaque;
    
    private int quantiteRestantee;
    
    private int quantiteDemandee;
    
    private int quantiteAccordee;
    
    private int indexFinMois;
    
    private int nombreHeuresTravail;
    
    private int quantiteCarburant;
    
    private int quantiteRetournee;
    
    private int indexFinMoisPrecedant;
    
    private Float pourcentageConsommation;
    
    private Float poucentageVehicule;
    
    private int distanceParcourus;
    
    private int jourOuvrables;
    
    private int jours2Production;
    
    private int jours2Dispense;
    
    private int jours2Repos;
    
    private boolean isBrouillon;
    
    private boolean isConfirme;
    
    private boolean isValide;

     @Override
    public String toString() {
        return "EtatMensuelTable{" +
                "idBeneficiaire=" + idBeneficiaire +
                ", idEtatMensuel=" + idEtatMensuel +
                ", idVehicule=" + idVehicule +
                ", structure='" + structure + '\'' +
                ", nomBeneficiaire='" + nomBeneficiaire + '\'' +
                ", matriculeBeneficiaire='" + matriculeBeneficiaire + '\'' +
                ", numeroPlaque='" + numeroPlaque + '\'' +
                ", quantiteRestantee=" + quantiteRestantee +
                ", quantiteDemandee=" + quantiteDemandee +
                ", quantiteAccordee=" + quantiteAccordee +
                ", indexFinMois=" + indexFinMois +
                ", nombreHeuresTravail=" + nombreHeuresTravail +
                ", quantiteCarburant=" + quantiteCarburant +
                ", quantiteRetournee=" + quantiteRetournee +
                ", indexFinMoisPrecedant=" + indexFinMoisPrecedant +
                ", pourcentageConsommation=" + pourcentageConsommation +
                ", poucentageVehicule=" + poucentageVehicule +
                ", distanceParcourus=" + distanceParcourus +
                ", jourOuvrables=" + jourOuvrables +
                ", jours2Production=" + jours2Production +
                ", jours2Dispense=" + jours2Dispense +
                ", jours2Repos=" + jours2Repos +
                ", isBrouillon=" + isBrouillon +
                ", isConfirme=" + isConfirme +
                ", isValide=" + isValide +
                '}';
    }
}
