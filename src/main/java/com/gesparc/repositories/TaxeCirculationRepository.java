package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.TaxeCirculationEntity;
import com.gesparc.entities.referentiel.StructureEntity;

import java.time.LocalDate;

@EnableAutoConfiguration
@Repository
public interface TaxeCirculationRepository extends PagingAndSortingRepository<TaxeCirculationEntity, Long> {

    Page<TaxeCirculationEntity> findAllByVehiculeStructure(StructureEntity structureEntity, Pageable pageable);

    @Query(value = "select MIN (poids) from taxe_ciruclation)", nativeQuery = true)
    int getMinPoids();

    @Query(value = "select Max (poids) from taxe_ciruclation)", nativeQuery = true)
    int getMaxPoids();

    @Query(value = "select MIN (nombre_places) from taxe_ciruclation)", nativeQuery = true)
    int getMinNombrePlaces();

    @Query(value = "select Max (nombre_places) from taxe_ciruclation)", nativeQuery = true)
    int getMaxNombrePlaces();

    @Query(value = "select Min (date_debut_circulation) from taxe_ciruclation)", nativeQuery = true)
    LocalDate getMinDateDebutCirculation();

    @Query(value = "select Max (date_fin_circulation) from taxe_ciruclation)", nativeQuery = true)
    LocalDate getMaxDateFinCirculation();

    @Query(value = "select Min (montant) from taxe_ciruclation)", nativeQuery = true)
    int getMinMontant();

    @Query(value = "select MAX (montant) from taxe_ciruclation)", nativeQuery = true)
    int getMaxMontant();

    @Query(value = "select Min (date_fin_validite) from taxe_ciruclation)", nativeQuery = true)
    LocalDate getMinDateFinValidite();

    @Query(value = "select Max (date_fin_validite) from taxe_ciruclation)", nativeQuery = true)
    LocalDate getMaxDateFinValidite();

    Page<TaxeCirculationEntity> findAllByDateDebutCirculationGreaterThanAndDateFinCirculationIsLessThanAndDateFinValiditeBetweenAndMontantBetweenAndPoidsBetween(LocalDate DateDebutCirculation, LocalDate DateFinCirculation, LocalDate MinDateFinValidite, LocalDate MaxDateFinValidite, int minMontant, int maxMontant, int minPoids, int maxPoids, Pageable page);

    Page<TaxeCirculationEntity> findAllByDateDebutCirculationGreaterThanAndDateFinCirculationIsLessThanAndDateFinValiditeBetweenAndMontantBetweenAndNombrePlacesBetween(LocalDate DateDebutCirculation, LocalDate DateFinCirculation, LocalDate MinDateFinValidite, LocalDate MaxDateFinValidite, int minMontant, int maxMontant, int minNombrePlace, int maxNombrePlace, Pageable page);

}
