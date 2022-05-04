package com.gesparc.sharedDTO.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gesparc.security.entity.UserEntity;
import com.gesparc.sharedDTO.ordreMission.AccompagnonMissionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDataTableDTO 
{	
    private Long id;
    
    private String profil;
    
    private String designation;
    
    private String authorities;
    
    List<UserDTO> users = new ArrayList<>();

}
