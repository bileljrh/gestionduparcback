package com.gesparc.repositories.stock;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.stock.MagasinVirtuelEntity;
import com.gesparc.entities.stock.ParcTransfertEntity;

@Repository
@EnableAutoConfiguration
public interface ParcTransfertRepository extends PagingAndSortingRepository<ParcTransfertEntity, Long>{

	 @Query(value = "select * from parc_transfert_article p ,ugp_article ua ,article a , unite_gestion_parc u  where (ua.parc_id=p.id) and (ua.article_id=a.id )and (a.ugp_id =u.id) and (u.designation =:ugp)", nativeQuery = true)
	    Page<ParcTransfertEntity> findAllByUgp(String ugp, Pageable pageable);
}
