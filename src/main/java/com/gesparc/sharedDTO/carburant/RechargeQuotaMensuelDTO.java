package com.gesparc.sharedDTO.carburant;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;
import com.gesparc.sharedDTO.CartePlafondDTO;
import com.gesparc.sharedDTO.referentiel.ArticleDTO;

import com.gesparc.sharedDTO.stock.ParcTransfertDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeQuotaMensuelDTO implements Serializable  {

	 private Long id;
	
	 private Boolean confirmed  ;
	    
	 private Boolean validated ;
	 
	 private String nom;
	 
	 private String prenom;
	 
	 private String matricule;
	 
     @JsonIgnore
     CartePlafondEntity cartePlafond;
	 
	 
	
}
