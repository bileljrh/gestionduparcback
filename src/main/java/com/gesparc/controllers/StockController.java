package com.gesparc.controllers;


import com.gesparc.entities.UserExportFile;
import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.entities.stock.HistoriqueRegulation;
import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.MagasinRotationNull;
import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;
import com.gesparc.entities.stock.MagasinVirtuelEntity;
import com.gesparc.entities.stock.ParcTransfertArticleEntity;
import com.gesparc.entities.stock.ParcTransfertEntity;

import com.gesparc.repositories.maintenance.BonTravailArticleRepository;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.repositories.referentiel.StructureRepository;
import com.gesparc.repositories.referentiel.UgpRepository;
import com.gesparc.repositories.stock.HistoriqueRegulationRepository;
import com.gesparc.repositories.stock.InventaireStockRepository;
import com.gesparc.repositories.stock.MagasinVirtuelArticleReposiroty;
import com.gesparc.repositories.stock.MagasinVirtuelRepository;
import com.gesparc.repositories.stock.ParcTransfertRepository;
import com.gesparc.repositories.stock.RegulationArticleRepository;
import com.gesparc.repositories.stock.UgpArticleRepository;
import com.gesparc.entities.carburant.RechargeSousCompte;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.RetourStructure;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.requests.Achat.additional.UpdateBonCommandeRequest;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.requests.Carburant.ModifyDemandeQuotaCarteJockerRequest;
import com.gesparc.requests.stock.MagasinVirtuelRequest;
import com.gesparc.requests.stock.UpdateParcTransfertRequest;
import com.gesparc.requests.stock.additionnel.UpdateMagasinVirtuelRequest;
import com.gesparc.requests.stock.additionnel.UpdateReguclationRequest;
import com.gesparc.responses.DeclarationPerteCarteJockerResponse;
import com.gesparc.responses.Achat.BonCommandeResponse;
import com.gesparc.responses.maintenance.BonTravailArticleResponse;
import com.gesparc.responses.maintenance.BonTravailResponse;
import com.gesparc.responses.maintenance.additional.BonTravailDataTableResponse;
import com.gesparc.responses.stock.MagasinVirtuelDataTableResponse;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.StockImpl;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModifyDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailArticleDTO;
import com.gesparc.sharedDTO.referentiel.UgpDTO;
import com.gesparc.sharedDTO.stock.HistoriqueRegulationDTO;
import com.gesparc.sharedDTO.stock.ListInventaireDTO;
import com.gesparc.sharedDTO.stock.ListInventaireStockDTO;
import com.gesparc.sharedDTO.stock.ListRegulationArticleDTO;
import com.gesparc.sharedDTO.stock.ListRetourDTO;
import com.gesparc.sharedDTO.stock.MagasinRotationNullDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.NewInventaireStockDTO;
import com.gesparc.sharedDTO.stock.NewRegulationDTO;
import com.gesparc.sharedDTO.stock.NewRetourDTO;
import com.gesparc.sharedDTO.stock.ParcTransfertDTO;
import com.gesparc.sharedDTO.stock.UpdateParcTransfertDTO;
import com.gesparc.sharedDTO.stock.additionnel.MagasinVirtuelDataTableDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;

