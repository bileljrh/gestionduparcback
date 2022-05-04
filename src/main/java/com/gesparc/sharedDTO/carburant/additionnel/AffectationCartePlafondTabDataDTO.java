package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectationCartePlafondTabDataDTO
{
    private Long idCartePlafond;
    
    private Long idBeneficiaire;
    
    private Long idVehicule;
    
    private String numeroCarte;
    
    private int montant;
    
    private String matriculeBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String numeroPlaque;
    
    private String typeCarburant;
    
    private String structure;
    
    private LocalDate dateDerniereAffectation;
    
    private LocalDate dateDerniereDesaffectation;
    
    private LocalDate dateDerniereDemandeAffectation;
    
    private LocalDate dateDerniereDemandeDesaffectation;
    
    private boolean affected;
    
    private boolean confirmed;
    
    private boolean validated;
    
    private boolean deconfirmed;
    
    private boolean devalidated;
}
