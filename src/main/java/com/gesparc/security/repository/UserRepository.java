package com.gesparc.security.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.security.entity.UserEntity;

import java.util.List;


@Repository
@EnableAutoConfiguration
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>
{

    UserEntity findFirstByMatricule(String matricule);

    UserEntity findFirstByEmail(String email);

    List<UserEntity> findAllByStructures(StructureEntity structure);

    boolean existsById(Long id);

}
