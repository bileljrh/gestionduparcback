package com.gesparc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteAgilis.DeclarationPerteCarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteAgilis.DemandeAnnulationCarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteAgilis.RechargeCarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;
import com.gesparc.repositories.CarteAgilisCashRepository;
import com.gesparc.repositories.CartePlafondRepository;
import com.gesparc.repositories.DeclarationPerteCarteAgilisCashRepository;
import com.gesparc.repositories.DemandeAnnulationCarteAgilisCashRepository;
import com.gesparc.repositories.HistoriqueAffectationCarteAgilisCashRepository;
import com.gesparc.repositories.RechargeCarteAgilisCashRepository;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.requests.DeclarationPerteCarteAgilisCashRequest;
import com.gesparc.requests.DemandeAnnulationCarteAgilisCashRequest;
import com.gesparc.requests.DemandeAnnulationCartePlafondRequest;
import com.gesparc.requests.Carburant.DemandeAffectationCarteAgilisCashRequest;
import com.gesparc.requests.Carburant.NewCarteAgilisCashRequest;
import com.gesparc.requests.Carburant.NewRechargeCarteAgilisCashRequest;
import com.gesparc.responses.DeclarationPerteCarteAgilisCashResponse;
import com.gesparc.responses.DeclarationPerteCarteJockerResponse;
import com.gesparc.responses.DemandeAffectationCarteAgilisCashResponse;
import com.gesparc.responses.DemandeAnnulationCarteAgilisCashResponse;
import com.gesparc.responses.HistoriqueAffectationCarteAgilisCashResponse;
import com.gesparc.responses.administratif.additionnel.SelectVehiculeResponse;
import com.gesparc.responses.carburant.ListVehiculesServiceResponse;
import com.gesparc.responses.carburant.additionnel.CarteAgilisCashTabDataResponse;
import com.gesparc.responses.carburant.additionnel.DemandeAnnulationCarteAgilisCashTabDataResponse;
import com.gesparc.responses.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataResponse;
import com.gesparc.responses.carburant.additionnel.ListCarteAgilisCashResponse;
import com.gesparc.responses.carburant.additionnel.RechargeCarteAgilisCashTabDataResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.CarteAgilisCashImpl;
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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class CarteAgilisCashController {

	@Autowired
	CarteAgilisCashImpl carteAgilisCash;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VehiculeRepository vehiculeRepository;

	@Autowired
	private CarteAgilisCashRepository carteAgilisCashRepository;

	@Autowired
	private DemandeAnnulationCarteAgilisCashRepository demandeAnnulationCarteAgilisCashRepository;

	@Autowired
	private DeclarationPerteCarteAgilisCashRepository declarationPerteCarteAgilisCashRepository;

	@Autowired
	private RechargeCarteAgilisCashRepository rechargeCarteAgilisCashRepository;

	@Autowired
	private HistoriqueAffectationCarteAgilisCashRepository historiqueAffectationCarteAgilisCashRepository;

	@Autowired
	private TracabiliteController tracabiliteController;

	public CarteAgilisCashController(TracabiliteController tracabiliteController) {
		super();
		this.tracabiliteController = tracabiliteController;
	}

	@PostMapping(path = "/new_recharge_carte_agilis_cash")
	public ResponseEntity<HttpStatus> createNewRechargeRequestCarteAgilisCash(
			@RequestBody NewRechargeCarteAgilisCashRequest newRechargeCarteAgilisCashRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewRechargeCarteAgilisCashDTO newRechargeCarteAgilisCashDTO = modelMapper.map(newRechargeCarteAgilisCashRequest,
				NewRechargeCarteAgilisCashDTO.class);
		carteAgilisCash.createNewRechargeRequestCarteAgilisCash(newRechargeCarteAgilisCashDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/list_recharge_carte_agilis_cash")
	public List<DemandeAffectationCarteAgilisCashResponse> getListDemandeRechargeCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAffectationCarteAgilisCashResponse> demandeAffectationCarteAgilisCashResponses = new ArrayList<>();
		List<DemandeAffectationCarteAgilisCashDTO> demandeAffectationCarteAgilisCashDTOS = carteAgilisCash
				.getListDemandeRechargeCarteAgilisCash();
		if (!demandeAffectationCarteAgilisCashDTOS.isEmpty()) {
			demandeAffectationCarteAgilisCashDTOS.forEach(demandeAffectationCarteAgilisCashDTO -> {
				demandeAffectationCarteAgilisCashResponses.add(modelMapper.map(demandeAffectationCarteAgilisCashDTO,
						DemandeAffectationCarteAgilisCashResponse.class));
			});
		}
		return demandeAffectationCarteAgilisCashResponses;
	}

	@DeleteMapping(path = "/recharge_carte_agilis_cash/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedRechargeCarteAgilisCash(@PathVariable Long id) {
		carteAgilisCash.deleteSelectedRechargeCarteAgilisCash(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/recharge_carte_agilis_cash")
	public ResponseEntity<HttpStatus> modifySelectedRechargeCarteAgilisCash(
			@RequestBody NewRechargeCarteAgilisCashRequest newRechargeCarteAgilisCashRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewRechargeCarteAgilisCashDTO newRechargeCarteAgilisCashDTO = modelMapper.map(newRechargeCarteAgilisCashRequest,
				NewRechargeCarteAgilisCashDTO.class);
		carteAgilisCash.modifySelectedRechargeCarteAgilisCash(newRechargeCarteAgilisCashDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/historique_recharge_carte_agilis_cash")
	public List<HistoriqueAffectationCarteAgilisCashResponse> getListHistoriqueCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<HistoriqueAffectationCarteAgilisCashResponse> historiqueAffectationCarteAgilisCashResponses = new ArrayList<>();
		List<HistoriqueAffectationCarteAgilisCashDTO> historiqueAffectationCarteAgilisCashDTOS = carteAgilisCash
				.getListHistoriqueCarteAgilisCash();
		if (!historiqueAffectationCarteAgilisCashDTOS.isEmpty()) {
			historiqueAffectationCarteAgilisCashDTOS.forEach(historiqueRechargeCarteAgilisCashDTO -> {
				historiqueAffectationCarteAgilisCashResponses.add(modelMapper.map(historiqueRechargeCarteAgilisCashDTO,
						HistoriqueAffectationCarteAgilisCashResponse.class));
			});
		}
		return historiqueAffectationCarteAgilisCashResponses;
	}

	@DeleteMapping(path = "/historique_recharge_carte_agilis_cash/{id}")
	public void deleteSelectedHistoriqueRechargeCarteAgilisCash(@PathVariable Long id) {
		carteAgilisCash.deleteSelectedHistoriqueRechargeCarteAgilisCash(id);
	}

	@PostMapping(path = "/declaration_perte_carte_agilis_cash")
	public void createOneDeclarationPerteCarteAgilisCash(
			@RequestBody DeclarationPerteCarteAgilisCashRequest declarationPerteCarteAgilisCashRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCarteAgilisCashDTO declarationPerteCarteAgilisCashDTO = modelMapper
				.map(declarationPerteCarteAgilisCashRequest, DeclarationPerteCarteAgilisCashDTO.class);
		carteAgilisCash.createOneDeclarationPerteCarteAgilisCash(declarationPerteCarteAgilisCashDTO);
	}

	@GetMapping(path = "/declaration_perte_carte_agilis_cash")
	public List<DeclarationPerteCarteAgilisCashResponse> getListDeclarationPerteCarteAgilisCashByConfirmation(
			@RequestParam boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteAgilisCashResponse> declarationPerteCarteAgilisCashResponses = new ArrayList<>();
		List<DeclarationPerteCarteAgilisCashDTO> declarationPerteCarteAgilisCashDTOS = carteAgilisCash
				.getListDeclarationPerteCarteAgilisCashByConfirmation(confirmation);
		if (!declarationPerteCarteAgilisCashDTOS.isEmpty()) {
			declarationPerteCarteAgilisCashDTOS.forEach(declarationPerteCarteAgilisCashDTO -> {
				declarationPerteCarteAgilisCashResponses.add(modelMapper.map(declarationPerteCarteAgilisCashDTO,
						DeclarationPerteCarteAgilisCashResponse.class));
			});
		}
		return declarationPerteCarteAgilisCashResponses;
	}

	@PostMapping(path = "/confirm_declaration_perte_carte_agilis_cash")
	public void confirmDeclarationPerteCarteAgilisCash(@RequestBody Long id) {
		carteAgilisCash.confirmDeclarationPerteCarteAgilisCash(id);
	}

	@DeleteMapping(path = "/declaration_perte_carte_agilis_cash/{id}")
	public void deleteOneDeclarationPerteCarteAgilisCash(@PathVariable Long id) {
		carteAgilisCash.deleteOneDeclarationPerteCarteAgilisCash(id);
	}

	@PutMapping(path = "/declaration_perte_carte_agilis_cash")
	public void modifyOneDeclarationPerteCarteAgilisCash(
			@RequestBody DeclarationPerteCarteAgilisCashRequest declarationPerteCarteAgilisCashRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCarteAgilisCashDTO declarationPerteCarteAgilisCashDTO = modelMapper
				.map(declarationPerteCarteAgilisCashRequest, DeclarationPerteCarteAgilisCashDTO.class);
		carteAgilisCash.modifyOneDeclarationPerteCarteAgilisCash(declarationPerteCarteAgilisCashDTO);
	}

	@GetMapping(path = "/historique_declaration_perte_carte_agilis_cash")
	public List<DeclarationPerteCarteAgilisCashResponse> getHistoriqueDeclarationPerteCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteAgilisCashResponse> declarationPerteCarteAgilisCashResponses = new ArrayList<>();
		List<DeclarationPerteCarteAgilisCashDTO> declarationPerteCarteAgilisCashDTOS = carteAgilisCash
				.getHistoriqueDeclarationPerteCarteAgilisCash();
		if (!declarationPerteCarteAgilisCashDTOS.isEmpty()) {
			declarationPerteCarteAgilisCashDTOS.forEach(declarationPerteCarteAgilisCashDTO -> {
				declarationPerteCarteAgilisCashResponses.add(modelMapper.map(declarationPerteCarteAgilisCashDTO,
						DeclarationPerteCarteAgilisCashResponse.class));
			});
		}
		return declarationPerteCarteAgilisCashResponses;
	}

	@PostMapping(path = "/demande_annulation_carte_agilis_cash")
	public void createNewDemandeAnnulationCarteAgilisCash(
			@RequestBody DemandeAnnulationCarteAgilisCashRequest DemandeAnnulationCarteAgilisCashDTO) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCarteAgilisCashDTO demandeAnnulationCarteAgilisCashDTO = modelMapper
				.map(DemandeAnnulationCarteAgilisCashDTO, DemandeAnnulationCarteAgilisCashDTO.class);
		tracabiliteController.createNewDemandeAnnulationCarteAgilisCashTracabilite(
				demandeAnnulationCarteAgilisCashDTO.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		carteAgilisCash.createNewDemandeAnnulationCarteCarburant(demandeAnnulationCarteAgilisCashDTO);
	}

	@GetMapping(path = "/historique_annulation_carte_agilis_cash/{confirmation}")
	public List<DemandeAnnulationCarteAgilisCashRequest> getHistoriqueDemandeAnnulationCarteAgilisCashByConfirmation(
			@PathVariable boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAnnulationCarteAgilisCashRequest> demandeAnnulationCarteAgilisCashRequests = new ArrayList<>();
		List<DemandeAnnulationCarteAgilisCashDTO> demandeAnnulationCarteAgilisCashDTOS = carteAgilisCash
				.getHistoriqueDemandeAnnulationCarteAgilisCashByConfirmation(confirmation);
		if (!demandeAnnulationCarteAgilisCashDTOS.isEmpty()) {
			demandeAnnulationCarteAgilisCashDTOS.forEach(demandeAnnulationCarteAgilisCashDTO -> {
				demandeAnnulationCarteAgilisCashRequests.add(modelMapper.map(demandeAnnulationCarteAgilisCashDTO,
						DemandeAnnulationCarteAgilisCashRequest.class));
			});
		}
		return demandeAnnulationCarteAgilisCashRequests;
	}

	@DeleteMapping(path = "/historique_annulation_carte_agilis_cash/{id}")
	public void deleteDemandeAnnulationCarteAgilisCash(@PathVariable Long id) {
		DemandeAnnulationCarteAgilisCashEntity demandeAnnulationCarteAgilisCashEntity = demandeAnnulationCarteAgilisCashRepository
				.findById(id).get();
		tracabiliteController.deleteDemandeAnnulationCarteAgilisCashTracabilite(
				demandeAnnulationCarteAgilisCashEntity.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

		carteAgilisCash.deleteDemandeAnnulationCarteAgilisCash(id);
	}

	@GetMapping(path = "/list_vehicule_service_with_no_cart_agilis_cash")
	List<ListVehiculesServiceResponse> getListVehiculesServiceWithNoCarteAgilisCash() {
		List<ListVehiculesServiceResponse> listVehiculesServiceResponses = new ArrayList<>();
		List<VehiculeDTO> vehiculeDTOS = carteAgilisCash.getListVehiculesServiceWithNoCarteAgilisCash();
		if (!vehiculeDTOS.isEmpty()) {
			vehiculeDTOS.forEach(vehiculeDTO -> {
				ListVehiculesServiceResponse listVehiculesServiceResponse = new ListVehiculesServiceResponse();
				listVehiculesServiceResponses.add(listVehiculesServiceResponse);
			});
		}
		return listVehiculesServiceResponses;
	}

	@GetMapping(path = "/carte_agilis_cash_not_recharged")
	ResponseEntity<List<ListCarteAgilisCashResponse>> getListCarteAgilisCash() {
		ModelMapper modelMapper = new ModelMapper();
		List<ListCarteAgilisCashResponse> listCarteAgilisCashes = new ArrayList<>();
		List<ListCarteAgilisCashDTO> listCarteAgilisCashDTOS = carteAgilisCash.getListCarteAgilisCash();
		if (!listCarteAgilisCashDTOS.isEmpty()) {
			listCarteAgilisCashDTOS.forEach(listCarteAgilisCashDTO -> {
				listCarteAgilisCashes.add(modelMapper.map(listCarteAgilisCashDTO, ListCarteAgilisCashResponse.class));
			});
		}
		return new ResponseEntity<>(listCarteAgilisCashes, HttpStatus.OK);
	}

	@GetMapping("/list-carte-agilis-nest-pas-encore-annuler")
	public List<CarteAgilisCashEntity> listCarsAnnuler() {
		return carteAgilisCashRepository.getAllCars();
	}

	@GetMapping(path = "/list_all_carte_agilis_cash")
	List<ListCarteAgilisCashResponse> getListAllCarteAgilisCash() {
		List<ListCarteAgilisCashResponse> listCarteAgilisCashes = new ArrayList<>();
		List<CarteAgilisCashDTO> carteAgilisCashDTOS = carteAgilisCash.getListAllCarteAgilisCash();
		if (!carteAgilisCashDTOS.isEmpty()) {
			carteAgilisCashDTOS.forEach(carteAgilisCashDTO -> {
				ListCarteAgilisCashResponse listCarteAgilisCash = new ListCarteAgilisCashResponse();
				listCarteAgilisCash.setNumeroCarte(carteAgilisCashDTO.getNumeroCarte());
				listCarteAgilisCash.setTypeCarburant(carteAgilisCashDTO.getVehicule().getEnergie().getDescription());
				listCarteAgilisCash.setSoldeRestant(carteAgilisCashDTO.getSoldeRestant());
				listCarteAgilisCash.setIdVehicule(carteAgilisCashDTO.getVehicule().getId());
				listCarteAgilisCash.setIdBeneficiaire(carteAgilisCashDTO.getVehicule().getBeneficiaire().getId());
				listCarteAgilisCash
						.setMatriculeBeneficiaire(carteAgilisCashDTO.getVehicule().getBeneficiaire().getMatricule());
				listCarteAgilisCash.setNomBeneficiaire(carteAgilisCashDTO.getVehicule().getBeneficiaire().getNom());
				listCarteAgilisCash.setNumeroPlaque(carteAgilisCashDTO.getVehicule().getNumeroPlaque());
				listCarteAgilisCashes.add(listCarteAgilisCash);
			});
		}
		return listCarteAgilisCashes;
	}

	@GetMapping(path = "/list_declaration_perte_carte_agilis_cash")
	public Iterable<DeclarationPerteCarteAgilisCashEntity> test(@RequestParam boolean confirmed) {
		return declarationPerteCarteAgilisCashRepository.findAllByConfirmed(confirmed);
	}

	@GetMapping(path = "/list_demande_annulation_carte_agilis_cash/{confirmed}")
	List<DemandeAnnulationCarteAgilisCashResponse> getHistoriqueDemandeAnnulationCarteAgilisCashByConfirmationn(
			@PathVariable boolean confirmed) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAnnulationCarteAgilisCashResponse> demandeAnnulationCarteAgilisCashResponses = new ArrayList<>();
		List<DemandeAnnulationCarteAgilisCashDTO> demandeAnnulationCarteAgilisCashDTOS = carteAgilisCash
				.getHistoriqueDemandeAnnulationCarteAgilisCashByConfirmation(confirmed);
		if (!demandeAnnulationCarteAgilisCashDTOS.isEmpty()) {
			demandeAnnulationCarteAgilisCashDTOS.forEach(demandeAnnulationCarteAgilisCashDTO -> {
				demandeAnnulationCarteAgilisCashResponses.add(modelMapper.map(demandeAnnulationCarteAgilisCashDTO,
						DemandeAnnulationCarteAgilisCashResponse.class));
			});
		}
		return demandeAnnulationCarteAgilisCashResponses;
	}

	@GetMapping(path = "/pagination_annulation_carte_agilis")
	ResponseEntity<List<DemandeAnnulationCarteAgilisCashTabDataResponse>> getPaginationDemandesAffectationCarteJocker(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "confirmed") String confirmed) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAnnulationCarteAgilisCashTabDataResponse> demandeAnnulationCarteAgilisCashTabDataResponses = new ArrayList<>();
		List<DemandeAnnulationCarteAgilisCashDataTableDTO> demandeAnnulationCarteAgilisCashDataTableDTOS = carteAgilisCash
				.getListDemandeAnnulationCarteAgilisCash(Integer.parseInt(page), Integer.parseInt(limit), confirmed);
		if (!demandeAnnulationCarteAgilisCashDataTableDTOS.isEmpty()) {
			demandeAnnulationCarteAgilisCashDataTableDTOS.forEach(demandeAnnulationCarteAgilisCashDataTableDTO -> {
				demandeAnnulationCarteAgilisCashTabDataResponses
						.add(modelMapper.map(demandeAnnulationCarteAgilisCashDataTableDTO,
								DemandeAnnulationCarteAgilisCashTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(demandeAnnulationCarteAgilisCashTabDataResponses, HttpStatus.OK);
	}

	@PutMapping(path = "/demande_annulation_carte_agilis_cash/{id}")
	void confirmDemandeAnnulationCarteAgilisCash(@PathVariable Long id) {
		tracabiliteController.confirmDemandeAnnulationCarteAgilisCashTracabilite(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		DemandeAnnulationCarteAgilisCashEntity dacae = demandeAnnulationCarteAgilisCashRepository.findById(id).get();
		dacae.setConfirmed(true);
		this.test(dacae.getIdCard());
		demandeAnnulationCarteAgilisCashRepository.save(dacae);
	}

	private ResponseEntity<HttpStatus> test(@PathVariable Long id) {
		carteAgilisCash.deleteSelectedCarteAgilisCash(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/demande_annulation_agilis")
	public void modifyOneDemandeAnnulationCarteAgilis(
			@RequestBody DemandeAnnulationCarteAgilisCashRequest demandeAnnulationCarteAgilisCashRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCarteAgilisCashDTO demandeAnnulationCarteAgilisCashDTO = modelMapper
				.map(demandeAnnulationCarteAgilisCashRequest, DemandeAnnulationCarteAgilisCashDTO.class);
		carteAgilisCash.modifyOneDemandeAnnulationCarteAgilis(demandeAnnulationCarteAgilisCashDTO);
	}

	@GetMapping(path = "/select_vehicule_carte_agilis_cash")
	ResponseEntity<List<SelectVehiculeResponse>> getSelectVehiculeByStrucutureForAgilisCash() {
		List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository
				.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		List<SelectVehiculeDTO> selectVehiculeDTOS = carteAgilisCash
				.getSelectVehiculeByStrucutureForAgilisCash(userEntity);
		if (!selectVehiculeDTOS.isEmpty()) {
			selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
				selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));
			});
		}
		return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}

	@PostMapping(path = "/carte_agilis")
	ResponseEntity<HttpStatus> createNewCarteAgilisCash(
			@RequestBody NewCarteAgilisCashRequest newCarteAgilisCashRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewCarteAgilisCashDTO newCarteAgilisCashDTO = modelMapper.map(newCarteAgilisCashRequest,
				NewCarteAgilisCashDTO.class);
		carteAgilisCash.createNewCarteAgilisCash(newCarteAgilisCashDTO);
		tracabiliteController.createNewCarteAgilisCashTracabilite(newCarteAgilisCashDTO.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/carte_agilis")
	ResponseEntity<HttpStatus> modifySelectedCarteAgilisCash(
			@RequestBody NewCarteAgilisCashRequest newCarteAgilisCashRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewCarteAgilisCashDTO newCarteAgilisCashDTO = modelMapper.map(newCarteAgilisCashRequest,
				NewCarteAgilisCashDTO.class);
		carteAgilisCash.modifySelectedCarteAgilisCash(newCarteAgilisCashDTO);
		tracabiliteController.modifySelectedCarteAgilisCashTracabilite(newCarteAgilisCashDTO.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/confirm_carte_agilis")
	ResponseEntity<HttpStatus> confirmSelectedCarteAgilisCash(@RequestBody Long id) {
		RechargeCarteAgilisCashEntity rechargeCarteAgilis = rechargeCarteAgilisCashRepository.findById(id).get();
		tracabiliteController.confirmSelectedCarteAgilisCashTracabilite(
				rechargeCarteAgilis.getCarteAgilisCash().getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		carteAgilisCash.confirmSelectedCarteAgilisCash(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_carte_agilis")
	ResponseEntity<HttpStatus> validateSelectedCarteAgilisCash(@RequestBody Long id) {
		RechargeCarteAgilisCashEntity rechargeCarteAgilis = rechargeCarteAgilisCashRepository.findById(id).get();
		tracabiliteController.validateSelectedCarteAgilisCashTracabilite(
				rechargeCarteAgilis.getCarteAgilisCash().getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		carteAgilisCash.validateSelectedCarteAgilisCash(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_carte_agilis_cash")
	ResponseEntity<List<CarteAgilisCashTabDataResponse>> getPaginationListCarteAgilisCashByTypeCarburant(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "typeCarburant") String typeCarburant) {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteAgilisCashTabDataResponse> carteAgilisCashTabDataResponses = new ArrayList<>();
		List<CarteAgilisCashTabDataDTO> carteAgilisCashDTOS = carteAgilisCash
				.getPaginationListCarteAgilisCashByTypeCarburant(Integer.parseInt(page), Integer.parseInt(limit),
						typeCarburant);
		if (!carteAgilisCashDTOS.isEmpty()) {
			carteAgilisCashDTOS.forEach(carteAgilisCashTabDataDTO -> {
				carteAgilisCashTabDataResponses
						.add(modelMapper.map(carteAgilisCashTabDataDTO, CarteAgilisCashTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(carteAgilisCashTabDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_recharge_carte_agilis_cash")
	ResponseEntity<List<RechargeCarteAgilisCashTabDataResponse>> getPaginationListRechargeCarteAgilisCash(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RechargeCarteAgilisCashTabDataResponse> rechargeCarteAgilisCashTabDataResponses = new ArrayList<>();
		List<RechargeCarteAgilisCashTabDataDTO> rechargeCarteAgilisCashTabDataDTOS = carteAgilisCash
				.getPaginationListRechargeCarteAgilisCash(Integer.parseInt(page), Integer.parseInt(limit));
		if (!rechargeCarteAgilisCashTabDataDTOS.isEmpty()) {
			rechargeCarteAgilisCashTabDataDTOS.forEach(rechargeCarteAgilisCashTabDataDTO -> {
				rechargeCarteAgilisCashTabDataResponses.add(modelMapper.map(rechargeCarteAgilisCashTabDataDTO,
						RechargeCarteAgilisCashTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(rechargeCarteAgilisCashTabDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_annulation_carte_agilis_agilis_cash")
	ResponseEntity<Long> getTotalNumberAnnulationCarteAgilisCash() {
		Long totalItem = carteAgilisCash.getTotalNumberAnnulationCarteAgilisCash();
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_recharge_carte_agilis_cash")
	ResponseEntity<Long> getTotalNumberListRechargeCarteAgilisCash() {
		Long totalItem = carteAgilisCash.getTotalNumberListRechargeCarteAgilisCash();
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_carte_agilis_cash")
	ResponseEntity<Long> getTotalNumberListCarteAgilisCashByTypeCarburant(
			@RequestParam(name = "typeCarburant") String typeCarburant) {
		Long totalNumber = carteAgilisCash.getTotalNumberListCarteAgilisCashByTypeCarburant(typeCarburant);
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

	@DeleteMapping(path = "/carte_agilis_cash/{id}")
	ResponseEntity<HttpStatus> deleteSelectedCarteAgilisCash(@PathVariable Long id) {
		CarteAgilisCashEntity carteAgilis = carteAgilisCashRepository.findById(id).get();
		tracabiliteController.deleteSelectedCarteAgilisCashTracabilite(carteAgilis.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		carteAgilisCash.deleteSelectedCarteAgilisCash(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/carte_Agilis")
	public Iterable<CarteAgilisCashEntity> getListeCarteAgilisCash() {
		return carteAgilisCashRepository.findAll();
	}

	@GetMapping(path = "/pagination_declaration_perte_carteAgilis")
	ResponseEntity<List<DeclarationPerteCarteAgilisCashResponse>> getPaginationdeclarationpertecarteagilis(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "confirmed") String confirmed) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteAgilisCashResponse> declarationPerteCarteAgilisCashResponse = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<DeclarationPerteCarteAgilisCashDTO> declarationPerteCarteAgilisCashDTOS = carteAgilisCash
				.getPaginationdeclarationperteCarteAgilis(Integer.parseInt(page), Integer.parseInt(limit), confirmed);
		if (!declarationPerteCarteAgilisCashDTOS.isEmpty()) {
			declarationPerteCarteAgilisCashDTOS.forEach(declarationPerteCarteAgilisCashDTO -> {
				declarationPerteCarteAgilisCashResponse.add(modelMapper.map(declarationPerteCarteAgilisCashDTO,
						DeclarationPerteCarteAgilisCashResponse.class));
			});
		}
		return new ResponseEntity<>(declarationPerteCarteAgilisCashResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_declaration_carte_Agilis")
	ResponseEntity<Long> getTotalNumberdeclarationperteCarteAgilis() {
		Long totalNumber = carteAgilisCash.getTotalNumberdeclarationPerteCarteAgilis();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}
}
