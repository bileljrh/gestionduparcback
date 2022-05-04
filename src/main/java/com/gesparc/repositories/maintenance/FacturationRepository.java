package com.gesparc.repositories.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gesparc.entities.maintenance.FacturationEntity;

public interface FacturationRepository extends JpaRepository<FacturationEntity, Long>{

	FacturationEntity findByFournisseurId(Long id);

}
