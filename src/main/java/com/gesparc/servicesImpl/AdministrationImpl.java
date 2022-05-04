package com.gesparc.servicesImpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gesparc.entities.administration.AlerteEntity;
import com.gesparc.entities.administration.MessageApplicatifEntity;
import com.gesparc.entities.administration.MessageEntity;
import com.gesparc.entities.administration.TracabiliteEntity;
import com.gesparc.entities.carburant.cartePlafond.HistoriqueAffectationCartePlafondEntity;
import com.gesparc.entities.referentiel.ParametreApplicationEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.repositories.administration.MessageRepository;
import com.gesparc.repositories.administration.TracabiliteRepository;
import com.gesparc.repositories.referentiel.AlerteRepository;
import com.gesparc.repositories.referentiel.MessageApplicatifRepository;
import com.gesparc.repositories.referentiel.ParametreApplicationRepository;
import com.gesparc.repositories.referentiel.StructureRepository;
import com.gesparc.security.entity.RoleEntity;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.RoleRepository;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.services.Administration;
import com.gesparc.sharedDTO.administration.AlerteDTO;
import com.gesparc.sharedDTO.administration.MessageApplicatifDTO;
import com.gesparc.sharedDTO.administration.MessageDTO;
import com.gesparc.sharedDTO.administration.TracabiliteDTO;
import com.gesparc.sharedDTO.carburant.additionnel.HistoriqueAffectationCartePlafondTabDataDTO;
import com.gesparc.sharedDTO.ordreMission.AccompagnonMissionDTO;
import com.gesparc.sharedDTO.referentiel.MagasinDTO;
import com.gesparc.sharedDTO.referentiel.ParametreApplicationDTO;
import com.gesparc.sharedDTO.referentiel.StructureDTO;
import com.gesparc.sharedDTO.referentiel.UgpDTO;
import com.gesparc.sharedDTO.security.RoleDTO;
import com.gesparc.sharedDTO.security.RoleDataTableDTO;
import com.gesparc.sharedDTO.security.UserDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
@Service
public class AdministrationImpl implements Administration {

	Logger logger = LoggerFactory.getLogger(AdministrationImpl.class);

	private MessageRepository messageRepository;

	private MessageApplicatifRepository messageApplicatifRepository;

	private AlerteRepository alerteRepository;

	private ParametreApplicationRepository parametreApplicationRepository;

	private StructureRepository structureRepository;

	private UserRepository userRepository;

	private TracabiliteRepository tracabiliteRepository;

	private RoleRepository roleRepository;

