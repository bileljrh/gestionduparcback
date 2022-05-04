package com.gesparc.sharedDTO.administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneVehiculeDTO implements Serializable 
{
    private Long id;
    
    private String numeroPlaque;
    
    private String genre;
    
    private String marque;
    
    private String type;
    
    private Long idStructure;
    
    private Long idGouvernorat;
    
    private Long idBeneficiaire;
    
    private Long idFournisseur;
    
    private Long idLieuParking;
    
    private Long idEnergie;
    
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

}
