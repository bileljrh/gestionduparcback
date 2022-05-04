package com.gesparc.responses.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import com.gesparc.responses.security.UserResponse;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse implements Serializable 
{
    private Long id;
    
    private UserResponse user;
    
    private Date dateSent;
    
    private Boolean responded;
    
}
