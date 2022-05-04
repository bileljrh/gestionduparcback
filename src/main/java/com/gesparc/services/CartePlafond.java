package com.gesparc.services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.sharedDTO.DeclarationPerteCartePlafondDTO;
import com.gesparc.sharedDTO.DemandeAnnulationCartePlafondDTO;
import com.gesparc.sharedDTO.HistoriqueAffectationCartePlafondDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.NewCartePlafondDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CartePlafondTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CartePlafondTableDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataDTO;

import java.util.List;

@EnableAutoConfiguration
@Service
public interface CartePlafond {

    void createNewCartePlafond(NewCartePlafondDTO newCartePlafondDTO);

    void deleteSelectedCartePlafond(Long id);

    void modifySelectedCartePlafond(NewCartePlafondDTO newCartePlafondDTO);

    List<CartePlafondTabDataDTO> getPaginationListCartePlafondByTypeCarburant(String typeCarte, int page, int limit);

    Long getTotalNumberListCartePlafondByTypeCarburant(String typeCarburant);


    List<SelectVehiculeDTO> getListVehiculeWithNoCartePlafond(UserEntity userEntity);

    void createNewDemandAffectationCartePlafond(HistoriqueAffectationCartePlafondEntity hacpe);

    void confirmDemandeAffectationCartePlafond(Long idCarte);

    void validateDemandeAffectationCartePlafond(Long idCarte);

    void deleteDemandAffectationCartePlafond(Long id);

    Long getTotalItemsHistoriqueAffectationCartesPlafondBySelection(String typeCarburant);

   // List<HistoriqueAffectationCartePlafondDTO> getPaginationHistoriqueAffectationCartePlafond(int page, int limit, String selectedTypeCarburant);

    List<HistoriqueAffectationCartePlafondTabDataDTO> getPaginationHistoriqueAffectationCartePlafond(int page, int limit, String selectedTypeCarburant);
    
    void deleteSelectedHistoriqueAffectationCartePlafond(Long id);

    List<CartePlafondTabDataDTO> getListCartePlafond();

    void createNewDeclarationPerteCartePlafond(DeclarationPerteCartePlafondDTO declarationPerteCartePlafondDTO);

    void modifySelectedDeclarationPerteCartePlafond(DeclarationPerteCartePlafondDTO declarationPerteCartePlafondDTO);

    void createNewDemandeAnnulationCarteCarburant(DemandeAnnulationCartePlafondDTO demandeAnnulationCartePlafondDTO);

	Long getTotalNumberdeclarationPerteCartePlafond();

	List<DeclarationPerteCartePlafondDTO> getPaginationdeclarationperteCartePlafond(int page, int limit,String confirmed);

}
