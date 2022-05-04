package com.gesparc.entities.carburant;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.EtatMensuelEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.EtatMensuelEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "distribution_service")
public class Distribution2ServiceEntity extends  AbstractBaseEntity
{
    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "etat_mensuel_id")
    EtatMensuelEntity etatMensuel;
    
    @Id
    @GeneratedValue()
    private Long id;
    
    private String typeDistributionService;

    @Override
    public String toString() {
        return "Distribution2ServiceEntity{" +
                "id=" + id +
                ", typeDistributionService='" + typeDistributionService + '\'' +
                ", etatMensuel=" + etatMensuel +
                '}';
    }
}
