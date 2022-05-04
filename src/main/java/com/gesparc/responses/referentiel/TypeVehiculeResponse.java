package com.gesparc.responses.referentiel;

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
public class TypeVehiculeResponse implements Serializable 
{
    @JsonIgnore
    List<ArticleResponse> articles = new ArrayList<>();
    
    @JsonIgnore
    List<ProgrammeEntretiensPreventifsResponse> programmeEntretien;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    private MarqueVehiculeResponse marques;

}
