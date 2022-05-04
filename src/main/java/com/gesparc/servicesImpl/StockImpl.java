          package com.gesparc.servicesImpl;
import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.entities.achat.DemandeArticleEntity;
import com.gesparc.entities.carburant.HistoriqueOperationRecharge;
import com.gesparc.entities.carburant.RechargeSousCompte;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.achat.MarcheEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.ArticlePricesEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;

import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.entities.stock.HistoriqueRegulation;
import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.MagasinRotationNull;
import com.gesparc.entities.stock.MagasinVirtuelArticleEntity;
import com.gesparc.entities.stock.MagasinVirtuelEntity;
import com.gesparc.entities.stock.ParcTransfertArticleEntity;
import com.gesparc.entities.stock.ParcTransfertEntity;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.RetourStructure;
import com.gesparc.repositories.achat.BonCommandeRepository;
import com.gesparc.repositories.achat.DemandeArticleRepository;
import com.gesparc.repositories.achat.MarcheRepository;
import com.gesparc.repositories.maintenance.BonTravailArticleRepository;
import com.gesparc.repositories.maintenance.BonTravailRepository;
import com.gesparc.repositories.referentiel.ArticlePricesRepository;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.repositories.referentiel.FournisseurRepository;
import com.gesparc.repositories.referentiel.UgpRepository;
import com.gesparc.repositories.stock.HistoriqueRegulationRepository;
import com.gesparc.repositories.stock.InventaireStockRepository;
import com.gesparc.repositories.stock.MagasinRotationNullRepository;
import com.gesparc.repositories.stock.MagasinVirtuelRepository;
import com.gesparc.repositories.stock.ParcTransfertRepository;
import com.gesparc.repositories.stock.RegulationArticleRepository;
import com.gesparc.requests.Maintenance.BonTravailArticleEntity;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleForInventaireRequest;
import com.gesparc.repositories.stock.RetourStructureRepository;
import com.gesparc.repositories.stock.UgpArticleRepository;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleForRegulationRequest;
import com.gesparc.requests.referentiel.Additionnel.UpdateForRetourRequest;
import com.gesparc.requests.referentiel.Additionnel.UpdateArticleRequest;
import com.gesparc.services.Stock;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;
import com.gesparc.sharedDTO.carburant.DemandeRechargeSousCompteDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModifyDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailArticleDTO;
import com.gesparc.sharedDTO.referentiel.UgpDTO;
import com.gesparc.sharedDTO.referentiel.UpdateArticleDTO;
import com.gesparc.sharedDTO.referentiel.UpdateArticleForRegulationDTO;
import com.gesparc.sharedDTO.stock.HistoriqueRegulationDTO;
import com.gesparc.sharedDTO.stock.ListInventaireDTO;
import com.gesparc.sharedDTO.stock.ListInventaireStockDTO;
import com.gesparc.sharedDTO.referentiel.UpdateRetourStructureDTO;
import com.gesparc.sharedDTO.stock.ListRegulationArticleDTO;
import com.gesparc.sharedDTO.stock.ListRetourDTO;
import com.gesparc.sharedDTO.stock.MagasinRotationNullDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelArticleDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.NewInventaireStockDTO;
import com.gesparc.sharedDTO.stock.NewRegulationDTO;
import com.gesparc.sharedDTO.stock.NewRetourDTO;
import com.gesparc.sharedDTO.stock.ParcTransfertDTO;
import com.gesparc.sharedDTO.stock.UpdateParcTransfertDTO;
import com.gesparc.sharedDTO.stock.additionnel.MagasinVirtuelDataTableDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateRegulationDTO;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@EnableAutoConfiguration
@Service
public class StockImpl implements Stock {

	Logger logger = LoggerFactory.getLogger(StockImpl.class);

	@Autowired
	private ReferentielImpl referentiel;

	@Autowired
	BonCommandeRepository bonCommandeRepository;

	@Autowired
	RegulationArticleRepository regulationRepository;

	@Autowired
	RetourStructureRepository retourRepository;
	
	@Autowired
	UgpRepository ugpRepository;
	
	@Autowired
	ParcTransfertRepository parcRepository;
	
	@Autowired
	UgpArticleRepository ugpArticleRepository;

	@Autowired
	private MagasinVirtuelRepository magasinVirtuelRepository;

	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	HistoriqueRegulationRepository historiqueRepository ;
	
	@Autowired
	private ArticlePricesRepository articlePricesRepository ;

	Date d = new Date();

	@Autowired
	FournisseurRepository fournisseurRepository;

	@Autowired
	DemandeArticleRepository demandeArticleRepository;
	
	@Autowired
	MagasinRotationNullRepository magasinRotationNullRepository ;

	@Override
	public List<BonCommandeDTO> getPaginationReceptionFournisseurListByStatus(String status, int page, int limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<BonCommandeEntity> bonCommandeEntities = new ArrayList<>();
		List<BonCommandeDTO> bonCommandeDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (status.equals("Réceptionné")) {
			Page<BonCommandeEntity> bonCommandeEntityPage = bonCommandeRepository.findAllByReceipt(true, pageable);
			bonCommandeEntities = bonCommandeEntityPage.getContent();
		} else if (status.equals("Non Réceptionné")) {
			Page<BonCommandeEntity> bonCommandeEntityPage = bonCommandeRepository.findAllByReceipt(false, pageable);
			bonCommandeEntities = bonCommandeEntityPage.getContent();
		} else {
			Page<BonCommandeEntity> bonCommandeEntityPage = bonCommandeRepository.findAll(pageable);
			bonCommandeEntities = bonCommandeEntityPage.getContent();
		}
		if (!bonCommandeEntities.isEmpty()) {
			bonCommandeEntities.forEach(bonCommandeEntity -> {
				bonCommandeDTOS.add(modelMapper.map(bonCommandeEntity, BonCommandeDTO.class));
			});
		}
		return bonCommandeDTOS;
	}

