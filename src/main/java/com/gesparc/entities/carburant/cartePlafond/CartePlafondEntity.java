package com.gesparc.entities.carburant.cartePlafond;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.BeneficiaireEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.Details2DistributionEntity;
import com.gesparc.entities.carburant.Distribution2FonctionEntity;
import com.gesparc.entities.carburant.HistoriqueRecahrgeComplementaire;
import com.gesparc.entities.carburant.HistoriqueRechargeCarburantCompensation;
import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.RechargeComplementaire;
import com.gesparc.entities.carburant.RechargeCarburantCompensation;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "carte_plafond")
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"numero_carte"})})

public class CartePlafondEntity extends  AbstractBaseEntity
{
	
    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "vehicule_id")
    VehiculeEntity vehicule;
    
    @Id
    @GeneratedValue
    private Long id;
    
    private int montant;
    
    private LocalDate dateValiditee;
    
    private Date dateAjout ;
    
    private String status;
    
    @NotBlank(message = "Numero de la carte ne peut pas etre vide ")
    @Column(name = "numero_carte", unique = true, nullable = false)
    private String numeroCarte;
    
    private String typeCarburant;
    
    private boolean activated;
    
    @JsonIgnore
    @OneToMany(mappedBy = "cartePlafond", cascade = CascadeType.ALL)
    List<HistoriqueAffectationCartePlafondEntity> historiqueAffectationCartePlafond;

    @JsonIgnore
    @OneToMany(mappedBy = "cartePlafond", cascade = CascadeType.ALL)
    List<RechargeComplementaire> rechargeComplementaire;
    
    @JsonIgnore
    @OneToMany(mappedBy = "carteplafond", cascade = CascadeType.ALL)
    List<DeclarationPerteCartePlafondEntity> declarationPerteCartePlafondEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "cartePlafond", cascade = CascadeType.ALL)
    List<RechargeQuotaMensuelEntity> rechargeQuotaMensuel;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "cartePlafond", cascade = CascadeType.ALL)
    List<RechargeCarburantCompensation> rechargeCarburantCompensation;
   
	public void setHistoriqueAffectationCartePlafond(
			List<HistoriqueAffectationCartePlafondEntity> historiqueAffectationCartePlafond) {
		this.historiqueAffectationCartePlafond = historiqueAffectationCartePlafond;
	}
	
	

}
