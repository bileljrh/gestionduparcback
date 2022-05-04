package com.gesparc.repositories.stock;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;

@EnableAutoConfiguration
@Repository
public interface MagasinVirtuelArticleReposiroty extends PagingAndSortingRepository<MagasinVirtuelArticleEntity, Long> {

	@Query(value = "SELECT * FROM mv_article a WHERE a.magasin_virtuel_id IN (SELECT mv.id FROM magasin_virtuel mv where mv.id =:id)", nativeQuery = true)
	List<MagasinVirtuelArticleEntity> getAllMVA(Long id);

	List<MagasinVirtuelArticleEntity> findByMagasinVirtuelId(Long id);

}
