package com.gesparc.sharedDTO.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.referentiel.EtatStockResponse;
import com.gesparc.sharedDTO.Achat.DemandeArticleDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelArticleDTO;

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
public class ArticleEtatStockDTO implements Serializable 
{
    private Long id;
 
    private String designation;
}
