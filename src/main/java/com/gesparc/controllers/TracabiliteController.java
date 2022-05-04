package com.gesparc.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesparc.entities.administration.TracabiliteEntity;
import com.gesparc.repositories.administration.TracabiliteRepository;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.security.entity.RoleEntity;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.security.repository.RoleRepository;
import com.gesparc.security.repository.UserRepository;
import com.gesparc.services.Tracabilite;
import com.gesparc.sharedDTO.administration.AlerteDTO;
import com.gesparc.sharedDTO.administration.MessageApplicatifDTO;
import com.gesparc.sharedDTO.administration.additionnel.NewUserDTO;
import com.gesparc.sharedDTO.referentiel.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class TracabiliteController implements Tracabilite 
{
    private TracabiliteRepository tracabiliteRepository;
    
    private UserRepository userRepository;

    private ArticleRepository articleRepository;

    private RoleRepository roleRepository;

	public TracabiliteController(TracabiliteRepository tracabiliteRepository, UserRepository userRepository,
			ArticleRepository articleRepository, RoleRepository roleRepository) {
		this.tracabiliteRepository = tracabiliteRepository;
		this.userRepository = userRepository;
		this.articleRepository = articleRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void addNewFamilleArticleTracabilite(FamilleArticleDTO familleArticleDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une famille d'article");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle famille d'article nommée " + familleArticleDTO.getFamille()
				+ " dont le code d'article est égale à " + familleArticleDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedFamilleArticleTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une famille d'article");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une famille d'article nommée " + designation + " dont le code d'article est égale à "
				+ code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewSousFamilleArticleTracabilite(SousFamilleArticleDTO sousFamilleArticleDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une sous famille d'article");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une sous famille d'article nommée " + sousFamilleArticleDTO.getSousFamille()
				+ " dont le code de sous article est égale à " + sousFamilleArticleDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedSousFamilleArticleTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une sous famille d'article");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une sous famille d'article nommée " + designation
				+ " dont le code de sous famille d'article est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewArticleTracabilite(UpdateArticleDTO updateArticleDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un article");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau article nommé " + updateArticleDTO.getDesignation()
				+ " dont le code d'article est égale à " + updateArticleDTO.getCodeArticle());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedArticleTracabilite(UpdateArticleDTO updateArticleDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un article");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié l'article nommé " + updateArticleDTO.getDesignation()
				+ " dont le code d'article est égale à " + updateArticleDTO.getCodeArticle());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedArticleTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un article");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé l'article nommé " + designation + " dont le code d'article est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewGouvernoratTracabilite(GouvernoratDTO gouvernoratDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un gouvernorat");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un gouvernorat nommé " + gouvernoratDTO.getDesignation()
				+ " dont le code d'article est égale à " + gouvernoratDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedGouvernoratTracabilite(GouvernoratDTO gouvernoratDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un gouvernorat");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié un gouvernorat nommé " + gouvernoratDTO.getDesignation()
				+ " dont le code d'article est égale à " + gouvernoratDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedGouvernoratTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un gouvernorat");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé un gouvernorat nommé " + designation + " dont le code d'article est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewStationPeageTracabilite(String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une station de péage");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle station de péage nommée " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedStationPeageTracabilite(StationPeageDTO stationPeageDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une station de péage");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié une station de péage nommée " + stationPeageDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedStationPeageTracabilite(String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une station de péage");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une station de péage nommée " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewExpertTracabilite(ExpertDTO expertDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un nouvel expert");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouvel expert nommé " + expertDTO.getDesignation() + " dont son code est égale à "
				+ expertDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedExpertTracabilite(ExpertDTO expertDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un expert");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation(
				"L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom() + " a modifié un expert nommé "
						+ expertDTO.getDesignation() + " dont son code est égale à " + expertDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedExpertTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un expert");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé un expert nommé " + designation + " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewFournisseurTracabilite(FournisseurDTO fournisseurDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un fournisseur");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau fournisseur nommé " + fournisseurDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedFournisseurTracabilite(FournisseurDTO fournisseurDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un fournisseur");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié le fournisseur nommé " + fournisseurDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedFournisseurTracabilite(String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un fournisseur");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé le fournisseur nommé " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewLieuParkingTracabilite(LieuParkingDTO lieuParkingDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un lieu de parking");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau lieu de parking d'adresse " + lieuParkingDTO.getAdresse());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedLieuParkingTracabilite(LieuParkingDTO lieuParkingDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un lieu de parking");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié un lieu de parking d'adresse " + lieuParkingDTO.getAdresse());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedLieuParkingTracabilite(String adresse, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un lieu de parking");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé un lieu de parking d'adresse " + adresse);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewFamilleOperationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une famille d'opérations de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une famille d'opérations de réparation nommée "
				+ familleOperationReparationDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedFamilleOperationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une famille d'opérations de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié une famille d'opérations de réparation nommée "
				+ familleOperationReparationDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedFamilleOperationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une famille d'opérations de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une famille d'opérations de réparation nommée "
				+ familleOperationReparationDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewOperationReparationTracabilite(OperationReparationDTO operationReparationDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une opération de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une opération de réparation nommée " + operationReparationDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedOperationReparationTracabilite(OperationReparationDTO operationReparationDTO,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une opération de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié une opération de réparation nommée " + operationReparationDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedOperationReparationTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une opération de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une opération de réparation nommée " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewProgrammeEntretiensPreventifsTracabilite(
			ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un programme d'entretien préventif");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un programme d'entretien préventif nommé "
				+ programmeEntretiensPreventifsDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedProgrammeEntretiensPreventifsTracabilite(
			ProgrammeEntretiensPreventifsDTO programmeEntretiensPreventifsDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un programme d'entretien préventif");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié le programme d'entretien préventif nommé "
				+ programmeEntretiensPreventifsDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedProgrammeEntretiensPreventifsTracabilite(String code, String designation,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un programme d'entretien préventif");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé le programme d'entretien préventif nommé " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewAnneeTracabilite(AnneeDTO anneeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une année");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle année de valeur " + anneeDTO.getAnnee());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedAnneeTracabilite(AnneeDTO anneeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une année");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié l'année de valeur " + anneeDTO.getAnnee());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedAnneeTracabilite(int annee, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une année");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé l'année de valeur " + annee);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewEnergieTracabilite(EnergieDTO energieDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une énergie");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle énergie nommée " + energieDTO.getDescription());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedEnergieTracabilite(EnergieDTO energieDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une énergie");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié l'énergie nommée " + energieDTO.getDescription());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedEnergieTracabilite(String code, String description, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une énergie");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé l'énergie nommée " + description);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewTVATracabilite(TvaDTO tvaDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une TVA");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle TVA de valeur " + tvaDTO.getTva());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedTVATracabilite(TvaDTO tvaDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification de TVA");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié la TVA de valeur " + tvaDTO.getTva());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedTVATracabilite(float tva, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression de TVA");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé la TVA de valeur " + tva);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewUniteTracabilite(UniteDTO uniteDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une unité");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle unité de valeur " + uniteDTO.getUnite());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedUniteTracabilite(UniteDTO uniteDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'unité");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié l'unité de valeur " + uniteDTO.getUnite());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedUniteTracabilite(String unite, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'unité");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé l'unité de valeur " + unite);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewStructureTracabilite(StructureDTO structureDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une structure");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle structure mère nommé " + structureDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewChildStructureTracabilite(StructureDTO structureDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une structure");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle structure nommée " + structureDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedStructureTracabilite(StructureDTO structureDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une structure");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié la structure nommée " + structureDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedStructureTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une structure");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé la structure nommée " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewUGPreTracabilite(UgpDTO ugpDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une unité de gestion de parc");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle unité de gestion de parc nommée " + ugpDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedUGPTracabilite(UgpDTO ugpDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une unité de gestion de parc");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié l'unité de gestion de parc nommé " + ugpDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedUGPTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une unité de gestion de parc");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé l'unité de gestion de parc nommée " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewAtelierTracabilite(AtelierDTO atelierDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un nouveau atelier");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau atelier nommé " + atelierDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedAtelierTracabilite(AtelierDTO atelierDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un atelier");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié l'atelier nommé " + atelierDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedAtelierTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un atelier");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé l'atelier nommé " + designation);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewMagasinTracabilite(MagasinDTO magasinDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un magasin");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau magasin nommé " + magasinDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedMagasinTracabilite(MagasinDTO magasinDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un magasin");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié le magasin nommé " + magasinDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedMagasinTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un magasin");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé le magasin nommé " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewRessourceTracabilite(RessourceDTO ressourceDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une ressource");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle ressource nommée " + ressourceDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedRessourceTracabilite(RessourceDTO ressourceDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une ressource");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié une ressource nommée " + ressourceDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedRessourceTracabilite(String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une ressource");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une ressource nommée " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewSectionTracabilite(SectionDTO sectionDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une section");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle section nommée " + sectionDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedSectionTracabilite(SectionDTO sectionDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une section");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié une section nommée " + sectionDTO.getDesignation());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedSectionTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une section");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une section nommée " + designation);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewPersonnelTracabilite(PersonnelDTO personnelDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un personnel");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un personnel nommée " + personnelDTO.getNom() + " " + personnelDTO.getPrenom());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedPersonnelTracabilite(PersonnelDTO personnelDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un personnel");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié un personnel nommé " + personnelDTO.getNom() + " " + personnelDTO.getPrenom());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedPersonnelTracabilite(String immatriculationUnique, String nom, String prenom,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un personnel");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé un personnel nommé " + nom + " " + prenom);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewEtatStockTracabilite(EtatStockDTO etatStockDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un état de stock");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau état de stock pour l'article " + etatStockDTO.getArticle().getDesignation()
				+ " dont son code est égale à " + etatStockDTO.getArticle().getCodeArticle());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedEtatStockTracabilite(EtatStockDTO etatStockDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un état de stock");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié un état de stock pour l'article " + etatStockDTO.getArticle().getDesignation()
				+ " dont son code est égale à " + etatStockDTO.getArticle().getCodeArticle());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedEtatStockTracabilite(String designation, String code, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un état de stock");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé un état de stock pour l'article " + designation + " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewBeneficiaireEmpruntTracabilite(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un bénéficiaire d'emprunt");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau bénéficiaire d'emprunt de nom " + beneficiaireEmpruntDTO.getNomBeneficiaire()
				+ " dont son code est égale à " + beneficiaireEmpruntDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedBeneficiaireEmpruntTracabilite(BeneficiaireEmpruntDTO beneficiaireEmpruntDTO,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un bénéficiaire d'emprunt");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié le bénéficiaire d'emprunt de nom " + beneficiaireEmpruntDTO.getNomBeneficiaire()
				+ " dont son code est égale à " + beneficiaireEmpruntDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedBeneficiaireEmpruntTracabilite(String nomBeneficiaire, String code, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un bénéficiaire d'emprunt");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé le bénéficiaire d'emprunt de nom " + nomBeneficiaire + " dont son code est égale à "
				+ code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewFamilleOperationReparationTracabilite(FamilleOperationReparationDTO familleOperationReparationDTO,
			String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une famille opérations de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle famille opérations de réparation nommée "
				+ familleOperationReparationDTO.getDesignation() + " dont son code est égale à "
				+ familleOperationReparationDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedFamilleOperationReparationTracabilite(
			FamilleOperationReparationDTO familleOperationReparationDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une famille opérations de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié famille opérations de réparation nommée " + familleOperationReparationDTO.getDesignation()
				+ " dont son code est égale à " + familleOperationReparationDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedFamilleOperationReparationTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une famille opérations de réparation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une famille opérations de réparation nommée " + designation
				+ " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewTypeVehiculeTracabilite(TypeVehiculeDTO typeVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un type véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouveau type véhicule nommé " + typeVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + typeVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedTypeVehiculeTracabilite(TypeVehiculeDTO typeVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un type véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié le type véhicule nommé " + typeVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + typeVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedTypeVehiculeTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un type véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé le type véhicule nommé " + designation + " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewMarqueVehiculeTracabilite(MarqueVehiculeDTO marqueVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une marque véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté la marque véhicule nommée " + marqueVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + marqueVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedMarqueVehiculeTracabilite(MarqueVehiculeDTO marqueVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une marque véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié la marque véhicule nommée " + marqueVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + marqueVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedMarqueVehiculeTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une marque véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé la marque véhicule nommée " + designation + " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewGenreVehiculeTracabilite(GenreVehiculeDTO genreVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'un nouveau genre véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau genre véhicule nommé " + genreVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + genreVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedGenreVehiculeTracabilite(GenreVehiculeDTO genreVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'un genre véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié un genre véhicule nommé " + genreVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + genreVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedGenreVehiculeTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'un genre véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé un genre véhicule nommé " + designation + " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewUsageVehiculeTracabilite(UsageVehiculeDTO usageVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'usage véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau usage véhicule nommé " + usageVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + usageVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedUsageVehiculeTracabilite(UsageVehiculeDTO usageVehiculeDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'usage véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié l'usage véhicule nommé " + usageVehiculeDTO.getDesignation()
				+ " dont son code est égale à " + usageVehiculeDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedUsageVehiculeTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'usage véhicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé l'usage véhicule nommé " + designation + " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewCauseSinistreTracabilite(CauseSinistreDTO causeSinistreDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Ajout d'une cause sinistre");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté une nouvelle cause sinistre nommée " + causeSinistreDTO.getDesignation()
				+ " dont son code est égale à " + causeSinistreDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedCauseSinistreTracabilite(CauseSinistreDTO causeSinistreDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Modification d'une cause sinistre");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié une cause sinistre nommée " + causeSinistreDTO.getDesignation()
				+ " dont son code est égale à " + causeSinistreDTO.getCode());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedCauseSinistreTracabilite(String code, String designation, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Reférentiel");
		tracabiliteEntity.setOperation("Suppression d'une cause sinistre");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé une cause sinistre nommée " + designation + " dont son code est égale à " + code);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewUtilisateurTracabilite(NewUserDTO newUserDTO, String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Ajout d'un compte utilisateur");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau compte utilisateur nommé " + newUserDTO.getNom() + " " + newUserDTO.getPrenom()
				+ " de matricue égale à " + newUserDTO.getMatricule());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void activateDesactivateSelectedUtilisateurTracabilite(Long id, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		UserEntity userEntity2 = userRepository.findById(id).get();
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		if (userEntity2.isActive()) {
			tracabiliteEntity.setOperation("Activation d'un compte utilisateur");
			tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " "
					+ userEntity1.getPrenom() + " a activé le compte d'utilisateur nommé " + userEntity2.getNom() + " "
					+ userEntity2.getPrenom() + " de matricue égale à " + userEntity2.getMatricule());
		} else {
			tracabiliteEntity.setOperation("Désactivation d'un compte utilisateur");
			tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " "
					+ userEntity1.getPrenom() + " a désactivé le compte d'utilisateur nommé " + userEntity2.getNom()
					+ " " + userEntity2.getPrenom() + " de matricue égale à " + userEntity2.getMatricule());
		}
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void verrouillageDeverrouillageUtilisateurTracabilite(Long id, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		UserEntity userEntity2 = userRepository.findById(id).get();
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		if (userEntity2.isNotLocked()) {
			tracabiliteEntity.setOperation("Déverrouillage d'un compte utilisateur");
			tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " "
					+ userEntity1.getPrenom() + " a déverrouillé le compte d'utilisateur nommé " + userEntity2.getNom()
					+ " " + userEntity2.getPrenom() + " de matricue égale à " + userEntity2.getMatricule());
		} else {
			tracabiliteEntity.setOperation("Verrouillage d'un compte utilisateur");
			tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " "
					+ userEntity1.getPrenom() + " a verrouillé le compte d'utilisateur nommé " + userEntity2.getNom()
					+ " " + userEntity2.getPrenom() + " de matricue égale à " + userEntity2.getMatricule());
		}
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modificationMot2PassUtilisateurTracabilite(Long id, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		UserEntity userEntity2 = userRepository.findById(id).get();
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Modification du mot de passe du compte utilisateur");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié le mot de passe du compte utilisateur nommé " + userEntity2.getNom() + " "
				+ userEntity2.getPrenom() + " de matricue égale à " + userEntity2.getMatricule());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void reinitialisationMot2PassUtilisateurTracabilite(Long id, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		UserEntity userEntity2 = userRepository.findById(id).get();
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Réinitialisation du mot de passe du compte utilisateur");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a réinitialisé le mot de passe du compte utilisateur nommé " + userEntity2.getNom() + " "
				+ userEntity2.getPrenom() + " de matricue égale à " + userEntity2.getMatricule());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedUtilisateurTracabilite(Long id, String matricule) {
		UserEntity userEntity = userRepository.findById(id).get();
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Modification du compte utilisateur");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getNom()
				+ " a modifié le compte d'utilisateur nommé " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " de matricue égale à " + userEntity.getMatricule());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedUtilisateurTracabilite(String nom, String prenom, String matriculeUtilisateur,
			String matricule) {
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Suppression du compte utilisateur");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé le compte utilisateur nommé " + nom + " " + prenom + " de matricue égale à "
				+ matriculeUtilisateur);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewGroupeUtilisateurTracabilite(String profil, String designation, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Ajout d'un groupe utilisateur");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouté un nouveau groupe utilisateur nommé " + designation + " et de profil " + profil);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addGroupeAuthoritiesTracabilite(Long id, String matricule) {
		RoleEntity roleEntity = roleRepository.findById(id).get();
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Modification des autorités d'un groupe utilisateur");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifé les autorités du groupe utilisateur nommé " + roleEntity.getDesignation()
				+ " et de profil " + roleEntity.getProfil());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addGroupeUtilisateursTracabilite(Long id, String matricule) {
		RoleEntity roleEntity = roleRepository.findById(id).get();
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Ajout d'un groupe utilisateurs");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouté un nouveau groupe utilisateurs nommé " + roleEntity.getDesignation() + " et de profil "
				+ roleEntity.getProfil());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedGroupeUtilisateurTracabilite(String designation, String profil, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Suppression d'un groupe utilisateurs");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé le groupe utilisateurs nommé " + designation + " et de profil " + profil);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedGroupeUtilisateurTracabilite(Long id, String matricule) {
		RoleEntity roleEntity = roleRepository.findById(id).get();
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Modification d'un groupe utilisateurs");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié le groupe utilisateurs nommé " + roleEntity.getDesignation() + " et de profil "
				+ roleEntity.getProfil());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewMessageApplicatifTracabilite(MessageApplicatifDTO messageApplicatifDTO, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Ajout d'un message applicatif");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouté un nouveau message applicatif de code " + messageApplicatifDTO.getCode());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedMessageApplicatifTracabilite(MessageApplicatifDTO messageApplicatifDTO,
			String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Modification d'un message applicatif");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié un message applicatif de code " + messageApplicatifDTO.getCode());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedMessageApplicatifTracabilite(String code, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Suppression d'un message applicatif");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé un message applicatif de code " + code);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewAlerteTracabilite(AlerteDTO alerteDTO, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Ajout d'une Alerte");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouté une nouvelle alerte de code " + alerteDTO.getCode());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedAlerteTracabilite(AlerteDTO alerteDTO, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Modification d'une Alerte");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié une alerte de code " + alerteDTO.getCode());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedAlerteTracabilite(String code, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Suppression d'une Alerte");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a a une alerte de code " + code);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addParametreApplicationTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Ajout du paramètres d'application");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouté des nouveuax paramètres d'application");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifyParametreApplicationTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administration");
		tracabiliteEntity.setOperation("Modification de paramètres d'application");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié les paramètres d'application");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	// ajouter le 30/06/2021
	@Override
	public void modifySelectedFamilleArticleTracabilite(SousFamilleArticleDTO sousFamilleArticleDTO, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Referentiel");
		tracabiliteEntity.setOperation("Modification d'un sous famille");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié une sous famille de code " + sousFamilleArticleDTO.getCode());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedFamilleTracabilite(FamilleArticleDTO familleArticleDTO, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Referentiel");
		tracabiliteEntity.setOperation("Modification d'une famille");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié une sous famille de code " + familleArticleDTO.getCode());
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}
	@Override
	public void addNewOrdreMissionTracabilite(String numero, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Ordre de Mission");
		tracabiliteEntity.setOperation("Ajout d'un Ordre de mission");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouté l'ordre de mission avec le numero: " + numero);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedOrdreMissionTracabilite(String numero, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Ordre de Mission");
		tracabiliteEntity.setOperation("modification d'un Ordre de mission");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié l'ordre de mission numero: " + numero);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedOrdreMissionTracabilite(String numero, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Ordre de Mission");
		tracabiliteEntity.setOperation("Suppression d'un Ordre de mission");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé l'ordre de mission numero: " + numero);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void confirmSelectedOrdreMissionTracabilite(String numero, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Ordre de Mission");
		tracabiliteEntity.setOperation("La confirmation d'un ordre");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a confirmé l'ordre de mission numero: " + numero);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void addNewDemandeReservationVehiculeTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Nouvelle demande de Reservation ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouter une nouvelle demande de reservation ");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedDemandeReservationVehiculeTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("modification d'une demande de Reservation ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié une demande de reservation ");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedReservationVehiculeTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Suppression d'une demande de Reservation ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé une demande de reservation ");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void addNewLocationVehiculeTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Ajout d'un nouvelle location ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a ajouté une nouvelle demande de location ");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedLocationVehiculeTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Modification d'un nouvelle location ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié une demande de location ");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedLocationVehiculeTracabilite(String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Suppresssion d'un  location ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé une demande de location ");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void addNewSinistreVehiculeTracabilite(String numeroSinistre, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("ajout d'une Sinistre ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié une sinistre numero :" + numeroSinistre);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedSinistreVehiculeTracabilite(String numeroSinistre, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Modification d'une Sinistre ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a modifié une sinistre numero :" + numeroSinistre);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedSinistreVehiculeTracabilite(String numeroSinistre, String matricule) {
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("suppression d'une Sinistre ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé une sinistre numero :" + numeroSinistre);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedEmpruntVehiculeTracabilite(LocalDate dateEmprunt, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Suppression d'un emprunt vehicule ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ " a supprimé un l'emprunt fait le :" + dateEmprunt);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void confirmSelectedEmpruntVehiculeTracabilite(LocalDate dateEDate, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("confirmation d'un emprunt vehicule ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ "  a confirmé un l'emprunt fait l " + dateEDate);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedEmpruntVehiculeTracabilite(LocalDate dateEmprunt, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Modificartion d'un emprunt vehicule ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ "  a modifié un l'emprunt fait l " + dateEmprunt);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewEmpruntVehiculeTracabilite(LocalDate dateEmprunt, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity1 = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Exploitation");
		tracabiliteEntity.setOperation("Modificartion d'un emprunt vehicule ");
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity1.getNom() + " " + userEntity1.getPrenom()
				+ "  a modifié un l'emprunt fait l " + dateEmprunt);
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity1);
		if (userEntity1.getTracabilites().isEmpty()) {
			userEntity1.setTracabilites(new ArrayList<>());
		}
		userEntity1.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity1);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewBonCommandeTracabilite(String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Achat");
		tracabiliteEntity.setOperation("Ajout d'un nouveau bon de commande");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté un nouveau bon de commande ");
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedBonCommandeTracabilite(String matricule) {
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Achat");
		tracabiliteEntity.setOperation("suppression d'un bon de commande");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) un nouveau bon de commande ");
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedVehiculeTracabilite(String numplaque, String marticule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(marticule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Suppression d'une vehicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) une nouvelle vehicule , numero plaque :" + numplaque);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedVehiculeTracabilite(String numplaque, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Modification d'une vehicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié(e) une vehicule , numero plaque :" + numplaque);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void addNewVehiculeTracabilite(String numplaque, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("L'ajout d'une vehicule");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle vehicule , numero plaque :" + numplaque);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedTaxeCirculationTracabilite(String numplaque, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("La modification d'une nouvelle Taxe de circulation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié(e) une Taxe de circulation d'une vehicule dont son numero plaque est:" + numplaque);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void addNewTaxeCirculationTracabilite(String numplaque, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("L'ajout d'une nouvelle Taxe de circulation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle Taxe de circulation d'une vehicule dont son numero plaque est:"
				+ numplaque);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedTaxeCirculationTracabilite(String numplaque, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Suprression d'une Taxe de circulation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) une nouvelle Taxe de circulation d'une vehicule dont son numero plaque est:"
				+ numplaque);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedAssuranceTracabilite(String compgnie, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Suprression d'une Assuance");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) une assuarnce dont son nom est :" + compgnie);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedAssuranceTracabilite(String compagine, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Modification d'une Assuance");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié(e) une assuarnce dont son nom est :" + compagine);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewAssuranceTracabilite(String compagnie, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("L'AJOUT d'une Assuance");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une assuarnce dont son nom est :" + compagnie);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedVisiteTechniqueTracabilite(String plaqueVehicule, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Suppression d'une Visite Technique");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) une visite technique relié a la voiture dont son numero de plaque est:"
				+ plaqueVehicule);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedVisiteTechniqueTracabilite(String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Modification d'une Visite Technique");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié(e) une visite technique relié a la voiture dont son numero de plaque est:");
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void addNewVisiteTechniqueTracabilite(String numeroPlaque, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("L'ajout d'une Visite Technique");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une visite technique relié a la voiture dont son numero de plaque est:" + numeroPlaque);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedReformeTracabilite(String nom, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Suppression d'une reforme");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) une  reforme dont son nom est :" + nom);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedReformeTracabilite(String nom, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Modification d'une reforme");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié(e) une reforme dont son nom est :" + nom);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewReformeTracabilite(String nom, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("L'ajout d'une Nouvelle reforme");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle reforme dont son nom est :" + nom);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedGPSTracabilite(String codeIMEI, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Suppression d'un gps");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) un gps avec le code IMEI est : :" + codeIMEI);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedGPSTracabilite(String codeIMEI, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("Modification d'un nouevau gps");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié(e) un gps avec le code IMEI est : :" + codeIMEI);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void addNewGPSTracabilite(String codeIMEI, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Administratif");
		tracabiliteEntity.setOperation("L'ajout d'un nouevau gps");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) un nouveau gps avec le code IMEI est : :" + codeIMEI);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void deleteSelectedCartePlafondTracabilte(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("La suppression d'une carte plafond");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle carte plafond:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void modifySelectedCartePlafondTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("La modification d'une carte plafond");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle carte plafond:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void createNewCartePlafondTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("L'ajout d'une carte plafond");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle carte plafond:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteDemandAffectationCartePlafondTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("Supression d'une demande d'affectation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) une demande d'affectation de la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void validateDemandeAffectationCartePlafondTracabilite(String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("validation d'une demande d'affectation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a validé(e) une demande d'affectation de la carte:");
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void confirmDemandeAffectationCartePlafondTracabilite(String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("confirmation d'une demande d'affectation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a confirmé(e) une demande d'affectation de la carte:");
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void createNewDemandAffectationCartePlafondTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("creation d'une demande d'affectation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle demande d'affectation de la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedDeclarationPerteCartePlafondTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("modifcation d'une demande de perte d'une carte");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a modifié(e) une demande de perte de la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void createNewDeclarationPerteCartePlafondTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("creation d'une demande de perte de carte plafond");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle demande de perte de la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void createNewDemandeAnnulationCarteCarburantTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("demande d'annulation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a ajouté(e) une nouvelle demande d'annulation de la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void validateSelectedCarteAgilisCashTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("validation de lacarte agilis");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a validé(e) la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void confirmSelectedCarteAgilisCashTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("confirmation recharge de la carte agilis");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a confirmé(e) la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void modifySelectedCarteAgilisCashTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("modification de la carte agilis");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a confirmé(e) la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void createNewCarteAgilisCashTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("l'ajout d'une nouvelle carte agilis");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a confirmé(e) la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteSelectedCarteAgilisCashTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("la suppression d'une carte agilis");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) la carte:" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void deleteDemandeAnnulationCarteAgilisCashTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("la suppression d'une demande d'annulation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a supprimé(e) la demande de la carte numero :" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);
	}

	@Override
	public void createNewDemandeAnnulationCarteAgilisCashTracabilite(String numCarte, String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("la creation d'une demande d'annulation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a cré(e) une demande d'annulation la carte numero :" + numCarte);
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}

	@Override
	public void confirmDemandeAnnulationCarteAgilisCashTracabilite(String matricule) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findFirstByMatricule(matricule);
		TracabiliteEntity tracabiliteEntity = new TracabiliteEntity();
		tracabiliteEntity.setNomModule("Carburant");
		tracabiliteEntity.setOperation("la confirmation d'une demande d'annulation");
		tracabiliteEntity.setDateOperation(LocalDate.now());
		tracabiliteEntity.setDetailsOperation("L'utilisateur " + userEntity.getNom() + " " + userEntity.getPrenom()
				+ " a confirmé(e) l'annulation la carte");
		tracabiliteRepository.save(tracabiliteEntity);
		tracabiliteEntity.setUser(userEntity);
		if (userEntity.getTracabilites().isEmpty()) {
			userEntity.setTracabilites(new ArrayList<>());
		}
		userEntity.getTracabilites().add(tracabiliteEntity);
		userRepository.save(userEntity);
		tracabiliteRepository.save(tracabiliteEntity);

	}
}
