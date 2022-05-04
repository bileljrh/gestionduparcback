package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.maintenance.FacturationEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "fournisseur")
public class FournisseurEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "marche", cascade = CascadeType.MERGE)
    List<BonCommandeEntity> bonCommandes;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.MERGE)
    List<VehiculeEntity> vehicules = new ArrayList<>();
    
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "fournisseur", cascade=CascadeType.MERGE)
    List<FacturationEntity> facturation;

    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;
    
    private String activite;
    
    private LocalDate dateAjout;
	  
}
