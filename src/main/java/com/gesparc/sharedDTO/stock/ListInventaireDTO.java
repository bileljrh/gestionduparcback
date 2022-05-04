package com.gesparc.sharedDTO.stock;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.entities.administratif.VehiculeEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import com.gesparc.sharedDTO.maintenance.DemandeMaintenanceDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInventaireDTO {

    
  
    
 
    
    @Column(unique = true)
    private String codeArticle;
    
    private String designation;
    
    @Column(nullable = true)
    private int quantiteStock ;
    
    
    private double prix;
            
    private float tva;
    
}
