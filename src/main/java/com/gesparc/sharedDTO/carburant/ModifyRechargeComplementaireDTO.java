package com.gesparc.sharedDTO.carburant;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyRechargeComplementaireDTO {

	@Id
    @GeneratedValue()
    private Long id;
	
	String observation;

	String mission; 
	
	String destination;
}
