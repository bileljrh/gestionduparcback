package com.gesparc.sharedDTO.stock.additionnel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRegulationDTO {

	@Id
	@GeneratedValue
	private Long id;

	 private String observation;
	 
}
