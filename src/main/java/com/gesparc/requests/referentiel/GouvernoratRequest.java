package com.gesparc.requests.referentiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GouvernoratRequest implements Serializable 
{
    @JsonIgnore
    List<LieuParkingRequest> lieuxParking;
    
    private Long id;
    
    private String designation;
    
    private String code;
}
