package com.gesparc.requests.Carburant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.requests.DeclarationPerteJockerRequest;
import com.gesparc.requests.referentiel.ArticleRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCarteJockerRequest {
	
	
	@JsonIgnore
    List<DeclarationPerteJockerRequest> pertejocker;
	
    private Long id;
    
    private Long idVehicule;
    
    private String numeroCarte;
    
    private int solde;
    
    private String typeCarburant;
    
    private LocalDate dateValiditee;
}
