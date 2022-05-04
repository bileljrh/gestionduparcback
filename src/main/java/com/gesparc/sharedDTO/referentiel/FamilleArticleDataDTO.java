package com.gesparc.sharedDTO.referentiel;



import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilleArticleDataDTO implements Serializable 
{
	private Long id;

	private String famille;
    
	private String code;

}
