package com.gesparc.services;


import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.sharedDTO.administration.AlerteDTO;
import com.gesparc.sharedDTO.administration.MessageApplicatifDTO;
import com.gesparc.sharedDTO.administration.additionnel.NewUserDTO;
import com.gesparc.sharedDTO.referentiel.*;

@EnableAutoConfiguration
@Service
public interface Tracabilite {

    void addNewFamilleArticleTracabilite(FamilleArticleDTO familleArticleDTO, String matricule);

    void deleteSelectedFamilleArticleTracabilite(String code, String designation, String matricule);

    void addNewSousFamilleArticleTracabilite(SousFamilleArticleDTO sousFamilleArticleDTO, String matricule);

    void deleteSelectedSousFamilleArticleTracabilite(String code, String designation, String matricule);

    void addNewArticleTracabilite(UpdateArticleDTO updateArticleDTO, String matricule);

    void modifySelectedArticleTracabilite(UpdateArticleDTO updateArticleDTO, String matricule);

    void modifySelectedFamilleArticleTracabilite(SousFamilleArticleDTO sousFamilleArticleDTO, String matricule);
    
    void deleteSelectedArticleTracabilite(String code, String designation, String matricule);

    void addNewGouvernoratTracabilite(GouvernoratDTO gouvernoratDTO, String matricule);

    void modifySelectedGouvernoratTracabilite(GouvernoratDTO gouvernoratDTO, String matricule);

    void deleteSelectedGouvernoratTracabilite(String code, String designation, String matricule);

    void addNewStationPeageTracabilite(String designtion, String matricule);
    
    void addNewBonCommandeTracabilite(String matricule );

    void modifySelectedStationPeageTracabilite(StationPeageDTO stationPeageDTO, String matricule);

    void deleteSelectedStationPeageTracabilite(String designtion, String matricule);

    void addNewExpertTracabilite(ExpertDTO expertDTO, String matricule);

    void modifySelectedExpertTracabilite(ExpertDTO expertDTO, String matricule);

    void deleteSelectedExpertTracabilite(String code, String designation, String matricule);

    void addNewFournisseurTracabilite(FournisseurDTO fournisseurDTO, String matricule);

    void modifySelectedFournisseurTracabilite(FournisseurDTO fournisseurDTO, String matricule);

    void deleteSelectedFournisseurTracabilite(String designation, String matricule);

    void addNewLieuParkingTracabilite(LieuParkingDTO lieuParkingDTO, String matricule);

    void modifySelectedLieuParkingTracabilite(LieuParkingDTO lieuParkingDTO, String matricule);

    void deleteSelectedLieuParkingTracabilite(String adresse, String matricule);

