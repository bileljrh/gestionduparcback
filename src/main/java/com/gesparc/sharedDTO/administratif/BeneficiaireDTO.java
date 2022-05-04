package com.gesparc.sharedDTO.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.Distribution2FonctionDTO;
import com.gesparc.sharedDTO.EtatMensuelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaireDTO implements Serializable 
{
    private Long id;
    
    private String nom;
    
    private String matricule;
    
    private String type;
    
    private String fonction;
    
    @JsonIgnore
    List<Distribution2FonctionDTO> distribution2Fonction;
    
    @JsonIgnore
    List<EtatMensuelDTO> etatMensuels;
    
    @JsonIgnore
    List<VehiculeDTO> vehicules = new ArrayList<>();
}
