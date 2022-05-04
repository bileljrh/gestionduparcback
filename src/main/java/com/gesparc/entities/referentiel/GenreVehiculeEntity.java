package com.gesparc.entities.referentiel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.exploitation.LocationVehiculeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "genre_vehicule")
public class GenreVehiculeEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "genreVehicule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ArticleEntity> articles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL)
    List<LocationVehiculeEntity> locations;

    @Id
    @GeneratedValue  (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;
    
    private LocalDate dateAjout;
    
}
