package com.gesparc.requests.Carburant;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyDemandeQuotaCarteJockerRequest 
{

    private Long id;
    
    private String causeDeBlocage ;
    
    private String demandeur ;
    
    private int quantiteValide ; 
    
}
