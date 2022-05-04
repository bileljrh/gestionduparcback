package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeCarteAgilisCashTabDataDTO implements Serializable
{
    private Long id;

    private Long idVehicule;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int montant;
    
    private LocalDate dateFinValidite;
    
    private String numeroPlaque;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int quantiteCarburantReservoir;
    
    private boolean confirmed;
    
    private boolean validated;
    
    private boolean recharged;
    
    private int montantConfirmee;
    
    private int montantAccordee;
}
