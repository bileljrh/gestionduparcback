package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administratif.AssuranceEntity;
import com.gesparc.entities.carburant.cartePlafond.RechargeQuotaMensuelEntity;
import com.gesparc.entities.stock.MagasinVirtuelEntity;

@EnableAutoConfiguration
@Repository
public interface RechargeQuotaMensuelRepository extends PagingAndSortingRepository<RechargeQuotaMensuelEntity, Long> {

	@Query(value = "select * from recharge_quota_mensuel r ,carte_plafond c  where (r.carte_plafond_id=c.id) and (c.numero_carte =:cartePlafond)", nativeQuery = true)
    Page<RechargeQuotaMensuelEntity> findAllByNumCarte(String cartePlafond, Pageable pageable);
	
	 @Query("select count (*)  from recharge_quota_mensuel r where r.confirmed =false")
	 int findNombreNotif ();
	 

}
