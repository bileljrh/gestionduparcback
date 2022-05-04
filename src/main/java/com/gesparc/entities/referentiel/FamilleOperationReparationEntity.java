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
@Entity(name = "famille_operation_reparation")
public class FamilleOperationReparationEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToMany(mappedBy = "familleOperations", cascade = CascadeType.ALL)
    List<OperationReparationEntity> operationsReparation = new ArrayList<>();
    
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String designation;
}
