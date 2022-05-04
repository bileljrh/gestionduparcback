package com.gesparc.entities.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.security.entity.UserEntity;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tracabilite")
public class TracabiliteEntity extends  AbstractBaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "utilisateur_id")
    
    private UserEntity user;
    
    private LocalDate dateOperation;
    
    private String nomModule;
    
    private String operation;
    
    private String detailsOperation;
    
}
