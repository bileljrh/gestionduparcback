package com.gesparc.requests.Administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReformeRequest 
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
