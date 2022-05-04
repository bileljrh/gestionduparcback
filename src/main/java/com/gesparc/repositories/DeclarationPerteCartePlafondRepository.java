package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.DeclarationPerteCarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.DeclarationPerteCartePlafondEntity;

import java.util.List;


@EnableAutoConfiguration
@Repository
public interface DeclarationPerteCartePlafondRepository extends PagingAndSortingRepository<DeclarationPerteCartePlafondEntity, Long> {

    @Query(value = "select * from declaration_perte_carte_plafond where (confirmed = ?1)", nativeQuery = true)
    List<DeclarationPerteCartePlafondEntity> getListDeclarationPerteCartePlafondByConfirmation(boolean confirmation);
    
    Page<DeclarationPerteCartePlafondEntity> findByConfirmed(boolean confirmed, Pageable pageable);
}
