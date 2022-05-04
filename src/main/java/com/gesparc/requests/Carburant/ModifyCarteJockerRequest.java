package com.gesparc.requests.Carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCarteJockerRequest 
{
    private Long id;
    
    private String numeroCarte;
    
    private int solde;
}