	public AdministrationImpl(MessageRepository messageRepository,
			MessageApplicatifRepository messageApplicatifRepository, AlerteRepository alerteRepository,
			ParametreApplicationRepository parametreApplicationRepository, StructureRepository structureRepository,
			UserRepository userRepository, TracabiliteRepository tracabiliteRepository, RoleRepository roleRepository) {
		this.messageRepository = messageRepository;
		this.messageApplicatifRepository = messageApplicatifRepository;
		this.alerteRepository = alerteRepository;
		this.parametreApplicationRepository = parametreApplicationRepository;
		this.structureRepository = structureRepository;
		this.userRepository = userRepository;
		this.tracabiliteRepository = tracabiliteRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void addNewMessageApplicatif(MessageApplicatifDTO messageApplicatifDTO) {
		ModelMapper modelMapper = new ModelMapper();
		MessageApplicatifEntity messageApplicatifEntity = modelMapper.map(messageApplicatifDTO,
				MessageApplicatifEntity.class);
		messageApplicatifRepository.save(messageApplicatifEntity);
	}

	@Override
	public void modifySelectedMessageApplicatif(MessageApplicatifDTO messageApplicatifDTO) {
		ModelMapper modelMapper = new ModelMapper();
		MessageApplicatifEntity messageApplicatifEntity = modelMapper.map(messageApplicatifDTO,
				MessageApplicatifEntity.class);
		messageApplicatifRepository.save(messageApplicatifEntity);
	}

	@Override
	public void deleteSelectedMessageApplicatif(Long id) {
		messageApplicatifRepository.deleteById(id);
	}

	@Override
	public List<MessageApplicatifDTO> getListMessageApplicatif() {
		ModelMapper modelMapper = new ModelMapper();
		List<MessageApplicatifDTO> messageApplicatifDTOS = new ArrayList<>();
		List<MessageApplicatifEntity> messageApplicatifEntities = (List<MessageApplicatifEntity>) messageApplicatifRepository
				.findAll();
		if (!messageApplicatifEntities.isEmpty()) {
			messageApplicatifEntities.forEach(messageApplicatifEntity -> {
				messageApplicatifDTOS.add(modelMapper.map(messageApplicatifEntity, MessageApplicatifDTO.class));
			});
		}
		return messageApplicatifDTOS;
	}

	@Override
	public void addNewAlerte(AlerteDTO alerteDTO) {
		ModelMapper modelMapper = new ModelMapper();
		AlerteEntity alerteEntity = modelMapper.map(alerteDTO, AlerteEntity.class);
		alerteRepository.save(alerteEntity);
	}

	@Override
	public void modifySelectedAlerte(AlerteDTO alerteDTO) {
		ModelMapper modelMapper = new ModelMapper();
		AlerteEntity alerteEntity = modelMapper.map(alerteDTO, AlerteEntity.class);
		alerteRepository.save(alerteEntity);
	}

	@Override
	public void deleteSelectedAlerte(Long id) {
		alerteRepository.deleteById(id);
	}

	@Override
	public List<AlerteDTO> getListAlerte() {
		ModelMapper modelMapper = new ModelMapper();
		List<AlerteDTO> alerteDTOS = new ArrayList<>();
		List<AlerteEntity> alerteEntities = (List<AlerteEntity>) alerteRepository.findAll();
		if (!alerteEntities.isEmpty()) {
			alerteEntities.forEach(alerteEntity -> {
				alerteDTOS.add(modelMapper.map(alerteEntity, AlerteDTO.class));
			});
		}
		return alerteDTOS;
	}

	@Override
	public void addParametreApplication(ParametreApplicationDTO parametreApplicationDTO) {
		ModelMapper modelMapper = new ModelMapper();
		ParametreApplicationEntity parametreApplicationEntity = modelMapper.map(parametreApplicationDTO,
				ParametreApplicationEntity.class);
		parametreApplicationRepository.save(parametreApplicationEntity);
	}

	@Override
	public void modifyParametreApplication(ParametreApplicationDTO parametreApplicationDTO) {
		ModelMapper modelMapper = new ModelMapper();
		ParametreApplicationEntity parametreApplicationEntity = modelMapper.map(parametreApplicationDTO,
				ParametreApplicationEntity.class);
		parametreApplicationRepository.save(parametreApplicationEntity);
	}

	@Override
	public void deleteParametreApplication(Long id) {
		parametreApplicationRepository.deleteById(id);
	}

	@Override
	public ParametreApplicationDTO getParametreApplication() {
		ModelMapper modelMapper = new ModelMapper();
		ParametreApplicationDTO parametreApplicationDTO = new ParametreApplicationDTO();
		List<ParametreApplicationEntity> parametreApplicationEntities = (List<ParametreApplicationEntity>) parametreApplicationRepository
				.findAll();
		if (!parametreApplicationEntities.isEmpty()) {
			parametreApplicationDTO = modelMapper.map(parametreApplicationEntities.get(0),
					ParametreApplicationDTO.class);
		}
		return parametreApplicationDTO;
	}

	@Override
	public List<StructureDTO> getListStructureUgpMagasin() {
		ModelMapper modelMapper = new ModelMapper();
		List<StructureDTO> structureDTOS = new ArrayList<>();
		List<StructureEntity> structureEntities = (List<StructureEntity>) structureRepository.findAll();
		if (!structureEntities.isEmpty()) {
			structureEntities.forEach(structureEntity -> {
				structureDTOS.add(modelMapper.map(structureEntity, StructureDTO.class));
			});
		}
		return structureDTOS;
	}

	@Override
	public List<TracabiliteDTO> getPaginationListTracabilite(int page, int limit, Long idUser, String nomModule,
			LocalDate dateMin, LocalDate dateMax) {
		ModelMapper modelMapper = new ModelMapper();
		List<TracabiliteDTO> tracabiliteDTOS = new ArrayList<>();
		List<TracabiliteEntity> tracabiliteEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		LocalDate dateReference = LocalDate.of(2021, 1, 1);
		LocalDate dateMin4Search;
		LocalDate dateMax4Search;
		if (idUser == -1) {

			if (nomModule.equals("tousModules")) {

				if (dateMin.equals(dateReference)) {
					dateMin4Search = tracabiliteRepository.getMinDateForAllModulesAndUsers();
					if (dateMax.equals(dateReference)) {
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository.findAll(pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					}
				} else {
					dateMin4Search = dateMin;
					if (dateMax.equals(dateReference)) {
						dateMax4Search = tracabiliteRepository.getMaxDateForAllModulesAndUsers();
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					}
				}
			} else {

				if (dateMin.equals(dateReference)) {
					dateMin4Search = tracabiliteRepository.getMinDateForAllUsersByModule(nomModule);
					if (dateMax.equals(dateReference)) {
						dateMax4Search = tracabiliteRepository.getMaxDateForAllUsersByModule(nomModule);
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					}
				} else {
					dateMin4Search = dateMin;
					if (dateMax.equals(dateReference)) {
						dateMax4Search = tracabiliteRepository.getMaxDateForAllUsersByModule(nomModule);
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetweenAndNomModule(dateMin4Search.minusDays(1),
										dateMax4Search.plusDays(1), nomModule, pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						tracabiliteEntities = tracabiliteEntityPage.getContent();
					}
				}
			}

		} else {
			if (userRepository.existsById(idUser)) {
				if (tracabiliteRepository.existsByUserId(idUser)) {
					UserEntity userEntity = userRepository.findById(idUser).get();
					if (nomModule.equals("tousModules")) {

						if (dateMin.equals(dateReference)) {
							dateMin4Search = tracabiliteRepository.getMinDateForAllModulesByUser(idUser);
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateForAllModulesByUser(idUser);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							}
						} else {
							dateMin4Search = dateMin;
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateForAllModulesByUser(idUser);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							}
						}
					} else {

						if (dateMin.equals(dateReference)) {
							dateMin4Search = tracabiliteRepository.getMinDateByUserAndByModule(idUser, nomModule);
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateByUserAndByModule(idUser, nomModule);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUserAndNomModule(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, nomModule, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							}
						} else {
							dateMin4Search = dateMin;
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateByUserAndByModule(idUser, nomModule);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUserAndNomModule(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, nomModule, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUserAndNomModule(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, nomModule, pageable);
								tracabiliteEntities = tracabiliteEntityPage.getContent();
							}
						}
					}
				}
			}
		}
		if (!tracabiliteEntities.isEmpty()) {
			tracabiliteEntities.forEach(tracabiliteEntity -> {
				tracabiliteDTOS.add(modelMapper.map(tracabiliteEntity, TracabiliteDTO.class));
			});
		}
		return tracabiliteDTOS;
	}

	@Override
	public Long getTotalItemTracabilite(Long idUser, String nomModule, LocalDate dateMin, LocalDate dateMax) {
		Long totalItem;
		PageRequest pageable = PageRequest.of(0, 8);
		LocalDate dateReference = LocalDate.of(1970, 1, 1);
		LocalDate dateMin4Search;
		LocalDate dateMax4Search;
		if (idUser == -1) {

			if (nomModule.equals("tousModules")) {

				if (dateMin.equals(dateReference)) {
					dateMin4Search = tracabiliteRepository.getMinDateForAllModulesAndUsers();
					if (dateMax.equals(dateReference)) {
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository.findAll(pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					}
				} else {
					dateMin4Search = dateMin;
					if (dateMax.equals(dateReference)) {
						dateMax4Search = tracabiliteRepository.getMaxDateForAllModulesAndUsers();
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					}
				}
			} else {

				if (dateMin.equals(dateReference)) {
					dateMin4Search = tracabiliteRepository.getMinDateForAllUsersByModule(nomModule);
					if (dateMax.equals(dateReference)) {
						dateMax4Search = tracabiliteRepository.getMaxDateForAllUsersByModule(nomModule);
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					}
				} else {
					dateMin4Search = dateMin;
					if (dateMax.equals(dateReference)) {
						dateMax4Search = tracabiliteRepository.getMaxDateForAllUsersByModule(nomModule);
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetweenAndNomModule(dateMin4Search.minusDays(1),
										dateMax4Search.plusDays(1), nomModule, pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					} else {
						dateMax4Search = dateMax;
						Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
								.findAllByDateOperationBetween(dateMin4Search.minusDays(1), dateMax4Search.plusDays(1),
										pageable);
						totalItem = tracabiliteEntityPage.getTotalElements();
					}
				}
			}

		} else {
			if (userRepository.existsById(idUser)) {
				UserEntity userEntity = userRepository.findById(idUser).get();
				if (tracabiliteRepository.existsByUserId(idUser)) {
					if (nomModule.equals("tousModules")) {

						if (dateMin.equals(dateReference)) {
							dateMin4Search = tracabiliteRepository.getMinDateForAllModulesByUser(idUser);
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateForAllModulesByUser(idUser);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							}
						} else {
							dateMin4Search = dateMin;
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateForAllModulesByUser(idUser);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							}
						}
					} else {

						if (dateMin.equals(dateReference)) {
							dateMin4Search = tracabiliteRepository.getMinDateByUserAndByModule(idUser, nomModule);
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateByUserAndByModule(idUser, nomModule);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUser(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUserAndNomModule(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, nomModule, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							}
						} else {
							dateMin4Search = dateMin;
							if (dateMax.equals(dateReference)) {
								dateMax4Search = tracabiliteRepository.getMaxDateByUserAndByModule(idUser, nomModule);
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUserAndNomModule(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, nomModule, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							} else {
								dateMax4Search = dateMax;
								Page<TracabiliteEntity> tracabiliteEntityPage = tracabiliteRepository
										.findAllByDateOperationBetweenAndUserAndNomModule(dateMin4Search.minusDays(1),
												dateMax4Search.plusDays(1), userEntity, nomModule, pageable);
								totalItem = tracabiliteEntityPage.getTotalElements();
							}
						}
					}
				} else {
					totalItem = (long) 0;
				}
			} else {
				totalItem = (long) 0;
			}
		}
		return totalItem;
	}

	@Override
	public void deleteSelectedTracabilite(Long id) {
		TracabiliteEntity tracabiliteEntity = tracabiliteRepository.findById(id).get();
		UserEntity userEntity = tracabiliteEntity.getUser();
		userEntity.getTracabilites().remove(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.deleteById(id);
	}

	@Override
	public void addNewGroupeUtilisateur(String profil, String designation) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setProfil(profil);
		roleEntity.setDesignation(designation);
		roleRepository.save(roleEntity);
	}

	@Override
	public List<RoleDataTableDTO> getListGroupeUtilisateur(int page, int limit) {
		PageRequest pageable = PageRequest.of(page, limit);
		List<RoleEntity> roleEntity = new ArrayList<>();
		Page<RoleEntity> role = roleRepository.findAll(pageable);
		roleEntity = role.getContent();
		return this.getListGroupeUsers(roleEntity);
	}

	private List<RoleDataTableDTO> getListGroupeUsers(List<RoleEntity> roleEntities) {
		List<RoleDataTableDTO> roleDataTableDTOS = new ArrayList<>();
		if (!roleEntities.isEmpty()) {
			roleEntities.forEach(roleEntity -> {
				RoleDataTableDTO roleDataTableDTO = new RoleDataTableDTO();
				roleDataTableDTO.setId(roleEntity.getId());
				roleDataTableDTO.setAuthorities(roleEntity.getAuthorities());
				roleDataTableDTO.setDesignation(roleEntity.getDesignation());
				roleDataTableDTO.setProfil(roleEntity.getProfil());
				if (!roleEntity.getUsers().isEmpty()) {
					roleDataTableDTO.setUsers(new ArrayList<>());
					roleEntity.getUsers().forEach(userEntity -> {
						UserDTO userDTO = new UserDTO();
						userDTO.setId(userEntity.getId());
						userDTO.setEmail(userEntity.getEmail());
						userDTO.setNom(userEntity.getNom());
						userDTO.setPrenom(userEntity.getPrenom());
						userDTO.setActive(userEntity.isActive());
						userDTO.setDateAjout(userEntity.getDateAjout());
						userDTO.setDateDerniereConnexion(userEntity.getDateDerniereConnexion());
						userDTO.setMatricule(userEntity.getMatricule());
						userDTO.setOrdre(userEntity.getOrdre());
						userDTO.setTypeCompte(userEntity.getTypeCompte());

						// unite de gestion de parc
						if (!userEntity.getUgps().isEmpty()) {
							userDTO.setUgps(new ArrayList<>());
							userEntity.getUgps().forEach(ugpEntity -> {
								UgpDTO ugpDTO = new UgpDTO();
								ugpDTO.setId(ugpEntity.getId());
								userDTO.getUgps().add(ugpDTO);
							});
						}

						// structure ou il aprtient
						if (!userEntity.getStructures().isEmpty()) {
							userDTO.setStructures(new ArrayList<>());
							userEntity.getStructures().forEach(structureEntity -> {
								StructureDTO structureDTO = new StructureDTO();
								structureDTO.setId(structureEntity.getId());
								userDTO.getStructures().add(structureDTO);
							});
						}

						// Le magasin ou le user il appartien
						if (!userEntity.getMagasins().isEmpty()) {
							userDTO.setMagasins(new ArrayList<>());
							userEntity.getMagasins().forEach(magasinEntity -> {
								MagasinDTO magasinDTO = new MagasinDTO();
								magasinDTO.setId(magasinEntity.getId());
								userDTO.getMagasins().add(magasinDTO);
							});
						}

						roleDataTableDTO.getUsers().add(userDTO);
					});
				}

				roleDataTableDTOS.add(roleDataTableDTO);

			});
		}

		return roleDataTableDTOS;
	}

	@Override
	public void deleteSelectedGroupeUtilisateur(Long id) {
		RoleEntity roleEntity = roleRepository.findById(id).get();

		if (!roleEntity.getUsers().isEmpty()) {
			roleEntity.getUsers().forEach(userEntity -> {
				userEntity.getRoles().remove(roleEntity);
				userRepository.save(userEntity);
			});
		}
		roleEntity.getUsers().clear();
		roleRepository.save(roleEntity);

		roleRepository.save(roleEntity);

		roleRepository.delete(roleEntity);
	}

	@Override
	public void addGroupeAuthorities(Long id, List<String> authorities) {
		RoleEntity roleEntity = roleRepository.findById(id).get();
		if (!authorities.isEmpty()) {
			roleEntity.setAuthorities(
					authorities.toString().replaceAll("[\\[\\]]", "").replaceAll(" \"", "").replaceAll("\"", ""));
		} else {
			roleEntity.setAuthorities("");
		}
		roleRepository.save(roleEntity);
	}

	@Override
	public void addGroupeUtilisateurs(Long id, List<String> utilisateurs) {
		RoleEntity roleEntity = roleRepository.findById(id).get();
		if (!roleEntity.getUsers().isEmpty()) {
			roleEntity.getUsers().forEach(userEntity -> {
				userEntity.getRoles().remove(roleEntity);
				userRepository.save(userEntity);
			});
		}
		roleEntity.getUsers().clear();
		roleRepository.save(roleEntity);
		roleEntity.setUsers(new ArrayList<>());
		if (!utilisateurs.isEmpty()) {
			utilisateurs.forEach(s -> {
				UserEntity userEntity = userRepository.findFirstByMatricule(s);
				if (userEntity.getRoles().isEmpty()) {
					userEntity.setRoles(new ArrayList<>());
				}
				userEntity.getRoles().add(roleEntity);
				roleEntity.getUsers().add(userEntity);
				userRepository.save(userEntity);
				roleRepository.save(roleEntity);
			});
		}
	}

	@Override
	public void deleteSelectedGroupeUtilisateur(RoleDTO roleDTO) {
		RoleEntity roleEntity = roleRepository.findById(roleDTO.getId()).get();
		roleEntity.setDesignation(roleDTO.getDesignation());
		roleEntity.setProfil(roleDTO.getProfil());
		roleRepository.save(roleEntity);
	}

	@Override
	public void sendNewMessage(String matricule, String email) {
		MessageEntity messageEntity = new MessageEntity();
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		if (userEntity.getEmail().equals(email)) {
			messageEntity.setResponded(false);
			messageEntity.setDateSent(new Date());
			messageEntity.setUser(userEntity);
			if (userEntity.getMessages().isEmpty()) {
				userEntity.setMessages(new ArrayList<>());
			}
			userEntity.getMessages().add(messageEntity);
			messageRepository.save(messageEntity);
		}
	}

	@Override
	public List<MessageDTO> getListMessage(String structure) {
		ModelMapper modelMapper = new ModelMapper();
		List<MessageDTO> messageDTOS = new ArrayList<>();
		List<MessageEntity> messageEntities = new ArrayList<>();
		if (structure.equals("tousStrucutures")) {
			messageEntities = (List<MessageEntity>) messageRepository.findAll();
		} else {
			StructureEntity structureEntity = structureRepository.findFirstByDesignation(structure);
			List<MessageEntity> messageEntities1 = (List<MessageEntity>) messageRepository.findAll();
			if (!messageEntities1.isEmpty()) {
				List<MessageEntity> finalMessageEntities = messageEntities;
				messageEntities1.forEach(messageEntity -> {
					if (!messageEntity.getUser().getStructures().isEmpty()) {
						if (messageEntity.getUser().getStructures().contains(structureEntity)) {
							finalMessageEntities.add(messageEntity);
						}
					}
				});
				messageEntities = finalMessageEntities;
			}
		}
		if (!messageEntities.isEmpty()) {
			messageEntities.forEach(messageEntity -> {
				messageDTOS.add(modelMapper.map(messageEntity, MessageDTO.class));
			});
		}
		return messageDTOS;
	}

	@Override
	public void deleteSelectedMessage(Long id) {
		MessageEntity messageEntity = messageRepository.findById(id).get();
		UserEntity userEntity = messageEntity.getUser();
		userEntity.getMessages().remove(messageEntity);
		userRepository.save(userEntity);
		messageRepository.deleteById(id);
	}

	@Override
	public int getNombreMessages(String structure) {
		List<StructureEntity> structureEntities = new ArrayList<>();
		List<MessageEntity> messageEntities = new ArrayList<>();
		if (structure.equals("tousStrucutures")) {
			messageEntities = (List<MessageEntity>) messageRepository.findAll();
		} else {
			StructureEntity structureEntity = structureRepository.findFirstByDesignation(structure);
			List<MessageEntity> messageEntities1 = (List<MessageEntity>) messageRepository.findAll();
			if (!messageEntities1.isEmpty()) {
				List<MessageEntity> finalMessageEntities = messageEntities;
				messageEntities1.forEach(messageEntity -> {
					if (!messageEntity.getUser().getStructures().isEmpty()) {
						if (messageEntity.getUser().getStructures().contains(structureEntity)) {
							finalMessageEntities.add(messageEntity);
						}
					}
				});
				messageEntities = finalMessageEntities;
			}
		}
		if (messageEntities.isEmpty()) {
			return 0;
		} else {
			return messageEntities.size();
		}
	}
}
