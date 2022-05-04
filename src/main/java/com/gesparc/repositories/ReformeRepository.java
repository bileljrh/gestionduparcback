package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.ReformeEntity;
import com.gesparc.entities.referentiel.StructureEntity;


@EnableAutoConfiguration
@Repository
public interface ReformeRepository extends PagingAndSortingRepository<ReformeEntity, Long> {

    Page<ReformeEntity> findAllByVehiculeStructure(StructureEntity structureEntity, Pageable page);
}
