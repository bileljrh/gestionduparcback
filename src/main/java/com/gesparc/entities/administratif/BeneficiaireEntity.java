package com.gesparc.entities.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.EtatMensuelEntity;
import com.gesparc.entities.carburant.Distribution2FonctionEntity;
import com.gesparc.entities.exploitation.EmpruntVehiculeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "beneficiaire")
public class BeneficiaireEntity extends AbstractBaseEntity {
	@JsonIgnore
	@OneToMany(mappedBy = "beneficiaire", cascade = CascadeType.ALL)
	List<Distribution2FonctionEntity> distribution2Fonction;
	@JsonIgnore
	@OneToMany(mappedBy = "beneficiaire", cascade = CascadeType.ALL)
	List<EtatMensuelEntity> etatMensuels;
	@JsonIgnore
	@OneToMany(mappedBy = "beneficiaire", cascade = CascadeType.ALL)
	List<VehiculeEntity> vehicules = new ArrayList<>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	private String matricule;
	private String type;
	private String fonction;
	private int agePermis;

	@JsonIgnore
	@OneToOne(mappedBy = "beneficiaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	EmpruntVehiculeEntity emprunt;

	private Long Test;
}
