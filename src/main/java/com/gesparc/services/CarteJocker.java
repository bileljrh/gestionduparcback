package com.gesparc.services;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;
import com.gesparc.responses.carburant.additionnel.CarteJockerTabDataResponse;
import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.DeclarationPerteCarteJockerDTO;
import com.gesparc.sharedDTO.DemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.HistoriqueAffectationCarteJockerDTO;
import com.gesparc.sharedDTO.HistoriqueDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.carburant.ListDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModificationDemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModifyCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.ModifyDemandeQuotaCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.NewCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.NouvelleDemandeDesaffectationCarteJockerDTO;
import com.gesparc.sharedDTO.carburant.additionnel.CarteJockerTabDataDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCarteJockerTabDataDTO;

import java.time.LocalDate;
import java.util.List;

@EnableAutoConfiguration
@Service
public interface CarteJocker {

    List<CarteJockerTabDataDTO> getPaginationCarteJockerList(int page, int limit);
    
    List<SelectVehiculeDTO> getListVehiculesServiceWithNoCarteJocker();

    List<HistoriqueAffectationCarteJockerDTO> getPaginationHistoriqueAffectationCarteJockerByFilteredDate(int page, int limit, LocalDate dateMin, LocalDate dateMax);

    List<HistoriqueAffectationCarteJockerTabDataDTO> getPaginationDemandesAffectationCarteJocker(int page, int limit);

    List<DemandeDesaffectationCarteJockerDTO> getPaginationDemandesDesaffectationCarteJockerByFilteredDate(int page, int limit, LocalDate dateMin, LocalDate dateMax);

    List<CarteJockerDTO> getListAffectedVehiculesAndCartesJocker();

    List<CarteJockerDTO> getListCarteJockerAvailableForAffectation();

    List<CarteJockerTabDataDTO> getListNotAffectedCartesJocker();
    
    List<CarteJockerTabDataDTO> getListAffectedCartesJocker();
    
    List<HistoriqueDesaffectationCarteJockerDTO> getPaginationHistoriqueDesaffectationCarteJockerByFilteredDate(int page, int limit);

    List<DeclarationPerteCarteJockerDTO> getPaginationdeclarationperteCarteJocker(int page, int limit);

	List<DeclarationPerteCarteJockerDTO> getPaginationdeclarationperteCarteJocker(int page, int limit,String confirmed);
    
	List<ListDemandeQuotaCarteJockerDTO>  getPaginationDemandeQuotaCarteJockerList(int page, int limit);

	List<DeclarationPerteCarteJockerDTO> getListDeclarationPerteCarteJockerByConfirmation(boolean confirmation);

	Long getTotalNumberdeclarationPerteCarteJocker();

	Long getTotalNumberHistoriqueDesaffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax);

    Long getTotalNumberCarteJocker();

    Long getTotalDemandesAffectationCarteJockerByFilteredDate();

    Long getTotalNumberCarteJockerByEtatCarte(String etatCarte);
    
    Long getTotalNumberHistoriqueAffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax);

    Long getTotalNumberDemandesDesaffectationCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax);

    void modifySelectedCarteJocker(NewCarteJockerDTO newCarteJockerDTO);

    void deleteSelectedCarteJocker(Long id);

    void addNewCarteJocker(NewCarteJockerDTO newCarteJockerDTO);

    void addNewAffectationCarteJocker(Long idCarte, Long idVehicule);

    void modifySelectedDemandeAffectationCarteJocker(Long idCarte, Long idVehicule, LocalDate dateDerniereDemande);

    void deleteSelectedDemandeAffectationCarteJocker(Long idCarte,Long idHistorique);

    void deleteSelectedHistoriqueAffectationCarteJocker(Long idCarte);

    void createNouvelleDemandeDesaffectationCarteJockerRequest(HistoriqueDesaffectationCarteJockerDTO nouvelleDemandeDTO);

    void deleteSelectedDemandeDesaffectationCarteJocker(Long id);

    void confirmSelectedDemandeDesaffectationCarteJocker(Long id);

    void modificationDemandeDesaffectationCarteJockerDTO(HistoriqueDesaffectationCarteJockerDTO modificationDemandeDesaffectationCarteJockerDTO);

    void deleteSelectedHistoriqueDesaffectationCarteJocker(Long id);

    void confirmSelectedDemandeAffectationCarteJocker(Long id);

    void validateSelectedDemandeAffectationCarteJocker(Long id);

	void createNewDeclarationPerteCarteJocker(DeclarationPerteCarteJockerDTO declarationPerteCarteJockerDTO);

	void modifySelectedDeclarationPerteCarteJocker(DeclarationPerteCarteJockerDTO declarationPerteCarteJockeDTO);
   
	void deleteSelectedDemandeQuotaCarteJocker(Long id);

	void confirmSelectedDemandeQuotaCarteJocker(Long id);

	void validateSelectedDemandeQuotaCarteJocker(Long id);
	
	void modifySelectedDemandeQuotaCarteJocker(ModifyDemandeQuotaCarteJockerDTO demandeQuotaCarteJocker);

	DemandeQuotaCarteJocker addNewDemandeQuotaCarteJocker(DemandeQuotaCarteJocker demandeQuotaCarteJocker);

	DemandeQuotaCarteJocker getDemandeQuotaCarteJocker(Long id);

    Iterable<DemandeQuotaCarteJocker> getAllDemandeQuotaCarteJockers();

	Iterable<CarteJockerEntity> getAllCarteJockerTabDataResponses();

	long getTotalNumberDemandeQuotaCarteJockerByFilteredDate(LocalDate dateMin, LocalDate dateMax) ;
	
	public Long getTotalNumberDemandeQuotaCarteJocker();

	
	


}
