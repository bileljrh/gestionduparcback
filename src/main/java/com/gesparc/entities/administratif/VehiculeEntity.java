package com.gesparc.entities.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.CoutEntity;
import com.gesparc.entities.achat.DemandeArticleEntity;
import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.entities.exploitation.EmpruntVehiculeEntity;
import com.gesparc.entities.exploitation.ReservationVehiculeEntity;
import com.gesparc.entities.exploitation.SinistreVehiculeEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceAnnule;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.entities.ordreMission.OrdreMissionEntity;
import com.gesparc.entities.referentiel.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vehicule")
public class VehiculeEntity extends AbstractBaseEntity {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String numeroPlaque;
	private String genre;
	private String marque;
	private String type;
	private Float pourcentageVehicule;
	@JsonIgnore
	@OneToOne(mappedBy = "vehicule", cascade = CascadeType.MERGE)
	TaxeCirculationEntity taxeCirculation;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "assurance_id")
	AssuranceEntity assurance;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.REMOVE)
	List<DemandeMaintenanceEntity> demandeMaintenances;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.REMOVE)
	List<DemandeMaintenanceAnnule> demandeMaintenanceAnnule;

	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.REMOVE)
	List<VisiteTechniqueEntity> visiteTechnique;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.MERGE)
	List<CoutEntity> couts;
	@JsonIgnore
//    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
//    List<MaintenanceEntity> maintenances;
//    @ManyToOne()
//    @JoinColumn(name = "demandeMaintenance_id")
//    private DemandeMaintenanceEntity demandeMaintenance;
//    

//    @OneToMany(targetEntity = DemandeMaintenanceEntity.class,cascade = CascadeType.ALL)
//    @JoinColumn(name ="cp_fk",referencedColumnName = "id")
//    private List<DemandeMaintenanceEntity> demandeMaintenace;
//    
//    

	@OneToMany(targetEntity = MaintenanceEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "m_fk", referencedColumnName = "id")
	private List<MaintenanceEntity> maintenances;

	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
	List<DocumentEntity> documents = new ArrayList<>();
	@JsonIgnore
	@OneToOne(mappedBy = "vehicule", cascade = CascadeType.REMOVE)
	ReformeEntity reforme;
	@JsonIgnore
	@OneToOne(mappedBy = "vehicule", cascade = CascadeType.ALL)
	CarteJockerEntity carteJocker;

	@ManyToOne()
	@JoinColumn(name = "structure_id")
	private StructureEntity structure;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "ugp_id")
	private UgpEntity ugp;

	@JsonIgnore
	@OneToOne(mappedBy = "vehicule", cascade = CascadeType.ALL)
	private CartePlafondEntity cartePlafond;
	@JsonIgnore
	@OneToOne(mappedBy = "vehicule", cascade = CascadeType.MERGE)
	private CarteAgilisCashEntity carteAgilisCash;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "gouvernorat_id")
	private GouvernoratEntity gouvernorat;

	@ManyToOne()
	@JoinColumn(name = "beneficiaire_id")
	private BeneficiaireEntity beneficiaire;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "fournisseur_id")
	private FournisseurEntity fournisseur;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "lieuParking_id")
	private LieuParkingEntity lieuParking;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "energie_id")
	private EnergieEntity energie;

	@JsonIgnore
	@OneToOne(mappedBy = "vehicule", cascade = CascadeType.ALL)
	GpsEntity gps;

	// Affectation =========================
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

	// Exploitation =========================
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

	// Certe Grise ====================================
	private String numeroCarteGrise;
	private String typeCarteGrise;
	private String referenceType;
	private int nombreDePlaces;
	private String carosserie;
	private String numeroSerie;
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

	// New relations =========================================

	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
	List<ReservationVehiculeEntity> reservations;

	@JsonIgnore
	@OneToOne(mappedBy = "vehicule", cascade = CascadeType.ALL)
	SinistreVehiculeEntity sinistre;

	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.REMOVE)
	List<EmpruntVehiculeEntity> emprunts = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
	List<OrdreMissionEntity> ordreMissions = new ArrayList<>();

}
