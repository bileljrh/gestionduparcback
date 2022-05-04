package com.gesparc.responses.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeResponse implements Serializable
{
    private Long id;
    
    private int annee;
}
