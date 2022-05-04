package com.gesparc.repositories.ordreMission;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.ordreMission.AccompagnonMissionEntity;

@Repository
@EnableAutoConfiguration
public interface AccompagnonMissionRepository extends PagingAndSortingRepository<AccompagnonMissionEntity, Long> {
}
