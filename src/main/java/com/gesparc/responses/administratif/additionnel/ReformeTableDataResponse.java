package com.gesparc.responses.administratif.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReformeTableDataResponse 
{
    private Long id;
    
    private String nom;
    
    private LocalDate date;
    
    private String reference;
    
    private LocalDate dateSortie;
    
    private int prix;
    
    private String cause;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String structure;
}
