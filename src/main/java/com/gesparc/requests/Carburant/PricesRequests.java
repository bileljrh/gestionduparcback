package com.gesparc.requests.Carburant;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricesRequests {
	
	private String date;
	private double essence;
	private double gazoilsanssoufre ;
	private double gazoil ;
	

}
