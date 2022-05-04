package com.gesparc.sharedDTO.carburant;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gesparc.entities.carburant.RechargeCarburantCompensation;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRechargeCarburantCompensationDTO {

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
