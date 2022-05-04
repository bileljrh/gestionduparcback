package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gesparc.entities.carburant.RechargeSousCompte;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;

@EnableAutoConfiguration
@Repository
public interface DemandeRechargeSousCompteRepository extends PagingAndSortingRepository <RechargeSousCompte, Long>{


	 @Query("select count (*)  from demande_recharge_sous_compte d where d.confirmed =false")
	 int findNombreNotif ();
	 
	 @Query("select count (*)  from demande_recharge_sous_compte d where ((d.confirmed =true)and(d.validated= false)) ")
	 int findNombreNotifValidation ();

}
