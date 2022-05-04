package com.gesparc.requests.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertRequest implements Serializable 
{
    private Long id;
    
    private String code;
    
    private String designation;
    
    private String telephone;
    
    private String fax;
    
    private String adresse;
    
    private String codePostal;
    
    private String ville;
    
    private String email;
    
    private Date dateAjout;
    
}
