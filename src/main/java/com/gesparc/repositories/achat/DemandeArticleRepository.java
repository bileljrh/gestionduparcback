package com.gesparc.repositories.achat;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.achat.DemandeArticleEntity;

@EnableAutoConfiguration
@Repository
public interface DemandeArticleRepository extends PagingAndSortingRepository<DemandeArticleEntity, Long> {

	List<DemandeArticleEntity> findByBonCommandeId(Long id);

}
