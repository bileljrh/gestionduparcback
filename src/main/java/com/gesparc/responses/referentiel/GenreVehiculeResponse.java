package com.gesparc.responses.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreVehiculeResponse implements Serializable 
{
    @JsonIgnore
    List<ArticleResponse> articles = new ArrayList<>();
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String code;
    
    private String designation;
    
    private LocalDate dateAjout;
    
}
