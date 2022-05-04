package com.gesparc.entities.carburant.cartePlafond;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.carburant.HistoriqueOperationRecharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "historique_recharge_quota_mensuel")
public class HistoriqueRechargeQuotaMensuelEntity extends  AbstractBaseEntity {
	
	 @Id
     @GeneratedValue
     private Long id;
	
     private String nom;
     
     private String prenom;
     
     private String matricule;
     
     
     
    

}
