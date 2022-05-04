package com.gesparc.entities.carburant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "prices")
public class PricesEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	private String date;
	private double essence;
	private double gazoilsanssoufre ;
	private double gazoil ;

}