	@Override
	public Long getTotalItemsReceptionFournisseurByStatus(String status) {
		Long totalItem;
		PageRequest pageable = PageRequest.of(0, 8);
		if (status.equals("Non confirmé")) {
			Page<BonCommandeEntity> bonCommandeEntityPage = bonCommandeRepository.findAllByReceipt(true, pageable);
			totalItem = bonCommandeEntityPage.getTotalElements();
		} else if (status.equals("confirmé")) {
			Page<BonCommandeEntity> bonCommandeEntityPage = bonCommandeRepository.findAllByReceipt(false, pageable);
			totalItem = bonCommandeEntityPage.getTotalElements();
		} else {
			Page<BonCommandeEntity> bonCommandeEntityPage = bonCommandeRepository.findAll(pageable);
			totalItem = bonCommandeEntityPage.getTotalElements();
		}
		return totalItem;
	}

	@Override
	public void confirmReceptionFournisseur(BonCommandeDTO bonCommandeDTO) {
		BonCommandeEntity bonCommandeEntity = bonCommandeRepository.findById(bonCommandeDTO.getId()).get();
		BonCommandeEntity bonCommandeEntity1 = new BonCommandeEntity();

		
		if (bonCommandeEntity.getDemandesArticle() != null) {
			for (int i = 0; i < bonCommandeEntity.getDemandesArticle().size(); i++) {
				ArticleEntity articleEntity = articleRepository
						.findById(bonCommandeEntity.getDemandesArticle().get(i).getArticle().getId()).get();
				
				System.out.println("le bon de commande !!");
				System.out.println(articleEntity.getId());
				ArticlePricesEntity articlePricesEntity = new ArticlePricesEntity();
				
				
				//articlePricesEntity.setPrix();
				
				if (bonCommandeEntity.getDemandesArticle().get(i).isAccepted()) {
					articlePricesEntity.setArticle(articleEntity);
					articlePricesEntity.setPrix(bonCommandeDTO.getDemandesArticle().get(i).getPrix());
					articlePricesEntity.setDateArticle(d);
					articlePricesEntity.setQte(bonCommandeDTO.getDemandesArticle().get(i).getQuantiteLivree());	
					articlePricesRepository.save(articlePricesEntity)	;
					articleEntity.setQuantiteStock(articleEntity.getQuantiteStock()
							- bonCommandeEntity.getDemandesArticle().get(i).getQuantiteLivree()
							+ bonCommandeDTO.getDemandesArticle().get(i).getQuantiteLivree());
					bonCommandeEntity.getDemandesArticle().get(i)
							.setQuantiteCommandee(bonCommandeDTO.getDemandesArticle().get(i).getQuantiteCommandee());
					bonCommandeEntity.getDemandesArticle().get(i)
							.setQuantiteLivree(bonCommandeDTO.getDemandesArticle().get(i).getQuantiteLivree());
				} else {
					articlePricesEntity.setArticle(articleEntity);
					articlePricesEntity.setPrix(bonCommandeDTO.getDemandesArticle().get(i).getPrix());
					articlePricesEntity.setDateArticle(d);
					articlePricesEntity.setQte((bonCommandeDTO.getDemandesArticle().get(i).getQuantiteLivree()));	
					articlePricesRepository.save(articlePricesEntity)	;
					bonCommandeEntity.getDemandesArticle().get(i).setAccepted(true);
					bonCommandeEntity.getDemandesArticle().get(i)
							.setQuantiteCommandee(bonCommandeDTO.getDemandesArticle().get(i).getQuantiteCommandee());
					bonCommandeEntity.getDemandesArticle().get(i)
							.setQuantiteLivree(bonCommandeDTO.getDemandesArticle().get(i).getQuantiteLivree());
					articleEntity.setQuantiteStock(articleEntity.getQuantiteStock()
							+ bonCommandeDTO.getDemandesArticle().get(i).getQuantiteLivree());
				}
			}
		}
		bonCommandeEntity1.setParc(bonCommandeEntity.getParc());
		bonCommandeEntity1.setStatus(bonCommandeEntity.getStatus());
		bonCommandeEntity1.setDateDemande(d);
		bonCommandeEntity1.setCommandeHTBrut(bonCommandeEntity.getCommandeHTBrut());
		bonCommandeEntity1.setCommandeHTNet(bonCommandeEntity.getCommandeHTNet());
		bonCommandeEntity1.setCommandeTVA(bonCommandeEntity.getCommandeTVA());
		bonCommandeEntity1.setCommandeTimbreFix(bonCommandeEntity.getCommandeTimbreFix());
		bonCommandeEntity1.setCommandeTTC(bonCommandeEntity.getCommandeTTC());
		bonCommandeEntity1.setLivraisonHTBrut(bonCommandeEntity.getLivraisonHTBrut());
		bonCommandeEntity1.setLivraisonHTNet(bonCommandeEntity.getLivraisonHTNet());
		bonCommandeEntity1.setLivraisonTVA(bonCommandeEntity.getLivraisonTVA());
		bonCommandeEntity1.setLivraisonTimbreFix(bonCommandeEntity.getLivraisonTimbreFix());
		bonCommandeEntity1.setLivraisonTTC(bonCommandeEntity.getLivraisonTTC());
		bonCommandeEntity1.setMontantLivre(bonCommandeEntity.getMontantLivre());
		bonCommandeEntity1.setMontantFacture(bonCommandeEntity.getMontantFacture());
		bonCommandeEntity1.setDateFacture(bonCommandeEntity.getDateFacture());
		bonCommandeEntity1.setReferenceFacture(bonCommandeEntity.getReferenceFacture());
		bonCommandeEntity1.setMontantReglement(bonCommandeEntity.getMontantReglement());
		bonCommandeEntity1.setReferenceReglement(bonCommandeEntity.getReferenceReglement());
		bonCommandeEntity1.setReceipt(true);

		FournisseurEntity fournisseurEntity = fournisseurRepository.findById(bonCommandeEntity.getFournisseur().getId())
				.get();

		if (fournisseurEntity.getBonCommandes() == null) {
			fournisseurEntity.setBonCommandes(new ArrayList<>());
		}
		bonCommandeEntity1.setFournisseur(fournisseurEntity);
		fournisseurEntity.getBonCommandes().add(bonCommandeEntity1);

		bonCommandeEntity1.setDateReglement(bonCommandeEntity.getDateReglement());
		bonCommandeEntity1.setDemandesArticle(new ArrayList<>());
		if (bonCommandeEntity.getDemandesArticle() != null) {
			bonCommandeEntity.getDemandesArticle().forEach(updateDemandeArticleDTO -> {
				DemandeArticleEntity demandeArticleEntity = new DemandeArticleEntity();
				demandeArticleEntity.setDateDemandeArticle(LocalDate.now());
				ArticleEntity articleEntity = articleRepository
						.findFirstByCodeArticle(updateDemandeArticleDTO.getArticle().getCodeArticle());
				demandeArticleEntity.setQuantiteCommandee(updateDemandeArticleDTO.getQuantiteCommandee());
				demandeArticleEntity.setQuantiteLivree(updateDemandeArticleDTO.getQuantiteLivree());
				demandeArticleEntity.setAccepted(false);
				demandeArticleEntity.setArticle(articleEntity);
				if (articleEntity.getDemandeArticles() == null) {
					articleEntity.setDemandeArticles(new ArrayList<>());
				}
				articleEntity.getDemandeArticles().add(demandeArticleEntity);
				demandeArticleEntity.setBonCommande(bonCommandeEntity1);
				bonCommandeEntity1.getDemandesArticle().add(demandeArticleEntity);
			});
		}
		bonCommandeRepository.save(bonCommandeEntity1);
	}

