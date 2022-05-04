package com.gesparc.services;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.GenreVehiculeEntity;
import com.gesparc.entities.referentiel.MarqueVehiculeEntity;
import com.gesparc.entities.referentiel.TypeVehiculeEntity;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.MarcheDTO;
import com.gesparc.sharedDTO.referentiel.*;
import com.gesparc.sharedDTO.referentiel.additionnel.ListFamilleAndSousFamilleDTO;

import java.util.List;

@EnableAutoConfiguration
@Service
public interface Referentiel 
{

    List<FamilleArticleDTO> getListFamilleArticle();

    List<SousFamilleArticleDTO> getListSousFamilleArticle();

    void addNewFamilleArticle(FamilleArticleDTO familleArticleDTO);

    void addNewSousFamilleArticle(SousFamilleArticleDTO sousFamilleArticleDTO);

    void deleteSelectedFamilleArticle(Long idFamille);

    void deleteSelectedSousFamilleArticle(Long idSousFamille);

    List<ArticleDTO> getPaginationListArticle(int page, int limit);
    
    List<MarcheDTO> getPaginationListMarche(int page, int limit);
    
    List<FournisseurListDTO> getListFournisseurBonCommande();

    Long getTotalItemArticleBySelected();

    void createNewArticle(UpdateArticleDTO updateArticleDTO);

    void modifySelectedArticle(UpdateArticleDTO updateArticleDTO);
    

    //ajouter le 1/07/2021 UpdateArticleDTO
    void modifySelectedFamilleArticle(SousFamilleArticleDTO sousFamilleArticleDTO);
    
    void deleteSelectedArticle(Long id);

    List<ListFamilleAndSousFamilleDTO> getListFamilleAndSousFamille();

    void addNewTypeVehicule(TypeVehiculeDTO typeVehiculeDTO, Long idMarque);

    void modifySelectedTypeVehicule(TypeVehiculeDTO typeVehiculeDTO);

    void deleteSelectedTypeVehicule(Long id);

    //List<TypeVehiculeDTO> getListTypeVehicule();

    void addNewMarqueVehicule(MarqueVehiculeDTO marqueVehiculeDTO);

    void modifySelectedMarqueVehicule(MarqueVehiculeDTO marqueVehiculeDTO);

    void deleteSelectedMarqueVehicule(Long id);

    //List<MarqueVehiculeDTO> getListMarqueVehicule();

    void addNewGenreVehicule(GenreVehiculeDTO genreVehiculeDTO);

    void modifySelectedGenreVehicule(GenreVehiculeDTO genreVehiculeDTO);

    void deleteSelectedGenreVehicule(Long id);

   // List<GenreVehiculeDTO> getListGenreVehicule();

    void addNewUsageVehicule(UsageVehiculeDTO usageVehiculeDTO);

    void modifySelectedUsageVehicule(UsageVehiculeDTO usageVehiculeDTO);

    void deleteSelectedUsageVehicule(Long id);

    //List<UsageVehiculeDTO> getListUsageVehicule();

    void addNewCauseSinistre(CauseSinistreDTO causeSinistreDTO);

    void modifySelectedCauseSinistre(CauseSinistreDTO causeSinistreDTO);

    void deleteSelectedCauseSinistre(Long id);

   // List<CauseSinistreDTO> getListCauseSinistre();

    void addNewFamilleOperationReparation(FamilleOperationReparationDTO familleOperationReparationDTO);

    void modifySelectedFamilleOperationReparation(FamilleOperationReparationDTO familleOperationReparationDTO);

    void deleteSelectedFamilleOperationReparation(Long id);

   // List<FamilleOperationReparationDTO> getListFamilleOperationReparation();

    void addNewOperationReparation(OperationReparationDTO operationReparationDTO);

    void modifySelectedOperationReparation(OperationReparationDTO operationReparationDTO);

    void deleteSelectedOperationReparation(Long id);

    //List<OperationReparationDTO> getListOperationReparation();

    void addNewFournisseur(FournisseurDTO fournisseurDTO);

    void modifySelectedFournisseur(FournisseurDTO fournisseurDTO);

    void deleteSelectedFournisseur(Long id);

