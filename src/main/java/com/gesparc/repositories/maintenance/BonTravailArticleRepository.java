package com.gesparc.repositories.maintenance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;

public interface BonTravailArticleRepository extends JpaRepository<BonTravailArticleEntity,Long>{
	
	List<BonTravailArticleEntity> findByBonTravailId(Long id);



}
