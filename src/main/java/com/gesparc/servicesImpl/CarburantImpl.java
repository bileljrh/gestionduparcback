package com.gesparc.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gesparc.entities.EtatMensuelEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.Details2DistributionEntity;
import com.gesparc.entities.carburant.Distribution2FonctionEntity;
import com.gesparc.entities.carburant.Distribution2ServiceEntity;
import com.gesparc.entities.carburant.RechargeComplementaire;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.RechargeCarburantCompensation;
import com.gesparc.entities.carburant.HistoriqueOperationRecharge;
import com.gesparc.entities.carburant.HistoriqueRecahrgeComplementaire;
import com.gesparc.entities.carburant.HistoriqueRechargeCarburantCompensation;
import com.gesparc.entities.carburant.RechargeSousCompte;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.DeclarationPerteCartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.DemandeAnnulationCartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueRechargeQuotaMensuelEntity;
import com.gesparc.entities.carburant.cartePlafond.RechargeQuotaMensuelEntity;
import com.gesparc.entities.stock.ParcTransfertEntity;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.HistoriqueRegulation;
import com.gesparc.repositories.*;
import com.gesparc.services.Carburant;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.DeclarationPerteCartePlafondDTO;
import com.gesparc.sharedDTO.DemandeAnnulationCartePlafondDTO;
import com.gesparc.sharedDTO.Details2DistributionDTO;
import com.gesparc.sharedDTO.EtatMensuelDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.carburant.*;
import com.gesparc.sharedDTO.stock.ParcTransfertDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@EnableAutoConfiguration
@Service
public class CarburantImpl implements Carburant {

	@Autowired
	HistoriqueRechargeQuotaMensuelRepository rechargeHistoriqueQuotaMensuelRepository ;
	
	@Autowired
    RechargeQuotaMensuelRepository rechargeQuotaMensuelRepository ;
	
	@Autowired
    DemandeRechargeSousCompteRepository demandeRechargeSousCompteRepository ;
    
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
    DeclarationPerteCarteJockerRepository declarationPerteCarteJockerRepository;

    @Autowired
    CarteJockerRepository carteJockerRepository;
    
    @Autowired
    HistoriqueOperationRechargeRepository  historiqueRepository ;

    
    @Autowired
    HistoriqueRecahrgeComplementaireRepository  historiqueRecahrgeComplementaireRepository ;

    
    @Autowired
    HistoriqueRechargeCarburantCompensationRepository  historiqueRechargeCarburantCompensationRepository ;

	@Autowired
	RechargeComplementaireRepository rechargeComplementaireRepository;

	@Autowired
	RechargeCarburantCompensationRepository rechargeCarburantCompensationRepository;
	
	@Override
	public List<ListBeneficiairesInternesDTO> getListBeneficiairesInternes() {
		List<BeneficiaireEntity> beneficiaireEntities = this.beneficiaireRepository
				.getListBeneficiairesByType("Interne");
		List<ListBeneficiairesInternesDTO> listBeneficiairesInternesDTOS = new ArrayList<>();
		beneficiaireEntities.forEach(beneficiaireEntity -> {
			Long idBeneficiaire = beneficiaireEntity.getId();
			String matriculeBeneficiaire = beneficiaireEntity.getMatricule();
			String nomBeneficiaire = beneficiaireEntity.getNom();
			Long idVehicule;
			String numeroPlaque;
			String energie;
			String structure;
			Float pourcentageVehicule;
			String typeCarburant;
			if (beneficiaireEntity.getVehicules() == null) {
				idVehicule = null;
				numeroPlaque = null;
				energie = null;
				structure = null;
				pourcentageVehicule = null;
				typeCarburant = null;
			} else {
				idVehicule = beneficiaireEntity.getVehicules().get(0).getId();
				numeroPlaque = beneficiaireEntity.getVehicules().get(0).getNumeroPlaque();
				energie = beneficiaireEntity.getVehicules().get(0).getEnergie().getDescription();
				pourcentageVehicule = beneficiaireEntity.getVehicules().get(0).getPourcentageVehicule();
				typeCarburant = beneficiaireEntity.getVehicules().get(0).getEnergie().getDescription();
			}
			ListBeneficiairesInternesDTO listBeneficiairesInternesDTO = new ListBeneficiairesInternesDTO();
			listBeneficiairesInternesDTOS.add(listBeneficiairesInternesDTO);
		});
		return listBeneficiairesInternesDTOS;
	}

	@Override
	public List<ListBeneficiairesExternesDTO> getListBeneficiairesExternes() {
		List<BeneficiaireEntity> beneficiaireEntities = this.beneficiaireRepository
				.getListBeneficiairesByType("Externe");
		List<ListBeneficiairesExternesDTO> listBeneficiairesExternesDTOS = new ArrayList<>();
		beneficiaireEntities.forEach(beneficiaireEntity -> {
			ListBeneficiairesExternesDTO listBeneficiairesExternesDTO = new ListBeneficiairesExternesDTO(
					beneficiaireEntity.getId(), beneficiaireEntity.getMatricule(), beneficiaireEntity.getNom());
			listBeneficiairesExternesDTOS.add(listBeneficiairesExternesDTO);
		});
		return listBeneficiairesExternesDTOS;
	}

	@Override
	public List<DistributionFonctionTabDataDTO> getListDistributionsCarburant2FonctionBySource(String source) {
		List<Distribution2FonctionEntity> distribution2FonctionEntities = distribution2FonctionRepository
				.getListDistributionsCarburant2FonctionByType(source);
		List<DistributionFonctionTabDataDTO> distributionFonctionTabDataDTOS = new ArrayList<>();
		if (distribution2FonctionEntities.size() > 0) {
			distribution2FonctionEntities.forEach(distribution2FonctionEntity -> {
				ModelMapper modelMapper = new ModelMapper();
				Long idVehicule;
				Long idBeneficiaire;
				String matriculeBeneficiaire;
				String nomBeneficiaire;
				String numeroPlaque;
				Details2DistributionDTO details2Distribution;
				if (distribution2FonctionEntity.getDetails2distributions() == null) {
					details2Distribution = null;
				} else {
					details2Distribution = modelMapper.map(distribution2FonctionEntity.getDetails2distributions(),
							Details2DistributionDTO.class);
				}
				if (distribution2FonctionEntity.getBeneficiaire() != null) {
					idBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getId();
					matriculeBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getMatricule();
					nomBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getNom();
					if (distribution2FonctionEntity.getBeneficiaire().getVehicules().size() > 0) {
						idVehicule = distribution2FonctionEntity.getBeneficiaire().getVehicules().get(0).getId();
						numeroPlaque = distribution2FonctionEntity.getBeneficiaire().getVehicules().get(0)
								.getNumeroPlaque();
					} else {
						idVehicule = null;
						numeroPlaque = null;
					}
				} else {
					idVehicule = null;
					idBeneficiaire = null;
					matriculeBeneficiaire = null;
					nomBeneficiaire = null;
					numeroPlaque = null;
				}
				DistributionFonctionTabDataDTO distributionFonctionTabDataDTO = new DistributionFonctionTabDataDTO(
						idVehicule, idBeneficiaire, distribution2FonctionEntity.getId(), matriculeBeneficiaire,
						nomBeneficiaire, numeroPlaque, distribution2FonctionEntity.getQuantiteCarburant(),
						distribution2FonctionEntity.getNombre2Bons(), distribution2FonctionEntity.getQuota(),
						distribution2FonctionEntity.getMoisDistribution(),
						distribution2FonctionEntity.getSourceCarburant(), details2Distribution);
				distributionFonctionTabDataDTOS.add(distributionFonctionTabDataDTO);
			});
		}
		return distributionFonctionTabDataDTOS;
	}

