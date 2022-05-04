package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.cartePlafond.HistoriqueDesaffectationCartePlafondEntity;


@EnableAutoConfiguration
@Repository
public interface HistoriqueDesaffectationCartePlafondRepository extends CrudRepository<HistoriqueDesaffectationCartePlafondEntity, Long> {
}
