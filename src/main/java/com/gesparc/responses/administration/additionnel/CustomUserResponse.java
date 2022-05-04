package com.gesparc.responses.administration.additionnel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.UgpEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserResponse 
{
    List<String> authorities;
    
    private Long id;
    
    private String nom;
    
    private String prenom;
    
    private String matricule;
    
    private String email;
    
    private String typeCompte;
    
    private List<StructureEntity> structures;
    
    private List<UgpEntity> ugps;
    
    private List<MagasinEntity> magasins;
    
}
