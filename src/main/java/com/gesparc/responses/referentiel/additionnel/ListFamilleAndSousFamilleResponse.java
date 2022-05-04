package com.gesparc.responses.referentiel.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListFamilleAndSousFamilleResponse 
{
    private String famille;
    
    private String codeFamille;
    
    private List<ListSousFamilleResponse> sousFamille;
}
