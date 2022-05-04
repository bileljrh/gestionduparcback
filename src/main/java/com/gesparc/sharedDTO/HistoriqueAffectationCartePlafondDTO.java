package com.gesparc.sharedDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueAffectationCartePlafondDTO
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
    
}
