package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.Distribution2ServiceEntity;

import java.util.List;

@EnableAutoConfiguration
@Repository
public interface Distribution2ServiceRepository extends CrudRepository<Distribution2ServiceEntity, Long> {

    @Query(value = "select * from distribution_service where (type_distribution_service = ?1)", nativeQuery = true)
    List<Distribution2ServiceEntity> getListDistributionsCarburant2ServiceByType(String source);

}
