package com.gesparc.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details2DistributionRequest 
{

    private Long id;
    
    private LocalDate dateMission;
    
    private String destination;
    
    private Long indexKmDepart;
    
    private Long indexKmArrivee;
    
    private Long distanceParcouru;
    
    private Long droit2Recomponse;
    
    private int quantiteMoisPrecedant;
    
    private int taux2Consommation;
    
    private int nombre2Bons;
    
    private int reste;

    @JsonIgnore
    private Distribution2FonctionRequest distribution2Fonction;

    @Override
    public String toString() {
        return "Details2DistributionRequest{" +
                "id=" + id +
                ", dateMission=" + dateMission +
                ", destination='" + destination + '\'' +
                ", indexKmDepart=" + indexKmDepart +
                ", indexKmArrivee=" + indexKmArrivee +
                ", distanceParcouru=" + distanceParcouru +
                ", droit2Recomponse=" + droit2Recomponse +
                ", quantiteMoisPrecedant=" + quantiteMoisPrecedant +
                ", taux2Consommation=" + taux2Consommation +
                ", nombre2Bons=" + nombre2Bons +
                ", reste=" + reste +
                ", distribution2Fonction=" + distribution2Fonction +
                '}';
    }
}
