package com.gesparc.entities.maintenance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Facturation_Bon_Travail")
public class FacturationEntity extends  AbstractBaseEntity
{

	@Id
	@GeneratedValue
	private Long id;
	
	private Date dateCommande;
	
	private int numeroFacturation;
	
	private Date dateFacturation;
	
	private int montantFacturation;
	
	private int numeroReglement;
	
	private Date dateReglement;
	
	private int montantReglement;
	
	private int numeroCommande;
	
	private int montantCommande;

	@JsonIgnore
	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "fournisseur_id")
	@JsonBackReference
	FournisseurEntity fournisseur;

	@JsonIgnore
	@OneToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "bon_travail_id")
	BonTravailEntity bonTravail;

}
