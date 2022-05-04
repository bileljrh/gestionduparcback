package com.gesparc.services;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.sharedDTO.administration.AlerteDTO;
import com.gesparc.sharedDTO.administration.MessageApplicatifDTO;
import com.gesparc.sharedDTO.administration.MessageDTO;
import com.gesparc.sharedDTO.administration.TracabiliteDTO;
import com.gesparc.sharedDTO.referentiel.ParametreApplicationDTO;
import com.gesparc.sharedDTO.referentiel.StructureDTO;
import com.gesparc.sharedDTO.security.RoleDTO;
import com.gesparc.sharedDTO.security.RoleDataTableDTO;

import java.time.LocalDate;
import java.util.List;

@EnableAutoConfiguration
@Service
public interface Administration {

    List<MessageApplicatifDTO> getListMessageApplicatif();
    
    List<AlerteDTO> getListAlerte();
    
    List<MessageDTO> getListMessage(String struczture);

    List<StructureDTO> getListStructureUgpMagasin();
    
    List<RoleDataTableDTO> getListGroupeUtilisateur(int page , int limit);

    List<TracabiliteDTO> getPaginationListTracabilite(int page, int limit, Long idUser, String nomModule, LocalDate dateMin, LocalDate dateMax);

    void addNewMessageApplicatif(MessageApplicatifDTO messageApplicatifDTO);

    void modifySelectedMessageApplicatif(MessageApplicatifDTO messageApplicatifDTO);

    void deleteSelectedMessageApplicatif(Long id);

    void addNewAlerte(AlerteDTO alerteDTO);

    void modifySelectedAlerte(AlerteDTO alerteDTO);

    void deleteSelectedAlerte(Long id);

    void addParametreApplication(ParametreApplicationDTO parametreApplicationDTO);

    void modifyParametreApplication(ParametreApplicationDTO parametreApplicationDTO);

    void deleteParametreApplication(Long id);

    void deleteSelectedTracabilite(Long id);

    void addNewGroupeUtilisateur(String profil, String designation);

    void deleteSelectedGroupeUtilisateur(Long id);

    void addGroupeAuthorities(Long id, List<String> authorities);

    void addGroupeUtilisateurs(Long id, List<String> utilisateurs);

    void deleteSelectedGroupeUtilisateur(RoleDTO roleDTO);

    void sendNewMessage(String matricule, String email);

    void deleteSelectedMessage(Long id);

    int getNombreMessages(String structure);
    
    ParametreApplicationDTO getParametreApplication();

    Long getTotalItemTracabilite(Long idUser, String nomModule, LocalDate dateMin, LocalDate dateMax);


}
