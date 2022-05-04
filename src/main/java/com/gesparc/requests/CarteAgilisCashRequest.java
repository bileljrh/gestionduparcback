package com.gesparc.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.gesparc.requests.Administratif.VehiculeRequest;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteAgilisCashRequest 
{
    private Long id;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private LocalDate dateDebutUtilisation;
    
    private String etatCarte;
    
    private int soldeRestant;
    
    private boolean affected;
    
    private boolean confirmed;
    
    private boolean validated;
    
    private boolean deconfirmed;
    
    private boolean devalidated;
    
    private boolean requestForAffectation;
    
    private boolean requestForDesaffectation;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereDemandeAffectation;
    
    private LocalDate dateDerniereDemandeDesaffectation;
    
    private VehiculeRequest vehicule;
}
