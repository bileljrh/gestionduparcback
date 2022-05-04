package com.gesparc.entities.carburant;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "historique_operation_recharge")
public class HistoriqueOperationRecharge  extends  AbstractBaseEntity{

	
	@Id
    @GeneratedValue
    private Long id;

    private String numSousCompte;
      
    private String typeCarburant;
    
    private Date dateDemande;
    
    private String demandeur ;
    
    private int quantiteDemande ;
    
    private int quantiteValide ;
    
}
