package com.gesparc.entities.carburant.carteJocker;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "demande_quota_carte_jocker")
public class DemandeQuotaCarteJocker extends  AbstractBaseEntity
{
	    @Id
        @GeneratedValue
        private Long id;

	    private String numCarte;
	    
	    private boolean confirmed;
	    
	    private boolean validated;
	    
	    private String typeCarburant;
	    
	    private Date dateDemande;
	    
	    private String demandeur ;
	    
	    private int quantiteDemande ;
	    
	    private int quantiteValide ;
	    
	    private String causeDeBlocage ;
	    
}
