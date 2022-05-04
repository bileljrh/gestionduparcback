package com.gesparc.sharedDTO.carburant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModificationDemandeDesaffectationCarteJockerDTO
{
    private Long id;
   
    private int soldeDesaffectation;
    
    private LocalDate dateDemandeDesaffectation;
    
    private String note;
}
