package com.gesparc.repositories.referentiel;


import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.UgpEntity;

@Repository
@EnableAutoConfiguration
public interface UgpRepository extends PagingAndSortingRepository<UgpEntity, Long> {
	
	@Query(value="SELECT * FROM unite_gestion_parc ugp WHERE ugp.id  IN ( SELECT s.ugp_id FROM structure s WHERE s.id=?1 )", nativeQuery = true)
	UgpEntity findAllVehicule(Long id);
}
