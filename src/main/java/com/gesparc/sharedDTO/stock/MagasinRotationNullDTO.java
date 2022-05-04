package com.gesparc.sharedDTO.stock;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MagasinRotationNullDTO {

	@Id
	@GeneratedValue
	private Long id ;
	
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateTransfert;
    
    private String status;
    
    private Boolean confirmed  ;
    
    private Boolean validated ;
    
    
    private String designation;
    
    @Column(unique = true)
    private String codeArticle;
    
    @Column(nullable = true)
    private int quantiteStock ;
    
    private double prix;
      
    private LocalDate dateAjout;
        
    private float tva;
    
    private float remise;
}
