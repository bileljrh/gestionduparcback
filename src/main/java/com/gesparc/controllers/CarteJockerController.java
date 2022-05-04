package com.gesparc.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.carburant.carteJocker.HistoriqueAffectationCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.HistoriqueDesaffectationCarteJockerEntity;
import com.gesparc.repositories.CarteJockerRepository;
import com.gesparc.repositories.DeclarationPerteCarteJockerRepository;
import com.gesparc.repositories.DemandeQuotaCarteJockerRepository;
import com.gesparc.repositories.HistoriqueAffectationCarteJockerRepository;
import com.gesparc.repositories.HistoriqueDesaffectationCarteJockerRepository;
import com.gesparc.requests.DeclarationPerteJockerRequest;
import com.gesparc.requests.HistoriqueDesaffectationCarteJockerRequest;
import com.gesparc.requests.Carburant.ModificationDemandeDesaffectationCarteJockerRequest;
import com.gesparc.requests.Carburant.ModifyDemandeQuotaCarteJockerRequest;
import com.gesparc.requests.Carburant.NewCarteJockerRequest;
import com.gesparc.responses.DeclarationPerteCarteJockerResponse;
import com.gesparc.responses.DemandeDesaffectationCarteJockerResponse;
import com.gesparc.responses.HistoriqueAffectationCarteJockerResponse;
import com.gesparc.responses.HistoriqueDesaffectationCarteJockerResponse;
import com.gesparc.responses.administratif.additionnel.SelectVehiculeResponse;
import com.gesparc.responses.carburant.CarteJockerDataResponse;
import com.gesparc.responses.carburant.ListAffectedVehiculesAndCartesJockerResponse;
import com.gesparc.responses.carburant.additionnel.CarteJockerTabDataResponse;
import com.gesparc.responses.carburant.additionnel.HistoriqueAffectationCarteJockerTabDataResponse;
import com.gesparc.servicesImpl.CarteJockerImpl;
import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.DemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.HistoriqueAffectationCarteJockerDTO;
import com.gesparc.sharedDTO.HistoriqueDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModificationDemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModifyDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.NewCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CarteJockerTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCarteJockerTabDataDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class CarteJockerController {

	@Autowired
	CarteJockerImpl carteJocker;

	@Autowired
	private CarteJockerRepository carteJockerRepository;

	@Autowired
	private DeclarationPerteCarteJockerRepository declarationPerteCarteJockerRepository;

	@Autowired
	private HistoriqueAffectationCarteJockerRepository historiqueAffectationCarteJockerRepository;

	@Autowired
	HistoriqueDesaffectationCarteJockerRepository historiqueDesaffectationCarteJockerRepository;

	@Autowired
	DemandeQuotaCarteJockerRepository demandeQuotaCarteJockerRepository;

	@GetMapping(path = "/list_carte_jocker")
	public Iterable<CarteJockerEntity> getAllCarteJockerTabDataResponses() {

		return carteJocker.getAllCarteJockerTabDataResponses();
	}

	@GetMapping(path = "/pagination_carte_jocker")
	ResponseEntity<List<CarteJockerTabDataResponse>> getPaginationCarteJockerList(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteJockerTabDataResponse> carteJockerTabDataResponses = new ArrayList<>();
		List<CarteJockerTabDataDTO> carteJockerTabDataDTOS = carteJocker
				.getPaginationCarteJockerList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!carteJockerTabDataDTOS.isEmpty()) {
			carteJockerTabDataDTOS.forEach(carteJockerTabDataDTO -> {
				carteJockerTabDataResponses
						.add(modelMapper.map(carteJockerTabDataDTO, CarteJockerTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(carteJockerTabDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_carte_jocker")
	ResponseEntity<Long> getTotalNumberCarteJocker() {
		Long totalNumber = carteJocker.getTotalNumberCarteJocker();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_carte_jocker_by_etat/{etatCarte}")
	Long getTotalNumberCarteJockerByEtatCarte(@PathVariable String etatCarte) {
		return carteJocker.getTotalNumberCarteJockerByEtatCarte(etatCarte);
	}

	@PutMapping(path = "/carte_jocker")
	Void modifySelectedCarteJocker(@RequestBody NewCarteJockerRequest newCarteJockerRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewCarteJockerDTO newCarteJockerDTO = modelMapper.map(newCarteJockerRequest, NewCarteJockerDTO.class);
		carteJocker.modifySelectedCarteJocker(newCarteJockerDTO);
		return null;
	}

	@DeleteMapping(path = "/carte_jocker/{id}")
	ResponseEntity<HttpStatus> deleteSelectedCarteJocker(@PathVariable Long id) {
		carteJocker.deleteSelectedCarteJocker(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/carte_jocker")
	ResponseEntity<HttpStatus> addNewCarteJocker(@RequestBody NewCarteJockerRequest newCarteJockerRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewCarteJockerDTO newCarteJockerDTO = modelMapper.map(newCarteJockerRequest, NewCarteJockerDTO.class);
		carteJocker.addNewCarteJocker(newCarteJockerDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/carte_jocker_not_affected")
	ResponseEntity<List<CarteJockerTabDataResponse>> carteJockerNotAffected() {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteJockerTabDataResponse> carteJockerTabDataResponseList = new ArrayList<>();
		List<CarteJockerTabDataDTO> carteJockerTabDataDTOS = carteJocker.getListNotAffectedCartesJocker();
		if (!carteJockerTabDataDTOS.isEmpty()) {
			carteJockerTabDataDTOS.forEach(carteJockerTabDataDTO -> {
				carteJockerTabDataResponseList
						.add(modelMapper.map(carteJockerTabDataDTO, CarteJockerTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(carteJockerTabDataResponseList, HttpStatus.OK);
	}

	@GetMapping(path = "/carte-jocker-affected")
	ResponseEntity<List<CarteJockerTabDataResponse>> listCarsAnnuler() {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteJockerTabDataResponse> carteJockerTabDataResponseList = new ArrayList<>();
		List<CarteJockerTabDataDTO> carteJockerTabDataDTOS = carteJocker.getListAffectedCartesJocker();
		if (!carteJockerTabDataDTOS.isEmpty()) {
			carteJockerTabDataDTOS.forEach(carteJockerTabDataDTO -> {
				carteJockerTabDataResponseList
						.add(modelMapper.map(carteJockerTabDataDTO, CarteJockerTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(carteJockerTabDataResponseList, HttpStatus.OK);
	}

	@GetMapping(path = "/list_vehicules_service_no_carte_jocker")
	ResponseEntity<List<SelectVehiculeResponse>> getListVehiculesServiceWithNoCarteJocker() {
		ModelMapper modelMapper = new ModelMapper();
		List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		List<SelectVehiculeDTO> selectVehiculeDTOS = carteJocker.getListVehiculesServiceWithNoCarteJocker();
		if (!selectVehiculeDTOS.isEmpty()) {
			selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
				selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));
			});
		}
		return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}

	@PostMapping(path = "/affectation_carte_jocker/{idCarte}")
	ResponseEntity<HttpStatus> addNewAffectationCarteJocker(@PathVariable Long idCarte, @RequestBody Long idVehicule) {
		carteJocker.addNewAffectationCarteJocker(idCarte, idVehicule);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_affectation_carte_jocker")
	ResponseEntity<Long> getTotalDemandeAffectationCarteJockerByFilteredDate() {
		Long totalNumber = carteJocker.getTotalDemandesAffectationCarteJockerByFilteredDate();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_affectation_carte_jocker")
	ResponseEntity<List<HistoriqueAffectationCarteJockerTabDataResponse>> getPaginationDemandesAffectationCarteJocker(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<HistoriqueAffectationCarteJockerTabDataResponse> historiqueAffectationCarteJockerTabDataResponses = new ArrayList<>();
		List<HistoriqueAffectationCarteJockerTabDataDTO> historiqueAffectationCarteJockerTabDataDTOS = carteJocker
				.getPaginationDemandesAffectationCarteJocker(Integer.parseInt(page), Integer.parseInt(limit));
		if (!historiqueAffectationCarteJockerTabDataDTOS.isEmpty()) {
			historiqueAffectationCarteJockerTabDataDTOS.forEach(historiqueAffectationCarteJockerTabDataDTO -> {
				historiqueAffectationCarteJockerTabDataResponses
						.add(modelMapper.map(historiqueAffectationCarteJockerTabDataDTO,
								HistoriqueAffectationCarteJockerTabDataResponse.class));
			});
		}
		return new ResponseEntity<>(historiqueAffectationCarteJockerTabDataResponses, HttpStatus.OK);
	}

	@PutMapping(path = "/affectation_carte_jocker/{idCarte}/{idVehicule}")
	void modifySelectedDemandeAffectationCarteJocker(@PathVariable Long idCarte, @PathVariable Long idVehicule,
			@RequestBody LocalDate dateDerniereDemande) {
		carteJocker.modifySelectedDemandeAffectationCarteJocker(idCarte, idVehicule, dateDerniereDemande);
	}

	@PostMapping(path = "/confirm_affectation_carte_jocker")
	public ResponseEntity<HttpStatus> confirmSelectedDemandeAffectationCarteJocker(@RequestBody Long id) {
		carteJocker.confirmSelectedDemandeAffectationCarteJocker(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_affectation_carte_jocker")
	public ResponseEntity<HttpStatus> validateSelectedDemandeAffectationCarteJocker(@RequestBody Long id) {
		carteJocker.validateSelectedDemandeAffectationCarteJocker(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/affectation_carte_jocker/{idCarte}/{idHistorique}")
	ResponseEntity<HttpStatus> deleteSelectedDemandeAffectationCarteJocker(@PathVariable Long idCarte,
			@PathVariable Long idHistorique) {
		carteJocker.deleteSelectedDemandeAffectationCarteJocker(idCarte, idHistorique);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_historique_affectation_carte_jocker")
	List<HistoriqueAffectationCarteJockerResponse> getPaginationHistoriqueAffectationCarteJockerByFilteredDate(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "dateMin") String dateMin, @RequestParam(name = "dateMax") String dateMax) {
		ModelMapper modelMapper = new ModelMapper();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<HistoriqueAffectationCarteJockerResponse> historiqueAffectationCarteJockerResponses = new ArrayList<>();
		List<HistoriqueAffectationCarteJockerDTO> historiqueAffectationCarteJockerDTOS = carteJocker
				.getPaginationHistoriqueAffectationCarteJockerByFilteredDate(Integer.parseInt(page),
						Integer.parseInt(limit), LocalDate.parse(dateMin, formatter),
						LocalDate.parse(dateMax, formatter));
		if (!historiqueAffectationCarteJockerDTOS.isEmpty()) {
			historiqueAffectationCarteJockerDTOS.forEach(historiqueAffectationCarteJockerDTO -> {
				historiqueAffectationCarteJockerResponses.add(modelMapper.map(historiqueAffectationCarteJockerDTO,
						HistoriqueAffectationCarteJockerResponse.class));
			});
		}
		return historiqueAffectationCarteJockerResponses;
	}

	@GetMapping(path = "/total_number_historique_affectation_carte_jocker")
	Long getTotalNumberHistoriqueAffectationCarteJockerByFilteredDate(@RequestParam(name = "dateMin") String dateMin,
			@RequestParam(name = "dateMax") String dateMax) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return carteJocker.getTotalNumberHistoriqueAffectationCarteJockerByFilteredDate(
				LocalDate.parse(dateMin, formatter), LocalDate.parse(dateMax, formatter));
	}

	@DeleteMapping(path = "/delete_historique_affectation_carte_jocker/{idCarte}")
	void deleteSelectedHistoriqueAffectationCarteJocker(@PathVariable Long idCarte) {
		carteJocker.deleteSelectedHistoriqueAffectationCarteJocker(idCarte);
	}

	@GetMapping(path = "/list_affected_vehicules_and_cartes_jocker")
	List<ListAffectedVehiculesAndCartesJockerResponse> getListAffectedVehiculesAndCartesJocker() {
		List<ListAffectedVehiculesAndCartesJockerResponse> listAffectedVehiculesAndCartesJockerResponses = new ArrayList<>();
		List<CarteJockerDTO> carteJockerDTOS = carteJocker.getListAffectedVehiculesAndCartesJocker();
		if (!carteJockerDTOS.isEmpty()) {
			carteJockerDTOS.forEach(carteJockerDTO -> {
				ListAffectedVehiculesAndCartesJockerResponse listAffectedVehiculesAndCartesJockerResponse = new ListAffectedVehiculesAndCartesJockerResponse();
				listAffectedVehiculesAndCartesJockerResponse.setIdVehicule(carteJockerDTO.getVehicule().getId());
				listAffectedVehiculesAndCartesJockerResponse
						.setIdBeneficiaire(carteJockerDTO.getVehicule().getBeneficiaire().getId());
				listAffectedVehiculesAndCartesJockerResponse
						.setNumeroPlaque(carteJockerDTO.getVehicule().getNumeroPlaque());
				listAffectedVehiculesAndCartesJockerResponse
						.setTypeCarburant(carteJockerDTO.getVehicule().getEnergie().getDescription());
				listAffectedVehiculesAndCartesJockerResponse
						.setNomBeneficiaire(carteJockerDTO.getVehicule().getBeneficiaire().getNom());
				listAffectedVehiculesAndCartesJockerResponse
						.setMatriculeBeneficiaire(carteJockerDTO.getVehicule().getBeneficiaire().getMatricule());
				listAffectedVehiculesAndCartesJockerResponse.setIdCarte(carteJockerDTO.getId());
				listAffectedVehiculesAndCartesJockerResponse.setNumeroCarte(carteJockerDTO.getNumeroCarte());
				listAffectedVehiculesAndCartesJockerResponse
						.setDateAffectation(carteJockerDTO.getDateDerniereAffectation());
				listAffectedVehiculesAndCartesJockerResponse
						.setDateDemandeAffectation(carteJockerDTO.getDateDerniereAffectation());
				listAffectedVehiculesAndCartesJockerResponse.setSolde(carteJockerDTO.getSolde());
				listAffectedVehiculesAndCartesJockerResponse
						.setNombreAffectation(carteJockerDTO.getNombreAffectation());
				listAffectedVehiculesAndCartesJockerResponse
						.setDateDerniereDesaffectation(carteJockerDTO.getDateDerniereDesaffectation());
				listAffectedVehiculesAndCartesJockerResponses.add(listAffectedVehiculesAndCartesJockerResponse);
			});
		}
		return listAffectedVehiculesAndCartesJockerResponses;
	}

	@PostMapping(path = "/desaffectation_carte_jocker")
	void createNouvelleDemandeDesaffectationCarteJockerRequest(
			@RequestBody HistoriqueDesaffectationCarteJockerRequest NouvelleDemande) {
		ModelMapper modelMapper = new ModelMapper();
		HistoriqueDesaffectationCarteJockerDTO NouvelleDemandeDTO = modelMapper.map(NouvelleDemande,
				HistoriqueDesaffectationCarteJockerDTO.class);
		carteJocker.createNouvelleDemandeDesaffectationCarteJockerRequest(NouvelleDemandeDTO);
	}

	@GetMapping(path = "/pagination_desaffectation_carte_jocker")
	List<DemandeDesaffectationCarteJockerResponse> getPaginationDemandesDesaffectationCarteJockerByFilteredDate(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "dateMin") String dateMin, @RequestParam(name = "dateMax") String dateMax) {
		ModelMapper modelMapper = new ModelMapper();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<DemandeDesaffectationCarteJockerResponse> demandeDesaffectationCarteJockerResponses = new ArrayList<>();
		List<DemandeDesaffectationCarteJockerDTO> demandeDesaffectationCarteJockerDTOS = carteJocker
				.getPaginationDemandesDesaffectationCarteJockerByFilteredDate(Integer.parseInt(page),
						Integer.parseInt(limit), LocalDate.parse(dateMin, formatter),
						LocalDate.parse(dateMax, formatter));
		if (!demandeDesaffectationCarteJockerDTOS.isEmpty()) {
			demandeDesaffectationCarteJockerDTOS.forEach(demandeDesaffectationCarteJockerDTO -> {
				demandeDesaffectationCarteJockerResponses.add(modelMapper.map(demandeDesaffectationCarteJockerDTO,
						DemandeDesaffectationCarteJockerResponse.class));
			});
		}
		return demandeDesaffectationCarteJockerResponses;
	}

	@GetMapping(path = "/total_number_desaffectation_carte_jocker")
	Long getTotalNumberDemandesDesaffectationCarteJockerByFilteredDate(@RequestParam(name = "dateMin") String dateMin,
			@RequestParam(name = "dateMax") String dateMax) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return carteJocker.getTotalNumberDemandesDesaffectationCarteJockerByFilteredDate(
				LocalDate.parse(dateMin, formatter), LocalDate.parse(dateMax, formatter));
	}

	@DeleteMapping(path = "/desaffectation_carte_jocker/{id}")
	void deleteSelectedDemandeDesaffectationCarteJocker(@PathVariable Long id) {
		carteJocker.deleteSelectedDemandeDesaffectationCarteJocker(id);
	}

	@PutMapping(path = "/desaffectation_carte_jocker/{id}")
	void confirmSelectedDemandeDesaffectationCarteJocker(@PathVariable Long id) {
		HistoriqueDesaffectationCarteJockerEntity historiqueDesaffectationCarteJockerEntity = historiqueDesaffectationCarteJockerRepository
				.findById(id).get();
		CarteJockerEntity carteJockerEntity = carteJockerRepository
				.findById(historiqueDesaffectationCarteJockerEntity.getIdCarte()).get();
		historiqueDesaffectationCarteJockerEntity
				.setMatriculeBeneficiaire(carteJockerEntity.getVehicule().getBeneficiaire().getNom());
		carteJockerEntity.setVehicule(null);
		this.deleteAffCart((long) carteJockerEntity.getId());
		historiqueDesaffectationCarteJockerEntity.setDateConfirmationDesaffectation(new Date());
		carteJockerRepository.save(carteJockerEntity);
		historiqueDesaffectationCarteJockerRepository.save(historiqueDesaffectationCarteJockerEntity);

	}

	private void deleteAffCart(Long id) {
		HistoriqueAffectationCarteJockerEntity hacje = historiqueAffectationCarteJockerRepository
				.findByCarteJockerId(id).get();
		historiqueAffectationCarteJockerRepository.deleteById(hacje.getId());
	}

	@PutMapping(path = "/modify_desaffectation_carte_jocker")
	void modifySelectedDemandeDesaffectationCarteJocker(
			@RequestBody HistoriqueDesaffectationCarteJockerRequest modificationDemandeDesaffectationCarteJockerRequest) {
		ModelMapper modelMapper = new ModelMapper();
		HistoriqueDesaffectationCarteJockerDTO modificationDemandeDesaffectationCarteJockerDTO = modelMapper
				.map(modificationDemandeDesaffectationCarteJockerRequest, HistoriqueDesaffectationCarteJockerDTO.class);
		carteJocker.modificationDemandeDesaffectationCarteJockerDTO(modificationDemandeDesaffectationCarteJockerDTO);
	}

	@GetMapping(path = "/pagination_historique_desaffectation_carte_jocker")
	List<HistoriqueDesaffectationCarteJockerResponse> getPaginationHistoriqueDesaffectationCarteAgilis(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<HistoriqueDesaffectationCarteJockerResponse> historiqueRechargeCarteAgilisCashRequests = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<HistoriqueDesaffectationCarteJockerDTO> historiqueDesaffectationCarteJockerDTOS = carteJocker
				.getPaginationHistoriqueDesaffectationCarteJockerByFilteredDate(Integer.parseInt(page),
						Integer.parseInt(limit));
		if (!historiqueDesaffectationCarteJockerDTOS.isEmpty()) {
			historiqueDesaffectationCarteJockerDTOS.forEach(historiqueDesaffectationCarteJockerDTO -> {
				historiqueRechargeCarteAgilisCashRequests.add(modelMapper.map(historiqueDesaffectationCarteJockerDTO,
						HistoriqueDesaffectationCarteJockerResponse.class));
			});
		}
		return historiqueRechargeCarteAgilisCashRequests;
	}

	@GetMapping(path = "/total_number_historique_desaffectation_carte_jocker")
	Long getTotalNumberHistoriqueDesaffectationCarteJockerByFilteredDate(@RequestParam(name = "dateMin") String dateMin,
			@RequestParam(name = "dateMax") String dateMax) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return carteJocker.getTotalNumberHistoriqueDesaffectationCarteJockerByFilteredDate(
				LocalDate.parse(dateMin, formatter), LocalDate.parse(dateMax, formatter));
	}

	@DeleteMapping(path = "/historique_desaffectation_carte_jocker/{id}")
	void deleteSelectedHistoriqueDesaffectationCarteJocker(@PathVariable Long id) {
		carteJocker.deleteSelectedHistoriqueDesaffectationCarteJocker(id);
	}

	@GetMapping(path = "/list_carte_jocker_for_affectation")
	List<CarteJockerDataResponse> getListCarteJockerAvailableForAffectation() {
		ModelMapper modelMapper = new ModelMapper();
		List<CarteJockerDataResponse> carteJockerDataResponses = new ArrayList<>();
		List<CarteJockerDTO> carteJockerDTOS = carteJocker.getListCarteJockerAvailableForAffectation();
		if (!carteJockerDTOS.isEmpty()) {
			carteJockerDTOS.forEach(carteJockerDTO -> {
				carteJockerDataResponses.add(modelMapper.map(carteJockerDTO, CarteJockerDataResponse.class));
			});
		}
		return carteJockerDataResponses;
	}

	// --------------------------------------------------------------//
	@GetMapping(path = "/carte_Jocker")
	public Iterable<CarteJockerEntity> getListeCarteJocker() {
		return carteJockerRepository.findAll();
	}

	@GetMapping(path = "/carte_Jocker/{id}")
	public Optional<CarteJockerEntity> getListeCarteJockerbyid(@PathVariable Long id) {
		return carteJockerRepository.findById(id);
	}

	@GetMapping(path = "/list_declaration_perte_carte_jocker")
	public Iterable<DeclarationPerteCarteJockerEntity> test(@RequestParam boolean confirmation) {
		return declarationPerteCarteJockerRepository.getListDeclarationPerteCarteJockerByConfirmation(confirmation);
	}

	@PostMapping(path = "/declaration_perte_carte_jocker")
	public ResponseEntity<HttpStatus> createNewDeclarationPerteCarteJocker(
			@RequestBody DeclarationPerteJockerRequest declarationPerteJockerRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCarteJockerDTO declarationPerteCarteJockerDTO = modelMapper.map(declarationPerteJockerRequest,
				DeclarationPerteCarteJockerDTO.class);
		carteJocker.createNewDeclarationPerteCarteJocker(declarationPerteCarteJockerDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/declaration_perte_carte_jocker")
	public ResponseEntity<HttpStatus> modifySelectedDeclarationPerteCarteJocker(
			@RequestBody DeclarationPerteJockerRequest declarationPerteJockerRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DeclarationPerteCarteJockerDTO declarationPerteCarteJockerDTO = modelMapper.map(declarationPerteJockerRequest,
				DeclarationPerteCarteJockerDTO.class);
		carteJocker.modifySelectedDeclarationPerteCarteJocker(declarationPerteCarteJockerDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/list_demande_quota_carte_jocker")
	public Iterable<DemandeQuotaCarteJocker> getAllDemandeQuotaCarteJockers() {
		return carteJocker.getAllDemandeQuotaCarteJockers();
	}

	@GetMapping(path = "/total_number_demande_quota_carte_jocker")
	ResponseEntity<Long> getTotalNumberDemandeQuotaCarteJocker() {
		Long totalNumber = carteJocker.getTotalNumberDemandeQuotaCarteJocker();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_demande_quota_carte_jocker_filtre")
	Long getTotalNumberDemandeQuotaCarteJockerByFilteredDate(@RequestParam(name = "dateMin") String dateMin,
			@RequestParam(name = "dateMax") String dateMax) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return carteJocker.getTotalNumberDemandeQuotaCarteJockerByFilteredDate(LocalDate.parse(dateMin, formatter),
				LocalDate.parse(dateMax, formatter));
	}

	@GetMapping(path = "/pagination_demande_quota_carte_jocker")
	ResponseEntity<List<DemandeQuotaCarteJocker>> getPaginationDemandeQuotaCarteJockerList(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeQuotaCarteJocker> demandeQuotaCarteJockerListResponse = new ArrayList<>();
		List<ListDemandeQuotaCarteJockerDTO> demandeQuotaCarteJockerTabDataDTOS = carteJocker
				.getPaginationDemandeQuotaCarteJockerList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!demandeQuotaCarteJockerTabDataDTOS.isEmpty()) {
			demandeQuotaCarteJockerTabDataDTOS.forEach(demandeQuotaCarteJockerTabDataDTO -> {
				demandeQuotaCarteJockerListResponse
						.add(modelMapper.map(demandeQuotaCarteJockerTabDataDTO, DemandeQuotaCarteJocker.class));
			});
		}
		return new ResponseEntity<>(demandeQuotaCarteJockerListResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/demande_quota_carte_jocker/{id}")
	public DemandeQuotaCarteJocker getDemandeQuotaCarteJockerById(@PathVariable("id") Long id) {
		return carteJocker.getDemandeQuotaCarteJocker(id);
	}

	@PostMapping(path = "/creation_demande_quota_carte_jocker")
	ResponseEntity<HttpStatus> createDemandeQuotaCarteJocker(
			@RequestBody DemandeQuotaCarteJocker demandeQuotaCarteJocker) {
		carteJocker.addNewDemandeQuotaCarteJocker(demandeQuotaCarteJocker);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/modify_demande_quota_carte_jocker")
	void updateDemandeQuotaCarteJockerl(
			@RequestBody ModifyDemandeQuotaCarteJockerRequest modifyDemandeQuotaCarteJockerRequest) {
		ModelMapper modelMapper = new ModelMapper();
		ModifyDemandeQuotaCarteJockerDTO modifyDemandeQuotaCarteJockerDTO = modelMapper
				.map(modifyDemandeQuotaCarteJockerRequest, ModifyDemandeQuotaCarteJockerDTO.class);

		carteJocker.modifySelectedDemandeQuotaCarteJocker(modifyDemandeQuotaCarteJockerDTO);

	}

	@DeleteMapping(path = "/delete_demande_quota_carte_jocker/{id}")
	public ResponseEntity<HttpStatus> deleteDemandeQuotaCarteJocker(@PathVariable("id") Long id) {
		carteJocker.deleteSelectedDemandeQuotaCarteJocker(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/confirm_quota_carte_jocker")
	public ResponseEntity<HttpStatus> confirmSelectedDemandeQuotaCarteJocker(@RequestBody Long id) {
		carteJocker.confirmSelectedDemandeQuotaCarteJocker(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_quota_carte_jocker")
	public ResponseEntity<HttpStatus> validateSelectedDemandeQuotaCarteJocker(@RequestBody Long id) {
		carteJocker.validateSelectedDemandeQuotaCarteJocker(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_declaration_perte_cartejocker")
	ResponseEntity<List<DeclarationPerteCarteJockerResponse>> getPaginationdeclarationpertecartejocker(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit,
			@RequestParam(name = "confirmed") String confirmed) {
		ModelMapper modelMapper = new ModelMapper();
		List<DeclarationPerteCarteJockerResponse> declarationPerteCarteJockerResponse = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<DeclarationPerteCarteJockerDTO> declarationPerteCarteJockerDTOs = carteJocker
				.getPaginationdeclarationperteCarteJocker(Integer.parseInt(page), Integer.parseInt(limit), confirmed);
		if (!declarationPerteCarteJockerDTOs.isEmpty()) {
			declarationPerteCarteJockerDTOs.forEach(declarationPerteCarteJockerDTO -> {
				declarationPerteCarteJockerResponse.add(
						modelMapper.map(declarationPerteCarteJockerDTO, DeclarationPerteCarteJockerResponse.class));
			});
		}
		return new ResponseEntity<>(declarationPerteCarteJockerResponse, HttpStatus.OK);

	}

	@GetMapping(path = "/total_number_declaration_carte_jocker")
	ResponseEntity<Long> getTotalNumberdeclarationperteCarteJocker() {
		Long totalNumber = carteJocker.getTotalNumberdeclarationPerteCarteJocker();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}
}
