package com.gesparc.security.services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.exception.domain.EmailExistException;
import com.gesparc.security.exception.domain.UserNotFoundException;
import com.gesparc.security.exception.domain.UsernameExistException;
import com.gesparc.sharedDTO.administration.additionnel.NewUserDTO;
import com.gesparc.sharedDTO.security.UserDTO;
import com.gesparc.sharedDTO.security.UserDataTableDTO;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


@EnableAutoConfiguration
@Service
public interface UserService 
{

    HttpHeaders login(UserDTO userDTO);

    UserEntity register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<UserEntity> getUsers();

    UserEntity findUserByMatricule(String matricule);

    UserEntity findUserByEmail(String email);

    UserEntity addNewUtilisateur(NewUserDTO newUtilisateurDTO) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException;

    UserDTO modifySelectedUtilisateur(NewUserDTO newUserDTO) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException;

    void deleteUser(String username) throws IOException;


    List<UserDTO> getListUtilisateur(String structure);
    
    List<UserDataTableDTO> getListUser(int page , int limit ,String structure);


    void activateDesactivateSelectedUtilisateur(Long id);

    void verrouillageDeverrouillageUtilisateur(Long id);

    void modificationMot2PassUtilisateur(Long id, String ancienMot2pass, String nouveauMot2pass);

    void reinitialisationMot2PassUtilisateur(Long id, String nouveauMot2pass);

    void deleteSelectedUtilisateur(Long id);
    
}
