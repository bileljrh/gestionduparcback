package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.EtatMensuelEntity;

import java.time.LocalDate;
import java.util.List;


@EnableAutoConfiguration
@Repository
public interface EtatMensuelRepository extends CrudRepository<EtatMensuelEntity, Long> {

    @Query(value = "select * from etat_mensuel where (mois_etat between ?1 and ?2)", nativeQuery = true)
    List<EtatMensuelEntity> getEtatMensuelByDateSearching(LocalDate dateStart, LocalDate deadLine);

    @Query(value = "select * from etat_mensuel where (brouillon = ?1) and (confirme = ?2) and (valide = ?3)", nativeQuery = true)
    List<EtatMensuelEntity> getEtatMensuelByType(Boolean Brouillon, Boolean Confirme, Boolean Valide);

    @Query(value = "select * from etat_mensuel where (mois_etat between ?1 and ?2) and (brouillon = ?3) and (confirme = ?4) and (valide = ?5)", nativeQuery = true)
    List<EtatMensuelEntity> getEtatMensuelByDateSearchingAndType(LocalDate dateStart, LocalDate deadLine, Boolean Brouillon, Boolean Confirme, Boolean Valide);
}
