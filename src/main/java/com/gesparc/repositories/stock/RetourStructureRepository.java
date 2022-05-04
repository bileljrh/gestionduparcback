package com.gesparc.repositories.stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.RetourStructure;

public interface RetourStructureRepository extends PagingAndSortingRepository<RetourStructure, Long> {

	RetourStructure findRetourStructureById(Long retourStructure);

	@Query(value = "select * from retour_structure  r where (r.magasin =:magasin)", nativeQuery = true)
	Page<RetourStructure>  getRetourByMagasin(String  magasin,Pageable pageable);
	
	@Query(value = "select * from retour_structure r ,article a,unite_gestion_parc u  where (a.ugp_id =u.id) and(r.id=a.retour_id) and (u.designation =:ugp)", nativeQuery = true)
	Page<RetourStructure>  getRetourByUPG(String  ugp,Pageable pageable);

	@Query(value = "select * from retour_structure r ,article a,unite_gestion_parc u  where (a.ugp_id =u.id) and(r.id=a.retour_id) and (u.designation =:ugp)and (r.magasin =:magasin)", nativeQuery = true)
	Page<RetourStructure>  getRetourByUPGMagasin(String  magasin,String  ugp,Pageable pageable);
		
}
