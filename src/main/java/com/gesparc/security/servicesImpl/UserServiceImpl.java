package com.gesparc.security.servicesImpl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.repositories.referentiel.MagasinRepository;
import com.gesparc.repositories.referentiel.StructureRepository;
import com.gesparc.repositories.referentiel.UgpRepository;
import com.gesparc.security.entity.RoleEntity;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.entity.UserPrincipalEntity;
import com.gesparc.security.enumeration.Role;
import com.gesparc.security.exception.domain.EmailExistException;
import com.gesparc.security.exception.domain.UserNotFoundException;
import com.gesparc.security.exception.domain.UsernameExistException;
import com.gesparc.security.repository.RoleRepository;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.security.services.UserService;
import com.gesparc.sharedDTO.administration.additionnel.NewUserDTO;
import com.gesparc.sharedDTO.referentiel.MagasinDTO;
import com.gesparc.sharedDTO.referentiel.StructureDTO;
import com.gesparc.sharedDTO.referentiel.UgpDTO;
import com.gesparc.sharedDTO.security.RoleDataTableDTO;
import com.gesparc.sharedDTO.security.UserDTO;
import com.gesparc.sharedDTO.security.UserDataTableDTO;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.gesparc.security.constant.FileConstant.USER_FOLDER;
import static com.gesparc.security.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static com.gesparc.security.constant.UserImplConstant.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
@Service
@EnableAutoConfiguration
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService 
{

	@Autowired
	StructureRepository structureRepository;

	@Autowired
	UgpRepository ugpRepository;

	@Autowired
	MagasinRepository magasinRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	private BCryptPasswordEncoder passwordEncoder;
	
	private LoginAttemptService loginAttemptService;

	@Autowired
	AuthenticationManager authenticationManager;
	

@Autowired
private JavaMailSender mailSender;

	private JWTTokenProvider jwtTokenProvider;

	public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService,JWTTokenProvider jwtTokenProvider) 
	{
		this.passwordEncoder = passwordEncoder;
		this.loginAttemptService = loginAttemptService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public HttpHeaders login(UserDTO userDTO) 
	{
		authenticate(userDTO.getMatricule(), userDTO.getMot2passe());
		UserPrincipalEntity userPrincipal = new UserPrincipalEntity(
		userRepository.findFirstByMatricule(userDTO.getMatricule()));
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return jwtHeader;
	}

	@Override
	public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException 
	{
		UserEntity user = userRepository.findFirstByMatricule(matricule);
		if (user == null) 
		{
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_MATRICULE + matricule);
		} 
		else 
		{
			validateLoginAttempt(user);
			user.setDateDerniereConnexion(new Date());
			userRepository.save(user);
			UserPrincipalEntity userPrincipal = new UserPrincipalEntity(user);
			return userPrincipal;
		}
	}

	@Override
	public UserEntity register(String firstName, String lastName, String username, String email)throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException 
	{
		validatenewMatriculeAndEmail(EMPTY, username, email);
		UserEntity user = new UserEntity();
		String password = "";
		user.setNom(firstName);
		user.setPrenom(lastName);
		user.setEmail(email);
		user.setDateDerniereConnexion(new Date());
		user.setMot2passe(encodePassword(password));
		user.setActive(true);
		user.setNotLocked(true);
		userRepository.save(user);
		return user;
	}

	@Override
	public UserEntity addNewUtilisateur(NewUserDTO newUtilisateurDTO)throws UserNotFoundException, UsernameExistException, EmailExistException, IOException
	{
		validatenewMatriculeAndEmail(EMPTY, newUtilisateurDTO.getMatricule(), newUtilisateurDTO.getEmail());
		UserEntity user = new UserEntity();
		user.setNom(newUtilisateurDTO.getNom());
		user.setPrenom(newUtilisateurDTO.getPrenom());
		user.setMatricule(newUtilisateurDTO.getMatricule());
		user.setEmail(newUtilisateurDTO.getEmail());
		user.setTypeCompte(newUtilisateurDTO.getTypeCompte());
		user.setMot2passe(encodePassword(newUtilisateurDTO.getMot2passe()));
		user.setActive(true);
		user.setNotLocked(true);
		user.setOrdre(newUtilisateurDTO.getOrdre());
		user.setDateAjout(new Date());
		user.setDateDerniereConnexion(new Date());
		user.setDateAjout(new Date());
		user.setStructures(new ArrayList<>());
		user.setUgps(new ArrayList<>());
		user.setMagasins(new ArrayList<>());
		user.setTypeCompte(newUtilisateurDTO.getTypeCompte());
		
		userRepository.save(user);	
		if (newUtilisateurDTO.getIdRole() != null) 
		{
			 RoleEntity roleEntity = roleRepository.findById(newUtilisateurDTO.getIdRole()).get();
			 if ((roleEntity.getUsers().isEmpty())) 
			 {
				 roleEntity.setUsers(new ArrayList<>());
			 }
			 roleEntity.getUsers().add(user);
			 user.getRoles().add(roleEntity);
			 roleRepository.save(roleEntity);
		}
		if (newUtilisateurDTO.getIdStructure() != null) 
		{
			 StructureEntity structureEntity = structureRepository.findById(newUtilisateurDTO.getIdStructure()).get();
			 if ((structureEntity.getUsers().isEmpty())) 
			 {
				structureEntity.setUsers(new ArrayList<>());
			 }
			 structureEntity.getUsers().add(user);
			 user.getStructures().add(structureEntity);
			 structureRepository.save(structureEntity);
		}
		if (newUtilisateurDTO.getIdUgp() != null) 
		{
			 UgpEntity ugpEntity = ugpRepository.findById(newUtilisateurDTO.getIdUgp()).get();
			 if (ugpEntity.getUsers().isEmpty()) 
			 {
				 ugpEntity.setUsers(new ArrayList<>());
			 }
			 ugpEntity.getUsers().add(user);
			 user.getUgps().add(ugpEntity);
			 ugpRepository.save(ugpEntity);
		}
		if (newUtilisateurDTO.getIdMagasin() != null) 
		{
			 MagasinEntity magasinEntity = magasinRepository.findById(newUtilisateurDTO.getIdMagasin()).get();
			 if (magasinEntity.getUsers().isEmpty()) 
			 {
				 magasinEntity.setUsers(new ArrayList<>());
			 }
			 magasinEntity.getUsers().add(user);
			 user.getMagasins().add(magasinEntity);
			 magasinRepository.save(magasinEntity);
		 }
		 userRepository.save(user);
		 return user;
	}

	@Override
	public UserDTO modifySelectedUtilisateur(NewUserDTO newUserDTO)throws UserNotFoundException, UsernameExistException, EmailExistException, IOException 
	{
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository.findById(newUserDTO.getId()).get();
		UserEntity currentUser = validatenewMatriculeAndEmail(userEntity.getMatricule(), newUserDTO.getMatricule(),newUserDTO.getEmail());
		currentUser.setNom(newUserDTO.getNom());
		currentUser.setPrenom(newUserDTO.getPrenom());
		currentUser.setMatricule(newUserDTO.getMatricule());
		currentUser.setEmail(newUserDTO.getEmail());
		currentUser.setTypeCompte(newUserDTO.getTypeCompte());
		currentUser.setOrdre(newUserDTO.getOrdre());
		if (newUserDTO.getIdStructure() != null) 
		{
			if (!userEntity.getStructures().isEmpty()) 
			{
				StructureEntity ancienStructureEntity = userEntity.getStructures().get(0);
				StructureEntity newStructureEntity = structureRepository.findById(newUserDTO.getIdStructure()).get();
				ancienStructureEntity.getUsers().remove(userEntity);
				structureRepository.save(ancienStructureEntity);
				if ((newStructureEntity.getUsers().isEmpty())) 
				{
					newStructureEntity.setUsers(new ArrayList<>());
				}
				userEntity.setStructures(new ArrayList<>());
				newStructureEntity.getUsers().add(userEntity);
				userEntity.getStructures().add(newStructureEntity);
				structureRepository.save(newStructureEntity);
			} 
			else 
			{
				StructureEntity newStructureEntity = structureRepository.findById(newUserDTO.getIdStructure()).get();
				if ((newStructureEntity.getUsers().isEmpty()))
				{
					newStructureEntity.setUsers(new ArrayList<>());
				}
				userEntity.setStructures(new ArrayList<>());
				newStructureEntity.getUsers().add(userEntity);
				userEntity.getStructures().add(newStructureEntity);
				structureRepository.save(newStructureEntity);
			}
		}
		else 
		{
			if (!userEntity.getStructures().isEmpty()) 
			{
				StructureEntity structureEntity = userEntity.getStructures().get(0);
				userEntity.getStructures().remove(structureEntity);
				structureEntity.getUsers().remove(userEntity);
				structureRepository.save(structureEntity);
			}
		}

		if (newUserDTO.getIdUgp() != null) 
		{
			if (!userEntity.getUgps().isEmpty()) 
			{
				UgpEntity ancienUgpEntity = userEntity.getUgps().get(0);
				UgpEntity newUgpEntity = ugpRepository.findById(newUserDTO.getIdUgp()).get();
				ancienUgpEntity.getUsers().remove(userEntity);
				ugpRepository.save(ancienUgpEntity);
				if ((newUgpEntity.getUsers().isEmpty())) 
				{
					newUgpEntity.setUsers(new ArrayList<>());
				}
				userEntity.setUgps(new ArrayList<>());
				newUgpEntity.getUsers().add(userEntity);
				userEntity.getUgps().add(newUgpEntity);
				ugpRepository.save(newUgpEntity);
			} 
			else 
			{
				UgpEntity newUgpEntity = ugpRepository.findById(newUserDTO.getIdUgp()).get();
				if ((newUgpEntity.getUsers().isEmpty())) 
				{
					newUgpEntity.setUsers(new ArrayList<>());
				}
				userEntity.setUgps(new ArrayList<>());
				newUgpEntity.getUsers().add(userEntity);
				userEntity.getUgps().add(newUgpEntity);
				ugpRepository.save(newUgpEntity);
			}
		}
		else 
		{
			if (!userEntity.getUgps().isEmpty()) 
			{
				UgpEntity ugpEntity = userEntity.getUgps().get(0);
				userEntity.getUgps().remove(ugpEntity);
				ugpEntity.getUsers().remove(userEntity);
				ugpRepository.save(ugpEntity);
			}
		}
		if (newUserDTO.getIdMagasin() != null) 
		{
			if (!userEntity.getMagasins().isEmpty()) 
			{
				MagasinEntity ancienMagasinEntity = userEntity.getMagasins().get(0);
				MagasinEntity newMagasinEntity = magasinRepository.findById(newUserDTO.getIdMagasin()).get();
				ancienMagasinEntity.getUsers().remove(userEntity);
				magasinRepository.save(ancienMagasinEntity);
				if ((newMagasinEntity.getUsers().isEmpty())) 
				{
					newMagasinEntity.setUsers(new ArrayList<>());
				}
				userEntity.setMagasins(new ArrayList<>());
				newMagasinEntity.getUsers().add(userEntity);
				userEntity.getMagasins().add(newMagasinEntity);
				magasinRepository.save(newMagasinEntity);
			} 
			else 
			{
				MagasinEntity newMagasinEntity = magasinRepository.findById(newUserDTO.getIdMagasin()).get();
				if ((newMagasinEntity.getUsers().isEmpty())) 
				{
					newMagasinEntity.setUsers(new ArrayList<>());
				}
				userEntity.setMagasins(new ArrayList<>());
				newMagasinEntity.getUsers().add(userEntity);
				userEntity.getMagasins().add(newMagasinEntity);
				magasinRepository.save(newMagasinEntity);
			}
		}
		else 
		{
			if (!userEntity.getMagasins().isEmpty()) 
			{
				MagasinEntity magasinEntity = userEntity.getMagasins().get(0);
				userEntity.getMagasins().remove(magasinEntity);
				magasinEntity.getUsers().remove(userEntity);
				magasinRepository.save(magasinEntity);
			}
		}

		userRepository.save(currentUser);
		UserDTO userDTO1 = modelMapper.map(userEntity, UserDTO.class);
		return userDTO1;
	}

	@Override
	public List<UserEntity> getUsers()
	{
		return (List<UserEntity>) userRepository.findAll();
	}

	@Override
	public UserEntity findUserByMatricule(String matricule) 
	{
		return userRepository.findFirstByMatricule(matricule);
	}

	@Override
	public UserEntity findUserByEmail(String email) 
	{
		return userRepository.findFirstByEmail(email);
	}

	@Override
	public void deleteUser(String matricule) throws IOException 
	{
		UserEntity user = userRepository.findFirstByMatricule(matricule);
		Path userFolder = Paths.get(USER_FOLDER + user.getMatricule()).toAbsolutePath().normalize();
		FileUtils.deleteDirectory(new File(userFolder.toString()));
		userRepository.deleteById(user.getId());
	}

	private Role getRoleEnumName(String role)
	{
		return Role.valueOf(role.toUpperCase());
	}

	private String encodePassword(String password) 
	{
		return passwordEncoder.encode(password);
	}

	private String generateUserId() 
	{
		return RandomStringUtils.randomNumeric(10);
	}

	private void validateLoginAttempt(UserEntity user)
	{
		if (user.isNotLocked()) 
		{
			user.setNotLocked(!loginAttemptService.hasExceededMaxAttempts(user.getMatricule()));
		} 
		else
		{
			loginAttemptService.evictUserFromLoginAttemptCache(user.getMatricule());
		}
	}

	private UserEntity validatenewMatriculeAndEmail(String currentMatricule, String newMatricule, String newEmail)throws UserNotFoundException, UsernameExistException, EmailExistException 
	{
		UserEntity userBynewMatricule = findUserByMatricule(newMatricule);
		UserEntity userByNewEmail = findUserByEmail(newEmail);
		if (StringUtils.isNotBlank(currentMatricule)) 
		{
			UserEntity currentUser = findUserByMatricule(currentMatricule);
			if (currentUser == null) 
			{
				throw new UserNotFoundException(NO_USER_FOUND_BY_MATRICULE + currentMatricule);
			}
			if (userBynewMatricule != null && !currentUser.getId().equals(userBynewMatricule.getId()))
			{
				throw new UsernameExistException(MATRICULE_ALREADY_EXISTS);
			}
			if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) 
			{
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return currentUser;
		} 
		else 
		{
			if (userBynewMatricule != null) 
			{
				throw new UsernameExistException(MATRICULE_ALREADY_EXISTS);
			}
			if (userByNewEmail != null) 
			{
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return null;
		}
	}

	@Override
	public List<UserDTO> getListUtilisateur(String structure) 
	{
		List<UserDTO> userDTOS = new ArrayList<>();
		List<UserEntity> userEntities = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		if (structure.equals("tousStructures")) 
		{
			userEntities = (List<UserEntity>) userRepository.findAll();
		}
		else 
		{
			StructureEntity structureEntity = structureRepository.findFirstByDesignation(structure);
			userEntities = userRepository.findAllByStructures(structureEntity);
		}
		if (!userEntities.isEmpty()) 
		{
			userEntities.forEach(userEntity -> {
				userDTOS.add(modelMapper.map(userEntity, UserDTO.class));});
		}
		return userDTOS;
	}

	@Override
	public List<UserDataTableDTO> getListUser(int page, int limit, String structure) 
	{
		PageRequest pageable = PageRequest.of(page, limit);
		List<UserEntity> userEntity = new ArrayList<>();
		Page<UserEntity> user = userRepository.findAll(pageable);
		userEntity = user.getContent();
		return this.loadListUsers(userEntity);
	}

	private List<UserDataTableDTO> loadListUsers(List<UserEntity> userEntities) 
	{
		List<UserDataTableDTO> userDataTableDTOS = new ArrayList<>();
		if (!userEntities.isEmpty()) 
		{
			userEntities.forEach(userEntity -> {
				UserDataTableDTO userDataTableDTO = new UserDataTableDTO();
				userDataTableDTO.setId(userEntity.getId());
				userDataTableDTO.setActive(userEntity.isActive());
				userDataTableDTO.setDateAjout(userEntity.getDateAjout());
				userDataTableDTO.setDateDerniereConnexion(userEntity.getDateDerniereConnexion());
				userDataTableDTO.setEmail(userEntity.getEmail());
				userDataTableDTO.setNom(userEntity.getNom());
				userDataTableDTO.setPrenom(userEntity.getPrenom());
				userDataTableDTO.setTypeCompte(userEntity.getTypeCompte());
				userDataTableDTO.setMatricule(userEntity.getMatricule());
				userDataTableDTO.setNotLocked(userEntity.isNotLocked());
				userDataTableDTO.setOrdre(userEntity.getOrdre());
				if (!userEntity.getStructures().isEmpty())
				{
					  userDataTableDTO.setStructures(new ArrayList<>());
					  userEntity.getStructures().forEach(structureEntity -> 
					  {
					     StructureDTO structureDTO = new StructureDTO();
					     structureDTO.setId(structureEntity.getId());
					     structureDTO.setDesignation(structureEntity.getDesignation());
					     structureDTO.setCode(structureEntity.getCode());
					     structureDTO.setTypeStructure(structureEntity.getTypeStructure());
					     userDataTableDTO.getStructures().add(structureDTO);
					  });
				}
				if (!userEntity.getUgps().isEmpty()) 
				{
					userDataTableDTO.setUgps(new ArrayList<>());
					userEntity.getUgps().forEach(ugpEntity -> 
					{
						 UgpDTO ugpDTO = new UgpDTO();
						 ugpDTO.setId(ugpEntity.getId());
						 userDataTableDTO.getUgps().add(ugpDTO);
					});
				}

				if (!userEntity.getMagasins().isEmpty()) 
				{
					userDataTableDTO.setMagasins(new ArrayList<>());
					userEntity.getMagasins().forEach(magasinEntity -> 
					{
						 MagasinDTO magasinDTO = new MagasinDTO();
						 magasinDTO.setId(magasinEntity.getId());
						 userDataTableDTO.getMagasins().add(magasinDTO);
					});
				}

				userDataTableDTOS.add(userDataTableDTO);

			});
		}

		return userDataTableDTOS;
	}

	@Override
	public void activateDesactivateSelectedUtilisateur(Long id) 
	{
		UserEntity userEntity = userRepository.findById(id).get();
		userEntity.setActive(!userEntity.isActive());
		userRepository.save(userEntity);
	}

	@Override
	public void verrouillageDeverrouillageUtilisateur(Long id) 
	{
		UserEntity userEntity = userRepository.findById(id).get();
		userEntity.setNotLocked(!userEntity.isNotLocked());
		userRepository.save(userEntity);
	}

	@Override
	public void modificationMot2PassUtilisateur(Long id, String ancienMot2pass, String nouveauMot2pass) 
	{
		UserEntity userEntity = userRepository.findById(id).get();
		authenticate(userEntity.getMatricule(), ancienMot2pass);
		userEntity.setMot2passe(this.encodePassword(nouveauMot2pass));
		userRepository.save(userEntity);
	}

	@Override
	public void reinitialisationMot2PassUtilisateur(Long id, String nouveauMot2pass)
	{
		UserEntity userEntity = userRepository.findById(id).get();
		userEntity.setMot2passe(this.encodePassword(nouveauMot2pass));
		userRepository.save(userEntity);
	}

	@Override
	public void deleteSelectedUtilisateur(Long id) 
	{
		UserEntity userEntity = userRepository.findById(id).get();
		if (!userEntity.getRoles().isEmpty()) 
		{
			 userEntity.getRoles().forEach(roleEntity -> 
			 {
				 roleEntity.getUsers().remove(userEntity);
				 roleRepository.save(roleEntity);
			});
		}
		userEntity.getRoles().clear();
		userRepository.save(userEntity);
		if (!userEntity.getStructures().isEmpty()) 
		{
			 userEntity.getStructures().forEach(structureEntity ->
			 {
				  structureEntity.getUsers().remove(userEntity);
				  structureRepository.save(structureEntity);
			 });
		}
		userEntity.getStructures().clear();
		userRepository.save(userEntity);
		if (!userEntity.getUgps().isEmpty()) 
		{
			userEntity.getUgps().forEach(ugpEntity -> 
			{
				ugpEntity.getUsers().remove(userEntity);
				ugpRepository.save(ugpEntity);
			});
		}
		userEntity.getUgps().clear();
		userRepository.save(userEntity);
		userEntity.getStructures().clear();
		userRepository.save(userEntity);
		if (!userEntity.getMagasins().isEmpty()) 
		{
			userEntity.getMagasins().forEach(magasinEntity -> 
			{
				 magasinEntity.getUsers().remove(userEntity);
				 magasinRepository.save(magasinEntity);
			});
		}
		userEntity.getMagasins().clear();
		userRepository.save(userEntity);
		if (!userEntity.getTracabilites().isEmpty()) 
		{
			userEntity.getTracabilites().forEach(tracabiliteEntity -> 
			{
				userEntity.getTracabilites().remove(tracabiliteEntity);
			});
		}
		userRepository.save(userEntity);
		if (!userEntity.getMessages().isEmpty()) 
		{
			userEntity.getMessages().forEach(messageEntity -> 
			{
				userEntity.getMessages().remove(messageEntity);
			});
		}
		userRepository.save(userEntity);
		userRepository.deleteById(id);
	}

	private void authenticate(String matricule, String password) 
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(matricule, password));
	}

	private HttpHeaders getJwtHeader(UserPrincipalEntity user) 
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
		return headers;
	}

}