    List<FournisseurDTO> getListFournisseurByActivite(String activite);

    List<String> getListActiviteFournisseur();

    List<ArticleDTO> getListArticleByUGP(String ugp);


    List<MarcheDTO> getListMarche();

    void addNewGouvernorat(GouvernoratDTO gouvernoratDTO);

    void modifySelectedGouvernorat(GouvernoratDTO gouvernoratDTO);

    void deleteSelectedGouvernorat(Long id);

    List<GouvernoratDTO> getListGouvernorat();

    void addNewStationPeage(String designation);

    void modifySelectedStationPeage(StationPeageDTO stationPeageDTO);

    void deleteSelectedStationPeage(Long id);

    List<StationPeageDTO> getListStationPeage();

    void addNewExpert(ExpertDTO expertDTO);

    void modifySelectedExpert(ExpertDTO expertDTO);

    void deleteSelectedExpert(Long id);

    List<ExpertDTO> getListExpert();

    void addNewLieuParking(LieuParkingDTO lieuParkingDTO);

    void modifySelectedLieuParking(LieuParkingDTO lieuParkingDTO);

    void deleteSelectedLieuParking(Long id);

    List<LieuParkingDTO> getListLieuParkingByGouvernorat(String gouvernorat);

    void addNewBeneficiaireEmprunt(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO);

    void modifySelectedBeneficiaireEmprunt(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO);

    void deleteSelectedBeneficiaireEmprunt(Long id);

    //List<BeneficiaireEmpruntDTO> getListBeneficiaireEmprunt();

    void addNewEtatStock(EtatStockDTO etatStockDTO);

    void deleteSelectedEtatStock(Long id);

    void modifySelectedEtatStock(EtatStockDTO etatStockDTO);

    //List<EtatStockNewDTO> getListEtatStock();

    Long addNewPersonnel(PersonnelDTO personnelDTO);

    Long modifySelectedPersonnel(PersonnelDTO personnelDTO);

    void uploadImagePersonnel(Long id, String imageName, String imagePath);

    void deleteSelectedPersonnel(Long id);

    //List<PersonnelDTO> getListPersonnel();

    void addNewParentStructure(StructureDTO structureDTO);

    void addNewChildStructure(StructureDTO structureDTO, Long idParent);

    void modifySelectedStructure(StructureDTO structureDTO);

    void deleteSelectedStructure(Long id);

    List<StructureDTO> getListStructure();

    void addNewMagasin(MagasinDTO magasinDTO, Long idUGP);

    void modifySelectedMagasin(MagasinDTO magasinDTO);

    void deleteSelectedMagasin(Long id);

    void addNewAtelier(AtelierDTO atelierDTO, Long idUGP);

    void modifySelectedAtelier(AtelierDTO atelierDTO);

    void deleteSelectedAtelier(Long id);

    void addNewRessource(RessourceDTO ressourceDTO, Long idUGP);

    void modifySelectedRessource(RessourceDTO ressourceDTO);

    void deleteSelectedRessource(Long id);

    void addNewSection(SectionDTO sectionDTO, String parent, Long id);

    void modifySelectedSection(SectionDTO sectionDTO);

    void deleteSelectedSection(Long id);

    void addNewUGP(UgpDTO ugpDTO);

    void modifySelectedUGP(UgpDTO ugpDTO);

    void deleteSelectedUGP(Long id);

    List<UgpDTO> getListUGP();

    void addNewProgrammeEntretiensPreventifs(ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO);

    void modifySelectedProgrammeEntretiensPreventifs(ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO);

    void deleteSelectedProgrammeEntretiensPreventifs(Long id);

   // List<ProgrammeEntretiensPreventifsDTO> getListProgrammeEntretiensPreventifs(String marque, String type);

    void addNewEnergie(EnergieDTO energieDTO);

    void modifySelectedEnergie(EnergieDTO energieDTO);

    void deleteSelectedEnergie(Long id);

    List<EnergieDTO> getListEnergie();

    void addNewAnnee(AnneeDTO anneeDTO);

    void deleteSelectedAnnee(Long id);

    List<AnneeDTO> getListAnnee();

