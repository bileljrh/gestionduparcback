package com.gesparc.entities.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.exploitation.SinistreVehiculeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "expert")
public class ExpertEntity extends  AbstractBaseEntity
{
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String code;
    
    private String designation;
    
    private String telephone;
    
    private String fax;
    
    private String adresse;
    
    private String codePostal;
    
    private String ville;
    
    private String email;
    
    private Date dateAjout;
    
    @JsonIgnore

    @OneToMany(mappedBy = "expert",cascade = CascadeType.REMOVE)
    List<SinistreVehiculeEntity> sinistres = new ArrayList<>();
}
