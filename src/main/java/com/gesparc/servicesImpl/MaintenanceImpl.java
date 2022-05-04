package com.gesparc.servicesImpl;

import lombok.Builder;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceAnnule;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.entities.referentiel.TypeVehiculeEntity;
import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;
import com.gesparc.entities.stock.MagasinVirtuelEntity;
import com.gesparc.exceptions.ResourceNotFoundException;
import com.gesparc.messages.ApiResponse;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.maintenance.BonTravailRepository;
import com.gesparc.repositories.maintenance.DemandeMaintenanceAnnuleRepository;
import com.gesparc.repositories.maintenance.DemandeMaintenanceRepository;
import com.gesparc.repositories.referentiel.OperationReparationRepository;
import com.gesparc.requests.Maintenance.DemandeMaintenanceRequest;
import com.gesparc.responses.maintenance.DemandeMaintenanceResponse;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.services.Maintenance;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.maintenance.DemandeMaintenanceDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.DemandeMaintenanceDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.ListVehiculeAvailable4MaintenanceDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailDTO;
import com.gesparc.sharedDTO.referentiel.TypeVehiculeDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@EnableAutoConfiguration
@Service
public class MaintenanceImpl implements Maintenance {

	Logger logger = LoggerFactory.getLogger(MaintenanceImpl.class);

	@Autowired
	DemandeMaintenanceRepository demandeMaintenanceRepository;

	@Autowired
	BonTravailRepository bonTravailRepository;

	@Autowired
	private VehiculeRepository vehiculeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OperationReparationRepository operationReparationRepository;

	@Autowired
	private DemandeMaintenanceAnnuleRepository demandeMaintenanceAnnuleRepository;
	
	@Override
	public void annulerDemandeMaintenance(@PathVariable Long id) {
	DemandeMaintenanceEntity dme = demandeMaintenanceRepository.findById(id).get();
	dme.setAnnulation(true);
	DemandeMaintenanceAnnule demandeAnnule = new DemandeMaintenanceAnnule ();
	demandeAnnule.setNumeroSerie(dme.getNumeroSerie());
	demandeAnnule.setDescriptionIntervention(dme.getDescriptionIntervention());
	demandeAnnule.setUgpReparation(dme.getUgpReparation());
	demandeAnnule.setDemandeur(dme.getDemandeur());
	demandeAnnule.setNumeroDemande(dme.getNumeroDemande());
	demandeAnnule.setDateDemande(dme.getDateRDV());
	demandeAnnule.setAnnulation(true);
	demandeAnnule.setNumeroDemande(dme.getNumeroDemande());
	demandeAnnule.setStructure(dme.getStructure());
	demandeAnnule.setStatus(dme.getStatus());
	demandeAnnule.setIndexKm(dme.getIndexKm());
	demandeAnnule.setObservation(dme.getObservation());
	demandeAnnule.setNomBeneficiaire(dme.getNomBeneficiaire());
	demandeAnnule.setUgp(dme.getUgp());
	demandeAnnule.setVehicule(dme.getVehicule());
	DemandeMaintenanceAnnule annule = demandeMaintenanceAnnuleRepository.save(demandeAnnule);
	 demandeMaintenanceRepository.delete(dme);
	}
	
	@Override
	public List<ListVehiculeAvailable4MaintenanceDTO> getListVehiculeAvailableForMaintenanceByStructure(
			String structure) {
		List<ListVehiculeAvailable4MaintenanceDTO> listVehiculeAvailable4MaintenanceDTOS = new ArrayList<>();
		return listVehiculeAvailable4MaintenanceDTOS;
	}

