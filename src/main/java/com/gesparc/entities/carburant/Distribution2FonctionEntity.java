package com.gesparc.entities.carburant;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.administration.MessageEntity;
import com.gesparc.security.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "distribution_fonction")
public class Distribution2FonctionEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue()
    private Long id;
    
    private int quota;
    
    private LocalDate moisDistribution;
    
    private int nombre2Bons;
    
    private int quantiteCarburant;
    
    private String sourceCarburant;

    @ManyToOne()
    @JoinColumn(name = "beneficiaire_id")
    private BeneficiaireEntity beneficiaire;

    @OneToOne(mappedBy = "distribution2Fonction", cascade = CascadeType.ALL)
    private Details2DistributionEntity details2distributions;


  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public LocalDate getMoisDistribution() {
        return moisDistribution;
    }

    public void setMoisDistribution(LocalDate moisDistribution) {
        this.moisDistribution = moisDistribution;
    }

    public int getNombre2Bons() {
        return nombre2Bons;
    }

    public void setNombre2Bons(int nombre2Bons) {
        this.nombre2Bons = nombre2Bons;
    }

    public int getQuantiteCarburant() {
        return quantiteCarburant;
    }

    public void setQuantiteCarburant(int quantiteCarburant) {
        this.quantiteCarburant = quantiteCarburant;
    }

    public String getSourceCarburant() {
        return sourceCarburant;
    }

    public void setSourceCarburant(String sourceCarburant) {
        this.sourceCarburant = sourceCarburant;
    }

    public BeneficiaireEntity getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(BeneficiaireEntity beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public Details2DistributionEntity getDetails2distributions() {
        return details2distributions;
    }

    public void setDetails2distributions(Details2DistributionEntity details2distributions) {
        this.details2distributions = details2distributions;
    }

    @Override
    public String toString() {
        return "Distribution2FonctionEntity{" +
                "id=" + id +
                ", quota=" + quota +
                ", moisDistribution=" + moisDistribution +
                ", nombre2Bons=" + nombre2Bons +
                ", quantiteCarburant=" + quantiteCarburant +
                ", sourceCarburant='" + sourceCarburant + '\'' +
                ", beneficiaire=" + beneficiaire +
                ", details2distributions=" + details2distributions +
                '}';
    }


}
