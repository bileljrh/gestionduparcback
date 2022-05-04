package com.gesparc.services;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
@Service
public interface Achat {

    void addNewBonCommande(UpdateBonCommandeDTO updateBonCommandeDTO);

    void modifySelectedBonCommande(UpdateBonCommandeDTO updateBonCommandeDTO);

    void deleteSelectedBonCommande(Long id);



    Long getTotalItemBonCommandeList();
    
    Long getTotalItemMarcheList();

	

	List<BonCommandeDTO> getPaginationBonCommandeList2(String status, String parc, String fournisseur, String article,
			int page, int limit);

	
}