	@Override
	public void modifyOneDistributionsCarburant2Fonction(
			ModifyDistribution2FonctionDTO modifyDistribution2FonctionDTO) {
		Distribution2FonctionEntity distribution2FonctionEntity = distribution2FonctionRepository
				.findById(modifyDistribution2FonctionDTO.getIdDistribution()).get();
		if (distribution2FonctionEntity.getBeneficiaire().getId() != modifyDistribution2FonctionDTO
				.getIdBeneficiaire()) {
			BeneficiaireEntity ancienBeneficiaireEntity = distribution2FonctionEntity.getBeneficiaire();
			ancienBeneficiaireEntity.getDistribution2Fonction().remove(distribution2FonctionEntity);
			beneficiaireRepository.save(ancienBeneficiaireEntity);
			BeneficiaireEntity newBeneficiaireEntity = beneficiaireRepository
					.findById(modifyDistribution2FonctionDTO.getIdBeneficiaire()).get();
			newBeneficiaireEntity.getDistribution2Fonction().add(distribution2FonctionEntity);
			distribution2FonctionEntity.setBeneficiaire(newBeneficiaireEntity);
			beneficiaireRepository.save(newBeneficiaireEntity);
		}
		distribution2FonctionEntity.setMoisDistribution(modifyDistribution2FonctionDTO.getMoisDistribution());
		distribution2FonctionEntity.setNombre2Bons(modifyDistribution2FonctionDTO.getNombre2Bons());
		distribution2FonctionEntity.setQuantiteCarburant(modifyDistribution2FonctionDTO.getQuantiteCarburant());
		distribution2FonctionEntity.setSourceCarburant(modifyDistribution2FonctionDTO.getSourceCarburant());
		distribution2FonctionEntity.setQuota(modifyDistribution2FonctionDTO.getQuota());
		if (modifyDistribution2FonctionDTO.getDetails2Distribution() != null) {
			Details2DistributionEntity details2DistributionEntity = distribution2FonctionEntity
					.getDetails2distributions();
			details2DistributionEntity
					.setDateMission(modifyDistribution2FonctionDTO.getDetails2Distribution().getDateMission());
			details2DistributionEntity
					.setIndexKmDepart(modifyDistribution2FonctionDTO.getDetails2Distribution().getIndexKmDepart());
			details2DistributionEntity
					.setIndexKmArrivee(modifyDistribution2FonctionDTO.getDetails2Distribution().getIndexKmArrivee());
			details2DistributionEntity.setDistanceParcouru(
					modifyDistribution2FonctionDTO.getDetails2Distribution().getDistanceParcouru());
			details2DistributionEntity.setDroit2Recomponse(
					modifyDistribution2FonctionDTO.getDetails2Distribution().getDroit2Recomponse());
			details2DistributionEntity.setQuantiteMoisPrecedant(
					modifyDistribution2FonctionDTO.getDetails2Distribution().getQuantiteMoisPrecedant());
			details2DistributionEntity.setTaux2Consommation(
					modifyDistribution2FonctionDTO.getDetails2Distribution().getTaux2Consommation());
			details2DistributionEntity
					.setNombre2Bons(modifyDistribution2FonctionDTO.getDetails2Distribution().getNombre2Bons());
			details2DistributionEntity.setReste(modifyDistribution2FonctionDTO.getDetails2Distribution().getReste());
			details2DistributionEntity
					.setDestination(modifyDistribution2FonctionDTO.getDetails2Distribution().getDestination());
			details2DistributionEntity.setDistribution2Fonction(distribution2FonctionEntity);
			details2DistributionRepository.save(details2DistributionEntity);
		}
		distribution2FonctionEntity.getBeneficiaire().setDistribution2Fonction(null);
		distribution2FonctionRepository.save(distribution2FonctionEntity);
	}

	@Override
	public void createNewDistributionCarburant2Fonction(NewDistribution2FonctionDTO newDistribution2FonctionDTO) {
		BeneficiaireEntity beneficiaireEntity = this.beneficiaireRepository
				.findById(newDistribution2FonctionDTO.getIdBeneficiaire()).get();
		Distribution2FonctionEntity distribution2FonctionEntity = new Distribution2FonctionEntity();
		distribution2FonctionEntity.setQuota(newDistribution2FonctionDTO.getQuota());
		distribution2FonctionEntity.setMoisDistribution(newDistribution2FonctionDTO.getMoisDistribution());
		distribution2FonctionEntity.setNombre2Bons(newDistribution2FonctionDTO.getNombre2Bons());
		distribution2FonctionEntity.setQuantiteCarburant(newDistribution2FonctionDTO.getQuantiteCarburant());
		distribution2FonctionEntity.setSourceCarburant(newDistribution2FonctionDTO.getSourceCarburant());
		beneficiaireEntity.getDistribution2Fonction().add(distribution2FonctionEntity);
		distribution2FonctionEntity.setBeneficiaire(beneficiaireEntity);
		if (newDistribution2FonctionDTO.getDetails2Distribution() != null) {
			Details2DistributionEntity details2DistributionEntity = new Details2DistributionEntity();
			details2DistributionEntity
					.setDateMission(newDistribution2FonctionDTO.getDetails2Distribution().getDateMission());
			details2DistributionEntity
					.setDestination(newDistribution2FonctionDTO.getDetails2Distribution().getDestination());
			details2DistributionEntity
					.setIndexKmDepart(newDistribution2FonctionDTO.getDetails2Distribution().getIndexKmDepart());
			details2DistributionEntity
					.setIndexKmArrivee(newDistribution2FonctionDTO.getDetails2Distribution().getIndexKmArrivee());
			details2DistributionEntity
					.setDistanceParcouru(newDistribution2FonctionDTO.getDetails2Distribution().getDistanceParcouru());
			details2DistributionEntity
					.setDroit2Recomponse(newDistribution2FonctionDTO.getDetails2Distribution().getDroit2Recomponse());
			details2DistributionEntity.setQuantiteMoisPrecedant(
					newDistribution2FonctionDTO.getDetails2Distribution().getQuantiteMoisPrecedant());
			details2DistributionEntity
					.setTaux2Consommation(newDistribution2FonctionDTO.getDetails2Distribution().getTaux2Consommation());
			details2DistributionEntity
					.setNombre2Bons(newDistribution2FonctionDTO.getDetails2Distribution().getNombre2Bons());
			details2DistributionEntity.setReste(newDistribution2FonctionDTO.getDetails2Distribution().getReste());
			details2DistributionEntity.setDistribution2Fonction(distribution2FonctionEntity);
			this.details2DistributionRepository.save(details2DistributionEntity);
		}
		this.distribution2FonctionRepository.save(distribution2FonctionEntity);
	}

	@Override
	public void deleteOneDistributionCarburant2Fonction(Long id) {
		this.distribution2FonctionRepository.deleteById(id);
	}

	@Override
	public List<EtatMensuelDTO> getAllEtatMensuels() {
		ModelMapper modelMapper = new ModelMapper();
		List<EtatMensuelEntity> etatMensuelEntities = (List<EtatMensuelEntity>) this.etatMensuelRepository.findAll();
		List<EtatMensuelDTO> etatMensuelDTOS = new ArrayList<>();
		if (etatMensuelEntities.size() > 0) {
			etatMensuelEntities.forEach(etatMensuelEntity -> {
				etatMensuelDTOS.add(modelMapper.map(etatMensuelEntity, EtatMensuelDTO.class));
			});
		}
		return etatMensuelDTOS;
	}

	@Override
	public void confirmOneEtatMensuels(Long id) {
		EtatMensuelEntity etatMensuelEntity = this.etatMensuelRepository.findById(id).get();
		etatMensuelEntity.setConfirme(true);
		etatMensuelEntity.setValide(false);
		etatMensuelEntity.setBrouillon(false);
		etatMensuelRepository.save(etatMensuelEntity);
	}

	@Override
	public void unconfirmOneEtatMensuels(Long id) {
		EtatMensuelEntity etatMensuelEntity = this.etatMensuelRepository.findById(id).get();
		etatMensuelEntity.setConfirme(false);
		etatMensuelEntity.setValide(true);
		etatMensuelEntity.setBrouillon(false);
		etatMensuelRepository.save(etatMensuelEntity);
	}

	@Override
	public void validateOneEtatMensuels(Long id) {
		EtatMensuelEntity etatMensuelEntity = this.etatMensuelRepository.findById(id).get();
		etatMensuelEntity.setValide(true);
		etatMensuelEntity.setConfirme(false);
		etatMensuelEntity.setBrouillon(false);
		etatMensuelRepository.save(etatMensuelEntity);
	}

