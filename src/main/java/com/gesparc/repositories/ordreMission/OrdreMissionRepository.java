package com.gesparc.repositories.ordreMission;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.ordreMission.OrdreMissionEntity;
import com.gesparc.entities.referentiel.StructureEntity;


@Repository
@EnableAutoConfiguration
public interface OrdreMissionRepository extends PagingAndSortingRepository<OrdreMissionEntity, Long> {

    Page<OrdreMissionEntity> findAllByVehiculeStructure(StructureEntity structureEntity, Pageable page);

    Page<OrdreMissionEntity> findAllByConfirmed(Boolean confirmed, Pageable page);

    Page<OrdreMissionEntity> findAllByDepassantDateRetour(Boolean confirmed, Pageable page);

    Page<OrdreMissionEntity> findAllByVehiculeStructureAndConfirmed(StructureEntity structureEntity, Boolean confirmed, Pageable page);

    Page<OrdreMissionEntity> findAllByVehiculeStructureAndDepassantDateRetour(StructureEntity structureEntity, Boolean confirmed, Pageable page);

}
