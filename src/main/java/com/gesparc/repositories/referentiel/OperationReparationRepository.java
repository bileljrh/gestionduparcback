package com.gesparc.repositories.referentiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;


@Repository
@EnableAutoConfiguration
public interface OperationReparationRepository extends PagingAndSortingRepository<OperationReparationEntity, Long> ,CrudRepository<OperationReparationEntity, Long>{

	List<OperationReparationEntity> findByIdIn(@Param("ids") Long[] ids);

	@Query(value = "SELECT * FROM operation_reparation operationRepar where (famille_id = ?1)", nativeQuery = true)
	List<OperationReparationEntity> Famille(Long id);
}
