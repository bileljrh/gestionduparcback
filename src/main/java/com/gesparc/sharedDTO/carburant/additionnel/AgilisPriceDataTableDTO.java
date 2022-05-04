package com.gesparc.sharedDTO.carburant.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgilisPriceDataTableDTO {

	private Long id ;
	private String date;
	private double essence;
	private double gazoilsanssoufre ;
	private double gazoil ;
}
