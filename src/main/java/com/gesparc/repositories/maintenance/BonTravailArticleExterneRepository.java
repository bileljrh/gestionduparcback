package com.gesparc.repositories.maintenance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;

public interface BonTravailArticleExterneRepository extends JpaRepository<BonTravailArticleExterneEntity,Long> {

	List<BonTravailArticleExterneEntity> findByBonTravailId(Long id);
}
