package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.DemandeAffectationCarteAgilisCashEntity;


@Repository
@EnableAutoConfiguration
public interface DemandeAffectationCarteAgilisCashRepository extends PagingAndSortingRepository<DemandeAffectationCarteAgilisCashEntity, Long> {
}
