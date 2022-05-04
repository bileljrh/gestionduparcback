package com.gesparc.sharedDTO.carburant;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.carburant.RechargeSousCompte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeRechargeSousCompteDTO {

	

	@Id
    @GeneratedValue
    private Long id;

    private String numCarte;
    
    private boolean confirmed;
    
    private boolean validated;
    
    private String typeCarburant;
    
    private Date dateDemande;
    
    private String demandeur ;
    
    private int quantiteDemande ;
    
    private int quantiteValide ;
}
