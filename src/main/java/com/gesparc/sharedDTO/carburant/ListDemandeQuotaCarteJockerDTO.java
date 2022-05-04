package com.gesparc.sharedDTO.carburant;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.gesparc.sharedDTO.carburant.additionnel.CarteJockerTabDataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListDemandeQuotaCarteJockerDTO implements Serializable
{

	    private Long id;
	    
	    private boolean confirmed;
	    
	    private boolean validated;
	    
	    private String typeCarburant;
	    
	    private int quantiteDemande ;
	    
	    private int quantiteValide ; 
	    
	    private String numCarte;
	    
	    private String demandeur ;
	    
	    private String causeDeBlocage ;

	    
}
