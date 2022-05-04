package com.gesparc.responses.carburant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgilisPriceResponse {
	
	private Long id ;
	private String date;
	private double essence;
	private double gazoilsanssoufre ;
	private double gazoil ;

}
