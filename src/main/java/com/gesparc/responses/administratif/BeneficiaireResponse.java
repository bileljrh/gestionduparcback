package com.gesparc.responses.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Distribution2FonctionRequest;
import com.gesparc.requests.EtatMensuelRequest;
import com.gesparc.requests.Administratif.VehiculeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaireResponse 
{
    private Long id;
    
    private String nom;
    
    private String matricule;
    
    private String type;
    
    private String fonction;
    
    @JsonIgnore
    List<Distribution2FonctionRequest> distribution2Fonction;
    
    @JsonIgnore
    List<EtatMensuelRequest> etatMensuels;
    
    @JsonIgnore
    List<VehiculeRequest> vehicules = new ArrayList<>();
}
