package com.gesparc.requests.Carburant;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.gesparc.requests.Administratif.GpsRequest;
import com.gesparc.requests.Administratif.VehiculeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewEtatMensuelRequest 
{
    private Long idBeneficiaire;
    
    private int quantiteRestantee;
    
    private int quantiteDemandee;
    
    private int quantiteAccordee;
    
    private int quantiteCarburant;
    
    private int quantiteRetournee;
    
    private int indexFinMois;
    
    private int indexFinMoisPrecedant;
    
    private int nombreHeuresTravail;
    
    private int distanceParcourus;
    
    private Float pourcentageConsommation;
    
    private int jourOuvrables;
    
    private int jours2Production;
    
    private int jours2Dispense;
    
    private int jours2Repos;
    
    private boolean brouillon;
    
    private boolean confirme;
    
    private boolean valide;
    
    private LocalDate moisEtat;

    @Override
    public String toString() {
        return "NewEtatMensuelRequest{" +
                "idBeneficiaire=" + idBeneficiaire +
                ", quantiteRestantee=" + quantiteRestantee +
                ", quantiteDemandee=" + quantiteDemandee +
                ", quantiteAccordee=" + quantiteAccordee +
                ", quantiteCarburant=" + quantiteCarburant +
                ", quantiteRetournee=" + quantiteRetournee +
                ", indexFinMois=" + indexFinMois +
                ", indexFinMoisPrecedant=" + indexFinMoisPrecedant +
                ", nombreHeuresTravail=" + nombreHeuresTravail +
                ", distanceParcourus=" + distanceParcourus +
                ", pourcentageConsommation=" + pourcentageConsommation +
                ", jourOuvrables=" + jourOuvrables +
                ", jours2Production=" + jours2Production +
                ", jours2Dispense=" + jours2Dispense +
                ", jours2Repos=" + jours2Repos +
                ", brouillon=" + brouillon +
                ", confirme=" + confirme +
                ", valide=" + valide +
                ", moisEtat=" + moisEtat +
                '}';
    }
}
