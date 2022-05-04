package com.gesparc.entities.carburant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "agilis_file_data")
public class AgilisFileData {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
	
	private String idFile; 
	
	private String transacDate ;
	
	private String transac_num_ticket ;
	
	private String transac_num_ticket_annulation ;
	
	private String station ;
	
	private String transac_num_carte ;
	
	private String crt_Porteur_Perso ;
	
	private String transac_num_carte_transfert ;
	
	private double montant ;
	
	private double facturation ;
	
	private double qte ;
	
	private String transac_kilometrage ;
	
	private String produits ;
	
    private double consommation ;
    
    private String typ;
    
    private String type_transaction ;
    
    private double qte_reservoir ;
    
    private double index_fin_mois; 
    
	private double prixttc;
	
	private double ravitaillement;
	private double qte_reservoir_avant;


	private double index_avant_fin_mois ;
	
	private boolean confirm ;
	
	private boolean validation;
	
	private String state ;
	
}
