package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.TypeVehiculeEntity;

@Repository
@EnableAutoConfiguration
public interface TypeVehiculeRepository extends PagingAndSortingRepository<TypeVehiculeEntity, Long> {

    TypeVehiculeEntity findFirstByDesignation(String designation);
}
