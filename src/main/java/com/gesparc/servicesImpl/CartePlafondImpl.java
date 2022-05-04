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
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.DeclarationPerteCartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.DemandeAnnulationCartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.repositories.*;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.services.CartePlafond;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.DeclarationPerteCartePlafondDTO;
import com.gesparc.sharedDTO.DemandeAnnulationCartePlafondDTO;
import com.gesparc.sharedDTO.HistoriqueAffectationCartePlafondDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.NewCartePlafondDTO;
import com.gesparc.sharedDTO.carburant.additionnel.AffectationCartePlafondTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CartePlafondTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
@Service
public class CartePlafondImpl implements CartePlafond {

	Logger logger = LoggerFactory.getLogger(CartePlafondImpl.class);

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
	UserRepository userRepository;

	@Override
	public void createNewCartePlafond(NewCartePlafondDTO newCartePlafondDTO) {
		CartePlafondEntity cartePlafondEntity = new CartePlafondEntity();
		cartePlafondEntity.setMontant(newCartePlafondDTO.getMontant());
		cartePlafondEntity.setDateValiditee(newCartePlafondDTO.getDateValiditee());
		cartePlafondEntity.setNumeroCarte(newCartePlafondDTO.getNumeroCarte());
		cartePlafondEntity.setTypeCarburant(null);
		cartePlafondEntity.setActivated(false);
		cartePlafondEntity.setStatus(newCartePlafondDTO.getStatus());
		cartePlafondEntity.setDateAjout(new Date());
		cartePlafondRepository.save(cartePlafondEntity);
	}

	@Override
	public void modifySelectedCartePlafond(NewCartePlafondDTO newCartePlafondDTO) {
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository.findById(newCartePlafondDTO.getId()).get();
		cartePlafondEntity.setNumeroCarte(newCartePlafondDTO.getNumeroCarte());
		cartePlafondEntity.setMontant(newCartePlafondDTO.getMontant());
		cartePlafondEntity.setStatus(newCartePlafondDTO.getStatus());
		cartePlafondEntity.setDateValiditee(newCartePlafondDTO.getDateValiditee());
		cartePlafondRepository.save(cartePlafondEntity);
	}

	@Override
	public void deleteSelectedCartePlafond(Long id) {
		//CartePlafondEntity cartePlafondEntity = cartePlafondRepository.findById(id).get();
		//cartePlafondEntity.getVehicule().setCartePlafond(null);
		//cartePlafondEntity.setVehicule(null);
		//cartePlafondRepository.save(cartePlafondEntity);
		cartePlafondRepository.deleteById(id);
	}

	@Override
	public List<CartePlafondTabDataDTO> getPaginationListCartePlafondByTypeCarburant(String typeCarburant, int page,
			int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<CartePlafondEntity> cartePlafondEntities = new ArrayList<>();
		if (typeCarburant.equals("tousTypes")) {
			Page<CartePlafondEntity> cartePlafondEntityPage = cartePlafondRepository.findAll(pageable);
			cartePlafondEntities = cartePlafondEntityPage.getContent();
		} else {
			Page<CartePlafondEntity> cartePlafondEntityPage = cartePlafondRepository
					.findAllByTypeCarburant(typeCarburant, pageable);
			cartePlafondEntities = cartePlafondEntityPage.getContent();
		}
		return this.loadCartePlafondTabData(cartePlafondEntities);
	}

	@Override
	public Long getTotalNumberListCartePlafondByTypeCarburant(String typeCarburant) {
		if (typeCarburant.equals("tousTypes")) {
			return cartePlafondRepository.getTotalNumberListCartePlafond();
		} else {
			return cartePlafondRepository.getTotalNumberListCartePlafondByTypeCarburant(typeCarburant);
		}
	}

	@Override
	public List<SelectVehiculeDTO> getListVehiculeWithNoCartePlafond(UserEntity userEntity) {
		List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
		if (userEntity.getStructures().isEmpty()) {
			vehiculeEntities = vehiculeRepository.findAllByCartePlafondNull();
		} else {
			StructureEntity structureEntity = userEntity.getStructures().get(0);
			vehiculeEntities = vehiculeRepository.findAllByStructureAndCartePlafondNull(structureEntity);
		}
		return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
	}

