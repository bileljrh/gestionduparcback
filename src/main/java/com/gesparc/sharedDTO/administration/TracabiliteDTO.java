package com.gesparc.sharedDTO.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.sharedDTO.security.UserDTO;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TracabiliteDTO implements Serializable 
{
    @Id
    @GeneratedValue
    private Long id;
    
    private LocalDate dateOperation;
    
    private String nomModule;
    
    private String operation;
    
    private String detailsOperation;
    
    private UserDTO user;
}