    void addNewFamilleOperationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO, String matricule);

    void modifySelectedFamilleOperationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO, String matricule);

    void deleteSelectedFamilleOperationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO, String matricule);

    void addNewOperationReparationTracabilite(OperationReparationDTO operationReparationDTO, String matricule);

    void modifySelectedOperationReparationTracabilite(OperationReparationDTO operationReparationDTO, String matricule);

    void deleteSelectedOperationReparationTracabilite(String code, String designation, String matricule);

    void addNewProgrammeEntretiensPreventifsTracabilite(ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO, String matricule);

    void modifySelectedProgrammeEntretiensPreventifsTracabilite(ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO, String matricule);

    void deleteSelectedProgrammeEntretiensPreventifsTracabilite(String code, String designation, String matricule);

    void addNewAnneeTracabilite(AnneeDTO anneeDTO, String matricule);

    void modifySelectedAnneeTracabilite(AnneeDTO anneeDTO, String matricule);

    void deleteSelectedAnneeTracabilite(int annee, String matricule);

    void addNewEnergieTracabilite(EnergieDTO energieDTO, String matricule);

    void modifySelectedEnergieTracabilite(EnergieDTO energieDTO, String matricule);

    void deleteSelectedEnergieTracabilite(String code, String description, String matricule);

    void addNewTVATracabilite(TvaDTO tvaDTO, String matricule);

    void modifySelectedTVATracabilite(TvaDTO tvaDTO, String matricule);

    void deleteSelectedTVATracabilite(float tva, String matricule);

    void addNewUniteTracabilite(UniteDTO uniteDTO, String matricule);

    void modifySelectedUniteTracabilite(UniteDTO uniteDTO, String matricule);

    void deleteSelectedUniteTracabilite(String unite, String matricule);

    void addNewStructureTracabilite(StructureDTO structureDTO, String matricule);

    void addNewChildStructureTracabilite(StructureDTO structureDTO, String matricule);

    void modifySelectedStructureTracabilite(StructureDTO structureDTO, String matricule);

    void deleteSelectedStructureTracabilite(String code, String designation, String matricule);


    void addNewUGPreTracabilite(UgpDTO ugpDTO, String matricule);

    void modifySelectedUGPTracabilite(UgpDTO ugpDTO, String matricule);

    void deleteSelectedUGPTracabilite(String code, String designation, String matricule);

    void addNewAtelierTracabilite(AtelierDTO atelierDTO, String matricule);

    void modifySelectedAtelierTracabilite(AtelierDTO atelierDTO, String matricule);

    void deleteSelectedAtelierTracabilite(String code, String designation, String matricule);

    void addNewMagasinTracabilite(MagasinDTO magasinDTO, String matricule);

    void modifySelectedMagasinTracabilite(MagasinDTO magasinDTO, String matricule);

    void deleteSelectedMagasinTracabilite(String code, String designation, String matricule);

    void addNewRessourceTracabilite(RessourceDTO ressourceDTO, String matricule);

    void modifySelectedRessourceTracabilite(RessourceDTO ressourceDTO, String matricule);

    void deleteSelectedRessourceTracabilite(String designation, String matricule);

    void addNewSectionTracabilite(SectionDTO sectionDTO, String matricule);

    void modifySelectedSectionTracabilite(SectionDTO sectionDTO, String matricule);

    void deleteSelectedSectionTracabilite(String code, String designation, String matricule);

    void addNewPersonnelTracabilite(PersonnelDTO personnelDTO, String matricule);

    void modifySelectedPersonnelTracabilite(PersonnelDTO personnelDTO, String matricule);

    void deleteSelectedPersonnelTracabilite(String immatriculationUnique, String nom, String prenom, String matricule);

    void addNewEtatStockTracabilite(EtatStockDTO etatStockDTO, String matricule);

    void modifySelectedEtatStockTracabilite(EtatStockDTO etatStockDTO, String matricule);

    void deleteSelectedEtatStockTracabilite(String designation, String code, String matricule);

    void addNewBeneficiaireEmpruntTracabilite(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO, String matricule);

    void modifySelectedBeneficiaireEmpruntTracabilite(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO, String matricule);

    void deleteSelectedBeneficiaireEmpruntTracabilite(String nomBeneficiaire, String code, String matricule);

    void addNewFamilleOperationReparationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO, String matricule);

    void modifySelectedFamilleOperationReparationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO, String matricule);

    void deleteSelectedFamilleOperationReparationTracabilite(String code, String designation, String matricule);

    void addNewTypeVehiculeTracabilite(TypeVehiculeDTO typeVehiculeDTO, String matricule);

    void modifySelectedTypeVehiculeTracabilite(TypeVehiculeDTO typeVehiculeDTO, String matricule);

    void deleteSelectedTypeVehiculeTracabilite(String code, String designation, String matricule);

    void addNewMarqueVehiculeTracabilite(MarqueVehiculeDTO marqueVehiculeDTO, String matricule);

    void modifySelectedMarqueVehiculeTracabilite(MarqueVehiculeDTO marqueVehiculeDTO, String matricule);

    void deleteSelectedMarqueVehiculeTracabilite(String code, String designation, String matricule);

    void addNewGenreVehiculeTracabilite(GenreVehiculeDTO genreVehiculeDTO, String matricule);

    void modifySelectedGenreVehiculeTracabilite(GenreVehiculeDTO genreVehiculeDTO, String matricule);

    void deleteSelectedGenreVehiculeTracabilite(String code, String designation, String matricule);

    void addNewUsageVehiculeTracabilite(UsageVehiculeDTO usageVehiculeDTO, String matricule);

    void modifySelectedUsageVehiculeTracabilite(UsageVehiculeDTO usageVehiculeDTO, String matricule);

    void deleteSelectedUsageVehiculeTracabilite(String code, String designation, String matricule);

    void addNewCauseSinistreTracabilite(CauseSinistreDTO causeSinistreDTO, String matricule);

    void modifySelectedCauseSinistreTracabilite(CauseSinistreDTO causeSinistreDTO, String matricule);

    void deleteSelectedCauseSinistreTracabilite(String code, String designation, String matricule);

    void addNewUtilisateurTracabilite(NewUserDTO newUserDTO, String matricule);

    void activateDesactivateSelectedUtilisateurTracabilite(Long id, String matricule);

    void verrouillageDeverrouillageUtilisateurTracabilite(Long id, String matricule);

    void modificationMot2PassUtilisateurTracabilite(Long id, String matricule);

    void reinitialisationMot2PassUtilisateurTracabilite(Long id, String matricule);

    void modifySelectedUtilisateurTracabilite(Long id, String matricule);

    void deleteSelectedUtilisateurTracabilite(String nom, String prenom, String matriculeUtilisateur,String matricule);

    void addNewGroupeUtilisateurTracabilite(String profil, String designation, String matricule);

    void addGroupeAuthoritiesTracabilite(Long id, String matricule);

    void addGroupeUtilisateursTracabilite(Long id, String matricule);

    void modifySelectedGroupeUtilisateurTracabilite(Long id, String matricule);

    void deleteSelectedGroupeUtilisateurTracabilite(String designation, String profil, String matricule);

    void addNewMessageApplicatifTracabilite(MessageApplicatifDTO messageApplicatifDTO, String matricule);

    void modifySelectedMessageApplicatifTracabilite(MessageApplicatifDTO messageApplicatifDTO, String matricule);

    void deleteSelectedMessageApplicatifTracabilite(String code, String matricule);

    void addNewAlerteTracabilite(AlerteDTO alerteDTO, String matricule);

    void modifySelectedAlerteTracabilite(AlerteDTO alerteDTO, String matricule);

    void deleteSelectedAlerteTracabilite(String code, String matricule);

    void addParametreApplicationTracabilite(String matricule);

    void modifyParametreApplicationTracabilite(String matricule);
    
    
    void addNewOrdreMissionTracabilite(String numero , String matricule);
    
    void addNewDemandeReservationVehiculeTracabilite(String matricule);
    
    void modifySelectedDemandeReservationVehiculeTracabilite(String matricule);
    
    void deleteSelectedReservationVehiculeTracabilite(String matricule);
    
    void addNewLocationVehiculeTracabilite(String matricule);
    
    void modifySelectedLocationVehiculeTracabilite(String matricule);
    
    void deleteSelectedLocationVehiculeTracabilite(String matricule);
    
    void addNewSinistreVehiculeTracabilite(String numeroSinistre,String matricule);
    
    void modifySelectedSinistreVehiculeTracabilite(String numeroSinistre,String matricule);
    
    void deleteSelectedSinistreVehiculeTracabilite(String numeroSinistre,String matricule);
    
    void deleteSelectedEmpruntVehiculeTracabilite(LocalDate dateEmprunt,String matricule);
    
    void confirmSelectedEmpruntVehiculeTracabilite(LocalDate dateEDate , String matricule);
    
    void modifySelectedEmpruntVehiculeTracabilite(LocalDate dateEmprunt , String matricule );
    
    void addNewEmpruntVehiculeTracabilite(LocalDate dateEmprunt , String matricule);
    

    void modifySelectedOrdreMissionTracabilite(String numero , String matricule);
    
    void deleteSelectedOrdreMissionTracabilite(String numero , String matricule);
    
    void confirmSelectedOrdreMissionTracabilite(String numero , String matricule);
    void deleteSelectedBonCommandeTracabilite(String matricule);
        
    void deleteSelectedVehiculeTracabilite(String numplaque,String marticule);
    
    void modifySelectedVehiculeTracabilite(String numplaque , String matricule);
    
    void addNewVehiculeTracabilite(String numplaque,String matricule);
    
    void modifySelectedTaxeCirculationTracabilite(String numplaque, String matricule);
    
    void addNewTaxeCirculationTracabilite(String numplaque,String matricule);
    
    void deleteSelectedTaxeCirculationTracabilite(String numplaque,String matricule);
    
    void deleteSelectedAssuranceTracabilite(String compgnie ,String matricule);
    
    void modifySelectedAssuranceTracabilite(String compagine,String matricule);
    
    void addNewAssuranceTracabilite(String compagnie,String matricule);
    
    void deleteSelectedVisiteTechniqueTracabilite(String plaqueVehicule,String matricule);
    
    void modifySelectedVisiteTechniqueTracabilite(String matricule);
    
    void addNewVisiteTechniqueTracabilite(String numeroPlaque ,String matricule);
    
    void deleteSelectedReformeTracabilite(String nom , String matricule);
    
    void modifySelectedReformeTracabilite(String nom ,String matricule);
    
    void addNewReformeTracabilite(String nom ,String matricule);
    
    void deleteSelectedGPSTracabilite(String codeIMEI,String matricule);
    
    void modifySelectedGPSTracabilite(String codeIMEI,String matricule);
    
    void addNewGPSTracabilite(String codeIMEI,String matricule);

    void deleteSelectedCartePlafondTracabilte(String numCarte , String matricule);
    
    void modifySelectedCartePlafondTracabilite(String numCarte,String matricule);
    
    void createNewCartePlafondTracabilite(String numCarte ,String matricule);
    
    void deleteDemandAffectationCartePlafondTracabilite(String numCarte , String matricule);
    
    void validateDemandeAffectationCartePlafondTracabilite(String matricule);
    
    void confirmDemandeAffectationCartePlafondTracabilite(String matricule);
    
    void createNewDemandAffectationCartePlafondTracabilite(String numCarte,String matricule);
    
    void modifySelectedDeclarationPerteCartePlafondTracabilite(String numCarte,String matricule);
    
    void createNewDeclarationPerteCartePlafondTracabilite(String numCarte,String matricule);
    
    void createNewDemandeAnnulationCarteCarburantTracabilite(String numCarte,String matricule);
    
    void validateSelectedCarteAgilisCashTracabilite(String numCarte,String matricule);
    
    void confirmSelectedCarteAgilisCashTracabilite(String numCarte,String matricule);
    
    void modifySelectedCarteAgilisCashTracabilite(String numCarte,String matricule);
    
    void createNewCarteAgilisCashTracabilite(String numCarte,String matricule);
    
    void deleteSelectedCarteAgilisCashTracabilite(String numCarte,String matricule);
    
    void deleteDemandeAnnulationCarteAgilisCashTracabilite(String numCarte,String matricule);
    
    void createNewDemandeAnnulationCarteAgilisCashTracabilite(String numCarte,String matricule);
    
    void confirmDemandeAnnulationCarteAgilisCashTracabilite(String matricule);

	void modifySelectedFamilleTracabilite(FamilleArticleDTO familleArticleDTO, String matricule); 

}
