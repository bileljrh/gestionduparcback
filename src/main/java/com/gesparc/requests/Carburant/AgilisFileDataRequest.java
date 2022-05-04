package com.gesparc.requests.Carburant;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgilisFileDataRequest {

	private Long id;
	private String station;
	private String idFile;

	private String transac_date;

	private String transac_num_ticket;

	private String transac_num_ticket_annulation;

	private String transac_num_carte;

	private String crt_Porteur_Perso;

	private String transac_num_carte_transfert;

	private double montant;

	private double facturation;

	private double qte;

	private String transac_kilometrage;

	private String produits;

	private double consommation;
	
	private String typ;
	
	private String type_transaction ;
	
	private double prixTTc;
	
	private double ravitaillement ;
	
	private double qte_reservoir ;
	
	
	private double qte_reservoir_avant;


	private double index_avant_fin_mois ;
	
	
	private double index_fin_mois;
	
	

}
