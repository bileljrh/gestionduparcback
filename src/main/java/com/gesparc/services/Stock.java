package com.gesparc.services;

import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.stock.HistoriqueRegulation;
import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.MagasinRotationNull;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.RetourStructure;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;
import com.gesparc.sharedDTO.maintenance.BonTravailDTO;
import com.gesparc.sharedDTO.maintenance.additional.UpdateBonTravailArticleDTO;
import com.gesparc.sharedDTO.referentiel.UgpDTO;
import com.gesparc.sharedDTO.stock.HistoriqueRegulationDTO;
import com.gesparc.sharedDTO.stock.ListInventaireDTO;
import com.gesparc.sharedDTO.stock.ListInventaireStockDTO;
import com.gesparc.sharedDTO.stock.ListRegulationArticleDTO;
import com.gesparc.sharedDTO.stock.ListRetourDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.NewInventaireStockDTO;
import com.gesparc.sharedDTO.stock.NewRegulationDTO;
import com.gesparc.sharedDTO.stock.NewRetourDTO;
import com.gesparc.sharedDTO.stock.ParcTransfertDTO;
import com.gesparc.sharedDTO.stock.UpdateParcTransfertDTO;
import com.gesparc.sharedDTO.stock.additionnel.MagasinVirtuelDataTableDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateRegulationDTO;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.RegulationArticleStock;
import com.gesparc.entities.stock.RetourStructure;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;
import com.gesparc.sharedDTO.stock.ListInventaireStockDTO;
import com.gesparc.sharedDTO.stock.ListRegulationArticleDTO;
import com.gesparc.sharedDTO.stock.ListRetourDTO;
import com.gesparc.sharedDTO.stock.MagasinVirtuelDTO;
import com.gesparc.sharedDTO.stock.NewInventaireStockDTO;
import com.gesparc.sharedDTO.stock.NewRegulationDTO;
import com.gesparc.sharedDTO.stock.NewRetourDTO;
import com.gesparc.sharedDTO.stock.additionnel.MagasinVirtuelDataTableDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;

import java.util.List;

@EnableAutoConfiguration
@Service
public interface Stock {

	List<BonCommandeDTO> getPaginationReceptionFournisseurListByStatus(String type, int page, int limit);

	Long getTotalItemsReceptionFournisseurByStatus(String type);

	void confirmReceptionFournisseur(BonCommandeDTO bonCommandeDTO);

	void addNewAriclesMV(UpdateMagasinVirtuelDTO updateMagasinVirtuelDTO);

	List<MagasinVirtuelDataTableDTO> getPaginationMagasinVirtuelList(String ugp, int page, int limit);

	void confirmTransfert(long id);

	List<ListRegulationArticleDTO> getPaginationRegulationArticleList(String ugp, int page, int limit);

	void deleteSelectedRegulationArticle(Long id);

	RegulationArticleStock getRegulationArticle(Long id);

	Iterable<RegulationArticleStock> getAllRegulationArticles();

	RegulationArticleStock addNewRegulationArticle(NewRegulationDTO regulationArticle);

	void deleteSelectedMV(Long id);

	void modifySelectedDemandeMagasinVirtuel(UpdateMagasinVirtuelDTO updateMagasinVirtuelDTO);

	//List<ListRegulationArticleDTO> getPaginationRegulationList(String ugp, String magasin, int page, int limit);

	List<ListRetourDTO> getPaginationRetourList(String magasin, String ugp, int page, int limit);

	Long getTotalItemsRegulationStockList();

	RetourStructure addNewRetourStructure(NewRetourDTO retourStructure);

	void updateRegulation(RegulationArticleStock regulationArticleStock);

	void deleteSelectedRetourArticle(Long id);

	void deleteSelectedArticleForRetour(RetourStructure retour);

	RetourStructure getRetourArticle(Long id);

	Iterable<RetourStructure> getAllRetourStructures();

	List<ListRegulationArticleDTO> getPaginationRegulationList(int page, int limit);

	List<ListRegulationArticleDTO> getPaginationRegulationList(String ugp, int page, int limit);

	void modifySelectedRegulation(UpdateRegulationDTO updateRegulationDTO);

	

	List<ParcTransfertDTO> getPaginationUgpList(String ugp,int page, int limit);
	List<HistoriqueRegulation> getPaginationHistoriqueRegulationList(int page, int limit);

	void deleteSelectedHistoriqueRegulation(Long id);

	void deleteSelectedParcTransfert(Long id);

	void confirmParcTransfert(long id);

	void validParcTransfert(long id);

	void addNewAriclesUgp(UpdateParcTransfertDTO updateparcDTO);

	void modifySelectedDemandeTransfertParc(UpdateParcTransfertDTO updateParcTransfertDTO);
	List<ArticleEntity> getPaginationArticleList(int page, int limit);
	void deleteMagasinRotationNull(Long id);

	void confirmSelectedMagasinRotationNull(Long id);

	void validateSelectedMagasinRotationNull(Long id);

	MagasinRotationNull addNewMagasinRotationNull(MagasinRotationNull magasinRotationNull);

	void ReTransfertSelectedMagasinRotationNull(MagasinRotationNull magasinRotationNull);

	void ReTransfertSelectedMagasinRotationNull(Long id);

	List<ListInventaireDTO> geTInventaire();



}
  
