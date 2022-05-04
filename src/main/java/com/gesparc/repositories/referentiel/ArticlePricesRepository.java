package com.gesparc.repositories.referentiel;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.referentiel.ArticlePricesEntity;

@Repository
@EnableAutoConfiguration
public interface ArticlePricesRepository extends PagingAndSortingRepository<ArticlePricesEntity, Long> { 
//	@Query("SELECT * FROM article_prices a GROUP BY a.article_id")
//	List<ArticlePricesEntity> findByArticleId(Long id);
//	 @Query(value="SELECT * FROM carte_agilis_cash cac WHERE cac.id NOT IN  ( SELECT  d.id_card FROM demande_annulation_carte_agilis_cash d  )", nativeQuery = true)
//	    List<CarteAgilisCashEntity> getAllCars();
	
    @Query(value = "select  * from  article_prices a where (a.article_id = ?1)", nativeQuery = true)
    List<ArticlePricesEntity> findByAritcleidDistinct(Long id);

	
	
}


