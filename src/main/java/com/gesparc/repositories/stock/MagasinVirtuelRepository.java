package com.gesparc.repositories.stock;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.stock.MagasinVirtuelEntity;

@EnableAutoConfiguration
@Repository
public interface MagasinVirtuelRepository extends PagingAndSortingRepository<MagasinVirtuelEntity, Long> {

    @Query(value = "select * from magasin_virtuel m ,mv_article mv,article a , unite_gestion_parc u  where (mv.magasin_virtuel_id=m.id) and (mv.article_id=a.id )and (a.ugp_id =u.id) and (u.designation =:ugp)", nativeQuery = true)
    Page<MagasinVirtuelEntity> findAllByUgp(String ugp, Pageable pageable);

}
