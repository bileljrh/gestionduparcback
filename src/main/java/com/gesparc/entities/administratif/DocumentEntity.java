package com.gesparc.entities.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "document")
public class DocumentEntity extends AbstractBaseEntity {
	@Id
	  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String adresse;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "vehicule_id")
	private VehiculeEntity vehicule;
}
