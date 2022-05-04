package com.gesparc.sharedDTO.carburant.additionnel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgilisFileDataTableDTO {

	
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

		private boolean confirm;
		
		private boolean validation;
}
