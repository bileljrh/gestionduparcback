package com.gesparc.responses.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergieResponse implements Serializable 
{
    @JsonIgnore
    List<ProgrammeEntretiensPreventifsResponse> programmeEntretien = new ArrayList<>();
   
    @Id
    @GeneratedValue
    private Long id;
    
    private String code;
    
    private String description;
    
    private float prixUnitaire;
    
    private float tva;

}
