package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.Distribution2FonctionEntity;

import java.time.LocalDate;
import java.util.List;


@EnableAutoConfiguration
@Repository
public interface Distribution2FonctionRepository extends CrudRepository<Distribution2FonctionEntity, Long> {

    @Query(value = "select * from distribution_fonction where (source_carburant = ?1)", nativeQuery = true)
    List<Distribution2FonctionEntity> getListDistributionsCarburant2FonctionByType(String source);


    @Query(value = "select * from distribution_fonction where (mois_distribution between ?1 and ?2) and (source_carburant = ?3)", nativeQuery = true)
    List<Distribution2FonctionEntity> getListDistributionsCarburant2FonctionByCustomDateSearching(LocalDate dateStart, LocalDate dateDeadline, String source);

}
