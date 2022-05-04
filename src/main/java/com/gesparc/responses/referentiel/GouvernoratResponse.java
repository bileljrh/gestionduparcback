package com.gesparc.responses.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GouvernoratResponse implements Serializable
{
    private Long id;
    
    private String designation;
    
    private List<LieuParkingResponse> lieuxParking;
    
    private String code;
    
}
