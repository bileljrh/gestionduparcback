package com.gesparc.responses.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CauseSinistreResponse implements Serializable
{
    private Long id;
    
    private String code;
    
    private String designation;
    
    private LocalDate dateAjout;
    
}
