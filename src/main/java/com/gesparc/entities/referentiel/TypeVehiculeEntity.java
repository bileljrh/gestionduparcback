package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

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
@Entity(name = "type_vehicule")
public class TypeVehiculeEntity extends  AbstractBaseEntity
{

    @JsonIgnore
    @OneToMany(mappedBy = "typeVehicule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ArticleEntity> articles = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    List<ProgrammeEntretiensPreventifsEntity> programmeEntretien = new ArrayList<>();
   
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "marque_id")
    private MarqueVehiculeEntity marques;

}
