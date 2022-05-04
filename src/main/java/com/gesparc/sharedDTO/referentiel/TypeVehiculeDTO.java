package com.gesparc.sharedDTO.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeVehiculeDTO implements Serializable 
{
    @JsonIgnore
    List<ArticleDTO> articles = new ArrayList<>();

    @JsonIgnore
    List<ProgrammeEntretiensPreventifsDTO> programmeEntretien;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private MarqueVehiculeDTO marques;
}
