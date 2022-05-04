package com.gesparc.repositories.stock;

	import java.util.List;


	import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
	import org.springframework.data.domain.Page;
	import org.springframework.data.domain.Pageable;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.PagingAndSortingRepository;
	import org.springframework.stereotype.Repository;

import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.MagasinVirtuelEntity;

	@EnableAutoConfiguration
	@Repository
	public interface InventaireStockRepository  extends PagingAndSortingRepository<InventaireStock, Long> {

		
		@Query(value = "select * from Inventaire_Stock  r where (r.magasin =:magasin)", nativeQuery = true)
		Page<InventaireStock>  getinventaireByMagasin(String  magasin,Pageable pageable);
		
		@Query(value = "select * from Inventaire_Stock r ,article a,unite_gestion_parc u  where (a.ugp_id =u.id) and(r.id=a.inventaire_Stock_id) and (u.designation =:ugp)", nativeQuery = true)
		Page<InventaireStock>  getinventaireByUPG(String  ugp,Pageable pageable);

		@Query(value = "select * from Inventaire_Stock r ,article a,unite_gestion_parc u  where (a.ugp_id =u.id) and(r.id=a.inventaire_Stock_id) and (u.designation =:ugp)and (r.magasin =:magasin)", nativeQuery = true)
		Page<InventaireStock>  getinventaireByUPGMagasin(String  magasin,String  ugp,Pageable pageable);

		InventaireStock findInventaireStockById(Long inventaireStock);
		
		@Query(value = "select * from Inventaire_Stock a", nativeQuery = true)
		List<InventaireStock>  getinventaireMagasin();
		 
	}


