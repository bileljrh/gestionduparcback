package com.gesparc.sharedDTO.referentiel;

import java.time.LocalDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gesparc.entities.stock.RegulationArticleStock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleForRegulationDTO 
{
	 private Long id;
	 
	 private int quantiteStock;
	 
	 private String codeArticle;
	 
	 private Long regulationStock;

}
