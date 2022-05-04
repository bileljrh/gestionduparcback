package com.gesparc.entities.carburant.carteJocker;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.security.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "declaration_perte_carte_jocker")
public class DeclarationPerteCarteJockerEntity extends  AbstractBaseEntity
{

	    @Id
	    @GeneratedValue
	    private Long id;
	    
	    private String nomDeclarant;
	    
	    private String prenomDeclarant;
	    
	    private LocalDate dateNaissanceDeclarant;
	    
	    private String lieuNaissanceDeclarant;
	    
	    private String numeroCINDeclarant;
	    
	    private String sexeDeclarant;
	    
	    private String typeDeclarant;
	    
	    private String typeDeclaration;
	    
	    @ManyToOne()
	    @JoinColumn(name = "cartejocker_id")
	    CarteJockerEntity cartejocker;
	    
	    @ManyToOne()
	    @JoinColumn(name = "user_id")
	    UserEntity user;
	    
	    private LocalDate datePerte;
	    
	    private String lieuPerte;
	    
	    private String circonstances;
	    
	    private boolean confirmed;

}
