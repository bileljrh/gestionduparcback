package com.gesparc.requests.Administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewAssuranceRequest 
{
    private Long id;
    
    private LocalDate datePMC;
    
    private int nombreplaces;
    
    private int puissanceFiscale;
    
    private int montantAssurance;
    
    private LocalDate dateDebutValidite;
    
    private LocalDate dateFinValidite;
    
    private String assuranceSP;
    
    private String numeroContrat;
    
    private String compagnieAssurance;
    
    private List<Long> idVehicules = new ArrayList<>();
}