	@Override
	public void unvalidateOneEtatMensuels(Long id) {
		EtatMensuelEntity etatMensuelEntity = this.etatMensuelRepository.findById(id).get();
		etatMensuelEntity.setValide(false);
		etatMensuelEntity.setConfirme(true);
		etatMensuelEntity.setBrouillon(false);
		etatMensuelRepository.save(etatMensuelEntity);
	}

	@Override
	public void modifyOneEtatMensuels(ModifyEtatMensuelDTO modifyEtatMensuelDTO) {
		EtatMensuelEntity etatMensuelEntity = etatMensuelRepository.findById(modifyEtatMensuelDTO.getIdEtatMensuel())
				.get();
		if (etatMensuelEntity.getBeneficiaire().getId() != modifyEtatMensuelDTO.getIdBeneficiaire()) {
			BeneficiaireEntity ancienBeneficiaireEntity = etatMensuelEntity.getBeneficiaire();
			ancienBeneficiaireEntity.getEtatMensuels().remove(etatMensuelEntity);
			beneficiaireRepository.save(ancienBeneficiaireEntity);
			BeneficiaireEntity newBeneficiaireEntity = beneficiaireRepository
					.findById(modifyEtatMensuelDTO.getIdBeneficiaire()).get();
			newBeneficiaireEntity.getEtatMensuels().add(etatMensuelEntity);
			etatMensuelEntity.setBeneficiaire(newBeneficiaireEntity);
			beneficiaireRepository.save(newBeneficiaireEntity);
		}
		etatMensuelEntity.setQuantiteRestantee(modifyEtatMensuelDTO.getQuantiteRestantee());
		etatMensuelEntity.setQuantiteDemandee(modifyEtatMensuelDTO.getQuantiteDemandee());
		etatMensuelEntity.setQuantiteAccordee(modifyEtatMensuelDTO.getQuantiteAccordee());
		etatMensuelEntity.setQuantiteCarburant(modifyEtatMensuelDTO.getQuantiteCarburant());
		etatMensuelEntity.setQuantiteRestantee(modifyEtatMensuelDTO.getQuantiteRestantee());
		etatMensuelEntity.setQuantiteRetournee(modifyEtatMensuelDTO.getQuantiteRetournee());
		etatMensuelEntity.setIndexFinMois(modifyEtatMensuelDTO.getIndexFinMois());
		etatMensuelEntity.setIndexFinMoisPrecedant(modifyEtatMensuelDTO.getIndexFinMoisPrecedant());
		etatMensuelEntity.setNombreHeuresTravail(modifyEtatMensuelDTO.getNombreHeuresTravail());
		etatMensuelEntity.setDistanceParcourus(modifyEtatMensuelDTO.getDistanceParcourus());
		etatMensuelEntity.setPourcentageConsommation(modifyEtatMensuelDTO.getPourcentageConsommation());
		etatMensuelEntity.setJourOuvrables(modifyEtatMensuelDTO.getJourOuvrables());
		etatMensuelEntity.setJours2Production(modifyEtatMensuelDTO.getJours2Production());
		etatMensuelEntity.setJours2Dispense(modifyEtatMensuelDTO.getJours2Dispense());
		etatMensuelEntity.setJours2Repos(modifyEtatMensuelDTO.getJours2Repos());
		etatMensuelEntity.setBrouillon(modifyEtatMensuelDTO.isBrouillon());
		etatMensuelEntity.setConfirme(modifyEtatMensuelDTO.isConfirme());
		etatMensuelEntity.setValide(modifyEtatMensuelDTO.isValide());
		etatMensuelRepository.save(etatMensuelEntity);
	}

	@Override
	public void createNewEtatMensuel(NewEtatMensuelDTO newEtatMensuelDTO) {
		EtatMensuelEntity etatMensuelEntity = new EtatMensuelEntity();
		BeneficiaireEntity beneficiaireEntity = beneficiaireRepository.findById(newEtatMensuelDTO.getIdBeneficiaire())
				.get();
		etatMensuelEntity.setQuantiteRestantee(newEtatMensuelDTO.getQuantiteRestantee());
		etatMensuelEntity.setQuantiteDemandee(newEtatMensuelDTO.getQuantiteDemandee());
		etatMensuelEntity.setQuantiteAccordee(newEtatMensuelDTO.getQuantiteAccordee());
		etatMensuelEntity.setQuantiteCarburant(newEtatMensuelDTO.getQuantiteCarburant());
		etatMensuelEntity.setQuantiteRetournee(newEtatMensuelDTO.getQuantiteRetournee());
		etatMensuelEntity.setIndexFinMois(newEtatMensuelDTO.getIndexFinMois());
		etatMensuelEntity.setIndexFinMoisPrecedant(newEtatMensuelDTO.getIndexFinMoisPrecedant());
		etatMensuelEntity.setNombreHeuresTravail(newEtatMensuelDTO.getNombreHeuresTravail());
		etatMensuelEntity.setDistanceParcourus(newEtatMensuelDTO.getDistanceParcourus());
		etatMensuelEntity.setPourcentageConsommation(newEtatMensuelDTO.getPourcentageConsommation());
		etatMensuelEntity.setJourOuvrables(newEtatMensuelDTO.getJourOuvrables());
		etatMensuelEntity.setJours2Production(newEtatMensuelDTO.getJours2Production());
		etatMensuelEntity.setJours2Dispense(newEtatMensuelDTO.getJours2Dispense());
		etatMensuelEntity.setJours2Repos(newEtatMensuelDTO.getJours2Repos());
		etatMensuelEntity.setBrouillon(newEtatMensuelDTO.isBrouillon());
		etatMensuelEntity.setConfirme(newEtatMensuelDTO.isConfirme());
		etatMensuelEntity.setValide(newEtatMensuelDTO.isValide());
		etatMensuelEntity.setMoisEtat(newEtatMensuelDTO.getMoisEtat());
		beneficiaireEntity.getEtatMensuels().add(etatMensuelEntity);
		etatMensuelEntity.setBeneficiaire(beneficiaireEntity);
		etatMensuelRepository.save(etatMensuelEntity);
	}

	@Override
	public void deleteOneEtatMensuels(Long id) {
		etatMensuelRepository.deleteById(id);
	}

	@Override
	public List<EtatMensuelDTO> getEtatMensuelsByMonthAndType(String type, String month) {
		ModelMapper modelMapper = new ModelMapper();
		List<EtatMensuelDTO> etatMensuelDTOS = new ArrayList<>();
		boolean brouillon;
		boolean confirme;
		boolean valide;
		if (type.equals("Validé")) {
			brouillon = false;
			confirme = false;
			valide = true;
		} else if (type.equals("Confirmé")) {
			brouillon = false;
			confirme = true;
			valide = false;
		} else if (type.equals("Brouillon")) {
			brouillon = true;
			confirme = false;
			valide = false;
		} else {
			brouillon = false;
			confirme = false;
			valide = false;
		}
		if ((type.equals("undefined")) && (month.equals("undefined"))) {
			List<EtatMensuelEntity> etatMensuelEntities = (List<EtatMensuelEntity>) etatMensuelRepository.findAll();
			if (etatMensuelEntities.size() > 0) {
				etatMensuelEntities.forEach(etatMensuelEntity -> {
					EtatMensuelDTO etatMensuelDTO = modelMapper.map(etatMensuelEntity, EtatMensuelDTO.class);
					etatMensuelDTOS.add(etatMensuelDTO);
				});
			}
		} else if ((!type.equals("undefined")) && (month.equals("undefined"))) {
			List<EtatMensuelEntity> etatMensuelEntities = etatMensuelRepository.getEtatMensuelByType(brouillon,
					confirme, valide);
			if (etatMensuelEntities.size() > 0) {
				etatMensuelEntities.forEach(etatMensuelEntity -> {
					EtatMensuelDTO etatMensuelDTO = modelMapper.map(etatMensuelEntity, EtatMensuelDTO.class);
					etatMensuelDTOS.add(etatMensuelDTO);
				});
			}
		} else if ((type.equals("undefined")) && (!month.equals("undefined"))) {
			LocalDate localDate = LocalDate.parse(month);
			List<EtatMensuelEntity> etatMensuelEntities = etatMensuelRepository
					.getEtatMensuelByDateSearching(localDate.minusDays(1), localDate.plusDays(1));
			if (etatMensuelEntities.size() > 0) {
				etatMensuelEntities.forEach(etatMensuelEntity -> {
					EtatMensuelDTO etatMensuelDTO = modelMapper.map(etatMensuelEntity, EtatMensuelDTO.class);
					etatMensuelDTOS.add(etatMensuelDTO);
				});
			}
		} else {
			LocalDate localDate = LocalDate.parse(month);
			List<EtatMensuelEntity> etatMensuelEntities = etatMensuelRepository.getEtatMensuelByDateSearchingAndType(
					localDate.minusDays(5), localDate.plusDays(5), brouillon, confirme, valide);
			if (etatMensuelEntities.size() > 0) {
				etatMensuelEntities.forEach(etatMensuelEntity -> {
					EtatMensuelDTO etatMensuelDTO = modelMapper.map(etatMensuelEntity, EtatMensuelDTO.class);
					etatMensuelDTOS.add(etatMensuelDTO);
				});
			}
		}
		return etatMensuelDTOS;
	}

