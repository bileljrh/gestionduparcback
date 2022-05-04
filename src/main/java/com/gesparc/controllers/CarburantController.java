package com.gesparc.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gesparc.entities.carburant.RechargeCarburantCompensation;

import com.gesparc.entities.carburant.RechargeComplementaire;
import com.gesparc.entities.carburant.HistoriqueOperationRecharge;
import com.gesparc.entities.carburant.HistoriqueRecahrgeComplementaire;
import com.gesparc.entities.carburant.HistoriqueRechargeCarburantCompensation;
import com.gesparc.entities.carburant.RechargeSousCompte;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueRechargeQuotaMensuelEntity;
import com.gesparc.entities.carburant.cartePlafond.RechargeQuotaMensuelEntity;
import com.gesparc.entities.stock.HistoriqueRegulation;
import com.gesparc.requests.DeclarationPerteJockerRequest;
import com.gesparc.requests.DemandeAnnulationCartePlafondRequest;
import com.gesparc.requests.Carburant.ModifyDistribution2FonctionRequest;
import com.gesparc.requests.Carburant.ModifyEtatMensuelRequest;
import com.gesparc.requests.Carburant.ModifyRechargeComplementaireRequest;
import com.gesparc.requests.Carburant.ModifyRechargeCarburantCompensationRequest;
import com.gesparc.requests.Carburant.NewDistribution2FonctionRequest;
import com.gesparc.requests.Carburant.NewEtatMensuelRequest;
import com.gesparc.responses.DeclarationPerteCartePlafondResponse;
import com.gesparc.responses.DemandeAnnulationCartePlafondResponse;
import com.gesparc.responses.carburant.DistributionFonctionTabDataResponse;
import com.gesparc.responses.carburant.DistributionServiceTabDataResponse;
import com.gesparc.responses.carburant.EtatMensuelTable;
import com.gesparc.responses.carburant.ListBeneficiairesExternesResponse;
import com.gesparc.responses.carburant.ListBeneficiairesInternesResponse;
import com.gesparc.responses.carburant.ListVehiculesServiceResponse;
import com.gesparc.responses.carburant.ValidatedEtatMensuel;
import com.gesparc.servicesImpl.CarburantImpl;
import com.gesparc.sharedDTO.DeclarationPerteCartePlafondDTO;
import com.gesparc.sharedDTO.DemandeAnnulationCartePlafondDTO;
import com.gesparc.sharedDTO.EtatMensuelDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.carburant.DemandeRechargeSousCompteDTO;
import com.gesparc.sharedDTO.carburant.DistributionFonctionTabDataDTO;
import com.gesparc.sharedDTO.carburant.DistributionServiceTabDataDTO;
import com.gesparc.sharedDTO.carburant.ListBeneficiairesExternesDTO;
import com.gesparc.sharedDTO.carburant.ListBeneficiairesInternesDTO;
import com.gesparc.sharedDTO.carburant.ListRechargeCarburantCompensationDTO;
import com.gesparc.sharedDTO.carburant.ModifyRechargeCarburantCompensationDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ListRechargeComplementaireDTO;
import com.gesparc.sharedDTO.carburant.ModifyDistribution2FonctionDTO;
import com.gesparc.sharedDTO.carburant.ModifyEtatMensuelDTO;
import com.gesparc.sharedDTO.carburant.ModifyRechargeComplementaireDTO;
import com.gesparc.sharedDTO.carburant.NewDistribution2FonctionDTO;
import com.gesparc.sharedDTO.carburant.NewEtatMensuelDTO;
import com.gesparc.sharedDTO.carburant.RechargeQuotaMensuelDTO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class CarburantController {

	@Autowired
	CarburantImpl carburant;

	@GetMapping(path = "/list_beneficiaires_internes")
	public List<ListBeneficiairesInternesResponse> getListBeneficiairesInternes() {
		ModelMapper modelMapper = new ModelMapper();
		List<ListBeneficiairesInternesResponse> listBeneficiairesInternesResponses = new ArrayList<>();
		List<ListBeneficiairesInternesDTO> listBeneficiairesInternesDTOS = this.carburant
				.getListBeneficiairesInternes();
		listBeneficiairesInternesDTOS.forEach(listBeneficiairesInternesDTO -> {
			listBeneficiairesInternesResponses
					.add(modelMapper.map(listBeneficiairesInternesDTO, ListBeneficiairesInternesResponse.class));
		});
		return listBeneficiairesInternesResponses;
	}

	@GetMapping(path = "/list_beneficiaires_externes")
	public List<ListBeneficiairesExternesResponse> getListBeneficiairesExternes() {
		ModelMapper modelMapper = new ModelMapper();
		List<ListBeneficiairesExternesResponse> listBeneficiairesExternesResponses = new ArrayList<>();
		List<ListBeneficiairesExternesDTO> listBeneficiairesExternesDTOS = carburant.getListBeneficiairesExternes();
		listBeneficiairesExternesDTOS.forEach(listBeneficiairesExternesDTO -> {
			listBeneficiairesExternesResponses
					.add(modelMapper.map(listBeneficiairesExternesDTO, ListBeneficiairesExternesResponse.class));
		});
		return listBeneficiairesExternesResponses;
	}

	@GetMapping(path = "/list_distributions_carburant_fonction/{source}")
	public List<DistributionFonctionTabDataResponse> getListDistributionsCarburant2FonctionBySource(
			@PathVariable String source) {
		ModelMapper modelMapper = new ModelMapper();
		List<DistributionFonctionTabDataResponse> distributionFonctionTabDataResponses = new ArrayList<>();
		List<DistributionFonctionTabDataDTO> distributionFonctionTabDataDTOS = carburant
				.getListDistributionsCarburant2FonctionBySource(source);
		if (distributionFonctionTabDataDTOS.size() > 0) {
			distributionFonctionTabDataDTOS.forEach(distributionFonctionTabDataDTO -> {
				distributionFonctionTabDataResponses.add(
						modelMapper.map(distributionFonctionTabDataDTO, DistributionFonctionTabDataResponse.class));
			});
		}
		return distributionFonctionTabDataResponses;
	}

	@PostMapping(path = "/distributions_carburant_fonction")
	public String modifyOneDistributionsCarburant2Fonction(
			@RequestBody ModifyDistribution2FonctionRequest modifyDistribution2Fonction) {
		ModelMapper modelMapper = new ModelMapper();
		ModifyDistribution2FonctionDTO modifyDistribution2FonctionDTO = modelMapper.map(modifyDistribution2Fonction,
				ModifyDistribution2FonctionDTO.class);
		carburant.modifyOneDistributionsCarburant2Fonction(modifyDistribution2FonctionDTO);
		return "Distribution is modified";
	}

	@PostMapping(path = "/new_distribution_carburant_fonction")
	public void createNewDistributionCarburant2Fonction(
			@RequestBody NewDistribution2FonctionRequest newDistribution2FonctionRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewDistribution2FonctionDTO newDistribution2FonctionDTO = modelMapper.map(newDistribution2FonctionRequest,
				NewDistribution2FonctionDTO.class);
		this.carburant.createNewDistributionCarburant2Fonction(newDistribution2FonctionDTO);
	}

	@DeleteMapping(path = "/delete_distribution_carburant_fonction/{id}")
	public void deleteOneDistributionCarburant2Fonction(@PathVariable Long id) {
		this.carburant.deleteOneDistributionCarburant2Fonction(id);
	}

	@PostMapping(path = "/validate_etat_mensuels")
	public void validateOneEtatMensuels(@RequestBody Long id) {
		carburant.validateOneEtatMensuels(id);
	}

	@PostMapping(path = "/unvalidate_etat_mensuels")
	public void unvalidateOneEtatMensuels(@RequestBody Long id) {
		carburant.unvalidateOneEtatMensuels(id);
	}

	@PostMapping(path = "/confirm_etat_mensuels")
	public void confirmOneEtatMensuels(@RequestBody Long id) {
		carburant.confirmOneEtatMensuels(id);
	}

	@PostMapping(path = "/unconfirm_etat_mensuels")
	public void unconfirmOneEtatMensuels(@RequestBody Long id) {
		carburant.unconfirmOneEtatMensuels(id);
	}

	@GetMapping(path = "/etat_mensuels")
	public List<EtatMensuelTable> getAllEtatMensuels() {
		List<EtatMensuelDTO> etatMensuelDTOS = carburant.getAllEtatMensuels();
		List<EtatMensuelTable> etatMensuelTableList = new ArrayList<>();
		return etatMensuelTableList;
	}

	@PutMapping(path = "/etat_mensuel")
	public void modifyOneEtatMensuels(@RequestBody ModifyEtatMensuelRequest modifyEtatMensuelRequest) {
		ModelMapper modelMapper = new ModelMapper();
		ModifyEtatMensuelDTO modifyEtatMensuelDTO = modelMapper.map(modifyEtatMensuelRequest,
				ModifyEtatMensuelDTO.class);
		this.carburant.modifyOneEtatMensuels(modifyEtatMensuelDTO);
	}

	@PostMapping(path = "/etat_mensuel")
	public void createNewEtatMensuel(@RequestBody NewEtatMensuelRequest newEtatMensuelRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewEtatMensuelDTO newEtatMensuelDTO = modelMapper.map(newEtatMensuelRequest, NewEtatMensuelDTO.class);
		carburant.createNewEtatMensuel(newEtatMensuelDTO);
	}

	@DeleteMapping(path = "/etat_mensuel/{id}")
	public void deleteOneEtatMensuels(@PathVariable Long id) {
		carburant.deleteOneEtatMensuels(id);
	}

	@GetMapping(path = "/etat_mensuel/{type}/{month}")
	public List<EtatMensuelTable> getEtatMensuelsByMonthAndType(@PathVariable String type, @PathVariable String month) {
		ModelMapper modelMapper = new ModelMapper();
		List<EtatMensuelDTO> etatMensuelDTOS = carburant.getEtatMensuelsByMonthAndType(type, month);
		List<EtatMensuelTable> etatMensuelTableList = new ArrayList<>();
		return etatMensuelTableList;
	}

	@GetMapping(path = "/distributions_carburant_fonction/{type}/{month}")
	public List<DistributionFonctionTabDataResponse> getDistributionsCarburant2FonctionByMonthAndType(
			@PathVariable String type, @PathVariable String month) {
		ModelMapper modelMapper = new ModelMapper();
		List<DistributionFonctionTabDataResponse> distributionFonctionTabDataResponses = new ArrayList<>();
		List<DistributionFonctionTabDataDTO> distributionFonctionTabDataDTOS = carburant
				.getDistributionsCarburant2FonctionByMonthAndType(type, month);
		if (!distributionFonctionTabDataDTOS.isEmpty()) {
			distributionFonctionTabDataDTOS.forEach(distributionFonctionTabDataDTO -> {
				distributionFonctionTabDataResponses.add(
						modelMapper.map(distributionFonctionTabDataDTO, DistributionFonctionTabDataResponse.class));
			});
		}
		return distributionFonctionTabDataResponses;
	}

	@GetMapping(path = "/validated_etat_mensuel")
	public List<ValidatedEtatMensuel> getValidatedEtatMensuelList() {
		List<ValidatedEtatMensuel> validatedEtatMensuels = new ArrayList<>();
		List<EtatMensuelDTO> etatMensuelDTOS = carburant.getValidatedEtatMensuelList();
		if (!etatMensuelDTOS.isEmpty()) {
			etatMensuelDTOS.forEach(etatMensuelDTO -> {
				String sourceCarburant = "Service";
				ValidatedEtatMensuel validatedEtatMensuel = new ValidatedEtatMensuel(

				);
				validatedEtatMensuels.add(validatedEtatMensuel);
			});
		}
		return validatedEtatMensuels;
	}

	@PostMapping(path = "/new_distribution_service")
	public void createNewDistribution2Service(@RequestBody Long id) {
		carburant.createNewDistribution2Service(id);
	}

	@GetMapping(path = "/list_distributions_carburant_service/{source}")
	public List<DistributionServiceTabDataResponse> getListDistributionsCarburant2ServiceBySource(
			@PathVariable String source) {
		ModelMapper modelMapper = new ModelMapper();
		List<DistributionServiceTabDataResponse> distributionServiceTabDataResponses = new ArrayList<>();
		List<DistributionServiceTabDataDTO> distributionServiceTabDataDTOS = carburant
				.getListDistributionsCarburant2ServiceBySource(source);
		if (!distributionServiceTabDataDTOS.isEmpty()) {
			distributionServiceTabDataDTOS.forEach(distributionServiceTabDataDTO -> {
				distributionServiceTabDataResponses
						.add(modelMapper.map(distributionServiceTabDataDTO, DistributionServiceTabDataResponse.class));
			});
		}
		return distributionServiceTabDataResponses;
	}

	@DeleteMapping(path = "/distributions_carburant_service/{id}")
	public void deleteOneDistributionCarburant2Service(@PathVariable Long id) {
		carburant.deleteOneDistributionCarburant2Service(id);
	}

	@GetMapping(path = "/list_vehicule_service")
	public List<ListVehiculesServiceResponse> getListVehiculesService() {
		List<ListVehiculesServiceResponse> listVehiculesServiceResponses = new ArrayList<>();
		List<VehiculeDTO> vehiculeDTOS = carburant.getListVehiculesService();
		if (!vehiculeDTOS.isEmpty()) {
			vehiculeDTOS.forEach(vehiculeDTO -> {
				ListVehiculesServiceResponse listVehiculesServiceResponse = new ListVehiculesServiceResponse();
				listVehiculesServiceResponses.add(listVehiculesServiceResponse);
			});
		}
		return listVehiculesServiceResponses;
	}

	@GetMapping(path = "/list_vehicule_service_with_no_cart_plafond")
	public List<ListVehiculesServiceResponse> getListVehiculesServiceWithNoCartePlafond() {
		List<ListVehiculesServiceResponse> listVehiculesServiceResponses = new ArrayList<>();
		List<VehiculeDTO> vehiculeDTOS = carburant.getListVehiculesServiceWithNoCartePlafond();
		if (!vehiculeDTOS.isEmpty()) {
			vehiculeDTOS.forEach(vehiculeDTO -> {
				ListVehiculesServiceResponse listVehiculesServiceResponse = new ListVehiculesServiceResponse();
				listVehiculesServiceResponses.add(listVehiculesServiceResponse);
			});
		}
		return listVehiculesServiceResponses;
	}

	@PutMapping(path = "/affectation_carte_plafond/{idCartePlafond}")
	public void modifySelectedAffectationCartePlafond(@PathVariable Long idCartePlafond, @RequestBody Long idVehicule) {
		carburant.modifySelectedAffectationCartePlafond(idCartePlafond, idVehicule);
	}
	
	@PostMapping(path = "/confirm_declaration_perte_carte_plafond")
	public void confirmOneDeclarationPerteCartePlafond(@RequestBody Long id) {
		carburant.confirmOneDeclarationPerteCartePlafond(id);
	}

	@DeleteMapping(path = "/declaration_perte_carte_plafond/{id}")
	public void deleteOneDeclarationPerteCartePlafond(@PathVariable Long id) {
		carburant.deleteOneDeclarationPerteCartePlafond(id);
	}

	@GetMapping(path = "/historique_demande_annulation_carte_plafond")
	public List<DemandeAnnulationCartePlafondResponse> getHistoriqueDemandeAnnulationCartePlafondByConfirmation(
			@RequestParam boolean confirmation) {
		ModelMapper modelMapper = new ModelMapper();
		List<DemandeAnnulationCartePlafondResponse> demandeAnnulationCartePlafondResponses = new ArrayList<>();
		List<DemandeAnnulationCartePlafondDTO> demandeAnnulationCartePlafondDTOS = carburant
				.getHistoriqueDemandeAnnulationCartePlafondByConfirmation(confirmation);
		if (!demandeAnnulationCartePlafondDTOS.isEmpty()) {
			demandeAnnulationCartePlafondDTOS.forEach(demandeAnnulationCartePlafondDTO -> {
				demandeAnnulationCartePlafondResponses.add(
						modelMapper.map(demandeAnnulationCartePlafondDTO, DemandeAnnulationCartePlafondResponse.class));
			});
		}
		return demandeAnnulationCartePlafondResponses;
	}

	@DeleteMapping(path = "/demande_annulation_carte_plafond/{id}")
	public void deleteOneDemandeAnnulationCartePlafond(@PathVariable Long id) {
		carburant.deleteOneDemandeAnnulationCartePlafond(id);
	}

	@PostMapping(path = "/confirm_demande_annulation_carte_plafond")
	public void confirmSelectedDemandeAnnulationCarteCarburant(@RequestBody Long id) {
		carburant.confirmSelectedDemandeAnnulationCarteCarburant(id);
	}

	@PutMapping(path = "/demande_annulation_carte_plafond")
	public void modifyOneDemandeAnnulationCarteCarburant(
			@RequestBody DemandeAnnulationCartePlafondRequest demandeAnnulationCartePlafondRequest) {
		ModelMapper modelMapper = new ModelMapper();
		DemandeAnnulationCartePlafondDTO demandeAnnulationCartePlafondDTO = modelMapper
				.map(demandeAnnulationCartePlafondRequest, DemandeAnnulationCartePlafondDTO.class);
		carburant.modifyOneDemandeAnnulationCarteCarburant(demandeAnnulationCartePlafondDTO);
	}

	@DeleteMapping(path = "/delete_carte_plafond/{id}")
	public void deleteOneCartePlafond(@PathVariable Long id) {
		carburant.deleteOneCartePlafond(id);
	}

	@DeleteMapping(path = "/historique_demande_annulation_carte_plafond/{id}")
	void deleteHistoriqueDemandeAnnulationCartePlafond(@PathVariable Long id) {
		carburant.deleteHistoriqueDemandeAnnulationCartePlafond(id);
	}

	@DeleteMapping(path = "/delete_carte_jocker/{id}")
	public void deleteOneCarteJocker(@PathVariable Long id) {
		carburant.deleteOneCarteJocker(id);
	}

	@PostMapping(path = "/confirm_declaration_perte_carte_jocker")
	public void confirmOneDeclarationPerteCarteJocker(@RequestBody Long id) {
		carburant.confirmOneDeclarationPerteCarteJocker(id);
	}

	@DeleteMapping(path = "/declaration_perte_carte_jocker/{id}")
	public void deleteOneDeclarationPerteCarteJocker(@PathVariable Long id) {
		carburant.deleteOneDeclarationPerteCarteJocker(id);
	}
	
	//Demande recharge de sous compte :
	
	@GetMapping(path = "/pagination_demande_recharge_sous_compte")
	ResponseEntity<List<RechargeSousCompte>> getPaginationRechargeSousCompteList(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();

		List<RechargeSousCompte> demandeRechargeSousCompteListResponse = new ArrayList<>();
		List<DemandeRechargeSousCompteDTO> demandeRechargeSousCompteTabDataDTOS = carburant
				.getPaginationDemandeRechargeSousCompteList(Integer.parseInt(page), Integer.parseInt(limit));

		if (!demandeRechargeSousCompteTabDataDTOS.isEmpty()) {
			demandeRechargeSousCompteTabDataDTOS.forEach(demandeRechargeSousCompteDTO -> {
				demandeRechargeSousCompteListResponse
						.add(modelMapper.map(demandeRechargeSousCompteDTO, RechargeSousCompte.class));
			});
		}
		return new ResponseEntity<>(demandeRechargeSousCompteListResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/demande_recharge_sous_compte/{id}")
	public void deleteDemandeRechargeSousCompte(@PathVariable Long id) {
		carburant.deleteDemandeRechargeSousCompte(id);
	}

	@PostMapping(path = "/confirm_demande_recharge_sous_compte")
	public ResponseEntity<HttpStatus> confirmSelectedDemandeRechargeSousCompte(@RequestBody Long id) {
		carburant.confirmSelectedDemandeRechargeSousCompte(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_demande_recharge_sous_compte")
	public ResponseEntity<HttpStatus> validateSelectedDemandeRechargeSousCompte(@RequestBody Long id) {
		carburant.validateSelectedDemandeRechargeSousCompte(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/creation_demande_recharge_sous_compte")
	ResponseEntity<HttpStatus> createDemandeRechargeSousCompte(
			@RequestBody RechargeSousCompte rechargeSousCompte) {
		System.out.print(rechargeSousCompte);
		carburant.addNewDemandeRechargeSousCompte(rechargeSousCompte);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping(path = "/pagination_historique_recharge_operation")
	ResponseEntity<List<HistoriqueOperationRecharge>> getPaginationHistoriqueList(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit)
	{
	ModelMapper modelMapper = new ModelMapper();
	
	List<HistoriqueOperationRecharge> historiqueListResponse = new ArrayList<>();
	
	List<HistoriqueOperationRecharge> historiqueDTOS = carburant.getPaginationHistoriqueRechargeList(Integer.parseInt(page), Integer.parseInt(limit));
	
	if (!historiqueDTOS.isEmpty())
	{
		historiqueDTOS.forEach(HistoriqueOperationRecharge -> {
			historiqueListResponse.add(modelMapper.map(HistoriqueOperationRecharge, HistoriqueOperationRecharge.class));
	});
	}
	return new ResponseEntity<>(historiqueListResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete_historique_recharge/{id}")
	public ResponseEntity<HttpStatus> deleteHistoriqueRecharge(@PathVariable("id") Long id) {
		carburant.deleteSelectedHistoriqueRecharge(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@GetMapping(path = "/notif")
	public int  NombreNotif() {
		return carburant.NombreNotif();
	}
	
	@GetMapping(path = "/notifValid")
	public int  NombreNotifValid() {
		return carburant.NombreNotifValid();
	}
		

	
	
	

	@GetMapping(path = "/notif_complementaire")
	public int  NombreNotifRechargeComplementaire() {
		return carburant.NombreNotifRechargeComplementaire();
	}
	
	@GetMapping(path = "/notifValid_complementaire")
	public int  NombreNotifValidRechargeComplementaire() {
		return carburant.NombreNotifValidRechargeComplementaire();
	}
	
	

	@GetMapping(path = "/notif_compensation")
	public int  NombreNotifRechargeCompensation() {
		return carburant.NombreNotifRechargeCompensation();
	}
	
	@GetMapping(path = "/notifValid_compensation")
	public int  NombreNotifValidRechargeCompensation() {
		return carburant.NombreNotifValidRechargeCompensation();
	}
	
	//==================Recharge quota mensuel==============================
	
	@PostMapping(path = "/confirm_recharge_quota_mensuel")
	public ResponseEntity<HttpStatus> confirmRechargeQuotaMensuel(@RequestBody Long id) {
		carburant.confirmRechargeQuotaMensuel(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_recharge_quota_mensuel")
	public ResponseEntity<HttpStatus> validRechargeQuotaMensuel(@RequestBody Long id) {
		carburant.validRechargeQuotaMensuel(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/creation_recharge_quota_mensuel")
	ResponseEntity<HttpStatus> addNewRechargeQuotaMensuel(
			@RequestBody RechargeQuotaMensuelEntity rechargeQuotaMensuelEntity) {
		System.out.print(rechargeQuotaMensuelEntity);
		carburant.addNewRechargeQuotaMensuel(rechargeQuotaMensuelEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/pagination_recharge_quota_mensuel")
	ResponseEntity<List<RechargeQuotaMensuelEntity>> getPaginationRechargeQuotaMensuelEntityList(
			@RequestParam(name = "cartePlafond") String cartePlafond,
			@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();

		List<RechargeQuotaMensuelEntity> rechargeQuotaMensuelEntity = new ArrayList<>();
		List<RechargeQuotaMensuelDTO> rechargeQuotaMensuelDTOS = carburant
				.getPaginationRechargeQuotaMensuelList(cartePlafond,Integer.parseInt(page), Integer.parseInt(limit));

		if (!rechargeQuotaMensuelDTOS.isEmpty()) {
			rechargeQuotaMensuelDTOS.forEach(rechargeQuotaMensuelDTO -> {
				rechargeQuotaMensuelEntity
						.add(modelMapper.map(rechargeQuotaMensuelDTO, RechargeQuotaMensuelEntity.class));
			});
		}
		return new ResponseEntity<>(rechargeQuotaMensuelEntity, HttpStatus.OK);
	}
	
	@GetMapping(path = "/pagination_historique_recharge_quota_mensuel")
	ResponseEntity<List<HistoriqueRechargeQuotaMensuelEntity>> getPaginationHistoriqueRechargeQMList(
			@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<HistoriqueRechargeQuotaMensuelEntity> RechargeCarburantQM = new ArrayList<>();
		List<HistoriqueRechargeQuotaMensuelEntity> RechargeCarburantQMaDTOS = carburant
				.getPaginationHistoriqueRechargeQMList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!RechargeCarburantQMaDTOS.isEmpty()) {
			RechargeCarburantQMaDTOS.forEach(RechargeQMDTO -> {
				RechargeCarburantQM
						.add(modelMapper.map(RechargeQMDTO, HistoriqueRechargeQuotaMensuelEntity.class));
			});
		}
		return new ResponseEntity<>(RechargeCarburantQM, HttpStatus.OK);
	}


	
	@GetMapping(path = "/notif_ajout_quota_mensuel")
	public int  NbNotif() {
		return carburant.NombreNotif();
	}
	

	//=================================

	
	
	
	
	/****************************/

	@GetMapping(path = "/list_recharge_carburant_compensation")
	public Iterable<RechargeCarburantCompensation> getAllRechargeCarburantCompensations() {
		return carburant.getAllRechargeCarburantCompensations();
	}

	@GetMapping(path = "/total_number_recharge_carburant_compensation")
	ResponseEntity<Long> getTotalNumberRechargeCarburantCompensation() {
		Long totalNumber = carburant.getTotalNumberRechargeCarburantCompensation();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}
/*
	@GetMapping(path = "/pagination_recharge_carburant_compensation")
	ResponseEntity<List<RechargeCarburantCompensation>> getPaginationRechargeCarburantCompensationList(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RechargeCarburantCompensation> RechargeCarburantCompensationListResponse = new ArrayList<>();
		List<ListRechargeCarburantCompensationDTO> RechargeCarburantCompensationTabDataDTOS = carburant
				.getPaginationRechargeCarburantCompensationList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!RechargeCarburantCompensationTabDataDTOS.isEmpty()) {
			RechargeCarburantCompensationTabDataDTOS.forEach(RechargeCarburantCompensationTabDataDTO -> {
				RechargeCarburantCompensationListResponse
						.add(modelMapper.map(RechargeCarburantCompensationTabDataDTO, RechargeCarburantCompensation.class));
			});
		}
		return new ResponseEntity<>(RechargeCarburantCompensationListResponse, HttpStatus.OK);
	}
*/

	@GetMapping(path = "/pagination_recharge_carburant_compensation_filtrage")
	ResponseEntity<List<RechargeCarburantCompensation>> getPaginationRechargeCarburantCompensationfiltrageList(
			@RequestParam(name = "cartePlafond") String cartePlafond,
			@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RechargeCarburantCompensation> RechargeCarburantCompensationListResponse = new ArrayList<>();
		List<ListRechargeCarburantCompensationDTO> RechargeCarburantCompensationTabDataDTOS = carburant
				.getPaginationRechargeCarburantCompensationList(cartePlafond,Integer.parseInt(page), Integer.parseInt(limit));
		if (!RechargeCarburantCompensationTabDataDTOS.isEmpty()) {
			RechargeCarburantCompensationTabDataDTOS.forEach(RechargeCarburantCompensationTabDataDTO -> {
				RechargeCarburantCompensationListResponse
						.add(modelMapper.map(RechargeCarburantCompensationTabDataDTO, RechargeCarburantCompensation.class));
			});
		}
		return new ResponseEntity<>(RechargeCarburantCompensationListResponse, HttpStatus.OK);
	}


	@GetMapping(path = "/pagination_recharge_carburant_compensation_historique")
	ResponseEntity<List<RechargeCarburantCompensation>> getPaginationRechargeCarburantCompensationfiltrageHistoriqueList(
			@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RechargeCarburantCompensation> RechargeCarburantCompensationListResponse = new ArrayList<>();
		List<ListRechargeCarburantCompensationDTO> RechargeCarburantCompensationTabDataDTOS = carburant
				.getPaginationHistoriqueRechargeCarburantCompensationList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!RechargeCarburantCompensationTabDataDTOS.isEmpty()) {
			RechargeCarburantCompensationTabDataDTOS.forEach(RechargeCarburantCompensationTabDataDTO -> {
				RechargeCarburantCompensationListResponse
						.add(modelMapper.map(RechargeCarburantCompensationTabDataDTO, RechargeCarburantCompensation.class));
			});
		}
		return new ResponseEntity<>(RechargeCarburantCompensationListResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/demande_recharge_carburant_compensation/{id}")
	public RechargeCarburantCompensation getRechargeCarburantCompensationById(@PathVariable("id") Long id) {
		return carburant.getRechargeCarburantCompensation(id);
	}

	@PostMapping(path = "/creation_recharge_carburant_compensation")
	ResponseEntity<HttpStatus> createRechargeCarburantCompensation(
			@RequestBody RechargeCarburantCompensation RechargeCarburantCompensation) {
		carburant.addNewRechargeCarburantCompensation(RechargeCarburantCompensation);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/modify_recharge_carburant_compensation")
	void updateRechargeCarburantCompensationl(
			@RequestBody ModifyRechargeCarburantCompensationRequest modifyRechargeCarburantCompensationRequest) {
		ModelMapper modelMapper = new ModelMapper();
		ModifyRechargeCarburantCompensationDTO modifyRechargeCarburantCompensationDTO = modelMapper
				.map(modifyRechargeCarburantCompensationRequest, ModifyRechargeCarburantCompensationDTO.class);

		carburant.modifySelectedRechargeCarburantCompensation(modifyRechargeCarburantCompensationDTO);

	}

	@DeleteMapping(path = "/delete_recharge_carburant_compensation/{id}")
	public ResponseEntity<HttpStatus> deleteRechargeCarburantCompensation(@PathVariable("id") Long id) {
		carburant.deleteSelectedRechargeCarburantCompensation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/confirm_recharge_carburant_compensation")
	public ResponseEntity<HttpStatus> confirmSelectedRechargeCarburantCompensation(@RequestBody Long id) {
		carburant.confirmSelectedRechargeCarburantCompensation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_recharge_carburant_compensation")
	public ResponseEntity<HttpStatus> validateSelectedRechargeCarburantCompensation(@RequestBody Long id) {
		carburant.validateSelectedRechargeCarburantCompensation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	
	/****************************/

	@GetMapping(path = "/list_recharge_complementaire")
	public Iterable<RechargeComplementaire> getAllRechargeComplementaires() {
		return carburant.getAllRechargeComplementaires();
	}

	@GetMapping(path = "/total_number_recharge_complementaire")
	ResponseEntity<Long> getTotalNumberRechargeComplementaire() {
		Long totalNumber = carburant.getTotalNumberRechargeComplementaire();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}
/*
	@GetMapping(path = "/pagination_recharge_complementaire")
	ResponseEntity<List<RechargeComplementaire>> getPaginationRechargeComplementaireList(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RechargeComplementaire> RechargeComplementaireListResponse = new ArrayList<>();
		List<ListRechargeComplementaireDTO> RechargeComplementaireTabDataDTOS = carburant
				.getPaginationRechargeComplementaireList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!RechargeComplementaireTabDataDTOS.isEmpty()) {
			RechargeComplementaireTabDataDTOS.forEach(RechargeComplementaireTabDataDTO -> {
				RechargeComplementaireListResponse
						.add(modelMapper.map(RechargeComplementaireTabDataDTO, RechargeComplementaire.class));
			});
		}
		return new ResponseEntity<>(RechargeComplementaireListResponse, HttpStatus.OK);
	}
*/

	@GetMapping(path = "/pagination_recharge_complementaire_filtrage")
	ResponseEntity<List<RechargeComplementaire>> getPaginationRechargeComplementairefiltrageList(
			@RequestParam(name = "cartePlafond") String cartePlafond,
			@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RechargeComplementaire> RechargeComplementaireListResponse = new ArrayList<>();
		List<ListRechargeComplementaireDTO> RechargeComplementaireTabDataDTOS = carburant
				.getPaginationRechargeComplementaireList(cartePlafond,Integer.parseInt(page), Integer.parseInt(limit));
		if (!RechargeComplementaireTabDataDTOS.isEmpty()) {
			RechargeComplementaireTabDataDTOS.forEach(RechargeComplementaireTabDataDTO -> {
				RechargeComplementaireListResponse
						.add(modelMapper.map(RechargeComplementaireTabDataDTO, RechargeComplementaire.class));
			});
		}
		return new ResponseEntity<>(RechargeComplementaireListResponse, HttpStatus.OK);
	}


	@GetMapping(path = "/pagination_recharge_complementaire_historique")
	ResponseEntity<List<RechargeComplementaire>> getPaginationRechargeComplementaireHistoriqueList(
			@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RechargeComplementaire> RechargeComplementaireListResponse = new ArrayList<>();
		List<ListRechargeComplementaireDTO> RechargeComplementaireTabDataDTOS = carburant
				.getPaginationHistoriqueRechargeComplementaireList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!RechargeComplementaireTabDataDTOS.isEmpty()) {
			RechargeComplementaireTabDataDTOS.forEach(RechargeComplementaireTabDataDTO -> {
				RechargeComplementaireListResponse
						.add(modelMapper.map(RechargeComplementaireTabDataDTO, RechargeComplementaire.class));
			});
		}
		return new ResponseEntity<>(RechargeComplementaireListResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/demande_recharge_complementaire/{id}")
	public RechargeComplementaire getRechargeComplementaireById(@PathVariable("id") Long id) {
		return carburant.getRechargeComplementaire(id);
	}

	@PostMapping(path = "/creation_recharge_complementaire")
	ResponseEntity<HttpStatus> createRechargeComplementaire(
			@RequestBody RechargeComplementaire RechargeComplementaire) {
		carburant.addNewRechargeComplementaire(RechargeComplementaire);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/modify_recharge_complementaire")
	void updateRechargeComplementairel(
			@RequestBody ModifyRechargeComplementaireRequest modifyRechargeComplementaireRequest) {
		ModelMapper modelMapper = new ModelMapper();
		ModifyRechargeComplementaireDTO modifyRechargeComplementaireDTO = modelMapper
				.map(modifyRechargeComplementaireRequest, ModifyRechargeComplementaireDTO.class);

		carburant.modifySelectedRechargeComplementaire(modifyRechargeComplementaireDTO);

	}

	@DeleteMapping(path = "/delete_recharge_complementaire/{id}")
	public ResponseEntity<HttpStatus> deleteRechargeComplementaire(@PathVariable("id") Long id) {
		carburant.deleteSelectedRechargeComplementaire(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/confirm_recharge_complementaire")
	public ResponseEntity<HttpStatus> confirmSelectedRechargeComplementaire(@RequestBody Long id) {
		carburant.confirmSelectedRechargeComplementaire(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_recharge_complementaire")
	public ResponseEntity<HttpStatus> validateSelectedRechargeComplementaire(@RequestBody Long id) {
		carburant.validateSelectedRechargeComplementaire(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_historique_recharge_compensation")
	ResponseEntity<List<HistoriqueRechargeCarburantCompensation>> getPaginationHistoriqueRechargeCarburantCompensationList(
			@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit){
	ModelMapper modelMapper = new ModelMapper();
	List<HistoriqueRechargeCarburantCompensation> historiqueListResponse = new ArrayList<>();
	List<HistoriqueRechargeCarburantCompensation> historiqueDTOS = carburant.getPaginationHistoriqueRechargeCompensationList1(Integer.parseInt(page), Integer.parseInt(limit));
	if (!historiqueDTOS.isEmpty())
	{
		historiqueDTOS.forEach(HistoriqueOperationRecharge -> {
			historiqueListResponse.add(modelMapper.map(HistoriqueOperationRecharge, HistoriqueRechargeCarburantCompensation.class));
	});
	}
	return new ResponseEntity<>(historiqueListResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete_historique_recharge_compensation/{id}")
	public ResponseEntity<HttpStatus> deleteHistoriqueRechargeCarburantCompensation(@PathVariable("id") Long id) {
		carburant.deleteSelectedHistoriqueRecharge(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_historique_recharge_complementaire")
	ResponseEntity<List<HistoriqueRecahrgeComplementaire>> getPaginationHistoriqueRecahrgeComplementaireList(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit)
	{
	ModelMapper modelMapper = new ModelMapper();	
	List<HistoriqueRecahrgeComplementaire> historiqueListResponse = new ArrayList<>();
	List<HistoriqueRecahrgeComplementaire> historiqueDTOS = carburant.getPaginationHistoriqueRechargeComplementaireList1(Integer.parseInt(page), Integer.parseInt(limit));
	if (!historiqueDTOS.isEmpty())
	{
		historiqueDTOS.forEach(HistoriqueOperationRecharge -> {
			historiqueListResponse.add(modelMapper.map(HistoriqueOperationRecharge, HistoriqueRecahrgeComplementaire.class));
	});
	}
	return new ResponseEntity<>(historiqueListResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete_historique_recharge_complementaire/{id}")
	public ResponseEntity<HttpStatus> deleteHistoriqueHistoriqueRecahrgeComplementaire(@PathVariable("id") Long id) {
		carburant.deleteSelectedHistoriqueRecharge(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
