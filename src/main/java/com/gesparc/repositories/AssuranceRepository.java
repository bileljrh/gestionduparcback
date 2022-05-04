package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.AssuranceEntity;


@EnableAutoConfiguration
@Repository
public interface AssuranceRepository extends PagingAndSortingRepository<AssuranceEntity, Long> {


}
