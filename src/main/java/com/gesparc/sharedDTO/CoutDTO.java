package com.gesparc.sharedDTO;

import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoutDTO implements Serializable {

    private String operation;
    
    private Date date_operation;
    
    private int montant;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "vehicule_id")
    private VehiculeDTO vehicule;

   
}
