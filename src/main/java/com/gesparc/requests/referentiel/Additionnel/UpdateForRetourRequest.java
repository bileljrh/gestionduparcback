package com.gesparc.requests.referentiel.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateForRetourRequest 
{

	 private Long id;
	 
	 private int quantiteStock;
	 
	 private String codeArticle;
	 
	 private Long retourStructure;
		
}
