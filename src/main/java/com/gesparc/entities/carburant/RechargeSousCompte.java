package com.gesparc.entities.carburant;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "demande_recharge_sous_compte")
public class RechargeSousCompte extends  AbstractBaseEntity{

	
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
