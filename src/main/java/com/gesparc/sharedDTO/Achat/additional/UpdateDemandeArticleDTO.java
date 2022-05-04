package com.gesparc.sharedDTO.Achat.additional;

import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDemandeArticleDTO {
    private Long id;
    private UpdateArticleRequest updateArticle;
    private int quantiteCommandee;
    private int quantiteLivree;
}
