package com.gesparc.responses.administration.additionnel;

import java.time.LocalDate;

import com.gesparc.responses.security.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TracabiliteResponseDataTable
{
    private Long id;
    
    private LocalDate dateOperation;
    
    private String nomModule;
    
    private String operation;
    
    private String detailsOperation;
    
    private UserResponse user;
    
}
