package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.SectionEntity;

@Repository
@EnableAutoConfiguration
public interface SectionRepository extends PagingAndSortingRepository<SectionEntity, Long> {
}
