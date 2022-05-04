package com.gesparc.repositories.referentiel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.sharedDTO.referentiel.FournisseurDTO;

import java.util.List;

@Repository
@EnableAutoConfiguration
public interface FournisseurRepository extends PagingAndSortingRepository<FournisseurEntity, Long> {

    List<FournisseurEntity> findAllByActivite(String activite);
    
    FournisseurDTO findFournisseurDTOById(Long id);
    
    FournisseurEntity findFournisseurById(Long id);

}
