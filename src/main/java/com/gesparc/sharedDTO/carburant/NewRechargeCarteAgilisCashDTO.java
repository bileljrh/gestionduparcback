package com.gesparc.sharedDTO.carburant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewRechargeCarteAgilisCashDTO 
{
    private Long id;

    private Long idCarteAgilisCash;
    
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int montantDemandee;
    
    private int quantiteCarburantReservoir;
    
    private int montantConfirmee;
    
    private int montantAccordee;
}
