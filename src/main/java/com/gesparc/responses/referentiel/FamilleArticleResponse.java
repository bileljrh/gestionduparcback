package com.gesparc.responses.referentiel;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class FamilleArticleResponse 
{
    List<SousFamilleArticleResponse> sousFamilles = new ArrayList<>();
    
    private Long id;
    
    private String famille;
    
    private String code;
    
    private LocalDate dateAjout;
    
}
