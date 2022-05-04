package com.gesparc.servicesImpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.ordreMission.AccompagnonMissionEntity;
import com.gesparc.entities.ordreMission.OrdreMissionEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.ordreMission.AccompagnonMissionRepository;
import com.gesparc.repositories.ordreMission.OrdreMissionRepository;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.services.OrdreMission;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.ordreMission.AccompagnonMissionDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.ListVehiculeAvailable4OrdreMissionDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.NewOrdreMissionDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.OrdreMissionTableDataDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
@Service
public class OrdreMissionImpl implements OrdreMission {

	@Autowired
	VehiculeRepository vehiculeRepository;

	@Autowired
	OrdreMissionRepository ordreMissionRepository;

	@Autowired
	AccompagnonMissionRepository accompagnonMissionRepository;

	Logger logger = LoggerFactory.getLogger(OrdreMissionImpl.class);

	@Override
	public List<ListVehiculeAvailable4OrdreMissionDTO> getListVehiculeAvailable4OrdreMissionByStructure(
			String structure) {
		List<ListVehiculeAvailable4OrdreMissionDTO> listVehiculeAvailable4OrdreMissionDTOS = new ArrayList<>();
		return listVehiculeAvailable4OrdreMissionDTOS;
	}

	@Override
	public void addNewOrdreMission(NewOrdreMissionDTO newOrdreMissionDTO) {
		OrdreMissionEntity ordreMissionEntity = new OrdreMissionEntity();
		ordreMissionEntity.setNumeroOrdre(newOrdreMissionDTO.getNumeroOrdre());
		ordreMissionEntity.setDateOrdre(newOrdreMissionDTO.getDateOrdre());
		ordreMissionEntity.setHeureDateDepart(newOrdreMissionDTO.getHeureDateDepart());
		ordreMissionEntity.setIndexDepart(newOrdreMissionDTO.getIndexDepart());
		ordreMissionEntity.setLieuDepart(newOrdreMissionDTO.getLieuDepart());
		ordreMissionEntity.setDestination(newOrdreMissionDTO.getDestination());
		ordreMissionEntity.setObjectifMission(newOrdreMissionDTO.getObjectifMission());
		ordreMissionEntity.setMarchandiseTransportee(newOrdreMissionDTO.getMarchandiseTransportee());
		ordreMissionEntity.setDateDebutValidite(newOrdreMissionDTO.getDateDebutValidite());
		ordreMissionEntity.setDateFinValidite(newOrdreMissionDTO.getDateFinValidite());
		ordreMissionEntity.setIndexRetour(newOrdreMissionDTO.getIndexRetour());
		ordreMissionEntity.setConfirmed(false);
		ordreMissionEntity.setDepassantDateRetour(false);
		if (newOrdreMissionDTO.getHeureDateRetour() != null) {
			ordreMissionEntity.setHeureDateRetour(newOrdreMissionDTO.getHeureDateRetour());
		}
		if (newOrdreMissionDTO.getIdVehicule() != null) {
			VehiculeEntity vehiculeEntity = vehiculeRepository.findById(newOrdreMissionDTO.getIdVehicule()).get();
			if (vehiculeEntity.getOrdreMissions().isEmpty()) {
				vehiculeEntity.setOrdreMissions(new ArrayList<>());
			}
			vehiculeEntity.getOrdreMissions().add(ordreMissionEntity);
			ordreMissionEntity.setVehicule(vehiculeEntity);
		}
		if (!newOrdreMissionDTO.getAccompagnons().isEmpty()) {
			ModelMapper modelMapper = new ModelMapper();
			ordreMissionEntity.setAccompagnons(new ArrayList<>());
			newOrdreMissionDTO.getAccompagnons().forEach(accompagnonMissionDTO -> {
				AccompagnonMissionEntity accompagnonMissionEntity = modelMapper.map(accompagnonMissionDTO,
						AccompagnonMissionEntity.class);
				accompagnonMissionEntity.setOrdreMission(ordreMissionEntity);
				ordreMissionEntity.getAccompagnons().add(accompagnonMissionEntity);
				accompagnonMissionRepository.save(accompagnonMissionEntity);
			});
		}
		ordreMissionRepository.save(ordreMissionEntity);
	}

