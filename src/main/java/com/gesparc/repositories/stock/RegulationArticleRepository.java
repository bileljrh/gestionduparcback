package com.gesparc.repositories.stock;

import java.util.List;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.stock.MagasinVirtuelEntity;
import com.gesparc.entities.stock.RegulationArticleStock;

@EnableAutoConfiguration
@Repository
public interface RegulationArticleRepository  extends PagingAndSortingRepository<RegulationArticleStock, Long> {
	@Query(value = "select * from regulation_article_stock r ,article a,unite_gestion_parc u  where (a.ugp_id =u.id)  and (u.designation =:ugp)", nativeQuery = true)
	Page<RegulationArticleStock>  getRegulationByUPG(String  ugp,Pageable pageable);

	@Query(value = "select * from regulation_article_stock r ,article a,unite_gestion_parc u  where (a.ugp_id =u.id) and(r.id=a.regultion_id) and (u.designation =:ugp)and (r.magasin =:magasin)", nativeQuery = true)
	Page<RegulationArticleStock>  getRegulationByUPGMagasin(String  magasin,String  ugp,Pageable pageable);

	RegulationArticleStock findRegulationArticleStockById(Long regulationStock);
	
	@Query(value = "select * from regulation_article_stock a", nativeQuery = true)
	List<RegulationArticleStock>  getRegulationMagasin();
	 
}
