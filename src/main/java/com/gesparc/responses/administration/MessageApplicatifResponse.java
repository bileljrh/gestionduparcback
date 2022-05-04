package com.gesparc.responses.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageApplicatifResponse implements Serializable 
{
    private Long id;
    
    private String numero;
    
    private String code;
    
    private String message;
    
}