	@Override
	public List<DistributionFonctionTabDataDTO> getDistributionsCarburant2FonctionByMonthAndType(String type,
			String month) {
		List<DistributionFonctionTabDataDTO> distributionFonctionTabDataDTOS = new ArrayList<>();
		if ((month.equals("undefined"))) {
			List<Distribution2FonctionEntity> distribution2FonctionEntities = distribution2FonctionRepository
					.getListDistributionsCarburant2FonctionByType(type);
			if (distribution2FonctionEntities.size() > 0) {
				distribution2FonctionEntities.forEach(distribution2FonctionEntity -> {
					ModelMapper modelMapper = new ModelMapper();
					Long idVehicule;
					Long idBeneficiaire;
					String matriculeBeneficiaire;
					String nomBeneficiaire;
					String numeroPlaque;
					Details2DistributionDTO details2Distribution;
					if (distribution2FonctionEntity.getDetails2distributions() == null) {
						details2Distribution = null;
					} else {
						details2Distribution = modelMapper.map(distribution2FonctionEntity.getDetails2distributions(),
								Details2DistributionDTO.class);
					}
					if (distribution2FonctionEntity.getBeneficiaire() != null) {
						idBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getId();
						matriculeBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getMatricule();
						nomBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getNom();
						if (distribution2FonctionEntity.getBeneficiaire().getVehicules().size() > 0) {
							idVehicule = distribution2FonctionEntity.getBeneficiaire().getVehicules().get(0).getId();
							numeroPlaque = distribution2FonctionEntity.getBeneficiaire().getVehicules().get(0)
									.getNumeroPlaque();
						} else {
							idVehicule = null;
							numeroPlaque = null;
						}
					} else {
						idVehicule = null;
						idBeneficiaire = null;
						matriculeBeneficiaire = null;
						nomBeneficiaire = null;
						numeroPlaque = null;
					}
					DistributionFonctionTabDataDTO distributionFonctionTabDataDTO = new DistributionFonctionTabDataDTO(
							idVehicule, idBeneficiaire, distribution2FonctionEntity.getId(), matriculeBeneficiaire,
							nomBeneficiaire, numeroPlaque, distribution2FonctionEntity.getQuantiteCarburant(),
							distribution2FonctionEntity.getNombre2Bons(), distribution2FonctionEntity.getQuota(),
							distribution2FonctionEntity.getMoisDistribution(),
							distribution2FonctionEntity.getSourceCarburant(), details2Distribution);
					distributionFonctionTabDataDTOS.add(distributionFonctionTabDataDTO);
				});
			}

		} else {
			LocalDate localDate = LocalDate.parse(month);
			List<Distribution2FonctionEntity> distribution2FonctionEntities = distribution2FonctionRepository
					.getListDistributionsCarburant2FonctionByCustomDateSearching(localDate.minusDays(5),
							localDate.plusDays(5), type);
			if (distribution2FonctionEntities.size() > 0) {
				distribution2FonctionEntities.forEach(distribution2FonctionEntity -> {
					ModelMapper modelMapper = new ModelMapper();
					Long idVehicule;
					Long idBeneficiaire;
					String matriculeBeneficiaire;
					String nomBeneficiaire;
					String numeroPlaque;
					Details2DistributionDTO details2Distribution;
					if (distribution2FonctionEntity.getDetails2distributions() == null) {
						details2Distribution = null;
					} else {
						details2Distribution = modelMapper.map(distribution2FonctionEntity.getDetails2distributions(),
								Details2DistributionDTO.class);
					}
					if (distribution2FonctionEntity.getBeneficiaire() != null) {
						idBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getId();
						matriculeBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getMatricule();
						nomBeneficiaire = distribution2FonctionEntity.getBeneficiaire().getNom();
						if (distribution2FonctionEntity.getBeneficiaire().getVehicules().size() > 0) {
							idVehicule = distribution2FonctionEntity.getBeneficiaire().getVehicules().get(0).getId();
							numeroPlaque = distribution2FonctionEntity.getBeneficiaire().getVehicules().get(0)
									.getNumeroPlaque();
						} else {
							idVehicule = null;
							numeroPlaque = null;
						}
					} else {
						idVehicule = null;
						idBeneficiaire = null;
						matriculeBeneficiaire = null;
						nomBeneficiaire = null;
						numeroPlaque = null;
					}
					DistributionFonctionTabDataDTO distributionFonctionTabDataDTO = new DistributionFonctionTabDataDTO(
							idVehicule, idBeneficiaire, distribution2FonctionEntity.getId(), matriculeBeneficiaire,
							nomBeneficiaire, numeroPlaque, distribution2FonctionEntity.getQuantiteCarburant(),
							distribution2FonctionEntity.getNombre2Bons(), distribution2FonctionEntity.getQuota(),
							distribution2FonctionEntity.getMoisDistribution(),
							distribution2FonctionEntity.getSourceCarburant(), details2Distribution);
					distributionFonctionTabDataDTOS.add(distributionFonctionTabDataDTO);
				});
			}
		}
		return distributionFonctionTabDataDTOS;
	}

	@Override
	public List<EtatMensuelDTO> getValidatedEtatMensuelList() {
		ModelMapper modelMapper = new ModelMapper();
		List<EtatMensuelDTO> etatMensuelDTOS = new ArrayList<>();
		List<EtatMensuelEntity> etatMensuelEntities = etatMensuelRepository.getEtatMensuelByType(false, false, true);
		if (etatMensuelEntities.size() > 0) {
			etatMensuelEntities.forEach(etatMensuelEntity -> {
				etatMensuelDTOS.add(modelMapper.map(etatMensuelEntity, EtatMensuelDTO.class));
			});
		}
		return etatMensuelDTOS;
	}

	@Override
	public void createNewDistribution2Service(Long id) {
		EtatMensuelEntity etatMensuelEntity = etatMensuelRepository.findById(id).get();
		Distribution2ServiceEntity distribution2ServiceEntity = new Distribution2ServiceEntity();
		distribution2ServiceEntity.setTypeDistributionService("Service");
		distribution2ServiceEntity.setEtatMensuel(etatMensuelEntity);
		etatMensuelEntity.setValide(false);
		etatMensuelEntity.setDistribution2Service(distribution2ServiceEntity);
		distribution2ServiceRepository.save(distribution2ServiceEntity);
		etatMensuelRepository.save(etatMensuelEntity);
	}

