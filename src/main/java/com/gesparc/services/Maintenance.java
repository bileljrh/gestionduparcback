package com.gesparc.services;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.requests.Maintenance.DemandeMaintenanceRequest;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.maintenance.DemandeMaintenanceDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.DemandeMaintenanceDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.ListVehiculeAvailable4MaintenanceDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;

import java.text.ParseException;
import java.util.List;

@EnableAutoConfiguration
@Service
public interface Maintenance {

    List<ListVehiculeAvailable4MaintenanceDTO> getListVehiculeAvailableForMaintenanceByStructure(String structure);

    void createNewDemandeMaintenance(DemandeMaintenanceDTO demandeMaintenanceDTO);

    void modifySelectedBonTravail(BonTravailDTO bonTravailDTO);

    void deleteSelectedBonTravail(Long id);

    void modifySelectedDemandeMaintenance(DemandeMaintenanceDTO demandeMaintenanceDTO);

    void deleteSelectedDemandeMaintenance(Long id);

    List<DemandeMaintenanceDataTableDTO> getPaginationDemandeMaintenanceList(String status ,String ugp,String structure,int page, int limit)throws ParseException;
    
    List<DemandeMaintenanceDTO> getDemandeMaintenanceListAvailableForBonTravail();

    Long getTotalItemsDemandeMaintenanceList();

    Long getTotalItemsDemandeMaintenanceListByStatus(String status);

    void createNewBonTravail(BonTravailDTO bonTravailDTO);


    Long getTotalItemBonTravailList();
    
    void addnewoperationtobontravail(UpdateBonTravailDTO updateBonTravailDTO );

	List<BonTravailDataTableDTO> getPaginationBonTravailList(int page, int limit);

	List<BonTravailDataTableDTO> getPaginationBonTravailList(String mode, String natureTravaux, int page, int limit);

	List<BonTravailDataTableDTO> getPaginationBonTravailList2(String designation,String status,String ugp, int page,int limit);

	Long getTotalItemsBonTravailList();

	List<DemandeMaintenanceDataTableDTO> getPaginationHistoriqueDemandeMaintenanceList(String status, String ugp,
			String structure, int page, int limit);

	List<DemandeMaintenanceDataTableDTO> getPaginationHistoriqueDemandeMaintenanceList(String ugp, String structure,
			int page, int limit);
	
	void annulerDemandeMaintenance(Long id);

	void reouvrirBonTravail(long id);

	int NombreNotifValidIntervention();

	int NombreNotifIntervention();


}
