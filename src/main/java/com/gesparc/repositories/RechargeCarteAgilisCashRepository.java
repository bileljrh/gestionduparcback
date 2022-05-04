package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.RechargeCarteAgilisCashEntity;


@EnableAutoConfiguration
@Repository
public interface RechargeCarteAgilisCashRepository extends PagingAndSortingRepository<RechargeCarteAgilisCashEntity, Long> {

}
