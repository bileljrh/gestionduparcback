package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.DemandeAnnulationCarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;

import java.util.List;


@EnableAutoConfiguration
@Repository
public interface DemandeAnnulationCarteAgilisCashRepository extends CrudRepository<DemandeAnnulationCarteAgilisCashEntity, Long>,PagingAndSortingRepository<DemandeAnnulationCarteAgilisCashEntity, Long> {

    List<DemandeAnnulationCarteAgilisCashEntity> findAllByConfirmed(boolean confirmation);

    Page<DemandeAnnulationCarteAgilisCashEntity> findByConfirmed(boolean confirmed, Pageable pageable);

}
