package com.gesparc.entities.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.maintenance.BonTravailOperationEntity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "operation_reparation")
public class OperationReparationEntity extends  AbstractBaseEntity
{
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;

    @ManyToOne()
    @JoinColumn(name = "famille_id")
    private FamilleOperationReparationEntity familleOperations;
    
    @JsonIgnore
    @OneToMany(mappedBy = "operations", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BonTravailOperationEntity> bonTravailOperation = new HashSet<>();;
    
    
}
