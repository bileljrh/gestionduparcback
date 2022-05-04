package com.gesparc.repositories.maintenance;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;

import java.util.List;


@EnableAutoConfiguration
@Repository
public interface DemandeMaintenanceRepository extends PagingAndSortingRepository<DemandeMaintenanceEntity, Long> ,CrudRepository<DemandeMaintenanceEntity, Long> {

    @Query(value = "select * from demande_maintenance", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationDemandeMaintenance(Pageable pageable);

    @Query(value = "select * from demande_maintenance where (ugp = ?1)", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationDemandeMaintenanceByUGP(String ugp, Pageable pageable);

    @Query(value = "select * from demande_maintenance where (status = ?1)", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationDemandeMaintenanceByStatus(String status, Pageable pageable);

    @Query(value = "select * from demande_maintenance where (ugp = ?1) and (status = ?2)", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationDemandeMaintenanceByUGPAndStatus(String ugp, String status, Pageable pageable);

    @Query(value = "select count (id) from demande_maintenance", nativeQuery = true)
    Long getTotalNumberDemandeMaintenance();

    List<DemandeMaintenanceEntity> findAllByStatus(String status);
    
    Page<DemandeMaintenanceEntity> findAllByStatus(String status, Pageable pageable);
    
    Page<DemandeMaintenanceEntity> findByUgp(String ugp, Pageable pageable);
    
    Page<DemandeMaintenanceEntity> findByStructure(String structure, Pageable pageable);
    
    Page<DemandeMaintenanceEntity> findByStatusAndUgp(String status,String ugp, Pageable pageable);
    
    Page<DemandeMaintenanceEntity> findByStatusAndUgpAndStructure(String status,String ugp,String Structure, Pageable pageable);
    
    Page<DemandeMaintenanceEntity> findByStatusAndStructure(String status,String structure, Pageable pageable);
    
    Page<DemandeMaintenanceEntity> findByUgpAndStructure(String ugp,String structure, Pageable pageable);


    Page<DemandeMaintenanceEntity> findByUgpOrStatusOrStructure(String status,String ugp,String structure, Pageable pageable);

    
    
    
    @Query(value = "select * from demande_maintenance m ,vehicule v where((m.vehicule_id= v.id)and (m.status ='finis'))", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationHistoriqueDemandeMaintenance(Pageable pageable);

    @Query(value = "select * from demande_maintenance m  ,vehicule v where((m.vehicule_id= v.id)and (m.status ='finis')and(ugp = ?1))", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationHistoriqueDemandeMaintenanceByUGP(String ugp, Pageable pageable);

    @Query(value = "select * from demande_maintenance m,vehicule v where((m.vehicule_id= v.id)and (m.status ='finis'))", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationHistoriqueDemandeMaintenanceByStatus(String status, Pageable pageable);

    @Query(value = "select * from demande_maintenance m ,vehicule v where((m.vehicule_id= v.id)and (m.status ='finis')and (ugp = ?1))", nativeQuery = true)
    Page<DemandeMaintenanceEntity> getPaginationHistoriqueDemandeMaintenanceByUGPAndStatus(String ugp, String status, Pageable pageable);

       
    Page<DemandeMaintenanceEntity> findHistoriqueByUgp(String ugp, Pageable pageable);
    
    Page<DemandeMaintenanceEntity> findHistoriqueByStructure(String structure, Pageable pageable);
        
    Page<DemandeMaintenanceEntity> findHistoriqueByUgpAndStructure(String ugp,String Structure, Pageable pageable);
        
    Page<DemandeMaintenanceEntity> findHistoriqueByUgpOrStructure(String ugp,String structure, Pageable pageable);

    

	 @Query("select count (*)  from demande_maintenance d where d.status ='refus'")
	 int findNombreNotif ();
	 
	 @Query("select count (*)  from demande_maintenance d where (d.status ='finis') ")
	 int findNombreNotifValidation ();

	 
}
