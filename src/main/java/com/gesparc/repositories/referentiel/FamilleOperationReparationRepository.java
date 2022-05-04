package com.gesparc.repositories.referentiel;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.FamilleOperationReparationEntity;

@Repository
@EnableAutoConfiguration
public interface FamilleOperationReparationRepository extends PagingAndSortingRepository<FamilleOperationReparationEntity, Long> {
}
