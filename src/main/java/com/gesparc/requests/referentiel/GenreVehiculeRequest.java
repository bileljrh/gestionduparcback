package com.gesparc.requests.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreVehiculeRequest implements Serializable 
{
    @JsonIgnore
    List<ArticleRequest> articles = new ArrayList<>();
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    private LocalDate dateAjout;
}
