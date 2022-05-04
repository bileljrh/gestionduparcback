package com.gesparc.sharedDTO.referentiel;



import com.gesparc.entities.referentiel.GenreVehiculeEntity;
import com.gesparc.entities.referentiel.MarqueVehiculeEntity;
import com.gesparc.entities.referentiel.TypeVehiculeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ArticleDataTableDTO 
{
	private Long id;

	private String codeArticle;

	private String designation;
	
	private MarqueVehiculeEntity marqueVehicule;
	
	private TypeVehiculeEntity typeVehicule;
	
	private GenreVehiculeEntity genreVehicule;
	
	private int quantiteStock;
	
	private double prix;
}
