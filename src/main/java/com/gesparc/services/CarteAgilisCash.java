package com.gesparc.services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.security.entity.UserEntity;
import com.gesparc.sharedDTO.*;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.NewCarteAgilisCashDTO;
import com.gesparc.sharedDTO.carburant.NewRechargeCarteAgilisCashDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CarteAgilisCashTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.DemandeAnnulationCarteAgilisCashDataTableDTO;
import com.gesparc.sharedDTO.carburant.additionnel.ListCarteAgilisCashDTO;
import com.gesparc.sharedDTO.carburant.additionnel.RechargeCarteAgilisCashTabDataDTO;

import java.util.List;

@EnableAutoConfiguration
@Service
public interface CarteAgilisCash {

    void createNewRechargeRequestCarteAgilisCash(NewRechargeCarteAgilisCashDTO newRechargeCarteAgilisCashRequest);

    List<DemandeAffectationCarteAgilisCashDTO> getListDemandeRechargeCarteAgilisCash();

    void deleteSelectedRechargeCarteAgilisCash(Long id);

    void modifySelectedRechargeCarteAgilisCash(NewRechargeCarteAgilisCashDTO newRechargeCarteAgilisCashDTO);

    List<HistoriqueAffectationCarteAgilisCashDTO> getListHistoriqueCarteAgilisCash();

    void deleteSelectedHistoriqueRechargeCarteAgilisCash(Long id);

    List<CarteAgilisCashDTO> getListAgilisCashResponse();

    List<DemandeAnnulationCarteAgilisCashDataTableDTO> getListDemandeAnnulationCarteAgilisCash(int limit , int page ,String confirmation);

    
    void createOneDeclarationPerteCarteAgilisCash(DeclarationPerteCarteAgilisCashDTO declarationPerteCarteAgilisCashDTO);

    List<DeclarationPerteCarteAgilisCashDTO> getListDeclarationPerteCarteAgilisCashByConfirmation(boolean confirmation);

    void confirmDeclarationPerteCarteAgilisCash(Long id);

    void deleteOneDeclarationPerteCarteAgilisCash(Long id);

    void modifyOneDeclarationPerteCarteAgilisCash(DeclarationPerteCarteAgilisCashDTO declarationPerteCarteAgilisCashDTO);

    List<DeclarationPerteCarteAgilisCashDTO> getHistoriqueDeclarationPerteCarteAgilisCash();

    void createNewDemandeAnnulationCarteCarburant(DemandeAnnulationCarteAgilisCashDTO demandeAnnulationCarteAgilisCashDTO);

    List<DemandeAnnulationCarteAgilisCashDTO> getHistoriqueDemandeAnnulationCarteAgilisCashByConfirmation(boolean confirmation);

    void deleteDemandeAnnulationCarteAgilisCash(Long id);

    List<VehiculeDTO> getListVehiculesServiceWithNoCarteAgilisCash();

    void createNewCarteAgilisCash(NewCarteAgilisCashDTO newCarteAgilisCashDTO);

    void modifySelectedCarteAgilisCash(NewCarteAgilisCashDTO newCarteAgilisCashDTO);

    List<CarteAgilisCashTabDataDTO> getPaginationListCarteAgilisCashByTypeCarburant(int page, int limit, String typeCarburant);

    Long getTotalNumberListCarteAgilisCashByTypeCarburant(String typeCarburant);

    void deleteSelectedCarteAgilisCash(Long id);

    List<ListCarteAgilisCashDTO> getListCarteAgilisCash();

    List<CarteAgilisCashDTO> getListAllCarteAgilisCash();

    List<DeclarationPerteCarteAgilisCashDTO> getHistoriqueDeclarationPerteCarteAgilisCashByConfirmation(String confirmed);

    void confirmDemandeAnnulationCarteAgilisCash(Long id);

    List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForAgilisCash(UserEntity userEntity);

    List<RechargeCarteAgilisCashTabDataDTO> getPaginationListRechargeCarteAgilisCash(int page, int limit);

    Long getTotalNumberListRechargeCarteAgilisCash();
    
    Long getTotalNumberAnnulationCarteAgilisCash();

    void confirmSelectedCarteAgilisCash(Long id);

    void validateSelectedCarteAgilisCash(Long id);
    
    
    void modifyOneDemandeAnnulationCarteAgilis(DemandeAnnulationCarteAgilisCashDTO demandeAnnulationCarteAgilisCashDTO);

	List<DeclarationPerteCarteAgilisCashDTO> getPaginationdeclarationperteCarteAgilis(int page, int limit);

	Long getTotalNumberdeclarationPerteCarteAgilis();

	List<DeclarationPerteCarteAgilisCashDTO> getPaginationdeclarationperteCarteAgilis(int page, int limit,String confirmed);
}
