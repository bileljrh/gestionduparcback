package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRetourStructureDTO 
{
	 private Long id;

	 private int quantiteStock;
	 
	 private String codeArticle;
	 
	 private Long retourStructure;


}