	@Override
	public void createNewDemandAffectationCartePlafond(HistoriqueAffectationCartePlafondEntity HCPE) {
		CartePlafondEntity cpe = cartePlafondRepository.findById(HCPE.getCartePlafond().getId()).get();
		VehiculeEntity ve = vehiculeRepository.findById(HCPE.getIdVehicule()).get();
		HistoriqueAffectationCartePlafondEntity historiqueAffectationCartePlafondEntity = new HistoriqueAffectationCartePlafondEntity();
		historiqueAffectationCartePlafondEntity.setIdVehicule(HCPE.getIdVehicule());
		historiqueAffectationCartePlafondEntity.setDateAffectation(HCPE.getDateAffectation());
		cpe.setVehicule(ve);
		historiqueAffectationCartePlafondRepository.save(historiqueAffectationCartePlafondEntity);
	}

	@Override
	public void confirmDemandeAffectationCartePlafond(Long id) {
		HistoriqueAffectationCartePlafondEntity hacpe = historiqueAffectationCartePlafondRepository.findById(id).get();
		hacpe.setConfirmed(true);
		historiqueAffectationCartePlafondRepository.save(hacpe);
	}

	@Override
	public void validateDemandeAffectationCartePlafond(Long idCarte) {
		HistoriqueAffectationCartePlafondEntity hacpe = historiqueAffectationCartePlafondRepository.findById(idCarte)
				.get();
		hacpe.setValidated(true);
		historiqueAffectationCartePlafondRepository.save(hacpe);
	}

	@Override
	public void deleteDemandAffectationCartePlafond(Long id) {
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository.findById(id).get();
		cartePlafondEntity.getVehicule().setCartePlafond(null);
		vehiculeRepository.save(cartePlafondEntity.getVehicule());
		cartePlafondEntity.setVehicule(null);
		cartePlafondRepository.save(cartePlafondEntity);
	}

	@Override
	public Long getTotalItemsHistoriqueAffectationCartesPlafondBySelection(String typeCarburant) {
		if (typeCarburant.equals("tousTypes")) {
			return historiqueAffectationCartePlafondRepository.getTotalItems();
		} else {
			return historiqueAffectationCartePlafondRepository.getTotalItemsByTypeCarburant(typeCarburant);
		}
	}

