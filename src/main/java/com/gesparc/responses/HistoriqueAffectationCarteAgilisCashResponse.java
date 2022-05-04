package com.gesparc.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueAffectationCarteAgilisCashResponse 
{
    private Long id;
    
    private Long idCarte;
    
    private Long idVehicule;
    
    private Long idBeneficiaire;
    
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int montantDemandee;
    
    private int quantiteCarburantReservoir;
    
    private String numeroPlaque;
    
    private String structure;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private LocalDate dateDemandeAffectation;
    
    private LocalDate dateAffectation;
    
    private int soldeRestant;
}
