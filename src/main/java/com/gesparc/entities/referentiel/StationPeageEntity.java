package com.gesparc.entities.referentiel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "station_peage")
public class StationPeageEntity extends  AbstractBaseEntity
{
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String designation;
}
