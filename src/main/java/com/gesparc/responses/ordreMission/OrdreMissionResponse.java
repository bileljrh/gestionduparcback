package com.gesparc.responses.ordreMission;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdreMissionResponse 
{
    List<AccompagnonMissionResponse> accompagnons;
    
    private Long id;
    
    private Long idVehicule;
    
    private String numeroSerie;
    
    private String typeCarburant;
    
    private String structure;
    
    private Long idBeneficiaire;
    
    private String nomBeneficiaire;
    
    private String matriculeBeneficiaire;
    
    private String numeroOrdre;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateOrdre;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date heureDateDepart;
    
    private int indexDepart;
    
    private String lieuDepart;
    
    private String destination;
    
    private String objectifMission;
    
    private String marchandiseTransportee;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateDebutValidite;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateFinValidite;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date heureDateRetour;
    
    private int indexRetour;
    
    private boolean confirmed;
    
    private boolean depassantDateRetour;
    
}
