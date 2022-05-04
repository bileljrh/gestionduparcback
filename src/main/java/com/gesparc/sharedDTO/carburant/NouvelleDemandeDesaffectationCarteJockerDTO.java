package com.gesparc.sharedDTO.carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NouvelleDemandeDesaffectationCarteJockerDTO 
{
    private Long idCarte;

    private LocalDate dateDesaffectation;
    
    private int soldeDesaffectation;
    
    private String note;
}
