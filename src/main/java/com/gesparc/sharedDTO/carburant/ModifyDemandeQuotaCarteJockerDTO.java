package com.gesparc.sharedDTO.carburant;

import com.gesparc.requests.Carburant.ModifyDemandeQuotaCarteJockerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyDemandeQuotaCarteJockerDTO 
{

    private Long id;
    
    private String demandeur ;
    
    private int quantiteValide ; 
    
    private String causeDeBlocage ;
}
