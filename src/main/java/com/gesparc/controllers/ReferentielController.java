package com.gesparc.controllers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.cartePlafond.RechargeQuotaMensuelEntity;
import com.gesparc.entities.exploitation.SinistreVehiculeEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.ArticlePricesEntity;
import com.gesparc.entities.referentiel.AtelierEntity;
import com.gesparc.entities.referentiel.FamilleArticleEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.GenreVehiculeEntity;
import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.entities.referentiel.MarqueVehiculeEntity;
import com.gesparc.entities.referentiel.SousFamilleArticleEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.TypeVehiculeEntity;
import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.exploitation.SinistreVehiculeRepository;
import com.gesparc.repositories.referentiel.*;
import com.gesparc.requests.Carburant.ModifyDemandeQuotaCarteJockerRequest;
import com.gesparc.requests.referentiel.*;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.requests.stock.additionnel.UpdateMagasinVirtuelRequest;
import com.gesparc.responses.Achat.MarcheResponse;
import com.gesparc.responses.carburant.additionnel.CarteAgilisCashTabDataResponse;
import com.gesparc.responses.maintenance.additional.BonTravailDataTableResponse;
import com.gesparc.responses.referentiel.*;
import com.gesparc.responses.referentiel.additionnel.ArticleResponseDataTable;
import com.gesparc.responses.referentiel.additionnel.FamilleArticleResponseDataTable;
import com.gesparc.responses.referentiel.additionnel.ListFamilleAndSousFamilleResponse;
import com.gesparc.responses.referentiel.additionnel.ListTypeMarqueResponse;
import com.gesparc.responses.stock.MagasinVirtuelDataTableResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.ReferentielImpl;
import com.gesparc.sharedDTO.Achat.MarcheDTO;
import com.gesparc.sharedDTO.carburant.ModifyDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.RechargeQuotaMensuelDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CarteAgilisCashTabDataDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.referentiel.*;
import com.gesparc.sharedDTO.referentiel.additionnel.ListFamilleAndSousFamilleDTO;
import com.gesparc.sharedDTO.stock.additionnel.MagasinVirtuelDataTableDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
@ComponentScan()

public class ReferentielController {

	Logger logger = LoggerFactory.getLogger(ReferentielController.class);

	private UserRepository userRepository;
	
	

	private ArticleRepository articleRepository;

	private FamilleArticleRepository familleArticleRepository;

	private SousFamilleArticleRepository sousFamilleArticleRepository;

	private GouvernoratRepository gouvernoratRepository;

	private StationPeageReposiroty stationPeageReposiroty;

	private ExpertRepository expertRepository;

	private LieuParkingRepository lieuParkingRepository;

	private BeneficiaireEmpruntRepository beneficiaireEmpruntRepository;

	private EtatStockRepository etatStockRepository;

	private PersonnelRepository personnelRepository;

	private StructureRepository structureRepository;

	private MagasinRepository magasinRepository;

	private AtelierRepository atelierRepository;

	private RessourceRepository ressourceRepository;

	private SectionRepository sectionRepository;

	private UgpRepository ugpRepository;

	private EnergieRepository energieRepository;

	private AnneeRepository anneeRepository;

	private ProgrammeEntretiensPreventifsRepository programmeEntretiensPreventifsRepository;

	private TvaRepository tvaRepository;

	private UniteRepository uniteRepository;

	private FournisseurRepository fournisseurRepository;

	private FamilleOperationReparationRepository familleOperationReparationRepository;

	private OperationReparationRepository operationReparationRepository;

	private TypeVehiculeRepository typeVehiculeRepository;

	private MarqueVehiculeRepository marqueVehiculeRepository;

	private GenreVehiculeRepository genreVehiculeRepository;

	private CauseSinistreRepository causeSinistreRepository;

	private UsageVehiculeRepository usageVehiculeRepository;

	private ReferentielImpl referentiel;

	private TracabiliteController tracabiliteController;
	
	@Autowired
	private SinistreVehiculeRepository sinistreVehiculeRepository;

	@Autowired
	private VehiculeRepository vehiculeRepository;
	
	@Autowired
	private ArticlePricesRepository articlePricesRepository;
	
	@Autowired
	private SousFamilleArticleRepository SousFamilleArticleRepository;
	
	@Autowired
	ReferentielImpl article;

	public ReferentielController(UserRepository userRepository, ArticleRepository articleRepository,
			FamilleArticleRepository familleArticleRepository,
			SousFamilleArticleRepository sousFamilleArticleRepository, GouvernoratRepository gouvernoratRepository,
			StationPeageReposiroty stationPeageReposiroty, ExpertRepository expertRepository,
			LieuParkingRepository lieuParkingRepository, BeneficiaireEmpruntRepository beneficiaireEmpruntRepository,
			EtatStockRepository etatStockRepository, PersonnelRepository personnelRepository,
			StructureRepository structureRepository, MagasinRepository magasinRepository,
			AtelierRepository atelierRepository, RessourceRepository ressourceRepository,
			SectionRepository sectionRepository, UgpRepository ugpRepository, EnergieRepository energieRepository,
			AnneeRepository anneeRepository,
			ProgrammeEntretiensPreventifsRepository programmeEntretiensPreventifsRepository,
			TvaRepository tvaRepository, UniteRepository uniteRepository, FournisseurRepository fournisseurRepository,
			FamilleOperationReparationRepository familleOperationReparationRepository,
			OperationReparationRepository operationReparationRepository, TypeVehiculeRepository typeVehiculeRepository,
			MarqueVehiculeRepository marqueVehiculeRepository, GenreVehiculeRepository genreVehiculeRepository,
			CauseSinistreRepository causeSinistreRepository, UsageVehiculeRepository usageVehiculeRepository,
			ReferentielImpl referentiel, TracabiliteController tracabiliteController) {
		this.userRepository = userRepository;
		this.articleRepository = articleRepository;
		this.familleArticleRepository = familleArticleRepository;
		this.sousFamilleArticleRepository = sousFamilleArticleRepository;
		this.gouvernoratRepository = gouvernoratRepository;
		this.stationPeageReposiroty = stationPeageReposiroty;
		this.expertRepository = expertRepository;
		this.lieuParkingRepository = lieuParkingRepository;
		this.beneficiaireEmpruntRepository = beneficiaireEmpruntRepository;
		this.etatStockRepository = etatStockRepository;
		this.personnelRepository = personnelRepository;
		this.structureRepository = structureRepository;
		this.magasinRepository = magasinRepository;
		this.atelierRepository = atelierRepository;
		this.ressourceRepository = ressourceRepository;
		this.sectionRepository = sectionRepository;
		this.ugpRepository = ugpRepository;
		this.energieRepository = energieRepository;
		this.anneeRepository = anneeRepository;
		this.programmeEntretiensPreventifsRepository = programmeEntretiensPreventifsRepository;
		this.tvaRepository = tvaRepository;
		this.uniteRepository = uniteRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.familleOperationReparationRepository = familleOperationReparationRepository;
		this.operationReparationRepository = operationReparationRepository;
		this.typeVehiculeRepository = typeVehiculeRepository;
		this.marqueVehiculeRepository = marqueVehiculeRepository;
		this.genreVehiculeRepository = genreVehiculeRepository;
		this.causeSinistreRepository = causeSinistreRepository;
		this.usageVehiculeRepository = usageVehiculeRepository;
		this.referentiel = referentiel;
		this.tracabiliteController = tracabiliteController;
	}

