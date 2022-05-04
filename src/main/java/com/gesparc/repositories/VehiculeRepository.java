package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.referentiel.StructureEntity;

import java.util.List;


@EnableAutoConfiguration
@Repository
public interface VehiculeRepository extends PagingAndSortingRepository<VehiculeEntity, Long> {


    Page<VehiculeEntity> findAllByStructure(StructureEntity structureEntity, Pageable pageable);

    List<VehiculeEntity> findAllByStructure(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndTaxeCirculationIsNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndCarteAgilisCashIsNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndOrdreMissionsIsNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndEmpruntsIsNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndSinistreNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndAssuranceNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndVisiteTechniqueNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndReformeNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndReservationsNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndGpsNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByStructureAndCartePlafondNull(StructureEntity structureEntity);

    List<VehiculeEntity> findAllByTaxeCirculationNull();

    List<VehiculeEntity> findAllByCarteAgilisCashNull();

    List<VehiculeEntity> findAllByOrdreMissionsNull();

    List<VehiculeEntity> findAllByEmpruntsNull();

    List<VehiculeEntity> findAllBySinistreNull();

    List<VehiculeEntity> findAllByAssuranceNull();

    List<VehiculeEntity> findAllByVisiteTechniqueNull();

    List<VehiculeEntity> findAllByReformeNull();

    List<VehiculeEntity> findAllByReservationsNull();

    List<VehiculeEntity> findAllByGpsNull();

    List<VehiculeEntity> findAllByCartePlafondNull();

    List<VehiculeEntity> findAllByCarteJockerNull();
    
    List<VehiculeEntity> findByUgpId(Long id);

    List<VehiculeEntity> findByNumeroChassisAndNumeroCarteUtilisation(String numeroChassis , String numCarUt);
    
    

}
