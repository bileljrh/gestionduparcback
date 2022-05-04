package com.gesparc.responses.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse implements Serializable 
{
    private Long id;
    
    private String profil;
    
    private String designation;
    
    private String authorities;
    
    List<UserResponse> users = new ArrayList<>();
    
}