import org.apache.commons.compress.utils.IOUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.maintenance.BonTravailArticleExterneEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;
import com.gesparc.entities.stock.MagasinVirtuelEntity;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.RetourStructure;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.repositories.referentiel.StructureRepository;
import com.gesparc.repositories.referentiel.UgpRepository;
import com.gesparc.repositories.stock.InventaireStockRepository;
import com.gesparc.repositories.stock.MagasinVirtuelArticleReposiroty;
import com.gesparc.repositories.stock.MagasinVirtuelRepository;
import com.gesparc.repositories.stock.RegulationArticleRepository;
import com.gesparc.requests.Achat.additional.UpdateBonCommandeRequest;
import com.gesparc.requests.Carburant.ModifyDemandeQuotaCarteJockerRequest;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.requests.stock.MagasinVirtuelRequest;
import com.gesparc.requests.stock.additionnel.UpdateMagasinVirtuelRequest;
import com.gesparc.responses.DeclarationPerteCarteJockerResponse;
import com.gesparc.responses.Achat.BonCommandeResponse;
import com.gesparc.responses.maintenance.additional.BonTravailDataTableResponse;
import com.gesparc.responses.stock.MagasinVirtuelDataTableResponse;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.StockImpl;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModifyDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.stock.ListInventaireStockDTO;
import com.gesparc.sharedDTO.stock.ListRegulationArticleDTO;
import com.gesparc.sharedDTO.stock.ListRetourDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.NewInventaireStockDTO;
import com.gesparc.sharedDTO.stock.NewRegulationDTO;
import com.gesparc.sharedDTO.stock.NewRetourDTO;
import com.gesparc.sharedDTO.stock.additionnel.MagasinVirtuelDataTableDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateRegulationDTO;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class StockController {

	@Autowired
	StockImpl stock;

	Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private ParcTransfertRepository parcReposiroty;

	@Autowired
	private UgpArticleRepository ugpArticleReposiroty;
	
	@Autowired
	private MagasinVirtuelArticleReposiroty magasinVirtuelArticleReposiroty;

	@Autowired
	private MagasinVirtuelRepository magasinVirtuelRepository;

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	ArticleRepository article;

	@Autowired
	private UgpRepository ugpRepository;

	@Autowired
	private RegulationArticleRepository regulationarticleRepository;
	
	@Autowired
	HistoriqueRegulationRepository historiqueRepository ;

	@Autowired
	private StructureRepository structureRepository;

	@GetMapping(path = "/pagination_magasin_virtuel")
	List<MagasinVirtuelDataTableResponse> getPaginationMagasinVirtuel(@RequestParam(value = "ugp") String ugp,
			@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<MagasinVirtuelDataTableResponse> magasinVirtuelDataTableResponse = new ArrayList<>();
		List<MagasinVirtuelDataTableDTO> magasinVirtuelDataTableDTOS = stock.getPaginationMagasinVirtuelList(ugp,
				Integer.parseInt(page), Integer.parseInt(limit));
		if (!magasinVirtuelDataTableDTOS.isEmpty()) {
			magasinVirtuelDataTableDTOS.forEach(magasinVirtuelDataTableDTO -> {
				magasinVirtuelDataTableResponse
						.add(modelMapper.map(magasinVirtuelDataTableDTO, MagasinVirtuelDataTableResponse.class));
			});
		}
		return magasinVirtuelDataTableResponse;
	}

	@GetMapping(path = "/magasin-virtuel-articles/{id}")
	List<MagasinVirtuelArticleEntity> showMagasinVirtuelById(@PathVariable Long id) {
		return magasinVirtuelArticleReposiroty.getAllMVA(id);
	}

	@GetMapping(path = "/pagination_reception_fournisseur")
	ResponseEntity<List<BonCommandeResponse>> getPaginationReceptionFournisseurListByStatus(
			@RequestParam(name = "status") String status, @RequestParam(name = "page") String page,
			@RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<BonCommandeResponse> bonCommandeResponses = new ArrayList<>();
		List<BonCommandeDTO> bonCommandeDTOS = stock.getPaginationReceptionFournisseurListByStatus(status,
				Integer.parseInt(page), Integer.parseInt(limit));
		if (!bonCommandeDTOS.isEmpty()) {
			bonCommandeDTOS.forEach(bonCommandeDTO -> {
				bonCommandeResponses.add(modelMapper.map(bonCommandeDTO, BonCommandeResponse.class));
			});
		}
		return new ResponseEntity<>(bonCommandeResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/liste-articles-a-transferer/{id}")
	private List<MagasinVirtuelArticleEntity> getOneDemande(@PathVariable Long id) {
		return magasinVirtuelArticleReposiroty.getAllMVA(id);
	}

//    @GetMapping(path = "/total_item_reception_fournisseur")
	ResponseEntity<Long> getTotalItemsReceptionFournisseurByStatus(@RequestParam(name = "status") String status) {
		Long totalItem = stock.getTotalItemsReceptionFournisseurByStatus(status);
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@PostMapping(path = "/reception_fournisseur")
	ResponseEntity<HttpStatus> confirmReceptionFournisseur(@RequestBody BonCommandeResponse bonCommandeResponse) {
		ModelMapper modelMapper = new ModelMapper();
		BonCommandeDTO bonCommandeDTO = modelMapper.map(bonCommandeResponse, BonCommandeDTO.class);
		stock.confirmReceptionFournisseur(bonCommandeDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/transfert_article")
	ResponseEntity<HttpStatus> addNewAriclesMV(@RequestBody UpdateMagasinVirtuelRequest updateMagasinVirtuelRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UpdateMagasinVirtuelDTO updateMagasinVirtuelDTO = modelMapper.map(updateMagasinVirtuelRequest,
				UpdateMagasinVirtuelDTO.class);
		stock.addNewAriclesMV(updateMagasinVirtuelDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/confirmer_virtual_shop")
	public ResponseEntity<HttpStatus> confirmStock(@RequestBody Long id) {
		stock.confirmTransfert(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/validate_virtual_shop")
	public void validateStock(@RequestBody Long id) {
		MagasinVirtuelEntity magasinVirtuel = magasinVirtuelRepository.findById(id).get();
		List<MagasinVirtuelArticleEntity> mvArticle = magasinVirtuelArticleReposiroty.getAllMVA(id);
		if (magasinVirtuel.getConfirmed() == true) {
			for (int i = 0; i < mvArticle.size(); i++) {
				ArticleEntity articleEntity = articleRepository.findById(mvArticle.get(i).getArticle().getId()).get();
				articleEntity.setQuantiteStock(articleEntity.getQuantiteStock() - mvArticle.get(i).getQteTransferer());
				magasinVirtuel.setValidated(true);
				magasinVirtuelRepository.save(magasinVirtuel);
				System.out.println(magasinVirtuel);
				articleRepository.save(articleEntity);
			}
		}
	}

	@PutMapping(path = "/modify_demande_magasin_virtuel")
	public ResponseEntity<HttpStatus> updateDemandeMagasinVirtuel(
			@RequestBody UpdateMagasinVirtuelRequest updateMagasinVirtuelRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UpdateMagasinVirtuelDTO updateMagasinVirtuelDTO = modelMapper.map(updateMagasinVirtuelRequest,
				UpdateMagasinVirtuelDTO.class);
		stock.modifySelectedDemandeMagasinVirtuel(updateMagasinVirtuelDTO);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping(path = "/delete_demande_magasin_virtuel/{id}")
	public ResponseEntity<HttpStatus> deleteDemandeMV(@PathVariable("id") Long id) {
		stock.deleteSelectedMV(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/creation_retour_structure")
	ResponseEntity<HttpStatus> addNewRetourStructure(@RequestBody NewRetourDTO retourStructure) {
		stock.addNewRetourStructure(retourStructure);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping(path = "/pagination_retour_structure")
	ResponseEntity<List<RetourStructure>> getPaginationRetourStructureList(
			@RequestParam(name = "magasin") String magasin, @RequestParam(name = "ugp") String ugp,
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RetourStructure> RetourStructureListResponse = new ArrayList<>();
		List<ListRetourDTO> retourTabDataDTOS = stock.getPaginationRetourList(magasin, ugp, Integer.parseInt(page),
				Integer.parseInt(limit));

		if (!retourTabDataDTOS.isEmpty()) {
			retourTabDataDTOS.forEach(retourTabDataDTO -> {
				RetourStructureListResponse.add(modelMapper.map(retourTabDataDTO, RetourStructure.class));
			});
		}
		return new ResponseEntity<>(RetourStructureListResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/total_items_retour")

	Long getTotalItemsRetourList() {

		return stock.getTotalItemsRetourList();

	}

	@GetMapping(path = "/list_Structure")
	public Iterable<StructureEntity> listStructure() {
		return structureRepository.findAll();
	}

	@DeleteMapping(path = "/delete_retour_structure/{id}")
	public ResponseEntity<HttpStatus> deleteRetourStructure(@PathVariable("id") Long id) {
		stock.deleteSelectedRetourArticle(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/list_regulation_article")
	public Iterable<RegulationArticleStock> getAllRegulationArticles() {
		return stock.getAllRegulationArticles();
	}

	@GetMapping(path = "/list_regulation_article_magasin")
	public List<RegulationArticleStock> getRegulationParMagasin() {
		return regulationarticleRepository.getRegulationMagasin();
	}

	@GetMapping(path = "/total_number_regulation_article")
	ResponseEntity<Long> getTotalNumberRegulationArticle() {
		Long totalNumber = stock.getTotalNumberRegulationArticle();
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}
	
	@GetMapping(path = "/pagination_regulation_article")
	ResponseEntity<List<RegulationArticleStock>> getPaginationRegulationArticleList(
			@RequestParam(name = "ugp") String ugp,
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<RegulationArticleStock> RegulationArticleListResponse = new ArrayList<>();
		List<ListRegulationArticleDTO> regulationArticleTabDataDTOS = stock.getPaginationRegulationArticleList( ugp, Integer.parseInt(page),
				Integer.parseInt(limit));

		if (!regulationArticleTabDataDTOS.isEmpty()) {
			regulationArticleTabDataDTOS.forEach(retourTabDataDTO -> {
				RegulationArticleListResponse.add(modelMapper.map(retourTabDataDTO, RegulationArticleStock.class));
			});
		}
		return new ResponseEntity<>(RegulationArticleListResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/total_items_regulation")

	Long getTotalItemsRegulationStockList() {

		return stock.getTotalItemsRegulationStockList();

	}

	@GetMapping(path = "/list_regulation_article/{id}")
	public RegulationArticleStock getRegulationArticleById(@PathVariable("id") Long id) {
		return stock.getRegulationArticle(id);
	}

	@PostMapping(path = "/creation_regulation_article")
	ResponseEntity<HttpStatus> addNewRegulationArticle(@RequestBody NewRegulationDTO regulationArticle) {
		stock.addNewRegulationArticle(regulationArticle);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
    @PutMapping(path = "/regulation_modify")
	void  modifyRegulation(@RequestBody UpdateReguclationRequest updateReguclationRequest) {
    ModelMapper modelMapper = new ModelMapper();
    UpdateRegulationDTO updateRegulationDTO = modelMapper.map(updateReguclationRequest, UpdateRegulationDTO.class);

    stock.modifySelectedRegulation(updateRegulationDTO);

}

	@DeleteMapping(path = "/delete_regulation_article/{id}")
	public ResponseEntity<HttpStatus> deleteRegulationArticle(@PathVariable("id") Long id) {
		stock.deleteSelectedRegulationArticle(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/Liste_Ugp")
	public Iterable<UgpEntity> ugpList() {
		return ugpRepository.findAll();
	}
	
	//=============transfert du parc vers parc=============
	@PostMapping(path = "/transfert_article_ugp")
	ResponseEntity<HttpStatus> addNewAriclesUgp(@RequestBody UpdateParcTransfertRequest updateParcTransfertRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UpdateParcTransfertDTO updateParcTransfertDTO = modelMapper.map(updateParcTransfertRequest,
				UpdateParcTransfertDTO.class);
		stock.addNewAriclesUgp(updateParcTransfertDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_transfert_parc")
	List<ParcTransfertEntity> getPaginationTransfertParc(
			@RequestParam(value = "ugp") String ugp,
			@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<ParcTransfertEntity> parcEntity = new ArrayList<>();
		List<ParcTransfertDTO> parcDTOS = stock.getPaginationUgpList(ugp,Integer.parseInt(page), Integer.parseInt(limit));
		if (!parcDTOS.isEmpty()) {
			parcDTOS.forEach(parcDTO -> {
				parcEntity.add(modelMapper.map(parcDTO, ParcTransfertEntity.class));
			});
		}
		return parcEntity;
	}

	@DeleteMapping(path = "/delete_transfert_parc/{id}")
	public ResponseEntity<HttpStatus> deleteDemandeTransfertParc(@PathVariable("id") Long id) {
		stock.deleteSelectedParcTransfert(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/confirmer_parc_transfert")
	public ResponseEntity<HttpStatus> confirmParcTransfert(@RequestBody Long id) {
		stock.confirmParcTransfert(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/valider_parc_transfert")
	public ResponseEntity<HttpStatus> validParcTransfert(@RequestBody Long id) {
		stock.validParcTransfert(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping(path = "/total_item_parc_transfert")
	public ResponseEntity<Long> getTotalItemParc_transfertList() {
		Long totalItem = stock.getTotalItemParcTransfertList();
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}
	
		
	
	
	@PutMapping(path = "/modify_demande_transfert_parc_vers_magasin")
	public ResponseEntity<HttpStatus> updateDemandeParcTransfert(
			@RequestBody UpdateParcTransfertRequest updateParcTransfertRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UpdateParcTransfertDTO updateParcTransfertDTO = modelMapper.map(updateParcTransfertRequest,
				UpdateParcTransfertDTO.class);
		stock.modifySelectedDemandeTransfertParc(updateParcTransfertDTO);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	

	
//=======================================================================================================
	
	@GetMapping(path = "/pagination_historique_regulation")
	ResponseEntity<List<HistoriqueRegulation>> getPaginationHistoriqueRegulationList(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit)
	{
	ModelMapper modelMapper = new ModelMapper();
	
	List<HistoriqueRegulation> historiqueRegulationListResponse = new ArrayList<>();
	
	List<HistoriqueRegulation> historiqueRegulationDTOS = stock.getPaginationHistoriqueRegulationList(Integer.parseInt(page), Integer.parseInt(limit));
	
	if (!historiqueRegulationDTOS.isEmpty())
	{
		System.out.println("\n test 1\n");
		historiqueRegulationDTOS.forEach(HistoriqueRegulation -> {
		historiqueRegulationListResponse.add(modelMapper.map(HistoriqueRegulation, HistoriqueRegulation.class));
	});
	}
	System.out.println(historiqueRegulationListResponse);
	System.out.println("test2");

	return new ResponseEntity<>(historiqueRegulationListResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete_historique_regulation/{id}")
	public ResponseEntity<HttpStatus> deleteHistoriqueRegulation(@PathVariable("id") Long id) {
		stock.deleteSelectedHistoriqueRegulation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/article_inventaire")
	List<ArticleEntity> getPaginationArticleInventaire(@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleEntity> parcEntity = new ArrayList<>();
		List<ArticleEntity> parcDTOS = stock.getPaginationArticleList(Integer.parseInt(page), Integer.parseInt(limit));
		if (!parcDTOS.isEmpty()) {
			parcDTOS.forEach(parcDTO -> {
				parcEntity.add(modelMapper.map(parcDTO, ArticleEntity.class));
			});
		}
		return parcEntity;
	}

	
	//Rotation null :
	@GetMapping(path = "/pagination_rotation_null")
	ResponseEntity<List<MagasinRotationNull>> getPaginationTransfertRotationNullList(
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();

		List<MagasinRotationNull> magasinRotationNullResponse = new ArrayList<>();
		List<MagasinRotationNullDTO> magasinRotationNullResponseTabDataDTOS = stock
				.getPaginationMagasinRotationNullList(Integer.parseInt(page), Integer.parseInt(limit));

		if (!magasinRotationNullResponseTabDataDTOS.isEmpty()) {
			magasinRotationNullResponseTabDataDTOS.forEach(demandeRechargeSousCompteDTO -> {
				magasinRotationNullResponse
						.add(modelMapper.map(demandeRechargeSousCompteDTO, MagasinRotationNull.class));
			});
		}
		return new ResponseEntity<>(magasinRotationNullResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete_rotation_null/{id}")
	public void deleteMagasinRotationNull(@PathVariable Long id) {
		stock.deleteMagasinRotationNull(id);
	}

	@PostMapping(path = "/confirm_rotation_null")
	public ResponseEntity<HttpStatus> confirmSelectedMagasinRotationNull(@RequestBody Long id) {
		stock.confirmSelectedMagasinRotationNull(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/valide_rotation_null")
	public ResponseEntity<HttpStatus> validateSelectedMagasinRotationNull(@RequestBody Long id) {
		stock.validateSelectedMagasinRotationNull(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/creation_rotation_null")
	ResponseEntity<HttpStatus> createMagasinRotationNull(
			@RequestBody MagasinRotationNull magasinRotationNull) {
		System.out.print("test");

		System.out.print(magasinRotationNull.getCodeArticle());
		stock.addNewMagasinRotationNull(magasinRotationNull);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*@PostMapping(path = "/Re_Transfert_rotation_null")
	public ResponseEntity<HttpStatus> ReTransfertSelectedMagasinRotationNull(@RequestBody MagasinRotationNull magasinRotationNull) {
		
		stock.ReTransfertSelectedMagasinRotationNull(magasinRotationNull);
		return new ResponseEntity<>(HttpStatus.OK);
	}*/
	

	@DeleteMapping(path = "/Re_Transfert_rotation_null/{id}")
	public void ReTransfertSelectedMagasinRotationNull(@PathVariable Long id) {
		stock.deleteMagasinRotationNull(id);
	}

	@GetMapping("/telechager")
	public void DownloadEncelFichier(HttpServletResponse reponse) throws IOException
	{
	   reponse.setContentType("application/octet-stream");
	   String headerKey="Content-Disposqition";
	   String headervalue= "attachment ;filename=test.xlsx";
	   
       reponse.setHeader(headerKey, headervalue);
       
       
       List<ArticleEntity> listArticle= (List<ArticleEntity>) articleRepository.findAll();
       System.out.print(" \n test1 \n");

       UserExportFile exp = new UserExportFile(listArticle);
       System.out.print(" \n test2 \n");
       exp.export(reponse);

	}

	
	@GetMapping(path = "/listArticle")
	List<ListInventaireDTO> listArticle() {
		ModelMapper modelMapper = new ModelMapper();

		List<ListInventaireDTO> magasinRotationNullResponse = new ArrayList<>();
		List<ListInventaireDTO> magasinRotationNullResponseTabDataDTOS = stock
				.geTInventaire();

		if (!magasinRotationNullResponseTabDataDTOS.isEmpty()) {
			magasinRotationNullResponseTabDataDTOS.forEach(demandeRechargeSousCompteDTO -> {
				magasinRotationNullResponse
						.add(modelMapper.map(demandeRechargeSousCompteDTO, ListInventaireDTO.class));
			});
		}
		return magasinRotationNullResponse;
	}
}
