package com.gesparc.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.gesparc.responses.administratif.VehiculeResponse;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartePlafondResponse
{
    VehiculeResponse vehicule;
    
    private Long id;
    
    private int montant;
    
    private LocalDate dateAjout;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
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
}
