package com.gesparc.responses.maintenance.additional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.responses.administratif.VehiculeResponse;
import com.gesparc.responses.referentiel.additionnel.VehiculeResponseAdditionnel;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.responses.administratif.VehiculeResponse;
import com.gesparc.responses.carburant.CarteJockerDataResponse;
import com.gesparc.responses.referentiel.additionnel.VehiculeResponseAdditionnel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeMaintenanceDataTableResponse implements Serializable
{
	
	  private Long id;
	  
	    private VehiculeResponseAdditionnel vehicule;
	    
	    private String numeroDemande;
	    
	    private String numeroSerie;
	    
	    private String annulation;

	    private Long idBeneficiaire;
	    
	    private String nomBeneficiaire;
	    
	    private String matriculeBeneficiaire;
	    
	    private String structure;
	    
	    private String personnel;
	    
	    private String ugp;
	    
	    private String ugpReparation;
	    
	    private String demandeur;
	    
	    private Date dateDemande;
	    
	    private int indexKm;
	    
	    private String descriptionIntervention;
	    
	    private String status;
	    
	    private String dateRDV;
	    
	    private String observation;
	   	
}
