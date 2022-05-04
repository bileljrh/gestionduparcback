package com.gesparc.repositories.stock;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;
import com.gesparc.entities.stock.ParcTransfertArticleEntity;
import com.gesparc.entities.stock.RetourStructure;

@EnableAutoConfiguration
@Repository
public interface UgpArticleRepository extends PagingAndSortingRepository<ParcTransfertArticleEntity, Long> {
	
	@Query(value = "SELECT * FROM pt_article a WHERE a.parc_transfert_id IN (SELECT pt.id FROM parc_transfert pt where pt.id =:id)", nativeQuery = true)
	List<ParcTransfertArticleEntity> getAllPTA(Long id);

	List<ParcTransfertArticleEntity> findByParcTransfertId(Long id);

}
