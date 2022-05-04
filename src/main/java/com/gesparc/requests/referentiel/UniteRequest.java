package com.gesparc.requests.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniteRequest implements Serializable 
{
    private Long id;
    
    private String unite;
}
