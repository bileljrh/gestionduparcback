package com.gesparc.security.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.security.entity.RoleEntity;

@EnableAutoConfiguration
@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {

}