	@Override
	public List<DistributionServiceTabDataDTO> getListDistributionsCarburant2ServiceBySource(String source) {
		List<Distribution2ServiceEntity> distribution2ServiceEntities = distribution2ServiceRepository
				.getListDistributionsCarburant2ServiceByType(source);
		List<DistributionServiceTabDataDTO> distributionServiceTabDataDTOS = new ArrayList<>();
		String sourceCarburant = "Service";
		if (distribution2ServiceEntities.size() > 0) {
			distribution2ServiceEntities.forEach(distribution2ServiceEntity -> {
				DistributionServiceTabDataDTO distributionServiceTabDataDTO = new DistributionServiceTabDataDTO();
				distributionServiceTabDataDTOS.add(distributionServiceTabDataDTO);

			});
		}
		return distributionServiceTabDataDTOS;
	}

	@Override
	public void deleteOneDistributionCarburant2Service(Long id) {
		EtatMensuelEntity etatMensuelEntity = distribution2ServiceRepository.findById(id).get().getEtatMensuel();
		etatMensuelEntity.setValide(true);
		etatMensuelEntity.setDistribution2Service(null);
		etatMensuelRepository.save(etatMensuelEntity);
		distribution2ServiceRepository.deleteById(id);
	}

	@Override
	public List<VehiculeDTO> getListVehiculesService() {
		ModelMapper modelMapper = new ModelMapper();
		List<VehiculeDTO> vehiculeDTOS = new ArrayList<>();
		return vehiculeDTOS;
	}

	@Override
	public List<VehiculeDTO> getListVehiculesServiceWithNoCartePlafond() {
		ModelMapper modelMapper = new ModelMapper();
		List<VehiculeDTO> vehiculeDTOS = new ArrayList<>();
		return vehiculeDTOS;
	}

	@Override
	public void modifySelectedAffectationCartePlafond(Long idCartePlafond, Long idVehicule) {
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository.findById(idCartePlafond).get();
		VehiculeEntity previousVehiculeEntity = vehiculeRepository.findById(cartePlafondEntity.getVehicule().getId())
				.get();
		VehiculeEntity newVehiculeEntity = vehiculeRepository.findById(idVehicule).get();
		previousVehiculeEntity.setCartePlafond(null);
		vehiculeRepository.save(previousVehiculeEntity);
		cartePlafondEntity.setVehicule(newVehiculeEntity);
		newVehiculeEntity.setCartePlafond(cartePlafondEntity);
		cartePlafondRepository.save(cartePlafondEntity);
	}

	@Override
	public List<DeclarationPerteCartePlafondDTO> getListDeclarationPerteCartePlafondByConfirmation(
			boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCartePlafondDTO> declarationPerteCartePlafondDTOS = new ArrayList<>();
		List<DeclarationPerteCartePlafondEntity> declarationPerteCartePlafondEntities = declarationPerteCartePlafondRepository
				.getListDeclarationPerteCartePlafondByConfirmation(confirmation);
		if (!declarationPerteCartePlafondEntities.isEmpty()) {
			declarationPerteCartePlafondEntities.forEach(declarationPerteCartePlafondEntity -> {
				declarationPerteCartePlafondDTOS.add(
						modelMapper.map(declarationPerteCartePlafondEntity, DeclarationPerteCartePlafondDTO.class));
			});
		}
		return declarationPerteCartePlafondDTOS;
	}

	@Override
	public List<DeclarationPerteCarteJockerDTO> getListDeclarationPerteCarteJockerByConfirmation(boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteJockerDTO> declarationPerteCarteJockerDTOS = new ArrayList<>();
		List<DeclarationPerteCarteJockerEntity> declarationPerteCarteJockerEntities = declarationPerteCarteJockerRepository
				.getListDeclarationPerteCarteJockerByConfirmation(confirmation);
		if (!declarationPerteCarteJockerEntities.isEmpty()) {
			declarationPerteCarteJockerEntities.forEach(declarationPerteCarteJockerEntity -> {
				declarationPerteCarteJockerDTOS
						.add(modelMapper.map(declarationPerteCarteJockerEntity, DeclarationPerteCarteJockerDTO.class));
			});
		}
		return declarationPerteCarteJockerDTOS;
	}

	@Override
	public void deleteOneDeclarationPerteCartePlafond(Long id) {
		declarationPerteCartePlafondRepository.deleteById(id);
	}

	@Override
	public List<DemandeAnnulationCartePlafondDTO> getHistoriqueDemandeAnnulationCartePlafondByConfirmation(
			boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAnnulationCartePlafondDTO> demandeAnnulationCartePlafondDTOS = new ArrayList<>();
		List<DemandeAnnulationCartePlafondEntity> demandeAnnulationCartePlafondEntities = demandeAnnulationCartePlafondRepository
				.getHistoriqueDemandeAnnulationCartePlafondByConfirmation(confirmation);
		if (!demandeAnnulationCartePlafondEntities.isEmpty()) {
			demandeAnnulationCartePlafondEntities.forEach(demandeAnnulationCartePlafondEntity -> {
				demandeAnnulationCartePlafondDTOS.add(
						modelMapper.map(demandeAnnulationCartePlafondEntity, DemandeAnnulationCartePlafondDTO.class));
			});
		}
		return demandeAnnulationCartePlafondDTOS;
	}

	@Override
	public void deleteOneDemandeAnnulationCartePlafond(Long id) {
		demandeAnnulationCartePlafondRepository.deleteById(id);
	}

	@Override
	public void confirmOneDeclarationPerteCartePlafond(Long id) {
		DeclarationPerteCartePlafondEntity declarationPerteCartePlafondEntity = declarationPerteCartePlafondRepository
				.findById(id).get();
		declarationPerteCartePlafondEntity.setConfirmed(true);
		declarationPerteCartePlafondRepository.save(declarationPerteCartePlafondEntity);
	}

	@Override
	public void confirmSelectedDemandeAnnulationCarteCarburant(Long id) {
		DemandeAnnulationCartePlafondEntity demandeAnnulationCartePlafondEntity = demandeAnnulationCartePlafondRepository
				.findById(id).get();
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository
				.findFirstByNumeroCarte(demandeAnnulationCartePlafondEntity.getNumeroCarte());
		this.deleteSelectedCartePlafond(cartePlafondEntity.getId());
		demandeAnnulationCartePlafondEntity.setConfirmed(true);
		demandeAnnulationCartePlafondRepository.save(demandeAnnulationCartePlafondEntity);
	}

	@Override
	public void modifyOneDemandeAnnulationCarteCarburant(
			DemandeAnnulationCartePlafondDTO demandeAnnulationCartePlafondDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCartePlafondEntity demandeAnnulationCartePlafondEntity = modelMapper
				.map(demandeAnnulationCartePlafondDTO, DemandeAnnulationCartePlafondEntity.class);
		demandeAnnulationCartePlafondEntity.setDateDemande(demandeAnnulationCartePlafondRepository
				.findById(demandeAnnulationCartePlafondDTO.getId()).get().getDateDemande());
		demandeAnnulationCartePlafondEntity.setIdCard(demandeAnnulationCartePlafondRepository
				.findById(demandeAnnulationCartePlafondDTO.getId()).get().getIdCard());
		demandeAnnulationCartePlafondRepository.save(demandeAnnulationCartePlafondEntity);
	}

	@Override
	public void deleteOneCartePlafond(Long id) {
		cartePlafondRepository.deleteById(id);
	}

	@Override
	public void deleteHistoriqueDemandeAnnulationCartePlafond(Long id) {
		demandeAnnulationCartePlafondRepository.deleteById(id);
	}

	private void deleteSelectedCartePlafond(Long id) {
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository.findById(id).get();
		cartePlafondEntity.getVehicule().setCartePlafond(null);
		cartePlafondEntity.setVehicule(null);
		cartePlafondRepository.save(cartePlafondEntity);
		cartePlafondRepository.deleteById(id);
	}

	@Override
	public void deleteOneCarteJocker(Long id) {
		carteJockerRepository.deleteById(id);
	}

	@Override
	public void confirmOneDeclarationPerteCarteJocker(Long id) {
		DeclarationPerteCarteJockerEntity declarationPerteCarteJockerEntity = declarationPerteCarteJockerRepository
				.findById(id).get();
		declarationPerteCarteJockerEntity.setConfirmed(true);
		declarationPerteCarteJockerRepository.save(declarationPerteCarteJockerEntity);
	}

