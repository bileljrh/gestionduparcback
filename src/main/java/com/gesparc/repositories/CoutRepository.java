package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.CoutEntity;


@EnableAutoConfiguration
@Repository
public interface CoutRepository extends CrudRepository<CoutEntity, Long> {
}
