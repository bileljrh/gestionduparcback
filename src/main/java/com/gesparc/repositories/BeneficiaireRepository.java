package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.BeneficiaireEntity;

import java.util.List;


@EnableAutoConfiguration
@Repository
public interface BeneficiaireRepository extends CrudRepository<BeneficiaireEntity, Long> {

    @Query(value = "select * from beneficiaire where (type_beneficiaire = ?1)", nativeQuery = true)
    List<BeneficiaireEntity> getListBeneficiairesByType(String type);
}
