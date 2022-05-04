package com.gesparc.services;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.gesparc.security.entity.UserEntity;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.ListVehiculeAvailable4OrdreMissionDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.NewOrdreMissionDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.OrdreMissionTableDataDTO;

import java.text.ParseException;
import java.util.List;

@EnableAutoConfiguration
@Service
public interface OrdreMission 
{

    List<ListVehiculeAvailable4OrdreMissionDTO> getListVehiculeAvailable4OrdreMissionByStructure(String structure);

    void addNewOrdreMission(NewOrdreMissionDTO newOrdreMissionDTO);

    void modifySelectedOrdreMission(NewOrdreMissionDTO newOrdreMissionDTO);

    void confirmSelectedOrdreMission(Long id);

    void deleteSelectedOrdreMission(Long id);

    List<OrdreMissionTableDataDTO> getPaginationOrdreMissionListBySelectedEtat(UserEntity userEntity, String status, int page, int limit) throws ParseException;

    Long getTotalItemsOrdreMissionListBySelectedEtat(UserEntity userEntity, String status) throws ParseException;

    List<OrdreMissionTableDataDTO> getPaginationVehiculeListDepassantDateRetour(UserEntity userEntity, int page, int limit) throws ParseException;

    Long getTotalItemsVehiculeListDepassantDateRetour(UserEntity userEntity);

    List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForOrdreMission(UserEntity userEntity);

}
