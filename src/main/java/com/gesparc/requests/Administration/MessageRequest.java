package com.gesparc.requests.Administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import com.gesparc.requests.security.UserRequest;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest implements Serializable 
{
    private Long id;
    
    private UserRequest user;
    
    private Date dateSent;
    
    private Boolean responded;
    
}
