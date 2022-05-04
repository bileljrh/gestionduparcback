package com.gesparc.requests.referentiel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaireEmpruntRequest 
{
    private Long id;
    
    private String code;
    
    private String nomBeneficiaire;
    
}
