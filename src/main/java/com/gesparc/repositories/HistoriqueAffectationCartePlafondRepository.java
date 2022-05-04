package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;


@EnableAutoConfiguration
@Repository
public interface HistoriqueAffectationCartePlafondRepository extends PagingAndSortingRepository<HistoriqueAffectationCartePlafondEntity, Long> {

    @Query(value = "select count (id) from historique_affectation_carte_plafond where (type_carburant = ?1)", nativeQuery = true)
    Long getTotalItemsByTypeCarburant(String typeCarburant);

    @Query(value = "select count (id) from historique_affectation_carte_plafond", nativeQuery = true)
    Long getTotalItems();

    Page<HistoriqueAffectationCartePlafondEntity> findAllByTypeCarburant(String typeCarburant, Pageable pageable);
}
