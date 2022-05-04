package com.gesparc.entities.administratif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "maintenance")
public class MaintenanceEntity extends AbstractBaseEntity {

	@Id
	  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String article;
	private Date date_sortie;
	private int index_maintenance;
	private String capacite_exploit;

	@JsonIgnore
//    @ManyToOne()
//    @JoinColumn(name = "vehicule_id")
//    private VehiculeEntity vehicule;

	public MaintenanceEntity() {
	}

	public MaintenanceEntity(Long id, String article, Date date_sortie, int index_maintenance, String capacite_exploit,
			VehiculeEntity vehicule) {
		this.id = id;
		this.article = article;
		this.date_sortie = date_sortie;
		this.index_maintenance = index_maintenance;
		this.capacite_exploit = capacite_exploit;
		// this.vehicule = vehicule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Date getDate_sortie() {
		return date_sortie;
	}

	public void setDate_sortie(Date date_sortie) {
		this.date_sortie = date_sortie;
	}

	public int getIndex_maintenance() {
		return index_maintenance;
	}

	public void setIndex_maintenance(int index_maintenance) {
		this.index_maintenance = index_maintenance;
	}

	public String getCapacite_exploit() {
		return capacite_exploit;
	}

	public void setCapacite_exploit(String capacite_exploit) {
		this.capacite_exploit = capacite_exploit;
	}

//    public VehiculeEntity getVehicule() {
//        return vehicule;
//    }
//
//    public void setVehicule(VehiculeEntity vehicule) {
//        this.vehicule = vehicule;
//    }
}
