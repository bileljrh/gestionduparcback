package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.HistoriqueRecahrgeComplementaire;

@EnableAutoConfiguration
@Repository
public interface HistoriqueRecahrgeComplementaireRepository extends PagingAndSortingRepository<HistoriqueRecahrgeComplementaire, Long>  {

	HistoriqueRecahrgeComplementaire findHistoriqueRecahrgeComplementaireById(Long recharge);

}
