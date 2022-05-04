package com.gesparc.entities.carburant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gesparc.entities.carburant.cartePlafond.CartePlafondEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "historique_recharge_complementaire")
public class HistoriqueRecahrgeComplementaire {
	@Id
    @GeneratedValue()
    private Long id;
	
	private String observation;
	
	private String matricule;
    
    private int quantiteDemande;

}
