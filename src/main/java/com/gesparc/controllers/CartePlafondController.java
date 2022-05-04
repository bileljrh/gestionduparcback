package com.gesparc.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.DeclarationPerteCartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.DemandeAnnulationCartePlafondEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.repositories.CartePlafondRepository;
import com.gesparc.repositories.DeclarationPerteCartePlafondRepository;
import com.gesparc.repositories.DemandeAnnulationCartePlafondRepository;
import com.gesparc.repositories.HistoriqueAffectationCartePlafondRepository;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.requests.DeclarationPerteCartePlafondRequest;
import com.gesparc.requests.DemandeAnnulationCartePlafondRequest;
import com.gesparc.requests.HistoriqueAffectationCartePlafondRequest;
import com.gesparc.requests.Carburant.NewCartePlafondRequest;
import com.gesparc.responses.DeclarationPerteCarteJockerResponse;
import com.gesparc.responses.DeclarationPerteCartePlafondResponse;
import com.gesparc.responses.HistoriqueAffectationCartePlafondResponse;
import com.gesparc.responses.administratif.additionnel.SelectVehiculeResponse;
import com.gesparc.responses.carburant.additionnel.CartePlafondTabDataResponse;
import com.gesparc.responses.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.CartePlafondImpl;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.DeclarationPerteCartePlafondDTO;
import com.gesparc.sharedDTO.DemandeAnnulationCartePlafondDTO;
import com.gesparc.sharedDTO.HistoriqueAffectationCartePlafondDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.NewCartePlafondDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CartePlafondTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataDTO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CartePlafondController {

	Logger logger = LoggerFactory.getLogger(CartePlafondController.class);

	CartePlafondImpl cartePlafond;

	UserRepository userRepository;

	@Autowired
	VehiculeRepository vehiculeRepository;

	@Autowired
	HistoriqueAffectationCartePlafondRepository historiqueAffectationCartePlafondRepository;

	@Autowired
	private DemandeAnnulationCartePlafondRepository demandeAnnulationCartePlafondRepository;

	@Autowired
	private CartePlafondRepository cartePlafondRepository;

	@Autowired
	DeclarationPerteCartePlafondRepository declarationPerteCartePlafondRepository;

	@Autowired
	private TracabiliteController tracabiliteController;

	public CartePlafondController(CartePlafondImpl cartePlafond, UserRepository userRepository,
			TracabiliteController tracabiliteController) {
		this.cartePlafond = cartePlafond;
		this.userRepository = userRepository;
		this.tracabiliteController = tracabiliteController;
	}

	@PostMapping("/demande_affectation_carte_plafond")
	public HistoriqueAffectationCartePlafondEntity createNewDemandAffectationCartePlafond(
			@RequestBody HistoriqueAffectationCartePlafondRequest request) {
		HistoriqueAffectationCartePlafondEntity hacp = new HistoriqueAffectationCartePlafondEntity();
		hacp.setDateAffectation(request.getDateAffectation());
		hacp.setIdVehicule(request.getIdVehicule());
		System.out.println(request.getIdVehicule());
		hacp.setTypeCarburant(request.getTypeCarburant());
		hacp.setNumeroCarte(request.getNumeroCarte());
		hacp.setCartePlafond(request.getCartePlafond());
		CartePlafondEntity cpe = cartePlafondRepository.findById(request.getCartePlafond().getId()).get();
		VehiculeEntity ve = vehiculeRepository.findById(request.getIdVehicule()).get();
		hacp.setNumeroPlaque(ve.getNumeroPlaque());
		System.out.println(request.getTypeCarburant());
		hacp.setStructure(request.getStructure());
		hacp.setNomBeneficiaire(request.getNomBeneficiaire());
		hacp.setMatriculeBeneficiaire(request.getMatriculeBeneficiaire());
		cpe.setTypeCarburant(request.getTypeCarburant());
		cpe.setVehicule(ve);
		ve.setCartePlafond(cpe);
		vehiculeRepository.save(ve);
		cartePlafondRepository.save(cpe);
		tracabiliteController.createNewDemandAffectationCartePlafondTracabilite(
				request.getCartePlafond().getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return historiqueAffectationCartePlafondRepository.save(hacp);
	}

	@GetMapping("/Card-with-no-card-Plafond")
	public List<CartePlafondEntity> CardWithNoCar() {
		return cartePlafondRepository.findAllByVehiculeIdIsNull();
	}

	@GetMapping("/testcart")
	private List<Long> lisetCardNonAnnuler() {
		return demandeAnnulationCartePlafondRepository.listCard();
	}

	@GetMapping("/list-carte-nest-pas-encore-annuler")
	public List<CartePlafondEntity> listCarsAnnuler() {
		return cartePlafondRepository.getAllCars();
	}

	@PostMapping(path = "/confirm_demande_affectation_cartes_plafond/{id}")
	public ResponseEntity<HttpStatus> confirmDemandeAffectationCartePlafond(@PathVariable Long id) {
		cartePlafond.confirmDemandeAffectationCartePlafond(id);
		tracabiliteController.confirmDemandeAffectationCartePlafondTracabilite(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/carte_plafond")
	public ResponseEntity<HttpStatus> createNewCartePlafond(
			@RequestBody NewCartePlafondRequest newCartePlafondRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewCartePlafondDTO newCartePlafondDTO = modelMapper.map(newCartePlafondRequest, NewCartePlafondDTO.class);
		cartePlafond.createNewCartePlafond(newCartePlafondDTO);
		tracabiliteController.createNewCartePlafondTracabilite(newCartePlafondDTO.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/carte_plafond")
	public ResponseEntity<HttpStatus> modifySelectedCartePlafond(
			@RequestBody NewCartePlafondRequest newCartePlafondRequest) {
		System.out.println(newCartePlafondRequest.toString());
		ModelMapper modelMapper = new ModelMapper();
		NewCartePlafondDTO newCartePlafondDTO = modelMapper.map(newCartePlafondRequest, NewCartePlafondDTO.class);
		cartePlafond.modifySelectedCartePlafond(newCartePlafondDTO);
		tracabiliteController.modifySelectedCartePlafondTracabilite(newCartePlafondDTO.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/carte_plafond/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedCartePlafond(@PathVariable Long id) {
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository.findById(id).get();
		tracabiliteController.deleteSelectedCartePlafondTracabilte(cartePlafondEntity.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		cartePlafond.deleteSelectedCartePlafond(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_list_carte_plafond")
	public ResponseEntity<List<CartePlafondTabDataResponse>> getPaginationListCartePlafondByTypeCarburant(
			@RequestParam(name = "typeCarburant") String typeCarburant, @RequestParam(name = "page") String page,
			@RequestParam(name = "limit") String limit) {
		List<CartePlafondTabDataResponse> cartePlafondTabDataResponses = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		List<CartePlafondTabDataDTO> cartePlafondTabDataDTOS = cartePlafond
				.getPaginationListCartePlafondByTypeCarburant(typeCarburant, Integer.parseInt(page),
						Integer.parseInt(limit));
		if (!cartePlafondTabDataDTOS.isEmpty()) {
			cartePlafondTabDataDTOS.forEach(cartePlafondDTO -> {
				cartePlafondTabDataResponses.add(modelMapper.map(cartePlafondDTO, CartePlafondTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(cartePlafondTabDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_list_carte_plafond")
	public ResponseEntity<Long> getTotalNumberListCartePlafondByTypeCarburant(
			@RequestParam(name = "typeCarburant") String typeCarburant) {
		Long totalItem = cartePlafond.getTotalNumberListCartePlafondByTypeCarburant(typeCarburant);
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@GetMapping(path = "/carte_plafond_vehicule")
	public ResponseEntity<List<SelectVehiculeResponse>> getListVehiculeWithNoCartePlafond() {
		ModelMapper modelMapper = new ModelMapper();
		List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		UserEntity userEntity = userRepository
				.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		List<SelectVehiculeDTO> selectVehiculeDTOS = cartePlafond.getListVehiculeWithNoCartePlafond(userEntity);
		if (!selectVehiculeDTOS.isEmpty()) {
			selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
				selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));
			});
		}
		return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}

	@PostMapping(path = "/validate_demande_affectation_cartes_plafond/{idCarte}")
	public ResponseEntity<HttpStatus> validateDemandeAffectationCartePlafond(@PathVariable Long idCarte) {
		cartePlafond.validateDemandeAffectationCartePlafond(idCarte);
		tracabiliteController.validateDemandeAffectationCartePlafondTracabilite(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/demande_affectation_carte_plafond/{id}")
	public ResponseEntity<HttpStatus> deleteDemandAffectationCartePlafond(@PathVariable Long id) {
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository.findById(id).get();
		cartePlafond.deleteDemandAffectationCartePlafond(id);
		tracabiliteController.deleteDemandAffectationCartePlafondTracabilite(cartePlafondEntity.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/total_items_historique_affectation_cartes_plafond/{typeCarburant}")
	public ResponseEntity<Long> getTotalItemsHistoriqueAffectationCartesPlafondBySelection(
			@PathVariable String typeCarburant) {
		Long totalItem = cartePlafond.getTotalItemsHistoriqueAffectationCartesPlafondBySelection(typeCarburant);
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_historique_affectation_carte_plafond")
	ResponseEntity<List<HistoriqueAffectationCartePlafondTabDataResponse>> getPaginationDemandesAffectationCarteJocker(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "selectedTypeCarburant") String selectedTypeCarburant) {
		ModelMapper modelMapper = new ModelMapper();
		List<HistoriqueAffectationCartePlafondTabDataResponse> historiqueAffectationCarteJockerTabDataResponses = new ArrayList<>();
		List<HistoriqueAffectationCartePlafondTabDataDTO> historiqueAffectationCartePlafondTabDataDTOS = cartePlafond
				.getPaginationHistoriqueAffectationCartePlafond(Integer.parseInt(page), Integer.parseInt(limit),
						selectedTypeCarburant);
		if (!historiqueAffectationCartePlafondTabDataDTOS.isEmpty()) {
			historiqueAffectationCartePlafondTabDataDTOS.forEach(historiqueAffectationCartePlafondTabDataDTO -> {
				historiqueAffectationCarteJockerTabDataResponses
						.add(modelMapper.map(historiqueAffectationCartePlafondTabDataDTO,
								HistoriqueAffectationCartePlafondTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(historiqueAffectationCarteJockerTabDataResponses, HttpStatus.OK);
	}

	@DeleteMapping(path = "/historique_affectation_carte_plafond/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedHistoriqueAffectationCartePlafond(@PathVariable Long id) {
		cartePlafond.deleteSelectedHistoriqueAffectationCartePlafond(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/carte_plafond")
	public Iterable<CartePlafondEntity> getListCartePlafond() {
		return cartePlafondRepository.findAll();

	}

	@GetMapping(path = "/list_declaration_perte_carte_plafond")
	public Iterable<DeclarationPerteCartePlafondEntity> test(@RequestParam boolean confirmation) {
		return declarationPerteCartePlafondRepository.getListDeclarationPerteCartePlafondByConfirmation(confirmation);
	}

	@PostMapping(path = "/declaration_perte_carte_plafond")
	public ResponseEntity<HttpStatus> createNewDeclarationPerteCartePlafond(
			@RequestBody DeclarationPerteCartePlafondRequest declarationPerteCartePlafondRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCartePlafondDTO declarationPerteCartePlafondDTO = modelMapper
				.map(declarationPerteCartePlafondRequest, DeclarationPerteCartePlafondDTO.class);
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository
				.findById(declarationPerteCartePlafondDTO.getIdCarteplafond()).get();
		cartePlafond.createNewDeclarationPerteCartePlafond(declarationPerteCartePlafondDTO);
		tracabiliteController.createNewDeclarationPerteCartePlafondTracabilite(cartePlafondEntity.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/declaration_perte_carte_plafond")
	public ResponseEntity<HttpStatus> modifySelectedDeclarationPerteCartePlafond(
			@RequestBody DeclarationPerteCartePlafondRequest declarationPerteCartePlafondRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCartePlafondDTO declarationPerteCartePlafondDTO = modelMapper
				.map(declarationPerteCartePlafondRequest, DeclarationPerteCartePlafondDTO.class);
		CartePlafondEntity cartePlafondEntity = cartePlafondRepository
				.findById(declarationPerteCartePlafondDTO.getIdCarteplafond()).get();
		cartePlafond.modifySelectedDeclarationPerteCartePlafond(declarationPerteCartePlafondDTO);
		tracabiliteController.modifySelectedDeclarationPerteCartePlafondTracabilite(cartePlafondEntity.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/demande_annulation_carte_plafond")
	public ResponseEntity<HttpStatus> createNewDemandeAnnulationCarteCarburant(
			@RequestBody DemandeAnnulationCartePlafondRequest demandeAnnulationCartePlafondRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCartePlafondDTO demandeAnnulationCartePlafondDTO = modelMapper
				.map(demandeAnnulationCartePlafondRequest, DemandeAnnulationCartePlafondDTO.class);
		cartePlafond.createNewDemandeAnnulationCarteCarburant(demandeAnnulationCartePlafondDTO);
		CartePlafondEntity cartePlafond = cartePlafondRepository.findById(demandeAnnulationCartePlafondDTO.getIdCard())
				.get();
		tracabiliteController.createNewDemandeAnnulationCarteCarburantTracabilite(cartePlafond.getNumeroCarte(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_declaration_perte_cartePlafond")
	ResponseEntity<List<DeclarationPerteCartePlafondResponse>> getPaginationdeclarationpertecarteplafond(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "confirmed") String confirmed) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCartePlafondResponse> declarationPerteCartePlafondResponse = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<DeclarationPerteCartePlafondDTO> declarationPerteCartePlafondDTOs = cartePlafond
				.getPaginationdeclarationperteCartePlafond(Integer.parseInt(page), Integer.parseInt(limit), confirmed);
		if (!declarationPerteCartePlafondDTOs.isEmpty()) {
			declarationPerteCartePlafondDTOs.forEach(declarationPerteCartePlafondDTO -> {
				declarationPerteCartePlafondResponse.add(
						modelMapper.map(declarationPerteCartePlafondDTO, DeclarationPerteCartePlafondResponse.class));
			});
		}
		return new ResponseEntity<>(declarationPerteCartePlafondResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_declaration_cartePlafond")
	ResponseEntity<Long> getTotalNumberdeclarationperteCartePlafond() {
		Long totalNumber = cartePlafond.getTotalNumberdeclarationPerteCartePlafond();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

}
