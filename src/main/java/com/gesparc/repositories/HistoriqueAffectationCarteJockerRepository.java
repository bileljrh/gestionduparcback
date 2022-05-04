package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteJocker.HistoriqueAffectationCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.HistoriqueDesaffectationCarteJockerEntity;

import java.time.LocalDate;
import java.util.Optional;


@EnableAutoConfiguration
@Repository
public interface HistoriqueAffectationCarteJockerRepository extends PagingAndSortingRepository<HistoriqueAffectationCarteJockerEntity, Long> {

    @Query(value = "select MIN (date_affectation) from historique_affectation_carte_jocker", nativeQuery = true)
    LocalDate getMinDateHistoriqueAffectationCarteJocker();

    @Query(value = "select Max (date_affectation) from historique_affectation_carte_jocker", nativeQuery = true)
    LocalDate getMaxDateHistoriqueAffectationCarteJocker();

    @Query(value = "select count (id) from historique_affectation_carte_jocker where (date_affectation between ?1 and ?2)", nativeQuery = true)
    Long getTotalHistoriqueAffectationCarteJockerByFilteredDate(LocalDate minDate, LocalDate maxDate);

    @Query(value = "select * from historique_affectation_carte_jocker where (date_affectation between ?1 and ?2)", nativeQuery = true)
    Page<HistoriqueAffectationCarteJockerEntity> getPaginationHistoriqueAffectationCarteJockerByFilteredDate(LocalDate minDate, LocalDate maxDate, Pageable pageable);

	void deleteByCarteJockerId(Long id);

	Optional<HistoriqueAffectationCarteJockerEntity> findByCarteJockerId(Long id);

}
