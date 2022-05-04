package com.gesparc.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeAffectationCarteAgilisCashResponse 
{
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int montantDemandee;
    
    private int quantiteCarburantReservoir;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String structure;
    
    private Long idBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private Long id;
    
    private Long idCarte;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private LocalDate dateDemandeAffectation;
    
    private int soldeRestant;
    
    private boolean confirmed;
}
