package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.MarqueVehiculeEntity;
import com.gesparc.entities.referentiel.ProgrammeEntretiensPreventifsEntity;
import com.gesparc.entities.referentiel.TypeVehiculeEntity;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
@EnableAutoConfiguration
public interface ProgrammeEntretiensPreventifsRepository extends PagingAndSortingRepository<ProgrammeEntretiensPreventifsEntity, Long> {

    Page<ProgrammeEntretiensPreventifsEntity> findAllByMarque(MarqueVehiculeEntity marqueVehiculeEntity , Pageable pageble);

    Page<ProgrammeEntretiensPreventifsEntity> findAllByType(TypeVehiculeEntity typeVehiculeEntity, Pageable pageble);
}
