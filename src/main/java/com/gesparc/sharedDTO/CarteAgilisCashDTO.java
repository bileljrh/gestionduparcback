package com.gesparc.sharedDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.gesparc.sharedDTO.administratif.VehiculeDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteAgilisCashDTO 
{
    private Long id;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private LocalDate dateDebutUtilisation;
    
    private String etatCarte;
    
    private int soldeRestant;
    
    private boolean affected;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private VehiculeDTO vehicule;
    
}
