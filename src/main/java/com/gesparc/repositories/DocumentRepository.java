package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.DocumentEntity;


@EnableAutoConfiguration
@Repository
public interface DocumentRepository extends CrudRepository<DocumentEntity, Long> {

}
