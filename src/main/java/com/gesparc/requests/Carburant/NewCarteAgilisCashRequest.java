package com.gesparc.requests.Carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCarteAgilisCashRequest 
{
    private String numeroCarte;
    
    private String typeCarburant;
    
    private int montant;
    
    private LocalDate dateFinValidite;
    
    private Long idVehicule;
    
    private Long idCarte;
}
