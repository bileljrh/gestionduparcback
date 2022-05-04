package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteJocker.HistoriqueDesaffectationCarteJockerEntity;

import java.time.LocalDate;


@Repository
@EnableAutoConfiguration
public interface HistoriqueDesaffectationCarteJockerRepository extends PagingAndSortingRepository<HistoriqueDesaffectationCarteJockerEntity, Long> {

    @Query(value = "select MIN (date_confirmation_desaffectation) from historique_desaffectation_carte_jocker", nativeQuery = true)
    LocalDate getMinDateHistoriqueDesaffectationCarteJocker();

    @Query(value = "select Max (date_confirmation_desaffectation) from historique_desaffectation_carte_jocker", nativeQuery = true)
    LocalDate getMaxDateHistoriqueDesaffectationCarteJocker();


    @Query(value = "select * from historique_desaffectation_carte_jocker where (date_confirmation_desaffectation between ?1 and ?2)", nativeQuery = true)
    Page<HistoriqueDesaffectationCarteJockerEntity> getPaginationHistoriqueDesaffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax, Pageable pageable);


    @Query(value = "select count (id) from historique_desaffectation_carte_jocker where (date_confirmation_desaffectation between ?1 and ?2)", nativeQuery = true)
    Long getTotalNumberHistoriqueDesaffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax);


}