	@Override
	public void addNewAriclesMV(UpdateMagasinVirtuelDTO updateMagasinVirtuelDTO) {
		MagasinVirtuelEntity magasinVirtuelEntity = new MagasinVirtuelEntity();
		/*magasinVirtuelEntity.setDateTransfert(d);
		magasinVirtuelEntity.setStatus("En cours");
System.out.print("\n test \n");
System.out.print(updateMagasinVirtuelDTO);
		magasinVirtuelEntity.setMagasinVirtuelArticle(new ArrayList<>());
		if (updateMagasinVirtuelDTO.getUpdateMagasinVirtuelArticle() != null) {
			updateMagasinVirtuelDTO.getUpdateMagasinVirtuelArticle().forEach(upMV -> {
				MagasinVirtuelArticleEntity magasinVirtuelArticleEntity = new MagasinVirtuelArticleEntity();
				ArticleEntity articleEntity = articleRepository
						.findFirstByCodeArticle(upMV.getUpdateArticle().getCodeArticle());
				
				System.out.print("\n test article \n");
				System.out.print(articleEntity.getCodeArticle());
				
				magasinVirtuelArticleEntity.setQteTransferer(upMV.getQteTransferer());
				magasinVirtuelArticleEntity.setArticle(articleEntity);
				
				System.out.print("\n test article 77777 \n");
				System.out.print(magasinVirtuelArticleEntity);

				if (articleEntity.getMagasinVirtuelArticle() == null) {
					articleEntity.setMagasinVirtuelArticle(new ArrayList<>());
				}
				articleEntity.getMagasinVirtuelArticle().add(magasinVirtuelArticleEntity);
				magasinVirtuelArticleEntity.setMagasinVirtuel(magasinVirtuelEntity);
				magasinVirtuelEntity.getMagasinVirtuelArticle().add(magasinVirtuelArticleEntity);
			});
		}
		magasinVirtuelRepository.save(magasinVirtuelEntity);
*/
	}

	@Override
	public void modifySelectedDemandeMagasinVirtuel(UpdateMagasinVirtuelDTO updateMagasinVirtuelDTO) {
		/*System.out.println(updateMagasinVirtuelDTO.getId());

		MagasinVirtuelEntity magasinVirtuelEntity = magasinVirtuelRepository.findById(updateMagasinVirtuelDTO.getId())
				.get();
		magasinVirtuelEntity.setMagasinVirtuelArticle(new ArrayList<>());
		if (updateMagasinVirtuelDTO.getUpdateMagasinVirtuelArticle() != null) {
			updateMagasinVirtuelDTO.getUpdateMagasinVirtuelArticle().forEach(upMV -> {
				MagasinVirtuelArticleEntity magasinVirtuelArticleEntity = new MagasinVirtuelArticleEntity();
				ArticleEntity articleEntity = articleRepository
						.findFirstByCodeArticle(upMV.getUpdateArticle().getCodeArticle());
				magasinVirtuelArticleEntity.setQteTransferer(upMV.getQteTransferer());
				magasinVirtuelArticleEntity.setArticle(articleEntity);
				if (articleEntity.getMagasinVirtuelArticle() == null) {
					articleEntity.setMagasinVirtuelArticle(new ArrayList<>());
				}
				articleEntity.getMagasinVirtuelArticle().add(magasinVirtuelArticleEntity);
				magasinVirtuelArticleEntity.setMagasinVirtuel(magasinVirtuelEntity);
				magasinVirtuelEntity.getMagasinVirtuelArticle().add(magasinVirtuelArticleEntity);
			});
		}
		magasinVirtuelRepository.save(magasinVirtuelEntity);
*/
	}

	@Override
	public void deleteSelectedMV(Long id) {
		magasinVirtuelRepository.deleteById(id);

	}

