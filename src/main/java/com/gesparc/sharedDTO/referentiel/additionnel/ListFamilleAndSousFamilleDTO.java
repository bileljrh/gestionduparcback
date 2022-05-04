package com.gesparc.sharedDTO.referentiel.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListFamilleAndSousFamilleDTO
{
    private String famille;

    private String codeFamille;
    
    private List<ListSousFamilleDTO> sousFamille;
}
