package com.gesparc.requests.stock.additionnel;

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
public class UpdateReguclationRequest  {

	@Id
	@GeneratedValue
	private Long id;

	 private String observation;
	 }
