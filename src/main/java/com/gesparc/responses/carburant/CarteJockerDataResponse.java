package com.gesparc.responses.carburant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteJockerDataResponse 
{
    private Long id;
    
    private String numeroCarte;
    
    private LocalDate dateDebutUtilisation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private int nombreAffectation;
    
    private int solde;
    
    private boolean affected;
}
