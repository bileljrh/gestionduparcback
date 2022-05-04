package com.gesparc.sharedDTO.security;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gesparc.sharedDTO.administration.MessageDTO;
import com.gesparc.sharedDTO.administration.TracabiliteDTO;
import com.gesparc.sharedDTO.referentiel.MagasinDTO;
import com.gesparc.sharedDTO.referentiel.StructureDTO;
import com.gesparc.sharedDTO.referentiel.UgpDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataTableDTO 
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
	    
	    private List<StructureDTO> structures;
	    
	    private List<UgpDTO> ugps;
	    
	    private List<MagasinDTO> magasins;

	    @JsonIgnore
	    List<TracabiliteDTO> tracabilites;

	    @JsonIgnore
	    List<MessageDTO> messages;
}
