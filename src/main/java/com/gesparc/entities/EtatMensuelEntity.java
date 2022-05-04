package com.gesparc.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.carburant.Distribution2ServiceEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "etat_mensuel")
public class EtatMensuelEntity extends  AbstractBaseEntity {

    @JsonIgnore
    @OneToOne(mappedBy = "etatMensuel", cascade = CascadeType.ALL)
    Distribution2ServiceEntity distribution2Service;
    
    @Id
    @GeneratedValue
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
    
    private LocalDate moisEtat;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "beneficiaire_id")
    private BeneficiaireEntity beneficiaire;

    public EtatMensuelEntity() 
    {
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public int getQuantiteRestantee() 
    {
        return quantiteRestantee;
    }

    public void setQuantiteRestantee(int quantiteRestantee) 
    {
        this.quantiteRestantee = quantiteRestantee;
    }

    public int getQuantiteDemandee() 
    {
        return quantiteDemandee;
    }

    public void setQuantiteDemandee(int quantiteDemandee) 
    {
        this.quantiteDemandee = quantiteDemandee;
    }

    public int getQuantiteAccordee()
    {
        return quantiteAccordee;
    }

    public void setQuantiteAccordee(int quantiteAccordee)
    {
        this.quantiteAccordee = quantiteAccordee;
    }

    public int getQuantiteCarburant() 
    {
        return quantiteCarburant;
    }

    public void setQuantiteCarburant(int quantiteCarburant)
    {
        this.quantiteCarburant = quantiteCarburant;
    }

    public int getQuantiteRetournee() 
    {
        return quantiteRetournee;
    }

    public void setQuantiteRetournee(int quantiteRetournee) 
    {
        this.quantiteRetournee = quantiteRetournee;
    }

    public int getIndexFinMois()
    {
        return indexFinMois;
    }

    public void setIndexFinMois(int indexFinMois)
    {
        this.indexFinMois = indexFinMois;
    }

    public int getIndexFinMoisPrecedant() 
    {
        return indexFinMoisPrecedant;
    }

    public void setIndexFinMoisPrecedant(int indexFinMoisPrecedant)
    {
        this.indexFinMoisPrecedant = indexFinMoisPrecedant;
    }

    public int getNombreHeuresTravail() 
    {
        return nombreHeuresTravail;
    }

    public void setNombreHeuresTravail(int nombreHeuresTravail) 
    {
        this.nombreHeuresTravail = nombreHeuresTravail;
    }

    public int getDistanceParcourus() 
    {
        return distanceParcourus;
    }

    public void setDistanceParcourus(int distanceParcourus) 
    {
        this.distanceParcourus = distanceParcourus;
    }

    public Float getPourcentageConsommation() 
    {
        return pourcentageConsommation;
    }

    public void setPourcentageConsommation(Float pourcentageConsommation) 
    {
        this.pourcentageConsommation = pourcentageConsommation;
    }

    public int getJourOuvrables() 
    {
        return jourOuvrables;
    }

    public void setJourOuvrables(int jourOuvrables) 
    {
        this.jourOuvrables = jourOuvrables;
    }

    public int getJours2Production() 
    {
        return jours2Production;
    }

    public void setJours2Production(int jours2Production) 
    {
        this.jours2Production = jours2Production;
    }

    public int getJours2Dispense() 
    {
        return jours2Dispense;
    }

    public void setJours2Dispense(int jours2Dispense) 
    {
        this.jours2Dispense = jours2Dispense;
    }

    public int getJours2Repos() 
    {
        return jours2Repos;
    }

    public void setJours2Repos(int jours2Repos) 
    {
        this.jours2Repos = jours2Repos;
    }

    public boolean isBrouillon() 
    {
        return brouillon;
    }

    public void setBrouillon(boolean brouillon) 
    {
        this.brouillon = brouillon;
    }

    public boolean isConfirme() 
    {
        return confirme;
    }

    public void setConfirme(boolean confirme) 
    {
        this.confirme = confirme;
    }

    public boolean isValide() 
    {
        return valide;
    }

    public void setValide(boolean valide)
    {
        this.valide = valide;
    }

    public BeneficiaireEntity getBeneficiaire() 
    {
        return beneficiaire;
    }

    public void setBeneficiaire(BeneficiaireEntity beneficiaire) 
    {
        this.beneficiaire = beneficiaire;
    }

    public LocalDate getMoisEtat() 
    {
        return moisEtat;
    }

    public void setMoisEtat(LocalDate moisEtat) 
    {
        this.moisEtat = moisEtat;
    }

    public Distribution2ServiceEntity getDistribution2Service() 
    {
        return distribution2Service;
    }

    public void setDistribution2Service(Distribution2ServiceEntity distribution2Service)
    {
        this.distribution2Service = distribution2Service;
    }

    @Override
    public String toString() 
    {
        return "EtatMensuelEntity{" +
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
                ", moisEtat=" + moisEtat +
                ", distribution2Service=" + distribution2Service +
                ", beneficiaire=" + beneficiaire +
                '}';
    }
}
