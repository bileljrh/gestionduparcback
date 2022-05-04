package com.gesparc.repositories.administratif;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.GpsEntity;
import com.gesparc.entities.referentiel.StructureEntity;

@Repository
@EnableAutoConfiguration
public interface GpsRepository extends PagingAndSortingRepository<GpsEntity, Long> {

	Page<GpsEntity> findAllByVehiculeStructure(StructureEntity structureEntity, Pageable page);
}
