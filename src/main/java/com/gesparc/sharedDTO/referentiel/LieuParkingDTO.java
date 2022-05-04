package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LieuParkingDTO implements Serializable 
{
    private Long id;
    
    private String code;
    
    private String adresse;
    
    private GouvernoratDTO gouvernorat;
}
