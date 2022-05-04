package com.gesparc.requests.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest implements Serializable 
{
    private Long id;
    
    private String profil;
    
    private String designation;
    
    String authorities;
    
    List<UserRequest> users = new ArrayList<>();
}
