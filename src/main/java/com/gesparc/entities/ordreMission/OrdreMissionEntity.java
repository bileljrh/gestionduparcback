package com.gesparc.entities.ordreMission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ordre_mission")
public class OrdreMissionEntity extends  AbstractBaseEntity{
	
    @JsonIgnore
    @OneToMany(mappedBy = "ordreMission", cascade = CascadeType.ALL)
    List<AccompagnonMissionEntity> accompagnons = new ArrayList<>();
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "vehicule_id")
    VehiculeEntity vehicule;
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String numeroOrdre;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateOrdre;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date heureDateDepart;
    
    private int indexDepart;
    
    private String lieuDepart;
    
    private String destination;
    
    private String objectifMission;
    
    private String marchandiseTransportee;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateDebutValidite;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateFinValidite;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date heureDateRetour;
    
    private int indexRetour;
    
    @Column(columnDefinition = "boolean default false")
    private boolean confirmed;
    
    @Column(columnDefinition = "boolean default false")
    private boolean depassantDateRetour;
}
