package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administration.MessageApplicatifEntity;

@Repository
@EnableAutoConfiguration
public interface MessageApplicatifRepository extends PagingAndSortingRepository<MessageApplicatifEntity, Long> {
}
