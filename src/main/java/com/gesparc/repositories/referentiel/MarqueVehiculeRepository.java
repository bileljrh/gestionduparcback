package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.MarqueVehiculeEntity;


@Repository
@EnableAutoConfiguration
public interface MarqueVehiculeRepository extends PagingAndSortingRepository<MarqueVehiculeEntity, Long> {

    MarqueVehiculeEntity findFirstByDesignation(String designation);

}