	@Override
	public void modifySelectedOrdreMission(NewOrdreMissionDTO newOrdreMissionDTO) {
		OrdreMissionEntity ordreMissionEntity = ordreMissionRepository.findById(newOrdreMissionDTO.getId()).get();
		ordreMissionEntity.setNumeroOrdre(newOrdreMissionDTO.getNumeroOrdre());
		ordreMissionEntity.setDateOrdre(newOrdreMissionDTO.getDateOrdre());
		ordreMissionEntity.setHeureDateDepart(newOrdreMissionDTO.getHeureDateDepart());
		ordreMissionEntity.setIndexDepart(newOrdreMissionDTO.getIndexDepart());
		ordreMissionEntity.setLieuDepart(newOrdreMissionDTO.getLieuDepart());
		ordreMissionEntity.setDestination(newOrdreMissionDTO.getDestination());
		ordreMissionEntity.setObjectifMission(newOrdreMissionDTO.getObjectifMission());
		ordreMissionEntity.setMarchandiseTransportee(newOrdreMissionDTO.getMarchandiseTransportee());
		ordreMissionEntity.setDateDebutValidite(newOrdreMissionDTO.getDateDebutValidite());
		ordreMissionEntity.setDateFinValidite(newOrdreMissionDTO.getDateFinValidite());
		ordreMissionEntity.setIndexRetour(newOrdreMissionDTO.getIndexRetour());
		ordreMissionEntity.setConfirmed(newOrdreMissionDTO.isConfirmed());
		if (newOrdreMissionDTO.getHeureDateRetour() != null) {
			ordreMissionEntity.setHeureDateRetour(newOrdreMissionDTO.getHeureDateRetour());
		}
		ordreMissionRepository.save(ordreMissionEntity);
		if (!ordreMissionEntity.getAccompagnons().isEmpty()) {
			ordreMissionEntity.getAccompagnons().forEach(accompagnonMissionEntity -> {
				accompagnonMissionEntity.setOrdreMission(ordreMissionEntity);
				accompagnonMissionRepository.save(accompagnonMissionEntity);
				ordreMissionEntity.getAccompagnons().remove(accompagnonMissionEntity);
			});
		}
		if (!newOrdreMissionDTO.getAccompagnons().isEmpty()) {
			ModelMapper modelMapper = new ModelMapper();
			ordreMissionEntity.setAccompagnons(new ArrayList<>());
			newOrdreMissionDTO.getAccompagnons().forEach(accompagnonMissionDTO -> {
				AccompagnonMissionEntity accompagnonMissionEntity = new AccompagnonMissionEntity();
				accompagnonMissionEntity.setCin(accompagnonMissionDTO.getCin());
				accompagnonMissionEntity.setNom(accompagnonMissionDTO.getNom());
				accompagnonMissionEntity.setPrenom(accompagnonMissionDTO.getPrenom());
				accompagnonMissionEntity.setOrdreMission(ordreMissionEntity);
				ordreMissionEntity.getAccompagnons().add(accompagnonMissionEntity);
				accompagnonMissionRepository.save(accompagnonMissionEntity);
			});
		}
		ordreMissionRepository.save(ordreMissionEntity);
	}

	@Override
	public void confirmSelectedOrdreMission(Long id) {
		OrdreMissionEntity ordreMissionEntity = ordreMissionRepository.findById(id).get();
		ordreMissionEntity.setConfirmed(true);
		ordreMissionRepository.save(ordreMissionEntity);
	}

	@Override
	public void deleteSelectedOrdreMission(Long id) {
		OrdreMissionEntity ordreMissionEntity = ordreMissionRepository.findById(id).get();
		VehiculeEntity vehiculeEntity = ordreMissionEntity.getVehicule();
		vehiculeEntity.getOrdreMissions().remove(ordreMissionEntity);
		ordreMissionRepository.deleteById(id);
	}

	@Override
	public List<OrdreMissionTableDataDTO> getPaginationOrdreMissionListBySelectedEtat(UserEntity userEntity,
			String status, int page, int limit) {
		List<OrdreMissionEntity> ordreMissionEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (userEntity.getStructures().isEmpty()) {
			if (status.equals("En cours")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAllByConfirmed(false,
						pageable);
				ordreMissionEntities = ordreMissionEntityPage.getContent();
			} else if (status.equals("Confirmée")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAllByConfirmed(true,
						pageable);
				ordreMissionEntities = ordreMissionEntityPage.getContent();
			} else {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAll(pageable);
				ordreMissionEntities = ordreMissionEntityPage.getContent();
			}
		} else {
			StructureEntity structureEntity = userEntity.getStructures().get(0);
			if (status.equals("En cours")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
						.findAllByVehiculeStructureAndConfirmed(structureEntity, false, pageable);
				ordreMissionEntities = ordreMissionEntityPage.getContent();
			} else if (status.equals("Confirmée")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
						.findAllByVehiculeStructureAndConfirmed(structureEntity, true, pageable);
				ordreMissionEntities = ordreMissionEntityPage.getContent();
			} else {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
						.findAllByVehiculeStructure(structureEntity, pageable);
				ordreMissionEntities = ordreMissionEntityPage.getContent();
			}
		}
		return this.loadOrdreMissionTableDataDTO(ordreMissionEntities);
	}

