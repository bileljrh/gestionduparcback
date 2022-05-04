package com.gesparc.sharedDTO.ordreMission.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gesparc.sharedDTO.ordreMission.AccompagnonMissionDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdreMissionTableDataDTO implements Serializable 
{
    List<AccompagnonMissionDTO> accompagnons = new ArrayList<>();
    
    private Long id;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private Long idBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroOrdre;
    
    private Date dateOrdre;
    
    private Date heureDateDepart;
    
    private int indexDepart;
    
    private String lieuDepart;
    
    private String destination;
    
    private String objectifMission;
    
    private String marchandiseTransportee;
    
    private Date dateDebutValidite;
    
    private Date dateFinValidite;
    
    private Date heureDateRetour;
    
    private int indexRetour;
    
    private boolean confirmed;
    
    private boolean depassantDateRetour;
    
}
