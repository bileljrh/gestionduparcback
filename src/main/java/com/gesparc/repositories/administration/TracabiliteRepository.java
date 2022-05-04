package com.gesparc.repositories.administration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administration.TracabiliteEntity;
import com.gesparc.security.entity.UserEntity;

import java.time.LocalDate;


@Repository
@EnableAutoConfiguration
public interface TracabiliteRepository extends PagingAndSortingRepository<TracabiliteEntity, Long> {

    @Query(value = "select max (date_operation) from tracabilite", nativeQuery = true)
    LocalDate getMaxDateForAllModulesAndUsers();

    @Query(value = "select max (date_operation) from tracabilite where (nom_module = ?1)", nativeQuery = true)
    LocalDate getMaxDateForAllUsersByModule(String nomModule);

    @Query(value = "select max (date_operation) from tracabilite where (utilisateur_id = ?1)", nativeQuery = true)
    LocalDate getMaxDateForAllModulesByUser(Long idUser);

    @Query(value = "select max (date_operation) from tracabilite where (utilisateur_id = ?1) and (nom_module = ?2)", nativeQuery = true)
    LocalDate getMaxDateByUserAndByModule(Long idUser, String nomModule);

    @Query(value = "select min (date_operation) from tracabilite", nativeQuery = true)
    LocalDate getMinDateForAllModulesAndUsers();

    @Query(value = "select min (date_operation) from tracabilite where (nom_module = ?1)", nativeQuery = true)
    LocalDate getMinDateForAllUsersByModule(String nomModule);

    @Query(value = "select min (date_operation) from tracabilite where (utilisateur_id = ?1)", nativeQuery = true)
    LocalDate getMinDateForAllModulesByUser(Long idUser);

    @Query(value = "select min (date_operation) from tracabilite where (utilisateur_id = ?1) and (nom_module = ?2)", nativeQuery = true)
    LocalDate getMinDateByUserAndByModule(Long idUser, String nomModule);

    Page<TracabiliteEntity> findAllByDateOperationBetween(LocalDate dateMin, LocalDate dateMax, Pageable pageable);

    Page<TracabiliteEntity> findAllByDateOperationBetweenAndNomModule(LocalDate dateMin, LocalDate dateMax, String nomModule, Pageable pageable);

    Page<TracabiliteEntity> findAllByDateOperationBetweenAndUser(LocalDate dateMin, LocalDate dateMax, UserEntity userEntity, Pageable pageable);

    Page<TracabiliteEntity> findAllByDateOperationBetweenAndUserAndNomModule(LocalDate dateMin, LocalDate dateMax, UserEntity userEntity, String nomModule, Pageable pageable);

    boolean existsByUserId(Long id);
}