    void addNewTva(TvaDTO tvaDTO);

    void deleteSelectedTva(Long id);

    List<TvaDTO> getListTva();

    void addNewUnite(UniteDTO uniteDTO);

    void deleteSelectedUnite(Long id);

    List<UniteDTO> getListUnite();

    List<FournisseurDTO> getListFournisseur();


	void UpdateArticleForRegulation(ModifyArticleRegulationDTO updateArticleDTO);

	List<ArticleDataTableDTO> getListArticle(int page, int limit);
	
	List<FamilleArticleDataDTO> getListFamilleDataArticle();

	List<SousFamilleArticleDataDTO> getListSousFamilleArticleData();
	
	Long getTotalNumberArticleByMarque(String marque);

	Long getTotalNumberArticleByGenre(String genre);

	Long getTotalNumberArticleByType(String type);

	List<ArticleDTO> getPaginationArticleByMarque(int page, int limit, String typeCarburant);

	List<ArticleDTO> getPaginationArticleByGenre(int page, int limit, String genre);

	List<ArticleDTO> getPaginationArticleByType(int page, int limit, String type);

	List<ArticleDTO> getPaginationArticleByMarque(int page, int limit, MarqueVehiculeEntity marqueVehicule);

	List<ArticleDTO> getPaginationArticleByMarque(int page, int limit, MarqueVehiculeEntity marqueVehicule,Pageable pageable);

	Long getTotalNumberArticleByMarqueVehicule(MarqueVehiculeEntity marqueVehicule);

	Long getTotalNumberArticleByGenreVehicule(GenreVehiculeEntity genreVehicule);

	Long getTotalNumberArticleByTypeVehicule(TypeVehiculeEntity typeVehicule);

	List<ArticleDTO> getPaginationArticleByMarqueVehicule(int page, int limit, MarqueVehiculeEntity marqueVehicule);

	List<ArticleDTO> getPaginationArticleByGenreVehicule(int page, int limit, GenreVehiculeEntity genreVehicule);

	List<ArticleDTO> getPaginationArticleByTypeVehicule(int page, int limit, TypeVehiculeEntity typeVehicule);

	void deleteSelectedbonCommmandeForFournisseur(FournisseurEntity fournisseurEntity);

	void modifySelectedRetourStructure(UpdateRetourStructureDTO updateRetourDTO);

	void modifySelectedFamille(FamilleArticleDTO familleArticleDTO);
	void modifySelectedSousFamilleArticle(SousFamilleArticleDTO sousFamilleArticleDTO);

	List<ArticleEntity> findArticleForAlerting();

	List<ArticleEntity> getAlertArticle(int page, int limit);

	List<SousFamilleArticleDTO> getPaginationListSousFamilletArticle(int page, int limit);

	List<FamilleArticleDTO> getPaginationListFamilletArticle(int page, int limit);

	List<EnergieDTO> getPaginationListEnergie(int page, int limit);

	List<TvaDTO> getPaginationListTva(int page, int limit);

	List<GouvernoratDTO> getPaginationListGouvernorat(int page, int limit);
	List<BeneficiaireEmpruntDTO> getListBeneficiaireEmprunt(int page, int limit);

	List<EtatStockNewDTO> getListEtatStock(int page, int limit);

	List<PersonnelDTO> getListPersonnel(int page, int limit);

	List<FamilleOperationReparationDTO> getListFamilleOperationReparation(int page, int limit);

	List<OperationReparationDTO> getListOperationReparation(int page, int limit);

	List<ProgrammeEntretiensPreventifsDTO> getListProgrammeEntretiensPreventifs(String marque, String type, int page,
			int limit);

	List<CauseSinistreDTO> getListCauseSinistre(int page, int limit);

	List<MarqueVehiculeDTO> getListMarqueVehicule(int page, int limit);

	List<GenreVehiculeDTO> getListGenreVehicule(int page, int limit);

	List<TypeVehiculeDTO> getListTypeVehicule(int page, int limit);

	List<UsageVehiculeDTO> getListUsageVehicule(int page, int limit);

	List<FournisseurDTO> getListFournisseur(int page, int limit);

	

}
