package com.gesparc.services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.entities.carburant.RechargeComplementaire;
import com.gesparc.entities.carburant.RechargeCarburantCompensation;
import com.gesparc.entities.carburant.HistoriqueOperationRecharge;
import com.gesparc.entities.carburant.HistoriqueRecahrgeComplementaire;
import com.gesparc.entities.carburant.HistoriqueRechargeCarburantCompensation;
import com.gesparc.entities.carburant.RechargeSousCompte;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueRechargeQuotaMensuelEntity;
import com.gesparc.entities.carburant.cartePlafond.RechargeQuotaMensuelEntity;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.DeclarationPerteCartePlafondDTO;
import com.gesparc.sharedDTO.DemandeAnnulationCartePlafondDTO;
import com.gesparc.sharedDTO.EtatMensuelDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.carburant.*;

import java.text.ParseException;
import java.util.List;

@EnableAutoConfiguration
@Service
public interface Carburant {

	List<ListBeneficiairesInternesDTO> getListBeneficiairesInternes();

	List<ListBeneficiairesExternesDTO> getListBeneficiairesExternes();

	List<DistributionFonctionTabDataDTO> getListDistributionsCarburant2FonctionBySource(String source);

	List<EtatMensuelDTO> getAllEtatMensuels();

	List<EtatMensuelDTO> getEtatMensuelsByMonthAndType(String type, String month) throws ParseException;

	List<DistributionFonctionTabDataDTO> getDistributionsCarburant2FonctionByMonthAndType(String type, String month);

	List<EtatMensuelDTO> getValidatedEtatMensuelList();

	List<DistributionServiceTabDataDTO> getListDistributionsCarburant2ServiceBySource(String source);

	List<DemandeAnnulationCartePlafondDTO> getHistoriqueDemandeAnnulationCartePlafondByConfirmation(
			boolean confirmation);

	List<DeclarationPerteCarteJockerDTO> getListDeclarationPerteCarteJockerByConfirmation(boolean confirmation);

	List<VehiculeDTO> getListVehiculesService();

	List<VehiculeDTO> getListVehiculesServiceWithNoCartePlafond();

	List<DeclarationPerteCartePlafondDTO> getListDeclarationPerteCartePlafondByConfirmation(boolean confirmation);

	void deleteOneDeclarationPerteCartePlafond(Long id);

	void modifySelectedAffectationCartePlafond(Long idCartePlafond, Long idVehicule);

	void deleteOneDistributionCarburant2Service(Long id);

	void createNewDistribution2Service(Long id);

	void confirmOneEtatMensuels(Long id);

	void modifyOneDistributionsCarburant2Fonction(ModifyDistribution2FonctionDTO modifyDistribution2FonctionDTO)
			throws ParseException;

	void createNewDistributionCarburant2Fonction(NewDistribution2FonctionDTO newDistribution2FonctionDTO);

	void deleteOneDistributionCarburant2Fonction(Long id);

	void unconfirmOneEtatMensuels(Long id);

	void validateOneEtatMensuels(Long id);

	void unvalidateOneEtatMensuels(Long id);

	void modifyOneEtatMensuels(ModifyEtatMensuelDTO modifyEtatMensuelDTO);

	void createNewEtatMensuel(NewEtatMensuelDTO newEtatMensuelDTO);

	void deleteOneEtatMensuels(Long id);

	void deleteOneDemandeAnnulationCartePlafond(Long id);

	void confirmOneDeclarationPerteCartePlafond(Long id);

	void confirmSelectedDemandeAnnulationCarteCarburant(Long id);

	void modifyOneDemandeAnnulationCarteCarburant(DemandeAnnulationCartePlafondDTO demandeAnnulationCartePlafondDTO);

	void deleteOneCartePlafond(Long id);

	void deleteHistoriqueDemandeAnnulationCartePlafond(Long id);

	void deleteOneCarteJocker(Long id);

	void confirmOneDeclarationPerteCarteJocker(Long id);

