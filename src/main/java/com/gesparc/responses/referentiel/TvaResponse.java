package com.gesparc.responses.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TvaResponse implements Serializable 
{
    private Long id;
    
    private float tva;
}
