package com.gesparc.repositories;

import java.time.LocalDateTime;
import java.util.Date;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.VisiteTechniqueEntity;
import com.gesparc.entities.referentiel.StructureEntity;

@EnableAutoConfiguration
@Repository
public interface VisiteTechniqueRepository extends PagingAndSortingRepository<VisiteTechniqueEntity, Long> {

    Page<VisiteTechniqueEntity> findAllByVehiculeStructure(StructureEntity structureEntity, Pageable page);
   
    
    
    @Query(value = "select * from visite_technique vt  ,vehicule v , structure s  where (vt.vehicule_id=v.id) and (v.structure_id=s.id ) and (s.designation =:structure)", nativeQuery = true)
    Page<VisiteTechniqueEntity> findAllByStructure(String structure, Pageable pageable);

    @Query(value = "select * from visite_technique vt  ,vehicule v   where (vt.vehicule_id=v.id) ", nativeQuery = true)
    List<VisiteTechniqueEntity> findVisiteTechniqueVehicule();
   
    @Query(value = "select count (*) from visite_technique v   where v.date_fin_validite = : date ", nativeQuery = true)
    Long getTotalNumberVisiteTechniqueVehicule(Date  date);



}
