package com.gesparc.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteAgilis.*;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.cartePlafond.DemandeAnnulationCartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.repositories.*;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.services.CarteAgilisCash;
import com.gesparc.sharedDTO.*;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.NewCarteAgilisCashDTO;
import com.gesparc.sharedDTO.carburant.NewRechargeCarteAgilisCashDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CarteAgilisCashTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.DemandeAnnulationCarteAgilisCashDataTableDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.ListCarteAgilisCashDTO;
import com.gesparc.sharedDTO.carburant.additionnel.RechargeCarteAgilisCashTabDataDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration
@Service
public class CarteAgilisCashImpl implements CarteAgilisCash {

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
	DemandeAnnulationCarteAgilisCashRepository demandeAnnulationCarteAgilisCashRepository;

	@Autowired
	DemandeAffectationCarteAgilisCashRepository demandeAffectationCarteAgilisCashRepository;

	@Autowired
	HistoriqueAffectationCarteAgilisCashRepository historiqueAffectationCarteAgilisCashRepository;

	@Autowired
	RechargeCarteAgilisCashRepository rechargeCarteAgilisCashRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void createNewRechargeRequestCarteAgilisCash(NewRechargeCarteAgilisCashDTO newRechargeCarteAgilisCashDTO) {
		RechargeCarteAgilisCashEntity rechargeCarteAgilisCashEntity = new RechargeCarteAgilisCashEntity();
		rechargeCarteAgilisCashEntity.setMoisMission(newRechargeCarteAgilisCashDTO.getMoisMission());
		rechargeCarteAgilisCashEntity.setDestination(newRechargeCarteAgilisCashDTO.getDestination());
		rechargeCarteAgilisCashEntity.setIndexDepart(newRechargeCarteAgilisCashDTO.getIndexDepart());
		rechargeCarteAgilisCashEntity.setIndexArrivee(newRechargeCarteAgilisCashDTO.getIndexArrivee());
		rechargeCarteAgilisCashEntity.setDistanceParcourir(newRechargeCarteAgilisCashDTO.getDistanceParcourir());
		rechargeCarteAgilisCashEntity.setTauxConsommation(newRechargeCarteAgilisCashDTO.getTauxConsommation());
		rechargeCarteAgilisCashEntity
				.setQuantiteCarburantReservoir(newRechargeCarteAgilisCashDTO.getQuantiteCarburantReservoir());
		rechargeCarteAgilisCashEntity.setConfirmed(false);
		rechargeCarteAgilisCashEntity.setValidated(false);
		CarteAgilisCashEntity carteAgilisCashEntity = carteAgilisCashRepository
				.findById(newRechargeCarteAgilisCashDTO.getIdCarteAgilisCash()).get();
		if (carteAgilisCashEntity.getRechargeCarteAgilisCash().isEmpty()) {
			carteAgilisCashEntity.setRechargeCarteAgilisCash(new ArrayList<>());
		}
		carteAgilisCashEntity.setMontant(newRechargeCarteAgilisCashDTO.getMontantAccordee());
		carteAgilisCashRepository.save(carteAgilisCashEntity);
		carteAgilisCashEntity.getRechargeCarteAgilisCash().add(rechargeCarteAgilisCashEntity);
		rechargeCarteAgilisCashEntity.setCarteAgilisCash(carteAgilisCashEntity);
		rechargeCarteAgilisCashRepository.save(rechargeCarteAgilisCashEntity);
	}

