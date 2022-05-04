package com.gesparc.sharedDTO.referentiel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GouvernoratDTO implements Serializable
{
    private Long id;
    
    private String designation;
    
    private List<LieuParkingDTO> lieusParking;
    
    private String code;
}