	@Override
	public List<MagasinVirtuelDataTableDTO> getPaginationMagasinVirtuelList(String ugp, int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		Page<MagasinVirtuelEntity> magasinVirtuelEntityPage = magasinVirtuelRepository.findAll(pageable);
		List<MagasinVirtuelEntity> magasinVirtuelEntities = magasinVirtuelRepository.findAll(pageable).getContent();

		if ((ugp.length() > 0)) {
			Page<MagasinVirtuelEntity> magasinVirtuelEntity = magasinVirtuelRepository.findAllByUgp(ugp, pageable);
			magasinVirtuelEntities = magasinVirtuelEntity.getContent();

		}
		return this.loadListMagasinDataTableDTO(magasinVirtuelEntities);
	}

	private List<MagasinVirtuelDataTableDTO> loadListMagasinDataTableDTO(
			List<MagasinVirtuelEntity> magasinVirtuelEntities) {
		List<MagasinVirtuelDataTableDTO> magasinVirtuelDataTableDTOS = new ArrayList<>();
		if (!magasinVirtuelEntities.isEmpty()) {
			magasinVirtuelEntities.forEach(magasinVirtuelEntity -> {
				MagasinVirtuelDataTableDTO magasinVirtuelDataTableDTO = new MagasinVirtuelDataTableDTO();
				magasinVirtuelDataTableDTO.setId(magasinVirtuelEntity.getId());
				magasinVirtuelDataTableDTO.setDateTransfert(magasinVirtuelEntity.getDateTransfert());
				magasinVirtuelDataTableDTO.setStatus(magasinVirtuelEntity.getStatus());
				magasinVirtuelDataTableDTO.setConfirmed(magasinVirtuelEntity.getConfirmed());
				magasinVirtuelDataTableDTO.setValidated(magasinVirtuelEntity.getValidated());
				magasinVirtuelDataTableDTOS.add(magasinVirtuelDataTableDTO);

			});
		}
		return magasinVirtuelDataTableDTOS;

	}

	@Override
	public RetourStructure addNewRetourStructure(NewRetourDTO retourStructure) {
		List<UpdateForRetourRequest> updateRetourRequestList = retourStructure.getArticles();
		RetourStructure retourStructureEntity = new RetourStructure();
		retourStructureEntity.setDateSortie(retourStructure.getDateSortie());
		retourStructureEntity.setStructure(retourStructure.getStructure());
		retourStructureEntity.setMagasin(retourStructure.getMagasin());
		List<ArticleEntity> listArticle = new ArrayList<>();
		ArticleEntity article = new ArticleEntity();
		RetourStructure retourentity = new RetourStructure();
		retourentity = retourRepository.save(retourStructureEntity);

		for (int i = 0; i < updateRetourRequestList.size(); i++) {
			UpdateForRetourRequest updateRetourRequest = new UpdateForRetourRequest();
			updateRetourRequest.setId(updateRetourRequestList.get(i).getId());
			updateRetourRequest.setQuantiteStock(updateRetourRequestList.get(i).getQuantiteStock());
			updateRetourRequest.setRetourStructure(retourentity.getId());
			updateRetourRequest.setCodeArticle(updateRetourRequestList.get(i).getCodeArticle());
			ModelMapper modelMapper = new ModelMapper();
			UpdateRetourStructureDTO updateRetourDTO = modelMapper.map(updateRetourRequest,
					UpdateRetourStructureDTO.class);
			referentiel.modifySelectedRetourStructure(updateRetourDTO);
			ArticleEntity articleEntity = articleRepository.findFirstByCodeArticle(updateRetourDTO.getCodeArticle());
			listArticle.add(articleEntity);

		}
		retourentity.setArticles(listArticle);
		return retourRepository.save(retourentity);

	}

	public List<ListRetourDTO> getPaginationRetourList(String magasin, String ugp, int page, int limit) {

		List<RetourStructure> retourEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if ((ugp.length() == 0) && (magasin.length() > 0)) {
			Page<RetourStructure> retourEntity = retourRepository.getRetourByMagasin(magasin, pageable);
			retourEntities = retourEntity.getContent();

		} else if ((ugp.length() > 0 && (magasin.length()) == 0)) {
			Page<RetourStructure> retourEntity = retourRepository.getRetourByUPG(ugp, pageable);
			retourEntities = retourEntity.getContent();
		}

		else if ((ugp.length() > 0) && (magasin.length() > 0)) {
			Page<RetourStructure> retourEntity = retourRepository.getRetourByUPGMagasin(magasin, ugp, pageable);
			retourEntities = retourEntity.getContent();

		} else if ((magasin.length() == 0) && (ugp.length() == 0)) {
			Page<RetourStructure> retourEntityPage = retourRepository.findAll(pageable);
			retourEntities = retourEntityPage.getContent();

		}

		return this.loadRetourDataTableDTO(retourEntities);
	}

	private List<ListRetourDTO> loadRetourDataTableDTO(List<RetourStructure> retourEntities) {
		List<ListRetourDTO> ListRetourDTOS = new ArrayList<>();

		if (!retourEntities.isEmpty()) {
			retourEntities.forEach(RetourEntity -> {

				ListRetourDTO ListRetourDTO = new ListRetourDTO();

				ListRetourDTO.setId(RetourEntity.getId());
				ListRetourDTO.setDateSortie((RetourEntity.getDateSortie()));
				ListRetourDTO.setMagasin(RetourEntity.getMagasin());
				ListRetourDTO.setStructure(RetourEntity.getStructure());
				ListRetourDTOS.add(ListRetourDTO);
			});
		}
		return ListRetourDTOS;

	}

	public Long getTotalItemsRetourList() {

		PageRequest pageable = PageRequest.of(0, 8);

		Page<RetourStructure> retourEntityPage = retourRepository.findAll(pageable);

		return retourEntityPage.getTotalElements();

	}

	@Override
	public void deleteSelectedArticleForRetour(RetourStructure retour) {
		for (int i = 0; i < retour.getArticles().size(); i++) {
			retour.getArticles().get(i).setRetourStructure(null);
			articleRepository.save(retour.getArticles().get(i));
		}
	}