	@Override
	public List<DemandeAffectationCarteAgilisCashDTO> getListDemandeRechargeCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAffectationCarteAgilisCashDTO> demandeAffectationCarteAgilisCashDTOS = new ArrayList<>();
		List<DemandeAffectationCarteAgilisCashEntity> demandeAffectationCarteAgilisCashEntities = (List<DemandeAffectationCarteAgilisCashEntity>) demandeAffectationCarteAgilisCashRepository
				.findAll();
		if (!demandeAffectationCarteAgilisCashEntities.isEmpty()) {
			demandeAffectationCarteAgilisCashEntities.forEach(demandeAffectationCarteAgilisCashEntity -> {
				demandeAffectationCarteAgilisCashDTOS.add(modelMapper.map(demandeAffectationCarteAgilisCashEntity,
						DemandeAffectationCarteAgilisCashDTO.class));
			});
		}
		return demandeAffectationCarteAgilisCashDTOS;
	}

	@Override
	public void deleteSelectedRechargeCarteAgilisCash(Long id) {
		RechargeCarteAgilisCashEntity rechargeCarteAgilisCashEntity = rechargeCarteAgilisCashRepository.findById(id)
				.get();
		CarteAgilisCashEntity carteAgilisCashEntity = rechargeCarteAgilisCashEntity.getCarteAgilisCash();
		carteAgilisCashEntity.getRechargeCarteAgilisCash().remove(rechargeCarteAgilisCashEntity);
		carteAgilisCashRepository.save(carteAgilisCashEntity);
		rechargeCarteAgilisCashRepository.deleteById(id);
	}

	@Override
	public void modifySelectedRechargeCarteAgilisCash(NewRechargeCarteAgilisCashDTO newRechargeCarteAgilisCashDTO) {

		final int test;
		RechargeCarteAgilisCashEntity rechargeCarteAgilisCashEntity = rechargeCarteAgilisCashRepository
				.findById(newRechargeCarteAgilisCashDTO.getId()).get();

		test = rechargeCarteAgilisCashEntity.getMontantAccordee();
		System.out.println("test montant accordé :");
		System.out.println(test);

		rechargeCarteAgilisCashEntity.setDestination(newRechargeCarteAgilisCashDTO.getDestination());
		rechargeCarteAgilisCashEntity.setIndexDepart(newRechargeCarteAgilisCashDTO.getIndexDepart());
		// rechargeCarteAgilisCashEntity.setIndexArrivee(newRechargeCarteAgilisCashDTO.getIndexArrivee());
		rechargeCarteAgilisCashEntity.setDistanceParcourir(newRechargeCarteAgilisCashDTO.getDistanceParcourir());
		// rechargeCarteAgilisCashEntity.setTauxConsommation(newRechargeCarteAgilisCashDTO.getTauxConsommation());
		// rechargeCarteAgilisCashEntity.setQuantiteCarburantReservoir(newRechargeCarteAgilisCashDTO.getQuantiteCarburantReservoir());
		rechargeCarteAgilisCashEntity.setMontantConfirmee(newRechargeCarteAgilisCashDTO.getMontantConfirmee());
		rechargeCarteAgilisCashEntity.setMontantAccordee(newRechargeCarteAgilisCashDTO.getMontantAccordee());
		CarteAgilisCashEntity carteAgilisCashEntity = carteAgilisCashRepository
				.findById(rechargeCarteAgilisCashEntity.getCarteAgilisCash().getId()).get();

		carteAgilisCashEntity.setMontant(
				(carteAgilisCashEntity.getMontant() - test) + newRechargeCarteAgilisCashDTO.getMontantAccordee());
		carteAgilisCashRepository.save(carteAgilisCashEntity);

		rechargeCarteAgilisCashRepository.save(rechargeCarteAgilisCashEntity);
	}

	@Override
	public List<HistoriqueAffectationCarteAgilisCashDTO> getListHistoriqueCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<HistoriqueAffectationCarteAgilisCashDTO> historiqueAffectationCarteAgilisCashDTOS = new ArrayList<>();
		List<HistoriqueAffectationCarteAgilisCashEntity> historiqueAffectationCarteAgilisCashEntities = (List<HistoriqueAffectationCarteAgilisCashEntity>) historiqueAffectationCarteAgilisCashRepository
				.findAll();
		if (!historiqueAffectationCarteAgilisCashEntities.isEmpty()) {
			historiqueAffectationCarteAgilisCashEntities.forEach(historiqueRechargeCarteAgilisCashEntity -> {
				historiqueAffectationCarteAgilisCashDTOS.add(modelMapper.map(historiqueRechargeCarteAgilisCashEntity,
						HistoriqueAffectationCarteAgilisCashDTO.class));
			});
		}
		return historiqueAffectationCarteAgilisCashDTOS;
	}

	@Override
	public void deleteSelectedHistoriqueRechargeCarteAgilisCash(Long id) {
		historiqueAffectationCarteAgilisCashRepository.deleteById(id);
	}

	@Override
	public List<CarteAgilisCashDTO> getListAgilisCashResponse() {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteAgilisCashDTO> carteAgilisCashDTOS = new ArrayList<>();
		List<CarteAgilisCashEntity> carteAgilisCashEntities = (List<CarteAgilisCashEntity>) carteAgilisCashRepository
				.findAll();
		if (!carteAgilisCashEntities.isEmpty()) {
			carteAgilisCashEntities.forEach(carteAgilisCashEntity -> {
				carteAgilisCashDTOS.add(modelMapper.map(carteAgilisCashEntity, CarteAgilisCashDTO.class));
			});
		}
		return carteAgilisCashDTOS;
	}

	@Override
	public void createOneDeclarationPerteCarteAgilisCash(
			DeclarationPerteCarteAgilisCashDTO declarationPerteCarteAgilisCashDTO) {
		ModelMapper modelMapper = new ModelMapper();
		CarteAgilisCashEntity carteAgilisCashEntity = carteAgilisCashRepository
				.findById(declarationPerteCarteAgilisCashDTO.getIdCarteagilis()).get();
		UserEntity userEntity = userRepository.findById(declarationPerteCarteAgilisCashDTO.getIdUser()).get();

		DeclarationPerteCarteAgilisCashEntity declarationPerteCarteAgilisCashEntityy = modelMapper
				.map(declarationPerteCarteAgilisCashDTO, DeclarationPerteCarteAgilisCashEntity.class);
		declarationPerteCarteAgilisCashEntityy.setCarteagilis(carteAgilisCashEntity);
		declarationPerteCarteAgilisCashEntityy.setUser(userEntity);

		declarationPerteCarteAgilisCashRepository.save(declarationPerteCarteAgilisCashEntityy);
	}

	@Override
	public List<DeclarationPerteCarteAgilisCashDTO> getListDeclarationPerteCarteAgilisCashByConfirmation(
			boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteAgilisCashDTO> declarationPerteCarteAgilisCashDTOS = new ArrayList<>();
		List<DeclarationPerteCarteAgilisCashEntity> declarationPerteCarteAgilisCashEntities = declarationPerteCarteAgilisCashRepository
				.findAllByConfirmed(false);
		if (!declarationPerteCarteAgilisCashEntities.isEmpty()) {
			declarationPerteCarteAgilisCashEntities.forEach(declarationPerteCarteAgilisCashEntity -> {
				declarationPerteCarteAgilisCashDTOS.add(modelMapper.map(declarationPerteCarteAgilisCashEntity,
						DeclarationPerteCarteAgilisCashDTO.class));
			});
		}
		return declarationPerteCarteAgilisCashDTOS;
	}

	@Override
	public List<DemandeAnnulationCarteAgilisCashDataTableDTO> getListDemandeAnnulationCarteAgilisCash(int page,
			int limit, String confirmed) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<DemandeAnnulationCarteAgilisCashEntity> demandeAnnulationCarteAgilisCashEntity = new ArrayList<>();
		if (confirmed.equals("true")) {
			Page<DemandeAnnulationCarteAgilisCashEntity> demandeAnnulationCarteAgilisCashEntities = demandeAnnulationCarteAgilisCashRepository
					.findByConfirmed(true, pageable);
			demandeAnnulationCarteAgilisCashEntity = demandeAnnulationCarteAgilisCashEntities.getContent();

		} else if (confirmed.equals("false")) {
			Page<DemandeAnnulationCarteAgilisCashEntity> demandeAnnulationCarteAgilisCashEntities = demandeAnnulationCarteAgilisCashRepository
					.findByConfirmed(false, pageable);
			demandeAnnulationCarteAgilisCashEntity = demandeAnnulationCarteAgilisCashEntities.getContent();
		} else {
			Page<DemandeAnnulationCarteAgilisCashEntity> demandeAnnulationCarteAgilisCashEntities = demandeAnnulationCarteAgilisCashRepository
					.findAll(pageable);
			demandeAnnulationCarteAgilisCashEntity = demandeAnnulationCarteAgilisCashEntities.getContent();
		}
		return this.loadAnnulationCarteAgilisCash(demandeAnnulationCarteAgilisCashEntity);
	}

	private List<DemandeAnnulationCarteAgilisCashDataTableDTO> loadAnnulationCarteAgilisCash(
			List<DemandeAnnulationCarteAgilisCashEntity> demandeAnnulationCarteAgilisCashEntities) {
		List<DemandeAnnulationCarteAgilisCashDataTableDTO> demandeAnnulationCarteAgilisCashDataTableDTOS = new ArrayList<>();
		if (!demandeAnnulationCarteAgilisCashEntities.isEmpty()) {
			demandeAnnulationCarteAgilisCashEntities.forEach(demandeAnnulationCarteAgilisCashEntity -> {
				DemandeAnnulationCarteAgilisCashDataTableDTO demandeAnnulationCarteAgilisCashDataTableDTO = new DemandeAnnulationCarteAgilisCashDataTableDTO();
				demandeAnnulationCarteAgilisCashDataTableDTO.setId(demandeAnnulationCarteAgilisCashEntity.getId());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setDateDemande(demandeAnnulationCarteAgilisCashEntity.getDateDemande());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setDateNaissanceDeclarant(demandeAnnulationCarteAgilisCashEntity.getDateNaissanceDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setLieuNaissanceDeclarant(demandeAnnulationCarteAgilisCashEntity.getLieuNaissanceDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setCauseAnnulation(demandeAnnulationCarteAgilisCashEntity.getCauseAnnulation());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setNumeroCarte(demandeAnnulationCarteAgilisCashEntity.getNumeroCarte());

				demandeAnnulationCarteAgilisCashDataTableDTO
						.setMatriculeBeneficiaire(demandeAnnulationCarteAgilisCashEntity.getMatriculeBeneficiaire());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setNomBeneficiaire(demandeAnnulationCarteAgilisCashEntity.getNomBeneficiaire());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setNomDeclarant(demandeAnnulationCarteAgilisCashEntity.getNomDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setNomDeclarant(demandeAnnulationCarteAgilisCashEntity.getNomDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setNumeroCarte(demandeAnnulationCarteAgilisCashEntity.getNumeroCarte());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setNumeroCINDeclarant(demandeAnnulationCarteAgilisCashEntity.getNumeroCINDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setNumeroPlaque(demandeAnnulationCarteAgilisCashEntity.getNumeroPlaque());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setPrenomDeclarant(demandeAnnulationCarteAgilisCashEntity.getPrenomDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setSexeDeclarant(demandeAnnulationCarteAgilisCashEntity.getSexeDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setSoldeRestant(demandeAnnulationCarteAgilisCashEntity.getSoldeRestant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setStructure(demandeAnnulationCarteAgilisCashEntity.getStructure());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setTypeCarburant(demandeAnnulationCarteAgilisCashEntity.getTypeCarburant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setTypeDeclarant(demandeAnnulationCarteAgilisCashEntity.getTypeDeclarant());
				demandeAnnulationCarteAgilisCashDataTableDTO
						.setConfirmed(demandeAnnulationCarteAgilisCashEntity.isConfirmed());
				demandeAnnulationCarteAgilisCashDataTableDTOS.add(demandeAnnulationCarteAgilisCashDataTableDTO);

			});
		}

		return demandeAnnulationCarteAgilisCashDataTableDTOS;
	}

	@Override
	public void confirmDeclarationPerteCarteAgilisCash(Long id) {
		DeclarationPerteCarteAgilisCashEntity declarationPerteCarteAgilisCashEntity = declarationPerteCarteAgilisCashRepository
				.findById(id).get();
		declarationPerteCarteAgilisCashEntity.setConfirmed(true);
		declarationPerteCarteAgilisCashEntity.setDateConfirmation(LocalDate.now());
		declarationPerteCarteAgilisCashRepository.save(declarationPerteCarteAgilisCashEntity);
	}

	@Override
	public void deleteOneDeclarationPerteCarteAgilisCash(Long id) {
		declarationPerteCarteAgilisCashRepository.deleteById(id);
	}

	@Override
	public void modifyOneDeclarationPerteCarteAgilisCash(
			DeclarationPerteCarteAgilisCashDTO declarationPerteCarteAgilisCashDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCarteAgilisCashEntity declarationPerteCarteAgilisCashEntity = modelMapper
				.map(declarationPerteCarteAgilisCashDTO, DeclarationPerteCarteAgilisCashEntity.class);
		declarationPerteCarteAgilisCashRepository.save(declarationPerteCarteAgilisCashEntity);
	}

	@Override
	public List<DeclarationPerteCarteAgilisCashDTO> getHistoriqueDeclarationPerteCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteAgilisCashEntity> declarationPerteCarteAgilisCashEntities = declarationPerteCarteAgilisCashRepository
				.findAllByConfirmed(true);
		List<DeclarationPerteCarteAgilisCashDTO> declarationPerteCarteAgilisCashDTOS = new ArrayList<>();
		if (!declarationPerteCarteAgilisCashEntities.isEmpty()) {
			declarationPerteCarteAgilisCashEntities.forEach(declarationPerteCarteAgilisCashEntity -> {
				declarationPerteCarteAgilisCashDTOS.add(modelMapper.map(declarationPerteCarteAgilisCashEntity,
						DeclarationPerteCarteAgilisCashDTO.class));
			});
		}
		return declarationPerteCarteAgilisCashDTOS;
	}

	@Override
	public void createNewDemandeAnnulationCarteCarburant(
			DemandeAnnulationCarteAgilisCashDTO demandeAnnulationCarteAgilisCashDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCarteAgilisCashEntity demandeAnnulationCarteAgilisCashEntity = modelMapper
				.map(demandeAnnulationCarteAgilisCashDTO, DemandeAnnulationCarteAgilisCashEntity.class);
		demandeAnnulationCarteAgilisCashEntity.setIdCard(demandeAnnulationCarteAgilisCashDTO.getIdCard());
		demandeAnnulationCarteAgilisCashEntity.setDateDemande(LocalDate.now());
		System.out.println(demandeAnnulationCarteAgilisCashDTO);
		demandeAnnulationCarteAgilisCashRepository.save(demandeAnnulationCarteAgilisCashEntity);
	}

	@Override
	public List<DemandeAnnulationCarteAgilisCashDTO> getHistoriqueDemandeAnnulationCarteAgilisCashByConfirmation(
			boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAnnulationCarteAgilisCashEntity> demandeAnnulationCarteAgilisCashEntities = demandeAnnulationCarteAgilisCashRepository
				.findAllByConfirmed(false);
		List<DemandeAnnulationCarteAgilisCashDTO> demandeAnnulationCarteAgilisCashDTOS = new ArrayList<>();
		if (!demandeAnnulationCarteAgilisCashEntities.isEmpty()) {
			demandeAnnulationCarteAgilisCashEntities.forEach(demandeAnnulationCarteAgilisCashEntity -> {
				demandeAnnulationCarteAgilisCashDTOS.add(modelMapper.map(demandeAnnulationCarteAgilisCashEntity,
						DemandeAnnulationCarteAgilisCashDTO.class));
			});
		}
		return demandeAnnulationCarteAgilisCashDTOS;
	}

	@Override
	public void deleteDemandeAnnulationCarteAgilisCash(Long id) {
		demandeAnnulationCarteAgilisCashRepository.deleteById(id);
	}

	@Override
	public List<VehiculeDTO> getListVehiculesServiceWithNoCarteAgilisCash() {
		List<VehiculeDTO> vehiculeDTOS = new ArrayList<>();
		return vehiculeDTOS;
	}

	@Override
	public void createNewCarteAgilisCash(NewCarteAgilisCashDTO newCarteAgilisCashDTO) {
		VehiculeEntity vehiculeEntity = vehiculeRepository.findById(newCarteAgilisCashDTO.getIdVehicule()).get();
		CarteAgilisCashEntity carteAgilisCashEntity = new CarteAgilisCashEntity();
		carteAgilisCashEntity.setNumeroCarte(newCarteAgilisCashDTO.getNumeroCarte());
		carteAgilisCashEntity.setMontant(newCarteAgilisCashDTO.getMontant());
		carteAgilisCashEntity.setDateFinValidite(newCarteAgilisCashDTO.getDateFinValidite());
		carteAgilisCashEntity.setTypeCarburant(newCarteAgilisCashDTO.getTypeCarburant());
		carteAgilisCashEntity.setRecharged(false);
		carteAgilisCashEntity.setConfirmed(false);
		carteAgilisCashEntity.setValidated(false);
		carteAgilisCashEntity.setVehicule(vehiculeEntity);
		vehiculeEntity.setCarteAgilisCash(carteAgilisCashEntity);
		carteAgilisCashRepository.save(carteAgilisCashEntity);
	}

	@Override
	public Long getTotalNumberListCarteAgilisCashByTypeCarburant(String typeCarburant) {
		if (typeCarburant.equals("tousTypes")) {
			return carteAgilisCashRepository.getTotalNumberCarteAgilisCash();
		} else {
			return carteAgilisCashRepository.getTotalNumberCarteAgilisCashByTypeCarburant(typeCarburant);
		}
	}

	@Override
	public void deleteSelectedCarteAgilisCash(Long id) {
		carteAgilisCashRepository.deleteById(id);
		CarteAgilisCashEntity carteAgilisCashEntity = carteAgilisCashRepository.findById(id).get();
		carteAgilisCashEntity.getVehicule().setCarteAgilisCash(null);
		carteAgilisCashEntity.setVehicule(null);
		carteAgilisCashRepository.save(carteAgilisCashEntity);
		carteAgilisCashRepository.deleteById(id);
	}

	@Override
	public List<ListCarteAgilisCashDTO> getListCarteAgilisCash() {
		List<CarteAgilisCashEntity> carteAgilisCashEntities = carteAgilisCashRepository.findAllByActivated(false);
		return this.loadListCarteAgilisCashDTO(carteAgilisCashEntities);
	}

	@Override
	public List<CarteAgilisCashDTO> getListAllCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteAgilisCashDTO> carteAgilisCashDTOS = new ArrayList<>();
		List<CarteAgilisCashEntity> carteAgilisCashEntities = (List<CarteAgilisCashEntity>) carteAgilisCashRepository
				.findAll();
		if (!carteAgilisCashEntities.isEmpty()) {
			carteAgilisCashEntities.forEach(carteAgilisCashEntity -> {
				carteAgilisCashDTOS.add(modelMapper.map(carteAgilisCashEntity, CarteAgilisCashDTO.class));
			});
		}
		return carteAgilisCashDTOS;
	}

	@Override
	public List<DeclarationPerteCarteAgilisCashDTO> getHistoriqueDeclarationPerteCarteAgilisCashByConfirmation(
			String confirmed) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteAgilisCashDTO> declarationPerteCarteAgilisCashDTOS = new ArrayList<>();
		List<DeclarationPerteCarteAgilisCashEntity> declarationPerteCarteAgilisCashEntities = new ArrayList<>();
		if (confirmed.equals("true")) {
			declarationPerteCarteAgilisCashEntities = declarationPerteCarteAgilisCashRepository
					.findAllByConfirmed(true);
		} else {
			declarationPerteCarteAgilisCashEntities = declarationPerteCarteAgilisCashRepository
					.findAllByConfirmed(false);
		}

		if (!declarationPerteCarteAgilisCashEntities.isEmpty()) {
			declarationPerteCarteAgilisCashEntities.forEach(declarationPerteCarteAgilisCashEntity -> {
				declarationPerteCarteAgilisCashDTOS.add(modelMapper.map(declarationPerteCarteAgilisCashEntity,
						DeclarationPerteCarteAgilisCashDTO.class));
			});
		}
		return declarationPerteCarteAgilisCashDTOS;
	}

	@Override
	public void confirmDemandeAnnulationCarteAgilisCash(Long id) {
		DemandeAnnulationCarteAgilisCashEntity demandeAnnulationCarteAgilisCashEntity = demandeAnnulationCarteAgilisCashRepository
				.findById(id).get();
		CarteAgilisCashEntity cae = carteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashEntity.getIdCard()).get();
		carteAgilisCashRepository.deleteById(demandeAnnulationCarteAgilisCashEntity.getIdCard());
		demandeAnnulationCarteAgilisCashEntity.setConfirmed(true);
		demandeAnnulationCarteAgilisCashRepository.save(demandeAnnulationCarteAgilisCashEntity);
	}

	@Override
	public List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForAgilisCash(UserEntity userEntity) {
		List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
		if (userEntity.getStructures().isEmpty()) {
			vehiculeEntities = vehiculeRepository.findAllByCarteAgilisCashNull();
		} else {
			StructureEntity structureEntity = userEntity.getStructures().get(0);
			vehiculeEntities = vehiculeRepository.findAllByStructureAndCarteAgilisCashIsNull(structureEntity);
		}
		return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
	}

	@Override
	public List<CarteAgilisCashTabDataDTO> getPaginationListCarteAgilisCashByTypeCarburant(int page, int limit,
			String typeCarburant) {
		List<CarteAgilisCashEntity> carteAgilisCashEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (typeCarburant.equals("tousTypes")) {
			Page<CarteAgilisCashEntity> carteAgilisCashEntityPage = carteAgilisCashRepository.findAll(pageable);
			carteAgilisCashEntities = carteAgilisCashEntityPage.getContent();
		} else {
			Page<CarteAgilisCashEntity> carteAgilisCashEntityPage = carteAgilisCashRepository
					.findAllByTypeCarburant(typeCarburant, pageable);
			carteAgilisCashEntities = carteAgilisCashEntityPage.getContent();
		}
		return this.loadCarteAgilisCashTabDataDTO(carteAgilisCashEntities);
	}

	@Override
	public void modifySelectedCarteAgilisCash(NewCarteAgilisCashDTO newCarteAgilisCashDTO) {
		CarteAgilisCashEntity carteAgilisCashEntity = carteAgilisCashRepository
				.findById(newCarteAgilisCashDTO.getIdCarte()).get();
		carteAgilisCashEntity.setMontant(newCarteAgilisCashDTO.getMontant());
		carteAgilisCashEntity.setNumeroCarte(newCarteAgilisCashDTO.getNumeroCarte());
		carteAgilisCashEntity.setDateFinValidite(newCarteAgilisCashDTO.getDateFinValidite());
		carteAgilisCashRepository.save(carteAgilisCashEntity);
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
				selectVehiculeDTO.setTypeCarburant(vehiculeEntity.getEnergie().getDescription());
				if (vehiculeEntity.getBeneficiaire() != null) {
					selectVehiculeDTO.setNomBeneficiaire(vehiculeEntity.getBeneficiaire().getNom());
					selectVehiculeDTO.setMatriculeBeneficiaire(vehiculeEntity.getBeneficiaire().getMatricule());
					selectVehiculeDTO.setAgePermis(vehiculeEntity.getBeneficiaire().getAgePermis());
				}
				selectVehiculeDTOS.add(selectVehiculeDTO);
			});
		}
		return selectVehiculeDTOS;
	}

	private List<CarteAgilisCashTabDataDTO> loadCarteAgilisCashTabDataDTO(
			List<CarteAgilisCashEntity> carteAgilisCashEntities) {
		List<CarteAgilisCashTabDataDTO> carteAgilisCashTabDataDTOS = new ArrayList<>();
		if (!carteAgilisCashEntities.isEmpty()) {
			carteAgilisCashEntities.forEach(carteAgilisCashEntity -> {
				CarteAgilisCashTabDataDTO carteAgilisCashTabDataDTO = new CarteAgilisCashTabDataDTO();
				carteAgilisCashTabDataDTO.setId(carteAgilisCashEntity.getId());
				carteAgilisCashTabDataDTO.setIdVehicule(carteAgilisCashEntity.getVehicule().getId());
				carteAgilisCashTabDataDTO.setNumeroCarte(carteAgilisCashEntity.getNumeroCarte());
				carteAgilisCashTabDataDTO
						.setTypeCarburant(carteAgilisCashEntity.getVehicule().getEnergie().getDescription());
				carteAgilisCashTabDataDTO.setMontant(carteAgilisCashEntity.getMontant());
				carteAgilisCashTabDataDTO.setDateFinValidite(carteAgilisCashEntity.getDateFinValidite());
				carteAgilisCashTabDataDTO.setNumeroPlaque(carteAgilisCashEntity.getVehicule().getNumeroPlaque());
				carteAgilisCashTabDataDTO
						.setCodeStructure(carteAgilisCashEntity.getVehicule().getStructure().getCode());
				carteAgilisCashTabDataDTO
						.setDesignationStructure(carteAgilisCashEntity.getVehicule().getStructure().getDesignation());
				if (carteAgilisCashEntity.getVehicule().getBeneficiaire() != null) {
					carteAgilisCashTabDataDTO
							.setNomBeneficiaire(carteAgilisCashEntity.getVehicule().getBeneficiaire().getNom());
					carteAgilisCashTabDataDTO.setMatriculeBeneficiaire(
							carteAgilisCashEntity.getVehicule().getBeneficiaire().getMatricule());
				}
				carteAgilisCashTabDataDTOS.add(carteAgilisCashTabDataDTO);
			});
		}
		return carteAgilisCashTabDataDTOS;
	}

	private List<ListCarteAgilisCashDTO> loadListCarteAgilisCashDTO(
			List<CarteAgilisCashEntity> carteAgilisCashEntities) {
		List<ListCarteAgilisCashDTO> listCarteAgilisCashDTOS = new ArrayList<>();
		if (!carteAgilisCashEntities.isEmpty()) {
			carteAgilisCashEntities.forEach(carteAgilisCashEntity -> {
				ListCarteAgilisCashDTO listCarteAgilisCashDTO = new ListCarteAgilisCashDTO();
				listCarteAgilisCashDTO.setIdCarte(carteAgilisCashEntity.getId());
				listCarteAgilisCashDTO.setNumeroCarte(carteAgilisCashEntity.getNumeroCarte());
				listCarteAgilisCashDTO
						.setTypeCarburant(carteAgilisCashEntity.getVehicule().getEnergie().getDescription());
				listCarteAgilisCashDTO.setSoldeRestant(carteAgilisCashEntity.getSoldeRestant());
				listCarteAgilisCashDTO.setMontant(carteAgilisCashEntity.getMontant());
				listCarteAgilisCashDTO.setIdVehicule(carteAgilisCashEntity.getVehicule().getId());
				listCarteAgilisCashDTO.setNumeroPlaque(carteAgilisCashEntity.getVehicule().getNumeroPlaque());
				listCarteAgilisCashDTO
						.setDesignationStructure(carteAgilisCashEntity.getVehicule().getStructure().getDesignation());
				listCarteAgilisCashDTO.setCodeStructure(carteAgilisCashEntity.getVehicule().getStructure().getCode());
				listCarteAgilisCashDTO.setDateFinValidite(carteAgilisCashEntity.getDateFinValidite());
				if (carteAgilisCashEntity.getVehicule().getBeneficiaire() != null) {
					listCarteAgilisCashDTO
							.setIdBeneficiaire(carteAgilisCashEntity.getVehicule().getBeneficiaire().getId());
					listCarteAgilisCashDTO.setMatriculeBeneficiaire(
							carteAgilisCashEntity.getVehicule().getBeneficiaire().getMatricule());
					listCarteAgilisCashDTO
							.setNomBeneficiaire(carteAgilisCashEntity.getVehicule().getBeneficiaire().getNom());
				}
				listCarteAgilisCashDTOS.add(listCarteAgilisCashDTO);
			});
		}
		return listCarteAgilisCashDTOS;
	}

	@Override
	public List<RechargeCarteAgilisCashTabDataDTO> getPaginationListRechargeCarteAgilisCash(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<RechargeCarteAgilisCashEntity> rechargeCarteAgilisCashEntities = rechargeCarteAgilisCashRepository
				.findAll(pageable).getContent();
		return this.loadRechargeCarteAgilisCashTabDataDTO(rechargeCarteAgilisCashEntities);
	}

	private List<RechargeCarteAgilisCashTabDataDTO> loadRechargeCarteAgilisCashTabDataDTO(
			List<RechargeCarteAgilisCashEntity> rechargeCarteAgilisCashEntities) {
		List<RechargeCarteAgilisCashTabDataDTO> rechargeCarteAgilisCashTabDataDTOS = new ArrayList<>();
		if (!rechargeCarteAgilisCashEntities.isEmpty()) {
			rechargeCarteAgilisCashEntities.forEach(rechargeCarteAgilisCashEntity -> {
				RechargeCarteAgilisCashTabDataDTO RechargeCarteAgilisCashTabDataDTO = new RechargeCarteAgilisCashTabDataDTO();
				RechargeCarteAgilisCashTabDataDTO.setId(rechargeCarteAgilisCashEntity.getId());
				RechargeCarteAgilisCashTabDataDTO
						.setIdVehicule(rechargeCarteAgilisCashEntity.getCarteAgilisCash().getVehicule().getId());
				RechargeCarteAgilisCashTabDataDTO
						.setNumeroCarte(rechargeCarteAgilisCashEntity.getCarteAgilisCash().getNumeroCarte());
				RechargeCarteAgilisCashTabDataDTO.setTypeCarburant(
						rechargeCarteAgilisCashEntity.getCarteAgilisCash().getVehicule().getEnergie().getDescription());
				RechargeCarteAgilisCashTabDataDTO
						.setMontant(rechargeCarteAgilisCashEntity.getCarteAgilisCash().getMontant());
				RechargeCarteAgilisCashTabDataDTO
						.setDateFinValidite(rechargeCarteAgilisCashEntity.getCarteAgilisCash().getDateFinValidite());
				RechargeCarteAgilisCashTabDataDTO.setNumeroPlaque(
						rechargeCarteAgilisCashEntity.getCarteAgilisCash().getVehicule().getNumeroPlaque());
				RechargeCarteAgilisCashTabDataDTO.setCodeStructure(
						rechargeCarteAgilisCashEntity.getCarteAgilisCash().getVehicule().getStructure().getCode());
				RechargeCarteAgilisCashTabDataDTO.setDesignationStructure(rechargeCarteAgilisCashEntity
						.getCarteAgilisCash().getVehicule().getStructure().getDesignation());
				if (rechargeCarteAgilisCashEntity.getCarteAgilisCash().getVehicule().getBeneficiaire() != null) {
					RechargeCarteAgilisCashTabDataDTO.setNomBeneficiaire(rechargeCarteAgilisCashEntity
							.getCarteAgilisCash().getVehicule().getBeneficiaire().getNom());
					RechargeCarteAgilisCashTabDataDTO.setMatriculeBeneficiaire(rechargeCarteAgilisCashEntity
							.getCarteAgilisCash().getVehicule().getBeneficiaire().getMatricule());
				}
				RechargeCarteAgilisCashTabDataDTO.setMoisMission(rechargeCarteAgilisCashEntity.getMoisMission());
				RechargeCarteAgilisCashTabDataDTO.setDestination(rechargeCarteAgilisCashEntity.getDestination());
				RechargeCarteAgilisCashTabDataDTO.setIndexDepart(rechargeCarteAgilisCashEntity.getIndexDepart());
				RechargeCarteAgilisCashTabDataDTO.setIndexArrivee(rechargeCarteAgilisCashEntity.getIndexArrivee());
				RechargeCarteAgilisCashTabDataDTO
						.setDistanceParcourir(rechargeCarteAgilisCashEntity.getDistanceParcourir());
				RechargeCarteAgilisCashTabDataDTO
						.setTauxConsommation(rechargeCarteAgilisCashEntity.getTauxConsommation());
				RechargeCarteAgilisCashTabDataDTO
						.setQuantiteCarburantReservoir(rechargeCarteAgilisCashEntity.getQuantiteCarburantReservoir());
				RechargeCarteAgilisCashTabDataDTO.setConfirmed(rechargeCarteAgilisCashEntity.getConfirmed());
				RechargeCarteAgilisCashTabDataDTO.setValidated(rechargeCarteAgilisCashEntity.getValidated());
				RechargeCarteAgilisCashTabDataDTO
						.setMontantConfirmee(rechargeCarteAgilisCashEntity.getMontantConfirmee());
				RechargeCarteAgilisCashTabDataDTO
						.setMontantAccordee(rechargeCarteAgilisCashEntity.getMontantAccordee());
				rechargeCarteAgilisCashTabDataDTOS.add(RechargeCarteAgilisCashTabDataDTO);
			});
		}
		return rechargeCarteAgilisCashTabDataDTOS;
	}

	@Override
	public Long getTotalNumberListRechargeCarteAgilisCash() {
		PageRequest pageable = PageRequest.of(0, 8);
		return rechargeCarteAgilisCashRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public Long getTotalNumberAnnulationCarteAgilisCash() {
		PageRequest pageable = PageRequest.of(0, 8);
		return demandeAnnulationCarteAgilisCashRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public void confirmSelectedCarteAgilisCash(Long id) {
		RechargeCarteAgilisCashEntity rechargeCarteAgilisCashEntity = rechargeCarteAgilisCashRepository.findById(id)
				.get();
		rechargeCarteAgilisCashEntity.setConfirmed(true);
		rechargeCarteAgilisCashRepository.save(rechargeCarteAgilisCashEntity);
	}

	@Override
	public void validateSelectedCarteAgilisCash(Long id) {
		RechargeCarteAgilisCashEntity rechargeCarteAgilisCashEntity = rechargeCarteAgilisCashRepository.findById(id)
				.get();
		rechargeCarteAgilisCashEntity.setValidated(true);
		rechargeCarteAgilisCashRepository.save(rechargeCarteAgilisCashEntity);
	}

	@Override
	public void modifyOneDemandeAnnulationCarteAgilis(
			DemandeAnnulationCarteAgilisCashDTO demandeAnnulationCarteAgilisCashDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCarteAgilisCashEntity demandeAnnulationCarteAgilisCashEntity = modelMapper
				.map(demandeAnnulationCarteAgilisCashDTO, DemandeAnnulationCarteAgilisCashEntity.class);
		demandeAnnulationCarteAgilisCashEntity.setDateDemande(demandeAnnulationCarteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashDTO.getId()).get().getDateDemande());
		demandeAnnulationCarteAgilisCashEntity.setNumeroCarte(demandeAnnulationCarteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashDTO.getId()).get().getNumeroCarte());
		demandeAnnulationCarteAgilisCashEntity.setTypeCarburant(demandeAnnulationCarteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashDTO.getId()).get().getTypeCarburant());

		demandeAnnulationCarteAgilisCashEntity.setNumeroPlaque(demandeAnnulationCarteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashDTO.getId()).get().getNumeroPlaque());
		demandeAnnulationCarteAgilisCashEntity.setSoldeRestant(demandeAnnulationCarteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashDTO.getId()).get().getSoldeRestant());
		demandeAnnulationCarteAgilisCashEntity.setStructure(demandeAnnulationCarteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashDTO.getId()).get().getStructure());

		demandeAnnulationCarteAgilisCashEntity.setIdCard(demandeAnnulationCarteAgilisCashRepository
				.findById(demandeAnnulationCarteAgilisCashDTO.getId()).get().getIdCard());
		demandeAnnulationCarteAgilisCashRepository.save(demandeAnnulationCarteAgilisCashEntity);
	}

	@Override
	public List<DeclarationPerteCarteAgilisCashDTO> getPaginationdeclarationperteCarteAgilis(int page, int limit,
			String confirmed) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<DeclarationPerteCarteAgilisCashEntity> declarationPerteCarteAgilisCashEntity = new ArrayList<>();
		if (confirmed.equals("true")) {
			Page<DeclarationPerteCarteAgilisCashEntity> DeclarationPerteCarteAgilisCashEntities = declarationPerteCarteAgilisCashRepository
					.findByConfirmed(true, pageable);
			declarationPerteCarteAgilisCashEntity = DeclarationPerteCarteAgilisCashEntities.getContent();

		} else if (confirmed.equals("false")) {
			Page<DeclarationPerteCarteAgilisCashEntity> DeclarationPerteCarteAgilisCashEntities = declarationPerteCarteAgilisCashRepository
					.findByConfirmed(false, pageable);
			declarationPerteCarteAgilisCashEntity = DeclarationPerteCarteAgilisCashEntities.getContent();
		} else {
			Page<DeclarationPerteCarteAgilisCashEntity> DeclarationPerteCarteAgilisCashEntities = declarationPerteCarteAgilisCashRepository
					.findAll(pageable);
			declarationPerteCarteAgilisCashEntity = DeclarationPerteCarteAgilisCashEntities.getContent();
		}
		return this.loadPerteCarteAgilisCash(declarationPerteCarteAgilisCashEntity);
	}

	private List<DeclarationPerteCarteAgilisCashDTO> loadPerteCarteAgilisCash(
			List<DeclarationPerteCarteAgilisCashEntity> declarationPerteCarteAgilisCashEntities) {
		List<DeclarationPerteCarteAgilisCashDTO> declarationPerteCarteAgilisCashDTO = new ArrayList<>();
		if (!declarationPerteCarteAgilisCashEntities.isEmpty()) {
			declarationPerteCarteAgilisCashEntities.forEach(declarationPerteCarteAgilisCashEntity -> {
				DeclarationPerteCarteAgilisCashDTO declarationPerteCarteAgilisCashDTOs = new DeclarationPerteCarteAgilisCashDTO();
				declarationPerteCarteAgilisCashDTOs.setId(declarationPerteCarteAgilisCashEntity.getId());
				declarationPerteCarteAgilisCashDTOs.setDatePerte(declarationPerteCarteAgilisCashEntity.getDatePerte());
				declarationPerteCarteAgilisCashDTOs
						.setDateNaissanceDeclarant(declarationPerteCarteAgilisCashEntity.getDateNaissanceDeclarant());
				declarationPerteCarteAgilisCashDTOs
						.setLieuNaissanceDeclarant(declarationPerteCarteAgilisCashEntity.getLieuNaissanceDeclarant());
				declarationPerteCarteAgilisCashDTOs
						.setCirconstances(declarationPerteCarteAgilisCashEntity.getCirconstances());
				declarationPerteCarteAgilisCashDTOs
						.setNomBeneficiaire(declarationPerteCarteAgilisCashEntity.getNomBeneficiaire());
				declarationPerteCarteAgilisCashDTOs
						.setNomDeclarant(declarationPerteCarteAgilisCashEntity.getNomDeclarant());
				declarationPerteCarteAgilisCashDTOs
						.setNumeroCINDeclarant(declarationPerteCarteAgilisCashEntity.getNumeroCINDeclarant());
				declarationPerteCarteAgilisCashDTOs
						.setNumeroPlaque(declarationPerteCarteAgilisCashEntity.getNumeroPlaque());
				declarationPerteCarteAgilisCashDTOs
						.setPrenomDeclarant(declarationPerteCarteAgilisCashEntity.getPrenomDeclarant());
				declarationPerteCarteAgilisCashDTOs
						.setSexeDeclarant(declarationPerteCarteAgilisCashEntity.getSexeDeclarant());
				declarationPerteCarteAgilisCashDTOs.setStructure(declarationPerteCarteAgilisCashEntity.getStructure());
				declarationPerteCarteAgilisCashDTOs.setLieuPerte(declarationPerteCarteAgilisCashEntity.getLieuPerte());
				declarationPerteCarteAgilisCashDTOs
						.setTypeDeclarant(declarationPerteCarteAgilisCashEntity.getTypeDeclarant());
				declarationPerteCarteAgilisCashDTO.add(declarationPerteCarteAgilisCashDTOs);

			});
		}

		return declarationPerteCarteAgilisCashDTO;
	}

	@Override
	public Long getTotalNumberdeclarationPerteCarteAgilis() {
		PageRequest pageable = PageRequest.of(0, 8);
		return declarationPerteCarteAgilisCashRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public List<DeclarationPerteCarteAgilisCashDTO> getPaginationdeclarationperteCarteAgilis(int page, int limit) {
		return null;
	}

}
