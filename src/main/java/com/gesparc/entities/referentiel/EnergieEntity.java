package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
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
@Entity(name = "energie")
public class EnergieEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "energie", cascade = CascadeType.ALL)
    List<ProgrammeEntretiensPreventifsEntity> programmeEntretien = new ArrayList<>();
    
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String description;
    
    private float prixUnitaire;
    
    private float tva;

    @JsonIgnore
    @OneToMany(mappedBy = "energie", cascade = CascadeType.ALL)
    List<VehiculeEntity> vehicules = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL)
    List<LocationVehiculeEntity> locations;

}
