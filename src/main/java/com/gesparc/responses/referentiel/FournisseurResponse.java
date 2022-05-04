package com.gesparc.responses.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.responses.Achat.BonCommandeResponse;
import com.gesparc.responses.administratif.VehiculeResponse;
import com.gesparc.responses.maintenance.FacturationResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurResponse implements Serializable 
{
    @JsonIgnore
    List<BonCommandeResponse> bonCommandes;
    
    @JsonIgnore
    List<VehiculeResponse> vehicule;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    private String activite;
    
    private LocalDate dateAjout;
    
    private FacturationResponse facturation;
    
}
