package com.gesparc.servicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteAgilis.DeclarationPerteCarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeDesaffectationCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.carburant.carteJocker.HistoriqueAffectationCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.HistoriqueDesaffectationCarteJockerEntity;
import com.gesparc.repositories.AssuranceRepository;
import com.gesparc.repositories.BeneficiaireRepository;
import com.gesparc.repositories.CarteAgilisCashRepository;
import com.gesparc.repositories.CarteJockerRepository;
import com.gesparc.repositories.CartePlafondRepository;
import com.gesparc.repositories.DeclarationPerteCarteAgilisCashRepository;
import com.gesparc.repositories.DeclarationPerteCarteJockerRepository;
import com.gesparc.repositories.DeclarationPerteCartePlafondRepository;
import com.gesparc.repositories.DemandeAnnulationCarteAgilisCashRepository;
import com.gesparc.repositories.DemandeAnnulationCartePlafondRepository;
import com.gesparc.repositories.DemandeDesaffectationCarteJockerRepository;
import com.gesparc.repositories.DemandeQuotaCarteJockerRepository;
import com.gesparc.repositories.Details2DistributionRepository;
import com.gesparc.repositories.Distribution2FonctionRepository;
import com.gesparc.repositories.Distribution2ServiceRepository;
import com.gesparc.repositories.DocumentRepository;
import com.gesparc.repositories.EtatMensuelRepository;
import com.gesparc.repositories.HistoriqueAffectationCarteJockerRepository;
import com.gesparc.repositories.HistoriqueAffectationCartePlafondRepository;
import com.gesparc.repositories.HistoriqueDesaffectationCarteJockerRepository;
import com.gesparc.repositories.HistoriqueDesaffectationCartePlafondRepository;
import com.gesparc.repositories.RechargeCarteAgilisCashRepository;
import com.gesparc.repositories.ReformeRepository;
import com.gesparc.repositories.TaxeCirculationRepository;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.VisiteTechniqueRepository;
import com.gesparc.responses.carburant.additionnel.CarteJockerTabDataResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.services.CarteJocker;
import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.DeclarationPerteCarteAgilisCashDTO;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.DemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.HistoriqueAffectationCarteJockerDTO;
import com.gesparc.sharedDTO.HistoriqueDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModificationDemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModifyDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.NewCarteAgilisCashDTO;
import com.gesparc.sharedDTO.carburant.NewCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.NouvelleDemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CarteJockerTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCarteJockerTabDataDTO;

@EnableAutoConfiguration
@Service
public class CarteJockerImpl implements CarteJocker {

	Logger logger = LoggerFactory.getLogger(CarteJockerImpl.class);

	@Autowired
	VehiculeRepository vehiculeRepository;

	@Autowired
	AssuranceRepository assuranceRepository;

	@Autowired
	VisiteTechniqueRepository visiteTechniqueRepository;

	@Autowired
	TaxeCirculationRepository vignetteRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	ReformeRepository reformeRepository;

	@Autowired
	BeneficiaireRepository beneficiaireRepository;

	@Autowired
	Distribution2FonctionRepository distribution2FonctionRepository;

	@Autowired
	Details2DistributionRepository details2DistributionRepository;

	@Autowired
	EtatMensuelRepository etatMensuelRepository;

	@Autowired
	Distribution2ServiceRepository distribution2ServiceRepository;

	@Autowired
	CartePlafondRepository cartePlafondRepository;

	@Autowired
	HistoriqueAffectationCartePlafondRepository historiqueAffectationCartePlafondRepository;

	@Autowired
	HistoriqueDesaffectationCartePlafondRepository historiqueDesaffectationCartePlafondRepository;

	@Autowired
	DeclarationPerteCartePlafondRepository declarationPerteCartePlafondRepository;

	@Autowired
	DemandeAnnulationCartePlafondRepository demandeAnnulationCartePlafondRepository;

	@Autowired
	CarteAgilisCashRepository carteAgilisCashRepository;

	@Autowired
	RechargeCarteAgilisCashRepository historiqueRechargeCarteAgilisCashRepository;

	@Autowired
	DeclarationPerteCarteAgilisCashRepository declarationPerteCarteAgilisCashRepository;

	@Autowired
	DeclarationPerteCarteJockerRepository declarationPerteCarteJockerRepository;

	@Autowired
	DemandeAnnulationCarteAgilisCashRepository demandeAnnulationCarteAgilisCashRepository;

	@Autowired
	CarteJockerRepository carteJockerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	HistoriqueAffectationCarteJockerRepository historiqueAffectationCarteJockerRepository;

	@Autowired
	DemandeDesaffectationCarteJockerRepository demandeDesaffectationCarteJockerRepository;

	@Autowired
	HistoriqueDesaffectationCarteJockerRepository historiqueDesaffectationCarteJockerRepository;

	@Autowired
	HistoriqueAffectationCarteJockerRepository getHistoriqueAffectationCarteJockerRepository;

	@Autowired
	DemandeQuotaCarteJockerRepository demandeQuotaCarteJockerRepository;

	@Override
	public Iterable<CarteJockerEntity> getAllCarteJockerTabDataResponses() {
		return carteJockerRepository.findAll();
	}

