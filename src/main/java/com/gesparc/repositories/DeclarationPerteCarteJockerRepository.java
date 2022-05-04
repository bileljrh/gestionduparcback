package com.gesparc.repositories;

import java.util.List;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.DeclarationPerteCarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;


@EnableAutoConfiguration
@Repository
public interface DeclarationPerteCarteJockerRepository extends PagingAndSortingRepository<DeclarationPerteCarteJockerEntity, Long> {

	 @Query(value = "select * from declaration_perte_carte_jocker where (confirmed = ?1)", nativeQuery = true)
	    List<DeclarationPerteCarteJockerEntity> getListDeclarationPerteCarteJockerByConfirmation(boolean confirmation);

	    Page<DeclarationPerteCarteJockerEntity> findByConfirmed(boolean confirmed, Pageable pageable);


}



   