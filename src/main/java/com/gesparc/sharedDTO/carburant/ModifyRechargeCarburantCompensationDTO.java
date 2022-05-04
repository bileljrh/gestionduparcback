package com.gesparc.sharedDTO.carburant;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.carburant.RechargeCarburantCompensation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyRechargeCarburantCompensationDTO {

	@Id
    @GeneratedValue()
    private Long id;
	
	String observation;

	String mission; 
	
	String destination;
}
