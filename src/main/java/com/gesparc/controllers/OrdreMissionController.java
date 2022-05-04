package com.gesparc.controllers;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.gesparc.entities.ordreMission.OrdreMissionEntity;
import com.gesparc.repositories.VehiculeRepository;
import com.gesparc.repositories.ordreMission.OrdreMissionRepository;
import com.gesparc.requests.OrdreMission.Additionnel.NewOrdreMissionRequest;
import com.gesparc.responses.administratif.additionnel.SelectVehiculeResponse;
import com.gesparc.responses.ordreMission.Additionnel.OrdreMissionTableDataResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.servicesImpl.OrdreMissionImpl;
import com.gesparc.sharedDTO.administratif.Additionnel.SelectVehiculeDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.NewOrdreMissionDTO;
import com.gesparc.sharedDTO.ordreMission.Additionnel.OrdreMissionTableDataDTO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class OrdreMissionController {

	@Autowired
	OrdreMissionImpl ordreMission;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VehiculeRepository vehiculeRepository;

	@Autowired
	private TracabiliteController tracabiliteController;

	@Autowired
	private OrdreMissionRepository ordreMissionRepository;

	Logger logger = LoggerFactory.getLogger(OrdreMissionController.class);

	public TracabiliteController getTracabiliteController() 
	{
		return tracabiliteController;
	}

	public void setTracabiliteController(TracabiliteController tracabiliteController) 
	{
		this.tracabiliteController = tracabiliteController;
	}
	
    //Ajout nouvelle ordre mission
	@PostMapping(path = "/ordre_mission")
	public ResponseEntity<HttpStatus> addNewOrdreMission(@RequestBody NewOrdreMissionRequest newOrdreMissionRequest) 
	{
		 ModelMapper modelMapper = new ModelMapper();
		 NewOrdreMissionDTO newOrdreMissionDTO = modelMapper.map(newOrdreMissionRequest, NewOrdreMissionDTO.class);
		 ordreMission.addNewOrdreMission(newOrdreMissionDTO);
		 tracabiliteController.addNewOrdreMissionTracabilite(newOrdreMissionDTO.getNumeroOrdre(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//Modification nouvelle ordre mission
	@PutMapping(path = "/ordre_mission")
	public ResponseEntity<HttpStatus> modifySelectedOrdreMission(@RequestBody NewOrdreMissionRequest newOrdreMissionRequest) 
	{
		 ModelMapper modelMapper = new ModelMapper();
		 NewOrdreMissionDTO newOrdreMissionDTO = modelMapper.map(newOrdreMissionRequest, NewOrdreMissionDTO.class);
		 ordreMission.modifySelectedOrdreMission(newOrdreMissionDTO);
		 tracabiliteController.modifySelectedOrdreMissionTracabilite(newOrdreMissionDTO.getNumeroOrdre(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Confirmation ordre mission
	@PostMapping(path = "/confirm_ordre_mission")
	public void confirmSelectedOrdreMission(@RequestBody Long id) 
	{
		 OrdreMissionEntity ome = ordreMissionRepository.findById(id).get();
		 tracabiliteController.confirmSelectedOrdreMissionTracabilite(ome.getNumeroOrdre(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ordreMission.confirmSelectedOrdreMission(id);
	}

	//Delete ordre mission
	@DeleteMapping(path = "/ordre_mission/{id}")
	public void deleteSelectedOrdreMission(@PathVariable Long id)
	{
		 OrdreMissionEntity ome = ordreMissionRepository.findById(id).get();
		 tracabiliteController.deleteSelectedOrdreMissionTracabilite(ome.getNumeroOrdre(),
		 SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 ordreMission.deleteSelectedOrdreMission(id);
	}

	//Liste  ordre mission
	@GetMapping(path = "/pagination_ordre_mission")
	ResponseEntity<List<OrdreMissionTableDataResponse>> getPaginationOrdreMissionListBySelectedEtat(@RequestParam(value = "status") String status, @RequestParam(value = "page") String page,@RequestParam(value = "limit") String limit) {
		 ModelMapper modelMapper = new ModelMapper();
		 List<OrdreMissionTableDataResponse> ordreMissionTableDataResponses = new ArrayList<>();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<OrdreMissionTableDataDTO> ordreMissionTableDataDTOS = ordreMission.getPaginationOrdreMissionListBySelectedEtat(userEntity, status, Integer.parseInt(page),
		 Integer.parseInt(limit));
		 if (!ordreMissionTableDataDTOS.isEmpty()) 
		 {
			    ordreMissionTableDataDTOS.forEach(ordreMissionTableDataDTO -> 
			    {
				      ordreMissionTableDataResponses.add(modelMapper.map(ordreMissionTableDataDTO, OrdreMissionTableDataResponse.class));
			    });
		 }
		return new ResponseEntity<>(ordreMissionTableDataResponses, HttpStatus.OK);
	}

	//Liste  ordre mission avec etat
	@GetMapping(path = "/total_items_ordre_mission")
	ResponseEntity<Long> getTotalItemsOrdreMissionListBySelectedEtat(@RequestParam(value = "status") String status) 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 Long totalItems = ordreMission.getTotalItemsOrdreMissionListBySelectedEtat(userEntity, status);
		 return new ResponseEntity<>(totalItems, HttpStatus.OK);
	}

	//Liste  vehicule avec date retour
	@GetMapping(path = "/pagination_vehicule_depasse_date_retour")
	ResponseEntity<List<OrdreMissionTableDataResponse>> getPaginationVehiculeListDepassantDateRetour(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit)throws ParseException  
	{
		 ModelMapper modelMapper = new ModelMapper();
		 List<OrdreMissionTableDataResponse> ordreMissionTableDataResponses = new ArrayList<>();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<OrdreMissionTableDataDTO> ordreMissionTableDataDTOS = ordreMission.getPaginationVehiculeListDepassantDateRetour(userEntity, Integer.parseInt(page),
	     Integer.parseInt(limit));
		 if (!ordreMissionTableDataDTOS.isEmpty()) 
		       {
			         ordreMissionTableDataDTOS.forEach(ordreMissionTableDataDTO -> {
				     ordreMissionTableDataResponses.add(modelMapper.map(ordreMissionTableDataDTO, OrdreMissionTableDataResponse.class));});      
		       }
	           return new ResponseEntity<>(ordreMissionTableDataResponses, HttpStatus.OK);
	 }

	//Liste  vehicule avec date retour
	@GetMapping(path = "/total_items_vehicule_depasse_date_retour")
	ResponseEntity<Long> getTotalItemsVehiculeListDepassantDateRetour() throws ParseException 
	{
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 Long totalItems = ordreMission.getTotalItemsVehiculeListDepassantDateRetour(userEntity);
		 return new ResponseEntity<>(totalItems, HttpStatus.OK);
	}
	
	//Liste  vehicule d'ordre mission
	@GetMapping(path = "/select_vehicule_ordre_mission")
	ResponseEntity<List<SelectVehiculeResponse>> getSelectVehiculeByStrucutureForOrdreMission() 
	{
		 List<SelectVehiculeResponse> selectVehiculeResponses = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
		 UserEntity userEntity = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		 List<SelectVehiculeDTO> selectVehiculeDTOS = ordreMission.getSelectVehiculeByStrucutureForOrdreMission(userEntity);
		 if (!selectVehiculeDTOS.isEmpty()) 
		 {
			    selectVehiculeDTOS.forEach(selectVehiculeDTO -> {
				selectVehiculeResponses.add(modelMapper.map(selectVehiculeDTO, SelectVehiculeResponse.class));
			});
		 }
	return new ResponseEntity<>(selectVehiculeResponses, HttpStatus.OK);
	}
}
