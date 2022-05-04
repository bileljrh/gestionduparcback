package com.gesparc.sharedDTO.administratif;

import java.util.Date;

import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.sharedDTO.administratif.Additionnel.VisiteTechniqueTableDataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListVisiteTechniqueDTO {

	
	    private Long id;

	    private Date datePMC;
	    
	    private int nombrePlaces;
	    
	    private int puissanceFiscale;
	    
	    private int montantVisiteTechnique;
	    
	    private int prixAchat;
	    
	    private Date dateDebutValidite;
	    
	    private Date dateFinValidite;
	    
	    private Long idVehicule;
	    
	    private String numeroPlaque;
	    
	    private String Structure;
	    
	    private VehiculeEntity vehicule;
}