	List<ListRechargeCarburantCompensationDTO> getPaginationRechargeCarburantCompensationList(int page, int limit);

	RechargeCarburantCompensation addNewRechargeCarburantCompensation(
			RechargeCarburantCompensation rechargeCarburantCompensation);

	void modifySelectedRechargeCarburantCompensation(
			ModifyRechargeCarburantCompensationDTO rechargeCarburantCompensation);

	void deleteSelectedRechargeCarburantCompensation(Long id);

	RechargeCarburantCompensation getRechargeCarburantCompensation(Long id);

	Iterable<RechargeCarburantCompensation> getAllRechargeCarburantCompensations();

	void confirmSelectedRechargeCarburantCompensation(Long id);

	void validateSelectedRechargeCarburantCompensation(Long id);
	List<DemandeRechargeSousCompteDTO> getPaginationDemandeRechargeSousCompteList(int page, int limit);

	void deleteDemandeRechargeSousCompte(Long id);

	void confirmSelectedDemandeRechargeSousCompte(Long id);

	void validateSelectedDemandeRechargeSousCompte(Long id);

	RechargeSousCompte addNewDemandeRechargeSousCompte(RechargeSousCompte rechargeSousCompte);

	RechargeQuotaMensuelEntity addNewRechargeQuotaMensuel(RechargeQuotaMensuelEntity rechargeQuotaMensuel);

	void confirmRechargeQuotaMensuel(long id);

	void validRechargeQuotaMensuel(long id);

	List<RechargeQuotaMensuelDTO> getPaginationRechargeQuotaMensuelList(String cartePlafond, int page, int limit);
	List<ListRechargeComplementaireDTO> getPaginationRechargeComplementaireList(int page, int limit);

	RechargeComplementaire addNewRechargeComplementaire(RechargeComplementaire RechargeComplementaire);

	void modifySelectedRechargeComplementaire(ModifyRechargeComplementaireDTO RechargeComplementaire);

	void deleteSelectedRechargeComplementaire(Long id);

	RechargeComplementaire getRechargeComplementaire(Long id);

	Iterable<RechargeComplementaire> getAllRechargeComplementaires();

	void confirmSelectedRechargeComplementaire(Long id);

	void validateSelectedRechargeComplementaire(Long id);
	List<HistoriqueOperationRecharge> getPaginationHistoriqueRechargeList(int page, int limit);

	void deleteSelectedHistoriqueRecharge(Long id);

	Long getTotalNumberRechargeComplementaire();

	Long getTotalNumberRechargeCarburantCompensation();
	
List<ListRechargeCarburantCompensationDTO> loadRechargeCarburantCompensationTabDataDTO(
			List<RechargeCarburantCompensation> rechargeCarburantCompensations);

List<ListRechargeComplementaireDTO> getPaginationHistoriqueRechargeComplementaireList(int page, int limit);

List<ListRechargeComplementaireDTO> getPaginationRechargeComplementaireList(String cartePlafond, int page, int limit);

List<ListRechargeCarburantCompensationDTO> getPaginationRechargeCarburantCompensationList(String cartePlafond, int page,
		int limit);

List<ListRechargeCarburantCompensationDTO> getPaginationHistoriqueRechargeCarburantCompensationList(
		int page, int limit);
	int NombreNotif();

	int NombreNotifValid();
	List<HistoriqueRechargeQuotaMensuelEntity> getPaginationHistoriqueRechargeQMList(int page, int limit);

	int NbNotif();

	int NombreNotifRechargeComplementaire();

	int NombreNotifValidRechargeComplementaire();

	int NombreNotifRechargeCompensation();

	int NombreNotifValidRechargeCompensation();

	List<HistoriqueRecahrgeComplementaire> getPaginationHistoriqueRechargeComplementaireList1(int page, int limit);

	void deleteSelectedHistoriqueRechargeComplementaire(Long id);

	void deleteSelectedHistoriqueRechargeCompensation(Long id);

	List<HistoriqueRechargeCarburantCompensation> getPaginationHistoriqueRechargeCompensationList1(int page, int limit);

}
