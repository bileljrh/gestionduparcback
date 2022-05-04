package com.gesparc.sharedDTO.administration.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDTO implements Serializable
{
    private Long id;

    private String nom;
    
    private String prenom;
    
    private String matricule;
    
    private int ordre;
    
    private String mot2passe;
    
    private String typeCompte;
    
    private String email;
    
    private Long idStructure;
    
    private Long idUgp;
    
    private Long idMagasin;
    
    private Long idRole;

    
}
