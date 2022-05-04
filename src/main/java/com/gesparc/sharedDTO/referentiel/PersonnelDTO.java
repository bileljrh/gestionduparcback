package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelDTO implements Serializable 
{
    private Long id;

    private String immatriculationUnique;
    
    private String nom;
    
    private String prenom;
    
    private String cin;
    
    private String fonction;
    
    private String grade;
    
    private String avantage;

    private String quota;
    
    private LocalDate datePermis;
    
    private String nameImage;
    
    private String pathImage;
    
    private Long nombreVehicule;
    
    private StructureDTO structure;
}
