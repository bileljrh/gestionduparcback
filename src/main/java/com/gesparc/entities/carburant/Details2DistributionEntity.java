package com.gesparc.entities.carburant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;


import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administration.TracabiliteEntity;
import com.gesparc.security.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "details_distribution")
public class Details2DistributionEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToOne()
    @JoinColumn(name = "details_distribution_id")
    private Distribution2FonctionEntity distribution2Fonction;

    @Override
    public String toString() 
    {
        return "Details2DistributionEntity{" +
                "id='" + id + '\'' +
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
                '}';
    }
}
