package com.gesparc.responses;

import com.gesparc.responses.administratif.VehiculeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.administratif.VehiculeResponse;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoutResponse 
{

    private String operation;
    
    private Date date_operation;
    
    private int montant;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "vehicule_id")
    private VehiculeResponse vehicule;

}
