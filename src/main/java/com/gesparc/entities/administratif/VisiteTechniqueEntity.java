package com.gesparc.entities.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.referentiel.StructureEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "visite_technique")
public class VisiteTechniqueEntity extends AbstractBaseEntity {
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "vehicule_id")
	private VehiculeEntity vehicule;

	@Id
	  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Date datePMC;
	private int nombrePlaces;
	private int puissanceFiscale;
	private int prixAchat;
	private int montantVisiteTechnique;
	private Date dateDebutValidite;
	private Date dateFinValidite;
}
