package com.gesparc.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gesparc.entities.achat.MarcheEntity;
import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.cartePlafond.RechargeQuotaMensuelEntity;
import com.gesparc.entities.referentiel.*;
import com.gesparc.entities.stock.MagasinRotationNull;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.RetourStructure;
import com.gesparc.repositories.achat.BonCommandeRepository;
import com.gesparc.repositories.achat.MarcheRepository;
import com.gesparc.repositories.referentiel.*;
import com.gesparc.repositories.stock.RegulationArticleRepository;
import com.gesparc.repositories.stock.RetourStructureRepository;
import com.gesparc.services.Referentiel;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.MarcheDTO;
import com.gesparc.sharedDTO.carburant.RechargeQuotaMensuelDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CarteAgilisCashTabDataDTO;
import com.gesparc.sharedDTO.referentiel.*;
import com.gesparc.sharedDTO.referentiel.additionnel.ListFamilleAndSousFamilleDTO;
import com.gesparc.sharedDTO.referentiel.additionnel.ListSousFamilleDTO;
import com.gesparc.sharedDTO.stock.MagasinRotationNullDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableAutoConfiguration
@Service
public class ReferentielImpl implements Referentiel {

	private ArticleRepository articleRepository;

	private FamilleArticleRepository familleArticleRepository;

	private SousFamilleArticleRepository sousFamilleArticleRepository;

	private TypeVehiculeRepository typeVehiculeRepository;

	private MarqueVehiculeRepository marqueVehiculeRepository;

	private GenreVehiculeRepository genreVehiculeRepository;

	private MarcheRepository marcheRepository;

	private FamilleOperationReparationRepository familleOperationReparationRepository;

	private UsageVehiculeRepository usageVehiculeRepository;

	private CauseSinistreRepository causeSinistreRepository;

	private OperationReparationRepository operationReparationRepository;

	private FournisseurRepository fournisseurRepository;

	private GouvernoratRepository gouvernoratRepository;

	private StationPeageReposiroty stationPeageReposiroty;

	private ExpertRepository expertRepository;

	private LieuParkingRepository lieuParkingRepository;

	private BeneficiaireEmpruntRepository beneficiaireEmpruntRepository;

	private EtatStockRepository etatStockRepository;

	private PersonnelRepository personnelRepository;

	private UgpRepository ugpRepository;

	private StructureRepository structureRepository;

	private AtelierRepository atelierRepository;

	private MagasinRepository magasinRepository;

	private SectionRepository sectionRepository;

	private RessourceRepository ressourceRepository;

	private ProgrammeEntretiensPreventifsRepository programmeEntretiensPreventifsRepository;

	private EnergieRepository energieRepository;

	private AnneeRepository anneeRepository;

	private TvaRepository tvaRepository;

	private UniteRepository uniteRepository;

	private BonCommandeRepository bonCommandeRepository;

	public ReferentielImpl(ArticleRepository articleRepository, FamilleArticleRepository familleArticleRepository,
			SousFamilleArticleRepository sousFamilleArticleRepository, TypeVehiculeRepository typeVehiculeRepository,
			MarqueVehiculeRepository marqueVehiculeRepository, GenreVehiculeRepository genreVehiculeRepository,
			MarcheRepository marcheRepository,
			FamilleOperationReparationRepository familleOperationReparationRepository,
			UsageVehiculeRepository usageVehiculeRepository, CauseSinistreRepository causeSinistreRepository,
			OperationReparationRepository operationReparationRepository, FournisseurRepository fournisseurRepository,
			GouvernoratRepository gouvernoratRepository, StationPeageReposiroty stationPeageReposiroty,
			ExpertRepository expertRepository, LieuParkingRepository lieuParkingRepository,
			BeneficiaireEmpruntRepository beneficiaireEmpruntRepository, EtatStockRepository etatStockRepository,
			PersonnelRepository personnelRepository, UgpRepository ugpRepository,
			StructureRepository structureRepository, AtelierRepository atelierRepository,
			MagasinRepository magasinRepository, SectionRepository sectionRepository,
			RessourceRepository ressourceRepository,
			ProgrammeEntretiensPreventifsRepository programmeEntretiensPreventifsRepository,
			EnergieRepository energieRepository, AnneeRepository anneeRepository, TvaRepository tvaRepository,
			UniteRepository uniteRepository) {
		this.articleRepository = articleRepository;
		this.familleArticleRepository = familleArticleRepository;
		this.sousFamilleArticleRepository = sousFamilleArticleRepository;
		this.typeVehiculeRepository = typeVehiculeRepository;
		this.marqueVehiculeRepository = marqueVehiculeRepository;
		this.genreVehiculeRepository = genreVehiculeRepository;
		this.marcheRepository = marcheRepository;
		this.familleOperationReparationRepository = familleOperationReparationRepository;
		this.usageVehiculeRepository = usageVehiculeRepository;
		this.causeSinistreRepository = causeSinistreRepository;
		this.operationReparationRepository = operationReparationRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.gouvernoratRepository = gouvernoratRepository;
		this.stationPeageReposiroty = stationPeageReposiroty;
		this.expertRepository = expertRepository;
		this.lieuParkingRepository = lieuParkingRepository;
		this.beneficiaireEmpruntRepository = beneficiaireEmpruntRepository;
		this.etatStockRepository = etatStockRepository;
		this.personnelRepository = personnelRepository;
		this.ugpRepository = ugpRepository;
		this.structureRepository = structureRepository;
		this.atelierRepository = atelierRepository;
		this.magasinRepository = magasinRepository;
		this.sectionRepository = sectionRepository;
		this.ressourceRepository = ressourceRepository;
		this.programmeEntretiensPreventifsRepository = programmeEntretiensPreventifsRepository;
		this.energieRepository = energieRepository;
		this.anneeRepository = anneeRepository;
		this.tvaRepository = tvaRepository;
		this.uniteRepository = uniteRepository;
	}

	@Override
	public void addNewFamilleArticle(FamilleArticleDTO familleArticleDTO) {
		ModelMapper modelMapper = new ModelMapper();
		FamilleArticleEntity familleArticleEntity = modelMapper.map(familleArticleDTO, FamilleArticleEntity.class);
		familleArticleEntity.setDateAjout(LocalDate.now());
		familleArticleRepository.save(familleArticleEntity);
	}

	@Override
	public void addNewSousFamilleArticle(SousFamilleArticleDTO sousFamilleArticleDTO) {
		ModelMapper modelMapper = new ModelMapper();
		SousFamilleArticleEntity sousFamilleArticleEntity = modelMapper.map(sousFamilleArticleDTO,
				SousFamilleArticleEntity.class);
		sousFamilleArticleEntity.setDateAjout(LocalDate.now());
		if (sousFamilleArticleEntity.getFamille().getSousFamilles() == null) {
			sousFamilleArticleEntity.getFamille().setSousFamilles(new ArrayList<>());
		}
		sousFamilleArticleEntity.getFamille().getSousFamilles().add(sousFamilleArticleEntity);
		sousFamilleArticleRepository.save(sousFamilleArticleEntity);
	}

	@Override
	public void deleteSelectedFamilleArticle(Long idFamille) {
		familleArticleRepository.deleteById(idFamille);
	}

	@Override
	public void deleteSelectedSousFamilleArticle(Long idSousFamille) {
		sousFamilleArticleRepository.deleteById(idSousFamille);
	}

