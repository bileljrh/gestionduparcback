package com.gesparc.sharedDTO.maintenance.additional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.referentiel.ArticleRequest;
import com.gesparc.requests.stock.MagasinVirtuelArticleRequest;
import com.gesparc.requests.stock.MagasinVirtuelRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtelierBonTravailDTO {
private long id;
}