	@Override
	public void createNewDemandeMaintenance(DemandeMaintenanceDTO demandeMaintenanceDTO) {
		DemandeMaintenanceEntity demandeMaintenanceEntity = new DemandeMaintenanceEntity();
		demandeMaintenanceEntity.setNumeroDemande(
				this.generateNumeroBonCommande(demandeMaintenanceRepository.getTotalNumberDemandeMaintenance()));
		System.out.println(demandeMaintenanceDTO.getDemandeur());
		System.out.println(
				(this.generateNumeroBonCommande(demandeMaintenanceRepository.getTotalNumberDemandeMaintenance())));
		demandeMaintenanceEntity.setIndexKm(demandeMaintenanceDTO.getIndexKm());
		System.out.println(demandeMaintenanceDTO.getIndexKm());
		VehiculeEntity vehiculeEntity = vehiculeRepository.findById(demandeMaintenanceDTO.getVehiculedto().getId())
				.get();
		System.out.println("hello ");
		System.out.println(demandeMaintenanceDTO.getVehiculedto().getId());
		if (vehiculeEntity.getDemandeMaintenances() == null) {
			vehiculeEntity.setDemandeMaintenances(new ArrayList<>());
		}
		demandeMaintenanceEntity.setVehicule(vehiculeEntity);
		vehiculeEntity.getDemandeMaintenances().add(demandeMaintenanceEntity);
		System.out.println(
				this.generateNumeroBonCommande(demandeMaintenanceRepository.getTotalNumberDemandeMaintenance()));
		demandeMaintenanceRepository.save(demandeMaintenanceEntity);
	}

	@Override
	public void modifySelectedDemandeMaintenance(DemandeMaintenanceDTO demandeMaintenanceDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeMaintenanceEntity demandeMaintenanceEntity = modelMapper.map(demandeMaintenanceDTO,
				DemandeMaintenanceEntity.class);
		demandeMaintenanceRepository.save(demandeMaintenanceEntity);
	}

	@Override
	public void deleteSelectedDemandeMaintenance(Long id) {
		demandeMaintenanceRepository.deleteById(id);
	}

	@Override
	public Long getTotalItemsDemandeMaintenanceList() {
		PageRequest pageable = PageRequest.of(0, 8);
		Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository.findAll(pageable);
		return demandeMaintenanceEntityPage.getTotalElements();
	}

	@Override
	public Long getTotalItemsBonTravailList() {
		PageRequest pageable = PageRequest.of(0, 8);
		Page<BonTravailEntity> bonTravailEntityPage = bonTravailRepository.findAll(pageable);
		return bonTravailEntityPage.getTotalElements();
	}

	private String generateNumeroBonCommande(Long totalNumber) {
		LocalDate localDate = LocalDate.now();
		return String.format("%04d", totalNumber) + "-" + LocalDate.now().getDayOfMonth()
				+ LocalDate.now().getMonthValue() + LocalDate.now().getYear();
	}

	@Override
	public List<DemandeMaintenanceDTO> getDemandeMaintenanceListAvailableForBonTravail() {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeMaintenanceDTO> typeVehiculeDTOS = new ArrayList<>();
		List<DemandeMaintenanceEntity> demandeMaintenanceEntities = (List<DemandeMaintenanceEntity>) demandeMaintenanceRepository
				.findAllByStatus("Accord");
		if (!demandeMaintenanceEntities.isEmpty()) {
			demandeMaintenanceEntities.forEach(demandeMaintenanceEntity -> {
				typeVehiculeDTOS.add(modelMapper.map(demandeMaintenanceEntity, DemandeMaintenanceDTO.class));
			});
		}
		return typeVehiculeDTOS;
	}

	@Override
	public Long getTotalItemsDemandeMaintenanceListByStatus(String status) {
		PageRequest pageable = PageRequest.of(0, 8);
		Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository
				.getPaginationDemandeMaintenanceByStatus(status, pageable);
		return demandeMaintenanceEntityPage.getTotalElements();
	}

