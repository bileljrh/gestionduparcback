package com.gesparc.repositories.maintenance;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.maintenance.DemandeMaintenanceAnnule;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;

@EnableAutoConfiguration
@Repository
public interface DemandeMaintenanceAnnuleRepository extends PagingAndSortingRepository<DemandeMaintenanceAnnule, Long> ,CrudRepository<DemandeMaintenanceAnnule, Long> {

}
