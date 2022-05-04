package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.AssuranceEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueRechargeQuotaMensuelEntity;

@EnableAutoConfiguration
@Repository
public interface HistoriqueRechargeQuotaMensuelRepository extends PagingAndSortingRepository<HistoriqueRechargeQuotaMensuelEntity, Long> {

}
