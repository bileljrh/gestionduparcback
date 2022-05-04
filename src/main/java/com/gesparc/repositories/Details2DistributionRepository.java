package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.Details2DistributionEntity;


@EnableAutoConfiguration
@Repository
public interface Details2DistributionRepository extends CrudRepository<Details2DistributionEntity, Long> {
}
