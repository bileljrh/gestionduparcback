package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.exploitation.LocationVehiculeEntity;
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
@Entity(name = "structure")
public class StructureEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "structures")
    List<UserEntity> users;
    
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String typeStructure;
    
    private String designation;
    
    private boolean collapsed;
    
    private boolean square;
    
    @JsonIgnore
    @ManyToOne
    private StructureEntity structureMere;
    
    @JsonIgnore
    @OneToMany(mappedBy = "structureMere")
    private List<StructureEntity> structureFilles = new ArrayList<>();
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "ugp_id")
    private UgpEntity ugp;
    
    @JsonIgnore
    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL)
    List<PersonnelEntity> personnels = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL)
    List<VehiculeEntity> vehicules = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL)
    List<LocationVehiculeEntity> locations;

}
