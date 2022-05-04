package com.gesparc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesparc.security.exception.domain.EmailExistException;
import com.gesparc.security.exception.domain.UserNotFoundException;
import com.gesparc.security.exception.domain.UsernameExistException;
import com.gesparc.security.servicesImpl.UserServiceImpl;
import com.gesparc.sharedDTO.administration.additionnel.NewUserDTO;

import java.io.IOException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class TestController 
{

    private UserServiceImpl userService;

    public TestController(UserServiceImpl userService) 
    {
        this.userService = userService;
    }

    @GetMapping(path = "/backup/utilisateur")
    ResponseEntity<HttpStatus> addNewUtilisateur() throws UserNotFoundException, UsernameExistException, EmailExistException, IOException 
    {
        ModelMapper modelMapper = new ModelMapper();
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setMatricule("USER-TT-BACKUP");
        newUserDTO.setMot2passe("USERTTBACKUP");
        newUserDTO.setEmail("user.backup@gmail.com");
        newUserDTO.setNom("User TT");
        newUserDTO.setPrenom("BackUP");
        newUserDTO.setTypeCompte("Administrateur");
        userService.addNewUtilisateur(newUserDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