	@Override
	public void deleteSelectedRetourArticle(Long id) {
		RetourStructure retour = retourRepository.findRetourStructureById(id);
		if (retour.getArticles().isEmpty() != true) {
			deleteSelectedArticleForRetour(retour);
		}
		retour.getArticles().remove(retour);
		retourRepository.save(retour);
		retourRepository.delete(retour);
	}

	@Override
	public RetourStructure getRetourArticle(Long id) {
		return retourRepository.findById(id).get();
	}

	@Override
	public Iterable<RetourStructure> getAllRetourStructures() {

		return retourRepository.findAll();
	}

	// --------------------------

	@Override
	public RegulationArticleStock addNewRegulationArticle(NewRegulationDTO regulationArticle) {
		RegulationArticleStock regulationArticleEntity = new RegulationArticleStock();
		regulationArticleEntity.setCreationDate(regulationArticle.getCreationDate());
		regulationArticleEntity.setType_mouvement(regulationArticle.getType_mouvement());
		regulationArticleEntity.setObservation(regulationArticle.getObservation());
		regulationArticleEntity.setQuantite_modifier(regulationArticle.getQuantite_modifier());
		regulationArticleEntity.setCodeArticle(regulationArticle.getCodeArticle());
		regulationArticleEntity.setIdArticle(regulationArticle.getIdArticle());
		ArticleEntity articleForRegulation= new ArticleEntity();
		
		articleForRegulation = articleRepository.findArticleById(regulationArticle.getIdArticle());
		if (regulationArticle.getType_mouvement().equalsIgnoreCase( "Sortie")) {
		articleForRegulation.setQuantiteStock(articleForRegulation.getQuantiteStock() - regulationArticle.getQuantite_modifier());
		
		}
		else {
			articleForRegulation.setQuantiteStock(articleForRegulation.getQuantiteStock() + regulationArticle.getQuantite_modifier());
			articleRepository.save(articleForRegulation);
		}
		return regulationRepository.save(regulationArticleEntity);
	}

	
	private List<ListRegulationArticleDTO> loadRegulationArticleTabDataDTO(
			List<RegulationArticleStock> regulationArticles) {
		List<ListRegulationArticleDTO> listRegulationArticleDTOs = new ArrayList<>();
		if (!regulationArticles.isEmpty()) {
			regulationArticles.forEach(regulationEntity -> {
				ListRegulationArticleDTO listRegulationArticleDTO = new ListRegulationArticleDTO();
				listRegulationArticleDTO.setId(regulationEntity.getId());
				listRegulationArticleDTO.setCreationDate(regulationEntity.getCreationDate());
				listRegulationArticleDTO.setObservation(regulationEntity.getObservation());
				listRegulationArticleDTO.setCodeArticle(regulationEntity.getCodeArticle());
				listRegulationArticleDTO.setType_mouvement(regulationEntity.getType_mouvement());
				listRegulationArticleDTO.setQuantite_modifier(regulationEntity.getQuantite_modifier());
				listRegulationArticleDTOs.add(listRegulationArticleDTO);
			});
		}
		return listRegulationArticleDTOs;
	}

	@Override
	public List<ListRegulationArticleDTO> getPaginationRegulationArticleList(String ugp, int page, int limit) {
		List<RegulationArticleStock> regulationArticles = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		 if (ugp.length() > 0 ) {
			Page<RegulationArticleStock> regulationEntity = regulationRepository.getRegulationByUPG(ugp, pageable);
			regulationArticles = regulationEntity.getContent();
		}
		 else  {
			Page<RegulationArticleStock> regulationEntityPage = regulationRepository.findAll(pageable);
			regulationArticles = regulationEntityPage.getContent();

		}
		return this.loadRegulationArticleTabDataDTO(regulationArticles);
			
	}

	

	   @Override
	    public void modifySelectedRegulation(UpdateRegulationDTO updateRegulationDTO) {
		   RegulationArticleStock updateRegulation = regulationRepository.findById(updateRegulationDTO.getId()).get();
		   updateRegulation.setObservation(updateRegulationDTO.getObservation());
		   regulationRepository.save(updateRegulation);
	    }
	   
	   
	@Override
	public void updateRegulation(RegulationArticleStock regulationArticleStock) {
		RegulationArticleStock regulation = regulationRepository
				.findRegulationArticleStockById(regulationArticleStock.getId());
		regulation.setObservation(regulationArticleStock.getObservation());
	}

	@Override
	public void deleteSelectedRegulationArticle(Long id) {
		RegulationArticleStock regulation = regulationRepository.findRegulationArticleStockById(id);
		 HistoriqueRegulation historiqueRegulation = new HistoriqueRegulation();
		historiqueRegulation.setDateRegulation(regulation.getCreationDate());
		historiqueRegulation.setQuantite_modifier(regulation.getQuantite_modifier());
		historiqueRegulation.setId(regulation.getId());
		historiqueRegulation.setType_mouvement(regulation.getType_mouvement());
		historiqueRegulation.setCodeArticle(regulation.getCodeArticle());
		historiqueRegulation.setIdRegulation(regulation.getId());
		
		 historiqueRepository.save(historiqueRegulation);
		regulationRepository.delete(regulation);
	}

	@Override
	public RegulationArticleStock getRegulationArticle(Long id) {
		return regulationRepository.findById(id).get();
	}

	@Override
	public Iterable<RegulationArticleStock> getAllRegulationArticles() {

		return regulationRepository.findAll();
	}

	public Long getTotalNumberRegulationArticle() {
		PageRequest pageable = PageRequest.of(0, 8);
		return regulationRepository.findAll(pageable).getTotalElements();
	}

