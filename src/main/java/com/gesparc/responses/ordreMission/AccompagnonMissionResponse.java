package com.gesparc.responses.ordreMission;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccompagnonMissionResponse 
{
    @JsonIgnore
    OrdreMissionResponse ordreMission;
    
    private Long id;
    
    private String cin;
    
    private String nom;
    
    private String prenom;
    
}
