package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;

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
@Entity(name = "gouvernorat")
public class GouvernoratEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "gouvernorat", cascade = CascadeType.ALL)
    List<LieuParkingEntity> lieuxParking;

    @OneToMany(mappedBy = "gouvernorat", cascade = CascadeType.ALL)
    List<VehiculeEntity> vehicules = new ArrayList<>();

    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String designation;
    
    private String code;
    
}
