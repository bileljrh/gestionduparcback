package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.gesparc.entities.carburant.RechargeCarburantCompensation;
import com.gesparc.entities.carburant.RechargeComplementaire;
import com.gesparc.entities.carburant.cartePlafond.RechargeQuotaMensuelEntity;

@EnableAutoConfiguration
@Repository
public interface RechargeCarburantCompensationRepository extends PagingAndSortingRepository<RechargeCarburantCompensation, Long> {


	@Query(value = "select * from recharge_carburant_compensation r ,carte_plafond c  where (r.carte_plafond_id=c.id) and (c.numero_carte =:cartePlafond)", nativeQuery = true)
    Page<RechargeCarburantCompensation> findAllByNumCarte(String cartePlafond, Pageable pageable);

	
	@Query(value = "select * from recharge_carburant_compensation d  where  ((d.confirmed =true)and(d.validated= false))", nativeQuery = true)
    Page<RechargeCarburantCompensation> findAllByValidated(Pageable pageable );


	 @Query("select count (*)  from recharge_carburant_compensation d where d.confirmed =false")
	 int findNombreNotif ();
	 
	 @Query("select count (*)  from recharge_carburant_compensation d where ((d.confirmed =true)and(d.validated= false)) ")
	 int findNombreNotifValidation ();

}
