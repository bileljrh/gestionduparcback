package com.gesparc.controllers;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.entities.maintenance.FacturationEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.FamilleOperationReparationEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.exceptions.ResourceNotFoundException;
import com.gesparc.messages.ApiResponse;
import com.gesparc.repositories.BeneficiaireRepository;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.maintenance.BonTravailArticleExterneRepository;
import com.gesparc.repositories.maintenance.BonTravailArticleRepository;
import com.gesparc.repositories.maintenance.BonTravailOperationRepository;
import com.gesparc.repositories.maintenance.BonTravailRepository;
import com.gesparc.repositories.maintenance.DemandeMaintenanceRepository;
import com.gesparc.repositories.maintenance.FacturationRepository;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.repositories.referentiel.FamilleOperationReparationRepository;
import com.gesparc.repositories.referentiel.FournisseurRepository;
import com.gesparc.repositories.referentiel.MagasinRepository;
import com.gesparc.repositories.referentiel.OperationReparationRepository;
import com.gesparc.repositories.referentiel.StructureRepository;
import com.gesparc.repositories.referentiel.UgpRepository;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.requests.Maintenance.BonTravailRequest;
import com.gesparc.requests.Maintenance.DemandeMaintenanceRequest;
import com.gesparc.requests.Maintenance.additionnel.UpdateBonTravailRequest;
import com.gesparc.responses.maintenance.additional.BonTravailDataTableResponse;
import com.gesparc.responses.maintenance.additional.DemandeMaintenanceDataTableResponse;
import com.gesparc.responses.maintenance.additional.ListVehiculeAvailable4MaintenanceResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.MaintenanceImpl;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.maintenance.DemandeMaintenanceDTO;
import com.gesparc.sharedDTO.maintenance.ListMagasinDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.DemandeMaintenanceDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.ListVehiculeAvailable4MaintenanceDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class MaintenanceController {

	Logger logger = LoggerFactory.getLogger(MaintenanceController.class);

	@Autowired
	MaintenanceImpl maintenance;

	@Autowired
	private BeneficiaireRepository beneficiaireRepository;

	@Autowired
	private VehiculeRepository vehiculeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UgpRepository ugpRepository;

	@Autowired
	private StructureRepository structureRepository;

	@Autowired
	private DemandeMaintenanceRepository demandeMaintenanceRepository;

	@Autowired
	private BonTravailRepository bonTravailRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private MagasinRepository magasinRepository;

	@Autowired
	private FournisseurRepository fournisseurRepository;

	@Autowired
	private FacturationRepository facturationRepository;

	@Autowired
	private BonTravailOperationRepository bonTravailOperationRepository;

	@Autowired
	private BonTravailArticleRepository bonTravailArticleRepository;

	@Autowired
	private OperationReparationRepository operationReparationRepository;

	@Autowired
	private FamilleOperationReparationRepository familleOperationReparationRepository;

	@Autowired
	private BonTravailArticleExterneRepository bonTravailArticleExterneRepository;

	@JsonFormat(pattern = "YYYY-MM-DD", shape = JsonFormat.Shape.STRING)
	Date date = new Date();

	@GetMapping(path = "/list_vehicule_available_for_maintenance/{structure}")
	private List<ListVehiculeAvailable4MaintenanceResponse> getListVehiculeAvailableForMaintenanceByStructure(
			@PathVariable String structure) {
		ModelMapper modelMapper = new ModelMapper();
		List<ListVehiculeAvailable4MaintenanceResponse> listVehiculeAvailable4MaintenanceResponses = new ArrayList<>();
		List<ListVehiculeAvailable4MaintenanceDTO> listVehiculeAvailable4MaintenanceDTOS = maintenance
				.getListVehiculeAvailableForMaintenanceByStructure(structure);
		if (!listVehiculeAvailable4MaintenanceDTOS.isEmpty()) {
			listVehiculeAvailable4MaintenanceDTOS.forEach(listVehiculeAvailable4EmpruntDTO -> {
				listVehiculeAvailable4MaintenanceResponses.add(modelMapper.map(listVehiculeAvailable4EmpruntDTO,
						ListVehiculeAvailable4MaintenanceResponse.class));
			});
		}
		return listVehiculeAvailable4MaintenanceResponses;
	}

	@PutMapping(path = "/add_sortie_vehicule_date/{id}")
	private BonTravailEntity UpdateBonTravail(@PathVariable Long id, @RequestBody BonTravailRequest d) {
		BonTravailEntity bt = bonTravailRepository.findById(id).get();
		bt.setDateSortie(d.getDateSortie());
		bt.setCloturer(true);
		return bonTravailRepository.save(bt);
	}

	@GetMapping("/beneficiare/{id}")
	public Optional<BeneficiaireEntity> getBeneficiaire(@PathVariable Long id) {
		return beneficiaireRepository.findById(id);
	}

	@GetMapping("/vehicule_ugp/{id}")
	public List<VehiculeEntity> getVehiculeByUgp(@PathVariable Long id) {
		return vehiculeRepository.findByUgpId(id);
	}

	@GetMapping("/structue/{id}")
	public Optional<StructureEntity> findstructurebyid(@PathVariable Long id) {
		return structureRepository.findById(id);
	}

	@GetMapping("/structres")
	public Iterable<StructureEntity> listStructure() {
		return structureRepository.findAll();
	}

	@GetMapping(path = "/vehicule")
	public Iterable<VehiculeEntity> vehiculeList() {
		return vehiculeRepository.findAll();
	}

	@GetMapping(path = "/unite_gestion_parc")
	public Iterable<UgpEntity> ugpList() {
		return ugpRepository.findAll();
	}

	private String generateNumeroBonCommande(Long totalNumber) {
		return String.format("%04d", totalNumber) + "-" + LocalDate.now().getDayOfMonth()
				+ LocalDate.now().getMonthValue() + LocalDate.now().getYear();
	}

	@PostMapping("/demande_maintenance")
	public DemandeMaintenanceEntity placeOrder(@RequestBody DemandeMaintenanceRequest request) {
		DemandeMaintenanceEntity dme = new DemandeMaintenanceEntity();
		dme.setNumeroSerie(request.getNumeroSerie());
		dme.setDescriptionIntervention(request.getDescriptionIntervention());
		dme.setUgpReparation(request.getUgpReparation());
		dme.setDemandeur(request.getDemandeur());
		dme.setNumeroDemande(request.getNumeroDemande());
		dme.setDateDemande(date);
		dme.setAnnulation(false);
		dme.setNumeroDemande(this.generateNumeroBonCommande(getTotalItemsDemandeMaintenanceList()));
		dme.setStructure(request.getStructure());
		dme.setStatus("en cours");
		dme.setIndexKm(request.getIndexKm());
		dme.setObservation(request.getObservation());
		dme.setNomBeneficiaire(request.getNomBeneficiaire());
		dme.setUgp(request.getUgp());
		dme.setVehicule(request.getVehicule());
		return demandeMaintenanceRepository.save(dme);
	}

	@PutMapping("/demande_maintenance")
	public DemandeMaintenanceEntity updateDemandeMaintenance(@RequestBody DemandeMaintenanceRequest request) {
		DemandeMaintenanceEntity dme = demandeMaintenanceRepository.findById(request.getId()).get();// new
		dme.setNumeroSerie(request.getNumeroSerie());
		dme.setDescriptionIntervention(request.getDescriptionIntervention());
		dme.setUgpReparation(request.getUgpReparation());
		dme.setPersonnel(request.getPersonnel());
		dme.setDemandeur(request.getDemandeur());
		dme.setNumeroDemande(request.getNumeroDemande());
		dme.setDateDemande(date);
		dme.setNumeroDemande(this.generateNumeroBonCommande(getTotalItemsDemandeMaintenanceList()));
		if (request.getStatus().equals("Accord")) {
			dme.setDateRDV(request.getDateRDV());
		} else {
			dme.setDateRDV(null);
		}
		dme.setUgp(request.getUgp());
		dme.setStatus(request.getStatus());
		System.out.println(request.getVehicule().getId());
		return demandeMaintenanceRepository.save(dme);
	}


	@DeleteMapping("/demande_maintenance_annulation/{id}")
	public void annulerDemandeMaintenance(@PathVariable Long id) {
		 maintenance.annulerDemandeMaintenance(id);
	}

	
	@PutMapping(path = "/demande-piece-bon-travail")
	public ResponseEntity<?> commandeArticleForBonTravail(@RequestBody BonTravailRequest bonDeTravailRequest) {
		BonTravailEntity bonTravailEntity = bonTravailRepository.findById(bonDeTravailRequest.getId()).get();
		bonTravailEntity.setObservatioMode(bonDeTravailRequest.getObservatioMode());
		bonTravailEntity.setBonTravailArticle(new ArrayList<>());
		bonTravailEntity.setBonTravailOperation(new ArrayList<>());
		if (bonDeTravailRequest.getAtelier() != null) {
			bonTravailEntity.setAtelier(bonDeTravailRequest.getAtelier());
		}
		if (bonDeTravailRequest.getMagasin() != null) {
			bonTravailEntity.setMagasin(bonDeTravailRequest.getMagasin());
		}
		bonTravailEntity.setBonTravailArticleExterne(new ArrayList<>());
		if ((bonTravailEntity.getMode().toUpperCase().equals("INTERNE"))) {

			if (bonDeTravailRequest.getArticles() != null && bonDeTravailRequest.getOperations() != null) {

				this.dOperations(bonDeTravailRequest.getId());
				this.dInterne(bonDeTravailRequest.getId());
				bonDeTravailRequest.getArticles().forEach(bonTravailArticle -> {
					BonTravailArticleEntity demandeArticleEntity = new BonTravailArticleEntity();
					ArticleEntity articleEntity = articleRepository.findById(bonTravailArticle.getArticles().getId())
							.get();
					demandeArticleEntity.setArticles(articleEntity);
					demandeArticleEntity.setQuantiteLivree(bonTravailArticle.getQuantiteLivree());
					demandeArticleEntity.setQuantite(bonTravailArticle.getQuantite());
					if (articleEntity.getBonTravailArticle() == null) {
						articleEntity.setBonTravailArticle(new HashSet<>());
					}
					articleEntity.getBonTravailArticle().add(demandeArticleEntity);
					demandeArticleEntity.setBonTravail(bonTravailEntity);
					bonTravailEntity.getBonTravailArticle().add(demandeArticleEntity);
				});

				bonDeTravailRequest.getOperations().forEach(updateBontravailOperations -> {
					BonTravailOperationEntity bto = new BonTravailOperationEntity();
					OperationReparationEntity operationReparationEntity = operationReparationRepository
							.findById(updateBontravailOperations.getOperations().getId()).get();
					bto.setPrix(updateBontravailOperations.getPrix());
					bto.setDateDebut(updateBontravailOperations.getDateDebut());
					System.out.println("test date");
					System.out.println(updateBontravailOperations.getDateDebut());
					bto.setOperations(operationReparationEntity);// setArticle(articleEntity);

					if (operationReparationEntity.getBonTravailOperation() == null) {
						operationReparationEntity.setBonTravailOperation(new HashSet<>());
					}
					operationReparationEntity.getBonTravailOperation().add(bto);
					bto.setBonTravail(bonTravailEntity);
					bonTravailEntity.getBonTravailOperation().add(bto);
				});
				bonTravailRepository.save(bonTravailEntity);
			}

			return ResponseEntity.status(HttpStatus.OK).body("Bon Interne ajouter avec succes");

		} else if ((bonTravailEntity.getMode().toUpperCase().equals("EXTERNE"))) {
			BonTravailArticleEntity demandeArticleEntity = new BonTravailArticleEntity();

			if (bonDeTravailRequest.getArticles() != null && bonDeTravailRequest.getOperations() != null
					&& bonDeTravailRequest.getExternes() != null) {
				this.dOperations(bonDeTravailRequest.getId());
				this.dInterne(bonDeTravailRequest.getId());
				this.dExterne(bonDeTravailRequest.getId());
				bonDeTravailRequest.getArticles().forEach(bonTravailArticle -> {
					ArticleEntity articleEntity = articleRepository.findById(bonTravailArticle.getArticles().getId())
							.get();
					demandeArticleEntity.setArticles(articleEntity);
					demandeArticleEntity.setQuantiteLivree(bonTravailArticle.getQuantiteLivree());
					demandeArticleEntity.setQuantite(bonTravailArticle.getQuantite());
					if (articleEntity.getBonTravailArticle() == null) {
						articleEntity.setBonTravailArticle(new HashSet<>());
					}
					articleEntity.getBonTravailArticle().add(demandeArticleEntity);
					demandeArticleEntity.setBonTravail(bonTravailEntity);
					bonTravailEntity.getBonTravailArticle().add(demandeArticleEntity);
				});
				bonDeTravailRequest.getOperations().forEach(updateBontravailOperations -> {
					BonTravailOperationEntity bto = new BonTravailOperationEntity();
					OperationReparationEntity operationReparationEntity = operationReparationRepository
							.findById(updateBontravailOperations.getOperations().getId()).get();
					bto.setPrix(updateBontravailOperations.getPrix());
					bto.setOperations(operationReparationEntity);// setArticle(articleEntity);
					if (operationReparationEntity.getBonTravailOperation() == null) {
						operationReparationEntity.setBonTravailOperation(new HashSet<>());

					}
					operationReparationEntity.getBonTravailOperation().add(bto);
					bto.setBonTravail(bonTravailEntity);
					bonTravailEntity.getBonTravailOperation().add(bto);
				});
				bonDeTravailRequest.getExternes().forEach(updateDemandeArticleDTO -> {
					BonTravailArticleExterneEntity demandeArticleEntityy = new BonTravailArticleExterneEntity();
					ArticleEntity articleEntity = articleRepository
							.findById(updateDemandeArticleDTO.getExternes().getId()).get();
					demandeArticleEntityy.setExternes(articleEntity);
					demandeArticleEntity.setQuantiteLivree(updateDemandeArticleDTO.getQuantiteLivree());
					demandeArticleEntity.setQuantite(updateDemandeArticleDTO.getQuantite());
					if (articleEntity.getBonTravailArticleExterne() == null) {
						articleEntity.setBonTravailArticleExterne(new HashSet<>());
					}
					articleEntity.getBonTravailArticleExterne().add(demandeArticleEntityy);
					demandeArticleEntityy.setBonTravail(bonTravailEntity);
					bonTravailEntity.getBonTravailArticleExterne().add(demandeArticleEntityy);
				});
			}
			FournisseurEntity fournisseurEntity = fournisseurRepository
					.findById(bonDeTravailRequest.getFournisseur().getId()).get();
			FacturationEntity facturaionEntity = facturationRepository.findByFournisseurId(fournisseurEntity.getId());
			if (facturaionEntity == null) {
				facturaionEntity = new FacturationEntity();
			}
			if (fournisseurEntity.getFacturation() == null) {
				fournisseurEntity.setFacturation(new ArrayList<>());
			}
			facturaionEntity.setFournisseur(fournisseurEntity);
			facturaionEntity.setBonTravail(bonTravailEntity);
			facturaionEntity.setDateCommande(bonDeTravailRequest.getFacturation().getDateCommande());
			facturaionEntity.setNumeroFacturation(bonDeTravailRequest.getFacturation().getNumeroFacturation());
			facturaionEntity.setDateFacturation(bonDeTravailRequest.getFacturation().getDateFacturation());
			facturaionEntity.setDateReglement(bonDeTravailRequest.getFacturation().getDateReglement());
			facturaionEntity.setMontantFacturation(bonDeTravailRequest.getFacturation().getMontantFacturation());
			facturaionEntity.setNumeroCommande(bonDeTravailRequest.getFacturation().getNumeroCommande());
			facturaionEntity.setMontantCommande(bonDeTravailRequest.getFacturation().getMontantCommande());
			facturaionEntity.setMontantReglement(bonDeTravailRequest.getFacturation().getMontantReglement());
			facturaionEntity.setNumeroReglement(bonDeTravailRequest.getFacturation().getNumeroReglement());
			fournisseurEntity.getFacturation().add(facturaionEntity);
			bonTravailRepository.save(bonTravailEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Bon Interne ajouter avec succes");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
	}

	@GetMapping(path = "/bontravailInterne/{id}")
	public Optional<BonTravailEntity> showBontravailById(@PathVariable Long id) {
		return bonTravailRepository.findById(id);

	}

	@GetMapping(path = "/operation-reparation/{id}")
	public List<OperationReparationEntity> showoperationbyfamille(@PathVariable Long id) {
		return operationReparationRepository.Famille(id);

	}

	@GetMapping(path = "/findFamilleReparation")
	public Iterable<FamilleOperationReparationEntity> showAllFamille() {
		return familleOperationReparationRepository.findAll();

	}

	@PutMapping(path = "/deleteExterne/{id}")
	public void dExterne(@PathVariable Long id) {
		List<BonTravailArticleExterneEntity> b = bonTravailArticleExterneRepository.findByBonTravailId(id);
		for (Iterator<BonTravailArticleExterneEntity> iterator = b.iterator(); iterator.hasNext();) {
			BonTravailArticleExterneEntity externe = iterator.next();
			if (externe != null) {
				bonTravailArticleExterneRepository.delete(externe);
			}
		}
	}

	private void dOperations(@PathVariable Long id) {
		List<BonTravailOperationEntity> b = bonTravailOperationRepository.findByBonTravailId(id);
		for (Iterator<BonTravailOperationEntity> iterator = b.iterator(); iterator.hasNext();) {
			BonTravailOperationEntity course = iterator.next();
			if (course != null) {
				bonTravailOperationRepository.delete(course);
			}
		}
	}

	private void dInterne(@PathVariable Long id) {
		List<BonTravailArticleEntity> b = bonTravailArticleRepository.findByBonTravailId(id);
		for (Iterator<BonTravailArticleEntity> iterator = b.iterator(); iterator.hasNext();) {
			BonTravailArticleEntity interne = iterator.next();
			if (interne != null) {
				bonTravailArticleRepository.delete(interne);
			}
		}
	}

	@DeleteMapping("/demande_maintenance/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id) {
		return demandeMaintenanceRepository.findById(id).map(post -> {
			demandeMaintenanceRepository.delete(post);
			return ResponseEntity.ok().body(new ApiResponse(true, "demande deleted successfully"));
		}).orElseThrow(() -> new ResourceNotFoundException("demande  not found"));
	}

	@GetMapping(path = "/total_items_demande_maintenance")
	Long getTotalItemsDemandeMaintenanceList() {
		return maintenance.getTotalItemsDemandeMaintenanceList();
	}

	@GetMapping(path = "/total_items_bon_travail")
	Long getTotalItemsBonTravailList() {
		return maintenance.getTotalItemsBonTravailList();
	}

	@GetMapping("/demande_maintenance_for_bon_travail")
	List<DemandeMaintenanceEntity> getDemandeMaintenanceListAvailableForBonTravail() {
		return demandeMaintenanceRepository.findAllByStatus("Accord");

	}

	@PostMapping("/bon_travail")
	public Iterable<BonTravailEntity> newBonTravail(@RequestBody BonTravailRequest request) {
		DemandeMaintenanceEntity demandeMaintenanceEntity = demandeMaintenanceRepository
				.findById(request.getDemandeMaintenance().getId()).get();
		BonTravailEntity bonTravailEntity = new BonTravailEntity();
		bonTravailEntity.setDemandeMaintenance(demandeMaintenanceEntity);
		bonTravailEntity.setDateEntree(request.getDateEntree());
		bonTravailEntity.setDateSortiePrevue(request.getDateSortiePrevue());
		bonTravailEntity.setIndexKM(request.getIndexKM());
		bonTravailEntity.setMode(request.getMode());
		bonTravailEntity.setMode(request.getMode());
		bonTravailEntity.setCloturer(false);
		bonTravailEntity.setNatureTravaux(request.getNatureTravaux());
		bonTravailEntity.setObservation(request.getObservation());
		demandeMaintenanceEntity.setStatus("finis");
		bonTravailRepository.save(bonTravailEntity);
		demandeMaintenanceRepository.save(demandeMaintenanceEntity);
		return bonTravailRepository.findAll();
	}

	@PutMapping(path = "/bon_travail")
	ResponseEntity<HttpStatus> modifySelectedBonTravail(@RequestBody BonTravailRequest bonTravailRequest) {
		ModelMapper modelMapper = new ModelMapper();
		BonTravailDTO bonTravailDTO = modelMapper.map(bonTravailRequest, BonTravailDTO.class);
		maintenance.modifySelectedBonTravail(bonTravailDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/bon_travail/{id}")
	ResponseEntity<HttpStatus> deleteSelectedBonTravail(@PathVariable Long id) {
		maintenance.deleteSelectedBonTravail(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/articlebontravail/{id}")
	List<BonTravailArticleEntity> getAllBonArt(@PathVariable Long id) {
		return bonTravailArticleRepository.findByBonTravailId(id);
	}

	@GetMapping(path = "/articleExterneBonTravail/{id}")
	List<BonTravailArticleExterneEntity> getAllArticleExterne(@PathVariable Long id) {
		return bonTravailArticleExterneRepository.findByBonTravailId(id);
	}

	@GetMapping(path = "/operationReparationBonTravail/{id}")
	List<BonTravailOperationEntity> getAllOperations(@PathVariable Long id) {
		return bonTravailOperationRepository.findByBonTravailId(id);
	}

	@GetMapping(path = "/facturation/{id}")
	Optional<FacturationEntity> getOnFacturation(@PathVariable Long id) {
		return facturationRepository.findById(id);
	}

	@GetMapping(path = "/demande-maintenance/{id}")
	Optional<DemandeMaintenanceEntity> getOnDemandeMaintenance(@PathVariable Long id) {
		return demandeMaintenanceRepository.findById(id);
	}

	@GetMapping(path = "/find-vehicule/{id}")
	Optional<VehiculeEntity> getOneVehicule(@PathVariable Long id) {
		return vehiculeRepository.findById(id);
	}

	@GetMapping(path = "/pagination_bon_travail_filter")
	List<BonTravailDataTableResponse> getPaginationBonTravailList(@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit, @RequestParam(value = "mode") String mode,
			@RequestParam(value = "natureTravaux") String natureTravaux) {
		ModelMapper modelMapper = new ModelMapper();
		List<BonTravailDataTableResponse> bonTravailDataTableResponse = new ArrayList<>();
		List<BonTravailDataTableDTO> bonTravailDataTableDTOS = maintenance.getPaginationBonTravailList(mode,
				natureTravaux, Integer.parseInt(page), Integer.parseInt(limit));
		if (!bonTravailDataTableDTOS.isEmpty()) {
			bonTravailDataTableDTOS.forEach(bonTravailDataTableDTO -> {
				bonTravailDataTableResponse
						.add(modelMapper.map(bonTravailDataTableDTO, BonTravailDataTableResponse.class));
			});
		}
		return bonTravailDataTableResponse;
	}

	@GetMapping(path = "/pagination_demande_maintenance")
	List<DemandeMaintenanceDataTableResponse> getPaginationDemandeMaintenanceList(
			@RequestParam(value = "status") String status, @RequestParam(value = "ugp") String ugp,
			@RequestParam(value = "structure") String structure, @RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {

		System.out.println("test doo***********");
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeMaintenanceDataTableResponse> demandeMaintenanceDataTableResponses = new ArrayList<>();
		List<DemandeMaintenanceDataTableDTO> demandeMaintenanceDataTableDTOS = maintenance
				.getPaginationDemandeMaintenanceList(status, ugp, structure, Integer.parseInt(page),
						Integer.parseInt(limit));
		if (!demandeMaintenanceDataTableDTOS.isEmpty()) {
			demandeMaintenanceDataTableDTOS.forEach(demandeMaintenanceDataTableDTO -> {
				demandeMaintenanceDataTableResponses.add(
						modelMapper.map(demandeMaintenanceDataTableDTO, DemandeMaintenanceDataTableResponse.class));
			});
		}
		return demandeMaintenanceDataTableResponses;
	}

	@GetMapping(path = "/total_item_bon_travail")
	ResponseEntity<Long> getTotalItemBonTravailList() {
		Long totalIem = maintenance.getTotalItemBonTravailList();
		return new ResponseEntity<>(totalIem, HttpStatus.OK);
	}

	@PostMapping(path = "/operationToBon")
	ResponseEntity<HttpStatus> newoperation(@RequestBody UpdateBonTravailRequest updateBonTravailRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UpdateBonTravailDTO updateBonTravailDTO = modelMapper.map(updateBonTravailRequest, UpdateBonTravailDTO.class);
		maintenance.addnewoperationtobontravail(updateBonTravailDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/listOperation")
	public Iterable<OperationReparationEntity> operationList() {
		return operationReparationRepository.findAll();
	}

	// liste des tout les magasin
	@GetMapping(path = "/list_Magasin")
	public Iterable<MagasinEntity> ListMagasin() {
		return magasinRepository.findAll();
	}

	// liste des tout les magasin
	@GetMapping(path = "/list_Ugp")
	public Iterable<UgpEntity> ListUgp() {
		return ugpRepository.findAll();
	}

	// liste des status
	@GetMapping(path = "/list_status")
	public Iterable<DemandeMaintenanceEntity> ListStatus() {
		return demandeMaintenanceRepository.findAll();
	}
	
	//Recupérer la liste des articles liée a un bon de travail specifique(par un ID)
	@GetMapping(path = "/articlesForMaintenance/{id}")
	public List<BonTravailArticleEntity> li(@PathVariable Long id) {
		return bonTravailArticleRepository.findByBonTravailId(id);
	}
	
	
	// liste des tout les bons de travail pour bons de sortie avec filtrage :
	@GetMapping(path = "/pagination_bon_travail")
	ResponseEntity<List<BonTravailDataTableResponse>> ListBonTravailDataTableResponse(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(value = "magasin") String designation, @RequestParam(value = "status") String status,
			@RequestParam(value = "ugp") String ugp) {

		System.out.print("\n ********************ok******************** \n");
		ModelMapper modelMapper = new ModelMapper();
		List<BonTravailDataTableResponse> bonTravailListResponse = new ArrayList<>();
		List<BonTravailDataTableDTO> BonTravailTabDataDTOS = maintenance.getPaginationBonTravailList2(designation,
				status, ugp, Integer.parseInt(page), Integer.parseInt(limit));
		if (!BonTravailTabDataDTOS.isEmpty()) {
			BonTravailTabDataDTOS.forEach(BonTravailDataTableDTO -> {
				bonTravailListResponse.add(modelMapper.map(BonTravailDataTableDTO, BonTravailDataTableResponse.class));

			});

		}
		return new ResponseEntity<>(bonTravailListResponse, HttpStatus.OK);
	}

	private boolean isUserAuthorised(String authority, String matricule) {
		boolean isAuthorised = false;
		List<String> userAuthorities = new ArrayList<>();
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		if (!userEntity.getRoles().isEmpty()) {
			userEntity.getRoles().forEach(roleEntity -> {
				if (!StringUtils.isBlank(roleEntity.getAuthorities())) {
					List<String> tempAuthorities = Arrays
							.asList(StringUtils.splitPreserveAllTokens(roleEntity.getAuthorities(), ","));
					if (!tempAuthorities.isEmpty()) {
						tempAuthorities.forEach(s -> {
							if (!userAuthorities.contains(s)) {
								userAuthorities.add(s);
							}
						});
					}
				}
			});
		}
		if (!userAuthorities.isEmpty()) {
			if (userAuthorities.contains(authority)) {
				isAuthorised = true;
			}
		}
		return isAuthorised;
	}

	@GetMapping(path = "/List_bon_travail")
	ResponseEntity<List<BonTravailDataTableResponse>> BonTravailDataTableResponse(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<BonTravailDataTableResponse> bonTravailListResponse = new ArrayList<>();
		List<BonTravailDataTableDTO> BonTravailTabDataDTOS = maintenance
				.getPaginationBonTravailList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!BonTravailTabDataDTOS.isEmpty()) {
			BonTravailTabDataDTOS.forEach(BonTravailDataTableDTO -> {
				bonTravailListResponse.add(modelMapper.map(BonTravailDataTableDTO, BonTravailDataTableResponse.class));
			});
		}
		return new ResponseEntity<>(bonTravailListResponse, HttpStatus.OK);
	}

	
	@GetMapping(path = "/pagination_historique_demande_maintenance")
	List<DemandeMaintenanceDataTableResponse> getPaginationHistoriqueDemandeMaintenanceList(
		   @RequestParam(value = "ugp") String ugp,
			@RequestParam(value = "structure") String structure, 
			@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {
		
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeMaintenanceDataTableResponse> demandeMaintenanceDataTableResponses = new ArrayList<>();
		List<DemandeMaintenanceDataTableDTO> demandeMaintenanceDataTableDTOS = maintenance
				.getPaginationHistoriqueDemandeMaintenanceList(ugp, structure, Integer.parseInt(page),
						Integer.parseInt(limit));
		if (!demandeMaintenanceDataTableDTOS.isEmpty()) {
			demandeMaintenanceDataTableDTOS.forEach(demandeMaintenanceDataTableDTO -> {
				demandeMaintenanceDataTableResponses.add(
						modelMapper.map(demandeMaintenanceDataTableDTO, DemandeMaintenanceDataTableResponse.class));
			});
		}
		return demandeMaintenanceDataTableResponses;
	}
	
	/*@PutMapping(path = "/pagination_historique_demande_maintenance/{id}")
	private DemandeMaintenanceEntity deleteHistorique(@PathVariable Long id) {
		DemandeMaintenanceEntity h = demandeMaintenanceRepository.findById(id).get();
		return demandeMaintenanceRepository.save(h);
	}
	*/
	@DeleteMapping("/pagination_historique_demande_maintenance/{id}")
	public ResponseEntity<?> deleteHistorique(@PathVariable Long id) {
		return demandeMaintenanceRepository.findById(id).map(post -> {
			demandeMaintenanceRepository.delete(post);
			return ResponseEntity.ok().body(new ApiResponse(true, "demande deleted successfully"));
		}).orElseThrow(() -> new ResourceNotFoundException("demande  not found"));
	}

	

	@DeleteMapping("/bontravail_cloturer/{id}")
	public void reouvrirBonTravailCloturer(@PathVariable Long id) {
		
		maintenance.reouvrirBonTravail(id);
}
	

	@GetMapping(path = "/notif_maintenance")
	public int  NombreNotifRechargeCompensation() {
		return maintenance.NombreNotifIntervention();
	}
	
	@GetMapping(path = "/notifValid_maintenance")
	public int  NombreNotifValidRechargeCompensation() {
		return maintenance.NombreNotifValidIntervention();
	}
	
	
	
}