	@Override
	public Long getTotalItemsOrdreMissionListBySelectedEtat(UserEntity userEntity, String status) {
		Long totalItems;
		PageRequest pageable = PageRequest.of(0, 8);
		if (userEntity.getStructures().isEmpty()) {
			if (status.equals("En cours")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAllByConfirmed(false,
						pageable);
				totalItems = ordreMissionEntityPage.getTotalElements();
			} else if (status.equals("Confirmée")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAllByConfirmed(true,
						pageable);
				totalItems = ordreMissionEntityPage.getTotalElements();
			} else {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAll(pageable);
				totalItems = ordreMissionEntityPage.getTotalElements();
			}
		} else {
			StructureEntity structureEntity = userEntity.getStructures().get(0);
			if (status.equals("En cours")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
						.findAllByVehiculeStructureAndConfirmed(structureEntity, false, pageable);
				totalItems = ordreMissionEntityPage.getTotalElements();
			} else if (status.equals("Confirmée")) {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
						.findAllByVehiculeStructureAndConfirmed(structureEntity, true, pageable);
				totalItems = ordreMissionEntityPage.getTotalElements();
			} else {
				Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
						.findAllByVehiculeStructure(structureEntity, pageable);
				totalItems = ordreMissionEntityPage.getTotalElements();
			}
		}
		return totalItems;
	}

	@Override
	public List<OrdreMissionTableDataDTO> getPaginationVehiculeListDepassantDateRetour(UserEntity userEntity, int page,
			int limit) {
		this.setVehiculeListDepassantDateRetour();
		List<OrdreMissionEntity> ordreMissionEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (userEntity.getStructures().isEmpty()) {
			Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAllByDepassantDateRetour(true,
					pageable);
			ordreMissionEntities = ordreMissionEntityPage.getContent();
		} else {
			StructureEntity structureEntity = userEntity.getStructures().get(0);
			Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
					.findAllByVehiculeStructureAndDepassantDateRetour(structureEntity, true, pageable);
			ordreMissionEntities = ordreMissionEntityPage.getContent();
		}
		return this.loadOrdreMissionTableDataDTO(ordreMissionEntities);
	}

	@Override
	public Long getTotalItemsVehiculeListDepassantDateRetour(UserEntity userEntity) {
		this.setVehiculeListDepassantDateRetour();
		Long totalItem;
		PageRequest pageable = PageRequest.of(0, 8);
		if (userEntity.getStructures().isEmpty()) {
			Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository.findAllByDepassantDateRetour(true,
					pageable);
			totalItem = ordreMissionEntityPage.getTotalElements();
		} else {
			StructureEntity structureEntity = userEntity.getStructures().get(0);
			Page<OrdreMissionEntity> ordreMissionEntityPage = ordreMissionRepository
					.findAllByVehiculeStructureAndDepassantDateRetour(structureEntity, true, pageable);
			totalItem = ordreMissionEntityPage.getTotalElements();
		}
		return totalItem;
	}

	@Override
	public List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForOrdreMission(UserEntity userEntity) {
		List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
		if (userEntity.getStructures().isEmpty()) {
			vehiculeEntities = vehiculeRepository.findAllByOrdreMissionsNull();
		} else {
			StructureEntity structureEntity = userEntity.getStructures().get(0);
			vehiculeEntities = vehiculeRepository.findAllByStructureAndOrdreMissionsIsNull(structureEntity);
		}
		return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
	}

	private List<SelectVehiculeDTO> loadSelectVehiculeByStrucuture(List<VehiculeEntity> vehiculeEntities) {
		List<SelectVehiculeDTO> selectVehiculeDTOS = new ArrayList<>();
		if (!vehiculeEntities.isEmpty()) {
			vehiculeEntities.forEach(vehiculeEntity -> {
				SelectVehiculeDTO selectVehiculeDTO = new SelectVehiculeDTO();
				selectVehiculeDTO.setId(vehiculeEntity.getId());
				selectVehiculeDTO.setNumeroPlaque(vehiculeEntity.getNumeroPlaque());
				selectVehiculeDTO.setCodeStructure(vehiculeEntity.getStructure().getCode());
				selectVehiculeDTO.setDesignationStructure(vehiculeEntity.getStructure().getDesignation());
				if (vehiculeEntity.getBeneficiaire() != null) {
					selectVehiculeDTO.setIdBeneficiaire(vehiculeEntity.getBeneficiaire().getId());
					selectVehiculeDTO.setNomBeneficiaire(vehiculeEntity.getBeneficiaire().getNom());
					selectVehiculeDTO.setMatriculeBeneficiaire(vehiculeEntity.getBeneficiaire().getMatricule());
					selectVehiculeDTO.setAgePermis(vehiculeEntity.getBeneficiaire().getAgePermis());
				}
				selectVehiculeDTOS.add(selectVehiculeDTO);
			});
		}
		return selectVehiculeDTOS;
	}

