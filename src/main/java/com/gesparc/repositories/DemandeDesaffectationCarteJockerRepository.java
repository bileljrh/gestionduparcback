package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteJocker.DemandeDesaffectationCarteJockerEntity;

import java.time.LocalDate;


@EnableAutoConfiguration
@Repository
public interface DemandeDesaffectationCarteJockerRepository extends PagingAndSortingRepository<DemandeDesaffectationCarteJockerEntity, Long> {

    Page<DemandeDesaffectationCarteJockerEntity> findAllByDateDemandeDesaffectationBetween(LocalDate dateMin, LocalDate dateMax, Pageable pageable);

    @Query(value = "select MAX (date_demande_desaffectation) from demande_desaffectation_carte_jocker", nativeQuery = true)
    LocalDate getMaxDateDemandeDesaffectationCarteJocker();

    @Query(value = "select MIN (date_demande_desaffectation) from demande_desaffectation_carte_jocker", nativeQuery = true)
    LocalDate getMinDateDemandeDesaffectationCarteJocker();

    @Query(value = "select count (id) from demande_desaffectation_carte_jocker where (date_demande_desaffectation between ?1 and ?2)", nativeQuery = true)
    Long getTotalNumberDemandesDesaffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax);

}
