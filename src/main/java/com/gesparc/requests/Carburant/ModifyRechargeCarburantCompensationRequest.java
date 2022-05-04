package com.gesparc.requests.Carburant;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.sharedDTO.carburant.ModifyRechargeCarburantCompensationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyRechargeCarburantCompensationRequest {

	@Id
    @GeneratedValue()
    private Long id;
	
	String observation;

	String mission; 
	
	String destination;
}