	private List<OrdreMissionTableDataDTO> loadOrdreMissionTableDataDTO(List<OrdreMissionEntity> ordreMissionEntities) {
		List<OrdreMissionTableDataDTO> ordreMissionTableDataDTOS = new ArrayList<>();
		if (!ordreMissionEntities.isEmpty()) {
			ordreMissionEntities.forEach(ordreMissionEntity -> {
				OrdreMissionTableDataDTO ordreMissionTableDataDTO = new OrdreMissionTableDataDTO();
				ordreMissionTableDataDTO.setId(ordreMissionEntity.getId());
				ordreMissionTableDataDTO.setIdVehicule(ordreMissionEntity.getVehicule().getId());
				ordreMissionTableDataDTO.setNumeroPlaque(ordreMissionEntity.getVehicule().getNumeroPlaque());
				ordreMissionTableDataDTO.setCodeStructure(ordreMissionEntity.getVehicule().getStructure().getCode());
				ordreMissionTableDataDTO
						.setDesignationStructure(ordreMissionEntity.getVehicule().getStructure().getDesignation());
				if (ordreMissionEntity.getVehicule().getBeneficiaire() != null) {
					ordreMissionTableDataDTO
							.setIdBeneficiaire(ordreMissionEntity.getVehicule().getBeneficiaire().getId());
					ordreMissionTableDataDTO
							.setNomBeneficiaire(ordreMissionEntity.getVehicule().getBeneficiaire().getNom());
					ordreMissionTableDataDTO.setMatriculeBeneficiaire(
							ordreMissionEntity.getVehicule().getBeneficiaire().getMatricule());
				}
				ordreMissionTableDataDTO.setNumeroOrdre(ordreMissionEntity.getNumeroOrdre());
				ordreMissionTableDataDTO.setDateOrdre(ordreMissionEntity.getDateOrdre());
				ordreMissionTableDataDTO.setHeureDateDepart(ordreMissionEntity.getHeureDateDepart());
				ordreMissionTableDataDTO.setIndexDepart(ordreMissionEntity.getIndexDepart());
				ordreMissionTableDataDTO.setLieuDepart(ordreMissionEntity.getLieuDepart());
				ordreMissionTableDataDTO.setDestination(ordreMissionEntity.getDestination());
				ordreMissionTableDataDTO.setObjectifMission(ordreMissionEntity.getObjectifMission());
				ordreMissionTableDataDTO.setMarchandiseTransportee(ordreMissionEntity.getMarchandiseTransportee());
				ordreMissionTableDataDTO.setDateDebutValidite(ordreMissionEntity.getDateDebutValidite());
				ordreMissionTableDataDTO.setDateFinValidite(ordreMissionEntity.getDateFinValidite());
				if (ordreMissionEntity.getHeureDateRetour() != null) {
					ordreMissionTableDataDTO.setHeureDateRetour(ordreMissionEntity.getHeureDateRetour());
				}
				ordreMissionTableDataDTO.setIndexRetour(ordreMissionEntity.getIndexRetour());
				ordreMissionTableDataDTO.setConfirmed(ordreMissionEntity.isConfirmed());
				ordreMissionTableDataDTO.setDepassantDateRetour(ordreMissionEntity.isDepassantDateRetour());
				if (!ordreMissionEntity.getAccompagnons().isEmpty()) {
					ordreMissionTableDataDTO.setAccompagnons(new ArrayList<>());
					ordreMissionEntity.getAccompagnons().forEach(accompagnonMissionEntity -> {
						AccompagnonMissionDTO accompagnonMissionDTO = new AccompagnonMissionDTO();
						accompagnonMissionDTO.setId(accompagnonMissionEntity.getId());
						accompagnonMissionDTO.setCin(accompagnonMissionEntity.getCin());
						accompagnonMissionDTO.setNom(accompagnonMissionEntity.getNom());
						accompagnonMissionDTO.setPrenom(accompagnonMissionEntity.getPrenom());
						ordreMissionTableDataDTO.getAccompagnons().add(accompagnonMissionDTO);
					});
				}
				ordreMissionTableDataDTOS.add(ordreMissionTableDataDTO);
			});
		}
		return ordreMissionTableDataDTOS;
	}

	private void setVehiculeListDepassantDateRetour() {
		List<OrdreMissionEntity> ordreMissionEntities = (List<OrdreMissionEntity>) ordreMissionRepository.findAll();
		if (!ordreMissionEntities.isEmpty()) {
			ordreMissionEntities.forEach(ordreMissionEntity -> {
				Date actualDate = new Date();
				if (ordreMissionEntity.getHeureDateRetour().after(actualDate)) {
					ordreMissionEntity.setDepassantDateRetour(true);
					ordreMissionRepository.save(ordreMissionEntity);
				}
			});
		}
	}

}
