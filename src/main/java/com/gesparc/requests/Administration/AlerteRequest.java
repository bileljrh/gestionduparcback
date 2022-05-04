package com.gesparc.requests.Administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlerteRequest implements Serializable 
{
    private Long id;
    
    private String numero;
    
    private String code;
    
    private String message;
}
