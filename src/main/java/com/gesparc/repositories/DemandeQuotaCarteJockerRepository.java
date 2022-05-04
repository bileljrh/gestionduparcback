package com.gesparc.repositories;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.carburant.carteJocker.HistoriqueDesaffectationCarteJockerEntity;


@EnableAutoConfiguration
@Repository
public interface DemandeQuotaCarteJockerRepository extends PagingAndSortingRepository<DemandeQuotaCarteJocker, Long> {

	
	@Query(value = "select MIN (dateDemande) from demandeQuotaCarteJocker", nativeQuery = true)
    LocalDate getMinDateDemandeQuotaCarteJocker();

    @Query(value = "select Max (dateDemande) from demandeQuotaCarteJocker", nativeQuery = true)
    LocalDate getMaxDateDemandeQuotaCarteJocker();


    @Query(value = "select * from demandeQuotaCarteJocker where (dateDemande between ?1 and ?2)", nativeQuery = true)
    Page<DemandeQuotaCarteJocker> getPaginationDemandeQuotaCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax, Pageable pageable);


    @Query(value = "select count (id) from demandeQuotaCarteJocker where (dateDemande between ?1 and ?2)", nativeQuery = true)
    Long getTotalNumberDemandeQuotaCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax);


    
    
}
