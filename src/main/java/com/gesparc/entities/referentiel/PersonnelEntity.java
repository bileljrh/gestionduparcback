package com.gesparc.entities.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "personnel")
public class PersonnelEntity extends  AbstractBaseEntity
{
    private String immatriculationUnique;
    
    private String nom;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;
    
    private String prenom;
    
    @ManyToOne()
    @JoinColumn(name = "structure_id")
    private StructureEntity structure;
    
    private String cin;
    
    private String fonction;
    
    private String grade;
    
    private String avantage;

    private String quota;
    
    private LocalDate datePermis;
    
    private String nameImage;
    
    private String pathImage;
    
    private Long nombreVehicule;
    
}
