package com.gesparc.sharedDTO.ordreMission;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccompagnonMissionDTO 
{
    @JsonIgnore
    OrdreMissionDTO ordreMission;
    
    private Long id;
    
    private String cin;
    
    private String nom;
    
    private String prenom;
    
}
