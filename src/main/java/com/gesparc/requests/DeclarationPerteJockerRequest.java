package com.gesparc.requests;

import java.time.LocalDate;

import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.requests.Carburant.NewCarteJockerRequest;
import com.gesparc.requests.referentiel.SousFamilleArticleRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationPerteJockerRequest 
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
	    
	    private Long  idCartejocker;
	    
	    private Long  idUser;
	    
	    private String circonstances;
	    
	    private boolean confirmed;

}
