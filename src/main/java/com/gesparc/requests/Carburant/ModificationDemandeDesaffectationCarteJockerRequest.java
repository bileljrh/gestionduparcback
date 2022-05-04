package com.gesparc.requests.Carburant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModificationDemandeDesaffectationCarteJockerRequest 
{
    private Long id;
    
    private int soldeDesaffectation;
    
    private LocalDate dateDemandeDesaffectation;
    
    private String note;
}
