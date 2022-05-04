package com.gesparc.entities.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "assurance")
public class AssuranceEntity extends AbstractBaseEntity {
	@JsonIgnore
	@OneToMany(mappedBy = "assurance", cascade = CascadeType.ALL)
	List<VehiculeEntity> vehicules = new ArrayList<>();
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate datePMC;
	private int nombreplaces;
	private int puissanceFiscale;
	private int montantAssurance;
	private LocalDate dateDebutValidite;
	private LocalDate dateFinValidite;
	private String assuranceSP;
	private String numeroContrat;
	private String compagnieAssurance;
}