	public void deleteOneDeclarationPerteCarteJocker(Long id) {
		declarationPerteCarteJockerRepository.deleteById(id);

	}
	
	@Override
	public List<DemandeRechargeSousCompteDTO> getPaginationDemandeRechargeSousCompteList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<RechargeSousCompte> DemandeRechargeSousComptes = demandeRechargeSousCompteRepository.findAll(pageable)
				.getContent();
		return this.loadDemandeRechargeSousCompteTabDataDTO(DemandeRechargeSousComptes);
	}
	
	private List<DemandeRechargeSousCompteDTO> loadDemandeRechargeSousCompteTabDataDTO(
			List<RechargeSousCompte> RechargeSousComptes) {
		List<DemandeRechargeSousCompteDTO> RechargeSousCompteTabDataDTOS = new ArrayList<>();
		if (!RechargeSousComptes.isEmpty()) {
			RechargeSousComptes.forEach(RechargeSousCompteEntity -> {
				DemandeRechargeSousCompteDTO listRechargeSousCompteDTO = new DemandeRechargeSousCompteDTO();
				listRechargeSousCompteDTO.setId(RechargeSousCompteEntity.getId());
				listRechargeSousCompteDTO.setValidated(RechargeSousCompteEntity.isValidated());
				listRechargeSousCompteDTO.setConfirmed(RechargeSousCompteEntity.isConfirmed());
				listRechargeSousCompteDTO.setTypeCarburant(RechargeSousCompteEntity.getTypeCarburant());
				listRechargeSousCompteDTO.setQuantiteDemande(RechargeSousCompteEntity.getQuantiteDemande());
				listRechargeSousCompteDTO.setQuantiteValide(RechargeSousCompteEntity.getQuantiteValide());
				listRechargeSousCompteDTO.setDemandeur(RechargeSousCompteEntity.getDemandeur());
				listRechargeSousCompteDTO.setNumCarte(RechargeSousCompteEntity.getNumCarte());
				RechargeSousCompteTabDataDTOS.add(listRechargeSousCompteDTO);
			});
		}

		return RechargeSousCompteTabDataDTOS;
	}

	@Override
	public void deleteDemandeRechargeSousCompte(Long id) 
	{
		demandeRechargeSousCompteRepository.deleteById(id);

	}
	
	@Override
	public void confirmSelectedDemandeRechargeSousCompte(Long id) {
		RechargeSousCompte rechargeSousCompte = demandeRechargeSousCompteRepository.findById(id).get();
		rechargeSousCompte.setConfirmed(true);
		demandeRechargeSousCompteRepository.save(rechargeSousCompte);
	}

	@Override
	public void validateSelectedDemandeRechargeSousCompte(Long id) {
		RechargeSousCompte rechargeSousCompte = demandeRechargeSousCompteRepository.findById(id).get();
		rechargeSousCompte.setValidated(true);
		/*A verfier avec TT : si la quantite livrer doit relier a quantite de carte joker 
		CarteJockerEntity carteJocker = carteJockerRepository.findByNumeroCarte(rechargeSousCompte.getNumCarte());
		System.out.print(carteJocker.getSolde());
        carteJocker.setSolde((carteJocker.getSolde()) - (demandeQuotaCarteJocker.getQuantiteDemande()));
		*/
		HistoriqueOperationRecharge historiqueRecharge = new HistoriqueOperationRecharge();
		historiqueRecharge.setDemandeur(rechargeSousCompte.getDemandeur());
		historiqueRecharge.setNumSousCompte(rechargeSousCompte.getNumCarte());
		historiqueRecharge.setQuantiteDemande(rechargeSousCompte.getQuantiteDemande());	
		historiqueRecharge.setTypeCarburant(rechargeSousCompte.getTypeCarburant());
		historiqueRepository.save(historiqueRecharge);
		demandeRechargeSousCompteRepository.save(rechargeSousCompte);
	}

	@Override
	public RechargeSousCompte addNewDemandeRechargeSousCompte(RechargeSousCompte rechargeSousCompte) {
		return demandeRechargeSousCompteRepository.save(rechargeSousCompte);

	}

	//===================recharge de quota mensuel======================
	
	@Override
	public RechargeQuotaMensuelEntity addNewRechargeQuotaMensuel(RechargeQuotaMensuelEntity rechargeQuotaMensuel) {
		return rechargeQuotaMensuelRepository.save(rechargeQuotaMensuel);

	}
	
	@Override
	public List<RechargeQuotaMensuelDTO> getPaginationRechargeQuotaMensuelList(String cartePlafond, int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		Page<RechargeQuotaMensuelEntity> RechargeQuotaMensuelEntity = rechargeQuotaMensuelRepository.findAll(pageable);
		List<RechargeQuotaMensuelEntity> rechargeQuotaMensuelEntities = rechargeQuotaMensuelRepository.findAll(pageable).getContent();

		if ((cartePlafond.length() > 0)) {
			Page<RechargeQuotaMensuelEntity> rechargeQuotaMensuelEntity = rechargeQuotaMensuelRepository.findAllByNumCarte(cartePlafond, pageable);
			rechargeQuotaMensuelEntities = rechargeQuotaMensuelEntity.getContent();

		}
		return this.loadListRechargeQuotaMensuelDTO(rechargeQuotaMensuelEntities);
	}

	private List<RechargeQuotaMensuelDTO> loadListRechargeQuotaMensuelDTO(
			List<RechargeQuotaMensuelEntity> rechargeQuotaMensuelEntities) {
		List<RechargeQuotaMensuelDTO> rechargeQuotaMensuelDTOS = new ArrayList<>();
		if (!rechargeQuotaMensuelEntities.isEmpty()) {
			rechargeQuotaMensuelEntities.forEach(rechargeQuotaMensuelEntity -> {
				RechargeQuotaMensuelDTO rechargeQuotaMensuelDTO = new RechargeQuotaMensuelDTO();
				rechargeQuotaMensuelDTO.setId(rechargeQuotaMensuelEntity.getId());
				rechargeQuotaMensuelDTO.setNom(rechargeQuotaMensuelEntity.getNom());
				rechargeQuotaMensuelDTO.setPrenom(rechargeQuotaMensuelEntity.getPrenom());
				rechargeQuotaMensuelDTO.setCartePlafond(rechargeQuotaMensuelEntity.getCartePlafond());
				rechargeQuotaMensuelDTO.setMatricule(rechargeQuotaMensuelEntity.getMatricule());
				rechargeQuotaMensuelDTO.setConfirmed(rechargeQuotaMensuelEntity.isConfirmed());
				rechargeQuotaMensuelDTO.setValidated(rechargeQuotaMensuelEntity.isValidated());
				rechargeQuotaMensuelDTOS.add(rechargeQuotaMensuelDTO);

			});
		}
		return rechargeQuotaMensuelDTOS;
	}
		
	@Override
	public void confirmRechargeQuotaMensuel(long id) {
		RechargeQuotaMensuelEntity r = rechargeQuotaMensuelRepository.findById(id).get();
		r.setConfirmed(true);
		rechargeQuotaMensuelRepository.save(r);
		
	}
	
	@Override
	public void validRechargeQuotaMensuel(long id) {
		RechargeQuotaMensuelEntity r = rechargeQuotaMensuelRepository.findById(id).get();
		r.setValidated(true);
		HistoriqueRechargeQuotaMensuelEntity historiqueRecharge = new HistoriqueRechargeQuotaMensuelEntity();
		historiqueRecharge.setNom(r.getNom());
		historiqueRecharge.setPrenom(r.getPrenom());
		historiqueRecharge.setMatricule(r.getMatricule());	
		
		rechargeHistoriqueQuotaMensuelRepository.save(historiqueRecharge);
		rechargeQuotaMensuelRepository.save(r);
	}


	@Override
	public List<HistoriqueRechargeQuotaMensuelEntity> getPaginationHistoriqueRechargeQMList(int page, int limit) {
			List<HistoriqueRechargeQuotaMensuelEntity> HistoriqueEntities = new ArrayList<>();
			PageRequest pageable = PageRequest.of(page, limit);
			Page<HistoriqueRechargeQuotaMensuelEntity> historiqueEntityPage = rechargeHistoriqueQuotaMensuelRepository.findAll(pageable);
			HistoriqueEntities = historiqueEntityPage.getContent();
			System.out.print(HistoriqueEntities);
			return HistoriqueEntities;
		
	}
	
	@Override
	public int NbNotif() {
		int  n = rechargeQuotaMensuelRepository.findNombreNotif();	 
		return n;
	}
	

