package com.gesparc.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueAffectationCartePlafondRequest 
{
    private String id;
    
    private Long idVehicule;
    
    private String structure;
    
    private LocalDate dateAffectation;
    
    private String numeroCarte;
    
    private int montant;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroPlaque;
    
    private String typeCarburant;
    
    private CartePlafondEntity cartePlafond;
}
