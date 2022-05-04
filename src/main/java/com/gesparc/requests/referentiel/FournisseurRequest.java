package com.gesparc.requests.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.Achat.BonCommandeRequest;
import com.gesparc.requests.Administratif.VehiculeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurRequest implements Serializable 
{
    @JsonIgnore
    List<BonCommandeRequest> bonCommandes;
    
    @JsonIgnore
    List<VehiculeRequest> vehicule;
    
    private Long id;
    
    private String code;
    
    private String designation;
    
    private String activite;
    
    private LocalDate dateAjout;
}
