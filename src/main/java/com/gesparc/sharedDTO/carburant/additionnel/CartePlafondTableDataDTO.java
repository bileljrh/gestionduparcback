package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartePlafondTableDataDTO 
{
    private Long id;

    private int montant;
    
    private LocalDate dateAjout;
    
    private String numeroCarte;
    
    private String typeCarburant;
}
