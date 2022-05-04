package com.gesparc.repositories.maintenance;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;

@Repository
@EnableAutoConfiguration
public interface BonTravailRepository extends PagingAndSortingRepository<BonTravailEntity, Long> ,CrudRepository<BonTravailEntity,Long>{

	BonTravailEntity findByDemandeMaintenanceId(Long id);
	
    @Query(value = "select * from bon_travail where (mode = ?1)", nativeQuery = true)
    Page<BonTravailEntity> findAllByMode(String mode, Pageable pageable);
   
    @Query(value = "select * from bon_travail where (nature_travaux = ?1)", nativeQuery = true)
	Page<BonTravailEntity> findByNatureTravaux(String natureTravaux, PageRequest pageable);
    
    @Query(value = "select * from bon_travail where (mode = ?1) and (nature_travaux = ?2)", nativeQuery = true)
	Page<BonTravailEntity> findByModeAndNatureTravaux(String mode, String nature_travaux, PageRequest pageable);


    // les requetes de  filtrage par ugp, magasin , status
    
    
    //filtrage par magasin
    @Query(value="select * from bon_travail b, magasin m where (b.magasin_id=m.id )and (m.designation =:designation)", nativeQuery=true)
    Page<BonTravailEntity> findAllByMagasin(String designation, Pageable pageable);



// filtrage par ugp
    @Query(value = "select * from demande_maintenance d ,bon_travail b where b.demande_maintenance_id=d.id and (d.ugp =:ugp)", nativeQuery = true)
    Page<BonTravailEntity> findAllByUgp(String ugp, Pageable pageable);


// filtrage par sqtatus
    @Query(value = "select * from demande_maintenance d ,bon_travail b where b.demande_maintenance_id=d.id and (d.status =:status)", nativeQuery = true)
    Page<BonTravailEntity> findAllByStatus(String status, Pageable pageable);

    
    //filtrage par status et ugp
    @Query(value = "select * from bon_travail b,demande_maintenance d where( b.demande_maintenance_id=d.id )and(d.ugp =:ugp)and (d.status =:status)", nativeQuery = true)
    Page<BonTravailEntity> findAllByUgpAndStatus(String ugp ,String status, Pageable pageable);

    //filtrage par status et magasin
    @Query(value = "select * from demande_maintenance d ,bon_travail b , magasin m where b.demande_maintenance_id=d.id and b.magasin_id=m.id and (d.status =:status) and (m.designation =:magasin)", nativeQuery = true)
    Page<BonTravailEntity> findAllByStatusAndMagasin(String status,String magasin, Pageable pageable);

    
    //filtrage par ugp et magasin
    
    @Query(value = "select * from bon_travail b,magasin m ,demande_maintenance d where( b.demande_maintenance_id=d.id )and( b.magasin_id=m.id )and (d.ugp =:ugp) and (m.designation =:magasin)", nativeQuery = true)
    Page<BonTravailEntity> findAllByUgpAndMagasin(String ugp ,String magasin, Pageable pageable);

    
    //filtrage par ugp et magasin et status
    
    @Query(value = "select * from bon_travail b,magasin m ,demande_maintenance d where( b.demande_maintenance_id=d.id )and( b.magasin_id=m.id )and (d.ugp =:ugp) and (m.designation =:magasin)and (d.status =:status)", nativeQuery = true)
    Page<BonTravailEntity> findAllByUgpAndMagasinAndStatus(String ugp ,String magasin,String status, Pageable pageable);

}
