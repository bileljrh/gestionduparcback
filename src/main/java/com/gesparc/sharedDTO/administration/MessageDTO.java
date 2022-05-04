package com.gesparc.sharedDTO.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import com.gesparc.sharedDTO.security.UserDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable 
{
    private Long id;
    
    private UserDTO user;
    
    private Date dateSent;
    
    private Boolean responded;
}
