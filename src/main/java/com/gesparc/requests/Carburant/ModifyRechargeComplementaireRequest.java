package com.gesparc.requests.Carburant;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyRechargeComplementaireRequest {

	@Id
    @GeneratedValue()
    private Long id;
	
	String observation;

	String mission; 
	
	String destination;
}
