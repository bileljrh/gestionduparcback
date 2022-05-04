package com.gesparc.entities.carburant.cartePlafond;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteJocker.DemandeQuotaCarteJocker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "recharge_quota_mensuel")
public class RechargeQuotaMensuelEntity  extends  AbstractBaseEntity{

	 @Id
     @GeneratedValue
     private Long id;
	 
     private String nom;
     
     private String prenom;
     
     private String matricule;
     
    
     @ManyToOne()
     @JoinColumn(name = "carte_plafond_id")
     CartePlafondEntity cartePlafond;
     
	 private boolean confirmed;
	    
	 private boolean validated;
     
     
}
