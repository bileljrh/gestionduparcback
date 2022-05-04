package com.gesparc.repositories;


import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;


@EnableAutoConfiguration
@Repository
public interface CartePlafondRepository extends PagingAndSortingRepository<CartePlafondEntity, Long> {

	public static final String FIND_IDCARD = "SELECT  d.id FROM demande_annulation_carte_plafond d";

    Page<CartePlafondEntity> findAllByTypeCarburant(String typeCarburant, Pageable pageable);

    @Query(value = "select count (id) from carte_plafond where (type_carburant = ?1)", nativeQuery = true)
    Long getTotalNumberListCartePlafondByTypeCarburant(String typeCarburant);

    @Query(value = "select count (id) from carte_plafond", nativeQuery = true)
    Long getTotalNumberListCartePlafond();

    CartePlafondEntity findFirstByNumeroCarte(String numeroCarte);
    
    List<CartePlafondEntity> findAllByVehiculeIdIsNull();

    
    @Query(value="SELECT * FROM carte_plafond cp WHERE cp.id NOT IN  ( SELECT  d.id_card FROM demande_annulation_carte_plafond d  )", nativeQuery = true)
    List<CartePlafondEntity> getAllCars();
    
    CartePlafondEntity findByNumeroCarte(String numcarte);

}
