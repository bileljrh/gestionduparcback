package com.gesparc.requests.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gesparc.requests.Administration.MessageRequest;
import com.gesparc.requests.Administration.TracabiliteRequest;
import com.gesparc.requests.referentiel.MagasinRequest;
import com.gesparc.requests.referentiel.StructureRequest;
import com.gesparc.requests.referentiel.UgpRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable 
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
    
    private List<StructureRequest> structures;
    
    private List<UgpRequest> ugps;
    
    private List<MagasinRequest> magasins;

    @JsonIgnore
    List<TracabiliteRequest> tracabilites;

    @JsonIgnore
    List<MessageRequest> messages;

}
