package com.gesparc.repositories.achat;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.achat.MarcheEntity;

@EnableAutoConfiguration
@Repository
public interface MarcheRepository extends PagingAndSortingRepository<MarcheEntity, Long> {

}
