package com.gesparc.entities.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "programme_entretiens_preventifs")
public class ProgrammeEntretiensPreventifsEntity extends  AbstractBaseEntity
{
    @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private String cycle;
    
    private String designation;

    @ManyToOne()
    @JoinColumn(name = "marque_id")
    private MarqueVehiculeEntity marque;

    @ManyToOne()
    @JoinColumn(name = "type_id")
    private TypeVehiculeEntity type;

    @ManyToOne()
    @JoinColumn(name = "energie_id")
    private EnergieEntity energie;
}
