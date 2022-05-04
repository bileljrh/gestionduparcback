package com.gesparc.sharedDTO.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpsDTO implements Serializable 
{
    private Long id;
    
    private String codeIMEI;
    
    private String lien;
    
    @JsonIgnore
    VehiculeDTO vehicule;
}
