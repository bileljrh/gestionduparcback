package com.gesparc.sharedDTO;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class DeclarationPerteCarteJockerDTO 
{

	 private Long id;
	 
	private String nomDeclarant;
	
    private String prenomDeclarant;
    
    private LocalDate dateNaissanceDeclarant;
    
    private String lieuNaissanceDeclarant;
    
    private String numeroCINDeclarant;
    
    private String sexeDeclarant;
    
    private String typeDeclarant;
    
    private String typeDeclaration;
    
    private LocalDate datePerte;
    
    private Long idCartejocker;
    
    private Long idUser;
    
    private String lieuPerte;
    
    private String circonstances;
    
    private boolean confirmed;
    
}

