package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniteDTO implements Serializable 
{
    private Long id;

    private String unite;
}
