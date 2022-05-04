package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.gesparc.entities.carburant.HistoriqueRechargeCarburantCompensation;

@EnableAutoConfiguration
@Repository
public interface HistoriqueRechargeCarburantCompensationRepository extends PagingAndSortingRepository<HistoriqueRechargeCarburantCompensation, Long>  {

	HistoriqueRechargeCarburantCompensation findHistoriqueRechargeCarburantCompensationById(Long recharge);

}
