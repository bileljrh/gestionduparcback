package com.gesparc.responses.administratif;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Administratif.GpsRequest;
import com.gesparc.responses.CarteAgilisCashResponse;
import com.gesparc.responses.CarteJockerResponse;
import com.gesparc.responses.CartePlafondResponse;
import com.gesparc.responses.CoutResponse;
import com.gesparc.responses.referentiel.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeResponse 
{
    private Long id;
    
    private String numeroPlaque;
    
    private String genre;
    
    private String marque;
    
    private String type;
    
    private Float pourcentageVehicule;
    
    TaxeCirculationResponse taxeCirculation;
    
    AssuranceResponse assurance;
    
    VisiteTechniqueResponse visiteTechnique;
    
    List<CoutResponse> couts;
    
    List<MaintenanceResponse> maintenances;
    
    List<DocumentResponse> documents;
    
    List<ReformeResponse> reformes;
    
    CarteJockerResponse carteJocker;
    
    @JsonIgnore
    GpsRequest gps;
    
    private StructureResponse structure;
    
    private CartePlafondResponse cartePlafond;
    
    private CarteAgilisCashResponse carteAgilisCash;
    
    private GouvernoratResponse gouvernorat;
    
    private BeneficiaireResponse beneficiaire;
    
    private FournisseurResponse fournisseur;
    
    private LieuParkingResponse lieuParking;
    
    private EnergieResponse energie;
    
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
