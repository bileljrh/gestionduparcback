package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.exploitation.LocationVehiculeEntity;

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
@Entity(name = "marque_vehicule")
public class MarqueVehiculeEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "marqueVehicule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ArticleEntity> articles = new ArrayList<>();

    @OneToMany(mappedBy = "marques", cascade = CascadeType.ALL)
    List<TypeVehiculeEntity> types = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "marque", cascade = CascadeType.ALL)
    List<ProgrammeEntretiensPreventifsEntity> programmeEntretien = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "marque", cascade = CascadeType.ALL)
    List<LocationVehiculeEntity> locations = new ArrayList<>();

    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;

}
