package com.gesparc.entities.achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "marche")
public class MarcheEntity extends AbstractBaseEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "marche", cascade = CascadeType.ALL)
	List<BonCommandeEntity> bonCommandes;
	
	private String nomMarche;
	
	private String referenceMarche;
	
	private int budget;
}
