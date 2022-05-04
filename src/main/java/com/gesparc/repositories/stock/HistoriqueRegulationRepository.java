package com.gesparc.repositories.stock;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.stock.HistoriqueRegulation;
import com.gesparc.entities.stock.RegulationArticleStock;

@EnableAutoConfiguration
@Repository
public interface HistoriqueRegulationRepository extends PagingAndSortingRepository<HistoriqueRegulation, Long> {

	HistoriqueRegulation findHistoriqueRegulationById(Long regulationStock);

}
