package com.gesparc.entities.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reforme")
public class ReformeEntity extends AbstractBaseEntity {
	@JsonIgnore
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vehicule_id")
	VehiculeEntity vehicule;
	@Id
	  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private LocalDate date;
	private String reference;
	private LocalDate dateSortie;
	private int prix;
	private String cause;
}
