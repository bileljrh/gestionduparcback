package com.gesparc.requests.Carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NouvelleDemandeDesaffectationCarteJockerRequest 
{
    private Long idCarte;
    
    private LocalDate dateDesaffectation;
    
    private int soldeDesaffectation;
    
    private String note;
}
