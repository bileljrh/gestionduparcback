package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.security.entity.UserEntity;

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
@Entity(name = "magasin")
public class MagasinEntity extends  AbstractBaseEntity
{
	@OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL)
	List<SectionEntity> sections = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "magasins")
	List<UserEntity> users = new ArrayList<>();
	
	@Id
	  @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	private String designation;
	
	private boolean collapsed;
	
	private boolean square;
	
	@JsonIgnore
	@ManyToOne
	private UgpEntity ugp;
	
	@JsonIgnore
	@OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL)
	List<BonTravailEntity> bonTravail = new ArrayList<>();
	
}
