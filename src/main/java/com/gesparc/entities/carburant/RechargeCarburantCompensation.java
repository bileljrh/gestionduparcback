package com.gesparc.entities.carburant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.EtatMensuelEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "recharge_carburant_compensation")
public class RechargeCarburantCompensation {
	@Id
    @GeneratedValue()
    private Long id;
	
	private String observation;
		
	private String matricule;
	
	private String mission; 
	
	private String destination;
	
    private boolean confirmed;
    
    private boolean validated;
    
    private int quantiteDemande;
    
    @ManyToOne()
    @JoinColumn(name = "carte_plafond_id")
    CartePlafondEntity cartePlafond;
    
}
