package com.gesparc.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.sharedDTO.CarteAgilisCashDTO;

import java.util.List;


@EnableAutoConfiguration
@Repository
public interface CarteAgilisCashRepository extends PagingAndSortingRepository<CarteAgilisCashEntity, Long> {
    Page<CarteAgilisCashEntity> findAllByTypeCarburant(String typeCarburant, Pageable pageable);

    @Query(value = "select count (id) from carte_agilis_cash", nativeQuery = true)
    Long getTotalNumberCarteAgilisCash();

    @Query(value = "select count (id) from carte_agilis_cash where (type_carburant = ?1)", nativeQuery = true)
    Long getTotalNumberCarteAgilisCashByTypeCarburant(String typeCarburant);

    List<CarteAgilisCashEntity> findAllByRecharged(Boolean recharged);

    List<CarteAgilisCashEntity> findAllByActivated(Boolean recharged);
    
    @Query(value="SELECT * FROM carte_agilis_cash cac WHERE cac.id NOT IN  ( SELECT  d.id_card FROM demande_annulation_carte_agilis_cash d  )", nativeQuery = true)
    List<CarteAgilisCashEntity> getAllCars();
    
    
    CarteAgilisCashEntity findByNumeroCarte(String numcarte);
}
