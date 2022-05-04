package com.gesparc.sharedDTO.administratif.Additionnel;




import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewVehiculeDTO
{
    private Long id;
    
    private String numeroPlaque;
    
    private String genre;
    
    private String marque;
    
    private String type;
    
    private Float pourcentageVehicule;
    
    private Long idBeneficiaires;
    
    private Long idGouvernorat;
    
    private Long idLieuParking;
    
    private Long idStructure;
    
    private Long idFournisseur;
    
    private Long idEnergie;

    private String etat;
    
    private String natureAffectation;
    
    private String dateAffectation;
    
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
    
    private String dateReception;
    
    private String prixAchat;
    
    private String situationDouaniere;
    
    private String numeroImmatriculationTemporaire;
    
    private String compagnieLeasing;
    
    private String dateEcheance;

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
    
    private String datePremiereMiseEnCirculation;
    private LocalDate dateAjout;
}