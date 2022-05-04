package com.gesparc.entities.administratif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "taxe_ciruclation")
public class TaxeCirculationEntity extends AbstractBaseEntity {
	@OneToOne()
	@JoinColumn(name = "vehicule_id")
	VehiculeEntity vehicule;
	@Id
	  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroQuittance;
	private boolean parPoids;
	private int poids;
	private boolean parPlace;
	private int nombrePlaces;
	private LocalDate dateDebutCirculation;
	private LocalDate dateFinCirculation;
	private int montant;
	private LocalDate dateFinValidite;
}
