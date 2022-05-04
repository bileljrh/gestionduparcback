package com.gesparc.controllers;


import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.gesparc.entities.administration.additionnel.ModifyMot2PassRequest;
import com.gesparc.repositories.referentiel.AlerteRepository;
import com.gesparc.repositories.referentiel.MessageApplicatifRepository;
import com.gesparc.requests.Administration.AlerteRequest;
import com.gesparc.requests.Administration.MessageApplicatifRequest;
import com.gesparc.requests.Administration.additionnel.NewUserRequest;
import com.gesparc.requests.referentiel.ParametreApplicationRequest;
import com.gesparc.requests.security.RoleRequest;
import com.gesparc.requests.security.UserRequest;
import com.gesparc.responses.administration.AlerteResponse;
import com.gesparc.responses.administration.MessageApplicatifResponse;
import com.gesparc.responses.administration.MessageResponse;
import com.gesparc.responses.administration.TracabiliteResponse;
import com.gesparc.responses.administration.additionnel.CustomUserResponse;
import com.gesparc.responses.administration.additionnel.MagasinUGPResponse;
import com.gesparc.responses.administration.additionnel.StructureUgpMagasinResponse;
import com.gesparc.responses.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataResponse;
import com.gesparc.responses.referentiel.ParametreApplicationResponse;
import com.gesparc.responses.security.RoleResponse;
import com.gesparc.responses.security.RoleResponseDataTable;
import com.gesparc.responses.security.UserDataTableResponse;
import com.gesparc.responses.security.UserResponse;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.exception.domain.EmailExistException;
import com.gesparc.security.exception.domain.UserNotFoundException;
import com.gesparc.security.exception.domain.UsernameExistException;
import com.gesparc.security.repository.RoleRepository;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.security.servicesImpl.UserServiceImpl;
import com.gesparc.servicesImpl.AdministrationImpl;
import com.gesparc.sharedDTO.administration.AlerteDTO;
import com.gesparc.sharedDTO.administration.MessageApplicatifDTO;
import com.gesparc.sharedDTO.administration.MessageDTO;
import com.gesparc.sharedDTO.administration.TracabiliteDTO;
import com.gesparc.sharedDTO.administration.additionnel.NewUserDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataDTO;
import com.gesparc.sharedDTO.referentiel.ParametreApplicationDTO;
import com.gesparc.sharedDTO.referentiel.StructureDTO;
import com.gesparc.sharedDTO.security.RoleDTO;
import com.gesparc.sharedDTO.security.RoleDataTableDTO;
import com.gesparc.sharedDTO.security.UserDTO;
import com.gesparc.sharedDTO.security.UserDataTableDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class  AdministrationController 
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministrationController.class);

    private AdministrationImpl administration;


    private UserServiceImpl userService;


    private UserRepository userRepository;

    private RoleRepository roleRepository;


    private MessageApplicatifRepository messageApplicatifRepository;


    private AlerteRepository alerteRepository;

    TracabiliteController tracabiliteController;

    public AdministrationController(AdministrationImpl administration, UserServiceImpl userService, UserRepository userRepository, RoleRepository roleRepository, MessageApplicatifRepository messageApplicatifRepository, AlerteRepository alerteRepository, TracabiliteController tracabiliteController) 
    {
         this.administration = administration;
         this.userService = userService;
         this.userRepository = userRepository;
         this.roleRepository = roleRepository;
         this.messageApplicatifRepository = messageApplicatifRepository;
         this.alerteRepository = alerteRepository;
         this.tracabiliteController = tracabiliteController;
    }

    @PostMapping(path = "/message_applicatif")
    ResponseEntity<HttpStatus> addNewMessageApplicatif(@RequestBody MessageApplicatifRequest messageApplicatifRequest) {
         if (this.isUserAuthorised("ADD_MESSAGES_APPLICATIFS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              ModelMapper modelMapper = new ModelMapper();
              MessageApplicatifDTO messageApplicatifDTO = modelMapper.map(messageApplicatifRequest, MessageApplicatifDTO.class);
              administration.addNewMessageApplicatif(messageApplicatifDTO);
              tracabiliteController.addNewMessageApplicatifTracabilite(messageApplicatifDTO, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
             return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PutMapping(path = "/message_applicatif")
    ResponseEntity<HttpStatus> modifySelectedMessageApplicatif(@RequestBody MessageApplicatifRequest messageApplicatifRequest) 
    {
         if (this.isUserAuthorised("MODIFY_MESSAGES_APPLICATIFS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              ModelMapper modelMapper = new ModelMapper();
              MessageApplicatifDTO messageApplicatifDTO = modelMapper.map(messageApplicatifRequest, MessageApplicatifDTO.class);
              administration.modifySelectedMessageApplicatif(messageApplicatifDTO);
              tracabiliteController.modifySelectedMessageApplicatifTracabilite(messageApplicatifDTO, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @DeleteMapping(path = "/message_applicatif/{id}")
    ResponseEntity<HttpStatus> deleteSelectedMessageApplicatif(@PathVariable Long id) 
    {
         if (this.isUserAuthorised("DELETE_MESSAGES_APPLICATIFS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              String code = messageApplicatifRepository.findById(id).get().getCode();
              administration.deleteSelectedMessageApplicatif(id);
              tracabiliteController.deleteSelectedMessageApplicatifTracabilite(code, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @GetMapping(path = "/message_applicatif")
    ResponseEntity<List<MessageApplicatifResponse>> getListMessageApplicatif() 
    {
         List<MessageApplicatifResponse> messageApplicatifResponses = new ArrayList<>();
         if (this.isUserAuthorised("VIEW_MESSAGES_APPLICATIFS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              ModelMapper modelMapper = new ModelMapper();
              List<MessageApplicatifDTO> messageApplicatifDTOS = administration.getListMessageApplicatif();
              if (!messageApplicatifDTOS.isEmpty()) 
              {
                   messageApplicatifDTOS.forEach(messageApplicatifDTO -> {
                   messageApplicatifResponses.add(modelMapper.map(messageApplicatifDTO, MessageApplicatifResponse.class));});
              }
              return new ResponseEntity<>(messageApplicatifResponses, HttpStatus.OK);
         } 
         else 
         {
              return new ResponseEntity<>(messageApplicatifResponses, HttpStatus.UNAUTHORIZED);
         }
    }

    @PostMapping(path = "/alerte")
    ResponseEntity<HttpStatus> addNewAlerte(@RequestBody AlerteRequest alerteRequest) 
    {
         if (this.isUserAuthorised("ADD_ALERTES", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              ModelMapper modelMapper = new ModelMapper();
              AlerteDTO alerteDTO = modelMapper.map(alerteRequest, AlerteDTO.class);
              administration.addNewAlerte(alerteDTO);
              tracabiliteController.addNewAlerteTracabilite(alerteDTO, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(HttpStatus.OK);
         }     
         else 
         {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PutMapping(path = "/alerte")
    ResponseEntity<HttpStatus> modifySelectedAlerte(@RequestBody AlerteRequest alerteRequest) 
    {
         if (this.isUserAuthorised("MODIFY_ALERTES", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              ModelMapper modelMapper = new ModelMapper();
              AlerteDTO alerteDTO = modelMapper.map(alerteRequest, AlerteDTO.class);
              administration.modifySelectedAlerte(alerteDTO);
              tracabiliteController.modifySelectedAlerteTracabilite(alerteDTO, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(path = "/alerte/{id}")
    ResponseEntity<HttpStatus> deleteSelectedAlerte(@PathVariable Long id) 
    {
         if (this.isUserAuthorised("DELETE_ALERTES", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
             String code = alerteRepository.findById(id).get().getCode();
             administration.deleteSelectedAlerte(id);
             tracabiliteController.deleteSelectedAlerteTracabilite(code, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
             return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @GetMapping(path = "/alerte")
    ResponseEntity<List<AlerteResponse>> getListAlerte() 
    {
         List<AlerteResponse> alerteResponses = new ArrayList<>();
         if (this.isUserAuthorised("VIEW_ALERTES", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
             ModelMapper modelMapper = new ModelMapper();
             List<AlerteDTO> alerteDTOS = administration.getListAlerte();
             if (!alerteDTOS.isEmpty()) 
             {
                  alerteDTOS.forEach(alerteDTO -> {
                  alerteResponses.add(modelMapper.map(alerteDTO, AlerteResponse.class));});
             }
             return new ResponseEntity<>(alerteResponses, HttpStatus.OK);
             } 
             else 
             {
                 return new ResponseEntity<>(alerteResponses, HttpStatus.UNAUTHORIZED);
             }
    }

    @PostMapping(path = "/login")
    ResponseEntity<CustomUserResponse> login(@RequestBody UserRequest userRequest) 
    {
         List<String> authorities = new ArrayList<>();
         ModelMapper modelMapper = new ModelMapper();
         UserDTO userDTO = modelMapper.map(userRequest, UserDTO.class);
         HttpHeaders jwtHeader = userService.login(userDTO);
         UserEntity userEntity = userRepository.findFirstByMatricule(userRequest.getMatricule());
         userEntity.setDateDerniereConnexion(new Date());
         userRepository.save(userEntity);
         if (!userEntity.getRoles().isEmpty()) 
         {
             userEntity.getRoles().forEach(roleEntity -> {
               if (!StringUtils.isBlank(roleEntity.getAuthorities()))
               {
                    List<String> tempAuthorities = Arrays.asList(StringUtils.splitPreserveAllTokens(roleEntity.getAuthorities(), ","));
                    if (!tempAuthorities.isEmpty()) 
                    {
                        tempAuthorities.forEach(s -> {
                        if (!authorities.contains(s)) 
                        {
                              authorities.add(s);
                        }});
                    }
                }});
         }
         CustomUserResponse customUserResponse = new CustomUserResponse();
         customUserResponse.setId(userEntity.getId());
         customUserResponse.setNom(userEntity.getNom());
         customUserResponse.setPrenom(userEntity.getPrenom());
         customUserResponse.setMatricule(userEntity.getMatricule());
         customUserResponse.setEmail(userEntity.getEmail());
         customUserResponse.setTypeCompte(userEntity.getTypeCompte());
         customUserResponse.setStructures(userEntity.getStructures());
         customUserResponse.setUgps(userEntity.getUgps());
         customUserResponse.setMagasins(userEntity.getMagasins());
         customUserResponse.setAuthorities(authorities);
         return new ResponseEntity<>(customUserResponse, jwtHeader, OK);
    }

    @PostMapping(path = "/utilisateur")
    ResponseEntity<HttpStatus> addNewUtilisateur(@RequestBody NewUserRequest newUserRequest) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException
    {
         if (this.isUserAuthorised("ADD_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()))
         {
             ModelMapper modelMapper = new ModelMapper();
             NewUserDTO newUserDTO = modelMapper.map(newUserRequest, NewUserDTO.class);
             userService.addNewUtilisateur(newUserDTO);
             tracabiliteController.addNewUtilisateurTracabilite(newUserDTO, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
             return new ResponseEntity<>(HttpStatus.OK);
         }
         else 
         {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PutMapping(path = "/utilisateur")
    ResponseEntity<UserResponse> modifySelectedUtilisateur(@RequestBody NewUserRequest newUserRequest) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException 
    {
         if (this.isUserAuthorised("MODIFY_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
             Long id = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).getId();
             ModelMapper modelMapper = new ModelMapper();
             NewUserDTO newUserDTO = modelMapper.map(newUserRequest, NewUserDTO.class);
             UserDTO userDTO1 = userService.modifySelectedUtilisateur(newUserDTO);
             UserResponse userResponse = modelMapper.map(userDTO1, UserResponse.class);
             return new ResponseEntity<>(userResponse, HttpStatus.OK);
         } 
         else 
         {
             UserResponse userResponse = new UserResponse();
             return new ResponseEntity<>(userResponse, HttpStatus.UNAUTHORIZED);
         }
     }

    @DeleteMapping(path = "/utilisateur/{id}")
    ResponseEntity<HttpStatus> deleteSelectedUtilisateur(@PathVariable Long id) 
    {
         if (this.isUserAuthorised("DELETE_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
             Long id1 = userRepository.findFirstByMatricule(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).getId();
             String nom = userRepository.findById(id).get().getNom();
             String prenom = userRepository.findById(id).get().getPrenom();
             String matriculeUtilisateur = userRepository.findById(id).get().getMatricule();
             userService.deleteSelectedUtilisateur(id);
             return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @GetMapping(path = "/utilisateur")
    ResponseEntity<List<UserResponse>> getListUtilisateur(@RequestParam(value = "structure") String structure) 
    {
         ModelMapper modelMapper = new ModelMapper();
         List<UserResponse> userResponses = new ArrayList<>();
         if (this.isUserAuthorised("VIEW_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              List<UserDTO> userDTOS = userService.getListUtilisateur(structure);
              if (!userDTOS.isEmpty()) 
              {
                   userDTOS.forEach(userDTO -> {
                   userResponses.add(modelMapper.map(userDTO, UserResponse.class));});
              }
              return new ResponseEntity<>(userResponses, HttpStatus.OK);
         }
         else 
         {
              return new ResponseEntity<>(userResponses, HttpStatus.UNAUTHORIZED);
         }
    }
    
    @GetMapping(path = "/user")
    ResponseEntity<List<UserDataTableResponse>> getListUser(@RequestParam(value = "page") String page,@RequestParam(value = "limit") String limit,@RequestParam(value = "structure") String structure) 
    {
         List<UserDataTableResponse> userResponsesDataTable = new ArrayList<>();
    	 if (this.isUserAuthorised("VIEW_GROUPES_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
    	 ModelMapper modelMapper = new ModelMapper();
         List<UserDataTableDTO> userDataTableDTOS =  userService.getListUser(Integer.parseInt(page), Integer.parseInt(limit),structure);
         if (!userDataTableDTOS.isEmpty()) 
         {
        	  userDataTableDTOS.forEach(userDataTableDTO -> {
        	  userResponsesDataTable.add(modelMapper.map(userDataTableDTO, UserDataTableResponse.class)); });
         }
         return new ResponseEntity<>(userResponsesDataTable, HttpStatus.OK);
         } 
    	 else 
    	 {
             return new ResponseEntity<>(userResponsesDataTable, HttpStatus.UNAUTHORIZED);
         }
   	
    }
    
    @PostMapping(path = "/activation_utilisateur")
    ResponseEntity<HttpStatus> activateDesactivateSelectedUtilisateur(@RequestBody Long id) 
    {
         if (this.isUserAuthorised("ACTIVATE_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              userService.activateDesactivateSelectedUtilisateur(id);
              tracabiliteController.activateDesactivateSelectedUtilisateurTracabilite(id, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PostMapping(path = "/verrouillage_utilisateur")
    ResponseEntity<HttpStatus> verrouillageDeverrouillageUtilisateur(@RequestBody Long id) 
    {
         if (this.isUserAuthorised("LOCK_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              userService.verrouillageDeverrouillageUtilisateur(id);
              tracabiliteController.verrouillageDeverrouillageUtilisateurTracabilite(id, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(HttpStatus.OK);
         }
         else 
         {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PutMapping(path = "/mot2pass_utilisateur")
    ResponseEntity<HttpStatus> modificationMot2PassUtilisateur(@RequestBody ModifyMot2PassRequest modifyMot2PassRequest)
    {
          if (this.isUserAuthorised("MODIFY_PASSWORD_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()))
          {
               userService.modificationMot2PassUtilisateur(modifyMot2PassRequest.getId(), modifyMot2PassRequest.getAncienMot2pass(), modifyMot2PassRequest.getNouveauMot2pass());
               tracabiliteController.modificationMot2PassUtilisateurTracabilite(modifyMot2PassRequest.getId(), SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
               return new ResponseEntity<>(OK);
          }
          else 
          {
               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
          }
    }

    @PostMapping(path = "/mot2pass_utilisateur/{id}")
    ResponseEntity<HttpStatus> reinitialisationMot2PassUtilisateur(@PathVariable Long id, @RequestBody String nouveauMot2pass) 
    {
         if (this.isUserAuthorised("RESET_PASSWORD_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              userService.reinitialisationMot2PassUtilisateur(id, nouveauMot2pass);
              tracabiliteController.reinitialisationMot2PassUtilisateurTracabilite(id, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PostMapping(path = "/parametre_application")
    ResponseEntity<HttpStatus> addParametreApplication(@RequestBody ParametreApplicationRequest parametreApplicationRequest) 
    {
         ModelMapper modelMapper = new ModelMapper();
         ParametreApplicationDTO parametreApplicationDTO = modelMapper.map(parametreApplicationRequest, ParametreApplicationDTO.class);
         administration.addParametreApplication(parametreApplicationDTO);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/parametre_application")
    ResponseEntity<HttpStatus> modifyParametreApplication(@RequestBody ParametreApplicationRequest parametreApplicationRequest) 
    {
         ModelMapper modelMapper = new ModelMapper();
         ParametreApplicationDTO parametreApplicationDTO = modelMapper.map(parametreApplicationRequest, ParametreApplicationDTO.class);
         administration.modifyParametreApplication(parametreApplicationDTO);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/parametre_application")
    ResponseEntity<ParametreApplicationResponse> getParametreApplication() 
    {
         ModelMapper modelMapper = new ModelMapper();
         ParametreApplicationDTO parametreApplicationDTO = administration.getParametreApplication();
         ParametreApplicationResponse parametreApplicationResponse = modelMapper.map(parametreApplicationDTO, ParametreApplicationResponse.class);
         return new ResponseEntity<>(parametreApplicationResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/structure_ugp_magasin")
    ResponseEntity<List<StructureUgpMagasinResponse>> getListStructureUgpMagasin() 
    {
         List<StructureUgpMagasinResponse> structureUgpMagasinResponses = new ArrayList<>();
         List<StructureDTO> structureDTOS = administration.getListStructureUgpMagasin();
         if (!structureDTOS.isEmpty()) 
         {
              structureDTOS.forEach(structureDTO -> {
              StructureUgpMagasinResponse structureUgpMagasinResponse = new StructureUgpMagasinResponse();
              structureUgpMagasinResponse.setIdStructure(structureDTO.getId());
              structureUgpMagasinResponse.setDesignationStructure(structureDTO.getDesignation());
              structureUgpMagasinResponse.setCodeStructure(structureDTO.getCode());
              if (structureDTO.getUgp() != null) 
              {
                   structureUgpMagasinResponse.setIdUgp(structureDTO.getUgp().getId());
                   structureUgpMagasinResponse.setDesignationUgp(structureDTO.getUgp().getDesignation());
                   structureUgpMagasinResponse.setCodeUgp(structureDTO.getUgp().getCode());
                   if (!structureDTO.getUgp().getMagasins().isEmpty()) 
                   {
                         structureUgpMagasinResponse.setMagasins(new ArrayList<>());
                         structureDTO.getUgp().getMagasins().forEach(magasinDTO -> {
                         MagasinUGPResponse magasinUGPResponse = new MagasinUGPResponse();
                         magasinUGPResponse.setIdMagasin(magasinDTO.getId());
                         magasinUGPResponse.setCodeMagasin(magasinDTO.getCode());
                         magasinUGPResponse.setDesignationMagasin(magasinDTO.getDesignation());
                         structureUgpMagasinResponse.getMagasins().add(magasinUGPResponse);});
                   }
                }
                structureUgpMagasinResponses.add(structureUgpMagasinResponse);});
         }
         return new ResponseEntity<>(structureUgpMagasinResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/pagination_tracabilite")
    ResponseEntity<List<TracabiliteResponse>> getPaginationListTracabilite(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit, @RequestParam(name = "idUser") String idUser, @RequestParam(name = "nomModule") String nomModule, @RequestParam(name = "dateMin") String dateMin, @RequestParam(name = "dateMax") String dateMax) 
    {
         List<TracabiliteResponse> tracabiliteResponses = new ArrayList<>();
         if (this.isUserAuthorised("VIEW_TRACABILITES", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
         ModelMapper modelMapper = new ModelMapper();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         List<TracabiliteDTO> tracabiliteDTOS = administration.getPaginationListTracabilite(Integer.parseInt(page), Integer.parseInt(limit), Long.parseLong(idUser), nomModule, LocalDate.parse(dateMin, formatter), LocalDate.parse(dateMax, formatter));
         if (!tracabiliteDTOS.isEmpty()) 
         {
              tracabiliteDTOS.forEach(tracabiliteDTO -> {
              tracabiliteResponses.add(modelMapper.map(tracabiliteDTO, TracabiliteResponse.class));});
         }
         return new ResponseEntity<>(tracabiliteResponses, HttpStatus.OK);
         } 
         else 
         {
              return new ResponseEntity<>(tracabiliteResponses, HttpStatus.UNAUTHORIZED);
         }
    }

    @GetMapping(path = "/total_item_tracabilite")
    ResponseEntity<Long> getTotalItemTracabilite(@RequestParam(name = "idUser") String idUser, @RequestParam(name = "nomModule") String nomModule, @RequestParam(name = "dateMin") String dateMin, @RequestParam(name = "dateMax") String dateMax) 
    {
         Long totaItem;
         if (this.isUserAuthorised("VIEW_TRACABILITES", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
              totaItem = administration.getTotalItemTracabilite(Long.parseLong(idUser), nomModule, LocalDate.parse(dateMin, formatter), LocalDate.parse(dateMax, formatter));
              return new ResponseEntity<>(totaItem, HttpStatus.OK);
         } 
         else 
         {
              totaItem = (long) 0;
              return new ResponseEntity<>(totaItem, HttpStatus.UNAUTHORIZED);
         }
    }

    @DeleteMapping(path = "/tracabilite/{id}")
    ResponseEntity<HttpStatus> deleteSelectedTracabilite(@PathVariable Long id) 
    {
         if (this.isUserAuthorised("DELETE_TRACABILITES", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
              administration.deleteSelectedTracabilite(id);
              return new ResponseEntity<>(HttpStatus.OK);
         } 
         else 
         {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PostMapping(path = "/groupe_utilisateur/{profil}")
    ResponseEntity<HttpStatus> addNewGroupeUtilisateur(@PathVariable String profil, @RequestBody String designation) {
        if (this.isUserAuthorised("ADD_GROUPES_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
            administration.addNewGroupeUtilisateur(profil, designation);
            tracabiliteController.addNewGroupeUtilisateurTracabilite(profil, designation, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping(path = "/groupe_authorities/{id}")
    ResponseEntity<HttpStatus> addGroupeAuthorities(@RequestBody List<String> authorities, @PathVariable Long id) 
    {
         if (this.isUserAuthorised("MODIFY_GROUPES_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
        	  LOGGER.info(authorities.toString());
              administration.addGroupeAuthorities(id, authorities);
              tracabiliteController.addGroupeAuthoritiesTracabilite(id, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(OK);
         } 
         else 
         {
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PostMapping(path = "/groupe_utilisateurs/{id}")
    ResponseEntity<HttpStatus> addGroupeUtilisateurs(@RequestBody List<String> utilisateurs, @PathVariable Long id)
    {
         if (this.isUserAuthorised("MODIFY_GROUPES_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
         {
        	 System.out.println(utilisateurs);
              administration.addGroupeUtilisateurs(id, utilisateurs);
              tracabiliteController.addGroupeUtilisateursTracabilite(id, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
              return new ResponseEntity<>(OK);
         } 
         else 
         {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }
    
    @GetMapping(path = "/groupe_utilisateur")
    ResponseEntity<List<RoleResponseDataTable>> getPaginationRole(@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit)
    {
          List<RoleResponseDataTable> roleResponsesDataTable = new ArrayList<>();
    	  if (this.isUserAuthorised("VIEW_GROUPES_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
    	  {
    	       ModelMapper modelMapper = new ModelMapper();
               List<RoleDataTableDTO> roleDataTableDTOS =  administration.getListGroupeUtilisateur(Integer.parseInt(page), Integer.parseInt(limit));
               if (!roleDataTableDTOS.isEmpty()) 
               {
        	         roleDataTableDTOS.forEach(roleDataTableDTO -> {
        		     roleResponsesDataTable.add(modelMapper.map(roleDataTableDTO, RoleResponseDataTable.class));});
               }
                return new ResponseEntity<>(roleResponsesDataTable, HttpStatus.OK);
           } 
    	   else 
    	   {
                return new ResponseEntity<>(roleResponsesDataTable, HttpStatus.UNAUTHORIZED);
           }
        	
    }
    
    @DeleteMapping(path = "/groupe_utilisateur/{id}")
    ResponseEntity<HttpStatus> deleteSelectedGroupeUtilisateur(@PathVariable Long id) 
    {
          if (this.isUserAuthorised("DELETE_GROUPES_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
          {
               String designation = roleRepository.findById(id).get().getDesignation();
               String profil = roleRepository.findById(id).get().getProfil();
               administration.deleteSelectedGroupeUtilisateur(id);
               tracabiliteController.deleteSelectedGroupeUtilisateurTracabilite(designation, profil, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
               return new ResponseEntity<>(HttpStatus.OK);
          } 
          else 
          {
               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
          }
    }

    @PutMapping(path = "/groupe_utilisateur")
    ResponseEntity<HttpStatus> modifySelectedGroupeUtilisateur(@RequestBody RoleRequest roleRequest) 
    {
          if (this.isUserAuthorised("MODIFY_GROUPES_USERS", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) 
          {
               ModelMapper modelMapper = new ModelMapper();
               RoleDTO roleDTO = modelMapper.map(roleRequest, RoleDTO.class);
               administration.deleteSelectedGroupeUtilisateur(roleDTO);
               tracabiliteController.modifySelectedGroupeUtilisateurTracabilite(roleDTO.getId(), SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
               return new ResponseEntity<>(HttpStatus.OK);
           } 
          else 
          {
               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
          }
    }

    @PostMapping(path = "/reset_password/{matricule}")
    ResponseEntity<HttpStatus> sendNewMessage(@PathVariable String matricule, @RequestBody String email) 
    {
          administration.sendNewMessage(matricule, email);
          return new ResponseEntity<>(OK);
    }

    @GetMapping(path = "/message/{structure}")
    ResponseEntity<List<MessageResponse>> getListMessage(@PathVariable String structure) 
    {
          ModelMapper modelMapper = new ModelMapper();
          List<MessageResponse> messageResponses = new ArrayList<>();
          List<MessageDTO> messageDTOS = administration.getListMessage(structure);
          if (!messageDTOS.isEmpty()) 
          {
               messageDTOS.forEach(messageDTO -> {
               messageResponses.add(modelMapper.map(messageDTO, MessageResponse.class));});
          }
          return new ResponseEntity<>(messageResponses, OK);
    }

    @GetMapping(path = "/nombre_message/{structure}")
    ResponseEntity<Integer> getNombreMessages(@PathVariable String structure) 
    {
          int nombreMessages = administration.getNombreMessages(structure);
          return new ResponseEntity<>(nombreMessages, OK);
    }

    @DeleteMapping(path = "/message/{id}")
    ResponseEntity<HttpStatus> deleteSelectedMessage(@PathVariable Long id) 
    {
          administration.deleteSelectedMessage(id);
          return new ResponseEntity<>(OK);
    }

    private boolean isUserAuthorised(String authority, String matricule) 
    {
          boolean isAuthorised = false;
          List<String> userAuthorities = new ArrayList<>();
          UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
          if (!userEntity.getRoles().isEmpty()) 
          {
                userEntity.getRoles().forEach(roleEntity -> {
                if (!StringUtils.isBlank(roleEntity.getAuthorities())) 
                {
                      List<String> tempAuthorities = Arrays.asList(StringUtils.splitPreserveAllTokens(roleEntity.getAuthorities(), ","));
                      if (!tempAuthorities.isEmpty()) 
                      {
                          tempAuthorities.forEach(s -> {
                          if (!userAuthorities.contains(s)) 
                          {
                                userAuthorities.add(s);
                          }});
                      }
                }});
         }
         if (!userAuthorities.isEmpty()) 
         {
            if (userAuthorities.contains(authority)) 
            {
                isAuthorised = true;
            }
        }
        return isAuthorised;
    }

}