	public void confirmTransfert(long id) {
		MagasinVirtuelEntity m = magasinVirtuelRepository.findById(id).get();
		m.setConfirmed(true);
		magasinVirtuelRepository.save(m);
	}

	@Override

	public Long getTotalItemsRegulationStockList() {

		PageRequest pageable = PageRequest.of(0, 8);

		Page<RegulationArticleStock> regulationStockEntityPage = regulationRepository.findAll(pageable);

		return regulationStockEntityPage.getTotalElements();

	}
	
	
	/*
	 
	@Override
	public List<ListRegulationArticleDTO> getPaginationRegulationList(String ugp, String magasin, int page, int limit) {

		List<RegulationArticleStock> RegulationArticleStockEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if ((ugp.length() == 0) && (magasin.length() > 0)) {
			Page<RegulationArticleStock> RegulationArticleStockEntity = regulationRepository
					.getRegulationByMagasin(magasin, pageable);
			RegulationArticleStockEntities = RegulationArticleStockEntity.getContent();

		} else if ((ugp.length() > 0 && (magasin.length()) == 0)) {
			Page<RegulationArticleStock> RegulationArticleStockEntity = regulationRepository.getRegulationByUPG(ugp,
					pageable);
			RegulationArticleStockEntities = RegulationArticleStockEntity.getContent();
		}

		else if ((ugp.length() > 0) && (magasin.length() > 0)) {
			Page<RegulationArticleStock> RegulationArticleStockEntity = regulationRepository
					.getRegulationByUPGMagasin(magasin, ugp, pageable);
			RegulationArticleStockEntities = RegulationArticleStockEntity.getContent();
			System.out.println("par deux " + RegulationArticleStockEntities);
		} else if ((magasin.length() == 0) && (ugp.length() == 0)) {
			Page<RegulationArticleStock> regulationArticleStockEntityPage = regulationRepository.findAll(pageable);
			RegulationArticleStockEntities = regulationArticleStockEntityPage.getContent();

		}

		return this.loadRegulationArticleDataTableDTO(RegulationArticleStockEntities);
	}

	 */
	
	
	@Override
	public List<ListRegulationArticleDTO> getPaginationRegulationList(String ugp, int page, int limit) {
	List<RegulationArticleStock> RegulationArticleStockEntities = new ArrayList<>();
	PageRequest pageable = PageRequest.of(page, limit);
	 if (ugp.length() > 0) {
	Page<RegulationArticleStock> RegulationArticleStockEntity = regulationRepository.getRegulationByUPG(ugp,
	pageable);
	RegulationArticleStockEntities = RegulationArticleStockEntity.getContent();
	}
 else {
	Page<RegulationArticleStock> regulationArticleStockEntityPage = regulationRepository.findAll(pageable);
	RegulationArticleStockEntities = regulationArticleStockEntityPage.getContent();
	}
	return this.loadRegulationArticleDataTableDTO(RegulationArticleStockEntities);
	}

	private List<ListRegulationArticleDTO> loadRegulationArticleDataTableDTO(
			List<RegulationArticleStock> RegulationArticleStockEntities) {
		List<ListRegulationArticleDTO> ListRegulationArticleDTOS = new ArrayList<>();
		if (!RegulationArticleStockEntities.isEmpty()) {
			RegulationArticleStockEntities.forEach(RegulationArticleStockEntity -> {
				ListRegulationArticleDTO ListRegulationArticleDTO = new ListRegulationArticleDTO();
				ListRegulationArticleDTO.setId(RegulationArticleStockEntity.getId());
				ListRegulationArticleDTO.setId(RegulationArticleStockEntity.getId());
				ListRegulationArticleDTO.setCreationDate(RegulationArticleStockEntity.getCreationDate());
				ListRegulationArticleDTO.setObservation(RegulationArticleStockEntity.getObservation());
				ListRegulationArticleDTO.setType_mouvement(RegulationArticleStockEntity.getType_mouvement());
				ListRegulationArticleDTO.setQuantite_modifier(RegulationArticleStockEntity.getQuantite_modifier());
				ListRegulationArticleDTO.setCodeArticle(RegulationArticleStockEntity.getCodeArticle());
				ListRegulationArticleDTO.setIdArticle(RegulationArticleStockEntity.getIdArticle());
			});
		}
		return ListRegulationArticleDTOS;

	}

