package com.gesparc.responses.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.VehiculeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReformeResponse 
{
    @JsonIgnore
    VehiculeRequest vehicule;
    
    private Long id;
    
    private String nom;
    
    private LocalDate date;
    
    private String reference;
    
    private LocalDate dateSortie;
    
    private int prix;
    
    private String cause;
}
