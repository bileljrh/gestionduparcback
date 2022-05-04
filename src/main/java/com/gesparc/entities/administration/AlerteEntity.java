package com.gesparc.entities.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.entities.AbstractBaseEntity;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "alerte")
public class AlerteEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    private String numero;
    
    private String code;
    
    private String message;
    
}
