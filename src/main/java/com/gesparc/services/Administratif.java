package com.gesparc.services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.administratif.TaxeCirculationEntity;
import com.gesparc.entities.administratif.VisiteTechniqueEntity;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.sharedDTO.administratif.Additionnel.*;
import com.gesparc.sharedDTO.carburant.additionnel.AgilisFileDataTableDTO;
import com.gesparc.sharedDTO.carburant.additionnel.AgilisPriceDataTableDTO;

import java.util.List;


@EnableAutoConfiguration
@Service
public interface Administratif {

	List<BeneficiaireEntity> getListBeneficiaire();

	List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForGPS(UserEntity userEntity);

	List<TaxeCirculationTableDataDTO> getPaginationTaxeCirculationListByCustomSearching(CustomSearchingTaxeCirculationDTO customSearchingTaxeCirculationDTO, int page, int limit);

	List<GpsTableDataDTO> getPaginationGPSList(int page, int limit, UserEntity userEntity);

	List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForReforme(UserEntity userEntity);

	List<ReformeTableDataDTO> getPaginationReformeList(int page, int limit, UserEntity userEntity);

	List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForVisiteTechnique(UserEntity userEntity);

	List<AssuranceTableDataDTO> getPaginationAssuranceList(int page, int limit, UserEntity userEntity);

	List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForAssurance(UserEntity userEntity);

	List<TaxeCirculationTableDataDTO> getPaginationTaxeCirculationList(int page, int limit, UserEntity userEntity);

	List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForTaxeCirculation(UserEntity userEntity);

	List<VehiculeTableDataDTO> getPaginationListVehiculeTableData(int page, int limit, UserEntity userEntity);

    List<DocumentTableDataDTO> getListDocumentByVehicule(Long id);

	Long addNewVehicule(NewVehiculeDTO newVehiculeDTO);

    Long modifySelectedVehicule(NewVehiculeDTO newVehiculeDTO);

	Long getTotalItemVehiculeTableDataList(UserEntity userEntity);

	Long getTotalItemTaxeCirculationList(UserEntity userEntity);

	Long getTotalItemAssuranceList(UserEntity userEntity);

	Long getTotalItemVisiteTechniqueList(UserEntity userEntity);

	Long getTotalItemReformeList(UserEntity userEntity);

    List<VisiteTechniqueTableDataDTO> getPaginationVisiteTechniqueList(String structure ,int page, int limit, UserEntity userEntity);

	Long getTotalItemGPSList(UserEntity userEntity);
    
    void addNewTaxeCirculation(TaxeCirculationEntity taxeCirculationEntity, Long id);

    void modifySelectedTaxeCirculation(TaxeCirculationTableDataDTO taxeCirculationTableDataDTO);

    void deleteSelectedTaxeCirculation(Long id);

    void addNewAssurance(NewAssuranceDTO newAssuranceDTO);

    void modifySelectedAssurance(NewAssuranceDTO newAssuranceDTO);

    void deleteSelectedAssurance(Long id);

    void addNewVisiteTechnique(VisiteTechniqueTableDataDTO visiteTechniqueTableDataDTO);

    void modifySelectedVisiteTechnique(VisiteTechniqueTableDataDTO visiteTechniqueTableDataDTO);

    void deleteSelectedVisiteTechnique(Long id);

    void addNewReforme(ReformeTableDataDTO reformeTableDataDTO);

    void modifySelectedReforme(ReformeTableDataDTO reformeTableDataDTO);

    void deleteSelectedReforme(Long id);

    void addNewGPS(GpsTableDataDTO gpsTableDataDTO, Long idVehicule);

    void modifySelectedGPS(GpsTableDataDTO gpsTableDataDTO);

    void deleteSelectedGPS(Long id);

    void deleteSelectedVehicule(Long id);

    void deleteSelectedDocument(Long id);
    
    OneVehiculeDTO getSelectedVehicule(Long id);

	List<AgilisFileDataTableDTO> getPaginationDonneeExcelList(String idFile, int page, int limit);

	Long getTotalItemDataList();

	List<AgilisPriceDataTableDTO> getPaginationPriceList(int page, int limit);

	Long getTotalItemPricesList();

	List<VisiteTechniqueTableDataDTO> getPaginationVisiteTechniqueFinList(int page, int limit);

	List<VisiteTechniqueEntity> VisiteTechniqueFinList();

}
