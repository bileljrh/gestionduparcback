package com.gesparc.responses.maintenance.additional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import com.gesparc.entities.maintenance.FacturationEntity;
import com.gesparc.responses.maintenance.BonTravailArticleResponse;
import com.gesparc.responses.maintenance.DemandeMaintenanceResponse;
import com.gesparc.responses.maintenance.FacturationResponse;
import com.gesparc.responses.referentiel.AtelierResponse;
import com.gesparc.responses.referentiel.MagasinResponse;
import com.gesparc.entities.maintenance.FacturationEntity;
import com.gesparc.responses.Achat.DemandeArticleResponse;
import com.gesparc.responses.carburant.CarteJockerDataResponse;
import com.gesparc.responses.maintenance.BonTravailArticleResponse;
import com.gesparc.responses.maintenance.DemandeMaintenanceResponse;
import com.gesparc.responses.maintenance.FacturationResponse;
import com.gesparc.responses.referentiel.AtelierResponse;
import com.gesparc.responses.referentiel.MagasinResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonTravailDataTableResponse 
{

	private Long id;
	
	private Date dateEntree;
	
	private Date dateSortie;
	
	private Date dateSortiePrevue;
	
	private String natureTravaux;
	
	private String mode;
	
	private boolean cloturer;
	
	private int indexKM;
	
	private String observation;
	
	private String observatioMode;
	
	private DemandeMaintenanceResponse demandeMaintenance;
	
	List<BonTravailArticleResponse> bonTravailArticle;
	
	private FacturationResponse facturation;
	
	private String structure;
	
	private String serieVehicule;
	
	private String nomBeneficiaire;
	
	private MagasinResponse magasin;
	
	private AtelierResponse atelier;
	
	
	private String nomAtelier ;
	
	
	private String numSerieVoiture;
	
	
	private String nomMagasin;
	
}