	@Override
	public List<ListRegulationArticleDTO> getPaginationRegulationList(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//===============Transfert de parc vers magasin=============================================
		@Override
		
			public void addNewAriclesUgp(UpdateParcTransfertDTO updateParcTransfertDTO) {
				ParcTransfertEntity parcTransfertEntity = new ParcTransfertEntity();
				parcTransfertEntity.setDateTransfert(d);

				parcTransfertEntity.setParcTransfertArticle(new ArrayList<>());
				if (updateParcTransfertDTO.getUpdateParcTransfertArticle() != null) {
					updateParcTransfertDTO.getUpdateParcTransfertArticle().forEach(upMV -> {
						ParcTransfertArticleEntity parcTransfertArticleEntity = new ParcTransfertArticleEntity();
//						ArticleEntity articleEntity = articleRepository
//								.findFirstByCodeArticle(upMV.getUpdateArticle().getCodeArticle());
//						
						ArticleEntity articleEntity = articleRepository
								.findById(upMV.getUpdateArticle().getId()).get();
						
						
						parcTransfertArticleEntity.setQteTransferer(upMV.getQteTransferer());
						parcTransfertArticleEntity.setMagasinDestinataire(upMV.getMagasinDestinataire());
						System.out.println("test");
						parcTransfertArticleEntity.setArticle(articleEntity);
						if (articleEntity.getParcTransfertArticle() == null) {
							articleEntity.setParcTransfertArticle(new ArrayList<>());
						}
						articleEntity.getParcTransfertArticle().add(parcTransfertArticleEntity);
						parcTransfertArticleEntity.setParcTransfert(parcTransfertEntity);
						parcTransfertEntity.getParcTransfertArticle().add(parcTransfertArticleEntity);
					});
		
					System.out.println("test2");
				}
				parcRepository.save(parcTransfertEntity);
				System.out.println("test9");
		}
	
	@Override
	public List<ParcTransfertDTO> getPaginationUgpList(String ugp ,int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		Page<ParcTransfertEntity> parcEntityPage = parcRepository.findAll(pageable);
		List<ParcTransfertEntity> parcEntities = parcRepository.findAll(pageable).getContent();

	if((ugp.length() > 0)) {
			Page<ParcTransfertEntity> parcEntity = parcRepository.findAllByUgp(ugp, pageable);
			parcEntities = parcEntity.getContent();

		}
		return this.loadListUgpDTO(parcEntities);
	}

	private List<ParcTransfertDTO> loadListUgpDTO(
			List<ParcTransfertEntity> parcEntities) {
		List<ParcTransfertDTO> parcDTOS = new ArrayList<>();
		if (!parcEntities.isEmpty()) {
			parcEntities.forEach(parcEntity -> {
				ParcTransfertDTO parcDTO = new ParcTransfertDTO();
				parcDTO.setId(parcEntity.getId());
				parcDTO.setDateTransfert(parcEntity.getDateTransfert());
				parcDTO.setStatus(parcEntity.getStatus());
				parcDTO.setConfirmed(parcEntity.getConfirmed());
				parcDTO.setValidated(parcEntity.getValidated());
				parcDTO.setParcTransfertArticle(parcEntity.getParcTransfertArticle());
				parcDTOS.add(parcDTO);
				

			});
		}
		return parcDTOS;
	}
	
	public Long getTotalItemParcTransfertList() 
	{
		PageRequest pageable = PageRequest.of(0, 8);
		return parcRepository.findAll(pageable).getTotalElements();
	}
	
	@Override
	public void deleteSelectedParcTransfert(Long id) {
		parcRepository.deleteById(id);

	}
	
	@Override
	public void confirmParcTransfert(long id) {
		ParcTransfertEntity p = parcRepository.findById(id).get();
		p.setConfirmed(true);
		parcRepository.save(p);
	}
	
	@Override
	public void validParcTransfert(long id) {
		ParcTransfertEntity p = parcRepository.findById(id).get();
		p.setValidated(true);
		parcRepository.save(p);
	}

	private void dArticle(Long id) {
		System.out.println("ramla rupp 1 !");
		System.out.println(id);
		List<ParcTransfertArticleEntity> p = ugpArticleRepository.findByParcTransfertId(id);

		for (Iterator iterator = p.iterator(); iterator.hasNext();) {
		ParcTransfertArticleEntity parcTransfertArticleEntity = (ParcTransfertArticleEntity) iterator.next();

		System.out.println("hello Ramla");
		if (parcTransfertArticleEntity != null) {
		System.out.println("Ramla suppression");
		System.out.println(parcTransfertArticleEntity.getId());
		ugpArticleRepository.delete(parcTransfertArticleEntity);
		}

		}

		}

		@Override
		public void modifySelectedDemandeTransfertParc(UpdateParcTransfertDTO updateParcTransfertDTO) {
		System.out.println(updateParcTransfertDTO.getId());



		ParcTransfertEntity parcTransfertEntity = parcRepository.findById(updateParcTransfertDTO.getId())
		.get();
		parcTransfertEntity.setParcTransfertArticle(new ArrayList<>());
		if (updateParcTransfertDTO.getUpdateParcTransfertArticle() != null) {
		this.dArticle(updateParcTransfertDTO.getId());
		System.out.println("test ramla");
		System.out.println(updateParcTransfertDTO.getId());
		updateParcTransfertDTO.getUpdateParcTransfertArticle().forEach(upMV -> {

		ParcTransfertArticleEntity parcTransfertArticleEntity = new ParcTransfertArticleEntity();
		ArticleEntity articleEntity = articleRepository
		.findById(upMV.getUpdateArticle().getId()).get();

		parcTransfertArticleEntity.setQteTransferer(upMV.getQteTransferer());
		parcTransfertArticleEntity.setMagasinDestinataire(upMV.getMagasinDestinataire());
		parcTransfertArticleEntity.setArticle(articleEntity);
		if (articleEntity.getParcTransfertArticle() == null) {
		articleEntity.setParcTransfertArticle(new ArrayList<>());
		}
		articleEntity.getParcTransfertArticle().add(parcTransfertArticleEntity);
		parcTransfertArticleEntity.setParcTransfert(parcTransfertEntity);
		parcTransfertEntity.getParcTransfertArticle().add(parcTransfertArticleEntity);
		});
		}
		parcRepository.save(parcTransfertEntity);



		}
	//===============Transfert de parc vers magasin=============================================
	
	
	@Override
	public List<HistoriqueRegulation> getPaginationHistoriqueRegulationList(int page, int limit) {
		
		List<HistoriqueRegulation> HistoriqueRegulationEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<HistoriqueRegulation> historiqueRegulationEntityPage = historiqueRepository.findAll(pageable);
		HistoriqueRegulationEntities = historiqueRegulationEntityPage.getContent();
		System.out.print("test3");
		System.out.print(HistoriqueRegulationEntities);
		return HistoriqueRegulationEntities;
	}
	@Override
	public void deleteSelectedHistoriqueRegulation(Long id) {
		HistoriqueRegulation historique = historiqueRepository.findHistoriqueRegulationById(id);	 ;
		historiqueRepository.delete(historique);
	}
	@Override
	public List<ArticleEntity> getPaginationArticleList(int page, int limit) {
		
		List<ArticleEntity> HistoriqueRegulationEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		Page<ArticleEntity> historiqueRegulationEntityPage = articleRepository.findAll(pageable);
		HistoriqueRegulationEntities = historiqueRegulationEntityPage.getContent();
		System.out.print("test3");
		System.out.print(HistoriqueRegulationEntities);
		return HistoriqueRegulationEntities;
	}

	public List<MagasinRotationNullDTO> getPaginationMagasinRotationNullList(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<MagasinRotationNull>  magasinRotationNull = magasinRotationNullRepository.findAll(pageable)
				.getContent();
		return this.loadMagasinRotationNullRepositoryTabDataDTO(magasinRotationNull);
	}



			
	private List<MagasinRotationNullDTO> loadMagasinRotationNullRepositoryTabDataDTO(
			List<MagasinRotationNull> magasinRotationNull) {
		List<MagasinRotationNullDTO> RechargeSousCompteTabDataDTOS = new ArrayList<>();
		if (!magasinRotationNull.isEmpty()) {
			magasinRotationNull.forEach(magasinRotationNullEntity -> {
				
				MagasinRotationNullDTO listRechargeSousCompteDTO = new MagasinRotationNullDTO();
				listRechargeSousCompteDTO.setId(magasinRotationNullEntity.getId());
				listRechargeSousCompteDTO.setValidated(magasinRotationNullEntity.getValidated());
				listRechargeSousCompteDTO.setConfirmed(magasinRotationNullEntity.getConfirmed());
				listRechargeSousCompteDTO.setCodeArticle(magasinRotationNullEntity.getCodeArticle()	);
				
				listRechargeSousCompteDTO.setPrix(magasinRotationNullEntity.getPrix());
				listRechargeSousCompteDTO.setCodeArticle(magasinRotationNullEntity.getCodeArticle()	);
				listRechargeSousCompteDTO.setQuantiteStock(magasinRotationNullEntity.getQuantiteStock());
				listRechargeSousCompteDTO.setDesignation(magasinRotationNullEntity.getDesignation());

				RechargeSousCompteTabDataDTOS.add(listRechargeSousCompteDTO);
			});
		}

		return RechargeSousCompteTabDataDTOS;
	}		
		
	

	@Override
	public void deleteMagasinRotationNull(Long id) 
	{
		magasinRotationNullRepository.deleteById(id);

	}
	
	@Override
	public void confirmSelectedMagasinRotationNull(Long id) {
		MagasinRotationNull rechargeSousCompte = magasinRotationNullRepository.findById(id).get();
		rechargeSousCompte.setConfirmed(true);
		magasinRotationNullRepository.save(rechargeSousCompte);
	}

	@Override
	public void validateSelectedMagasinRotationNull(Long id) {
		MagasinRotationNull rechargeSousCompte = magasinRotationNullRepository.findById(id).get();
		rechargeSousCompte.setValidated(true);
		ArticleEntity article = articleRepository.findByCode(rechargeSousCompte.getCodeArticle());
		magasinRotationNullRepository.save(rechargeSousCompte);
		System.out.print("\n offffff menk \n");
         article.setQuantiteStock(0);
		System.out.print(article.getId());
		this.deleteArticle(article);
		articleRepository.save(article);


	}

	private void deleteArticle(ArticleEntity article) {
		System.out.print("\n offffff menk 22222222222\n");
		articleRepository.delete(article);
		
		

	}

	@Override
	public MagasinRotationNull addNewMagasinRotationNull(MagasinRotationNull magasinRotationNull) {
		
		ArticleEntity article = articleRepository.findByCode(magasinRotationNull.getCodeArticle());
		magasinRotationNull.setConfirmed(false);
		magasinRotationNull.setValidated(false);
		magasinRotationNull.setDesignation(article.getDesignation());
		magasinRotationNull.setQuantiteStock(article.getQuantiteStock());
		magasinRotationNull.setPrix(article.getPrix());
		
		articleRepository.delete(article);
		return magasinRotationNullRepository.save(magasinRotationNull);

	}
	
	
	@Override
	public void ReTransfertSelectedMagasinRotationNull(Long id) {
		MagasinRotationNull rechargeSousCompte = magasinRotationNullRepository.findById(id).get();

		ArticleEntity article = articleRepository.findByCode(rechargeSousCompte.getCodeArticle());
        article.setQuantiteStock(rechargeSousCompte.getQuantiteStock());
		System.out.print("\n offffff menk \n");
		articleRepository.save(article);
		magasinRotationNullRepository.delete(rechargeSousCompte);
	}

	@Override
	public void ReTransfertSelectedMagasinRotationNull(MagasinRotationNull magasinRotationNull) {
		// TODO Auto-generated method stub
		
	} 
	
	@Override
	public List<ListInventaireDTO> geTInventaire() {
		List<ArticleEntity>  magasinRotationNull = (List<ArticleEntity>) articleRepository.findAll();		
		return this.loadTabDataDTO(magasinRotationNull);
	}
	private List<ListInventaireDTO> loadTabDataDTO(
			List<ArticleEntity> magasinRotationNull) {
		List<ListInventaireDTO> RechargeSousCompteTabDataDTOS = new ArrayList<>();
		if (!magasinRotationNull.isEmpty()) {
			magasinRotationNull.forEach(magasinRotationNullEntity -> {
				
				ListInventaireDTO listRechargeSousCompteDTO = new ListInventaireDTO();
				listRechargeSousCompteDTO.setCodeArticle(magasinRotationNullEntity.getCodeArticle());
				listRechargeSousCompteDTO.setQuantiteStock(magasinRotationNullEntity.getQuantiteStock());
				listRechargeSousCompteDTO.setDesignation(magasinRotationNullEntity.getDesignation());
				listRechargeSousCompteDTO.setPrix(magasinRotationNullEntity.getPrix());
				listRechargeSousCompteDTO.setTva(magasinRotationNullEntity.getTva());
				RechargeSousCompteTabDataDTOS.add(listRechargeSousCompteDTO);
			});
		}

		return RechargeSousCompteTabDataDTOS;
	}

		
	
}