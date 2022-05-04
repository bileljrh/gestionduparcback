package com.gesparc.responses.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gesparc.responses.administration.MessageResponse;
import com.gesparc.responses.administration.TracabiliteResponse;
import com.gesparc.responses.referentiel.MagasinResponse;
import com.gesparc.responses.referentiel.StructureResponse;
import com.gesparc.responses.referentiel.UgpResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataTableResponse 
{	
    private Long id;
    
    private String nom;
    
    private String prenom;
    
    private String matricule;
    
    private int ordre;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String mot2passe;
    
    private String email;
    
    private String typeCompte;
    
    private Date dateDerniereConnexion;
    
    private Date dateAjout;
    
    private String role; 
    
    private boolean isActive;
    
    private boolean isNotLocked;
    
    private List<StructureResponse> structures;
    
    private List<UgpResponse> ugps;
    
    private List<MagasinResponse> magasins;

    @JsonIgnore
    List<TracabiliteResponse> tracabilites=new ArrayList<>();

    @JsonIgnore
    List<MessageResponse> messages=new ArrayList<>();

}
