package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.AtelierEntity;

@Repository
@EnableAutoConfiguration
public interface AtelierRepository extends PagingAndSortingRepository<AtelierEntity, Long> {
}