	@Override
	public void createNewBonTravail(BonTravailDTO bonTravailDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BonTravailEntity bonTravailEntity = modelMapper.map(bonTravailDTO, BonTravailEntity.class);
		DemandeMaintenanceEntity demandeMaintenanceEntity = demandeMaintenanceRepository
				.findById(bonTravailDTO.getDemandeMaintenance().getId()).get();
		demandeMaintenanceEntity.setBonTravaill(bonTravailEntity);
		bonTravailEntity.setDemandeMaintenance(demandeMaintenanceEntity);
		bonTravailRepository.save(bonTravailEntity);
		demandeMaintenanceEntity.setStatus("finis");
		demandeMaintenanceRepository.save(demandeMaintenanceEntity);
	}

	@Override
	public List<BonTravailDataTableDTO> getPaginationBonTravailList(String mode, String natureTravaux, int page,
			int limit) {
		PageRequest pageable = PageRequest.of(page, limit);

		Page<BonTravailEntity> bonTravailEntityPage = bonTravailRepository.findAll(pageable);
		List<BonTravailEntity> BonTravailEntities = new ArrayList<>();
		if ((mode.length() > 0) && (natureTravaux.length() == 0)) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAllByMode(mode, pageable);
			BonTravailEntities = bonTravailEntity.getContent();
		} else if ((mode.length() == 0) && (natureTravaux.length() > 0)) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findByNatureTravaux(natureTravaux, pageable);
			BonTravailEntities = bonTravailEntity.getContent();
		} else if ((mode.length() > 0) && natureTravaux.length() > 0) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findByModeAndNatureTravaux(mode,
					natureTravaux, pageable);
			BonTravailEntities = bonTravailEntity.getContent();
		} else {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAll(pageable);
			BonTravailEntities = bonTravailEntity.getContent();
		}
		return this.loadBonTravailDataTableDTO(BonTravailEntities);
	}

	@Override
	public List<BonTravailDataTableDTO> getPaginationBonTravailList2(String designation, String status, String ugp,
			int page, int limit) {
		List<BonTravailEntity> bonTravailEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if ((status.length() > 0) && (designation.length() == 0) && (ugp.length() == 0)) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAllByStatus(status, pageable);
			for (Iterator iterator = bonTravailEntity.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bonTravailEntity.getContent();
				}
			}

		} else if ((status.length() == 0) && (ugp.length() == 0) && (designation.length() > 0)) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAllByMagasin(designation, pageable);
			for (Iterator iterator = bonTravailEntity.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bonTravailEntity.getContent();
				}
			}
		} else if ((status.length() == 0) && (ugp.length() > 0) && (designation.length() == 0)) {
			Page<BonTravailEntity> bonTravailEntityPage = bonTravailRepository.findAllByUgp(ugp, pageable);
			for (Iterator iterator = bonTravailEntityPage.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bonTravailEntityPage.getContent();
				}
			}

		}

		else if ((status.length() > 0) && ugp.length() > 0 && (designation.length() == 0)) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAllByUgpAndStatus(ugp, status, pageable);
			for (Iterator iterator = bonTravailEntity.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bonTravailEntity.getContent();
				}
			}
		} else if ((status.length() > 0) && ugp.length() > 0 && (designation.length() > 0)) {
			Page<BonTravailEntity> bontravailEntity = bonTravailRepository.findAllByUgpAndMagasinAndStatus(ugp,
					designation, status, pageable);
			for (Iterator iterator = bontravailEntity.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bontravailEntity.getContent();
				}
			}
		} else if ((status.length() > 0 && ugp.length() == 0 && (designation.length() > 0))) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAllByStatusAndMagasin(status,
					designation, pageable);
			for (Iterator iterator = bonTravailEntity.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bonTravailEntity.getContent();
				}
			}
		} else if ((status.length() == 0) && ugp.length() > 0 && (designation.length() > 0)) {
			Page<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAllByUgpAndMagasin(ugp, designation,
					pageable);
			for (Iterator iterator = bonTravailEntity.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bonTravailEntity.getContent();
				}
			}
		} else if ((designation.length() == 0) && (status.length() == 0) && (ugp.length() == 0)) {
			Page<BonTravailEntity> bonTravailEntityPage = bonTravailRepository.findAll(pageable);
			for (Iterator iterator = bonTravailEntityPage.iterator(); iterator.hasNext();) {
				BonTravailEntity bonTravailEntity1 = (BonTravailEntity) iterator.next();
				if (!bonTravailEntity1.getBonTravailArticle().isEmpty()) {
					bonTravailEntities = bonTravailEntityPage.getContent();
				}
			}
		}
		return this.loadBonTravailDataTableDTO(bonTravailEntities);
	}

	private List<BonTravailDataTableDTO> loadBonTravailDataTableDTO(List<BonTravailEntity> bonTravailEntities) {
		List<BonTravailDataTableDTO> bonTravailDataTableDTOS = new ArrayList<>();
		if (!bonTravailEntities.isEmpty()) {
			bonTravailEntities.forEach(bonTravailEntity -> {
				BonTravailDataTableDTO bonTravailDataTableDTO = new BonTravailDataTableDTO();
				bonTravailDataTableDTO.setId(bonTravailEntity.getId());
				bonTravailDataTableDTO.setDateEntree(bonTravailEntity.getDateEntree());
				bonTravailDataTableDTO.setDateSortiePrevue(bonTravailEntity.getDateSortiePrevue());
				bonTravailDataTableDTO.setIndexKM(bonTravailEntity.getIndexKM());
				bonTravailDataTableDTO.setCloturer(bonTravailEntity.isCloturer());				
				bonTravailDataTableDTO.setDateSortie(bonTravailEntity.getDateSortie());
				bonTravailDataTableDTO.setNatureTravaux(bonTravailEntity.getNatureTravaux());
				bonTravailDataTableDTO.setObservatioMode(bonTravailEntity.getObservatioMode());
				bonTravailDataTableDTO.setObservation(bonTravailEntity.getObservation());
				bonTravailDataTableDTO.setMode(bonTravailEntity.getMode());
				if (bonTravailEntity.getFacturation() != null) {
					bonTravailDataTableDTO.setIdFacturation(bonTravailEntity.getFacturation().getId());
				}
				if (bonTravailEntity.getAtelier() != null) {
					bonTravailDataTableDTO.setIdAtelier(bonTravailEntity.getAtelier().getId());
					bonTravailDataTableDTO.setNomAtelier(bonTravailEntity.getAtelier().getDesignation());
				}
				if (bonTravailEntity.getMagasin() != null) {
					bonTravailDataTableDTO.setIdMagasin(bonTravailEntity.getMagasin().getId());
					bonTravailDataTableDTO.setNomMagasin(bonTravailEntity.getMagasin().getDesignation());
				}
				if (bonTravailEntity.getDemandeMaintenance() != null) {
					bonTravailDataTableDTO.setIdDemandeMaintenance(bonTravailEntity.getDemandeMaintenance().getId());
					bonTravailDataTableDTO.setStructure(bonTravailEntity.getDemandeMaintenance().getStructure());

					bonTravailDataTableDTO.setNumSerieVoiture(
							bonTravailEntity.getDemandeMaintenance().getVehicule().getNumeroSerie());
				}
				bonTravailDataTableDTOS.add(bonTravailDataTableDTO);

			});
		}
		return bonTravailDataTableDTOS;

	}

	@Override
	public List<DemandeMaintenanceDataTableDTO> getPaginationDemandeMaintenanceList(String status, String ugp,
			String structure, int page, int limit) {
		List<DemandeMaintenanceEntity> demandeMaintenanceEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if ((status.length() > 0) && (ugp.length() == 0) && (structure.length() == 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findAllByStatus(status, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
			System.out.println(status);
			System.out.println(ugp);
			System.out.println(structure);
		} else if ((status.length() > 0) && ugp.length() > 0 && (structure.length() == 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findByStatusAndUgp(status, ugp, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((status.length() > 0) && ugp.length() > 0 && (structure.length() > 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findByStatusAndUgpAndStructure(status, ugp, structure, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((status.length() > 0 && ugp.length() == 0 && (structure.length() > 0))) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findByStatusAndStructure(status, structure, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((status.length() == 0) && ugp.length() > 0 && (structure.length() > 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findByUgpAndStructure(ugp, structure, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((status.length() == 0) && ugp.length() == 0 && (structure.length() == 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository
					.findAll(pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntityPage.getContent();
		} else if ((status.length() == 0) && ugp.length() > 0 && (structure.length() == 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository.findByUgp(ugp,
					pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntityPage.getContent();
		} else if ((status.length() == 0) && ugp.length() == 0 && (structure.length() > 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findByUgpOrStatusOrStructure(status, ugp, structure, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository
					.findAll(pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntityPage.getContent();
		}
		return this.loadDemandeMaintenanceDataTableDTO(demandeMaintenanceEntities);
	}

	private List<DemandeMaintenanceDataTableDTO> loadDemandeMaintenanceDataTableDTO(
			List<DemandeMaintenanceEntity> demandeMaintenanceEntities) {
		List<DemandeMaintenanceDataTableDTO> demandeMaintenanceDataTableDTOs = new ArrayList<>();
		if (!demandeMaintenanceEntities.isEmpty()) {
			demandeMaintenanceEntities.forEach(demandeMaintenanceEntity -> {
				DemandeMaintenanceDataTableDTO demandeMaintenanceDataTableDTO = new DemandeMaintenanceDataTableDTO();
				demandeMaintenanceDataTableDTO.setId(demandeMaintenanceEntity.getId());
				demandeMaintenanceDataTableDTO.setNumeroDemande(demandeMaintenanceEntity.getNumeroDemande());
				demandeMaintenanceDataTableDTO.setNumeroSerie(demandeMaintenanceEntity.getNumeroSerie());
				demandeMaintenanceDataTableDTO
						.setMatriculeBeneficiaire(demandeMaintenanceEntity.getMatriculeBeneficiaire());
				demandeMaintenanceDataTableDTO.setStructure(demandeMaintenanceEntity.getStructure());
				demandeMaintenanceDataTableDTO.setPersonnel(demandeMaintenanceEntity.getPersonnel());
				demandeMaintenanceDataTableDTO.setUgp(demandeMaintenanceEntity.getUgp());
				demandeMaintenanceDataTableDTO.setUgpReparation(demandeMaintenanceEntity.getUgpReparation());
				demandeMaintenanceDataTableDTO.setDemandeur(demandeMaintenanceEntity.getDemandeur());
				demandeMaintenanceDataTableDTO.setDateDemande(demandeMaintenanceEntity.getDateDemande());
				demandeMaintenanceDataTableDTO.setIndexKm(demandeMaintenanceEntity.getIndexKm());
				demandeMaintenanceDataTableDTO
						.setDescriptionIntervention(demandeMaintenanceEntity.getDescriptionIntervention());
				demandeMaintenanceDataTableDTO.setStatus(demandeMaintenanceEntity.getStatus());
				demandeMaintenanceDataTableDTO.setAnnulation(demandeMaintenanceEntity.isAnnulation());
				demandeMaintenanceDataTableDTO.setDateRDV(demandeMaintenanceEntity.getDateRDV());
				demandeMaintenanceDataTableDTO.setObservation(demandeMaintenanceEntity.getObservation());
				if (demandeMaintenanceEntity.getVehicule() != null) {
					demandeMaintenanceDataTableDTO.setMarque(demandeMaintenanceEntity.getVehicule().getMarque());
					demandeMaintenanceDataTableDTO.setIdVehicule(demandeMaintenanceEntity.getVehicule().getId());

					System.out.println(demandeMaintenanceEntity.getVehicule().getMarque());

					if (demandeMaintenanceEntity.getVehicule().getBeneficiaire() != null) {
						demandeMaintenanceDataTableDTO
								.setIdBeneficiaire(demandeMaintenanceEntity.getVehicule().getBeneficiaire().getId());
						demandeMaintenanceDataTableDTO
								.setNomBeneficiaire(demandeMaintenanceEntity.getVehicule().getBeneficiaire().getNom());
					}
				}
				demandeMaintenanceDataTableDTOs.add(demandeMaintenanceDataTableDTO);
			});
		}

		return demandeMaintenanceDataTableDTOs;
	}

	@Override
	public Long getTotalItemBonTravailList() {
		PageRequest pageable = PageRequest.of(0, 8);
		return bonTravailRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public void modifySelectedBonTravail(BonTravailDTO bonTravailDTO) {
		BonTravailEntity bonTravailEntity = bonTravailRepository.findById(bonTravailDTO.getId()).get();
		bonTravailEntity.setDateEntree(bonTravailDTO.getDateEntree());
		bonTravailEntity.setDateSortiePrevue(bonTravailDTO.getDateSortiePrevue());
		bonTravailEntity.setNatureTravaux(bonTravailDTO.getNatureTravaux());
		bonTravailEntity.setMode(bonTravailDTO.getMode());
		bonTravailEntity.setCloturer(true);
		bonTravailEntity.setIndexKM(bonTravailDTO.getIndexKM());
		bonTravailEntity.setObservation(bonTravailDTO.getObservation());
		bonTravailRepository.save(bonTravailEntity);
	}

	@Override
	public void deleteSelectedBonTravail(Long id) {
		bonTravailRepository.deleteById(id);
	}

	@Override
	public void addnewoperationtobontravail(UpdateBonTravailDTO updateBonTravailDTO) {
	}

	@Override

	public List<BonTravailDataTableDTO> getPaginationBonTravailList(int page, int limit) {

		PageRequest pageable = PageRequest.of(page, limit);
		List<BonTravailEntity> bonTravailEntity = bonTravailRepository.findAll(pageable).getContent();
		return this.loadBonTravailDataTableDTO(bonTravailEntity);

	}
	
	@Override
	public List<DemandeMaintenanceDataTableDTO> getPaginationHistoriqueDemandeMaintenanceList( String ugp,
			String structure, int page, int limit) {
		List<DemandeMaintenanceEntity> demandeMaintenanceEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if ((ugp.length() > 0) && (structure.length() == 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findHistoriqueByUgp(ugp, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((ugp.length() > 0) && (structure.length() > 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findHistoriqueByUgpAndStructure(ugp, structure, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((ugp.length() == 0) && (structure.length() > 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findHistoriqueByStructure(structure, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((ugp.length() > 0) && (structure.length() > 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntity = demandeMaintenanceRepository
					.findHistoriqueByUgpAndStructure(ugp, structure, pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntity.getContent();
		} else if ((ugp.length() == 0) && (structure.length() == 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository
					.getPaginationHistoriqueDemandeMaintenance(pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntityPage.getContent();
		} else if ((ugp.length() > 0) && (structure.length() == 0)) {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository.findByUgp(ugp,
					pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntityPage.getContent();
		} else {
			Page<DemandeMaintenanceEntity> demandeMaintenanceEntityPage = demandeMaintenanceRepository
					.getPaginationHistoriqueDemandeMaintenance(pageable);
			demandeMaintenanceEntities = demandeMaintenanceEntityPage.getContent();
		}
		return this.loadDemandeMaintenanceDataTableDTO(demandeMaintenanceEntities);
	}

	@Override
	public List<DemandeMaintenanceDataTableDTO> getPaginationHistoriqueDemandeMaintenanceList(String status, String ugp,
			String structure, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void reouvrirBonTravail(long id ) {

		BonTravailEntity bt = bonTravailRepository.findById(id).get();
		bt.setCloturer(false);
		BonTravailEntity bontravail = bonTravailRepository.save(bt);

}
	
	

	@Override
	public int NombreNotifIntervention() {
		int  n = demandeMaintenanceRepository.findNombreNotif();	 
		return n;
	}
	
	@Override
	public int NombreNotifValidIntervention() {
		int  n = demandeMaintenanceRepository.findNombreNotifValidation();	 
		return n;
	}
	

}
