package com.gesparc.entities.ordreMission;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "accompagnon_ordre_mission")
public class AccompagnonMissionEntity extends  AbstractBaseEntity
{
	
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "ordre_mission_id")
    OrdreMissionEntity ordreMission;
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String cin;
    
    private String nom;
    
    private String prenom;
    
}
