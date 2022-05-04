package com.gesparc.repositories.maintenance;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;

public interface BonTravailOperationRepository extends JpaRepository<BonTravailOperationEntity, Long>{

	List<BonTravailOperationEntity> findByBonTravailId(Long id);
}
