package com.gesparc.entities.maintenance;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.VehiculeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "demande_maintenance_annule")
public class DemandeMaintenanceAnnule {

	@JsonIgnore
    @OneToOne(mappedBy = "demandeMaintenance",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    BonTravailEntity bonTravaill;
    
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
   // @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @JoinColumn(name = "vehicule_id")
    @JsonBackReference
    VehiculeEntity vehicule;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numeroDemande;
    
    private Long idVehicule;
    
    private String numeroSerie;
    
    private Long idBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String structure;
    
    private String personnel;
    
    private String ugp;
    
    private String ugpReparation;
    
    private String demandeur;
    
    @JsonFormat(pattern="YYYY-MM-DD",shape = JsonFormat.Shape.STRING)
    private Date dateDemande;
    
    private int indexKm;
    
    private String descriptionIntervention;
    
    private String status;

    private boolean annulation;

    private Date dateRDV;
    
    private String observation;
    
    private Boolean  etat ;
}
