package com.gesparc.sharedDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeCarteAgilisCashDTO implements Serializable 
{
    private Long id;
    
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int quantiteCarburantReservoir;
    
    private CarteAgilisCashDTO carteAgilisCash;
    
}
