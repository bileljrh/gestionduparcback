package com.gesparc.requests.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametreApplicationRequest implements Serializable 
{
    private Long id;
    
    private String societe;
    
    private String entete;
    
    private String pidesPage;
    
    private String reportServeur;
    
    private String IpServeur;
    
    private String port;
}
