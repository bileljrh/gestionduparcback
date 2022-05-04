package com.gesparc.sharedDTO;


import java.time.LocalDate;

import com.gesparc.sharedDTO.administratif.BeneficiaireDTO;
import com.gesparc.sharedDTO.administratif.BeneficiaireDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatMensuelDTO 
{
    private Long id;
    
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
    
    private BeneficiaireDTO beneficiaire;
    
    private LocalDate moisEtat;
    
    private Distribution2ServiceDTO distribution2Service;
    
    @Override
    public String toString() {
        return "EtatMensuelDTO{" +
                "id=" + id +
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
                ", beneficiaire=" + beneficiaire +
                ", moisEtat=" + moisEtat +
                ", distribution2Service=" + distribution2Service +
                '}';
    }
}
