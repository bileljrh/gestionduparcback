package com.gesparc.requests.Administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.CarteAgilisCashRequest;
import com.gesparc.requests.CarteJockerRequest;
import com.gesparc.requests.CartePlafondRequest;
import com.gesparc.requests.CoutRequest;
import com.gesparc.requests.referentiel.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeRequest implements Serializable 
{
    private Long id;
    
    private String numeroPlaque;
    
    private String genre;
    
    private String marque;
    
    private String type;
    
    private Float pourcentageVehicule;
    
    TaxeCirculationRequest taxeCirculation;
    
    AssuranceRequest assurance;
    
    VisiteTechniqueRequest visiteTechnique;
    
    List<CoutRequest> couts;
    
    List<MaintenanceRequest> maintenances;
    
    List<DocumentRequest> documents;
    
    List<ReformeRequest> reformes;
    
    CarteJockerRequest carteJocker;
    
    @JsonIgnore
    GpsRequest gps;
    
    private StructureRequest structure;
    
    private CartePlafondRequest cartePlafond;
    
    private CarteAgilisCashRequest carteAgilisCash;
    
    private GouvernoratRequest gouvernorat;
    
    private BeneficiaireRequest beneficiaire;
    
    private FournisseurRequest fournisseur;
    
    private LieuParkingRequest lieuParking;
    
    private EnergieRequest energie;

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

}
