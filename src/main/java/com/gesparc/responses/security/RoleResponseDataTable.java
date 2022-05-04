package com.gesparc.responses.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDataTable 
{
    private Long id;
    
    private String profil;
    
    private String designation;
    
    private String authorities;
    
    private List<UserResponse> users;
    
}