	@Override
	public List<ArticleDTO> getPaginationListArticle(int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleDTO> articleDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
		List<ArticleEntity> articleEntities = articleEntityPage.getContent();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				articleDTOS.add(modelMapper.map(articleEntity, ArticleDTO.class));
			});
		}
		return articleDTOS;
	}
	
	@Override
	public List<SousFamilleArticleDTO> getPaginationListSousFamilletArticle(int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<SousFamilleArticleDTO> articleDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<SousFamilleArticleEntity> articleEntityPage = sousFamilleArticleRepository.findAll(pageable);
		List<SousFamilleArticleEntity> articleEntities = articleEntityPage.getContent();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				articleDTOS.add(modelMapper.map(articleEntity, SousFamilleArticleDTO.class));
			});
		}
		return articleDTOS;
	}

	@Override
	public List<GouvernoratDTO> getPaginationListGouvernorat(int page, int limit) {
	ModelMapper modelMapper = new ModelMapper();
	List<GouvernoratDTO> gouvernoratDTOs = new ArrayList<>();
	PageRequest pageable = PageRequest.of(page, limit);
	Page<GouvernoratEntity> GVRTEntityPage = gouvernoratRepository.findAll(pageable);
	List<GouvernoratEntity> GVRTEntities = GVRTEntityPage.getContent();
	if (!GVRTEntities.isEmpty()) {
	GVRTEntities.forEach(gvrtEntity -> {
	gouvernoratDTOs.add(modelMapper.map(gvrtEntity, GouvernoratDTO.class));
	});
	}
	return gouvernoratDTOs;
	}

	@Override
	public List<TvaDTO> getPaginationListTva(int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<TvaDTO> articleDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<TvaEntity> articleEntityPage = tvaRepository.findAll(pageable);
		List<TvaEntity> articleEntities = articleEntityPage.getContent();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				articleDTOS.add(modelMapper.map(articleEntity, TvaDTO.class));
			});
		}
		return articleDTOS;
	}
	@Override
	public List<FamilleArticleDTO> getPaginationListFamilletArticle(int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<FamilleArticleDTO> articleDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<FamilleArticleEntity> articleEntityPage = familleArticleRepository.findAll(pageable);
		List<FamilleArticleEntity> articleEntities = articleEntityPage.getContent();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				articleDTOS.add(modelMapper.map(articleEntity, FamilleArticleDTO.class));
			});
		}
		return articleDTOS;
	}
	
	
	@Override
	public List<EnergieDTO> getPaginationListEnergie(int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<EnergieDTO> articleDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<FamilleArticleEntity> articleEntityPage = familleArticleRepository.findAll(pageable);
		List<FamilleArticleEntity> articleEntities = articleEntityPage.getContent();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				articleDTOS.add(modelMapper.map(articleEntity, EnergieDTO.class));
			});
		}
		return articleDTOS;
	}
	@Override
	public Long getTotalItemArticleBySelected() {
		PageRequest pageable = PageRequest.of(0, 8);
		Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
		return articleEntityPage.getTotalElements();
	}

	@Override
	public void createNewArticle(UpdateArticleDTO updateArticleDTO) {
		ArticleEntity articleEntity = new ArticleEntity();
		SousFamilleArticleEntity sousFamilleArticleEntity = sousFamilleArticleRepository
				.findById(updateArticleDTO.getIdSousFamille()).get();
		MarqueVehiculeEntity marqueVehiculeEntity = marqueVehiculeRepository
				.findById(updateArticleDTO.getIdMarqueVehicule()).get();
		TypeVehiculeEntity typeVehiculeEntity = typeVehiculeRepository.findById(updateArticleDTO.getIdTypeVehicule())
				.get();
		GenreVehiculeEntity genreVehiculeEntity = genreVehiculeRepository
				.findById(updateArticleDTO.getIdGenreVehicule()).get();
		UgpEntity ugpEntity = ugpRepository.findById(updateArticleDTO.getIdUgp()).get();
		articleEntity.setDesignation(updateArticleDTO.getDesignation());
		articleEntity.setCodeArticle(updateArticleDTO.getCodeArticle());
		articleEntity.setQuantiteStock(updateArticleDTO.getQuantiteStock());
		articleEntity.setPrix(updateArticleDTO.getPrix());
		articleEntity.setDateAjout(updateArticleDTO.getDateAjout());
		articleEntity.setAlertStock(updateArticleDTO.getAlertStock());
		articleEntity.setTva(updateArticleDTO.getTva());
		articleEntity.setRemise(updateArticleDTO.getRemise());
		articleEntity.setQuantiteLivree(updateArticleDTO.getQuantiteLivree());

		if (ugpEntity.getArticles().isEmpty()) {
			ugpEntity.setArticles(new ArrayList<>());
		}
		ugpEntity.getArticles().add(articleEntity);
		articleEntity.setUgp(ugpEntity);

		if (sousFamilleArticleEntity.getArticles() == null) {
			sousFamilleArticleEntity.setArticles(new ArrayList<>());
		}
		sousFamilleArticleEntity.getArticles().add(articleEntity);
		articleEntity.setSousFamille(sousFamilleArticleEntity);

		if (marqueVehiculeEntity.getArticles() == null) {
			marqueVehiculeEntity.setArticles(new ArrayList<>());
		}
		marqueVehiculeEntity.getArticles().add(articleEntity);
		articleEntity.setMarqueVehicule(marqueVehiculeEntity);

		if (typeVehiculeEntity.getArticles() == null) {
			typeVehiculeEntity.setArticles(new ArrayList<>());
		}
		typeVehiculeEntity.getArticles().add(articleEntity);
		articleEntity.setTypeVehicule(typeVehiculeEntity);

		if (genreVehiculeEntity.getArticles() == null) {
			genreVehiculeEntity.setArticles(new ArrayList<>());
		}
		genreVehiculeEntity.getArticles().add(articleEntity);
		articleEntity.setGenreVehicule(genreVehiculeEntity);

		articleRepository.save(articleEntity);
	}

	@Override
	public Long getTotalNumberArticleByMarqueVehicule(MarqueVehiculeEntity marqueVehicule) {
		if (marqueVehicule.equals("tousTypes")) {
			return articleRepository.getTotalNumberMarqueVehicule();
		} else {
			return articleRepository.getTotalNumberArticleByMarqueVehicule(marqueVehicule);
		}
	}

	@Override
	public Long getTotalNumberArticleByGenreVehicule(GenreVehiculeEntity genreVehicule) {
		if (genreVehicule.equals("tousTypes")) {
			return articleRepository.getTotalNumberGenreVehicule();
		} else {
			return articleRepository.getTotalNumberArticleByGenreVehicule(genreVehicule);
		}
	}

	@Override
	public Long getTotalNumberArticleByTypeVehicule(TypeVehiculeEntity typeVehicule) {
		if (typeVehicule.equals("tousTypes")) {
			return articleRepository.getTotalNumberTypeVehicule();
		} else {
			return articleRepository.getTotalNumberArticleByTypeVehicule(typeVehicule);
		}
	}

	@Override
	public List<ArticleDTO> getPaginationArticleByMarqueVehicule(int page, int limit,
			MarqueVehiculeEntity marqueVehicule) {
		List<ArticleEntity> articleEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (marqueVehicule.equals("tousTypes")) {
			Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
			articleEntities = articleEntityPage.getContent();
		} else {
			Page<ArticleEntity> articleEntityPage = articleRepository.findAllByMarqueVehicule(marqueVehicule, pageable);
			articleEntities = articleEntityPage.getContent();
		}
		return this.loadArticleDTO(articleEntities);
	}

	@Override
	public List<ArticleDTO> getPaginationArticleByGenreVehicule(int page, int limit,
			GenreVehiculeEntity genreVehicule) {
		List<ArticleEntity> articleEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (genreVehicule.equals("tousTypes")) {
			Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
			articleEntities = articleEntityPage.getContent();
		} else {
			Page<ArticleEntity> articleEntityPage = articleRepository.findAllByGenreVehicule(genreVehicule, pageable);
			articleEntities = articleEntityPage.getContent();
		}
		return this.loadArticleDTO(articleEntities);
	}

	@Override

	public List<ArticleDTO> getPaginationArticleByTypeVehicule(int page, int limit, TypeVehiculeEntity typeVehicule) {
		List<ArticleEntity> articleEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (typeVehicule.equals("tousTypes")) {
			Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
			articleEntities = articleEntityPage.getContent();
		} else {
			Page<ArticleEntity> articleEntityPage = articleRepository.findAllByTypeVehicule(typeVehicule, pageable);
			articleEntities = articleEntityPage.getContent();
		}
		return this.loadArticleDTO(articleEntities);
	}

	private List<ArticleDTO> loadArticleDTO(List<ArticleEntity> articleEntities) {
		List<ArticleDTO> articleDTOS = new ArrayList<>();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setId(articleEntity.getId());
				articleDTO.setDesignation(articleEntity.getDesignation());

				articleDTO.setQuantiteStock(articleEntity.getQuantiteStock());
				articleDTO.setPrix(articleEntity.getPrix());
				articleDTO.setUgp(articleDTO.getUgp());
				articleDTO.setDateAjout(articleEntity.getDateAjout());
				articleDTO.setCodeArticle(articleEntity.getCodeArticle());
				articleDTO.setQuantiteLivree(articleEntity.getQuantiteLivree());
				articleDTO.setTva(articleEntity.getTva());
				articleDTO.setRemise(articleEntity.getRemise());
				articleDTO.setMarqueVehicule(articleDTO.getMarqueVehicule());
				articleDTO.setTypeVehicule(articleDTO.getTypeVehicule());
				articleDTO.setGenreVehicule(articleDTO.getGenreVehicule());
				articleDTO.setSousFamille(articleDTO.getSousFamille());

				articleDTOS.add(articleDTO);
			});
		}
		return articleDTOS;
	}

	@Override
	public void modifySelectedArticle(UpdateArticleDTO updateArticleDTO) {
		ArticleEntity articleEntity = articleRepository.findById(updateArticleDTO.getId()).get();
		articleEntity.setDesignation(updateArticleDTO.getDesignation());
		articleEntity.setCodeArticle(updateArticleDTO.getCodeArticle());
		articleEntity.setQuantiteStock(updateArticleDTO.getQuantiteStock());
		articleEntity.setPrix(updateArticleDTO.getPrix());
		articleEntity.setDateAjout(updateArticleDTO.getDateAjout());
		articleEntity.setTva(updateArticleDTO.getTva());
		articleEntity.setRemise(updateArticleDTO.getRemise());
		articleEntity.setQuantiteLivree(updateArticleDTO.getQuantiteLivree());
		articleEntity.setRemise(updateArticleDTO.getRemise());
		articleEntity.setTva(updateArticleDTO.getTva());
		articleEntity.setQuantiteLivree(updateArticleDTO.getQuantiteLivree());
		if (articleEntity.getTypeVehicule().getId() != updateArticleDTO.getIdTypeVehicule()) {
			TypeVehiculeEntity ancienTypeVehiculeEntity = typeVehiculeRepository
					.findById(articleEntity.getTypeVehicule().getId()).get();
			ancienTypeVehiculeEntity.getArticles().remove(articleEntity);
			typeVehiculeRepository.save(ancienTypeVehiculeEntity);
			TypeVehiculeEntity newTypeVehiculeEntity = typeVehiculeRepository
					.findById(updateArticleDTO.getIdTypeVehicule()).get();
			if (newTypeVehiculeEntity.getArticles() == null) {
				newTypeVehiculeEntity.setArticles(new ArrayList<>());
			}
			newTypeVehiculeEntity.getArticles().add(articleEntity);
			articleEntity.setTypeVehicule(newTypeVehiculeEntity);
			typeVehiculeRepository.save(newTypeVehiculeEntity);
		}
		if (articleEntity.getMarqueVehicule().getId() != updateArticleDTO.getIdMarqueVehicule()) {
			MarqueVehiculeEntity ancienMarqueVehiculeEntity = marqueVehiculeRepository
					.findById(articleEntity.getMarqueVehicule().getId()).get();
			ancienMarqueVehiculeEntity.getArticles().remove(articleEntity);
			marqueVehiculeRepository.save(ancienMarqueVehiculeEntity);
			MarqueVehiculeEntity newMarqueVehiculeEntity = marqueVehiculeRepository
					.findById(updateArticleDTO.getIdMarqueVehicule()).get();
			if (newMarqueVehiculeEntity.getArticles() == null) {
				newMarqueVehiculeEntity.setArticles(new ArrayList<>());
			}
			newMarqueVehiculeEntity.getArticles().add(articleEntity);
			articleEntity.setMarqueVehicule(newMarqueVehiculeEntity);
			marqueVehiculeRepository.save(newMarqueVehiculeEntity);
		}
		if (articleEntity.getGenreVehicule().getId() != updateArticleDTO.getIdGenreVehicule()) {
			GenreVehiculeEntity ancienGenreVehiculeEntity = genreVehiculeRepository
					.findById(articleEntity.getGenreVehicule().getId()).get();
			ancienGenreVehiculeEntity.getArticles().remove(articleEntity);
			genreVehiculeRepository.save(ancienGenreVehiculeEntity);
			GenreVehiculeEntity newGenreVehiculeEntity = genreVehiculeRepository
					.findById(updateArticleDTO.getIdGenreVehicule()).get();
			if (newGenreVehiculeEntity.getArticles() == null) {
				newGenreVehiculeEntity.setArticles(new ArrayList<>());
			}
			newGenreVehiculeEntity.getArticles().add(articleEntity);
			articleEntity.setGenreVehicule(newGenreVehiculeEntity);
			genreVehiculeRepository.save(newGenreVehiculeEntity);
		}

		if (articleEntity.getUgp().getId() != updateArticleDTO.getIdUgp()) {
			UgpEntity ancienUgpEntity = ugpRepository.findById(articleEntity.getUgp().getId()).get();
			ancienUgpEntity.getArticles().remove(articleEntity);
			ugpRepository.save(ancienUgpEntity);
			UgpEntity newUgpEntity = ugpRepository.findById(updateArticleDTO.getIdUgp()).get();
			if (newUgpEntity.getArticles() == null) {
				newUgpEntity.setArticles(new ArrayList<>());
			}
			newUgpEntity.getArticles().add(articleEntity);
			articleEntity.setUgp(newUgpEntity);
			ugpRepository.save(newUgpEntity);
		}

		if (articleEntity.getSousFamille().getId() != updateArticleDTO.getIdSousFamille()) {
			SousFamilleArticleEntity ancienSousFamilleArticleEntity = sousFamilleArticleRepository
					.findById(articleEntity.getSousFamille().getId()).get();
			ancienSousFamilleArticleEntity.getArticles().remove(articleEntity);
			sousFamilleArticleRepository.save(ancienSousFamilleArticleEntity);
			SousFamilleArticleEntity newSousFamilleArticleEntity = sousFamilleArticleRepository
					.findById(updateArticleDTO.getIdSousFamille()).get();
			if (newSousFamilleArticleEntity.getArticles() == null) {
				newSousFamilleArticleEntity.setArticles(new ArrayList<>());
			}
			newSousFamilleArticleEntity.getArticles().add(articleEntity);
			articleEntity.setSousFamille(newSousFamilleArticleEntity);
			sousFamilleArticleRepository.save(newSousFamilleArticleEntity);
		}
		articleRepository.save(articleEntity);
	}

	@Autowired
	RegulationArticleRepository regulationRepository;
	@Autowired
	RetourStructureRepository retourRepository;

	@Override
	public void modifySelectedRetourStructure(UpdateRetourStructureDTO updateRetourDTO) {
		ArticleEntity articleEntity = new ArticleEntity();
		articleEntity = articleRepository.findFirstByCodeArticle(updateRetourDTO.getCodeArticle());
		RetourStructure retour = new RetourStructure();
		retour = retourRepository.findRetourStructureById(updateRetourDTO.getRetourStructure());
		articleEntity.setQuantiteStock(updateRetourDTO.getQuantiteStock());
		articleEntity.setRetourStructure(retour);
		articleRepository.save(articleEntity);

	}

	@Override
	public void UpdateArticleForRegulation(ModifyArticleRegulationDTO updateArticleDTO) {
		ArticleEntity articleEntity = new ArticleEntity();
		articleEntity = articleRepository.findById(updateArticleDTO.getId()).get();
		articleEntity.setQuantiteStock(updateArticleDTO.getQuantiteStock());
		articleRepository.save(articleEntity);

	}

	@Override
	public void deleteSelectedArticle(Long id) {
		ArticleEntity articleEntity = articleRepository.findById(id).get();
		articleEntity.getSousFamille().getArticles().remove(articleEntity);
		sousFamilleArticleRepository.save(articleEntity.getSousFamille());
		articleEntity.getMarqueVehicule().getArticles().remove(articleEntity);
		marqueVehiculeRepository.save(articleEntity.getMarqueVehicule());
		articleEntity.getGenreVehicule().getArticles().remove(articleEntity);
		genreVehiculeRepository.save(articleEntity.getGenreVehicule());
		articleEntity.getTypeVehicule().getArticles().remove(articleEntity);
		typeVehiculeRepository.save(articleEntity.getTypeVehicule());
		articleEntity.getUgp().getArticles().remove(articleEntity);
		ugpRepository.save(articleEntity.getUgp());
		articleRepository.delete(articleEntity);
	}

	@Override
	public List<FamilleArticleDataDTO> getListFamilleDataArticle() {
		ModelMapper modelMapper = new ModelMapper();
		List<FamilleArticleDataDTO> familleArticleDataDTOS = new ArrayList<>();
		List<FamilleArticleEntity> familleArticleEntities = (List<FamilleArticleEntity>) familleArticleRepository
				.findAll();
		if (!familleArticleEntities.isEmpty()) {
			familleArticleEntities.forEach(familleArticleEntity -> {
				familleArticleDataDTOS.add(modelMapper.map(familleArticleEntity, FamilleArticleDataDTO.class));
			});
		}
		return familleArticleDataDTOS;
	}

	@Override
	public List<SousFamilleArticleDataDTO> getListSousFamilleArticleData() {
		ModelMapper modelMapper = new ModelMapper();
		List<SousFamilleArticleEntity> sousFamilleArticleEntities = new ArrayList<>();
		List<SousFamilleArticleDataDTO> sousFamilleArticleDTOS = new ArrayList<>();
		sousFamilleArticleEntities = (List<SousFamilleArticleEntity>) sousFamilleArticleRepository.findAll();
		if (!sousFamilleArticleEntities.isEmpty()) {
			sousFamilleArticleEntities.forEach(sousFamilleArticleEntity -> {
				sousFamilleArticleDTOS.add(modelMapper.map(sousFamilleArticleEntity, SousFamilleArticleDataDTO.class));
			});
		}
		return sousFamilleArticleDTOS;
	}

	@Override
	public List<ListFamilleAndSousFamilleDTO> getListFamilleAndSousFamille() {
		List<ListFamilleAndSousFamilleDTO> listFamilleAndSousFamilleDTOS = new ArrayList<>();
		List<FamilleArticleEntity> familleArticleEntities = (List<FamilleArticleEntity>) familleArticleRepository
				.findAll();
		if (!familleArticleEntities.isEmpty()) {
			familleArticleEntities.forEach(familleArticleEntity -> {
				ListFamilleAndSousFamilleDTO listFamilleAndSousFamilleDTO = new ListFamilleAndSousFamilleDTO();
				listFamilleAndSousFamilleDTO.setFamille(familleArticleEntity.getFamille());
				listFamilleAndSousFamilleDTO.setCodeFamille(familleArticleEntity.getCode());
				listFamilleAndSousFamilleDTO.setSousFamille(new ArrayList<>());
				if (!familleArticleEntity.getSousFamilles().isEmpty()) {
					familleArticleEntity.getSousFamilles().forEach(sousFamilleArticleEntity -> {
						ListSousFamilleDTO listSousFamilleDTO = new ListSousFamilleDTO();
						listSousFamilleDTO.setNomSousFamille(sousFamilleArticleEntity.getSousFamille());
						listSousFamilleDTO.setCodeSousFamille(sousFamilleArticleEntity.getCode());
						listFamilleAndSousFamilleDTO.getSousFamille().add(listSousFamilleDTO);
					});
				}
				listFamilleAndSousFamilleDTOS.add(listFamilleAndSousFamilleDTO);
			});
		}
		return listFamilleAndSousFamilleDTOS;
	}

	@Override
	public void addNewTypeVehicule(TypeVehiculeDTO typeVehiculeDTO, Long idMarque) {
		ModelMapper modelMapper = new ModelMapper();
		TypeVehiculeEntity typeVehiculeEntity = modelMapper.map(typeVehiculeDTO, TypeVehiculeEntity.class);
		MarqueVehiculeEntity marqueVehiculeEntity = marqueVehiculeRepository.findById(idMarque).get();
		if (marqueVehiculeEntity.getTypes().isEmpty()) {
			marqueVehiculeEntity.setTypes(new ArrayList<>());
		}
		marqueVehiculeEntity.getTypes().add(typeVehiculeEntity);
		typeVehiculeEntity.setMarques(marqueVehiculeEntity);
		typeVehiculeRepository.save(typeVehiculeEntity);
	}

	@Override
	public void modifySelectedTypeVehicule(TypeVehiculeDTO typeVehiculeDTO) {
		TypeVehiculeEntity typeVehiculeEntity = typeVehiculeRepository.findById(typeVehiculeDTO.getId()).get();
		typeVehiculeEntity.setCode(typeVehiculeDTO.getCode());
		typeVehiculeEntity.setDesignation(typeVehiculeDTO.getDesignation());
		typeVehiculeRepository.save(typeVehiculeEntity);
		if (!typeVehiculeEntity.getMarques().getId().equals(typeVehiculeDTO.getMarques().getId())) {
			MarqueVehiculeEntity ancienMarqueVehiculeEntity = marqueVehiculeRepository
					.findById(typeVehiculeEntity.getMarques().getId()).get();
			ancienMarqueVehiculeEntity.getTypes().remove(typeVehiculeEntity);
			marqueVehiculeRepository.save(ancienMarqueVehiculeEntity);
			MarqueVehiculeEntity newMarqueVehiculeEntity = marqueVehiculeRepository
					.findById(typeVehiculeDTO.getMarques().getId()).get();
			if (newMarqueVehiculeEntity.getTypes().isEmpty()) {
				newMarqueVehiculeEntity.setTypes(new ArrayList<>());
			}
			newMarqueVehiculeEntity.getTypes().add(typeVehiculeEntity);
			typeVehiculeEntity.setMarques(newMarqueVehiculeEntity);
			marqueVehiculeRepository.save(newMarqueVehiculeEntity);
		}
		typeVehiculeRepository.save(typeVehiculeEntity);
	}

	@Override
	public void deleteSelectedTypeVehicule(Long id) {
		TypeVehiculeEntity typeVehiculeEntity = typeVehiculeRepository.findById(id).get();
		MarqueVehiculeEntity marqueVehiculeEntity = typeVehiculeEntity.getMarques();
		marqueVehiculeEntity.getTypes().remove(typeVehiculeEntity);
		marqueVehiculeRepository.save(marqueVehiculeEntity);
		typeVehiculeRepository.deleteById(id);
	}

	@Override
	public List<TypeVehiculeDTO> getListTypeVehicule( int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<TypeVehiculeDTO> typeVehiculeDTOS = new ArrayList<>();
		Page<TypeVehiculeEntity> typeVehiculeEntities = (Page<TypeVehiculeEntity>) typeVehiculeRepository.findAll(pageable);
		if (!typeVehiculeEntities.isEmpty()) {
			typeVehiculeEntities.forEach(typeVehiculeEntity -> {
				typeVehiculeDTOS.add(modelMapper.map(typeVehiculeEntity, TypeVehiculeDTO.class));
			});
		}
		return typeVehiculeDTOS;
	}

	@Override
	public void addNewMarqueVehicule(MarqueVehiculeDTO marqueVehiculeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		MarqueVehiculeEntity marqueVehiculeEntity = modelMapper.map(marqueVehiculeDTO, MarqueVehiculeEntity.class);
		marqueVehiculeRepository.save(marqueVehiculeEntity);
	}

	@Override
	public void modifySelectedMarqueVehicule(MarqueVehiculeDTO marqueVehiculeDTO) {
		MarqueVehiculeEntity marqueVehiculeEntity = marqueVehiculeRepository.findById(marqueVehiculeDTO.getId()).get();
		marqueVehiculeEntity.setCode(marqueVehiculeDTO.getCode());
		marqueVehiculeEntity.setDesignation(marqueVehiculeDTO.getDesignation());
		marqueVehiculeRepository.save(marqueVehiculeEntity);
	}

	@Override
	public void deleteSelectedMarqueVehicule(Long id) {
		MarqueVehiculeEntity marqueVehiculeEntity = marqueVehiculeRepository.findById(id).get();
		if (!marqueVehiculeEntity.getTypes().isEmpty()) {
			marqueVehiculeEntity.getTypes().forEach(typeVehiculeEntity -> {
				marqueVehiculeEntity.getTypes().remove(typeVehiculeEntity);
				marqueVehiculeRepository.save(marqueVehiculeEntity);
			});
		}
		marqueVehiculeRepository.deleteById(id);
	}

	@Override
	public List<MarqueVehiculeDTO> getListMarqueVehicule( int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<MarqueVehiculeDTO> marqueVehiculeDTOS = new ArrayList<>();
		Page<MarqueVehiculeEntity> marqueVehiculeEntities = (Page<MarqueVehiculeEntity>) marqueVehiculeRepository
				.findAll(pageable);
		if (!marqueVehiculeEntities.isEmpty()) {
			marqueVehiculeEntities.forEach(marqueVehiculeEntity -> {
				marqueVehiculeDTOS.add(modelMapper.map(marqueVehiculeEntity, MarqueVehiculeDTO.class));
			});
		}
		return marqueVehiculeDTOS;
	}

	@Override
	public void addNewGenreVehicule(GenreVehiculeDTO genreVehiculeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		GenreVehiculeEntity genreVehiculeEntity = modelMapper.map(genreVehiculeDTO, GenreVehiculeEntity.class);
		genreVehiculeRepository.save(genreVehiculeEntity);
	}

	@Override
	public void modifySelectedGenreVehicule(GenreVehiculeDTO genreVehiculeDTO) {
		GenreVehiculeEntity genreVehiculeEntity = genreVehiculeRepository.findById(genreVehiculeDTO.getId()).get();
		genreVehiculeEntity.setCode(genreVehiculeDTO.getCode());
		genreVehiculeEntity.setDesignation(genreVehiculeDTO.getDesignation());
		genreVehiculeEntity.setDateAjout(genreVehiculeDTO.getDateAjout());
		genreVehiculeRepository.save(genreVehiculeEntity);
	}

	@Override
	public void deleteSelectedGenreVehicule(Long id) {
		genreVehiculeRepository.deleteById(id);
	}

	@Override
	public List<GenreVehiculeDTO> getListGenreVehicule( int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<GenreVehiculeDTO> genreVehiculeDTOS = new ArrayList<>();
		Page<GenreVehiculeEntity> genreVehiculeEntities = (Page<GenreVehiculeEntity>) genreVehiculeRepository.findAll(pageable);
		if (!genreVehiculeEntities.isEmpty()) {
			genreVehiculeEntities.forEach(genreVehiculeEntity -> {
				genreVehiculeDTOS.add(modelMapper.map(genreVehiculeEntity, GenreVehiculeDTO.class));
			});
		}
		return genreVehiculeDTOS;
	}

	@Override
	public void addNewUsageVehicule(UsageVehiculeDTO usageVehiculeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UsageVehiculeEntity usageVehiculeEntity = modelMapper.map(usageVehiculeDTO, UsageVehiculeEntity.class);
		usageVehiculeRepository.save(usageVehiculeEntity);
	}

	@Override
	public void modifySelectedUsageVehicule(UsageVehiculeDTO usageVehiculeDTO) {
		UsageVehiculeEntity usageVehiculeEntity = usageVehiculeRepository.findById(usageVehiculeDTO.getId()).get();
		usageVehiculeEntity.setCode(usageVehiculeDTO.getCode());
		usageVehiculeEntity.setDesignation(usageVehiculeDTO.getDesignation());
		usageVehiculeEntity.setDateAjout(usageVehiculeDTO.getDateAjout());
		usageVehiculeRepository.save(usageVehiculeEntity);
	}

	@Override
	public void deleteSelectedUsageVehicule(Long id) {
		usageVehiculeRepository.deleteById(id);
	}

	@Override
	public List<UsageVehiculeDTO> getListUsageVehicule( int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<UsageVehiculeDTO> usageVehiculeDTOS = new ArrayList<>();
		Page<UsageVehiculeEntity> usageVehiculeEntities = (Page<UsageVehiculeEntity>) usageVehiculeRepository.findAll(pageable);
		if (!usageVehiculeEntities.isEmpty()) {
			usageVehiculeEntities.forEach(usageVehiculeEntity -> {
				usageVehiculeDTOS.add(modelMapper.map(usageVehiculeEntity, UsageVehiculeDTO.class));
			});
		}
		return usageVehiculeDTOS;
	}

	@Override
	public void addNewCauseSinistre(CauseSinistreDTO causeSinistreDTO) {
		ModelMapper modelMapper = new ModelMapper();
		CauseSinistreEntity causeSinistreEntity = modelMapper.map(causeSinistreDTO, CauseSinistreEntity.class);
		causeSinistreRepository.save(causeSinistreEntity);
	}

	@Override
	public void modifySelectedCauseSinistre(CauseSinistreDTO causeSinistreDTO) {
		CauseSinistreEntity causeSinistreEntity = causeSinistreRepository.findById(causeSinistreDTO.getId()).get();
		causeSinistreEntity.setCode(causeSinistreDTO.getCode());
		causeSinistreEntity.setDateAjout(causeSinistreDTO.getDateAjout());
		causeSinistreEntity.setDesignation(causeSinistreDTO.getDesignation());
		causeSinistreRepository.save(causeSinistreEntity);
	}

	@Override
	public void deleteSelectedCauseSinistre(Long id) {
		causeSinistreRepository.deleteById(id);
	}

	@Override
	public List<CauseSinistreDTO> getListCauseSinistre(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<CauseSinistreDTO> causeSinistreDTOS = new ArrayList<>();
		Page<CauseSinistreEntity> causeSinistreEntities = (Page<CauseSinistreEntity>) causeSinistreRepository.findAll(pageable);
		if (!causeSinistreEntities.isEmpty()) {
			causeSinistreEntities.forEach(causeSinistreEntity -> {
				causeSinistreDTOS.add(modelMapper.map(causeSinistreEntity, CauseSinistreDTO.class));
			});
		}
		return causeSinistreDTOS;
	}

	@Override
	public void addNewFamilleOperationReparation(FamilleOperationReparationDTO familleOperationReparationDTO) {
		ModelMapper modelMapper = new ModelMapper();
		FamilleOperationReparationEntity familleOperationReparationEntity = modelMapper
				.map(familleOperationReparationDTO, FamilleOperationReparationEntity.class);
		familleOperationReparationRepository.save(familleOperationReparationEntity);
	}

	@Override
	public void modifySelectedFamilleOperationReparation(FamilleOperationReparationDTO familleOperationReparationDTO) {
		FamilleOperationReparationEntity familleOperationReparationEntity = familleOperationReparationRepository
				.findById(familleOperationReparationDTO.getId()).get();
		familleOperationReparationEntity.setCode(familleOperationReparationDTO.getCode());
		familleOperationReparationEntity.setDesignation(familleOperationReparationDTO.getDesignation());
		familleOperationReparationRepository.save(familleOperationReparationEntity);
	}

	@Override
	public void deleteSelectedFamilleOperationReparation(Long id) {
		familleOperationReparationRepository.deleteById(id);
	}

	@Override
	public List<FamilleOperationReparationDTO> getListFamilleOperationReparation(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<FamilleOperationReparationDTO> familleOperationReparationDTOS = new ArrayList<>();
		Page<FamilleOperationReparationEntity> familleOperationReparationEntities = (Page<FamilleOperationReparationEntity>) familleOperationReparationRepository
				.findAll(pageable);
		if (!familleOperationReparationEntities.isEmpty()) {
			familleOperationReparationEntities.forEach(familleOperationReparationEntity -> {
				familleOperationReparationDTOS
						.add(modelMapper.map(familleOperationReparationEntity, FamilleOperationReparationDTO.class));
			});
		}
		return familleOperationReparationDTOS;
	}

	@Override
	public void addNewOperationReparation(OperationReparationDTO operationReparationDTO) {
		OperationReparationEntity operationReparationEntity = new OperationReparationEntity();
		operationReparationEntity.setCode(operationReparationDTO.getCode());
		operationReparationEntity.setDesignation(operationReparationDTO.getDesignation());
		FamilleOperationReparationEntity familleOperationReparationEntity = familleOperationReparationRepository
				.findById(operationReparationDTO.getFamilleOperations().getId()).get();
		if (familleOperationReparationEntity.getOperationsReparation().isEmpty()) {
			familleOperationReparationEntity.setOperationsReparation(new ArrayList<>());
		}
		familleOperationReparationEntity.getOperationsReparation().add(operationReparationEntity);
		operationReparationEntity.setFamilleOperations(familleOperationReparationEntity);
		operationReparationRepository.save(operationReparationEntity);
	}

	@Override
	public void modifySelectedOperationReparation(OperationReparationDTO operationReparationDTO) {
		OperationReparationEntity operationReparationEntity = operationReparationRepository
				.findById(operationReparationDTO.getId()).get();
		operationReparationEntity.setCode(operationReparationDTO.getCode());
		operationReparationEntity.setDesignation(operationReparationDTO.getDesignation());
		if (!operationReparationEntity.getFamilleOperations().getId()
				.equals(operationReparationDTO.getFamilleOperations().getId())) {
			FamilleOperationReparationEntity ancienFamilleOperationReparationEntity = familleOperationReparationRepository
					.findById(operationReparationEntity.getFamilleOperations().getId()).get();
			ancienFamilleOperationReparationEntity.getOperationsReparation().remove(operationReparationEntity);
			familleOperationReparationRepository.save(ancienFamilleOperationReparationEntity);
			FamilleOperationReparationEntity newFamilleOperationReparationEntity = familleOperationReparationRepository
					.findById(operationReparationDTO.getFamilleOperations().getId()).get();
			if (newFamilleOperationReparationEntity.getOperationsReparation().isEmpty()) {
				newFamilleOperationReparationEntity.setOperationsReparation(new ArrayList<>());
			}
			newFamilleOperationReparationEntity.getOperationsReparation().add(operationReparationEntity);
			operationReparationEntity.setFamilleOperations(newFamilleOperationReparationEntity);
			familleOperationReparationRepository.save(newFamilleOperationReparationEntity);
		}
		operationReparationRepository.save(operationReparationEntity);
	}

	@Override
	public void deleteSelectedOperationReparation(Long id) {
		OperationReparationEntity operationReparationEntity = operationReparationRepository.findById(id).get();
		FamilleOperationReparationEntity familleOperationReparationEntity = familleOperationReparationRepository
				.findById(operationReparationEntity.getFamilleOperations().getId()).get();
		familleOperationReparationEntity.getOperationsReparation().remove(operationReparationEntity);
		familleOperationReparationRepository.save(familleOperationReparationEntity);
		operationReparationRepository.deleteById(id);
	}

	@Override
	public List<OperationReparationDTO> getListOperationReparation( int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<OperationReparationDTO> operationReparationDTOS = new ArrayList<>();
		Page<OperationReparationEntity> operationReparationEntities = (Page<OperationReparationEntity>) operationReparationRepository
				.findAll(pageable);
		if (!operationReparationEntities.isEmpty()) {
			operationReparationEntities.forEach(operationReparationEntity -> {
				operationReparationDTOS.add(modelMapper.map(operationReparationEntity, OperationReparationDTO.class));
			});
		}
		return operationReparationDTOS;
	}

	@Override
	public void addNewFournisseur(FournisseurDTO fournisseurDTO) {
		ModelMapper modelMapper = new ModelMapper();
		FournisseurEntity fournisseurEntity = modelMapper.map(fournisseurDTO, FournisseurEntity.class);
		fournisseurRepository.save(fournisseurEntity);
	}

	@Override
	public void modifySelectedFournisseur(FournisseurDTO fournisseurDTO) {
		FournisseurEntity fournisseurEntity = fournisseurRepository.findById(fournisseurDTO.getId()).get();
		fournisseurEntity.setCode(fournisseurDTO.getCode());
		fournisseurEntity.setDesignation(fournisseurDTO.getDesignation());
		fournisseurEntity.setActivite(fournisseurDTO.getActivite());
		fournisseurEntity.setDateAjout(fournisseurDTO.getDateAjout());
		fournisseurRepository.save(fournisseurEntity);
	}

	@Override
	public void deleteSelectedbonCommmandeForFournisseur(FournisseurEntity fournisseurEntity) {
		for (int i = 0; i < fournisseurEntity.getBonCommandes().size(); i++) {
			fournisseurEntity.getBonCommandes().get(i).setFournisseur(null);
			;
			bonCommandeRepository.save(fournisseurEntity.getBonCommandes().get(i));
		}
	}

	@Override
	public void deleteSelectedFournisseur(Long id) {
		FournisseurEntity fournisseurEntity = fournisseurRepository.findFournisseurById(id);
		if (fournisseurEntity.getBonCommandes().isEmpty() != true) {
			deleteSelectedbonCommmandeForFournisseur(fournisseurEntity);
		}
		fournisseurEntity.getBonCommandes().remove(fournisseurEntity);
		fournisseurRepository.save(fournisseurEntity);
		fournisseurRepository.deleteById(id);
	}

	
	@Override
	public List<FournisseurDTO> getListFournisseur( int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<FournisseurDTO> fournisseurDTOS = new ArrayList<>();
		Page<FournisseurEntity> fournisseurEntities = (Page<FournisseurEntity>) fournisseurRepository
				.findAll(pageable);
		if (!fournisseurEntities.isEmpty()) {
			fournisseurEntities.forEach(fournisseurEntity -> {
				fournisseurDTOS.add(modelMapper.map(fournisseurEntity, FournisseurDTO.class));
			});
		}
		return fournisseurDTOS;
	}
	
	@Override
	public List<FournisseurDTO> getListFournisseurByActivite(String activite) {
		ModelMapper modelMapper = new ModelMapper();
		List<FournisseurEntity> fournisseurEntities = new ArrayList<>();
		List<FournisseurDTO> fournisseurDTOS = new ArrayList<>();
		if (activite.equals("tousActivite")) {
			fournisseurEntities = (List<FournisseurEntity>) fournisseurRepository.findAll();
		} else {
			fournisseurEntities = fournisseurRepository.findAllByActivite(activite);
		}
		if (!fournisseurEntities.isEmpty()) {
			fournisseurEntities.forEach(fournisseurEntity -> {
				fournisseurDTOS.add(modelMapper.map(fournisseurEntity, FournisseurDTO.class));
			});
		}
		return fournisseurDTOS;
	}

	@Override
	public List<String> getListActiviteFournisseur() {
		List<String> listActivite = new ArrayList<>();
		List<FournisseurEntity> fournisseurEntities = (List<FournisseurEntity>) fournisseurRepository.findAll();
		if (!fournisseurEntities.isEmpty()) {
			fournisseurEntities.forEach(fournisseurEntity -> {
				if (!listActivite.contains(fournisseurEntity.getActivite())) {
					listActivite.add(fournisseurEntity.getActivite());
				}
			});
		}
		return listActivite;
	}

	@Override
	public List<ArticleDTO> getListArticleByUGP(String ugp) {
		ModelMapper modelMapper = new ModelMapper();
		List<ArticleDTO> articleDTOS = new ArrayList<>();
		List<ArticleEntity> articleEntities = (List<ArticleEntity>) articleRepository.findAll();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				articleDTOS.add(modelMapper.map(articleEntity, ArticleDTO.class));
			});
		}
		return articleDTOS;
	}

	@Override
	public List<ArticleDataTableDTO> getListArticle(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<ArticleEntity> articleEntity = new ArrayList<>();
		Page<ArticleEntity> article = articleRepository.findAll(pageable);
		articleEntity = article.getContent();
		return this.getListArticles(articleEntity);
	}

	private List<ArticleDataTableDTO> getListArticles(List<ArticleEntity> articleEntities) {
		List<ArticleDataTableDTO> articleDataTableDTOS = new ArrayList<>();
		if (!articleEntities.isEmpty()) {
			articleEntities.forEach(articleEntity -> {
				ArticleDataTableDTO articleDataTableDTO = new ArticleDataTableDTO();
				articleDataTableDTO.setId(articleEntity.getId());
				articleDataTableDTO.setCodeArticle(articleEntity.getCodeArticle());
				articleDataTableDTO.setQuantiteStock(articleEntity.getQuantiteStock());
				articleDataTableDTO.setPrix(articleEntity.getPrix());
				articleDataTableDTO.setMarqueVehicule(articleEntity.getMarqueVehicule());
				articleDataTableDTO.setTypeVehicule(articleEntity.getTypeVehicule());
				articleDataTableDTO.setGenreVehicule(articleEntity.getGenreVehicule());

			});

		}
		return articleDataTableDTOS;
	}

	@Override
	public List<MarcheDTO> getListMarche() {
		ModelMapper modelMapper = new ModelMapper();
		List<MarcheDTO> marcheDTOS = new ArrayList<>();
		List<MarcheEntity> marcheEntities = (List<MarcheEntity>) marcheRepository.findAll();
		if (!marcheEntities.isEmpty()) {
			marcheEntities.forEach(marcheEntity -> {
				marcheDTOS.add(modelMapper.map(marcheEntity, MarcheDTO.class));
			});
		}
		return marcheDTOS;
	}

	@Override
	public void addNewGouvernorat(GouvernoratDTO gouvernoratDTO) {
		ModelMapper modelMapper = new ModelMapper();
		GouvernoratEntity gouvernoratEntity = modelMapper.map(gouvernoratDTO, GouvernoratEntity.class);
		gouvernoratRepository.save(gouvernoratEntity);
	}

	@Override
	public void modifySelectedGouvernorat(GouvernoratDTO gouvernoratDTO) {
		ModelMapper modelMapper = new ModelMapper();
		GouvernoratEntity gouvernoratEntity = modelMapper.map(gouvernoratDTO, GouvernoratEntity.class);
		gouvernoratRepository.save(gouvernoratEntity);
	}

	@Override
	public void deleteSelectedGouvernorat(Long id) {
		gouvernoratRepository.deleteById(id);
	}

	@Override
	public List<GouvernoratDTO> getListGouvernorat() {
		ModelMapper modelMapper = new ModelMapper();
		List<GouvernoratDTO> gouvernoratDTOS = new ArrayList<>();
		List<GouvernoratEntity> gouvernoratEntities = (List<GouvernoratEntity>) gouvernoratRepository.findAll();
		if (!gouvernoratEntities.isEmpty()) {
			gouvernoratEntities.forEach(gouvernoratEntity -> {
				gouvernoratDTOS.add(modelMapper.map(gouvernoratEntity, GouvernoratDTO.class));
			});
		}
		return gouvernoratDTOS;
	}

	@Override
	public void addNewStationPeage(String designation) {
		StationPeageEntity stationPeageEntity = new StationPeageEntity();
		stationPeageEntity.setDesignation(designation);
		stationPeageReposiroty.save(stationPeageEntity);
	}

	@Override
	public void modifySelectedStationPeage(StationPeageDTO stationPeageDTO) {
		ModelMapper modelMapper = new ModelMapper();
		StationPeageEntity stationPeageEntity = modelMapper.map(stationPeageDTO, StationPeageEntity.class);
		stationPeageReposiroty.save(stationPeageEntity);
	}

	@Override
	public void deleteSelectedStationPeage(Long id) {
		stationPeageReposiroty.deleteById(id);
	}

	@Override
	public List<StationPeageDTO> getListStationPeage() {
		ModelMapper modelMapper = new ModelMapper();
		List<StationPeageDTO> stationPeageDTOS = new ArrayList<>();
		List<StationPeageEntity> stationPeageEntities = (List<StationPeageEntity>) stationPeageReposiroty.findAll();
		if (!stationPeageEntities.isEmpty()) {
			stationPeageEntities.forEach(stationPeageEntity -> {
				stationPeageDTOS.add(modelMapper.map(stationPeageEntity, StationPeageDTO.class));
			});
		}
		return stationPeageDTOS;
	}

	@Override
	public void addNewExpert(ExpertDTO expertDTO) {
		ModelMapper modelMapper = new ModelMapper();
		ExpertEntity expertEntity = modelMapper.map(expertDTO, ExpertEntity.class);
		expertRepository.save(expertEntity);
	}

	@Override
	public void modifySelectedExpert(ExpertDTO expertDTO) {
		ModelMapper modelMapper = new ModelMapper();
		ExpertEntity expertEntity = modelMapper.map(expertDTO, ExpertEntity.class);
		expertRepository.save(expertEntity);
	}

	@Override
	public void deleteSelectedExpert(Long id) {
		expertRepository.deleteById(id);
	}

	@Override
	public List<ExpertDTO> getListExpert() {
		ModelMapper modelMapper = new ModelMapper();
		List<ExpertDTO> expertDTOS = new ArrayList<>();
		List<ExpertEntity> expertEntities = (List<ExpertEntity>) expertRepository.findAll();
		if (!expertEntities.isEmpty()) {
			expertEntities.forEach(expertEntity -> {
				expertDTOS.add(modelMapper.map(expertEntity, ExpertDTO.class));
			});
		}
		return expertDTOS;
	}

	@Override
	public void addNewLieuParking(LieuParkingDTO lieuParkingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		LieuParkingEntity lieuParkingEntity = modelMapper.map(lieuParkingDTO, LieuParkingEntity.class);
		if (lieuParkingEntity.getGouvernorat().getLieuxParking() == null) {
			lieuParkingEntity.getGouvernorat().setLieuxParking(new ArrayList<>());
		}
		lieuParkingEntity.getGouvernorat().getLieuxParking().add(lieuParkingEntity);
		lieuParkingRepository.save(lieuParkingEntity);
	}

	@Override
	public void modifySelectedLieuParking(LieuParkingDTO lieuParkingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		LieuParkingEntity lieuParkingEntity = lieuParkingRepository.findById(lieuParkingDTO.getId()).get();
		if (lieuParkingDTO.getGouvernorat().getId() != lieuParkingEntity.getGouvernorat().getId()) {
			GouvernoratEntity ancienGouvernoratEntity = lieuParkingEntity.getGouvernorat();
			ancienGouvernoratEntity.getLieuxParking().remove(lieuParkingEntity);
			gouvernoratRepository.save(ancienGouvernoratEntity);
			GouvernoratEntity newGouvernoratEntity = gouvernoratRepository
					.findById(lieuParkingDTO.getGouvernorat().getId()).get();
			lieuParkingEntity.setGouvernorat(newGouvernoratEntity);
			if (newGouvernoratEntity.getLieuxParking() == null) {
				newGouvernoratEntity.setLieuxParking(new ArrayList<>());
			}
			newGouvernoratEntity.getLieuxParking().add(lieuParkingEntity);
		}
		lieuParkingEntity.setAdresse(lieuParkingDTO.getAdresse());
		lieuParkingEntity.setCode(lieuParkingDTO.getCode());
		lieuParkingRepository.save(lieuParkingEntity);
	}

	@Override
	public void deleteSelectedLieuParking(Long id) {
		LieuParkingEntity lieuParkingEntity = lieuParkingRepository.findById(id).get();
		GouvernoratEntity gouvernoratEntity = lieuParkingEntity.getGouvernorat();
		gouvernoratEntity.getLieuxParking().remove(lieuParkingEntity);
		gouvernoratRepository.save(gouvernoratEntity);
		lieuParkingRepository.delete(lieuParkingEntity);
	}

	@Override
	public List<LieuParkingDTO> getListLieuParkingByGouvernorat(String gouvernorat) {
		ModelMapper modelMapper = new ModelMapper();
		List<LieuParkingEntity> lieuParkingEntities = new ArrayList<>();
		List<LieuParkingDTO> lieuParkingDTOS = new ArrayList<>();
		if (gouvernorat.equals("tousGouvernorats")) {
			lieuParkingEntities = (List<LieuParkingEntity>) lieuParkingRepository.findAll();
		} else {
			lieuParkingEntities = lieuParkingRepository.findAllByGouvernorat_Designation(gouvernorat);
		}
		if (!lieuParkingEntities.isEmpty()) {
			lieuParkingEntities.forEach(lieuParkingEntity -> {
				lieuParkingDTOS.add(modelMapper.map(lieuParkingEntity, LieuParkingDTO.class));
			});
		}
		return lieuParkingDTOS;
	}

	@Override
	public void addNewBeneficiaireEmprunt(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BeneficiaireEmpruntEntity beneficiaireEmpruntEntity = modelMapper.map(beneficiaireEmpruntDTO,
				BeneficiaireEmpruntEntity.class);
		beneficiaireEmpruntRepository.save(beneficiaireEmpruntEntity);
	}

	@Override
	public void modifySelectedBeneficiaireEmprunt(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BeneficiaireEmpruntEntity beneficiaireEmpruntEntity = modelMapper.map(beneficiaireEmpruntDTO,
				BeneficiaireEmpruntEntity.class);
		beneficiaireEmpruntRepository.save(beneficiaireEmpruntEntity);
	}

	@Override
	public void deleteSelectedBeneficiaireEmprunt(Long id) {
		beneficiaireEmpruntRepository.deleteById(id);
	}
	
	@Override
	public List<BeneficiaireEmpruntDTO> getListBeneficiaireEmprunt( int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		Page<BeneficiaireEmpruntEntity> BeneficiaireEmpruntEntity = beneficiaireEmpruntRepository.findAll(pageable);
		List<BeneficiaireEmpruntEntity> beneficiaireEmpruntEntities = beneficiaireEmpruntRepository.findAll(pageable).getContent();

		
		return this.loadListBeneficiareEmpruntDTO(beneficiaireEmpruntEntities);
	}

	private List<BeneficiaireEmpruntDTO> loadListBeneficiareEmpruntDTO(
			List<BeneficiaireEmpruntEntity> beneficiaireEmpruntEntities) {
		List<BeneficiaireEmpruntDTO> beneficiaireEmpruntDTOS = new ArrayList<>();
		if (!beneficiaireEmpruntEntities.isEmpty()) {
			beneficiaireEmpruntEntities.forEach(beneficiaireEmpruntEntity -> {
				BeneficiaireEmpruntDTO beneficiaireEmpruntDTO = new BeneficiaireEmpruntDTO();
				beneficiaireEmpruntDTO.setId(beneficiaireEmpruntEntity.getId());
				beneficiaireEmpruntDTO.setCode(beneficiaireEmpruntEntity.getCode());
				beneficiaireEmpruntDTO.setNomBeneficiaire(beneficiaireEmpruntEntity.getNomBeneficiaire());
		
				beneficiaireEmpruntDTOS.add(beneficiaireEmpruntDTO);

			});
		}
		return beneficiaireEmpruntDTOS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void addNewEtatStock(EtatStockDTO etatStockDTO) {
		ModelMapper modelMapper = new ModelMapper();
		EtatStockEntity etatStockEntity = modelMapper.map(etatStockDTO, EtatStockEntity.class);
		ArticleEntity articleEntity = articleRepository.findById(etatStockDTO.getArticle().getId()).get();
		if (articleEntity.getEtatsStock().isEmpty()) {
			articleEntity.setEtatsStock(new ArrayList<>());
		}
		articleEntity.getEtatsStock().add(etatStockEntity);
		etatStockEntity.setArticle(articleEntity);
		etatStockRepository.save(etatStockEntity);
	}

	@Override
	public void modifySelectedEtatStock(EtatStockDTO etatStockDTO) {
		EtatStockEntity etatStockEntity = etatStockRepository.findById(etatStockDTO.getId()).get();
		etatStockEntity.setChapitre(etatStockDTO.getChapitre());
		etatStockEntity.setParagraphe(etatStockDTO.getParagraphe());
		etatStockEntity.setRegion(etatStockDTO.getRegion());
		etatStockEntity.setSousParagraphe(etatStockDTO.getSousParagraphe());
		etatStockEntity.setTime(etatStockDTO.getTime());
		if (etatStockDTO.getArticle().getId() != etatStockEntity.getArticle().getId()) {
			ArticleEntity newArticleEntity = articleRepository.findById(etatStockDTO.getArticle().getId()).get();
			ArticleEntity ancienArticleEntity = articleRepository.findById(etatStockEntity.getArticle().getId()).get();
			ancienArticleEntity.getEtatsStock().remove(etatStockEntity);
			articleRepository.save(ancienArticleEntity);
			if (newArticleEntity.getEtatsStock() == null) {
				newArticleEntity.setEtatsStock(new ArrayList<>());
			}
			newArticleEntity.getEtatsStock().add(etatStockEntity);
			etatStockEntity.setArticle(newArticleEntity);
			articleRepository.save(newArticleEntity);
		}
		etatStockRepository.save(etatStockEntity);
	}

	@Override
	public void deleteSelectedEtatStock(Long id) {
		EtatStockEntity etatStockEntity = etatStockRepository.findById(id).get();
		ArticleEntity articleEntity = articleRepository.findById(etatStockEntity.getArticle().getId()).get();
		articleEntity.getEtatsStock().remove(etatStockEntity);
		articleRepository.save(articleEntity);
		etatStockRepository.deleteById(id);
	}

	@Override
	public List<EtatStockNewDTO> getListEtatStock(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<EtatStockNewDTO> etatStockDTOS = new ArrayList<>();
		Page<EtatStockEntity> etatStockEntities = (Page<EtatStockEntity>) etatStockRepository.findAll(pageable);
		if (!etatStockEntities.isEmpty()) {
			etatStockEntities.forEach(etatStockEntity -> {
				etatStockDTOS.add(modelMapper.map(etatStockEntity, EtatStockNewDTO.class));
			});
		}
		return etatStockDTOS;
	}

	@Override
	public Long addNewPersonnel(PersonnelDTO personnelDTO) {
		ModelMapper modelMapper = new ModelMapper();
		PersonnelEntity personnelEntity = modelMapper.map(personnelDTO, PersonnelEntity.class);
		StructureEntity structureEntity = structureRepository.findById(personnelDTO.getStructure().getId()).get();
		if (structureEntity.getPersonnels().isEmpty()) {
			structureEntity.setPersonnels(new ArrayList<>());
		}
		structureEntity.getPersonnels().add(personnelEntity);
		personnelEntity.setStructure(structureEntity);
		personnelRepository.save(personnelEntity);
		return personnelEntity.getId();
	}

	@Override
	public void uploadImagePersonnel(Long id, String imageName, String imagePath) {
		PersonnelEntity personnelEntity = personnelRepository.findById(id).get();
		personnelEntity.setNameImage(imageName);
		personnelEntity.setPathImage(imagePath);
		personnelRepository.save(personnelEntity);
	}

	@Override
	public Long modifySelectedPersonnel(PersonnelDTO personnelDTO) {
		PersonnelEntity personnelEntity = personnelRepository.findById(personnelDTO.getId()).get();
		personnelEntity.setImmatriculationUnique(personnelDTO.getImmatriculationUnique());
		personnelEntity.setNom(personnelDTO.getNom());
		personnelEntity.setPrenom(personnelDTO.getPrenom());
		personnelEntity.setCin(personnelDTO.getCin());
		personnelEntity.setFonction(personnelDTO.getFonction());
		personnelEntity.setGrade(personnelDTO.getGrade());
		personnelEntity.setQuota(personnelDTO.getQuota());
		personnelEntity.setDatePermis(personnelDTO.getDatePermis());
		personnelEntity.setNombreVehicule(personnelDTO.getNombreVehicule());
		if (personnelEntity.getStructure().getId() != personnelDTO.getStructure().getId()) {
			StructureEntity ancienStructureEntity = structureRepository.findById(personnelEntity.getStructure().getId())
					.get();
			ancienStructureEntity.getPersonnels().remove(personnelEntity);
			structureRepository.save(ancienStructureEntity);
			StructureEntity newStructureEntity = structureRepository.findById(personnelDTO.getStructure().getId())
					.get();
			if (newStructureEntity.getPersonnels().isEmpty()) {
				newStructureEntity.setPersonnels(new ArrayList<>());
			}
			newStructureEntity.getPersonnels().add(personnelEntity);
			personnelEntity.setStructure(newStructureEntity);
			structureRepository.save(newStructureEntity);

		}
		personnelRepository.save(personnelEntity);
		StructureEntity structureEntity = structureRepository.findById(personnelDTO.getStructure().getId()).get();
		if (structureEntity.getPersonnels().isEmpty()) {
			structureEntity.setPersonnels(new ArrayList<>());
		}
		structureEntity.getPersonnels().add(personnelEntity);
		personnelEntity.setStructure(structureEntity);
		personnelRepository.save(personnelEntity);
		return personnelEntity.getId();
	}

	@Override
	public void deleteSelectedPersonnel(Long id) {
		PersonnelEntity personnelEntity = personnelRepository.findById(id).get();
		StructureEntity structureEntity = personnelEntity.getStructure();
		structureEntity.getPersonnels().remove(personnelEntity);
		structureRepository.save(structureEntity);
		personnelRepository.deleteById(id);
	}

	@Override
	public List<PersonnelDTO> getListPersonnel(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<PersonnelDTO> personnelDTOS = new ArrayList<>();
		Page<PersonnelEntity> personnelEntities = (Page<PersonnelEntity>) personnelRepository.findAll(pageable);
		if (!personnelEntities.isEmpty()) {
			personnelEntities.forEach(personnelEntity -> {
				personnelDTOS.add(modelMapper.map(personnelEntity, PersonnelDTO.class));
			});
		}
		return personnelDTOS;
	}

	@Override
	public void addNewParentStructure(StructureDTO structureDTO) {
		ModelMapper modelMapper = new ModelMapper();
		StructureEntity structureEntity = modelMapper.map(structureDTO, StructureEntity.class);
		structureEntity.setCollapsed(false);
		structureEntity.setSquare(false);
		structureRepository.save(structureEntity);
		if (structureDTO.getUgp() != null) {
			UgpEntity ugpEntity = ugpRepository.findById(structureDTO.getUgp().getId()).get();
			if (ugpEntity.getStructures() != null) {
				ugpEntity.setStructures(new ArrayList<>());
			}
			ugpEntity.getStructures().add(structureEntity);
			structureEntity.setUgp(ugpEntity);
			ugpRepository.save(ugpEntity);
			structureRepository.save(structureEntity);
		}
	}

	@Override
	public void addNewChildStructure(StructureDTO structureDTO, Long idParent) {
		ModelMapper modelMapper = new ModelMapper();
		StructureEntity structureEntity = modelMapper.map(structureDTO, StructureEntity.class);
		structureEntity.setCollapsed(false);
		structureEntity.setSquare(false);
		structureRepository.save(structureEntity);
		StructureEntity structureMereEntity = structureRepository.findById(idParent).get();
		if (structureMereEntity.getStructureFilles().isEmpty()) {
			structureMereEntity.setStructureFilles(new ArrayList<>());
		}
		structureMereEntity.getStructureFilles().add(structureEntity);
		structureEntity.setStructureMere(structureMereEntity);
		structureRepository.save(structureMereEntity);
		structureRepository.save(structureEntity);
		if (structureDTO.getUgp() != null) {
			UgpEntity ugpEntity = ugpRepository.findById(structureDTO.getUgp().getId()).get();
			if (ugpEntity.getStructures().isEmpty()) {
				ugpEntity.setStructures(new ArrayList<>());
			}
			ugpEntity.getStructures().add(structureEntity);
			structureEntity.setUgp(ugpEntity);
			ugpRepository.save(ugpEntity);
			structureRepository.save(structureEntity);
		}
	}

	@Override
	public void modifySelectedStructure(StructureDTO structureDTO) {
		StructureEntity structureEntity = structureRepository.findById(structureDTO.getId()).get();
		structureEntity.setCode(structureDTO.getCode());
		structureEntity.setTypeStructure(structureDTO.getTypeStructure());
		structureEntity.setDesignation(structureDTO.getDesignation());
		structureEntity.setCollapsed(false);
		structureEntity.setSquare(false);
		if (!structureEntity.getUgp().getId().equals(structureDTO.getUgp().getId())) {
			UgpEntity ancienUgpEntity = ugpRepository.findById(structureEntity.getUgp().getId()).get();
			UgpEntity newUgpEntity = ugpRepository.findById(structureDTO.getUgp().getId()).get();
			ancienUgpEntity.getStructures().remove(structureEntity);
			ugpRepository.save(ancienUgpEntity);
			if (newUgpEntity.getStructures().isEmpty()) {
				newUgpEntity.setStructures(new ArrayList<>());
			}
			structureEntity.setUgp(newUgpEntity);
			newUgpEntity.getStructures().add(structureEntity);
			ugpRepository.save(newUgpEntity);
		}
		structureRepository.save(structureEntity);
	}

	@Override
	public void deleteSelectedStructure(Long id) {
		StructureEntity structureEntity = structureRepository.findById(id).get();
		if (structureEntity.getUgp() != null) {
			structureEntity.getUgp().getStructures().remove(structureEntity);
			ugpRepository.save(structureEntity.getUgp());
		}
		if (structureEntity.getStructureMere() != null) {
			StructureEntity structureMereEntity = structureEntity.getStructureMere();
			structureMereEntity.getStructureFilles().remove(structureEntity);
			structureRepository.save(structureMereEntity);
		}
		structureRepository.delete(structureEntity);
	}

	@Override
	public List<StructureDTO> getListStructure() {
		ModelMapper modelMapper = new ModelMapper();
		List<StructureDTO> structureDTOS = new ArrayList<>();
		List<StructureEntity> structureEntities = (List<StructureEntity>) structureRepository.findAll();
		if (!structureEntities.isEmpty()) {
			structureEntities.forEach(structureEntity -> {
				structureDTOS.add(modelMapper.map(structureEntity, StructureDTO.class));
			});
		}
		return structureDTOS;
	}

	@Override
	public void addNewMagasin(MagasinDTO magasinDTO, Long idUGP) {
		ModelMapper modelMapper = new ModelMapper();
		MagasinEntity magasinEntity = modelMapper.map(magasinDTO, MagasinEntity.class);
		magasinEntity.setCollapsed(false);
		magasinEntity.setSquare(false);
		UgpEntity ugpEntity = ugpRepository.findById(idUGP).get();
		if (ugpEntity.getMagasins().isEmpty()) {
			ugpEntity.setMagasins(new ArrayList<>());
		}
		ugpEntity.getMagasins().add(magasinEntity);
		magasinEntity.setUgp(ugpEntity);
		magasinRepository.save(magasinEntity);
	}

	@Override
	public void modifySelectedMagasin(MagasinDTO magasinDTO) {
		MagasinEntity magasinEntity = magasinRepository.findById(magasinDTO.getId()).get();
		magasinEntity.setCode(magasinDTO.getCode());
		magasinEntity.setDesignation(magasinDTO.getDesignation());
		magasinRepository.save(magasinEntity);
	}

	@Override
	public void deleteSelectedMagasin(Long id) {
		MagasinEntity magasinEntity = magasinRepository.findById(id).get();
		UgpEntity ugpEntity = ugpRepository.findById(magasinEntity.getUgp().getId()).get();
		ugpEntity.getMagasins().remove(magasinEntity);
		ugpRepository.save(ugpEntity);
		magasinRepository.delete(magasinEntity);
	}

	@Override
	public void addNewAtelier(AtelierDTO atelierDTO, Long idUGP) {
		ModelMapper modelMapper = new ModelMapper();
		AtelierEntity atelierEntity = modelMapper.map(atelierDTO, AtelierEntity.class);
		UgpEntity ugpEntity = ugpRepository.findById(idUGP).get();
		atelierEntity.setCollapsed(false);
		atelierEntity.setSquare(false);
		if (ugpEntity.getAteliers().isEmpty()) {
			ugpEntity.setAteliers(new ArrayList<>());
		}
		ugpEntity.getAteliers().add(atelierEntity);
		atelierEntity.setUgp(ugpEntity);
		atelierRepository.save(atelierEntity);
	}

	@Override
	public void modifySelectedAtelier(AtelierDTO atelierDTO) {
		AtelierEntity atelierEntity = atelierRepository.findById(atelierDTO.getId()).get();
		atelierEntity.setCode(atelierDTO.getCode());
		atelierEntity.setDesignation(atelierDTO.getDesignation());
		atelierRepository.save(atelierEntity);
	}

	@Override
	public void deleteSelectedAtelier(Long id) {
		AtelierEntity atelierEntity = atelierRepository.findById(id).get();
		UgpEntity ugpEntity = ugpRepository.findById(atelierEntity.getUgp().getId()).get();
		ugpEntity.getAteliers().remove(atelierEntity);
		ugpRepository.save(ugpEntity);
		atelierRepository.delete(atelierEntity);
	}

	@Override
	public void addNewRessource(RessourceDTO ressourceDTO, Long idUGP) {
		ModelMapper modelMapper = new ModelMapper();
		RessourceEntity ressourceEntity = modelMapper.map(ressourceDTO, RessourceEntity.class);
		UgpEntity ugpEntity = ugpRepository.findById(idUGP).get();
		ugpEntity.setCollapsed(false);
		ugpEntity.setSquare(false);
		if (ugpEntity.getRessources().isEmpty()) {
			ugpEntity.setRessources(new ArrayList<>());
		}
		ugpEntity.getRessources().add(ressourceEntity);
		ressourceEntity.setUgp(ugpEntity);
		ressourceRepository.save(ressourceEntity);
	}

	@Override
	public void modifySelectedRessource(RessourceDTO ressourceDTO) {
		RessourceEntity ressourceEntity = ressourceRepository.findById(ressourceDTO.getId()).get();
		ressourceEntity.setNumero(ressourceDTO.getNumero());
		ressourceEntity.setDesignation(ressourceDTO.getDesignation());
		ressourceEntity.setPrixUnitaire(ressourceDTO.getPrixUnitaire());
		ressourceEntity.setNombrePersonnels(ressourceDTO.getNombrePersonnels());
		ressourceRepository.save(ressourceEntity);
	}

	@Override
	public void deleteSelectedRessource(Long id) {
		RessourceEntity ressourceEntity = ressourceRepository.findById(id).get();
		UgpEntity ugpEntity = ugpRepository.findById(ressourceEntity.getUgp().getId()).get();
		ugpEntity.getAteliers().remove(ressourceEntity);
		ugpRepository.save(ugpEntity);
		ressourceRepository.delete(ressourceEntity);
	}

	@Override
	public void addNewSection(SectionDTO sectionDTO, String parent, Long id) {
		ModelMapper modelMapper = new ModelMapper();
		SectionEntity sectionEntity = modelMapper.map(sectionDTO, SectionEntity.class);
		sectionEntity.setCollapsed(false);
		sectionEntity.setSquare(false);
		if (parent.equals("Atelier")) {
			AtelierEntity atelierEntity = atelierRepository.findById(id).get();
			if (atelierEntity.getSections().isEmpty()) {
				atelierEntity.setSections(new ArrayList<>());
			}
			atelierEntity.getSections().add(sectionEntity);
			sectionEntity.setAtelier(atelierEntity);
		}
		if (parent.equals("Ressource")) {
			RessourceEntity ressourceEntity = ressourceRepository.findById(id).get();
			if (ressourceEntity.getSections().isEmpty()) {
				ressourceEntity.setSections(new ArrayList<>());
			}
			ressourceEntity.getSections().add(sectionEntity);
			sectionEntity.setRessource(ressourceEntity);
		}
		if (parent.equals("Magasin")) {
			MagasinEntity magasinEntity = magasinRepository.findById(id).get();
			if (magasinEntity.getSections().isEmpty()) {
				magasinEntity.setSections(new ArrayList<>());
			}
			magasinEntity.getSections().add(sectionEntity);
			sectionEntity.setMagasin(magasinEntity);
		}
		sectionRepository.save(sectionEntity);
	}

	@Override
	public void modifySelectedSection(SectionDTO sectionDTO) {
		SectionEntity sectionEntity = sectionRepository.findById(sectionDTO.getId()).get();
		sectionEntity.setCode(sectionDTO.getCode());
		sectionEntity.setDesignation(sectionDTO.getDesignation());
		sectionEntity.setNombrePersonnels(sectionDTO.getNombrePersonnels());
		sectionRepository.save(sectionEntity);
	}

	@Override
	public void deleteSelectedSection(Long id) {
		SectionEntity sectionEntity = sectionRepository.findById(id).get();
		if (sectionEntity.getAtelier() != null) {//
			AtelierEntity atelierEntity = sectionEntity.getAtelier();
			atelierEntity.getSections().remove(sectionEntity);
			atelierRepository.save(atelierEntity);
		}
		if (sectionEntity.getMagasin() != null) {
			MagasinEntity magasinEntity = sectionEntity.getMagasin();
			magasinEntity.getSections().remove(sectionEntity);
			magasinRepository.save(magasinEntity);
		}
		if (sectionEntity.getRessource() != null) {
			RessourceEntity ressourceEntity = sectionEntity.getRessource();
			ressourceEntity.getSections().remove(sectionEntity);
			ressourceRepository.save(ressourceEntity);
		}
		sectionRepository.deleteById(id);
	}

	@Override
	public void addNewUGP(UgpDTO ugpDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UgpEntity ugpEntity = modelMapper.map(ugpDTO, UgpEntity.class);
		ugpEntity.setCollapsed(false);
		ugpEntity.setSquare(false);
		ugpRepository.save(ugpEntity);
	}

	@Override
	public void modifySelectedUGP(UgpDTO ugpDTO) {
		UgpEntity ugpEntity = ugpRepository.findById(ugpDTO.getId()).get();
		ugpEntity.setCode(ugpDTO.getCode());
		ugpEntity.setDesignation(ugpDTO.getDesignation());
		ugpRepository.save(ugpEntity);
	}

	@Override
	public void deleteSelectedUGP(Long id) {
		ugpRepository.deleteById(id);
	}

	@Override
	public List<UgpDTO> getListUGP() {
		ModelMapper modelMapper = new ModelMapper();
		List<UgpDTO> ugpDTOS = new ArrayList<>();
		List<UgpEntity> ugpEntities = (List<UgpEntity>) ugpRepository.findAll();
		if (!ugpEntities.isEmpty()) {
			ugpEntities.forEach(ugpEntity -> {
				ugpDTOS.add(modelMapper.map(ugpEntity, UgpDTO.class));
			});
		}
		return ugpDTOS;
	}

	@Override
	public void addNewProgrammeEntretiensPreventifs(ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO) {
		ProgrammeEntretiensPreventifsEntity programmeEntretiensPreventifsEntity = new ProgrammeEntretiensPreventifsEntity();
		programmeEntretiensPreventifsEntity.setCode(programmeEntretiensPreventifsDTO.getCode());
		programmeEntretiensPreventifsEntity.setCycle(programmeEntretiensPreventifsDTO.getCycle());
		programmeEntretiensPreventifsEntity.setDesignation(programmeEntretiensPreventifsDTO.getDesignation());
		MarqueVehiculeEntity marqueVehiculeEntity = marqueVehiculeRepository
				.findById(programmeEntretiensPreventifsDTO.getMarque().getId()).get();
		if (marqueVehiculeEntity.getProgrammeEntretien().isEmpty()) {
			marqueVehiculeEntity.setProgrammeEntretien(new ArrayList<>());
		}
		marqueVehiculeEntity.getProgrammeEntretien().add(programmeEntretiensPreventifsEntity);
		programmeEntretiensPreventifsEntity.setMarque(marqueVehiculeEntity);
		TypeVehiculeEntity typeVehiculeEntity = typeVehiculeRepository
				.findById(programmeEntretiensPreventifsDTO.getType().getId()).get();
		if (typeVehiculeEntity.getProgrammeEntretien().isEmpty()) {
			typeVehiculeEntity.setProgrammeEntretien(new ArrayList<>());
		}
		typeVehiculeEntity.getProgrammeEntretien().add(programmeEntretiensPreventifsEntity);
		programmeEntretiensPreventifsEntity.setType(typeVehiculeEntity);
		EnergieEntity energieEntity = energieRepository.findById(programmeEntretiensPreventifsDTO.getEnergie().getId())
				.get();
		if (energieEntity.getProgrammeEntretien().isEmpty()) {
			energieEntity.setProgrammeEntretien(new ArrayList<>());
		}
		energieEntity.getProgrammeEntretien().add(programmeEntretiensPreventifsEntity);
		programmeEntretiensPreventifsEntity.setEnergie(energieEntity);
		programmeEntretiensPreventifsRepository.save(programmeEntretiensPreventifsEntity);
	}

	@Override
	public void modifySelectedProgrammeEntretiensPreventifs(
			ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO) {
		ProgrammeEntretiensPreventifsEntity programmeEntretiensPreventifsEntity = programmeEntretiensPreventifsRepository
				.findById(programmeEntretiensPreventifsDTO.getId()).get();
		programmeEntretiensPreventifsEntity.setCode(programmeEntretiensPreventifsDTO.getCode());
		programmeEntretiensPreventifsEntity.setCycle(programmeEntretiensPreventifsDTO.getCycle());
		programmeEntretiensPreventifsEntity.setDesignation(programmeEntretiensPreventifsDTO.getDesignation());
		if (!programmeEntretiensPreventifsDTO.getMarque().getId()
				.equals(programmeEntretiensPreventifsEntity.getMarque().getId())) {
			MarqueVehiculeEntity ancienMarqueVehiculeEntity = marqueVehiculeRepository
					.findById(programmeEntretiensPreventifsEntity.getMarque().getId()).get();
			ancienMarqueVehiculeEntity.getProgrammeEntretien().remove(programmeEntretiensPreventifsEntity);
			marqueVehiculeRepository.save(ancienMarqueVehiculeEntity);
			MarqueVehiculeEntity newMarqueVehiculeEntity = marqueVehiculeRepository
					.findById(programmeEntretiensPreventifsDTO.getMarque().getId()).get();
			if (newMarqueVehiculeEntity.getProgrammeEntretien().isEmpty()) {
				newMarqueVehiculeEntity.setProgrammeEntretien(new ArrayList<>());
			}
			newMarqueVehiculeEntity.getProgrammeEntretien().add(programmeEntretiensPreventifsEntity);
			programmeEntretiensPreventifsEntity.setMarque(newMarqueVehiculeEntity);
			marqueVehiculeRepository.save(newMarqueVehiculeEntity);
		}
		if (!programmeEntretiensPreventifsDTO.getType().getId()
				.equals(programmeEntretiensPreventifsEntity.getType().getId())) {
			TypeVehiculeEntity ancienTypeVehiculeEntity = typeVehiculeRepository
					.findById(programmeEntretiensPreventifsEntity.getType().getId()).get();
			ancienTypeVehiculeEntity.getProgrammeEntretien().remove(programmeEntretiensPreventifsEntity);
			typeVehiculeRepository.save(ancienTypeVehiculeEntity);
			TypeVehiculeEntity newTypeVehiculeEntity = typeVehiculeRepository
					.findById(programmeEntretiensPreventifsDTO.getType().getId()).get();
			if (newTypeVehiculeEntity.getProgrammeEntretien().isEmpty()) {
				newTypeVehiculeEntity.setProgrammeEntretien(new ArrayList<>());
			}
			newTypeVehiculeEntity.getProgrammeEntretien().add(programmeEntretiensPreventifsEntity);
			programmeEntretiensPreventifsEntity.setType(newTypeVehiculeEntity);
			typeVehiculeRepository.save(newTypeVehiculeEntity);
		}
		if (!programmeEntretiensPreventifsDTO.getEnergie().getId()
				.equals(programmeEntretiensPreventifsEntity.getEnergie().getId())) {
			EnergieEntity ancienEnergieEntity = energieRepository
					.findById(programmeEntretiensPreventifsEntity.getEnergie().getId()).get();
			ancienEnergieEntity.getProgrammeEntretien().remove(programmeEntretiensPreventifsEntity);
			energieRepository.save(ancienEnergieEntity);
			EnergieEntity newEnergieEntity = energieRepository
					.findById(programmeEntretiensPreventifsDTO.getEnergie().getId()).get();
			if (newEnergieEntity.getProgrammeEntretien().isEmpty()) {
				newEnergieEntity.setProgrammeEntretien(new ArrayList<>());
			}
			newEnergieEntity.getProgrammeEntretien().add(programmeEntretiensPreventifsEntity);
			programmeEntretiensPreventifsEntity.setEnergie(newEnergieEntity);
			energieRepository.save(newEnergieEntity);
		}
		programmeEntretiensPreventifsRepository.save(programmeEntretiensPreventifsEntity);
	}

	@Override
	public void deleteSelectedProgrammeEntretiensPreventifs(Long id) {
		ProgrammeEntretiensPreventifsEntity programmeEntretiensPreventifsEntity = programmeEntretiensPreventifsRepository
				.findById(id).get();
		MarqueVehiculeEntity marqueVehiculeEntity = marqueVehiculeRepository
				.findById(programmeEntretiensPreventifsEntity.getMarque().getId()).get();
		marqueVehiculeEntity.getProgrammeEntretien().remove(programmeEntretiensPreventifsEntity);
		marqueVehiculeRepository.save(marqueVehiculeEntity);
		TypeVehiculeEntity typeVehiculeEntity = typeVehiculeRepository
				.findById(programmeEntretiensPreventifsEntity.getType().getId()).get();
		typeVehiculeEntity.getProgrammeEntretien().remove(programmeEntretiensPreventifsEntity);
		typeVehiculeRepository.save(typeVehiculeEntity);
		programmeEntretiensPreventifsRepository.deleteById(id);
	}

	@Override
	public List<ProgrammeEntretiensPreventifsDTO> getListProgrammeEntretiensPreventifs(String marque, String type, int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		ModelMapper modelMapper = new ModelMapper();
		Page<ProgrammeEntretiensPreventifsEntity> programmeEntretiensPreventifsEntities = programmeEntretiensPreventifsRepository.findAll(pageable);
	//Page<ProgrammeEntretiensPreventifsEntity> programmeEntretiensPreventifsEntities = new ArrayList<>();
		List<ProgrammeEntretiensPreventifsDTO> programmeEntretiensPreventifsDTOS = new ArrayList<>();
		if (marque.equals("tousMarques")) {
			programmeEntretiensPreventifsEntities = (Page<ProgrammeEntretiensPreventifsEntity>) programmeEntretiensPreventifsRepository
					.findAll(pageable);
		} else {
			if (type.equals("tousTypes")) {
				MarqueVehiculeEntity marqueVehiculeEntity = marqueVehiculeRepository.findFirstByDesignation(marque);
				programmeEntretiensPreventifsEntities = programmeEntretiensPreventifsRepository
						.findAllByMarque(marqueVehiculeEntity,pageable);
			} else {
				TypeVehiculeEntity typeVehiculeEntity = typeVehiculeRepository.findFirstByDesignation(type);
				programmeEntretiensPreventifsEntities = programmeEntretiensPreventifsRepository
						.findAllByType(typeVehiculeEntity,pageable);
			}
		}
		if (!programmeEntretiensPreventifsEntities.isEmpty()) {
			programmeEntretiensPreventifsEntities.forEach(programmeEntretiensPreventifsEntity -> {
				programmeEntretiensPreventifsDTOS.add(
						modelMapper.map(programmeEntretiensPreventifsEntity, ProgrammeEntretiensPreventifsDTO.class));
			});
		}
		return programmeEntretiensPreventifsDTOS;
	}

	@Override
	public void addNewEnergie(EnergieDTO energieDTO) {
		ModelMapper modelMapper = new ModelMapper();
		EnergieEntity energieEntity = modelMapper.map(energieDTO, EnergieEntity.class);
		energieRepository.save(energieEntity);
	}

	@Override
	public void modifySelectedEnergie(EnergieDTO energieDTO) {
		EnergieEntity energieEntity = energieRepository.findById(energieDTO.getId()).get();
		energieEntity.setCode(energieDTO.getCode());
		energieEntity.setDescription(energieDTO.getDescription());
		energieEntity.setPrixUnitaire(energieDTO.getPrixUnitaire());
		energieEntity.setTva(energieDTO.getTva());
		energieRepository.save(energieEntity);
	}

	@Override
	public void deleteSelectedEnergie(Long id) {
		EnergieEntity energieEntity = energieRepository.findById(id).get();
		if (!energieEntity.getProgrammeEntretien().isEmpty()) {
			energieEntity.getProgrammeEntretien().forEach(programmeEntretiensPreventifsEntity -> {
				energieEntity.getProgrammeEntretien().remove(programmeEntretiensPreventifsEntity);
				energieRepository.save(energieEntity);
			});
		}
		energieRepository.delete(energieEntity);
	}

	@Override
	public List<EnergieDTO> getListEnergie() {
		ModelMapper modelMapper = new ModelMapper();
		List<EnergieDTO> energieDTOS = new ArrayList<>();
		List<EnergieEntity> energieEntities = (List<EnergieEntity>) energieRepository.findAll();
		if (!energieEntities.isEmpty()) {
			energieEntities.forEach(energieEntity -> {
				energieDTOS.add(modelMapper.map(energieEntity, EnergieDTO.class));
			});
		}
		return energieDTOS;
	}

	@Override
	public void addNewAnnee(AnneeDTO anneeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		AnneeEntity anneeEntity = modelMapper.map(anneeDTO, AnneeEntity.class);
		anneeRepository.save(anneeEntity);
	}

	@Override
	public void deleteSelectedAnnee(Long id) {
		anneeRepository.deleteById(id);
	}

	@Override
	public List<AnneeDTO> getListAnnee() {
		ModelMapper modelMapper = new ModelMapper();
		List<AnneeDTO> anneeDTOS = new ArrayList<>();
		List<AnneeEntity> anneeEntities = (List<AnneeEntity>) anneeRepository.findAll();
		if (!anneeEntities.isEmpty()) {
			anneeEntities.forEach(anneeEntity -> {
				anneeDTOS.add(modelMapper.map(anneeEntity, AnneeDTO.class));
			});
		}
		return anneeDTOS;
	}

	@Override
	public void addNewTva(TvaDTO tvaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		TvaEntity tvaEntity = modelMapper.map(tvaDTO, TvaEntity.class);
		tvaRepository.save(tvaEntity);
	}

	@Override
	public void deleteSelectedTva(Long id) {
		tvaRepository.deleteById(id);
	}

	@Override
	public List<TvaDTO> getListTva() {
		ModelMapper modelMapper = new ModelMapper();
		List<TvaDTO> tvaDTOS = new ArrayList<>();
		List<TvaEntity> tvaEntities = (List<TvaEntity>) tvaRepository.findAll();
		if (!tvaEntities.isEmpty()) {
			tvaEntities.forEach(tvaEntity -> {
				tvaDTOS.add(modelMapper.map(tvaEntity, TvaDTO.class));
			});
		}
		return tvaDTOS;
	}

	@Override
	public void addNewUnite(UniteDTO uniteDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UniteEntity uniteEntity = modelMapper.map(uniteDTO, UniteEntity.class);
		uniteRepository.save(uniteEntity);
	}

	@Override
	public void deleteSelectedUnite(Long id) {
		uniteRepository.deleteById(id);
	}

	@Override
	public List<UniteDTO> getListUnite() {
		ModelMapper modelMapper = new ModelMapper();
		List<UniteDTO> uniteDTOS = new ArrayList<>();
		List<UniteEntity> uniteEntities = (List<UniteEntity>) uniteRepository.findAll();
		if (!uniteEntities.isEmpty()) {
			uniteEntities.forEach(uniteEntity -> {
				uniteDTOS.add(modelMapper.map(uniteEntity, UniteDTO.class));
			});
		}
		return uniteDTOS;
	}

	@Override
	public List<FournisseurListDTO> getListFournisseurBonCommande() {
		ModelMapper modelMapper = new ModelMapper();
		List<FournisseurListDTO> fournisseurDTOS = new ArrayList<>();
		List<FournisseurEntity> fournisseurEntities = (List<FournisseurEntity>) fournisseurRepository.findAll();
		if (!fournisseurEntities.isEmpty()) {
			fournisseurEntities.forEach(fournisseurEntity -> {
				fournisseurDTOS.add(modelMapper.map(fournisseurEntity, FournisseurListDTO.class));
			});
		}
		return fournisseurDTOS;
	}

	@Override
	public void modifySelectedFamilleArticle(SousFamilleArticleDTO sousFamilleArticleDTO) {
		SousFamilleArticleEntity sousFamilleArticleEntity = sousFamilleArticleRepository
				.findById(sousFamilleArticleDTO.getId()).get();
		sousFamilleArticleEntity.setDateAjout(sousFamilleArticleDTO.getDateAjout());
		System.out.println(sousFamilleArticleDTO.getDateAjout());
		sousFamilleArticleEntity.setCode(sousFamilleArticleDTO.getCode());
		System.out.println(sousFamilleArticleDTO.getCode());
		sousFamilleArticleEntity.setSousFamille(sousFamilleArticleDTO.getSousFamille());
		System.out.println(sousFamilleArticleDTO.getSousFamille());
		if (sousFamilleArticleEntity.getFamille().getId() != (sousFamilleArticleDTO.getFamille().getId())) {
			FamilleArticleEntity ancienFamilleArticleEntity = familleArticleRepository
					.findById(sousFamilleArticleEntity.getFamille().getId()).get();
			ancienFamilleArticleEntity.getSousFamilles().remove(sousFamilleArticleEntity);
			familleArticleRepository.save(ancienFamilleArticleEntity);
			FamilleArticleEntity newFamilleArticleEntity = familleArticleRepository
					.findById(sousFamilleArticleDTO.getFamille().getId()).get();
			if (newFamilleArticleEntity.getSousFamilles() == null) {
				newFamilleArticleEntity.setSousFamilles(new ArrayList<>());
			}

		}
		sousFamilleArticleRepository.save(sousFamilleArticleEntity);
	}

	@Override
	public void modifySelectedFamille(FamilleArticleDTO familleArticleDTO) {
		FamilleArticleEntity familleArticleEntity = familleArticleRepository
				.findById(familleArticleDTO.getId()).get();
		System.out.println(familleArticleDTO.getFamille());
		System.out.println(familleArticleEntity.getFamille());
		familleArticleEntity.setCode(familleArticleDTO.getCode());
		familleArticleEntity.setFamille(familleArticleDTO.getFamille());
		familleArticleRepository.save(familleArticleEntity);
	}
	
	
	@Override
	public List<MarcheDTO> getPaginationListMarche(int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<MarcheDTO> marcheDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<MarcheEntity> marcheEntityPage = marcheRepository.findAll(pageable);
		List<MarcheEntity> marcheEntities = marcheEntityPage.getContent();
		if (!marcheEntities.isEmpty()) {
			marcheEntities.forEach(marcheEntity -> {
				marcheDTOS.add(modelMapper.map(marcheEntity, MarcheDTO.class));
			});
		}
		return marcheDTOS;
	}

	@Override
	public List<FournisseurDTO> getListFournisseur() {

		return null;
	}

	public List<ArticleDTO> getListArticleByMarque(String marque) {
		return null;
	}

	public List<ArticleDTO> getListArticleBySousFamille(String sousfamille) {
		return null;
	}

	public List<ArticleDTO> getListArticleByType(String type) {
		return null;
	}

	public List<ArticleDTO> getPaginationArticleByMarque(int page, int limit, String typeCarburant) {
		return null;
	}

	@Override
	public List<ArticleDTO> getPaginationArticleByMarque(int page, int limit, MarqueVehiculeEntity marqueVehicule,
			Pageable pageable) {
		return null;
	}

	@Override
	public Long getTotalNumberArticleByMarque(String marque) {
		return null;
	}

	@Override
	public Long getTotalNumberArticleByGenre(String genre) {
		return null;
	}

	@Override
	public Long getTotalNumberArticleByType(String type) {
		return null;
	}

	@Override
	public List<ArticleDTO> getPaginationArticleByGenre(int page, int limit, String genre) {
		return null;
	}

	@Override
	public List<ArticleDTO> getPaginationArticleByType(int page, int limit, String type) {
		return null;
	}

	@Override
	public List<ArticleDTO> getPaginationArticleByMarque(int page, int limit, MarqueVehiculeEntity marqueVehicule) {
		return null;
	}

	@Override
	public List<FamilleArticleDTO> getListFamilleArticle() {
		return null;
	}

	@Override
	public List<SousFamilleArticleDTO> getListSousFamilleArticle() {
		return null;
	}

	@Override
	public void modifySelectedSousFamilleArticle(SousFamilleArticleDTO sousFamilleArticleDTO) {
		System.out.println(sousFamilleArticleDTO);
		System.out.println(sousFamilleArticleDTO.getFamille());

		SousFamilleArticleEntity sousFamilleArticleEntity = sousFamilleArticleRepository
				.findById(sousFamilleArticleDTO.getId()).get();
		sousFamilleArticleEntity.setCode(sousFamilleArticleDTO.getCode());
		sousFamilleArticleEntity.setSousFamille(sousFamilleArticleDTO.getSousFamille());
		sousFamilleArticleRepository.save(sousFamilleArticleEntity);
	}

	@Override
	public List<ArticleEntity> getAlertArticle(int page, int limit) {
		
		List<ArticleEntity> HistoriqueRegulationEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<ArticleEntity> historiqueRegulationEntityPage = articleRepository.findArticleForAlerting(pageable);
		HistoriqueRegulationEntities = historiqueRegulationEntityPage.getContent();
		System.out.print("test3");
		System.out.print(HistoriqueRegulationEntities);
		return HistoriqueRegulationEntities;
	}

	@Override
	public List<ArticleEntity> findArticleForAlerting() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
