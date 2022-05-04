package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.DeclarationPerteCarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteAgilis.DemandeAnnulationCarteAgilisCashEntity;

import java.util.List;

@Repository
@EnableAutoConfiguration
public interface DeclarationPerteCarteAgilisCashRepository extends PagingAndSortingRepository<DeclarationPerteCarteAgilisCashEntity, Long> {

    List<DeclarationPerteCarteAgilisCashEntity> findAllByConfirmed(boolean confirmed);
    
    Page<DeclarationPerteCarteAgilisCashEntity> findByConfirmed(boolean confirmed, Pageable pageable);

}
