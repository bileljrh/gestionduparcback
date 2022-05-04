package com.gesparc.sharedDTO.carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCartePlafondDTO 
{
    private Long id;

    private Long idVehicule;
    
    private String numeroCarte;
    
    private int montant;
    
    private String typeCarburant;
    
    private LocalDate dateValiditee;
    
    private String status;
}

