package com.gesparc.sharedDTO.administratif;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.GpsRequest;
import com.gesparc.sharedDTO.CarteAgilisCashDTO;
import com.gesparc.sharedDTO.CarteJockerDTO;
import com.gesparc.sharedDTO.CartePlafondDTO;
import com.gesparc.sharedDTO.CoutDTO;
import com.gesparc.sharedDTO.maintenance.DemandeMaintenanceDTO;
import com.gesparc.sharedDTO.referentiel.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO implements Serializable 
{
    private Long id;
    
    private String numeroPlaque;
    
    private String genre;
    
    private String marque;
    
    private String type;
    
    private Float pourcentageVehicule;
    
    TaxeCirculationDTO taxeCirculation;
    
    AssuranceDTO assurance;
    
    VisiteTechniqueDTO visiteTechnique;
    
    List<CoutDTO> couts;
    
    List<MaintenanceDTO> maintenances;
    
    List<DocumentDTO> documents;
    
    List<ReformeDTO> reformes;
    
    CarteJockerDTO carteJocker;
    
    private StructureDTO structure;
    
    private CartePlafondDTO cartePlafond;
    
    private CarteAgilisCashDTO carteAgilisCash;
    
    private GouvernoratDTO gouvernorat;
    
    private BeneficiaireDTO beneficiaire;
    
    private FournisseurDTO fournisseur;
    
    private LieuParkingDTO lieuParking;
    
    private EnergieDTO energie;
    
    @JsonIgnore
    GpsRequest gps;

    private String etat;
    
    private String natureAffectation;
    
    private LocalDate dateAffectation;
    
    private String referenceAffectation;
    
    private String numeroImmatriculation;
    
    private String numeroProprietaireEtat;
    
    private String numeroChassis;
    
    private String numeroCarteUtilisation;
    
    private String urlImageVehicule;
    
    private String nameImageVehicule;
    
    private int indexKm;

    private String typeTaxe;
    
    private String exploitationUsage;
    
    private String typeAssurance;
    
    private String nomAssurance;
    
    private String referenceBC;
    
    private LocalDate dateReception;
    
    private String prixAchat;
    
    private String situationDouaniere;
    
    private String numeroImmatriculationTemporaire;
    
    private String compagnieLeasing;
    
    private LocalDate dateEcheance;
    
    private String numeroCarteGrise;
    
    private String typeCarteGrise;
    
    private String referenceType;
    
    private int nombreDePlaces;
    
    private String carosserie;
    
    private int poidsTotalACharge;
    
    private int consommationMoyenne;
    
    private int chargeUtile;
    
    private int poidsTotalSansCharge;
    
    private int dimensionsPneuAvant;
    
    private int dimensionsPneuArriere;
    
    private int poidsAVide;
    
    private int puissanceFixale;
    
    private int puissanceMoteur;
    
    private int nombreEssieux;
    
    private int volumeCylindre;
    
    private LocalDate datePremiereMiseEnCirculation;
    
    private DemandeMaintenanceDTO demandeMaintenance;

}
