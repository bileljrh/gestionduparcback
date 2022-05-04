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
@Entity(name = "gps")
public class GpsEntity extends AbstractBaseEntity {
	@Id
	  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String codeIMEI;
	private String lien;
	@JsonIgnore
	@OneToOne()
	@JoinColumn(name = "vehicule_id")
	VehiculeEntity vehicule;
}