//=======================================================================================================================

	private List<ListRechargeComplementaireDTO> loadRechargeComplementaireTabDataDTO(
			List<RechargeComplementaire> RechargeComplementaires) {
		List<ListRechargeComplementaireDTO> RechargeComplementaireTabDataDTOS = new ArrayList<>();
		if (!RechargeComplementaires.isEmpty()) {
			RechargeComplementaires.forEach(RechargeComplementaireEntity -> {
				ListRechargeComplementaireDTO listRechargeComplementaireDTO = new ListRechargeComplementaireDTO();
				listRechargeComplementaireDTO.setId(RechargeComplementaireEntity.getId());
				listRechargeComplementaireDTO.setValidated(RechargeComplementaireEntity.isValidated());
				listRechargeComplementaireDTO.setConfirmed(RechargeComplementaireEntity.isConfirmed());
				listRechargeComplementaireDTO.setQuantiteDemande(RechargeComplementaireEntity.getQuantiteDemande());
				listRechargeComplementaireDTO.setCartePlafond(RechargeComplementaireEntity.getCartePlafond());
				listRechargeComplementaireDTO.setMatricule(RechargeComplementaireEntity.getMatricule());
				listRechargeComplementaireDTO.setObservation(RechargeComplementaireEntity.getObservation());
				RechargeComplementaireTabDataDTOS.add(listRechargeComplementaireDTO);
			});
		}
		return RechargeComplementaireTabDataDTOS;
	}

	@Override
	public List<ListRechargeComplementaireDTO> getPaginationRechargeComplementaireList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<RechargeComplementaire> RechargeComplementaires = rechargeComplementaireRepository.findAll(pageable).getContent();
		return this.loadRechargeComplementaireTabDataDTO(RechargeComplementaires);
	}

	//filtrage
	@Override
	public List<ListRechargeComplementaireDTO> getPaginationRechargeComplementaireList(String cartePlafond, int page, int limit) {
		List<RechargeComplementaire> rechargeEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if ((cartePlafond.length() > 0)) {
		Page<RechargeComplementaire> RechargeEntity = rechargeComplementaireRepository.findAllByNumCarte(cartePlafond,
		pageable);
		rechargeEntities = RechargeEntity.getContent();
		}
	 else {
		Page<RechargeComplementaire> RechargeEntityPage = rechargeComplementaireRepository.findAll(pageable);
		rechargeEntities = RechargeEntityPage.getContent();
		}
		return this.loadRechargeComplementaireTabDataDTO(rechargeEntities);
	}
	
	//historique
	@Override
	public List<ListRechargeComplementaireDTO> getPaginationHistoriqueRechargeComplementaireList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<RechargeComplementaire> RechargeComplementaires = rechargeComplementaireRepository.findAllByValidated(pageable).getContent();
		return this.loadRechargeComplementaireTabDataDTO(RechargeComplementaires);
	}
	@Override
	public RechargeComplementaire addNewRechargeComplementaire(RechargeComplementaire RechargeComplementaire) {
		return rechargeComplementaireRepository.save(RechargeComplementaire);
	}
	
	@Override
	public List<HistoriqueOperationRecharge> getPaginationHistoriqueRechargeList(int page, int limit) {
		List<HistoriqueOperationRecharge> HistoriqueEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<HistoriqueOperationRecharge> historiqueEntityPage = historiqueRepository.findAll(pageable);
		HistoriqueEntities = historiqueEntityPage.getContent();
		System.out.print(HistoriqueEntities);
		return HistoriqueEntities;
	}
	@Override
	public void deleteSelectedHistoriqueRecharge(Long id) {
		HistoriqueOperationRecharge historique = historiqueRepository.findHistoriqueRechargeById(id);	 ;
		historiqueRepository.delete(historique);
	}

	@Override
	public int NombreNotif() {
		int  n = demandeRechargeSousCompteRepository.findNombreNotif();	 
		return n;
	}
	
	@Override
	public int NombreNotifValid() {
		int  n = demandeRechargeSousCompteRepository.findNombreNotifValidation();	 
		return n;
	}


	@Override
	public int NombreNotifRechargeComplementaire() {
		int  n = rechargeComplementaireRepository.findNombreNotif();	 
		return n;
	}
	
	@Override
	public int NombreNotifValidRechargeComplementaire() {
		int  n = rechargeComplementaireRepository.findNombreNotifValidation();	 
		return n;
	}

	@Override
	public int NombreNotifRechargeCompensation() {
		int  n = rechargeCarburantCompensationRepository.findNombreNotif();	 
		return n;
	}
	
	@Override
	public int NombreNotifValidRechargeCompensation() {
		int  n = rechargeCarburantCompensationRepository.findNombreNotifValidation();	 
		return n;
	}
	
	/*******************************/

	@Override
	public List<ListRechargeCarburantCompensationDTO> loadRechargeCarburantCompensationTabDataDTO(
			List<RechargeCarburantCompensation> rechargeCarburantCompensations) {
		List<ListRechargeCarburantCompensationDTO> rechargeCarburantCompensationTabDataDTOS = new ArrayList<>();
		if (!rechargeCarburantCompensations.isEmpty()) {
			rechargeCarburantCompensations.forEach(rechargeCarburantCompensationEntity -> {
				ListRechargeCarburantCompensationDTO listRechargeCarburantCompensationDTO = new ListRechargeCarburantCompensationDTO();
				listRechargeCarburantCompensationDTO.setId(rechargeCarburantCompensationEntity.getId());
				listRechargeCarburantCompensationDTO.setValidated(rechargeCarburantCompensationEntity.isValidated());
				listRechargeCarburantCompensationDTO.setConfirmed(rechargeCarburantCompensationEntity.isConfirmed());
				listRechargeCarburantCompensationDTO.setMatricule(rechargeCarburantCompensationEntity.getMatricule());
				listRechargeCarburantCompensationDTO.setQuantiteDemande(rechargeCarburantCompensationEntity.getQuantiteDemande());
				listRechargeCarburantCompensationDTO.setCartePlafond(rechargeCarburantCompensationEntity.getCartePlafond());
				listRechargeCarburantCompensationDTO.setMission(rechargeCarburantCompensationEntity.getMission());
				listRechargeCarburantCompensationDTO.setDestination(rechargeCarburantCompensationEntity.getDestination());
				listRechargeCarburantCompensationDTO.setObservation(rechargeCarburantCompensationEntity.getObservation());
				rechargeCarburantCompensationTabDataDTOS.add(listRechargeCarburantCompensationDTO);
			});
		}
		return rechargeCarburantCompensationTabDataDTOS;
	}

	@Override
	public List<ListRechargeCarburantCompensationDTO> getPaginationRechargeCarburantCompensationList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<RechargeCarburantCompensation> rechargeCarburantCompensations = rechargeCarburantCompensationRepository.findAll(pageable).getContent();
		return this.loadRechargeCarburantCompensationTabDataDTO(rechargeCarburantCompensations);
	}

		//filtrage
	@Override
	public List<ListRechargeCarburantCompensationDTO> getPaginationRechargeCarburantCompensationList(String cartePlafond, int page, int limit) {
	
		List<RechargeCarburantCompensation> rechargeEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if ((cartePlafond.length() > 0)) {
		Page<RechargeCarburantCompensation> RegulationArticleStockEntity = rechargeCarburantCompensationRepository.findAllByNumCarte(cartePlafond,
		pageable);
		rechargeEntities = RegulationArticleStockEntity.getContent();
		}
	 else {
		Page<RechargeCarburantCompensation> rechargeEntityPage = rechargeCarburantCompensationRepository.findAll(pageable);
		rechargeEntities = rechargeEntityPage.getContent();
		}
		return this.loadRechargeCarburantCompensationTabDataDTO(rechargeEntities);
	}
	
	//Historique
	@Override
	public List<ListRechargeCarburantCompensationDTO> getPaginationHistoriqueRechargeCarburantCompensationList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<RechargeCarburantCompensation> rechargeCarburantCompensations = rechargeCarburantCompensationRepository.findAllByValidated(pageable).getContent();
		return this.loadRechargeCarburantCompensationTabDataDTO(rechargeCarburantCompensations);
	}

	
	
	@Override
	public RechargeCarburantCompensation addNewRechargeCarburantCompensation(RechargeCarburantCompensation rechargeCarburantCompensation) {
		return rechargeCarburantCompensationRepository.save(rechargeCarburantCompensation);

	}

	@Override
	public void modifySelectedRechargeComplementaire(ModifyRechargeComplementaireDTO RechargeComplementaire) {
		RechargeComplementaire RechargeComplementaireEntity = rechargeComplementaireRepository
				.findById(RechargeComplementaire.getId()).get();
		RechargeComplementaireEntity.setObservation(RechargeComplementaire.getObservation());
		rechargeComplementaireRepository.save(RechargeComplementaireEntity);
	}

	@Override
	public void deleteSelectedRechargeComplementaire(Long id) {
		rechargeComplementaireRepository.deleteById(id);
	}
	
	@Override
	public void modifySelectedRechargeCarburantCompensation(ModifyRechargeCarburantCompensationDTO rechargeCarburantCompensation) {
		RechargeCarburantCompensation rechargeCarburantCompensationEntity = rechargeCarburantCompensationRepository
				.findById(rechargeCarburantCompensation.getId()).get();
		rechargeCarburantCompensationEntity.setMission(rechargeCarburantCompensation.getMission());
		rechargeCarburantCompensationEntity.setDestination(rechargeCarburantCompensation.getDestination());
		rechargeCarburantCompensationEntity.setObservation(rechargeCarburantCompensation.getObservation());
		rechargeCarburantCompensationRepository.save(rechargeCarburantCompensationEntity);
	}

	@Override
	public void deleteSelectedRechargeCarburantCompensation(Long id) {
		rechargeCarburantCompensationRepository.deleteById(id);

	}

	@Override
	public RechargeComplementaire getRechargeComplementaire(Long id) {
		return rechargeComplementaireRepository.findById(id).get();
	}

	@Override
	public Iterable<RechargeComplementaire> getAllRechargeComplementaires() {

		return rechargeComplementaireRepository.findAll();
	}

	@Override
	public void confirmSelectedRechargeComplementaire(Long id) {
		RechargeComplementaire RechargeComplementaire = rechargeComplementaireRepository.findById(id).get();
		RechargeComplementaire.setConfirmed(true);
		rechargeComplementaireRepository.save(RechargeComplementaire);
	}

	@Override
	public void validateSelectedRechargeComplementaire(Long id) {
		RechargeComplementaire RechargeComplementaire = rechargeComplementaireRepository.findById(id).get();
		RechargeComplementaire.setValidated(true);
		CartePlafondEntity cartePlafond = cartePlafondRepository.findFirstByNumeroCarte(RechargeComplementaire.getCartePlafond().getNumeroCarte());
		cartePlafond.setMontant((cartePlafond.getMontant())+(RechargeComplementaire.getQuantiteDemande()));
		cartePlafondRepository.save(cartePlafond);
		HistoriqueRecahrgeComplementaire historiqueRecharge = new HistoriqueRecahrgeComplementaire();
		historiqueRecharge.setMatricule(RechargeComplementaire.getMatricule());
		historiqueRecharge.setQuantiteDemande(RechargeComplementaire.getQuantiteDemande());
		historiqueRecharge.setObservation(RechargeComplementaire.getObservation());
		historiqueRecahrgeComplementaireRepository.save(historiqueRecharge);
		rechargeComplementaireRepository.save(RechargeComplementaire);
	}
	
	@Override
	public Long getTotalNumberRechargeComplementaire() {
		PageRequest pageable = PageRequest.of(0, 8);
		return rechargeComplementaireRepository.findAll(pageable).getTotalElements();
	}
	
	@Override
	public RechargeCarburantCompensation getRechargeCarburantCompensation(Long id) {
		return rechargeCarburantCompensationRepository.findById(id).get();
	}

	@Override
	public Iterable<RechargeCarburantCompensation> getAllRechargeCarburantCompensations() {

		return rechargeCarburantCompensationRepository.findAll();
	}

	@Override
	public void confirmSelectedRechargeCarburantCompensation(Long id) {
		RechargeCarburantCompensation rechargeCarburantCompensation = rechargeCarburantCompensationRepository.findById(id).get();
		rechargeCarburantCompensation.setConfirmed(true);
		rechargeCarburantCompensationRepository.save(rechargeCarburantCompensation);
	}

	@Override
	public void validateSelectedRechargeCarburantCompensation(Long id) {
		RechargeCarburantCompensation rechargeCarburantCompensation = rechargeCarburantCompensationRepository.findById(id).get();
		rechargeCarburantCompensation.setValidated(true);
		CartePlafondEntity cartePlafond = cartePlafondRepository.findFirstByNumeroCarte(rechargeCarburantCompensation.getCartePlafond().getNumeroCarte());
		cartePlafond.setMontant((cartePlafond.getMontant())+(rechargeCarburantCompensation.getQuantiteDemande()));
		HistoriqueRechargeCarburantCompensation historiqueRecharge = new HistoriqueRechargeCarburantCompensation();
		historiqueRecharge.setMatricule(rechargeCarburantCompensation.getMatricule());
		historiqueRecharge.setQuantiteDemande(rechargeCarburantCompensation.getQuantiteDemande());
		historiqueRecharge.setMission(rechargeCarburantCompensation.getMission());
		historiqueRecharge.setDestination(rechargeCarburantCompensation.getDestination());
		historiqueRecharge.setObservation(rechargeCarburantCompensation.getObservation());
		historiqueRechargeCarburantCompensationRepository.save(historiqueRecharge);
		cartePlafondRepository.save(cartePlafond);
		rechargeCarburantCompensationRepository.save(rechargeCarburantCompensation);
	}

	@Override
	public Long getTotalNumberRechargeCarburantCompensation() {
		PageRequest pageable = PageRequest.of(0, 8);
		return rechargeCarburantCompensationRepository.findAll(pageable).getTotalElements();
	}

	
	@Override
	public List<HistoriqueRecahrgeComplementaire> getPaginationHistoriqueRechargeComplementaireList1(int page, int limit) {
		List<HistoriqueRecahrgeComplementaire> HistoriqueEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<HistoriqueRecahrgeComplementaire> historiqueEntityPage = historiqueRecahrgeComplementaireRepository.findAll(pageable);
		HistoriqueEntities = historiqueEntityPage.getContent();
		System.out.print(HistoriqueEntities);
		return HistoriqueEntities;
	}
	@Override
	public void deleteSelectedHistoriqueRechargeComplementaire(Long id) {
		HistoriqueRecahrgeComplementaire historique = historiqueRecahrgeComplementaireRepository.findHistoriqueRecahrgeComplementaireById(id);	 ;
		historiqueRepository.deleteById(historique.getId());
	}


	@Override
	public List<HistoriqueRechargeCarburantCompensation> getPaginationHistoriqueRechargeCompensationList1(int page, int limit) {
		List<HistoriqueRechargeCarburantCompensation> HistoriqueEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<HistoriqueRechargeCarburantCompensation> historiqueEntityPage = historiqueRechargeCarburantCompensationRepository.findAll(pageable);
		HistoriqueEntities = historiqueEntityPage.getContent();
		System.out.print(HistoriqueEntities);
		return HistoriqueEntities;
	}
	@Override
	public void deleteSelectedHistoriqueRechargeCompensation(Long id) {
		HistoriqueRechargeCarburantCompensation historique = historiqueRechargeCarburantCompensationRepository.findHistoriqueRechargeCarburantCompensationById(id);	 ;
		historiqueRepository.deleteById(historique.getId());
	}

}

