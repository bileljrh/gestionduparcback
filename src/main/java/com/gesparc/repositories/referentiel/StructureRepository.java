package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.StructureEntity;

@Repository
@EnableAutoConfiguration
public interface StructureRepository extends PagingAndSortingRepository<StructureEntity, Long> {

    StructureEntity findFirstByDesignation(String designation);
}