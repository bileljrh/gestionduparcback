package com.gesparc.sharedDTO.carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCarteAgilisCashDTO 
{
    private String numeroCarte;

    private String typeCarburant;
    
    private int montant;
    
    private LocalDate dateFinValidite;
    
    private Long idVehicule;
    
    private Long idCarte;
}
