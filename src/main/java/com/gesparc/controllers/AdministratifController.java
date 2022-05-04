package com.gesparc.controllers;

import com.gesparc.entities.administratif.AssuranceEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.administratif.DocumentEntity;
import com.gesparc.entities.administratif.GpsEntity;
import com.gesparc.entities.administratif.ReformeEntity;
import com.gesparc.entities.administratif.TaxeCirculationEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.administratif.VisiteTechniqueEntity;
import com.gesparc.entities.carburant.AgilisFileData;
import com.gesparc.entities.carburant.PricesEntity;
import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.entities.exploitation.EmpruntVehiculeEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.repositories.AssuranceRepository;
import com.gesparc.repositories.CarteAgilisCashRepository;
import com.gesparc.repositories.CarteJockerRepository;
import com.gesparc.repositories.CartePlafondRepository;
import com.gesparc.repositories.DocumentRepository;
import com.gesparc.repositories.ReformeRepository;
import com.gesparc.repositories.TaxeCirculationRepository;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.VisiteTechniqueRepository;
import com.gesparc.repositories.administratif.GpsRepository;
import com.gesparc.repositories.carburants.AgilisFileDataRepository;
import com.gesparc.repositories.carburants.PricesRepository;
import com.gesparc.repositories.exploitation.EmpruntVehiculeRepository;
import com.gesparc.repositories.referentiel.StructureRepository;
import com.gesparc.requests.Administratif.Additionnel.CustomSearchingTaxeCirculationRequest;
import com.gesparc.requests.Administratif.Additionnel.NewAssuranceRequest;
import com.gesparc.requests.Administratif.Additionnel.NewVehiculeRequest;
import com.gesparc.requests.Carburant.AgilisFileDataRequest;
import com.gesparc.requests.Carburant.PricesRequests;
import com.gesparc.responses.administratif.TaxeCirculationResponse;
import com.gesparc.responses.administratif.additionnel.*;
import com.gesparc.responses.carburant.AgilisFileDataResponse;
import com.gesparc.responses.carburant.AgilisPriceResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.AdministratifImpl;
import com.gesparc.sharedDTO.administratif.Additionnel.*;
import com.gesparc.sharedDTO.carburant.additionnel.AgilisFileDataTableDTO;
import com.gesparc.sharedDTO.carburant.additionnel.AgilisPriceDataTableDTO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gesparc.entities.administratif.AssuranceEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.administratif.DocumentEntity;
import com.gesparc.entities.administratif.GpsEntity;
import com.gesparc.entities.administratif.ReformeEntity;
import com.gesparc.entities.administratif.TaxeCirculationEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.administratif.VisiteTechniqueEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.repositories.AssuranceRepository;
import com.gesparc.repositories.DocumentRepository;
import com.gesparc.repositories.ReformeRepository;
import com.gesparc.repositories.TaxeCirculationRepository;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.VisiteTechniqueRepository;
import com.gesparc.repositories.administratif.GpsRepository;
import com.gesparc.requests.Administratif.Additionnel.CustomSearchingTaxeCirculationRequest;
import com.gesparc.requests.Administratif.Additionnel.NewAssuranceRequest;
import com.gesparc.requests.Administratif.Additionnel.NewVehiculeRequest;
import com.gesparc.responses.administratif.TaxeCirculationResponse;
import com.gesparc.responses.administratif.additionnel.*;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.AdministratifImpl;
import com.gesparc.sharedDTO.administratif.Additionnel.*;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class AdministratifController 
{

	Logger logger = LoggerFactory.getLogger(AdministratifController.class);

	@Autowired
	private PricesRepository  pricesRepository;
	
	@Autowired
	private CartePlafondRepository cartePlafondRepository;
	

    @Autowired
	private EmpruntVehiculeRepository  empruntRepository;
	
	@Autowired
	private CarteJockerRepository carteJockerRepository;
	
	@Autowired
	private AgilisFileDataRepository agilisFileDataRepository;
	
	 @Autowired
	 private CarteAgilisCashRepository carteAgilisCashRepository;
	@Autowired
	AdministratifImpl administratif;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VehiculeRepository vehiculeRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private GpsRepository gpsRepository;

	@Autowired
	private AssuranceRepository assuranceRepository;

	@Autowired
	private TaxeCirculationRepository taxeCirculationRepository;

	@Autowired
	private VisiteTechniqueRepository visiteTechniqueRepository;

	@Autowired
	private ReformeRepository reformeRepository;
	@Autowired

	private StructureRepository structureRepository;

	@Autowired
	private TracabiliteController tracabiliteController;

	public TracabiliteController getTracabiliteController()
	{
		 return tracabiliteController;
	}

	public void setTracabiliteController(TracabiliteController tracabiliteController) 
	{
		 this.tracabiliteController = tracabiliteController;
	}
    
	@PostMapping(path = "/vehicule")
	public ResponseEntity<Long> addNewVehicule(@RequestBody NewVehiculeDTO newVehiculeDTO) {
		newVehiculeDTO.setDateAjout(LocalDate.now());
		Long idVehicule = administratif.addNewVehicule(newVehiculeDTO);
		tracabiliteController.addNewVehiculeTracabilite(newVehiculeDTO.getNumeroPlaque(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(idVehicule, HttpStatus.OK);
	}

	@PutMapping(path = "/vehicule")
	public ResponseEntity<Long> modifySelectedVehicule(@RequestBody NewVehiculeRequest newVehiculeRequest) 
	{
		 ModelMapper modelMapper = new ModelMapper();
		 NewVehiculeDTO newVehiculeDTO = modelMapper.map(newVehiculeRequest, NewVehiculeDTO.class);
		 Long idVehicule = administratif.modifySelectedVehicule(newVehiculeDTO);
		 tracabiliteController.modifySelectedVehiculeTracabilite(newVehiculeDTO.getNumeroPlaque(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(idVehicule, HttpStatus.OK);
	}

	@DeleteMapping(path = "/vehicule/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedVehicule(@PathVariable Long id) 
	{
		 VehiculeEntity v = vehiculeRepository.findById(id).get();
		 EmpruntVehiculeEntity e = empruntRepository.findByVehiculeId(id);
			if (e!=null) {
				e.setVehicule(null)	;
			}
		 administratif.deleteSelectedVehicule(id);
		 tracabiliteController.deleteSelectedVehiculeTracabilite(v.getNumeroPlaque(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/vehicule/{id}")
	public ResponseEntity<OneVehiculeResponse> getSelectedVehicule(@PathVariable Long id)
	{
		 ModelMapper modelMapper = new ModelMapper();
		 modelMapper.getConfiguration().setAmbiguityIgnored(true);
		 OneVehiculeDTO oneVehiculeDTO = administratif.getSelectedVehicule(id);
		 OneVehiculeResponse oneVehiculeResponse = modelMapper.map(oneVehiculeDTO, OneVehiculeResponse.class);
		 return new ResponseEntity<>(oneVehiculeResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_vehicule")
	public ResponseEntity<List<VehiculeTableDataResponse>> getPaginationVehiculeTableDataList(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ModelMapper modelMapper = new ModelMapper();
		 List<VehiculeTableDataResponse> vehiculeTableDataResponse = new ArrayList<>();
		 List<VehiculeTableDataDTO> vehiculeTableDataDTOS = administratif.getPaginationListVehiculeTableData(Integer.parseInt(page), Integer.parseInt(limit), userEntity);
		 if (!vehiculeTableDataDTOS.isEmpty()) 
		 {
			  vehiculeTableDataDTOS.forEach(vehiculeTableDataDTO -> {
			  vehiculeTableDataResponse.add(modelMapper.map(vehiculeTableDataDTO, VehiculeTableDataResponse.class));});
		 }
		 return new ResponseEntity<>(vehiculeTableDataResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_vehicule")
	public ResponseEntity<Long> getTotalItemVehiculeTableDataList()
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 Long totalItem = administratif.getTotalItemVehiculeTableDataList(userEntity);
		 return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@GetMapping(path = "/select_vehicule_taxe_circulation")
	public ResponseEntity<List<SelectVehiculeResponse>> getSelectVehiculeByStrucutureForTaxeCirculation() 
	{
		 List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<SelectVehiculeDTO> selectVehiculeDTOS = administratif.getSelectVehiculeByStrucutureForTaxeCirculation(userEntity);
		 if (!selectVehiculeDTOS.isEmpty()) 
		 {
			  selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
			  selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));});
		 }
		 return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/beneficiaire")
	public ResponseEntity<List<BeneficiaireEntity>> getListBeneficiaire() 
	{
		 List<BeneficiaireEntity> beneficiaireEntities = administratif.getListBeneficiaire();
		 return new ResponseEntity<>(beneficiaireEntities, HttpStatus.OK);
	}

	@PostMapping(path = "/taxe_circulation/{idVehicule}")
	public ResponseEntity<HttpStatus> addNewTaxeCirculation(@RequestBody TaxeCirculationEntity taxeCirculationEntity,@PathVariable Long idVehicule) 
	{
		 administratif.addNewTaxeCirculation(taxeCirculationEntity, idVehicule);
		 VehiculeEntity v = vehiculeRepository.findById(idVehicule).get();
		 tracabiliteController.addNewTaxeCirculationTracabilite(v.getNumeroPlaque(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/taxe_circulation")
	public ResponseEntity<HttpStatus> modifySelectedTaxeCirculation(@RequestBody TaxeCirculationTableDataDTO taxeCirculationTableDataDTO) 
	{
		 administratif.modifySelectedTaxeCirculation(taxeCirculationTableDataDTO);
		 tracabiliteController.modifySelectedTaxeCirculationTracabilite(taxeCirculationTableDataDTO.getNumeroPlaque(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_taxe_circulation")
	public ResponseEntity<List<TaxeCirculationTableDataResponse>> getPaginationTaxeCirculationList(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ModelMapper modelMapper = new ModelMapper();
		 List<TaxeCirculationTableDataResponse> taxeCirculationTableDataResponses = new ArrayList<>();
		 List<TaxeCirculationTableDataDTO> taxeCirculationTableDataDTOS = administratif.getPaginationTaxeCirculationList(Integer.parseInt(page), Integer.parseInt(limit), userEntity);
		 if (!taxeCirculationTableDataDTOS.isEmpty()) 
		 {
			   taxeCirculationTableDataDTOS.forEach(taxeCirculationTableDataDTO -> {
			   taxeCirculationTableDataResponses.add(modelMapper.map(taxeCirculationTableDataDTO, TaxeCirculationTableDataResponse.class));});
		 }
		 return new ResponseEntity<>(taxeCirculationTableDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_taxe_circulation")
	public ResponseEntity<Long> getTotalItemTaxeCirculationList()
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ModelMapper modelMapper = new ModelMapper();
		 List<TaxeCirculationResponse> taxeCirculationResponses = new ArrayList<>();
		 Long totalItem = administratif.getTotalItemTaxeCirculationList(userEntity);
		 return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@DeleteMapping(path = "/taxe_circulation/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedTaxeCirculation(@PathVariable Long id) 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 TaxeCirculationEntity tc = taxeCirculationRepository.findById(id).get();
		 tracabiliteController.deleteSelectedTaxeCirculationTracabilite(tc.getVehicule().getNumeroPlaque(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 administratif.deleteSelectedTaxeCirculation(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/assurance")
	public ResponseEntity<HttpStatus> addNewAssurance(@RequestBody NewAssuranceRequest newAssuranceRequest) {
		ModelMapper modelMapper = new ModelMapper();
		NewAssuranceDTO newAssuranceDTO = modelMapper.map(newAssuranceRequest, NewAssuranceDTO.class);
		administratif.addNewAssurance(newAssuranceDTO);
		tracabiliteController.addNewAssuranceTracabilite(newAssuranceDTO.getCompagnieAssurance(),
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/select_vehicule_assurance")
	public ResponseEntity<List<SelectVehiculeResponse>> getSelectVehiculeByStrucutureForAssurance() 
	{
		 List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<SelectVehiculeDTO> selectVehiculeDTOS = administratif.getSelectVehiculeByStrucutureForAssurance(userEntity);
		 if (!selectVehiculeDTOS.isEmpty()) 
		 {
			  selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
			  selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));});
		 }
		 return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_assurance")
	public ResponseEntity<List<AssuranceTableDataResponse>> getPaginationAssuranceList(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ModelMapper modelMapper = new ModelMapper();
		 List<AssuranceTableDataResponse> assuranceTableDataResponses = new ArrayList<>();
		 List<AssuranceTableDataDTO> assuranceTableDataDTOS = administratif.getPaginationAssuranceList(Integer.parseInt(page), Integer.parseInt(limit), userEntity);
		 if (!assuranceTableDataDTOS.isEmpty()) 
		 {
			  assuranceTableDataDTOS.forEach(assuranceTableDataDTO -> {
			  assuranceTableDataResponses.add(modelMapper.map(assuranceTableDataDTO, AssuranceTableDataResponse.class));});
		 }
		 return new ResponseEntity<>(assuranceTableDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_assurance")
	public ResponseEntity<Long> getTotalItemAssuranceList()
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 Long totalItem = administratif.getTotalItemAssuranceList(userEntity);
		 return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@PutMapping(path = "/assurance")
	public ResponseEntity<HttpStatus> modifySelectedAssurance(@RequestBody NewAssuranceRequest newAssuranceRequest) 
	{
		 ModelMapper modelMapper = new ModelMapper();
		 NewAssuranceDTO newAssuranceDTO = modelMapper.map(newAssuranceRequest, NewAssuranceDTO.class);
		 administratif.modifySelectedAssurance(newAssuranceDTO);
		 tracabiliteController.modifySelectedAssuranceTracabilite(newAssuranceDTO.getCompagnieAssurance(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/assurance/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedAssurance(@PathVariable Long id) 
	{
		 AssuranceEntity ae = assuranceRepository.findById(id).get();
		 tracabiliteController.deleteSelectedAssuranceTracabilite(ae.getCompagnieAssurance(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 administratif.deleteSelectedAssurance(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/visite_technique")
	public ResponseEntity<HttpStatus> addNewVisiteTechnique(@RequestBody VisiteTechniqueTableDataDTO visiteTechniqueTableDataDTO) 
	{
		 administratif.addNewVisiteTechnique(visiteTechniqueTableDataDTO);
		 VehiculeEntity v = vehiculeRepository.findById(visiteTechniqueTableDataDTO.getIdVehicule()).get();
		 tracabiliteController.addNewVisiteTechniqueTracabilite(v.getNumeroPlaque(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/select_vehicule_visite_technique")
	public ResponseEntity<List<SelectVehiculeResponse>> getSelectVehiculeByStrucutureForVisiteTechnique() 
	{
		 List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<SelectVehiculeDTO> selectVehiculeDTOS = administratif.getSelectVehiculeByStrucutureForVisiteTechnique(userEntity);
		 if (!selectVehiculeDTOS.isEmpty()) 
		 {
			selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
				selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));});
		 }
		 return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}
	@GetMapping("/liste_structre")
	public Iterable<StructureEntity> listStructure() {
		return structureRepository.findAll();
	}
	@GetMapping(path = "/pagination_visite_technique")
	public ResponseEntity<List<VisiteTechniqueTableDataResponse>> getPaginationVisiteTechniqueList(
			@RequestParam(name = "structure") String structure, @RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		UserEntity userEntity = userRepository
				.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		ModelMapper modelMapper = new ModelMapper();
		List<VisiteTechniqueTableDataResponse> visiteTechniqueTableDataResponses = new ArrayList<>();
		List<VisiteTechniqueTableDataDTO> visiteTechniqueTableDataDTOS = administratif
				.getPaginationVisiteTechniqueList(structure,Integer.parseInt(page), Integer.parseInt(limit), userEntity);
		if (!visiteTechniqueTableDataDTOS.isEmpty()) {
			visiteTechniqueTableDataDTOS.forEach(visiteTechniqueTableDataDTO -> {
				visiteTechniqueTableDataResponses
						.add(modelMapper.map(visiteTechniqueTableDataDTO, VisiteTechniqueTableDataResponse.class));
			});
		}
		return new ResponseEntity<>(visiteTechniqueTableDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_visite_technique")
	public ResponseEntity<Long> getTotalItemVisiteTechniqueList() 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 Long totalItem = administratif.getTotalItemVisiteTechniqueList(userEntity);
		 return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@PutMapping(path = "/visite_technique")
	public ResponseEntity<HttpStatus> modifySelectedVisiteTechnique(@RequestBody VisiteTechniqueTableDataDTO visiteTechniqueTableDataDTO) 
	{
		System.out.println(visiteTechniqueTableDataDTO.getId());
		 administratif.modifySelectedVisiteTechnique(visiteTechniqueTableDataDTO);
		 tracabiliteController.modifySelectedVisiteTechniqueTracabilite(
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/visite_technique/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedVisiteTechnique(@PathVariable Long id) 
	{
		 VisiteTechniqueEntity vte = visiteTechniqueRepository.findById(id).get();
		 VehiculeEntity ve = vehiculeRepository.findById(vte.getVehicule().getId()).get();
		 tracabiliteController.deleteSelectedVisiteTechniqueTracabilite(ve.getNumeroPlaque(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 administratif.deleteSelectedVisiteTechnique(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/reforme")
	public ResponseEntity<HttpStatus> addNewReforme(@RequestBody ReformeTableDataDTO reformeTableDataDTO) 
	{
		 administratif.addNewReforme(reformeTableDataDTO);
		 tracabiliteController.addNewReformeTracabilite(reformeTableDataDTO.getNom(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/reforme")
	public ResponseEntity<HttpStatus> modifySelectedReforme(@RequestBody ReformeTableDataDTO reformeTableDataDTO) 
	{
		 administratif.modifySelectedReforme(reformeTableDataDTO);
		 tracabiliteController.modifySelectedReformeTracabilite(reformeTableDataDTO.getNom(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/select_vehicule_reforme")
	public ResponseEntity<List<SelectVehiculeResponse>> getSelectVehiculeByStrucutureForReforme() 
	{
		 List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<SelectVehiculeDTO> selectVehiculeDTOS = administratif.getSelectVehiculeByStrucutureForReforme(userEntity);
		 if (!selectVehiculeDTOS.isEmpty()) 
		 {
			   selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
			   selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));});
		 }
		 return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_reforme")
	public ResponseEntity<List<ReformeTableDataResponse>> getPaginationReformeList(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ModelMapper modelMapper = new ModelMapper();
		 List<ReformeTableDataResponse> reformeTableDataResponses = new ArrayList<>();
		 List<ReformeTableDataDTO> reformeTableDataDTOS = administratif.getPaginationReformeList(Integer.parseInt(page),Integer.parseInt(limit), userEntity);
		 if (!reformeTableDataDTOS.isEmpty()) 
		 {
			  reformeTableDataDTOS.forEach(reformeTableDataDTO -> {
			  reformeTableDataResponses.add(modelMapper.map(reformeTableDataDTO, ReformeTableDataResponse.class));});
		 }
		 return new ResponseEntity<>(reformeTableDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_reforme")
	public ResponseEntity<Long> getTotalItemReformeList() 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 Long totalItem = administratif.getTotalItemReformeList(userEntity);
		 return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@DeleteMapping(path = "/reforme/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedReforme(@PathVariable Long id) 
	{
		 ReformeEntity re = reformeRepository.findById(id).get();
		 tracabiliteController.deleteSelectedReformeTracabilite(re.getNom(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 administratif.deleteSelectedReforme(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/gps/{idVehicule}")
	public ResponseEntity<HttpStatus> addNewGPS(@RequestBody GpsTableDataDTO gpsTableDataDTO,@PathVariable Long idVehicule) 
	{
		 administratif.addNewGPS(gpsTableDataDTO, idVehicule);
		 tracabiliteController.addNewGPSTracabilite(gpsTableDataDTO.getCodeIMEI(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/gps")
	public ResponseEntity<HttpStatus> modifySelectedGPS(@RequestBody GpsTableDataDTO gpsTableDataDTO) 
	{
		 administratif.modifySelectedGPS(gpsTableDataDTO);
		 tracabiliteController.modifySelectedGPSTracabilite(gpsTableDataDTO.getCodeIMEI(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/gps/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedGPS(@PathVariable Long id) 
	{
		 GpsEntity ge = gpsRepository.findById(id).get();
		 tracabiliteController.deleteSelectedGPSTracabilite(ge.getCodeIMEI(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 administratif.deleteSelectedGPS(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_gps")
	public ResponseEntity<List<GpsTableDataResponse>> getPaginationGPSList(@RequestParam(name = "page") String page,@RequestParam(name = "limit") String limit) 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ModelMapper modelMapper = new ModelMapper();
		 List<GpsTableDataResponse> gpsTableDataResponses = new ArrayList<>();
		 List<GpsTableDataDTO> gpsTableDataDTOS = administratif.getPaginationGPSList(Integer.parseInt(page),Integer.parseInt(limit), userEntity);
		 if (!gpsTableDataDTOS.isEmpty()) 
		 {
			  gpsTableDataDTOS.forEach(gpsTableDataDTO -> {
			  gpsTableDataResponses.add(modelMapper.map(gpsTableDataDTO, GpsTableDataResponse.class));});
		 }
		 return new ResponseEntity<>(gpsTableDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_gps")
	public ResponseEntity<Long> getTotalItemGPSList() 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 Long totalItem = administratif.getTotalItemGPSList(userEntity);
		 return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@GetMapping(path = "/select_vehicule_gps")
	public ResponseEntity<List<SelectVehiculeResponse>> getSelectVehiculeByStrucutureForGPS() 
	{
		 List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<SelectVehiculeDTO> selectVehiculeDTOS = administratif.getSelectVehiculeByStrucutureForGPS(userEntity);
		 if (!selectVehiculeDTOS.isEmpty()) 
		 {
			  selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
			  selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));});
		 }
		 return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}

	@PostMapping(path = "/upload_image_car/{id}")
	public ResponseEntity<HttpStatus> uploadImage(@PathVariable Long id, @RequestParam MultipartFile file) 
	{
		 String fileName = file.getOriginalFilename();
		 String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
		 File serverFile = new File("src/main/resources/Images/" + newFileName);
		 try 
		 {
			 FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			 VehiculeEntity vehiculeEntity = vehiculeRepository.findById(id).get();
			 vehiculeEntity.setUrlImageVehicule(newFileName);
			 vehiculeEntity.setNameImageVehicule(file.getOriginalFilename());
			 vehiculeRepository.save(vehiculeEntity);
		 } 
		 catch (IOException e) 
		 {
			 e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/image_car/{imageName}")
	public ResponseEntity<InputStreamResource> getByteImageCar(@PathVariable String imageName) throws IOException 
	{
		 ClassPathResource imgFile = new ClassPathResource("/Images/" + imageName);
		 return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(imgFile.getInputStream()));
	}

	@PostMapping(path = "/upload_document_car/{id}")
	public ResponseEntity<HttpStatus> uploadDocumentCar(@PathVariable Long id, @RequestParam MultipartFile file) 
	{
		 String fileName = file.getOriginalFilename();
		 String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
		 File serverFile = new File("src/main/resources/Documents/" + newFileName);
		 try 
		 {
			  FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			  VehiculeEntity vehiculeEntity = vehiculeRepository.findById(id).get();
			  DocumentEntity documentEntity = new DocumentEntity();
			  documentEntity.setNome(file.getOriginalFilename());
			  documentEntity.setAdresse(newFileName);
			  if (vehiculeEntity.getDocuments().isEmpty()) 
			  {
				  vehiculeEntity.setDocuments(new ArrayList<>());
			  }
			  vehiculeEntity.getDocuments().add(documentEntity);
			  documentEntity.setVehicule(vehiculeEntity);
			  vehiculeRepository.save(vehiculeEntity);
			  documentRepository.save(documentEntity);
		  }  
		  catch (IOException e) 
		  {
			  e.printStackTrace();
		  }
		  return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/document_car/{documentName}")
	public ResponseEntity<InputStreamResource> getByteDocumentCar(@PathVariable String documentName)throws IOException 
	{
		 ClassPathResource imgFile = new ClassPathResource("/Documents/" + documentName);
		 return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(imgFile.getInputStream()));
	}

	@GetMapping(path = "/document_vehicule/{idVehicule}")
	public ResponseEntity<List<DocumentTableDataResponse>> getListDocumentByVehicule(@PathVariable Long idVehicule) 
	{
		 ModelMapper modelMapper = new ModelMapper();
		 List<DocumentTableDataResponse> documentTableDataResponses = new ArrayList<>();
		 List<DocumentTableDataDTO> documentTableDataDTOS = administratif.getListDocumentByVehicule(idVehicule);
		 if (!documentTableDataDTOS.isEmpty()) 
		 {
			  documentTableDataDTOS.forEach(documentTableDataDTO -> {
			  documentTableDataResponses.add(modelMapper.map(documentTableDataDTO, DocumentTableDataResponse.class));});
		 }
		 return new ResponseEntity<>(documentTableDataResponses, HttpStatus.OK);
	}

	@DeleteMapping(path = "/document_vehicule/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedDocument(@PathVariable Long id) 
	{
		 administratif.deleteSelectedDocument(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/pagination_taxe_circulation_custom_searching/{page}/{limit}")
	public ResponseEntity<List<TaxeCirculationTableDataResponse>> getPaginationTaxeCirculationList(@RequestBody CustomSearchingTaxeCirculationRequest customSearchingTaxeCirculationRequest,@PathVariable int page, @PathVariable int limit) 
	{
		 List<TaxeCirculationTableDataResponse> taxeCirculationTableDataResponses = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
		 CustomSearchingTaxeCirculationDTO customSearchingTaxeCirculationDTO = modelMapper.map(customSearchingTaxeCirculationRequest, CustomSearchingTaxeCirculationDTO.class);
		 return new ResponseEntity<>(taxeCirculationTableDataResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/user/{id}")
	public Optional<UserEntity> getlistuserbyid(@PathVariable Long id) 
	{
		 return userRepository.findById(id);
	}
	
	
	/**********correction**********/
	
	
		@PutMapping(path = "/postFileContent")
		public void updatePrices(@RequestBody List<PricesRequests> pricesRequests) {
			String y;
	
			for (Iterator iterator = pricesRequests.iterator(); iterator.hasNext();) {
				PricesRequests pricesRequests2 = (PricesRequests) iterator.next();
				PricesEntity price = new PricesEntity();
	
				System.out.println(pricesRequests2);
				String[] parts = pricesRequests2.getDate().split("-");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				System.out.println("length part 2");
				System.out.println(part2.length());
			if (part2.length() < 2) {
					part2 = '0' + part2;
				}
				if (part3.length() < 2) {
					part3 = '0' + part3;
				}
				y = part1 + '-' + part2 + '-' + part3;
				System.out.println(y);
				price.setDate(y);
				price.setEssence(pricesRequests2.getEssence());
				price.setGazoil(pricesRequests2.getGazoil());
				price.setGazoilsanssoufre(pricesRequests2.getGazoilsanssoufre());
				pricesRepository.save(price);
				List<AgilisFileData> agilis = agilisFileDataRepository.findByTransacDate(y);
			System.out.println("hello");
				for (Iterator iterator2 = agilis.iterator(); iterator2.hasNext();) {
					System.out.println("hello 2");
					AgilisFileData agilisFileData = (AgilisFileData) iterator2.next();
					System.out.println("La consommation !!");
					// System.out.println(agilisFileData.setPrixttc());
					if (agilisFileData.getProduits() != null) {
						if (agilisFileData.getProduits().equals("Gasoil")) {
							agilisFileData.setPrixttc(pricesRequests2.getGazoil());
							agilisFileData.setRavitaillement(agilisFileData.getQte()/pricesRequests2.getGazoil());
							System.out.println("ravitaillement");
							System.out.println(agilisFileData.getQte()/pricesRequests2.getGazoil());
									
						} else if (agilisFileData.getProduits().equals("Gasoil sans soufre")) {
							agilisFileData.setPrixttc(pricesRequests2.getGazoilsanssoufre());
							agilisFileData.setRavitaillement(agilisFileData.getQte()/pricesRequests2.getGazoilsanssoufre());
							System.out.println("ravitaillement");
							System.out.println(agilisFileData.getQte()/pricesRequests2.getGazoil());
	
	
						} else if (agilisFileData.getProduits().equals("Super SP")) {
							agilisFileData.setPrixttc(pricesRequests2.getEssence());
	
							agilisFileData.setRavitaillement(agilisFileData.getQte()/pricesRequests2.getGazoilsanssoufre());
							System.out.println("ravitaillement");
							System.out.println(agilisFileData.getQte()/pricesRequests2.getEssence());
	
						}
					}
					agilisFileDataRepository.save(agilisFileData);
	
				}
			}
		}
	
		@PostMapping(path = "/postFileContent")
		public void insertExcelData(@RequestBody List<AgilisFileDataRequest> agilisFileDataRequest) {
			String y;
	
			int d;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH);
			int day = Calendar.getInstance().get(Calendar.DATE);
		System.out.println("le jr");
		System.out.println(day);
			System.out.println("l'annee");
			System.out.println(year);
			System.out.println("le mois");
			System.out.println(month + 1);
			String yr = Integer.toString(year);
			String mth = Integer.toString(month + 1);
			if (mth.length() < 2) {
				mth = '0' + mth;
			}
	
			String dd = mth + '-' + yr;
	
		AgilisFileData ag = agilisFileDataRepository.findByIdFile(dd);
	
	
			System.out.println("d=");
			System.out.println(dd);
			if (ag == null) {
				for (Iterator iterator = agilisFileDataRequest.iterator(); iterator.hasNext();) {
					AgilisFileData agilisFileData = new AgilisFileData();
				AgilisFileDataRequest agilisFileDataRequest2 = (AgilisFileDataRequest) iterator.next();
					if (agilisFileDataRequest2.getTransac_num_carte() != null) {
						System.out.println(agilisFileDataRequest2.getTransac_num_carte());
						CarteAgilisCashEntity carteAgilis = carteAgilisCashRepository
								.findByNumeroCarte(agilisFileDataRequest2.getTransac_num_carte());
						System.out.println(agilisFileDataRequest2.getTransac_num_carte());
						CartePlafondEntity cartePlafond = cartePlafondRepository
								.findByNumeroCarte(agilisFileDataRequest2.getTransac_num_carte());
						if (carteAgilis != null && carteAgilis.getVehicule() != null) {
							System.out.println("le num de la carte agilis !!");
							System.out.println(carteAgilis.getVehicule().getBeneficiaire().getNom());
						}
						if (cartePlafond != null && cartePlafond.getVehicule() != null) {
							System.out.println("le num de la carte plafond !!");
							System.out.println(cartePlafond.getVehicule().getBeneficiaire().getNom());
						}
					}
					agilisFileData.setStation(agilisFileDataRequest2.getStation());
					agilisFileData.setIdFile(dd);
					agilisFileData.setCrt_Porteur_Perso(agilisFileDataRequest2.getCrt_Porteur_Perso());
					agilisFileData.setMontant(agilisFileDataRequest2.getMontant());
					agilisFileData.setProduits(agilisFileDataRequest2.getProduits());
					agilisFileData.setQte(agilisFileDataRequest2.getQte());
					agilisFileData.setType_transaction(agilisFileDataRequest2.getType_transaction());
					String[] parts = agilisFileDataRequest2.getTransac_date().split("-");
					String part1 = parts[0];
					String part2 = parts[1];
					String part3 = parts[2];
					if (part2.length() < 2) {
						part2 = '0' + part2;
					}
					if (part3.length() < 2) {
						part3 = '0' + part3;
					}
					y = part1 + '-' + part2 + '-' + part3;
					agilisFileData.setTransacDate(y);
					agilisFileData.setTransac_kilometrage(agilisFileDataRequest2.getTransac_kilometrage());
					agilisFileData.setTransac_num_carte(agilisFileDataRequest2.getTransac_num_carte());
					agilisFileData.setTransac_num_carte_transfert(agilisFileDataRequest2.getTransac_num_carte_transfert());
					agilisFileData.setTransac_num_ticket(agilisFileDataRequest2.getTransac_num_ticket());
					agilisFileData
							.setTransac_num_ticket_annulation(agilisFileDataRequest2.getTransac_num_ticket_annulation());
					agilisFileData.setTyp(agilisFileDataRequest2.getTyp());
					
					if (agilisFileDataRequest2.getType_transaction().equals("Ann Achat Ps")) {
						agilisFileData.setFacturation(-1.00);
	
					}
	
					if (agilisFileDataRequest2.getTyp().equals("744") || agilisFileDataRequest2.getTyp().equals("741")) {
						agilisFileData.setFacturation(agilisFileDataRequest2.getMontant());
					}
					// System.out.println(agilisFileDataRequest2.getType_transaction().equals("Ann
					// Achat Ps"));
				
					agilisFileData.setPrixttc(0);
					agilisFileDataRepository.save(agilisFileData);
				}
			}
		}
	
	
		@GetMapping(path = "/total_item_dataExcel")
		public ResponseEntity<Long> getTotalItemDataList() {
			Long totalItem = administratif.getTotalItemDataList();
			return new ResponseEntity<>(totalItem, HttpStatus.OK);
	
		}
	
		@GetMapping(path = "/pagination_agilis_data")
		List<AgilisFileDataResponse> getPaginationListAgilis(@RequestParam(value = "page") String page,
				@RequestParam(value = "limit") String limit, @RequestParam(value = "idFile") String idFile) {
			ModelMapper modelMapper = new ModelMapper();
			List<AgilisFileDataResponse> agilisFileDataResponse = new ArrayList<>();
	
			List<AgilisFileDataTableDTO> agilisFileDataTableDTOS = administratif.getPaginationDonneeExcelList(idFile,
					Integer.parseInt(page), Integer.parseInt(limit));
			if (!agilisFileDataTableDTOS.isEmpty()) {
				agilisFileDataTableDTOS.forEach(agilisFileDataTableDTO -> {
					agilisFileDataResponse.add(modelMapper.map(agilisFileDataTableDTO, AgilisFileDataResponse.class));
				});
			}
			return agilisFileDataResponse;
		}
	
		
		@GetMapping(path = "/pagination_agilis_prices")
		List<AgilisPriceResponse> getPaginationAgilisPrices(@RequestParam(value = "page") String page,
				@RequestParam(value = "limit") String limit) {
			ModelMapper modelMapper = new ModelMapper();
			List<AgilisPriceResponse> agilisPriceResponse = new ArrayList<>();
	
			List<AgilisPriceDataTableDTO> agilisPriceDataTableDTOS = administratif.getPaginationPriceList(
					Integer.parseInt(page), Integer.parseInt(limit));
			if (!agilisPriceDataTableDTOS.isEmpty()) {
				agilisPriceDataTableDTOS.forEach(agilisFileDataTableDTO -> {
					agilisPriceResponse.add(modelMapper.map(agilisFileDataTableDTO, AgilisPriceResponse.class));
				});
			}
			return agilisPriceResponse;
		}
	
		
		
		
		@PutMapping(path = "/consommation")
		public void consommationCarburant(@RequestBody AgilisFileDataRequest agilisFileDataRequest) {
	      
				
			
			AgilisFileData agilis = agilisFileDataRepository.findById(agilisFileDataRequest.getId()).get();
			if (agilis!=null) {
				agilis.setIndex_fin_mois(agilisFileDataRequest.getIndex_fin_mois());
				agilis.setIndex_avant_fin_mois(agilisFileDataRequest.getIndex_avant_fin_mois());
				agilis.setQte_reservoir_avant(agilisFileDataRequest.getQte_reservoir_avant());
				agilis.setQte_reservoir(agilisFileDataRequest.getQte_reservoir());
				double consomm , cons1 , resaAv , resCour ,Ravi ;
				resaAv = agilis.getQte_reservoir_avant();
				resCour=agilis.getQte_reservoir();
				Ravi = agilis.getRavitaillement();
				consomm=(agilis.getQte()*resaAv)+(agilis.getQte_reservoir()-Ravi) ;
				System.out.println("consomm");
				System.out.println(consomm);
				agilis.setConsommation(consomm);
				agilisFileDataRepository.save(agilis);        }
	
		}
		
		@GetMapping(path = "/total_item_Prices")
		public ResponseEntity<Long> getTotalItemPricesList() {
			Long totalItem = administratif.getTotalItemPricesList();
			return new ResponseEntity<>(totalItem, HttpStatus.OK);
	
		}
	

@GetMapping(path = "/listFinVisite")
ResponseEntity<List<VisiteTechniqueEntity>> getPaginationTransfertRotationNullList(
	@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
	ModelMapper modelMapper = new ModelMapper();

	List<VisiteTechniqueEntity> magasinRotationNullResponse = new ArrayList<>();
	List<VisiteTechniqueTableDataDTO> magasinRotationNullResponseTabDataDTOS = administratif
			.getPaginationVisiteTechniqueFinList(Integer.parseInt(page), Integer.parseInt(limit));

	if (!magasinRotationNullResponseTabDataDTOS.isEmpty()) {
		magasinRotationNullResponseTabDataDTOS.forEach(demandeRechargeSousCompteDTO -> {
			magasinRotationNullResponse
					.add(modelMapper.map(demandeRechargeSousCompteDTO, VisiteTechniqueEntity.class));
		});
	}
	return new ResponseEntity<>(magasinRotationNullResponse, HttpStatus.OK);
}

@GetMapping("/liste_fin_visite")
public Iterable<VisiteTechniqueEntity> list() {
	return administratif.VisiteTechniqueFinList();
}

@GetMapping("/nombre_fin_visite")
public int Nombre() {
	List<VisiteTechniqueEntity> v= administratif.VisiteTechniqueFinList();
	int nbv =v.size();
	return nbv;
}
}
/*******fin de la correction******/

