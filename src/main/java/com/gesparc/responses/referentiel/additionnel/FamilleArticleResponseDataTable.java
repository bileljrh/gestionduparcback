package com.gesparc.responses.referentiel.additionnel;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilleArticleResponseDataTable implements Serializable 
{
		
    private Long id;
    
    private String famille;
    
    private String code;

}
