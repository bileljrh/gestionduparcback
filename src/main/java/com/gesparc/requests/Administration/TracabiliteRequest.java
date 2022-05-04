package com.gesparc.requests.Administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gesparc.requests.security.UserRequest;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TracabiliteRequest implements Serializable 
{
    @Id
    @GeneratedValue
    private Long id;
    
    private LocalDate dateOperation;
    
    private String nomModule;
    
    private String operation;
    
    private String detailsOperation;

    private UserRequest user;

}
