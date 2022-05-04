package com.gesparc.requests.Achat.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDemandeArticleRequest implements Serializable {
    private Long id;
    private UpdateArticleRequest updateArticle;
    private int quantiteCommandee;
    private int quantiteLivree;
}
