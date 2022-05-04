package com.gesparc.requests.Administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpsRequest implements Serializable 
{
    private Long id;
    
    private String codeIMEI;
    
    private String lien;
    
    @JsonIgnore
    VehiculeRequest vehicule;
}