	@GetMapping(path = "/hello")
	ResponseEntity<String> sayHello() {
		String response = "Hello, congratulation ! your app is now runnig on the server !";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/famille_article")
	ResponseEntity<HttpStatus> addNewFamilleArticle(@RequestBody FamilleArticleRequest familleArticleRequest) {
		if (this.isUserAuthorised("ADD_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			FamilleArticleDTO familleArticleDTO = modelMapper.map(familleArticleRequest, FamilleArticleDTO.class);
			referentiel.addNewFamilleArticle(familleArticleDTO);
			tracabiliteController.addNewFamilleArticleTracabilite(familleArticleDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/famille_article/{idFamille}")
	ResponseEntity<HttpStatus> deleteSelectedFamilleArticle(@PathVariable Long idFamille) {
		if (this.isUserAuthorised("DELETE_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			FamilleArticleEntity familleArticleEntity = familleArticleRepository.findById(idFamille).get();
			String designation = familleArticleEntity.getFamille();
			String code = familleArticleEntity.getCode();
			referentiel.deleteSelectedFamilleArticle(idFamille);
			tracabiliteController.deleteSelectedFamilleArticleTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/famille_article")
	ResponseEntity<List<FamilleArticleResponseDataTable>> getListFamilleArticle() {
		List<FamilleArticleResponseDataTable> familleArticleResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<FamilleArticleDataDTO> familleArticleDTOS = referentiel.getListFamilleDataArticle();
			if (!familleArticleDTOS.isEmpty()) {
				familleArticleDTOS.forEach(familleArticleDTO -> {
					familleArticleResponses
							.add(modelMapper.map(familleArticleDTO, FamilleArticleResponseDataTable.class));
				});
			}
			return new ResponseEntity<>(familleArticleResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(familleArticleResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/sous_famille_article")
	ResponseEntity<HttpStatus> addNewSousFamilleArticle(
			@RequestBody SousFamilleArticleRequest sousFamilleArticleRequest) {
		if (this.isUserAuthorised("ADD_SOUS_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			SousFamilleArticleDTO sousFamilleArticleDTO = modelMapper.map(sousFamilleArticleRequest,
					SousFamilleArticleDTO.class);
			referentiel.addNewSousFamilleArticle(sousFamilleArticleDTO);
			tracabiliteController.addNewSousFamilleArticleTracabilite(sousFamilleArticleDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/sous_famille_article/{idSousFamille}")
	ResponseEntity<HttpStatus> deleteSelectedSousFamilleArticle(@PathVariable Long idSousFamille) {
		if (this.isUserAuthorised("DELETE_SOUS_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = sousFamilleArticleRepository.findById(idSousFamille).get().getSousFamille();
			String code = sousFamilleArticleRepository.findById(idSousFamille).get().getCode();
			referentiel.deleteSelectedSousFamilleArticle(idSousFamille);
			tracabiliteController.deleteSelectedSousFamilleArticleTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping(path = "/sous_famille_article")
	ResponseEntity<List<SousFamilleArticleResponseData>> getListSousFamilleArticleData() {
		List<SousFamilleArticleResponseData> sousFamilleArticleResponse = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_SOUS_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<SousFamilleArticleDataDTO> sousFamilleArticleDTOS = referentiel.getListSousFamilleArticleData();
			if (!sousFamilleArticleDTOS.isEmpty()) {
				sousFamilleArticleDTOS.forEach(sousFamilleArticleDTO -> {
					sousFamilleArticleResponse
							.add(modelMapper.map(sousFamilleArticleDTO, SousFamilleArticleResponseData.class));
				});
			}
			return new ResponseEntity<>(sousFamilleArticleResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(sousFamilleArticleResponse, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/list_article/{ugp}")
	ResponseEntity<List<ArticleResponse>> getListArticleByUGP(@PathVariable String ugp) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleResponse> articleResponses = new ArrayList<>();
		List<ArticleDTO> articleDTOS = referentiel.getListArticleByUGP(ugp);
		if (!articleDTOS.isEmpty()) {
			articleDTOS.forEach(articleDTO -> {
				articleResponses.add(modelMapper.map(articleDTO, ArticleResponse.class));
			});
		}
		return new ResponseEntity<>(articleResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_article")
	ResponseEntity<List<ArticleResponseDataTable>> getPaginationListArticle(@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleResponseDataTable> articleResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			List<ArticleDataTableDTO> articleDTOS = referentiel.getListArticle(Integer.parseInt(page),
					Integer.parseInt(limit));
			if (!articleDTOS.isEmpty()) {
				articleDTOS.forEach(articleDTO -> {
					articleResponses.add(modelMapper.map(articleDTO, ArticleResponseDataTable.class));
				});
			}
			return new ResponseEntity<>(articleResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(articleResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/getAricleToTransfer/{id}")
	public Iterable<ArticleEntity> ListArticleToVirtuelMagsin(@PathVariable Long id) {
		return articleRepository.listArticleAtransferer(id);
	}

	@GetMapping(path = "/pagination_marche")
	ResponseEntity<List<MarcheResponse>> getPaginationMarche(@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<MarcheResponse> marcheResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_MARCHE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			List<MarcheDTO> marcheDTOS = referentiel.getPaginationListMarche(Integer.parseInt(page),
					Integer.parseInt(limit));
			if (!marcheDTOS.isEmpty()) {
				marcheDTOS.forEach(marcheDTO -> {
					marcheResponses.add(modelMapper.map(marcheDTO, MarcheResponse.class));
				});
			}
			return new ResponseEntity<>(marcheResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(marcheResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/total_item_article")
	ResponseEntity<Long> getTotalItemArticleBySelected() {
		Long totalItem = (long) 0;
		if (this.isUserAuthorised("VIEW_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			totalItem = referentiel.getTotalItemArticleBySelected();
			return new ResponseEntity<>(totalItem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(totalItem, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/article")
	ResponseEntity<HttpStatus> createNewArticle(@RequestBody UpdateArticleRequest updateArticleRequest) {
		System.out.print("\n**********************************\n");
		System.out.println(updateArticleRequest.getAlertStock());

		if (this.isUserAuthorised("ADD_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			System.out.print("\n*****************oui****************\n");
			ModelMapper modelMapper = new ModelMapper();
			UpdateArticleDTO updateArticleDTO = modelMapper.map(updateArticleRequest, UpdateArticleDTO.class);
			referentiel.createNewArticle(updateArticleDTO);
			this.tracabiliteController.addNewArticleTracabilite(updateArticleDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/article")
	ResponseEntity<HttpStatus> modifySelectedArticle(@RequestBody UpdateArticleRequest updateArticleRequest) {

		if (this.isUserAuthorised("MODIFY_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			UpdateArticleDTO updateArticleDTO = modelMapper.map(updateArticleRequest, UpdateArticleDTO.class);
			referentiel.modifySelectedArticle(updateArticleDTO);
			this.tracabiliteController.modifySelectedArticleTracabilite(updateArticleDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/total_number_article_marque")
	ResponseEntity<Long> getTotalNumberArticleByMarqueVehicule(
			@RequestParam(name = "marqueVehicule") MarqueVehiculeEntity marqueVehicule) {
		Long totalNumber = article.getTotalNumberArticleByMarqueVehicule(marqueVehicule);
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_article_genre")
	ResponseEntity<Long> getTotalNumberArticleByGenreVehicule(
			@RequestParam(name = "genreVehicule") GenreVehiculeEntity genreVehicule) {
		Long totalNumber = article.getTotalNumberArticleByGenreVehicule(genreVehicule);
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

	@GetMapping(path = "/total_number_article_type")
	ResponseEntity<Long> getTotalNumberArticleByTypeVehicule(
			@RequestParam(name = "typeVehicule") TypeVehiculeEntity typeVehicule) {
		Long totalNumber = article.getTotalNumberArticleByTypeVehicule(typeVehicule);
		return new ResponseEntity<>(totalNumber, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_article_marque")
	ResponseEntity<List<ArticleResponse>> getPaginationArticleByMarqueVehicule(@RequestParam(name = "page") String page,
			@RequestParam(name = "limit") String limit,
			@RequestParam(name = "marqueVehicule") MarqueVehiculeEntity marqueVehicule) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleResponse> articleResponses = new ArrayList<>();
		List<ArticleDTO> articleDTOS = article.getPaginationArticleByMarqueVehicule(Integer.parseInt(page),
				Integer.parseInt(limit), marqueVehicule);
		if (!articleDTOS.isEmpty()) {
			articleDTOS.forEach(articleDTO -> {
				articleResponses.add(modelMapper.map(articleDTO, ArticleResponse.class));
			});
		}
		return new ResponseEntity<>(articleResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_article_genre")
	ResponseEntity<List<ArticleResponse>> getPaginationArticleByGenreVehicule(@RequestParam(name = "page") String page,
			@RequestParam(name = "limit") String limit,
			@RequestParam(name = "genreVehicule") GenreVehiculeEntity genreVehicule) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleResponse> articleResponses = new ArrayList<>();
		List<ArticleDTO> articleDTOS = article.getPaginationArticleByGenreVehicule(Integer.parseInt(page),
				Integer.parseInt(limit), genreVehicule);
		if (!articleDTOS.isEmpty()) {
			articleDTOS.forEach(articleDTO -> {
				articleResponses.add(modelMapper.map(articleDTO, ArticleResponse.class));
			});
		}
		return new ResponseEntity<>(articleResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/pagination_article_type")
	ResponseEntity<List<ArticleResponse>> getPaginationArticleByTypeVehicule(@RequestParam(name = "page") String page,
			@RequestParam(name = "limit") String limit,
			@RequestParam(name = "typeVehicule") TypeVehiculeEntity typeVehicule) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleResponse> articleResponses = new ArrayList<>();
		List<ArticleDTO> articleDTOS = article.getPaginationArticleByTypeVehicule(Integer.parseInt(page),
				Integer.parseInt(limit), typeVehicule);
		if (!articleDTOS.isEmpty()) {
			articleDTOS.forEach(articleDTO -> {
				articleResponses.add(modelMapper.map(articleDTO, ArticleResponse.class));
			});
		}
		return new ResponseEntity<>(articleResponses, HttpStatus.OK);
	}

	@DeleteMapping(path = "/article/{id}")
	ResponseEntity<HttpStatus> deleteSelectedArticle(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = articleRepository.findById(id).get().getDesignation();
			String code = articleRepository.findById(id).get().getCodeArticle();
			referentiel.deleteSelectedArticle(id);
			tracabiliteController.deleteSelectedArticleTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/list_famille_sous_famille")
	ResponseEntity<List<ListFamilleAndSousFamilleResponse>> getListFamilleAndSousFamille() {
		ModelMapper modelMapper = new ModelMapper();
		List<ListFamilleAndSousFamilleResponse> listFamilleAndSousFamilleResponses = new ArrayList<>();
		List<ListFamilleAndSousFamilleDTO> listFamilleAndSousFamilleDTOS = referentiel.getListFamilleAndSousFamille();
		if (!listFamilleAndSousFamilleDTOS.isEmpty()) {
			listFamilleAndSousFamilleDTOS.forEach(listFamilleAndSousFamilleDTO -> {
				listFamilleAndSousFamilleResponses
						.add(modelMapper.map(listFamilleAndSousFamilleDTO, ListFamilleAndSousFamilleResponse.class));
			});
		}
		return new ResponseEntity<>(listFamilleAndSousFamilleResponses, HttpStatus.OK);
	}

	@PostMapping(path = "/type_vehicule/{idMarque}")
	ResponseEntity<HttpStatus> addNewTypeVehicule(@RequestBody TypeVehiculeRequest typeVehiculeRequest,
			@PathVariable Long idMarque) {
		if (this.isUserAuthorised("ADD_TYPES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			logger.info(typeVehiculeRequest.toString());
			ModelMapper modelMapper = new ModelMapper();
			TypeVehiculeDTO typeVehiculeDTO = modelMapper.map(typeVehiculeRequest, TypeVehiculeDTO.class);
			referentiel.addNewTypeVehicule(typeVehiculeDTO, idMarque);
			tracabiliteController.addNewTypeVehiculeTracabilite(typeVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/type_vehicule")
	ResponseEntity<HttpStatus> modifySelectedTypeVehicule(@RequestBody TypeVehiculeRequest typeVehiculeRequest) {
		if (this.isUserAuthorised("MODIFY_TYPES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			TypeVehiculeDTO typeVehiculeDTO = modelMapper.map(typeVehiculeRequest, TypeVehiculeDTO.class);
			referentiel.modifySelectedTypeVehicule(typeVehiculeDTO);
			tracabiliteController.modifySelectedTypeVehiculeTracabilite(typeVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/sous_famille_article")
	ResponseEntity<HttpStatus> modifySelectedSousFamilleArticle(@RequestBody SousFamilleArticleRequest sousFamilleArticleRequest) {
		if (this.isUserAuthorised("MODIFY_SOUS_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			SousFamilleArticleDTO sousFamilleArticleDTO = modelMapper.map(sousFamilleArticleRequest,
					SousFamilleArticleDTO.class);
			referentiel.modifySelectedSousFamilleArticle(sousFamilleArticleDTO);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@PutMapping(path = "/famille_article")
	ResponseEntity<HttpStatus> modifySelectedFamilleArticle(
			@RequestBody SousFamilleArticleRequest sousFamilleArticleRequest) {
		if (this.isUserAuthorised("MODIFY_SOUS_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			// UpdateArticleDTO updateArticleDTO = modelMapper.map(updateArticleRequest,
			// UpdateArticleDTO.class);
			SousFamilleArticleDTO sousFamilleArticleDTO = modelMapper.map(sousFamilleArticleRequest,
					SousFamilleArticleDTO.class);
			// referentiel.modifySelectedArticle(sousFamilleArticleDTO);
			referentiel.modifySelectedFamilleArticle(sousFamilleArticleDTO);
			this.tracabiliteController.modifySelectedFamilleArticleTracabilite(sousFamilleArticleDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/modify_famille_article")
	ResponseEntity<HttpStatus> modifySelectedFamille(@RequestBody FamilleArticleRequest familleArticleRequest) {
	/*	if (this.isUserAuthorised("MODIFY_FAMILLES_ARTICLES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {*/
			ModelMapper modelMapper = new ModelMapper();
			FamilleArticleDTO familleArticleDTO = modelMapper.map(familleArticleRequest, FamilleArticleDTO.class);
			referentiel.modifySelectedFamille(familleArticleDTO);
			this.tracabiliteController.modifySelectedFamilleTracabilite(familleArticleDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} /*else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}*/


	@DeleteMapping(path = "/type_vehicule/{id}")
	ResponseEntity<HttpStatus> deleteSelectedTypeVehicule(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_TYPES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = typeVehiculeRepository.findById(id).get().getCode();
			String designation = typeVehiculeRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedTypeVehicule(id);
			tracabiliteController.deleteSelectedTypeVehiculeTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/type_vehicule")
	ResponseEntity<List<TypeVehiculeResponse>> getListTypeVehicule(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<TypeVehiculeResponse> typeVehiculeResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_TYPES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<TypeVehiculeDTO> typeVehiculeDTOS = referentiel.getListTypeVehicule(Integer.parseInt(page), Integer.parseInt(limit));
			if (!typeVehiculeDTOS.isEmpty()) {
				typeVehiculeDTOS.forEach(typeVehiculeDTO -> {
					typeVehiculeResponses.add(modelMapper.map(typeVehiculeDTO, TypeVehiculeResponse.class));
				});
			}
			return new ResponseEntity<>(typeVehiculeResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(typeVehiculeResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/type_marque_vehicule")
	ResponseEntity<List<ListTypeMarqueResponse>> getListTypeMarqueVehicule(	@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<ListTypeMarqueResponse> listTypeMarqueResponses = new ArrayList<>();
		List<TypeVehiculeDTO> typeVehiculeDTOS = referentiel.getListTypeVehicule(Integer.parseInt(page), Integer.parseInt(limit));
		if (!typeVehiculeDTOS.isEmpty()) {
			typeVehiculeDTOS.forEach(typeVehiculeDTO -> {
				ListTypeMarqueResponse listTypeMarqueResponse = new ListTypeMarqueResponse();
				listTypeMarqueResponse.setIdType(typeVehiculeDTO.getId());
				listTypeMarqueResponse.setIdMarque(typeVehiculeDTO.getMarques().getId());
				listTypeMarqueResponse.setCodeType(typeVehiculeDTO.getCode());
				listTypeMarqueResponse.setCodeMarque(typeVehiculeDTO.getMarques().getCode());
				listTypeMarqueResponse.setDesignationType(typeVehiculeDTO.getDesignation());
				listTypeMarqueResponse.setDesignationMarque(typeVehiculeDTO.getMarques().getDesignation());
				listTypeMarqueResponses.add(listTypeMarqueResponse);
			});
		}
		return new ResponseEntity<>(listTypeMarqueResponses, HttpStatus.OK);
	}

	@PostMapping(path = "/marque_vehicule")
	ResponseEntity<HttpStatus> addNewMarqueVehicule(@RequestBody MarqueVehiculeRequest marqueVehiculeRequest) {
		if (this.isUserAuthorised("ADD_MARQUES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			MarqueVehiculeDTO marqueVehiculeDTO = modelMapper.map(marqueVehiculeRequest, MarqueVehiculeDTO.class);
			referentiel.addNewMarqueVehicule(marqueVehiculeDTO);
			tracabiliteController.addNewMarqueVehiculeTracabilite(marqueVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/marque_vehicule")
	ResponseEntity<HttpStatus> modifySelectedMarqueVehicule(@RequestBody MarqueVehiculeRequest marqueVehiculeRequest) {
		if (this.isUserAuthorised("MODIFY_MARQUES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			MarqueVehiculeDTO marqueVehiculeDTO = modelMapper.map(marqueVehiculeRequest, MarqueVehiculeDTO.class);
			referentiel.modifySelectedMarqueVehicule(marqueVehiculeDTO);
			tracabiliteController.modifySelectedMarqueVehiculeTracabilite(marqueVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping(path = "/marque_vehicule/{id}")
	ResponseEntity<HttpStatus> deleteSelectedMarqueVehicule(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_MARQUES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = marqueVehiculeRepository.findById(id).get().getCode();
			String designation = marqueVehiculeRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedMarqueVehicule(id);
			tracabiliteController.deleteSelectedMarqueVehiculeTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	// 23_07_2021
	@GetMapping(path = "/listarticle")

	public Iterable<ArticleEntity> articleList() {
		return articleRepository.findAll();
	}
	
	@GetMapping(path = "/PaginationListArticle")
	public List<ArticleResponse>PaginationarticleList(
			@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit
			) {	
			ModelMapper modelMapper = new ModelMapper();
			List<ArticleResponse> magasinVirtuelDataTableResponse = new ArrayList<>();
			List<ArticleDTO> magasinVirtuelDataTableDTOS = referentiel.getPaginationListArticle(
					Integer.parseInt(page), Integer.parseInt(limit));
			if (!magasinVirtuelDataTableDTOS.isEmpty()) {
				magasinVirtuelDataTableDTOS.forEach(magasinVirtuelDataTableDTO -> {
					magasinVirtuelDataTableResponse
							.add(modelMapper.map(magasinVirtuelDataTableDTO, ArticleResponse.class));
				});
			}
			return magasinVirtuelDataTableResponse;
		}
		
	
	@GetMapping(path = "/PaginationListSousFamilleArticle")
	public List<SousFamilleArticleResponse> PaginationSousFamilleArticleList(
			@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit
			) {	
		
		System.out.print("\n page\n"+page);
		System.out.print("limite \n"+limit);
			ModelMapper modelMapper = new ModelMapper();
			List<SousFamilleArticleResponse> magasinVirtuelDataTableResponse = new ArrayList<>();
			List<SousFamilleArticleDTO> magasinVirtuelDataTableDTOS = referentiel.getPaginationListSousFamilletArticle(
					Integer.parseInt(page), Integer.parseInt(limit));
			if (!magasinVirtuelDataTableDTOS.isEmpty()) {
				magasinVirtuelDataTableDTOS.forEach(magasinVirtuelDataTableDTO -> {
					magasinVirtuelDataTableResponse
							.add(modelMapper.map(magasinVirtuelDataTableDTO, SousFamilleArticleResponse.class));
				});
			}
			return magasinVirtuelDataTableResponse;
		}
		
	@GetMapping(path = "/Pagination_List_Famille_Article")
	public List<FamilleArticleResponse> PaginationFamilleArticleList(
			@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit
			) {	
		
		System.out.print("\n page\n"+page);
		System.out.print("limite \n"+limit);
			ModelMapper modelMapper = new ModelMapper();
			List<FamilleArticleResponse> magasinVirtuelDataTableResponse = new ArrayList<>();
			List<FamilleArticleDTO> magasinVirtuelDataTableDTOS = referentiel.getPaginationListFamilletArticle(
					Integer.parseInt(page), Integer.parseInt(limit));
			if (!magasinVirtuelDataTableDTOS.isEmpty()) {
				magasinVirtuelDataTableDTOS.forEach(magasinVirtuelDataTableDTO -> {
					magasinVirtuelDataTableResponse
							.add(modelMapper.map(magasinVirtuelDataTableDTO, FamilleArticleResponse.class));
				});
			}
			return magasinVirtuelDataTableResponse;
		}
		
	@GetMapping(path = "/Pagination_List_energies")
	public List<EnergieResponse> PaginationEnergieList(
			@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit
			) {	
		
		System.out.print("\n page\n"+page);
		System.out.print("limite \n"+limit);
			ModelMapper modelMapper = new ModelMapper();
			List<EnergieResponse> magasinVirtuelDataTableResponse = new ArrayList<>();
			List<EnergieDTO> magasinVirtuelDataTableDTOS = referentiel.getPaginationListEnergie(
					Integer.parseInt(page), Integer.parseInt(limit));
			if (!magasinVirtuelDataTableDTOS.isEmpty()) {
				magasinVirtuelDataTableDTOS.forEach(magasinVirtuelDataTableDTO -> {
					magasinVirtuelDataTableResponse
							.add(modelMapper.map(magasinVirtuelDataTableDTO, EnergieResponse.class));
				});
			}
			return magasinVirtuelDataTableResponse;
		}
	
	@GetMapping(path = "/Pagination_List_TVA")
	public List<TvaResponse> PaginationTvaList(
			@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit
			) {	
		
		System.out.print("\n page\n"+page);
		System.out.print("limite \n"+limit);
			ModelMapper modelMapper = new ModelMapper();
			List<TvaResponse> magasinVirtuelDataTableResponse = new ArrayList<>();
			List<TvaDTO> magasinVirtuelDataTableDTOS = referentiel.getPaginationListTva(
					Integer.parseInt(page), Integer.parseInt(limit));
			if (!magasinVirtuelDataTableDTOS.isEmpty()) {
				magasinVirtuelDataTableDTOS.forEach(magasinVirtuelDataTableDTO -> {
					magasinVirtuelDataTableResponse
							.add(modelMapper.map(magasinVirtuelDataTableDTO, TvaResponse.class));
				});
			}
			return magasinVirtuelDataTableResponse;
		}
	

	@GetMapping(path = "/listMagasin")
	public Iterable<MagasinEntity> listMagasin() {
		return magasinRepository.findAll();
	}

	@GetMapping(path = "/listAtelier")
	public Iterable<AtelierEntity> listAtelier() {
		return atelierRepository.findAll();
	}

	@GetMapping(path = "/listVehicule")

	public Iterable<VehiculeEntity> vehiculeList() {
		return vehiculeRepository.findAll();

	}

	@PostMapping(path = "/energie")
	ResponseEntity<HttpStatus> addNewEnergie(@RequestBody EnergieRequest energieRequest) {
		if (this.isUserAuthorised("ADD_ENERGIES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			EnergieDTO energieDTO = modelMapper.map(energieRequest, EnergieDTO.class);
			referentiel.addNewEnergie(energieDTO);
			tracabiliteController.addNewEnergieTracabilite(energieDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/energie")
	ResponseEntity<HttpStatus> modifySelectedEnergie(@RequestBody EnergieRequest energieRequest) {
		if (this.isUserAuthorised("MODIFY_ENERGIES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			EnergieDTO energieDTO = modelMapper.map(energieRequest, EnergieDTO.class);
			referentiel.modifySelectedEnergie(energieDTO);
			tracabiliteController.modifySelectedEnergieTracabilite(energieDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/energie/{id}")
	ResponseEntity<HttpStatus> deleteSelectedEnergie(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_ENERGIES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = energieRepository.findById(id).get().getCode();
			String description = energieRepository.findById(id).get().getDescription();
			referentiel.deleteSelectedEnergie(id);
			tracabiliteController.deleteSelectedEnergieTracabilite(code, description,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/energie")
	ResponseEntity<List<EnergieResponse>> getListEnergie() {
		List<EnergieResponse> energieResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_ENERGIES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<EnergieDTO> energieDTOS = referentiel.getListEnergie();
			if (!energieDTOS.isEmpty()) {
				energieDTOS.forEach(energieDTO -> {
					energieResponses.add(modelMapper.map(energieDTO, EnergieResponse.class));
				});
			}
			return new ResponseEntity<>(energieResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(energieResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/annee")
	ResponseEntity<HttpStatus> addNewAnnee(@RequestBody AnneeRequest anneeRequest) {
		if (this.isUserAuthorised("ADD_ANNEES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			AnneeDTO anneeDTO = modelMapper.map(anneeRequest, AnneeDTO.class);
			referentiel.addNewAnnee(anneeDTO);
			tracabiliteController.addNewAnneeTracabilite(anneeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/annee/{id}")
	ResponseEntity<HttpStatus> deleteSelectedAnnee(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_ANNEES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			int annee = anneeRepository.findById(id).get().getAnnee();
			referentiel.deleteSelectedAnnee(id);
			tracabiliteController.deleteSelectedAnneeTracabilite(annee,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/annee")
	ResponseEntity<List<AnneeResponse>> getListAnnee() {
		List<AnneeResponse> anneeResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_ANNEES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<AnneeDTO> anneeDTOS = referentiel.getListAnnee();
			if (!anneeDTOS.isEmpty()) {
				anneeDTOS.forEach(anneeDTO -> {
					anneeResponses.add(modelMapper.map(anneeDTO, AnneeResponse.class));
				});
			}
			return new ResponseEntity<>(anneeResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(anneeResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/tva")
	ResponseEntity<HttpStatus> addNewTva(@RequestBody TvaRequest tvaRequest) {
		if (this.isUserAuthorised("ADD_TVA",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			TvaDTO tvaDTO = modelMapper.map(tvaRequest, TvaDTO.class);
			referentiel.addNewTva(tvaDTO);
			tracabiliteController.addNewTVATracabilite(tvaDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/tva/{id}")
	ResponseEntity<HttpStatus> deleteSelectedTva(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_TVA",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			float tva = tvaRepository.findById(id).get().getTva();
			referentiel.deleteSelectedTva(id);
			tracabiliteController.deleteSelectedTVATracabilite(tva,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/tva")
	ResponseEntity<List<TvaResponse>> getListTva() {
		List<TvaResponse> tvaResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_TVA",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<TvaDTO> tvaDTOS = referentiel.getListTva();
			if (!tvaDTOS.isEmpty()) {
				tvaDTOS.forEach(tvaDTO -> {
					tvaResponses.add(modelMapper.map(tvaDTO, TvaResponse.class));
				});
			}
			return new ResponseEntity<>(tvaResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(tvaResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/unite")
	ResponseEntity<HttpStatus> addNewUnite(@RequestBody UniteRequest uniteRequest) {
		if (this.isUserAuthorised("ADD_UNITES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			UniteDTO uniteDTO = modelMapper.map(uniteRequest, UniteDTO.class);
			referentiel.addNewUnite(uniteDTO);
			tracabiliteController.addNewUniteTracabilite(uniteDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/unite/{id}")
	ResponseEntity<HttpStatus> deleteSelectedUnite(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_UNITES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String unite = uniteRepository.findById(id).get().getUnite();
			referentiel.deleteSelectedUnite(id);
			tracabiliteController.deleteSelectedUniteTracabilite(unite,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/unite")
	ResponseEntity<List<UniteResponse>> getListUnite() {
		List<UniteResponse> uniteResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_UNITES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<UniteDTO> uniteDTOS = referentiel.getListUnite();
			if (!uniteDTOS.isEmpty()) {
				uniteDTOS.forEach(uniteDTO -> {
					uniteResponses.add(modelMapper.map(uniteDTO, UniteResponse.class));
				});
			}
			return new ResponseEntity<>(uniteResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(uniteResponses, HttpStatus.UNAUTHORIZED);
		}
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

	// End

	@GetMapping(path = "/marque_vehicule")
	ResponseEntity<List<MarqueVehiculeResponse>> getListMarqueVehicule(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		if (this.isUserAuthorised("VIEW_MARQUES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<MarqueVehiculeResponse> marqueVehiculeResponses = new ArrayList<>();
			List<MarqueVehiculeDTO> marqueVehiculeDTOS = referentiel.getListMarqueVehicule(Integer.parseInt(page), Integer.parseInt(limit));
			if (!marqueVehiculeDTOS.isEmpty()) {
				marqueVehiculeDTOS.forEach(marqueVehiculeDTO -> {
					marqueVehiculeResponses.add(modelMapper.map(marqueVehiculeDTO, MarqueVehiculeResponse.class));
				});
			}
			return new ResponseEntity<>(marqueVehiculeResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/genre_vehicule")
	ResponseEntity<HttpStatus> addNewGenreVehicule(@RequestBody GenreVehiculeRequest genreVehiculeRequest) {
		if (this.isUserAuthorised("ADD_GENRES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			GenreVehiculeDTO genreVehiculeDTO = modelMapper.map(genreVehiculeRequest, GenreVehiculeDTO.class);
			referentiel.addNewGenreVehicule(genreVehiculeDTO);
			tracabiliteController.addNewGenreVehiculeTracabilite(genreVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/genre_vehicule")
	ResponseEntity<HttpStatus> modifySelectedGenreVehicule(@RequestBody GenreVehiculeRequest genreVehiculeRequest) {

		if (this.isUserAuthorised("MODIFY_GENRES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			GenreVehiculeDTO genreVehiculeDTO = modelMapper.map(genreVehiculeRequest, GenreVehiculeDTO.class);
			referentiel.modifySelectedGenreVehicule(genreVehiculeDTO);
			tracabiliteController.modifySelectedGenreVehiculeTracabilite(genreVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping(path = "/genre_vehicule/{id}")
	ResponseEntity<HttpStatus> deleteSelectedGenreVehicule(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_GENRES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = genreVehiculeRepository.findById(id).get().getCode();
			String designation = genreVehiculeRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedGenreVehicule(id);
			tracabiliteController.deleteSelectedGenreVehiculeTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/genre_vehicule")
	ResponseEntity<List<GenreVehiculeResponse>> getListGenreVehicule(	@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<GenreVehiculeResponse> genreVehiculeResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_GENRES_VEHICULE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<GenreVehiculeDTO> genreVehiculeDTOS = referentiel.getListGenreVehicule(Integer.parseInt(page), Integer.parseInt(limit));
			if (!genreVehiculeDTOS.isEmpty()) {
				genreVehiculeDTOS.forEach(genreVehiculeDTO -> {
					genreVehiculeResponses.add(modelMapper.map(genreVehiculeDTO, GenreVehiculeResponse.class));
				});
			}
			return new ResponseEntity<>(genreVehiculeResponses, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(genreVehiculeResponses, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(path = "/gouvernoratList")
	ResponseEntity<List<GouvernoratResponse>> getListGouvernorat(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit)
	{
	System.out.print("\n "+limit);
	List<GouvernoratResponse> gouvernoratResponses = new ArrayList<>();
	if (this.isUserAuthorised("VIEW_GOUVERNORATS",
	SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
	ModelMapper modelMapper = new ModelMapper();
	List<GouvernoratDTO> gouvernoratDTOS = referentiel.getPaginationListGouvernorat(Integer.parseInt(page), Integer.parseInt(limit));
	if (!gouvernoratDTOS.isEmpty()) {
	gouvernoratDTOS.forEach(gouvernoratDTO -> {
	gouvernoratResponses.add(modelMapper.map(gouvernoratDTO, GouvernoratResponse.class));
	});
	}
	return new ResponseEntity<>(gouvernoratResponses, HttpStatus.OK);
	} else {
	return new ResponseEntity<>(gouvernoratResponses, HttpStatus.UNAUTHORIZED);
	}
	}

	
	@PostMapping(path = "/usage_vehicule")
	ResponseEntity<HttpStatus> addNewUsageVehicule(@RequestBody UsageVehiculeRequest usageVehiculeRequest) {
		if (this.isUserAuthorised("ADD_USAGES_VEHICULES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			UsageVehiculeDTO usageVehiculeDTO = modelMapper.map(usageVehiculeRequest, UsageVehiculeDTO.class);
			referentiel.addNewUsageVehicule(usageVehiculeDTO);
			tracabiliteController.addNewUsageVehiculeTracabilite(usageVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/usage_vehicule")
	ResponseEntity<HttpStatus> modifySelectedUsageVehicule(@RequestBody UsageVehiculeRequest usageVehiculeRequest) {
		if (this.isUserAuthorised("MODIFY_USAGES_VEHICULES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			UsageVehiculeDTO usageVehiculeDTO = modelMapper.map(usageVehiculeRequest, UsageVehiculeDTO.class);
			referentiel.modifySelectedUsageVehicule(usageVehiculeDTO);
			tracabiliteController.modifySelectedUsageVehiculeTracabilite(usageVehiculeDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/usage_vehicule/{id}")
	ResponseEntity<HttpStatus> deleteSelectedUsageVehicule(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_USAGES_VEHICULES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = usageVehiculeRepository.findById(id).get().getCode();
			String designation = usageVehiculeRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedUsageVehicule(id);
			tracabiliteController.deleteSelectedUsageVehiculeTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/usage_vehicule")
	ResponseEntity<List<UsageVehiculeResponse>> getListUsageVehicule(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<UsageVehiculeResponse> usageVehiculeResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_USAGES_VEHICULES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<UsageVehiculeDTO> usageVehiculeDTOS = referentiel.getListUsageVehicule(Integer.parseInt(page), Integer.parseInt(limit));
			if (!usageVehiculeDTOS.isEmpty()) {
				usageVehiculeDTOS.forEach(usageVehiculeDTO -> {
					usageVehiculeResponses.add(modelMapper.map(usageVehiculeDTO, UsageVehiculeResponse.class));
				});
			}
			return new ResponseEntity<>(usageVehiculeResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(usageVehiculeResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/cause_sinistre")
	ResponseEntity<HttpStatus> addNewCauseSinistre(@RequestBody CauseSinistreRequest causeSinistreRequest) {
		if (this.isUserAuthorised("ADD_CAUSES_SINISTRES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			CauseSinistreDTO causeSinistreDTO = modelMapper.map(causeSinistreRequest, CauseSinistreDTO.class);
			referentiel.addNewCauseSinistre(causeSinistreDTO);
			tracabiliteController.addNewCauseSinistreTracabilite(causeSinistreDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/cause_sinistre")
	ResponseEntity<HttpStatus> modifySelectedCauseSinistre(@RequestBody CauseSinistreRequest causeSinistreRequest) {
		if (this.isUserAuthorised("MODIFY_CAUSES_SINISTRES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			CauseSinistreDTO causeSinistreDTO = modelMapper.map(causeSinistreRequest, CauseSinistreDTO.class);
			tracabiliteController.modifySelectedCauseSinistreTracabilite(causeSinistreDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			referentiel.modifySelectedCauseSinistre(causeSinistreDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/cause_sinistre/{id}")
	ResponseEntity<HttpStatus> deleteSelectedCauseSinistre(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_CAUSES_SINISTRES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = causeSinistreRepository.findById(id).get().getCode();
			String designation = causeSinistreRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedCauseSinistre(id);
			tracabiliteController.deleteSelectedCauseSinistreTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/cause_sinistre")
	ResponseEntity<List<CauseSinistreResponse>> getListCauseSinistre(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<CauseSinistreResponse> causeSinistreResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_CAUSES_SINISTRES",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<CauseSinistreDTO> causeSinistreDTOS = referentiel.getListCauseSinistre(Integer.parseInt(page), Integer.parseInt(limit));
			if (!causeSinistreDTOS.isEmpty()) {
				causeSinistreDTOS.forEach(causeSinistreDTO -> {
					causeSinistreResponses.add(modelMapper.map(causeSinistreDTO, CauseSinistreResponse.class));
				});
			}
			return new ResponseEntity<>(causeSinistreResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(causeSinistreResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/famille_operation_reparation")
	ResponseEntity<HttpStatus> addNewFamilleOperationReparation(
			@RequestBody FamilleOperationReparationRequest familleOperationReparationRequest) {
		if (this.isUserAuthorised("ADD_FAMILLES_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			FamilleOperationReparationDTO familleOperationReparationDTO = modelMapper
					.map(familleOperationReparationRequest, FamilleOperationReparationDTO.class);
			referentiel.addNewFamilleOperationReparation(familleOperationReparationDTO);
			tracabiliteController.addNewFamilleOperationReparationTracabilite(familleOperationReparationDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/famille_operation_reparation")
	ResponseEntity<HttpStatus> modifySelectedFamilleOperationReparation(
			@RequestBody FamilleOperationReparationRequest familleOperationReparationRequest) {
		if (this.isUserAuthorised("MODIFY_FAMILLES_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			FamilleOperationReparationDTO familleOperationReparationDTO = modelMapper
					.map(familleOperationReparationRequest, FamilleOperationReparationDTO.class);
			referentiel.modifySelectedFamilleOperationReparation(familleOperationReparationDTO);
			tracabiliteController.modifySelectedFamilleOperationReparationTracabilite(familleOperationReparationDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/famille_operation_reparation/{id}")
	ResponseEntity<HttpStatus> deleteSelectedFamilleOperationReparation(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_FAMILLES_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = familleOperationReparationRepository.findById(id).get().getCode();
			String designation = familleOperationReparationRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedFamilleOperationReparation(id);
			tracabiliteController.deleteSelectedFamilleOperationReparationTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/famille_operation_reparation")
	ResponseEntity<List<FamilleOperationReparationResponse>> getListFamilleOperationReparation(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<FamilleOperationReparationResponse> familleOperationReparationResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_FAMILLES_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<FamilleOperationReparationDTO> familleOperationReparationDTOS = referentiel
					.getListFamilleOperationReparation(Integer.parseInt(page), Integer.parseInt(limit));
			if (!familleOperationReparationDTOS.isEmpty()) {
				familleOperationReparationDTOS.forEach(familleOperationReparationDTO -> {
					familleOperationReparationResponses.add(
							modelMapper.map(familleOperationReparationDTO, FamilleOperationReparationResponse.class));
				});
			}
			return new ResponseEntity<>(familleOperationReparationResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(familleOperationReparationResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/operation_reparation")
	ResponseEntity<HttpStatus> addNewOperationReparation(
			@RequestBody OperationReparationRequest operationReparationRequest) {
		if (this.isUserAuthorised("ADD_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			OperationReparationDTO operationReparationDTO = modelMapper.map(operationReparationRequest,
					OperationReparationDTO.class);
			referentiel.addNewOperationReparation(operationReparationDTO);
			tracabiliteController.addNewOperationReparationTracabilite(operationReparationDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/operation_reparation")
	ResponseEntity<HttpStatus> modifySelectedOperationReparation(
			@RequestBody OperationReparationRequest operationReparationRequest) {
		if (this.isUserAuthorised("MODIFY_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			OperationReparationDTO operationReparationDTO = modelMapper.map(operationReparationRequest,
					OperationReparationDTO.class);
			referentiel.modifySelectedOperationReparation(operationReparationDTO);
			tracabiliteController.modifySelectedOperationReparationTracabilite(operationReparationDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/operation_reparation/{id}")
	ResponseEntity<HttpStatus> deleteSelectedOperationReparation(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = operationReparationRepository.findById(id).get().getCode();
			String designation = operationReparationRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedOperationReparation(id);
			tracabiliteController.deleteSelectedOperationReparationTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/operation_reparation")
	ResponseEntity<List<OperationReparationResponse>> getListOperationReparation(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<OperationReparationResponse> operationReparationResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_OPERATIONS_REPARATION",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<OperationReparationDTO> operationReparationDTOS = referentiel.getListOperationReparation(Integer.parseInt(page), Integer.parseInt(limit));
			if (!operationReparationDTOS.isEmpty()) {
				operationReparationDTOS.forEach(operationReparationDTO -> {
					operationReparationResponses
							.add(modelMapper.map(operationReparationDTO, OperationReparationResponse.class));
				});
			}
			return new ResponseEntity<>(operationReparationResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(operationReparationResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/fournisseur")
	ResponseEntity<HttpStatus> addNewFournisseur(@RequestBody FournisseurRequest fournisseurRequest) {
		if (this.isUserAuthorised("ADD_FOURNISSEURS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			FournisseurDTO fournisseurDTO = modelMapper.map(fournisseurRequest, FournisseurDTO.class);
			referentiel.addNewFournisseur(fournisseurDTO);
			tracabiliteController.addNewFournisseurTracabilite(fournisseurDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/fournisseur")
	ResponseEntity<HttpStatus> modifySelectedFournisseur(@RequestBody FournisseurRequest fournisseurRequest) {
		if (this.isUserAuthorised("MODIFY_FOURNISSEURS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			FournisseurDTO fournisseurDTO = modelMapper.map(fournisseurRequest, FournisseurDTO.class);
			referentiel.modifySelectedFournisseur(fournisseurDTO);
			tracabiliteController.modifySelectedFournisseurTracabilite(fournisseurDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/fournisseur/{id}")
	ResponseEntity<HttpStatus> deleteSelectedFournisseur(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_FOURNISSEURS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = fournisseurRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedFournisseur(id);
			tracabiliteController.deleteSelectedFournisseurTracabilite(designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*@GetMapping(path = "/fournisseur")
	Iterable<FournisseurEntity> getListFournisseur() {
		return fournisseurRepository.findAll(pageable);

	}*/
	
	@GetMapping(path = "/fournisseur")
	ResponseEntity<List<FournisseurResponse>> getListFournisseur(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<FournisseurResponse> fournisseurResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_FOURNISSEURS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<FournisseurDTO> fournisseurDTOS = referentiel
					.getListFournisseur(Integer.parseInt(page), Integer.parseInt(limit));
			if (!fournisseurDTOS.isEmpty()) {
				fournisseurDTOS.forEach(fournisseurDTO -> {
					fournisseurResponses.add(
							modelMapper.map(fournisseurDTO, FournisseurResponse.class));
				});
			}
			return new ResponseEntity<>(fournisseurResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(fournisseurResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/fournisseurBonCommande")
	ResponseEntity<List<FournisseurResponse>> getListFournisseurBonCommande() {
		List<FournisseurResponse> fournisseurResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_FOURNISSEURS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<FournisseurListDTO> fournisseurDTOS = referentiel.getListFournisseurBonCommande();
			if (!fournisseurDTOS.isEmpty()) {
				fournisseurDTOS.forEach(fournisseurDTO -> {
					fournisseurResponses.add(modelMapper.map(fournisseurDTO, FournisseurResponse.class));
				});
			}
			return new ResponseEntity<>(fournisseurResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(fournisseurResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/activite_fournisseur")
	ResponseEntity<List<String>> getListActiviteFournisseur() {
		List<String> listActivite = referentiel.getListActiviteFournisseur();
		return new ResponseEntity<>(listActivite, HttpStatus.OK);
	}

	@GetMapping(path = "/marche")
	ResponseEntity<List<MarcheResponse>> getListMarche() {
		ModelMapper modelMapper = new ModelMapper();
		List<MarcheResponse> marcheResponses = new ArrayList<>();
		List<MarcheDTO> marcheDTOS = referentiel.getListMarche();
		if (!marcheDTOS.isEmpty()) {
			marcheDTOS.forEach(marcheDTO -> {
				marcheResponses.add(modelMapper.map(marcheDTO, MarcheResponse.class));
			});
		}
		return new ResponseEntity<>(marcheResponses, HttpStatus.OK);
	}

	@PostMapping(path = "/gouvernorat")
	ResponseEntity<HttpStatus> addNewGouvernorat(@RequestBody GouvernoratRequest gouvernoratRequest) {
		if (this.isUserAuthorised("ADD_GOUVERNORATS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			GouvernoratDTO gouvernoratDTO = modelMapper.map(gouvernoratRequest, GouvernoratDTO.class);
			referentiel.addNewGouvernorat(gouvernoratDTO);
			tracabiliteController.addNewGouvernoratTracabilite(gouvernoratDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@PutMapping(path = "/gouvernorat")
	ResponseEntity<HttpStatus> modifySelectedGouvernorat(@RequestBody GouvernoratRequest gouvernoratRequest) {
		if (this.isUserAuthorised("MODIFY_GOUVERNORATS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			GouvernoratDTO gouvernoratDTO = modelMapper.map(gouvernoratRequest, GouvernoratDTO.class);
			referentiel.modifySelectedGouvernorat(gouvernoratDTO);
			tracabiliteController.modifySelectedGouvernoratTracabilite(gouvernoratDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/gouvernorat/{id}")
	ResponseEntity<HttpStatus> deleteSelectedGouvernorat(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_GOUVERNORATS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = gouvernoratRepository.findById(id).get().getDesignation();
			String code = gouvernoratRepository.findById(id).get().getCode();
			referentiel.deleteSelectedGouvernorat(id);
			tracabiliteController.deleteSelectedGouvernoratTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/gouvernorat")
	ResponseEntity<List<GouvernoratResponse>> getListGouvernorat() {
		List<GouvernoratResponse> gouvernoratResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_GOUVERNORATS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<GouvernoratDTO> gouvernoratDTOS = referentiel.getListGouvernorat();
			if (!gouvernoratDTOS.isEmpty()) {
				gouvernoratDTOS.forEach(gouvernoratDTO -> {
					gouvernoratResponses.add(modelMapper.map(gouvernoratDTO, GouvernoratResponse.class));
				});
			}
			return new ResponseEntity<>(gouvernoratResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gouvernoratResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/station_peage")
	ResponseEntity<HttpStatus> addNewStationPeage(@RequestBody String designation) {
		if (this.isUserAuthorised("ADD_STATIONS_PEAGE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			referentiel.addNewStationPeage(designation);
			tracabiliteController.addNewStationPeageTracabilite(designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/station_peage")
	ResponseEntity<HttpStatus> modifySelectedStationPeage(@RequestBody StationPeageRequest stationPeageRequest) {
		if (this.isUserAuthorised("MODIFY_STATIONS_PEAGE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			StationPeageDTO stationPeageDTO = modelMapper.map(stationPeageRequest, StationPeageDTO.class);
			referentiel.modifySelectedStationPeage(stationPeageDTO);
			tracabiliteController.modifySelectedStationPeageTracabilite(stationPeageDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/station_peage/{id}")
	ResponseEntity<HttpStatus> deleteSelectedStationPeage(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_STATIONS_PEAGE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = stationPeageReposiroty.findById(id).get().getDesignation();
			referentiel.deleteSelectedStationPeage(id);
			tracabiliteController.deleteSelectedStationPeageTracabilite(designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/station_peage")
	ResponseEntity<List<StationPeageResponse>> getListStationPeage() {
		List<StationPeageResponse> stationPeageResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_STATIONS_PEAGE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<StationPeageDTO> stationPeageDTOS = referentiel.getListStationPeage();
			if (!stationPeageDTOS.isEmpty()) {
				stationPeageDTOS.forEach(stationPeageDTO -> {
					stationPeageResponses.add(modelMapper.map(stationPeageDTO, StationPeageResponse.class));
				});
			}
			return new ResponseEntity<>(stationPeageResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(stationPeageResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/expert")
	ResponseEntity<HttpStatus> addNewExpert(@RequestBody ExpertRequest expertRequest) {
		if (this.isUserAuthorised("ADD_EXPERTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			ExpertDTO expertDTO = modelMapper.map(expertRequest, ExpertDTO.class);
			referentiel.addNewExpert(expertDTO);
			tracabiliteController.addNewExpertTracabilite(expertDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/expert")
	ResponseEntity<HttpStatus> modifySelectedExpert(@RequestBody ExpertRequest expertRequest) {
		if (this.isUserAuthorised("MODIFY_EXPERTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			ExpertDTO expertDTO = modelMapper.map(expertRequest, ExpertDTO.class);
			referentiel.modifySelectedExpert(expertDTO);
			tracabiliteController.modifySelectedExpertTracabilite(expertDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/expert/{id}")
	ResponseEntity<HttpStatus> deleteSelectedExpert(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_EXPERTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = expertRepository.findById(id).get().getDesignation();
			String code = expertRepository.findById(id).get().getCode();
			SinistreVehiculeEntity se = sinistreVehiculeRepository.findByExpertId(id);
			if (se!=null) {
				se.setExpert(null)	;
			}
			
			referentiel.deleteSelectedExpert(id);
			tracabiliteController.deleteSelectedExpertTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/expert")
	ResponseEntity<List<ExpertResponse>> getListExpert() {
		List<ExpertResponse> expertResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_EXPERTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<ExpertDTO> expertDTOS = referentiel.getListExpert();
			if (!expertDTOS.isEmpty()) {
				expertDTOS.forEach(expertDTO -> {
					expertResponses.add(modelMapper.map(expertDTO, ExpertResponse.class));
				});
			}
			return new ResponseEntity<>(expertResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(expertResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/lieu_parking")
	ResponseEntity<HttpStatus> addNewLieuParking(@RequestBody LieuParkingRequest lieuParkingRequest) {
		if (this.isUserAuthorised("ADD_LIEUX_PARKING",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			LieuParkingDTO lieuParkingDTO = modelMapper.map(lieuParkingRequest, LieuParkingDTO.class);
			referentiel.addNewLieuParking(lieuParkingDTO);
			tracabiliteController.addNewLieuParkingTracabilite(lieuParkingDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/lieu_parking")
	ResponseEntity<HttpStatus> modifySelectedLieuParking(@RequestBody LieuParkingRequest lieuParkingRequest) {
		if (this.isUserAuthorised("MODIFY_LIEUX_PARKING",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			LieuParkingDTO lieuParkingDTO = modelMapper.map(lieuParkingRequest, LieuParkingDTO.class);
			referentiel.modifySelectedLieuParking(lieuParkingDTO);
			tracabiliteController.modifySelectedLieuParkingTracabilite(lieuParkingDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/lieu_parking/{id}")
	ResponseEntity<HttpStatus> deleteSelectedLieuParking(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_LIEUX_PARKING",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String adresse = lieuParkingRepository.findById(id).get().getAdresse();
			referentiel.deleteSelectedLieuParking(id);
			tracabiliteController.deleteSelectedLieuParkingTracabilite(adresse,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/lieu_parking/{gouvernorat}")
	ResponseEntity<List<LieuParkingResponse>> getListLieuParkingByGouvernorat(@PathVariable String gouvernorat) {
		List<LieuParkingResponse> lieuParkingResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_LIEUX_PARKING",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<LieuParkingDTO> lieuParkingDTOS = referentiel.getListLieuParkingByGouvernorat(gouvernorat);
			if (!lieuParkingDTOS.isEmpty()) {
				lieuParkingDTOS.forEach(lieuParkingDTO -> {
					lieuParkingResponses.add(modelMapper.map(lieuParkingDTO, LieuParkingResponse.class));
				});
			}
			return new ResponseEntity<>(lieuParkingResponses, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(lieuParkingResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/beneficiaire_emprunt")
	ResponseEntity<HttpStatus> addNewBeneficiaireEmprunt(
			@RequestBody BeneficiaireEmpruntRequest beneficiaireEmpruntRequest) {
		if (this.isUserAuthorised("ADD_BENEFICIAIRES_EMPRUNTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			BeneficiaireEmpruntDTO beneficiaireEmpruntDTO = modelMapper.map(beneficiaireEmpruntRequest,
					BeneficiaireEmpruntDTO.class);
			referentiel.addNewBeneficiaireEmprunt(beneficiaireEmpruntDTO);
			tracabiliteController.addNewBeneficiaireEmpruntTracabilite(beneficiaireEmpruntDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/beneficiaire_emprunt")
	ResponseEntity<HttpStatus> modifySelectedBeneficiaireEmprunt(
			@RequestBody BeneficiaireEmpruntRequest beneficiaireEmpruntRequest) {
		if (this.isUserAuthorised("MODIFY_BENEFICIAIRES_EMPRUNTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			BeneficiaireEmpruntDTO beneficiaireEmpruntDTO = modelMapper.map(beneficiaireEmpruntRequest,
					BeneficiaireEmpruntDTO.class);
			referentiel.modifySelectedBeneficiaireEmprunt(beneficiaireEmpruntDTO);
			tracabiliteController.modifySelectedBeneficiaireEmpruntTracabilite(beneficiaireEmpruntDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/beneficiaire_emprunt/{id}")
	ResponseEntity<HttpStatus> deleteSelectedBeneficiaireEmprunt(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_BENEFICIAIRES_EMPRUNTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String nomBeneficiaire = beneficiaireEmpruntRepository.findById(id).get().getNomBeneficiaire();
			String code = beneficiaireEmpruntRepository.findById(id).get().getCode();
			referentiel.deleteSelectedBeneficiaireEmprunt(id);
			tracabiliteController.deleteSelectedBeneficiaireEmpruntTracabilite(nomBeneficiaire, code,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/beneficiaire_emprunt")
	ResponseEntity<List<BeneficiaireEmpruntResponse>> getListBeneficiaireEmprunt(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit){
		List<BeneficiaireEmpruntResponse> beneficiaireEmpruntResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_BENEFICIAIRES_EMPRUNTS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<BeneficiaireEmpruntDTO> beneficiaireEmpruntDTOS = referentiel.getListBeneficiaireEmprunt(Integer.parseInt(page), Integer.parseInt(limit));
			if (!beneficiaireEmpruntDTOS.isEmpty()) {
				beneficiaireEmpruntDTOS.forEach(beneficiaireEmpruntDTO -> {
					beneficiaireEmpruntResponses
							.add(modelMapper.map(beneficiaireEmpruntDTO, BeneficiaireEmpruntResponse.class));
				});
			}
			return new ResponseEntity<>(beneficiaireEmpruntResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(beneficiaireEmpruntResponses, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(path = "/etat_stock")
	ResponseEntity<HttpStatus> addNewEtatStock(@RequestBody EtatStockRequest etatStockRequest) {
		if (this.isUserAuthorised("ADD_ETATS_STOCK",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			EtatStockDTO etatStockDTO = modelMapper.map(etatStockRequest, EtatStockDTO.class);
			referentiel.addNewEtatStock(etatStockDTO);
			tracabiliteController.addNewEtatStockTracabilite(etatStockDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/etat_stock")
	ResponseEntity<HttpStatus> modifySelectedEtatStock(@RequestBody EtatStockRequest etatStockRequest) {
		if (this.isUserAuthorised("MODIFY_ETATS_STOCK",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			EtatStockDTO etatStockDTO = modelMapper.map(etatStockRequest, EtatStockDTO.class);
			referentiel.modifySelectedEtatStock(etatStockDTO);
			tracabiliteController.modifySelectedEtatStockTracabilite(etatStockDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/etat_stock/{id}")
	ResponseEntity<HttpStatus> deleteSelectedEtatStock(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_ETATS_STOCK",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = etatStockRepository.findById(id).get().getArticle().getDesignation();
			String code = etatStockRepository.findById(id).get().getArticle().getCodeArticle();
			referentiel.deleteSelectedEtatStock(id);
			tracabiliteController.deleteSelectedEtatStockTracabilite(designation, code,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/etat_stock")
	ResponseEntity<List<EtatStockListResponse>> getListEtatStock(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<EtatStockListResponse> etatStockResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_ETATS_STOCK",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<EtatStockNewDTO> etatStockDTOS = referentiel.getListEtatStock(Integer.parseInt(page), Integer.parseInt(limit));
			if (!etatStockDTOS.isEmpty()) {
				etatStockDTOS.forEach(etatStockDTO -> {
					etatStockResponses.add(modelMapper.map(etatStockDTO, EtatStockListResponse.class));
				});
			}
			return new ResponseEntity<>(etatStockResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(etatStockResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/personnel")
	ResponseEntity<Long> addNewPersonnel(@RequestBody PersonnelRequest personnelRequest) {
		if (this.isUserAuthorised("ADD_DETAILS_PERSONNELS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			PersonnelDTO personnelDTO = modelMapper.map(personnelRequest, PersonnelDTO.class);
			Long idPersonnel = referentiel.addNewPersonnel(personnelDTO);
			tracabiliteController.addNewPersonnelTracabilite(personnelDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(idPersonnel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/image_personnel/{id}")
	private ResponseEntity<Long> uploadImagePersonnel(@PathVariable Long id, @RequestParam MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
		File serverFile = new File(
				"src/main/resources/image_personnel/" + File.separator + id + File.separator + newFileName);
		try {
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			referentiel.uploadImagePersonnel(id, file.getOriginalFilename(),
					"http://localhost:8080/api/image_personnel/" + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/personnel")
	ResponseEntity<Long> modifySelectedPersonnel(@RequestBody PersonnelRequest personnelRequest) {
		if (this.isUserAuthorised("MODIFY_DETAILS_PERSONNELS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			PersonnelDTO personnelDTO = modelMapper.map(personnelRequest, PersonnelDTO.class);
			Long idPersonnel = referentiel.modifySelectedPersonnel(personnelDTO);
			tracabiliteController.modifySelectedPersonnelTracabilite(personnelDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(idPersonnel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/image_personnel/{imageName}")
	private ResponseEntity<InputStreamResource> getByteImagePersonnel(@PathVariable String imageName)
			throws IOException {
		ClassPathResource imgFile = new ClassPathResource("/image_personnel/" + imageName);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(imgFile.getInputStream()));
	}

	@DeleteMapping(path = "/personnel/{id}")
	private ResponseEntity<HttpStatus> deleteSelectedPersonnel(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_DETAILS_PERSONNELS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String immatriculationUnique = personnelRepository.findById(id).get().getImmatriculationUnique();
			String nom = personnelRepository.findById(id).get().getNom();
			String prenom = personnelRepository.findById(id).get().getPrenom();
			referentiel.deleteSelectedPersonnel(id);
			tracabiliteController.deleteSelectedPersonnelTracabilite(immatriculationUnique, nom, prenom,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/personnel")
	private ResponseEntity<List<PersonnelResponse>> getListPersonnel(@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<PersonnelResponse> personnelResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_DETAILS_PERSONNELS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<PersonnelDTO> personnelDTOS = referentiel.getListPersonnel(Integer.parseInt(page), Integer.parseInt(limit));
			if (!personnelDTOS.isEmpty()) {
				personnelDTOS.forEach(personnelDTO -> {
					personnelResponses.add(modelMapper.map(personnelDTO, PersonnelResponse.class));
				});
			}
			return new ResponseEntity<>(personnelResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(personnelResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/child_structure/{idParent}")
	ResponseEntity<HttpStatus> addNewChildStructure(@PathVariable Long idParent,
			@RequestBody StructureRequest structureRequest) {
		if (this.isUserAuthorised("ADD_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			StructureDTO structureDTO = modelMapper.map(structureRequest, StructureDTO.class);
			referentiel.addNewChildStructure(structureDTO, idParent);
			tracabiliteController.addNewChildStructureTracabilite(structureDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/parent_structure")
	ResponseEntity<HttpStatus> addNewParentStructure(@RequestBody StructureRequest structureRequest) {
		if (this.isUserAuthorised("ADD_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			StructureDTO structureDTO = modelMapper.map(structureRequest, StructureDTO.class);
			referentiel.addNewParentStructure(structureDTO);
			tracabiliteController.addNewChildStructureTracabilite(structureDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/structure")
	ResponseEntity<HttpStatus> modifySelectedStructure(@RequestBody StructureRequest structureRequest) {
		if (this.isUserAuthorised("MODIFY_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			StructureDTO structureDTO = modelMapper.map(structureRequest, StructureDTO.class);
			referentiel.modifySelectedStructure(structureDTO);
			tracabiliteController.modifySelectedStructureTracabilite(structureDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/structure/{id}")
	ResponseEntity<HttpStatus> deleteSelectedStructure(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = structureRepository.findById(id).get().getCode();
			String designation = structureRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedStructure(id);
			tracabiliteController.deleteSelectedStructureTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/structure")
	ResponseEntity<List<StructureResponse>> getListStructure() {
		List<StructureResponse> structureResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<StructureDTO> structureDTOS = referentiel.getListStructure();
			if (!structureDTOS.isEmpty()) {
				structureDTOS.forEach(structureDTO -> {
					if (structureDTO.getStructureMere() == null) {
						structureResponses.add(modelMapper.map(structureDTO, StructureResponse.class));
					}
				});
			}
			return new ResponseEntity<>(structureResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(structureResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	// 10_07_2021
	@GetMapping("/structure/{id}")
	public Optional<StructureEntity> getPost(@PathVariable Long id) {
		return structureRepository.findById(id);
	}

	@GetMapping("/Sous_Famille")
	public Iterable<SousFamilleArticleEntity> getSousFamille() {
		return SousFamilleArticleRepository.findAll();
	}

	@PostMapping(path = "/magasin/{idUGP}")
	ResponseEntity<HttpStatus> addNewMagasin(@PathVariable Long idUGP, @RequestBody MagasinRequest magasinRequest) {
		if (this.isUserAuthorised("ADD_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			MagasinDTO magasinDTO = modelMapper.map(magasinRequest, MagasinDTO.class);
			referentiel.addNewMagasin(magasinDTO, idUGP);
			tracabiliteController.addNewMagasinTracabilite(magasinDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/magasin")
	ResponseEntity<HttpStatus> modifySelectedMagasin(@RequestBody MagasinRequest magasinRequest) {
		if (this.isUserAuthorised("MODIFY_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			MagasinDTO magasinDTO = modelMapper.map(magasinRequest, MagasinDTO.class);
			referentiel.modifySelectedMagasin(magasinDTO);
			tracabiliteController.modifySelectedMagasinTracabilite(magasinDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/magasin/{id}")
	ResponseEntity<HttpStatus> deleteSelectedMagasin(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = magasinRepository.findById(id).get().getCode();
			String designation = magasinRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedMagasin(id);
			tracabiliteController.deleteSelectedMagasinTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/atelier/{idUGP}")
	ResponseEntity<HttpStatus> addNewAtelier(@RequestBody AtelierRequest atelierRequest, @PathVariable Long idUGP) {
		if (this.isUserAuthorised("ADD_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			AtelierDTO atelierDTO = modelMapper.map(atelierRequest, AtelierDTO.class);
			referentiel.addNewAtelier(atelierDTO, idUGP);
			tracabiliteController.addNewAtelierTracabilite(atelierDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/atelier")
	ResponseEntity<HttpStatus> modifySelectedAtelier(@RequestBody AtelierRequest atelierRequest) {
		if (this.isUserAuthorised("MODIFY_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			AtelierDTO atelierDTO = modelMapper.map(atelierRequest, AtelierDTO.class);
			referentiel.modifySelectedAtelier(atelierDTO);
			tracabiliteController.modifySelectedAtelierTracabilite(atelierDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/atelier/{id}")
	ResponseEntity<HttpStatus> deleteSelectedAtelier(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = atelierRepository.findById(id).get().getCode();
			String designation = atelierRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedAtelier(id);
			tracabiliteController.deleteSelectedAtelierTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/ressource/{idUGP}")
	ResponseEntity<HttpStatus> addNewRessource(@RequestBody RessourceRequest ressourceRequest,
			@PathVariable Long idUGP) {
		if (this.isUserAuthorised("ADD_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			RessourceDTO ressourceDTO = modelMapper.map(ressourceRequest, RessourceDTO.class);
			referentiel.addNewRessource(ressourceDTO, idUGP);
			tracabiliteController.addNewRessourceTracabilite(ressourceDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/ressource")
	ResponseEntity<HttpStatus> modifySelectedRessource(@RequestBody RessourceRequest ressourceRequest) {
		if (this.isUserAuthorised("MODIFY_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			RessourceDTO ressourceDTO = modelMapper.map(ressourceRequest, RessourceDTO.class);
			referentiel.modifySelectedRessource(ressourceDTO);
			tracabiliteController.modifySelectedRessourceTracabilite(ressourceDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/ressource/{id}")
	ResponseEntity<HttpStatus> deleteSelectedRessource(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String designation = ressourceRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedRessource(id);
			tracabiliteController.deleteSelectedRessourceTracabilite(designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/section/{parent}/{id}")
	ResponseEntity<HttpStatus> addNewSection(@RequestBody SectionRequest sectionRequest, @PathVariable String parent,
			@PathVariable Long id) {
		if (this.isUserAuthorised("ADD_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			SectionDTO sectionDTO = modelMapper.map(sectionRequest, SectionDTO.class);
			referentiel.addNewSection(sectionDTO, parent, id);
			tracabiliteController.addNewSectionTracabilite(sectionDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/section")
	ResponseEntity<HttpStatus> modifySelectedSection(@RequestBody SectionRequest sectionRequest) {
		if (this.isUserAuthorised("MODIFY_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			SectionDTO sectionDTO = modelMapper.map(sectionRequest, SectionDTO.class);
			tracabiliteController.modifySelectedSectionTracabilite(sectionDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			referentiel.modifySelectedSection(sectionDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/section/{id}")
	ResponseEntity<HttpStatus> deleteSelectedSection(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_STRUCTURE_ADMINISTRATIVE",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = sectionRepository.findById(id).get().getCode();
			String designation = sectionRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedSection(id);
			tracabiliteController.deleteSelectedSectionTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/ugp")
	ResponseEntity<HttpStatus> addNewUGP(@RequestBody UgpRequest ugpRequest) {
		if (this.isUserAuthorised("ADD_UNITE_GESTION_PARC",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			UgpDTO ugpDTO = modelMapper.map(ugpRequest, UgpDTO.class);
			referentiel.addNewUGP(ugpDTO);
			tracabiliteController.addNewUGPreTracabilite(ugpDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/ugp")
	ResponseEntity<HttpStatus> modifySelectedUGP(@RequestBody UgpRequest ugpRequest) {
		if (this.isUserAuthorised("MODIFY_UNITE_GESTION_PARC",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			UgpDTO ugpDTO = modelMapper.map(ugpRequest, UgpDTO.class);
			referentiel.modifySelectedUGP(ugpDTO);
			tracabiliteController.modifySelectedUGPTracabilite(ugpDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/ugp/{id}")
	ResponseEntity<HttpStatus> deleteSelectedUGP(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_UNITE_GESTION_PARC",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = ugpRepository.findById(id).get().getCode();
			String designation = ugpRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedUGP(id);
			tracabiliteController.deleteSelectedUGPTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/ugp")
	ResponseEntity<List<UgpResponse>> getListUGP() {
		List<UgpResponse> ugpResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_UNITE_GESTION_PARC",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<UgpDTO> ugpDTOS = referentiel.getListUGP();
			if (!ugpDTOS.isEmpty()) {
				ugpDTOS.forEach(ugpDTO -> {
					ugpResponses.add(modelMapper.map(ugpDTO, UgpResponse.class));
				});
			}
			return new ResponseEntity<>(ugpResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ugpResponses, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/programme_entretiens_preventifs")
	ResponseEntity<HttpStatus> addNewProgrammeEntretiensPreventifs(
			@RequestBody ProgrammeEntretiensPreventifsRequest programmeEntretiensPreventifsRequest) {
		if (this.isUserAuthorised("ADD_PROGRAMMES_ENTRETIENS_PREVENTIFS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO = modelMapper
					.map(programmeEntretiensPreventifsRequest, ProgrammeEntretiensPreventifsDTO.class);
			referentiel.addNewProgrammeEntretiensPreventifs(programmeEntretiensPreventifsDTO);
			tracabiliteController.addNewProgrammeEntretiensPreventifsTracabilite(programmeEntretiensPreventifsDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/programme_entretiens_preventifs")
	ResponseEntity<HttpStatus> modifySelectedProgrammeEntretiensPreventifs(
			@RequestBody ProgrammeEntretiensPreventifsRequest programmeEntretiensPreventifsRequest) {
		if (this.isUserAuthorised("MODIFY_PROGRAMMES_ENTRETIENS_PREVENTIFS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO = modelMapper
					.map(programmeEntretiensPreventifsRequest, ProgrammeEntretiensPreventifsDTO.class);
			referentiel.modifySelectedProgrammeEntretiensPreventifs(programmeEntretiensPreventifsDTO);
			tracabiliteController.modifySelectedProgrammeEntretiensPreventifsTracabilite(
					programmeEntretiensPreventifsDTO,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(path = "/programme_entretiens_preventifs/{id}")
	ResponseEntity<HttpStatus> deleteSelectedProgrammeEntretiensPreventifs(@PathVariable Long id) {
		if (this.isUserAuthorised("DELETE_PROGRAMMES_ENTRETIENS_PREVENTIFS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			String code = programmeEntretiensPreventifsRepository.findById(id).get().getCode();
			String designation = programmeEntretiensPreventifsRepository.findById(id).get().getDesignation();
			referentiel.deleteSelectedProgrammeEntretiensPreventifs(id);
			tracabiliteController.deleteSelectedProgrammeEntretiensPreventifsTracabilite(code, designation,
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(path = "/programme_entretiens_preventifs")
	ResponseEntity<List<ProgrammeEntretiensPreventifsResponse>> getListProgrammeEntretiensPreventifs(
			@RequestParam(name = "marque") String marque, @RequestParam(name = "type") String type,@RequestParam(name = "page") String page, 
			@RequestParam(name = "limit") String limit) {
		List<ProgrammeEntretiensPreventifsResponse> programmeEntretiensPreventifsResponses = new ArrayList<>();
		if (this.isUserAuthorised("VIEW_PROGRAMMES_ENTRETIENS_PREVENTIFS",
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
			ModelMapper modelMapper = new ModelMapper();
			List<ProgrammeEntretiensPreventifsDTO> programmeEntretiensPreventifsDTOS = referentiel
					.getListProgrammeEntretiensPreventifs(marque, type,Integer.parseInt(page), Integer.parseInt(limit));
			if (!programmeEntretiensPreventifsDTOS.isEmpty()) {
				programmeEntretiensPreventifsDTOS.forEach(programmeEntretiensPreventifsDTO -> {
					programmeEntretiensPreventifsResponses.add(modelMapper.map(programmeEntretiensPreventifsDTO,
							ProgrammeEntretiensPreventifsResponse.class));
				});
			}
			return new ResponseEntity<>(programmeEntretiensPreventifsResponses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping(path = "/article_for_regulation")
	void UpdateArticleForRegulation(@RequestBody ModifyArticleRegulationRequest modifyArticleRegulationRequest) {
		ModelMapper modelMapper = new ModelMapper();
		ModifyArticleRegulationDTO modifyArticleRegulationDTO = modelMapper.map(modifyArticleRegulationRequest,
				ModifyArticleRegulationDTO.class);

		referentiel.UpdateArticleForRegulation(modifyArticleRegulationDTO);

	}

	// list des article filtrer par sous famille

	@GetMapping(path = "/list_article_Filtre_Sous/{Sousfamille}")
	private List<ArticleEntity> getListArticleBySousFamille(@PathVariable String Sousfamille) {
		return articleRepository.findArticleBySousFamille(Sousfamille);
	}

	// list des article filtrer par ugp

	
	@GetMapping(path="/listeDesArticles/{id}")
	private List<ArticleEntity> lisArticles(@PathVariable Long id){
		return articleRepository.findByUgpId(id);
		}
	
	// list des article filtrer par famille
	@GetMapping(path = "/list_article_Filtre_Famille/{famille}")
	private List<ArticleEntity> getListArticleByFamille(@PathVariable String famille) {
		return articleRepository.findArticleByFamille(famille);
	}

	// list des article filtrer par famille and sous famille
	@GetMapping(path = "/list_article_Filtre_Famille_sous/{famille}/{sousfamille}")
	private List<ArticleEntity> getListArticleByFamilleSousFamille(@PathVariable String famille,
			@PathVariable String sousfamille) {
		return articleRepository.findArticleByFamilleSousFamille(famille, sousfamille);
	}

	// list des article filtrer par famille and genre
	@GetMapping(path = "/list_article_Filtre_Famille_genre/{famille}/{genre}")
	private List<ArticleEntity> getListArticleByFamilleGenre(@PathVariable String famille, @PathVariable String genre) {
		return articleRepository.findArticleByFamilleGenre(famille, genre);
	}

	// list des article filtrer par famille and Marque
	@GetMapping(path = "/list_article_Filtre_Famille_marque/{famille}/{marque}")
	private List<ArticleEntity> getListArticleByFamilleMarque(@PathVariable String famille,
			@PathVariable String marque) {
		return articleRepository.findArticleByFamilleMarque(famille, marque);
	}

	// list des article filtrer par famille and type
	@GetMapping(path = "/list_article_Filtre_Famille_type/{famille}/{type}")
	private List<ArticleEntity> getListArticleByFamilleType(@PathVariable String famille, @PathVariable String type) {
		return articleRepository.findArticleByFamilleType(famille, type);
	}

	// list des article filtrer par Marque
	@GetMapping(path = "/list_article_Filtre_marque/{marque}")
	private List<ArticleEntity> getdArticleByMarque(@PathVariable String marque) {
		return articleRepository.findArticleByMarque(marque);
	}

	// list des article filtrer par Genre
	@GetMapping(path = "/list_article_Filtre_Sous_genre/{genre}")
	private List<ArticleEntity> getdArticleByGenre(@PathVariable String genre) {
		return articleRepository.findArticleByGenre(genre);
	}

	// list des article filtrer par type
	@GetMapping(path = "/list_article_filtre_type/{type}")
	private List<ArticleEntity> getdArticleByType(@PathVariable String type) {
		return articleRepository.findArticleByType(type);
	}

	// list des article filtrer par genre ET SOUS famille
	@GetMapping(path = "/list_article_Filtre_genre_Sous/{genre}/{sousFamille}")
	private List<ArticleEntity> getdArticleByGenreSousFamille(@PathVariable String genre,
			@PathVariable String sousFamille) {
		return articleRepository.findArticleByGenreSousFamille(genre, sousFamille);
	}

	// list des article filtrer par genre et marque
	@GetMapping(path = "/list_article_Filtre_genre_marque/{genre}/{marque}")
	private List<ArticleEntity> getdArticleByGenreMarque(@PathVariable String genre, @PathVariable String marque) {
		return articleRepository.findArticleByGenreMarque(genre, marque);
	}

	// list des article filtrer par genre et type
	@GetMapping(path = "/list_article_Filtre_genre_type/{genre}/{type}")
	private List<ArticleEntity> getdArticleByGenreType(@PathVariable String genre, @PathVariable String type) {
		return articleRepository.findArticleByGenreType(genre, type);
	}

	// list des article filtrer par sousFamille et marque
	@GetMapping(path = "/list_article_Filtre_sous_marque/{sousFamille}/{marque}")
	private List<ArticleEntity> getdArticleBySousFamilleMarque(@PathVariable String sousFamille,
			@PathVariable String marque) {
		return articleRepository.findArticleBySousFamilleMarque(sousFamille, marque);
	}

	// list des article filtrer par sousFamille et type
	@GetMapping(path = "/list_article_Filtre_sous_type/{sousFamille}/{type}")
	private List<ArticleEntity> getdArticleBySousFamilleType(@PathVariable String sousFamille,
			@PathVariable String type) {
		return articleRepository.findArticleBySousFamilleType(sousFamille, type);
	}

	// list des article filtrer par type et marque
	@GetMapping(path = "/list_article_Filtre_type_marque/{type}/{marque}")
	private List<ArticleEntity> getdArticleByTypeMarque(@PathVariable String type, @PathVariable String marque) {
		return articleRepository.findArticleByTypeMarque(type, marque);
	}

	// list des article filtrer par sousFamille et marque et genre
	@GetMapping(path = "/list_article_Filtre_sous_marque/{sousFamille}/{marque}/{genre}")
	private List<ArticleEntity> getdArticleBySousFamilleMarqueGenre(@PathVariable String sousFamille,
			@PathVariable String marque, @PathVariable String genre) {
		return articleRepository.findArticleBySousFamilleMarqueGenre(sousFamille, marque, genre);
	}

	// list des article filtrer par sousFamille et marque et type
	@GetMapping(path = "/list_article_Filtre_sous_marque_type/{sousFamille}/{marque}/{type}")
	private List<ArticleEntity> getdArticleBySousFamilleMarqueType(@PathVariable String sousFamille,
			@PathVariable String marque, @PathVariable String type) {
		return articleRepository.findArticleBySousFamilleMarqueType(sousFamille, marque, type);
	}

	// list des article filtrer par sousFamille et type et genre
	@GetMapping(path = "/list_article_Filtre_sous_type_genre/{sousFamille}/{type}/{genre}")
	private List<ArticleEntity> getdArticleBySousFamilleTypeGenre(@PathVariable String sousFamille,
			@PathVariable String type, @PathVariable String genre) {
		return articleRepository.findArticleBySousFamilleTypeGenre(sousFamille, type, genre);
	}

	// list des article filtrer par type et marque et genre
	@GetMapping(path = "/list_article_Filtre_genre_marque_type/{genre}/{marque}/{type}")
	private List<ArticleEntity> getdArticleByGenreMarqueType(@PathVariable String genre, @PathVariable String marque,
			@PathVariable String type) {
		return articleRepository.findArticleByGenreMarqueType(genre, marque, type);
	}

	// list des article filtrer par tout
	@GetMapping(path = "/list_article_Filtre_tout/{sousFamille}/{marque}/{genre}/{type}")
	private List<ArticleEntity> getdArticleByAll(@PathVariable String sousFamille, @PathVariable String marque,
			@PathVariable String genre, @PathVariable String type) {
		return articleRepository.findArticleByTouts(sousFamille, marque, genre, type);
	}
    
	@GetMapping(path = "/list_nombre_alert_article")
	public Long getAlertArticleNombre() {
		return articleRepository.findNombreArticleForAlerting();
	}
    
	@GetMapping(path = "/testtesttets/{id}")
	public List<ArticlePricesEntity> testets(@PathVariable Long id) {
		return articlePricesRepository.findByAritcleidDistinct(id);
	}
	@GetMapping(path = "/list_alert_article")
	List<ArticleEntity> getAlertArticle(@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleEntity> parcEntity = new ArrayList<>();
		List<ArticleEntity> parcDTOS = referentiel.getAlertArticle(Integer.parseInt(page), Integer.parseInt(limit));
		if (!parcDTOS.isEmpty()) {
			parcDTOS.forEach(parcDTO -> {
				parcEntity.add(modelMapper.map(parcDTO, ArticleEntity.class));
			});
		}
		return parcEntity;
	}

}
