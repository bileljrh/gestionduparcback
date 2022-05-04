package com.gesparc.sharedDTO.carburant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCarteJockerDTO
{
    private Long id;
    
    private String numeroCarte;
   
    private int solde;
}