	@Override
	public List<CarteJockerTabDataDTO> getPaginationCarteJockerList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<CarteJockerEntity> carteJockerEntities = carteJockerRepository.findAll(pageable).getContent();
		return this.loadCarteJockerTabDataDTO(carteJockerEntities);
	}

	@Override
	public Long getTotalNumberCarteJocker() {
		PageRequest pageable = PageRequest.of(0, 8);
		return carteJockerRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public Long getTotalNumberCarteJockerByEtatCarte(String etatCarte) {
		Long totalNumber = null;
		return totalNumber;
	}

	@Override
	public void modifySelectedCarteJocker(NewCarteJockerDTO newCarteJockerDTO) {
		CarteJockerEntity carteJockerEntity = carteJockerRepository.findById(newCarteJockerDTO.getId()).get();
		carteJockerEntity.setSolde(newCarteJockerDTO.getSolde());
		carteJockerEntity.setNumeroCarte(newCarteJockerDTO.getNumeroCarte());
		carteJockerEntity.setDateFinValiditee(newCarteJockerDTO.getDateValiditee());
		carteJockerRepository.save(carteJockerEntity);
	}

	@Override
	public void deleteSelectedCarteJocker(Long id) {
		carteJockerRepository.deleteById(id);
	}

	@Override
	public void addNewCarteJocker(NewCarteJockerDTO newCarteJockerDTO) {
		CarteJockerEntity carteJockerEntity = new CarteJockerEntity();
		carteJockerEntity.setNumeroCarte(newCarteJockerDTO.getNumeroCarte());
		carteJockerEntity.setDateDebutUtilisation(LocalDate.now());
		carteJockerEntity.setDateFinValiditee(newCarteJockerDTO.getDateValiditee());
		carteJockerEntity.setNombreAffectation(0);
		carteJockerEntity.setNombreAffectation(0);
		carteJockerEntity.setSolde(newCarteJockerDTO.getSolde());
		carteJockerEntity.setAffected(false);
		carteJockerRepository.save(carteJockerEntity);
	}

	@Override
	public List<SelectVehiculeDTO> getListVehiculesServiceWithNoCarteJocker() {
		List<VehiculeEntity> vehiculeEntities = vehiculeRepository.findAllByCarteJockerNull();
		return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
	}

	@Override
	public void addNewAffectationCarteJocker(Long idCarte, Long idVehicule) {
		CarteJockerEntity carteJockerEntity = carteJockerRepository.findById(idCarte).get();
		VehiculeEntity vehiculeEntity = vehiculeRepository.findById(idVehicule).get();
		carteJockerEntity.setVehicule(vehiculeEntity);
		carteJockerEntity.setNombreAffectation(carteJockerEntity.getNombreAffectation() + 1);
		vehiculeEntity.setCarteJocker(carteJockerEntity);
		vehiculeRepository.save(vehiculeEntity);
		carteJockerRepository.save(carteJockerEntity);
		HistoriqueAffectationCarteJockerEntity historiqueAffectationCarteJockerEntity = new HistoriqueAffectationCarteJockerEntity();
		historiqueAffectationCarteJockerEntity.setIdVehicule(carteJockerEntity.getVehicule().getId());
		historiqueAffectationCarteJockerEntity.setNumeroPlaque(carteJockerEntity.getVehicule().getNumeroPlaque());
		historiqueAffectationCarteJockerEntity
				.setCodeStructure(carteJockerEntity.getVehicule().getStructure().getCode());
		historiqueAffectationCarteJockerEntity
				.setDesignationStructure(carteJockerEntity.getVehicule().getStructure().getDesignation());
		historiqueAffectationCarteJockerEntity
				.setTypeCarburant(carteJockerEntity.getVehicule().getEnergie().getDescription());
		historiqueAffectationCarteJockerEntity.setConfirmed(false);
		historiqueAffectationCarteJockerEntity.setValidated(false);
		if (carteJockerEntity.getVehicule().getBeneficiaire() != null) {
			historiqueAffectationCarteJockerEntity
					.setIdBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getId());
			historiqueAffectationCarteJockerEntity
					.setNomBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getNom());
			historiqueAffectationCarteJockerEntity
					.setMatriculeBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getMatricule());
		}
		historiqueAffectationCarteJockerEntity.setCarteJocker(carteJockerEntity);
		if (carteJockerEntity.getHistoriqueAffectationCarteJocker().isEmpty()) {
			carteJockerEntity.setHistoriqueAffectationCarteJocker(new ArrayList<>());
		}
		carteJockerEntity.getHistoriqueAffectationCarteJocker().add(historiqueAffectationCarteJockerEntity);
		historiqueAffectationCarteJockerRepository.save(historiqueAffectationCarteJockerEntity);
	}

	@Override
	public Long getTotalDemandesAffectationCarteJockerByFilteredDate() {
		PageRequest pageable = PageRequest.of(0, 8);
		return historiqueAffectationCarteJockerRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public List<HistoriqueAffectationCarteJockerTabDataDTO> getPaginationDemandesAffectationCarteJocker(int page,
			int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		Page<HistoriqueAffectationCarteJockerEntity> historiqueAffectationCarteJockerEntities = historiqueAffectationCarteJockerRepository
				.findAll(pageable);
		return this
				.loadHistoriqueAffectationCarteJockerTabDataDTO(historiqueAffectationCarteJockerEntities.getContent());
	}

	@Override
	public void modifySelectedDemandeAffectationCarteJocker(Long idCarte, Long idVehicule,
			LocalDate dateDerniereDemande) {
		CarteJockerEntity carteJockerEntity = carteJockerRepository.findById(idCarte).get();
		VehiculeEntity vehiculeEntity = vehiculeRepository.findById(idVehicule).get();
		carteJockerEntity.setVehicule(vehiculeEntity);
		vehiculeEntity.setCarteJocker(carteJockerEntity);
		carteJockerRepository.save(carteJockerEntity);
	}

	@Override
	public void deleteSelectedDemandeAffectationCarteJocker(Long idCarte, Long idHistorique) {
		CarteJockerEntity carteJockerEntity = carteJockerRepository.findById(idCarte).get();
		carteJockerEntity.setVehicule(null);
		HistoriqueAffectationCarteJockerEntity historiqueAffectationCarteJockerEntity = historiqueAffectationCarteJockerRepository
				.findById(idHistorique).get();
		historiqueAffectationCarteJockerEntity.setCarteJocker(null);
		carteJockerEntity.setHistoriqueAffectationCarteJocker(null);
		carteJockerRepository.save(carteJockerEntity);
		historiqueAffectationCarteJockerRepository.deleteById(idHistorique);
	}

	@Override
	public List<HistoriqueAffectationCarteJockerDTO> getPaginationHistoriqueAffectationCarteJockerByFilteredDate(
			int page, int limit, LocalDate dateMin, LocalDate dateMax) {
		List<HistoriqueAffectationCarteJockerDTO> historiqueAffectationCarteJockerDTOS = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		LocalDate dateReference = LocalDate.of(1970, 1, 1);
		if (dateMax.equals(dateReference)) {
			dateMax = historiqueAffectationCarteJockerRepository.getMaxDateHistoriqueAffectationCarteJocker();
		}
		if (dateMin.equals(dateReference)) {
			dateMin = historiqueAffectationCarteJockerRepository.getMinDateHistoriqueAffectationCarteJocker();
		}
		PageRequest pageable = PageRequest.of(page, limit);
		Page<HistoriqueAffectationCarteJockerEntity> historiqueAffectationCarteJockerEntityPage = historiqueAffectationCarteJockerRepository
				.getPaginationHistoriqueAffectationCarteJockerByFilteredDate(dateMin, dateMax, pageable);
		List<HistoriqueAffectationCarteJockerEntity> historiqueAffectationCarteJockerEntities = historiqueAffectationCarteJockerEntityPage
				.getContent();
		if (!historiqueAffectationCarteJockerEntities.isEmpty()) {
			historiqueAffectationCarteJockerEntities.forEach(historiqueAffectationCarteJockerEntity -> {
				historiqueAffectationCarteJockerDTOS.add(modelMapper.map(historiqueAffectationCarteJockerEntity,
						HistoriqueAffectationCarteJockerDTO.class));
			});
		}
		return historiqueAffectationCarteJockerDTOS;
	}

	@Override
	public Long getTotalNumberHistoriqueAffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax) {
		LocalDate dateReference = LocalDate.of(1970, 1, 1);
		if (dateMax.equals(dateReference)) {
			dateMax = historiqueAffectationCarteJockerRepository.getMaxDateHistoriqueAffectationCarteJocker();
		}
		if (dateMin.equals(dateReference)) {
			dateMin = historiqueAffectationCarteJockerRepository.getMinDateHistoriqueAffectationCarteJocker();
		}
		return historiqueAffectationCarteJockerRepository
				.getTotalHistoriqueAffectationCarteJockerByFilteredDate(dateMin, dateMax);
	}

	@Override
	public void deleteSelectedHistoriqueAffectationCarteJocker(Long idCarte) {
		historiqueAffectationCarteJockerRepository.deleteById(idCarte);
	}

	@Override
	public List<CarteJockerDTO> getListAffectedVehiculesAndCartesJocker() {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteJockerEntity> carteJockerEntities = new ArrayList<>();
		List<CarteJockerDTO> carteJockerDTOS = new ArrayList<>();
		if (!carteJockerEntities.isEmpty()) {
			carteJockerEntities.forEach(carteJockerEntity -> {
				carteJockerDTOS.add(modelMapper.map(carteJockerEntity, CarteJockerDTO.class));
			});
		}
		return carteJockerDTOS;
	}

	@Override
	public void createNouvelleDemandeDesaffectationCarteJockerRequest(
			HistoriqueDesaffectationCarteJockerDTO nouvelleDemandeDTO) {
		CarteJockerEntity carteJockerEntity = carteJockerRepository.findById(nouvelleDemandeDTO.getIdCarte()).get();
		HistoriqueDesaffectationCarteJockerEntity demandeDesaffectationCarteJockerEntity = new HistoriqueDesaffectationCarteJockerEntity();
		demandeDesaffectationCarteJockerEntity.setIdCarte(carteJockerEntity.getId());
		demandeDesaffectationCarteJockerEntity.setIdVehicule(carteJockerEntity.getVehicule().getId());
		demandeDesaffectationCarteJockerEntity
				.setIdBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getId());
		demandeDesaffectationCarteJockerEntity.setNumeroCarte(carteJockerEntity.getNumeroCarte());
		demandeDesaffectationCarteJockerEntity
				.setDateDemandeAffectation(carteJockerEntity.getDateDerniereAffectation());
		demandeDesaffectationCarteJockerEntity.setSoldeAffectation(carteJockerEntity.getSolde());
		demandeDesaffectationCarteJockerEntity.setSoldeDesaffectation(nouvelleDemandeDTO.getSoldeDesaffectation());
		demandeDesaffectationCarteJockerEntity.setNombreAffectation(carteJockerEntity.getNombreAffectation());
		demandeDesaffectationCarteJockerEntity.setNumeroPlaque(carteJockerEntity.getVehicule().getNumeroPlaque());
		demandeDesaffectationCarteJockerEntity
				.setTypeCarburant(carteJockerEntity.getVehicule().getEnergie().getDescription());
		demandeDesaffectationCarteJockerEntity
				.setNomBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getNom());
		demandeDesaffectationCarteJockerEntity
				.setMatriculeBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getMatricule());
		demandeDesaffectationCarteJockerEntity.setNote(nouvelleDemandeDTO.getNote());
		carteJockerRepository.save(carteJockerEntity);
		historiqueDesaffectationCarteJockerRepository.save(demandeDesaffectationCarteJockerEntity);
	}

	@Override
	public List<DemandeDesaffectationCarteJockerDTO> getPaginationDemandesDesaffectationCarteJockerByFilteredDate(
			int page, int limit, LocalDate dateMin, LocalDate dateMax) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeDesaffectationCarteJockerDTO> demandeDesaffectationCarteJockerDTOS = new ArrayList<>();
		LocalDate dateReference = LocalDate.of(1970, 1, 1);
		if (dateMax.equals(dateReference)) {
			dateMax = demandeDesaffectationCarteJockerRepository.getMaxDateDemandeDesaffectationCarteJocker();
		}
		if (dateMin.equals(dateReference)) {
			dateMin = demandeDesaffectationCarteJockerRepository.getMinDateDemandeDesaffectationCarteJocker();
		}
		PageRequest pageable = PageRequest.of(page, limit);
		Page<DemandeDesaffectationCarteJockerEntity> demandeDesaffectationCarteJockerEntitiesPage = demandeDesaffectationCarteJockerRepository
				.findAllByDateDemandeDesaffectationBetween(dateMin, dateMax, pageable);
		List<DemandeDesaffectationCarteJockerEntity> demandeDesaffectationCarteJockerEntities = demandeDesaffectationCarteJockerEntitiesPage
				.getContent();
		if (!demandeDesaffectationCarteJockerEntities.isEmpty()) {
			demandeDesaffectationCarteJockerEntities.forEach(demandeDesaffectationCarteJockerEntity -> {
				demandeDesaffectationCarteJockerDTOS.add(modelMapper.map(demandeDesaffectationCarteJockerEntity,
						DemandeDesaffectationCarteJockerDTO.class));
			});
		}
		return demandeDesaffectationCarteJockerDTOS;
	}

	@Override
	public Long getTotalNumberDemandesDesaffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax) {
		LocalDate dateReference = LocalDate.of(1970, 1, 1);
		if (dateMax.equals(dateReference)) {
			dateMax = demandeDesaffectationCarteJockerRepository.getMaxDateDemandeDesaffectationCarteJocker();
		}
		if (dateMin.equals(dateReference)) {
			dateMin = demandeDesaffectationCarteJockerRepository.getMinDateDemandeDesaffectationCarteJocker();
		}
		return demandeDesaffectationCarteJockerRepository
				.getTotalNumberDemandesDesaffectationCarteJockerByFilteredDate(dateMin, dateMax);
	}
	
	@Override
	public void deleteSelectedDemandeDesaffectationCarteJocker(Long id) {
		demandeDesaffectationCarteJockerRepository.deleteById(id);
	}

	@Override
	public void confirmSelectedDemandeDesaffectationCarteJocker(Long id) {
		HistoriqueDesaffectationCarteJockerEntity historiqueDesaffectationCarteJockerEntity = historiqueDesaffectationCarteJockerRepository
				.findById(id).get();
		CarteJockerEntity carteJockerEntity = carteJockerRepository
				.findById(historiqueDesaffectationCarteJockerEntity.getIdCarte()).get();     
		carteJockerEntity.setVehicule(null);
		carteJockerRepository.save(carteJockerEntity);
		historiqueDesaffectationCarteJockerRepository.save(historiqueDesaffectationCarteJockerEntity);
	}

	@Override
	public void modificationDemandeDesaffectationCarteJockerDTO(
			HistoriqueDesaffectationCarteJockerDTO modificationDemande) {
		HistoriqueDesaffectationCarteJockerEntity demandeDesaffectationCarteJockerEntity = historiqueDesaffectationCarteJockerRepository
				.findById(modificationDemande.getId()).get();
		demandeDesaffectationCarteJockerEntity.setSoldeDesaffectation(modificationDemande.getSoldeDesaffectation());
		demandeDesaffectationCarteJockerEntity
				.setDateDemandeDesaffectation(modificationDemande.getDateDemandeDesaffectation());
		demandeDesaffectationCarteJockerEntity.setNote(modificationDemande.getNote());
		historiqueDesaffectationCarteJockerRepository.save(demandeDesaffectationCarteJockerEntity);
	}

	@Override
	public List<HistoriqueDesaffectationCarteJockerDTO> getPaginationHistoriqueDesaffectationCarteJockerByFilteredDate(
			int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<HistoriqueDesaffectationCarteJockerDTO> historiqueDesaffectationCarteJockerDTOS = new ArrayList<>();
		LocalDate dateReference = LocalDate.of(1970, 1, 1);

		PageRequest pageable = PageRequest.of(page, limit);
		Page<HistoriqueDesaffectationCarteJockerEntity> historiqueDesaffectationCarteJockerEntitiesPages = historiqueDesaffectationCarteJockerRepository
				.findAll(pageable);
		List<HistoriqueDesaffectationCarteJockerEntity> historiqueDesaffectationCarteJockerEntities = historiqueDesaffectationCarteJockerEntitiesPages
				.getContent();
		if (!historiqueDesaffectationCarteJockerEntities.isEmpty()) {
			historiqueDesaffectationCarteJockerEntities.forEach(historiqueDesaffectationCarteJockerEntity -> {
				historiqueDesaffectationCarteJockerDTOS.add(modelMapper.map(historiqueDesaffectationCarteJockerEntity,
						HistoriqueDesaffectationCarteJockerDTO.class));
			});
		}
		return historiqueDesaffectationCarteJockerDTOS;
	}

	@Override
	public Long getTotalNumberHistoriqueDesaffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax) {
		LocalDate dateReference = LocalDate.of(1970, 1, 1);
		if (dateMax.equals(dateReference)) {
			dateMax = historiqueDesaffectationCarteJockerRepository.getMaxDateHistoriqueDesaffectationCarteJocker();
		}
		if (dateMin.equals(dateReference)) {
			dateMin = historiqueDesaffectationCarteJockerRepository.getMinDateHistoriqueDesaffectationCarteJocker();
		}
		return historiqueDesaffectationCarteJockerRepository
				.getTotalNumberHistoriqueDesaffectationCarteJockerByFilteredDate(dateMin, dateMax);
	}

	@Override
	public void deleteSelectedHistoriqueDesaffectationCarteJocker(Long id) {
		historiqueDesaffectationCarteJockerRepository.deleteById(id);
	}

	@Override
	public List<CarteJockerDTO> getListCarteJockerAvailableForAffectation() {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteJockerDTO> carteJockerDTOS = new ArrayList<>();
		List<CarteJockerEntity> carteJockerEntities = new ArrayList<>();
		if (!carteJockerEntities.isEmpty()) {
			carteJockerEntities.forEach(carteJockerEntity -> {
				carteJockerDTOS.add(modelMapper.map(carteJockerEntity, CarteJockerDTO.class));
			});
		}
		return carteJockerDTOS;
	}

	private List<CarteJockerTabDataDTO> loadCarteJockerTabDataDTO(List<CarteJockerEntity> carteJockerEntities) {
		List<CarteJockerTabDataDTO> carteJockerTabDataDTOS = new ArrayList<>();
		if (!carteJockerEntities.isEmpty()) {
			carteJockerEntities.forEach(carteJockerEntity -> {
				CarteJockerTabDataDTO carteJockerTabDataDTO = new CarteJockerTabDataDTO();
				carteJockerTabDataDTO.setId(carteJockerEntity.getId());
				carteJockerTabDataDTO.setNumeroCarte(carteJockerEntity.getNumeroCarte());
				if (carteJockerEntity.getDateDerniereAffectation() != null) {
					carteJockerTabDataDTO.setDateDerniereAffectation(carteJockerEntity.getDateDerniereAffectation());
				}
				if (carteJockerEntity.getDateDerniereDesaffectation() != null) {
					carteJockerTabDataDTO
							.setDateDerniereDesaffectation(carteJockerEntity.getDateDerniereDesaffectation());
				}
				carteJockerTabDataDTO.setDateDebutUtilisation(carteJockerEntity.getDateDebutUtilisation());
				carteJockerTabDataDTO.setNombreAffectation(carteJockerEntity.getNombreAffectation());
				carteJockerTabDataDTO.setSolde(carteJockerEntity.getSolde());
				carteJockerTabDataDTO.setAffected(carteJockerEntity.isAffected());
				carteJockerTabDataDTO.setDateFinValidite(carteJockerEntity.getDateFinValiditee());
				if (carteJockerEntity.getVehicule() != null) {
					carteJockerTabDataDTO.setNumeroPlaque(carteJockerEntity.getVehicule().getNumeroPlaque());
					carteJockerTabDataDTO.setCodeStructure(carteJockerEntity.getVehicule().getStructure().getCode());
					carteJockerTabDataDTO
							.setDesignationStructure(carteJockerEntity.getVehicule().getStructure().getDesignation());
					if (carteJockerEntity.getVehicule().getBeneficiaire() != null) {
						carteJockerTabDataDTO
								.setNomBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getNom());
						carteJockerTabDataDTO.setMatriculeBeneficiaire(
								carteJockerEntity.getVehicule().getBeneficiaire().getMatricule());
					}
				}
				carteJockerTabDataDTOS.add(carteJockerTabDataDTO);
			});
		}
		return carteJockerTabDataDTOS;
	}

	@Override
	public List<CarteJockerTabDataDTO> getListNotAffectedCartesJocker() {
		List<CarteJockerEntity> carteJockerEntities = carteJockerRepository.findAllByAffected(false);
		return this.loadCarteJockerTabDataDTO(carteJockerEntities);
	}

	@Override
	public List<CarteJockerTabDataDTO> getListAffectedCartesJocker() {
		List<CarteJockerEntity> carteJockerEntities = carteJockerRepository.listCartePretADesaffecter();
		return this.loadCarteJockerTabDataDTO(carteJockerEntities);
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

	private List<HistoriqueAffectationCarteJockerTabDataDTO> loadHistoriqueAffectationCarteJockerTabDataDTO(
			List<HistoriqueAffectationCarteJockerEntity> historiqueAffectationCarteJockerEntities) {
		List<HistoriqueAffectationCarteJockerTabDataDTO> historiqueAffectationCarteJockerTabDataDTOS = new ArrayList<>();
		if (!historiqueAffectationCarteJockerEntities.isEmpty()) {
			historiqueAffectationCarteJockerEntities.forEach(historiqueAffectationCarteJockerEntity -> {
				HistoriqueAffectationCarteJockerTabDataDTO historiqueAffectationCarteJockerTabDataDTO = new HistoriqueAffectationCarteJockerTabDataDTO();
				historiqueAffectationCarteJockerTabDataDTO.setId(historiqueAffectationCarteJockerEntity.getId());
				historiqueAffectationCarteJockerTabDataDTO
						.setIdCarte(historiqueAffectationCarteJockerEntity.getCarteJocker().getId());
				historiqueAffectationCarteJockerTabDataDTO
						.setIdVehicule(historiqueAffectationCarteJockerEntity.getIdVehicule());
				if (historiqueAffectationCarteJockerEntity.getIdBeneficiaire() != null) {
					historiqueAffectationCarteJockerTabDataDTO
							.setIdBeneficiaire(historiqueAffectationCarteJockerEntity.getIdBeneficiaire());
				}
				historiqueAffectationCarteJockerTabDataDTO
						.setNumeroPlaque(historiqueAffectationCarteJockerEntity.getNumeroPlaque());
				historiqueAffectationCarteJockerTabDataDTO
						.setDateAffectation(historiqueAffectationCarteJockerEntity.getDateAffectation());
				historiqueAffectationCarteJockerTabDataDTO
						.setCodeStructure(historiqueAffectationCarteJockerEntity.getCodeStructure());
				historiqueAffectationCarteJockerTabDataDTO
						.setDesignationStructure(historiqueAffectationCarteJockerEntity.getDesignationStructure());
				historiqueAffectationCarteJockerTabDataDTO
						.setTypeCarburant(historiqueAffectationCarteJockerEntity.getTypeCarburant());
				historiqueAffectationCarteJockerTabDataDTO
						.setNomBeneficiaire(historiqueAffectationCarteJockerEntity.getNomBeneficiaire());
				historiqueAffectationCarteJockerTabDataDTO
						.setMatriculeBeneficiaire(historiqueAffectationCarteJockerEntity.getMatriculeBeneficiaire());
				historiqueAffectationCarteJockerTabDataDTO
						.setNumeroCarte(historiqueAffectationCarteJockerEntity.getCarteJocker().getNumeroCarte());
				historiqueAffectationCarteJockerTabDataDTO.setDateDebutUtilisation(
						historiqueAffectationCarteJockerEntity.getCarteJocker().getDateDebutUtilisation());
				if (historiqueAffectationCarteJockerEntity.getCarteJocker().getDateDerniereDesaffectation() != null) {
					historiqueAffectationCarteJockerTabDataDTO.setDateDerniereDesaffectation(
							historiqueAffectationCarteJockerEntity.getCarteJocker().getDateDerniereDesaffectation());
				}
				historiqueAffectationCarteJockerTabDataDTO.setDateDerniereAffectation(
						historiqueAffectationCarteJockerEntity.getCarteJocker().getDateDerniereAffectation());
				historiqueAffectationCarteJockerTabDataDTO.setNombreAffectation(
						historiqueAffectationCarteJockerEntity.getCarteJocker().getNombreAffectation());
				historiqueAffectationCarteJockerTabDataDTO
						.setSolde(historiqueAffectationCarteJockerEntity.getCarteJocker().getSolde());
				historiqueAffectationCarteJockerTabDataDTO.setDateFinValidite(
						historiqueAffectationCarteJockerEntity.getCarteJocker().getDateFinValiditee());
				historiqueAffectationCarteJockerTabDataDTO
						.setConfirmed(historiqueAffectationCarteJockerEntity.isConfirmed());
				historiqueAffectationCarteJockerTabDataDTO
						.setValidated(historiqueAffectationCarteJockerEntity.isValidated());
				historiqueAffectationCarteJockerTabDataDTOS.add(historiqueAffectationCarteJockerTabDataDTO);
			});
		}
		return historiqueAffectationCarteJockerTabDataDTOS;
	}

	@Override
	public void confirmSelectedDemandeAffectationCarteJocker(Long id) {
		HistoriqueAffectationCarteJockerEntity historiqueAffectationCarteJockerEntity = historiqueAffectationCarteJockerRepository
				.findById(id).get();
		historiqueAffectationCarteJockerEntity.setConfirmed(true);
		historiqueAffectationCarteJockerRepository.save(historiqueAffectationCarteJockerEntity);
	}

	@Override
	public void validateSelectedDemandeAffectationCarteJocker(Long id) {
		HistoriqueAffectationCarteJockerEntity historiqueAffectationCarteJockerEntity = historiqueAffectationCarteJockerRepository
				.findById(id).get();
		historiqueAffectationCarteJockerEntity.setValidated(true);
		historiqueAffectationCarteJockerRepository.save(historiqueAffectationCarteJockerEntity);
	}

	@Override
	public List<DeclarationPerteCarteJockerDTO> getListDeclarationPerteCarteJockerByConfirmation(boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteJockerDTO> declarationPerteCarteJockerDTOS = new ArrayList<>();
		List<DeclarationPerteCarteJockerEntity> declarationPerteCarteJockerEntities = declarationPerteCarteJockerRepository
				.getListDeclarationPerteCarteJockerByConfirmation(false);
		if (!declarationPerteCarteJockerEntities.isEmpty()) {
			declarationPerteCarteJockerEntities.forEach(declarationPerteCarteJockerEntity -> {
				declarationPerteCarteJockerDTOS
						.add(modelMapper.map(declarationPerteCarteJockerEntity, DeclarationPerteCarteJockerDTO.class));
			});
		}
		return declarationPerteCarteJockerDTOS;
	}

	@Override
	public void createNewDeclarationPerteCarteJocker(DeclarationPerteCarteJockerDTO declarationPerteCarteJockerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		CarteJockerEntity cartejockerEntity = carteJockerRepository
				.findById(declarationPerteCarteJockerDTO.getIdCartejocker()).get();
		UserEntity userEntity = userRepository.findById(declarationPerteCarteJockerDTO.getIdUser()).get();
		DeclarationPerteCarteJockerEntity declarationPerteCarteJockerEntityy = modelMapper
				.map(declarationPerteCarteJockerDTO, DeclarationPerteCarteJockerEntity.class);
		declarationPerteCarteJockerEntityy.setCartejocker(cartejockerEntity);
		declarationPerteCarteJockerEntityy.setUser(userEntity);
		declarationPerteCarteJockerRepository.save(declarationPerteCarteJockerEntityy);
	}

	@Override
	public void modifySelectedDeclarationPerteCarteJocker(
			DeclarationPerteCarteJockerDTO declarationPerteCarteJockeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCarteJockerEntity declarationPerteCarteJockerEntity = modelMapper
				.map(declarationPerteCarteJockeDTO, DeclarationPerteCarteJockerEntity.class);
		declarationPerteCarteJockerRepository.save(declarationPerteCarteJockerEntity);
	}

	private List<ListDemandeQuotaCarteJockerDTO> loadDemandeQuotaCarteJockerTabDataDTO(
			List<DemandeQuotaCarteJocker> demandeQuotaCarteJockers) {
		List<ListDemandeQuotaCarteJockerDTO> demandeQuotaCarteJockerTabDataDTOS = new ArrayList<>();
		if (!demandeQuotaCarteJockers.isEmpty()) {
			demandeQuotaCarteJockers.forEach(demandeQuotaCarteJockerEntity -> {
				ListDemandeQuotaCarteJockerDTO listDemandeQuotaCarteJockerDTO = new ListDemandeQuotaCarteJockerDTO();
				listDemandeQuotaCarteJockerDTO.setId(demandeQuotaCarteJockerEntity.getId());
				listDemandeQuotaCarteJockerDTO.setValidated(demandeQuotaCarteJockerEntity.isValidated());
				listDemandeQuotaCarteJockerDTO.setConfirmed(demandeQuotaCarteJockerEntity.isConfirmed());
				listDemandeQuotaCarteJockerDTO.setTypeCarburant(demandeQuotaCarteJockerEntity.getTypeCarburant());
				listDemandeQuotaCarteJockerDTO.setQuantiteDemande(demandeQuotaCarteJockerEntity.getQuantiteDemande());
				listDemandeQuotaCarteJockerDTO.setQuantiteValide(demandeQuotaCarteJockerEntity.getQuantiteValide());
				listDemandeQuotaCarteJockerDTO.setDemandeur(demandeQuotaCarteJockerEntity.getDemandeur());
				listDemandeQuotaCarteJockerDTO.setNumCarte(demandeQuotaCarteJockerEntity.getNumCarte());
				listDemandeQuotaCarteJockerDTO.setCauseDeBlocage(demandeQuotaCarteJockerEntity.getCauseDeBlocage());
				demandeQuotaCarteJockerTabDataDTOS.add(listDemandeQuotaCarteJockerDTO);
			});
		}

		return demandeQuotaCarteJockerTabDataDTOS;
	}

	@Override
	public List<ListDemandeQuotaCarteJockerDTO> getPaginationDemandeQuotaCarteJockerList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<DemandeQuotaCarteJocker> DemandeQuotaCarteJockers = demandeQuotaCarteJockerRepository.findAll(pageable)
				.getContent();
		return this.loadDemandeQuotaCarteJockerTabDataDTO(DemandeQuotaCarteJockers);
	}

	@Override
	public DemandeQuotaCarteJocker addNewDemandeQuotaCarteJocker(DemandeQuotaCarteJocker demandeQuotaCarteJocker) {
		return demandeQuotaCarteJockerRepository.save(demandeQuotaCarteJocker);

	}

	@Override
	public void modifySelectedDemandeQuotaCarteJocker(ModifyDemandeQuotaCarteJockerDTO demandeQuotaCarteJocker) {
		DemandeQuotaCarteJocker demandeQuotaCarteJockerEntity = demandeQuotaCarteJockerRepository
				.findById(demandeQuotaCarteJocker.getId()).get();
		demandeQuotaCarteJockerEntity.setDemandeur(demandeQuotaCarteJocker.getDemandeur());
		demandeQuotaCarteJockerEntity.setCauseDeBlocage(demandeQuotaCarteJocker.getCauseDeBlocage());
		demandeQuotaCarteJockerEntity.setQuantiteValide(demandeQuotaCarteJocker.getQuantiteValide());
		demandeQuotaCarteJockerRepository.save(demandeQuotaCarteJockerEntity);
	}

	@Override
	public void deleteSelectedDemandeQuotaCarteJocker(Long id) {
		demandeQuotaCarteJockerRepository.deleteById(id);

	}

	@Override
	public DemandeQuotaCarteJocker getDemandeQuotaCarteJocker(Long id) {
		return demandeQuotaCarteJockerRepository.findById(id).get();
	}

	@Override
	public Iterable<DemandeQuotaCarteJocker> getAllDemandeQuotaCarteJockers() {

		return demandeQuotaCarteJockerRepository.findAll();
	}

	public long getTotalNumberDemandeQuotaCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax) {
		LocalDate dateReference = LocalDate.of(1970, 1, 1);
		if (dateMax.equals(dateReference)) {
			dateMax = demandeQuotaCarteJockerRepository.getMaxDateDemandeQuotaCarteJocker();
		}
		if (dateMin.equals(dateReference)) {
			dateMin = demandeQuotaCarteJockerRepository.getMinDateDemandeQuotaCarteJocker();
		}
		return demandeQuotaCarteJockerRepository.getTotalNumberDemandeQuotaCarteJockerByFilteredDate(dateMin, dateMax);
	}

	@Override
	public void confirmSelectedDemandeQuotaCarteJocker(Long id) {
		DemandeQuotaCarteJocker demandeQuotaCarteJocker = demandeQuotaCarteJockerRepository.findById(id).get();
		demandeQuotaCarteJocker.setConfirmed(true);
		demandeQuotaCarteJockerRepository.save(demandeQuotaCarteJocker);
	}

	@Override
	public void validateSelectedDemandeQuotaCarteJocker(Long id) {
		DemandeQuotaCarteJocker demandeQuotaCarteJocker = demandeQuotaCarteJockerRepository.findById(id).get();
		demandeQuotaCarteJocker.setValidated(true);
		CarteJockerEntity carteJocker = carteJockerRepository.findByNumeroCarte(demandeQuotaCarteJocker.getNumCarte());
		System.out.print(carteJocker.getSolde());

		carteJocker.setSolde((carteJocker.getSolde()) - (demandeQuotaCarteJocker.getQuantiteDemande()));

		System.out.print(carteJocker.getSolde());
		demandeQuotaCarteJockerRepository.save(demandeQuotaCarteJocker);
	}

	public Long getTotalNumberDemandeQuotaCarteJocker() {
		PageRequest pageable = PageRequest.of(0, 8);
		return demandeQuotaCarteJockerRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public List<DeclarationPerteCarteJockerDTO> getPaginationdeclarationperteCarteJocker(int page, int limit,
			String confirmed) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<DeclarationPerteCarteJockerEntity> declarationPerteCarteJockerEntity = new ArrayList<>();
		if (confirmed.equals("true")) {
			Page<DeclarationPerteCarteJockerEntity> DeclarationPerteCarteJockerEntities = declarationPerteCarteJockerRepository
					.findByConfirmed(true, pageable);
			declarationPerteCarteJockerEntity = DeclarationPerteCarteJockerEntities.getContent();

		} else if (confirmed.equals("false")) {
			Page<DeclarationPerteCarteJockerEntity> DeclarationPerteCarteJockerEntities = declarationPerteCarteJockerRepository
					.findByConfirmed(false, pageable);
			declarationPerteCarteJockerEntity = DeclarationPerteCarteJockerEntities.getContent();
		} else {
			Page<DeclarationPerteCarteJockerEntity> DeclarationPerteCarteJockerEntities = declarationPerteCarteJockerRepository
					.findAll(pageable);
			declarationPerteCarteJockerEntity = DeclarationPerteCarteJockerEntities.getContent();
		}
		return this.loadPerteCarteJocker(declarationPerteCarteJockerEntity);
	}

	private List<DeclarationPerteCarteJockerDTO> loadPerteCarteJocker(
			List<DeclarationPerteCarteJockerEntity> declarationPerteCarteJockerEntities) {
		List<DeclarationPerteCarteJockerDTO> declarationPerteCarteJockerDTO = new ArrayList<>();
		if (!declarationPerteCarteJockerEntities.isEmpty()) {
			declarationPerteCarteJockerEntities.forEach(declarationPerteCarteJockerEntitiy -> {
				DeclarationPerteCarteJockerDTO declarationPerteCarteJockerDTOS = new DeclarationPerteCarteJockerDTO();
				declarationPerteCarteJockerDTOS.setId(declarationPerteCarteJockerEntitiy.getId());
				declarationPerteCarteJockerDTOS.setDatePerte(declarationPerteCarteJockerEntitiy.getDatePerte());
				declarationPerteCarteJockerDTOS
						.setDateNaissanceDeclarant(declarationPerteCarteJockerEntitiy.getDateNaissanceDeclarant());
				declarationPerteCarteJockerDTOS
						.setLieuNaissanceDeclarant(declarationPerteCarteJockerEntitiy.getLieuNaissanceDeclarant());
				declarationPerteCarteJockerDTOS.setCirconstances(declarationPerteCarteJockerEntitiy.getCirconstances());
				declarationPerteCarteJockerDTOS.setNomDeclarant(declarationPerteCarteJockerEntitiy.getNomDeclarant());
				declarationPerteCarteJockerDTOS
						.setNumeroCINDeclarant(declarationPerteCarteJockerEntitiy.getNumeroCINDeclarant());
				declarationPerteCarteJockerDTOS
						.setPrenomDeclarant(declarationPerteCarteJockerEntitiy.getPrenomDeclarant());
				declarationPerteCarteJockerDTOS.setSexeDeclarant(declarationPerteCarteJockerEntitiy.getSexeDeclarant());
				declarationPerteCarteJockerDTOS.setTypeDeclarant(declarationPerteCarteJockerEntitiy.getTypeDeclarant());
				declarationPerteCarteJockerDTO.add(declarationPerteCarteJockerDTOS);

			});
		}

		return declarationPerteCarteJockerDTO;
	}

	@Override
	public Long getTotalNumberdeclarationPerteCarteJocker() {
		PageRequest pageable = PageRequest.of(0, 8);
		return declarationPerteCarteJockerRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public List<DeclarationPerteCarteJockerDTO> getPaginationdeclarationperteCarteJocker(int page, int limit) {
		return null;
	}

}