	@Override
	public List<HistoriqueAffectationCartePlafondTabDataDTO> getPaginationHistoriqueAffectationCartePlafond(int page,
			int limit, String selectedTypeCarburant) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<HistoriqueAffectationCartePlafondEntity> historiqueAffectationCartePlafondEntity = new ArrayList<>();
		if (selectedTypeCarburant.equals("tousTypes")) {

			Page<HistoriqueAffectationCartePlafondEntity> historiqueAffectationCartePlafondEntities = historiqueAffectationCartePlafondRepository
					.findAll(pageable);
			historiqueAffectationCartePlafondEntity = historiqueAffectationCartePlafondEntities.getContent();

		} else {
			Page<HistoriqueAffectationCartePlafondEntity> historiqueAffectationCartePlafondEntities = historiqueAffectationCartePlafondRepository
					.findAllByTypeCarburant(selectedTypeCarburant, pageable);
			historiqueAffectationCartePlafondEntity = historiqueAffectationCartePlafondEntities.getContent();
		}
		return this.loadHistoriqueAffectationCartePlafondTabDataDTO(historiqueAffectationCartePlafondEntity);
	}

	private List<HistoriqueAffectationCartePlafondTabDataDTO> loadHistoriqueAffectationCartePlafondTabDataDTO(
			List<HistoriqueAffectationCartePlafondEntity> historiqueAffectationCartePlafondEntities) {
		List<HistoriqueAffectationCartePlafondTabDataDTO> historiqueAffectationCartePlafondTabDataDTOS = new ArrayList<>();
		if (!historiqueAffectationCartePlafondEntities.isEmpty()) {
			historiqueAffectationCartePlafondEntities.forEach(historiqueAffectationCartePlafondEntity -> {
				HistoriqueAffectationCartePlafondTabDataDTO historiqueAffectationCartePlafondTabDataDTO = new HistoriqueAffectationCartePlafondTabDataDTO();
				historiqueAffectationCartePlafondTabDataDTO.setId(historiqueAffectationCartePlafondEntity.getId());
				historiqueAffectationCartePlafondTabDataDTO
						.setDateAffectation(historiqueAffectationCartePlafondEntity.getDateAffectation());
				historiqueAffectationCartePlafondTabDataDTO
						.setIdVehicule(historiqueAffectationCartePlafondEntity.getIdVehicule());
				historiqueAffectationCartePlafondTabDataDTO
						.setConfirmed(historiqueAffectationCartePlafondEntity.isConfirmed());
				historiqueAffectationCartePlafondTabDataDTO
						.setMatriculeBeneficiaire(historiqueAffectationCartePlafondEntity.getMatriculeBeneficiaire());
				historiqueAffectationCartePlafondTabDataDTO
						.setMontant(historiqueAffectationCartePlafondEntity.getMontant());
				historiqueAffectationCartePlafondTabDataDTO
						.setNomBeneficiaire(historiqueAffectationCartePlafondEntity.getNomBeneficiaire());
				historiqueAffectationCartePlafondTabDataDTO
						.setNumeroCarte(historiqueAffectationCartePlafondEntity.getNumeroCarte());
				historiqueAffectationCartePlafondTabDataDTO
						.setNumeroPlaque(historiqueAffectationCartePlafondEntity.getNumeroPlaque());
				historiqueAffectationCartePlafondTabDataDTO
						.setStructure(historiqueAffectationCartePlafondEntity.getStructure());
				historiqueAffectationCartePlafondTabDataDTO
						.setTypeCarburant(historiqueAffectationCartePlafondEntity.getTypeCarburant());
				historiqueAffectationCartePlafondTabDataDTO
						.setValidated(historiqueAffectationCartePlafondEntity.isValidated());
				if (historiqueAffectationCartePlafondEntity.getCartePlafond() != null) {
					historiqueAffectationCartePlafondTabDataDTO
							.setIdCarte(historiqueAffectationCartePlafondEntity.getCartePlafond().getId());
				}
				historiqueAffectationCartePlafondTabDataDTOS.add(historiqueAffectationCartePlafondTabDataDTO);

			});
		}

		return historiqueAffectationCartePlafondTabDataDTOS;
	}

	@Override
	public void deleteSelectedHistoriqueAffectationCartePlafond(Long id) {
		historiqueAffectationCartePlafondRepository.deleteById(id);
	}

	@Override
	public List<CartePlafondTabDataDTO> getListCartePlafond() {
		List<CartePlafondEntity> cartePlafondEntities = (List<CartePlafondEntity>) cartePlafondRepository.findAll();
		return this.loadCartePlafondTabData(cartePlafondEntities);
	}

	@Override
	public void createNewDeclarationPerteCartePlafond(DeclarationPerteCartePlafondDTO declarationPerteCartePlafondDTO) {
		ModelMapper modelMapper = new ModelMapper();
		CartePlafondEntity carteplafondEntity = cartePlafondRepository
				.findById(declarationPerteCartePlafondDTO.getIdCarteplafond()).get();
		UserEntity userEntity = userRepository.findById(declarationPerteCartePlafondDTO.getIdUser()).get();
		DeclarationPerteCartePlafondEntity declarationPerteCartePlafondEntityy = modelMapper
				.map(declarationPerteCartePlafondDTO, DeclarationPerteCartePlafondEntity.class);
		declarationPerteCartePlafondEntityy.setCarteplafond(carteplafondEntity);
		declarationPerteCartePlafondEntityy.setUser(userEntity);
		declarationPerteCartePlafondRepository.save(declarationPerteCartePlafondEntityy);
	}

	private List<CartePlafondTabDataDTO> loadCartePlafondTabData(List<CartePlafondEntity> cartePlafondEntities) {
		List<CartePlafondTabDataDTO> cartePlafondTabDataDTOS = new ArrayList<>();
		if (!cartePlafondEntities.isEmpty()) {
			cartePlafondEntities.forEach(cartePlafondEntity -> {
				CartePlafondTabDataDTO cartePlafondTabDataDTO = new CartePlafondTabDataDTO();
				cartePlafondTabDataDTO.setId(cartePlafondEntity.getId());
				// cartePlafondTabDataDTO.setIdVehicule(cartePlafondEntity.getVehicule().getId());
				cartePlafondTabDataDTO.setMontant(cartePlafondEntity.getMontant());
				cartePlafondTabDataDTO.setDateValiditee(cartePlafondEntity.getDateValiditee());
				cartePlafondTabDataDTO.setNumeroCarte(cartePlafondEntity.getNumeroCarte());
				cartePlafondTabDataDTO.setTypeCarburant(cartePlafondEntity.getTypeCarburant());
				cartePlafondTabDataDTO.setActivated(cartePlafondEntity.isActivated());
				cartePlafondTabDataDTO.setStatus(cartePlafondEntity.getStatus());
				cartePlafondTabDataDTOS.add(cartePlafondTabDataDTO);
			});
		}
		return cartePlafondTabDataDTOS;
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
					selectVehiculeDTO.setIdBeneficiaire(vehiculeEntity.getBeneficiaire().getId());
					selectVehiculeDTO.setNomBeneficiaire(vehiculeEntity.getBeneficiaire().getNom());
					selectVehiculeDTO.setMatriculeBeneficiaire(vehiculeEntity.getBeneficiaire().getMatricule());
				}
				selectVehiculeDTOS.add(selectVehiculeDTO);
			});
		}
		return selectVehiculeDTOS;
	}

	private List<AffectationCartePlafondTabDataDTO> loadAffectationCartePlafondTabDataDTO(
			List<CartePlafondEntity> cartePlafondEntities) {
		List<AffectationCartePlafondTabDataDTO> affectationCartePlafondTabDataDTOS = new ArrayList<>();
		if (!cartePlafondEntities.isEmpty()) {
			cartePlafondEntities.forEach(cartePlafondEntity -> {
				AffectationCartePlafondTabDataDTO affectationCartePlafondTabDataDTO = new AffectationCartePlafondTabDataDTO();
				affectationCartePlafondTabDataDTO.setIdCartePlafond(cartePlafondEntity.getId());
				affectationCartePlafondTabDataDTO.setIdVehicule(cartePlafondEntity.getVehicule().getId());
				affectationCartePlafondTabDataDTO.setNumeroCarte(cartePlafondEntity.getNumeroCarte());
				affectationCartePlafondTabDataDTO.setMontant(cartePlafondEntity.getMontant());
				if (cartePlafondEntity.getVehicule().getBeneficiaire() != null) {
					affectationCartePlafondTabDataDTO
							.setIdBeneficiaire(cartePlafondEntity.getVehicule().getBeneficiaire().getId());
					affectationCartePlafondTabDataDTO.setMatriculeBeneficiaire(
							cartePlafondEntity.getVehicule().getBeneficiaire().getMatricule());
					affectationCartePlafondTabDataDTO
							.setNomBeneficiaire(cartePlafondEntity.getVehicule().getBeneficiaire().getNom());
				}
				affectationCartePlafondTabDataDTO.setNumeroPlaque(cartePlafondEntity.getVehicule().getNumeroPlaque());
				affectationCartePlafondTabDataDTO.setTypeCarburant(cartePlafondEntity.getTypeCarburant());
				affectationCartePlafondTabDataDTOS.add(affectationCartePlafondTabDataDTO);
			});
		}
		return affectationCartePlafondTabDataDTOS;
	}

	@Override
	public void modifySelectedDeclarationPerteCartePlafond(
			DeclarationPerteCartePlafondDTO declarationPerteCartePlafondDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCartePlafondEntity declarationPerteCartePlafondEntity = modelMapper
				.map(declarationPerteCartePlafondDTO, DeclarationPerteCartePlafondEntity.class);
		declarationPerteCartePlafondRepository.save(declarationPerteCartePlafondEntity);
	}

	@Override
	public void createNewDemandeAnnulationCarteCarburant(
			DemandeAnnulationCartePlafondDTO demandeAnnulationCartePlafondDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCartePlafondEntity demandeAnnulationCartePlafondEntity = modelMapper
				.map(demandeAnnulationCartePlafondDTO, DemandeAnnulationCartePlafondEntity.class);
		demandeAnnulationCartePlafondEntity.setIdCard(demandeAnnulationCartePlafondDTO.getIdCard());
		demandeAnnulationCartePlafondEntity.setDateDemande(LocalDate.now());
		demandeAnnulationCartePlafondRepository.save(demandeAnnulationCartePlafondEntity);
	}

	@Override
	public List<DeclarationPerteCartePlafondDTO> getPaginationdeclarationperteCartePlafond(int page, int limit,
			String confirmed) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<DeclarationPerteCartePlafondEntity> declarationPerteCartePlafondEntity = new ArrayList<>();
		if (confirmed.equals("true")) {
			Page<DeclarationPerteCartePlafondEntity> DeclarationPerteCartePlafondEntities = declarationPerteCartePlafondRepository
					.findByConfirmed(true, pageable);
			declarationPerteCartePlafondEntity = DeclarationPerteCartePlafondEntities.getContent();

		} else if (confirmed.equals("false")) {
			Page<DeclarationPerteCartePlafondEntity> DeclarationPerteCartePlafondEntities = declarationPerteCartePlafondRepository
					.findByConfirmed(false, pageable);
			declarationPerteCartePlafondEntity = DeclarationPerteCartePlafondEntities.getContent();
		} else {
			Page<DeclarationPerteCartePlafondEntity> DeclarationPerteCartePlafondEntities = declarationPerteCartePlafondRepository
					.findAll(pageable);
			declarationPerteCartePlafondEntity = DeclarationPerteCartePlafondEntities.getContent();
		}
		return this.loadPerteCartePlafond(declarationPerteCartePlafondEntity);
	}

	private List<DeclarationPerteCartePlafondDTO> loadPerteCartePlafond(
			List<DeclarationPerteCartePlafondEntity> declarationPerteCartePlafondEntities) {
		List<DeclarationPerteCartePlafondDTO> declarationPerteCartePlafondDTO = new ArrayList<>();
		if (!declarationPerteCartePlafondEntities.isEmpty()) {
			declarationPerteCartePlafondEntities.forEach(declarationPerteCartePlafondEntity -> {
				DeclarationPerteCartePlafondDTO DeclarationPerteCartePlafondDTOs = new DeclarationPerteCartePlafondDTO();
				DeclarationPerteCartePlafondDTOs.setId(declarationPerteCartePlafondEntity.getId());
				DeclarationPerteCartePlafondDTOs.setDatePerte(declarationPerteCartePlafondEntity.getDatePerte());
				DeclarationPerteCartePlafondDTOs
						.setDateNaissanceDeclarant(declarationPerteCartePlafondEntity.getDateNaissanceDeclarant());
				DeclarationPerteCartePlafondDTOs
						.setLieuNaissanceDeclarant(declarationPerteCartePlafondEntity.getLieuNaissanceDeclarant());
				DeclarationPerteCartePlafondDTOs
						.setCirconstances(declarationPerteCartePlafondEntity.getCirconstances());
				DeclarationPerteCartePlafondDTOs.setNomDeclarant(declarationPerteCartePlafondEntity.getNomDeclarant());
				DeclarationPerteCartePlafondDTOs
						.setNumeroCINDeclarant(declarationPerteCartePlafondEntity.getNumeroCINDeclarant());
				DeclarationPerteCartePlafondDTOs
						.setPrenomDeclarant(declarationPerteCartePlafondEntity.getPrenomDeclarant());
				DeclarationPerteCartePlafondDTOs
						.setSexeDeclarant(declarationPerteCartePlafondEntity.getSexeDeclarant());
				DeclarationPerteCartePlafondDTOs.setLieuPerte(declarationPerteCartePlafondEntity.getLieuPerte());
				DeclarationPerteCartePlafondDTOs
						.setTypeDeclarant(declarationPerteCartePlafondEntity.getTypeDeclarant());
				if (declarationPerteCartePlafondEntity.getCarteplafond() != null) {
					DeclarationPerteCartePlafondDTOs
							.setNumeroCarte(declarationPerteCartePlafondEntity.getCarteplafond().getNumeroCarte());
				}
				declarationPerteCartePlafondDTO.add(DeclarationPerteCartePlafondDTOs);

			});
		}

		return declarationPerteCartePlafondDTO;
	}

	@Override
	public Long getTotalNumberdeclarationPerteCartePlafond() {
		PageRequest pageable = PageRequest.of(0, 8);
		return declarationPerteCartePlafondRepository.findAll(pageable).getTotalElements();
	}
}
