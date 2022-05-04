package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.LieuParkingEntity;

import java.util.List;

@Repository
@EnableAutoConfiguration
public interface LieuParkingRepository extends PagingAndSortingRepository<LieuParkingEntity, Long> {

    List<LieuParkingEntity> findAllByGouvernorat_Designation(String Designation);

}
