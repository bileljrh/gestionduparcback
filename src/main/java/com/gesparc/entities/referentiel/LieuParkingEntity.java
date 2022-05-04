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
@Entity(name = "lieu_parking")
public class LieuParkingEntity extends  AbstractBaseEntity
{
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String adresse;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "gouvernorat_id")
    private GouvernoratEntity gouvernorat;

    @OneToMany(mappedBy = "gouvernorat", cascade = CascadeType.ALL)
    List<VehiculeEntity> vehicules = new ArrayList<>();
}
