package com.gesparc.repositories;

import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;

import java.time.LocalDate;
import java.util.List;

@EnableAutoConfiguration
@Repository
public interface CarteJockerRepository extends PagingAndSortingRepository<CarteJockerEntity, Long> {

    List<CarteJockerEntity> findAllByAffected(boolean affected);

	List<CarteJockerEntity> findAllByVehiculeIdIsNull();
	
	 @Query(value="SELECT * FROM carte_jocker cj WHERE cj.id NOT IN  ( SELECT  d.id_carte FROM historique_desaffectation_carte_jocker d  )", nativeQuery = true)
	    List<CarteJockerEntity> getAllCars();
	 
	 @Query(value="SELECT * FROM carte_jocker cj WHERE (cj.vehicule_id IS NOT NULL) AND cj.id  IN  ( SELECT  d.carte_jocker_id FROM historique_affectation_carte_jocker d  ) AND cj.id  NOT IN  ( SELECT  d.id_carte FROM historique_desaffectation_carte_jocker d  ) ", nativeQuery = true)
	    List<CarteJockerEntity> listCartePretADesaffecter();


	 @Query("select cj from carte_jocker cj where  cj.numeroCarte like %:numeroCarte")
		CarteJockerEntity findByNumeroCarte (@Param("numeroCarte") String numeroCarte);

}
