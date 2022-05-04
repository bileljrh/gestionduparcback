package com.gesparc.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.repositories.achat.BonCommandeRepository;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.repositories.referentiel.FournisseurRepository;
import com.gesparc.requests.Achat.additional.UpdateBonCommandeRequest;
import com.gesparc.responses.Achat.BonCommandeResponse;
import com.gesparc.servicesImpl.AchatImpl;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class AchatController {

	@Autowired
	AchatImpl achat;

	Logger logger = LoggerFactory.getLogger(AchatController.class);

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private FournisseurRepository fournisseurRepository;

	@Autowired
	private BonCommandeRepository bonCommandeRepository;

	@Autowired
	private TracabiliteController tracabiliteController;

	public TracabiliteController getTracabiliteController() {
		return tracabiliteController;
	}

	public void setTracabiliteController(TracabiliteController tracabiliteController) {
		this.tracabiliteController = tracabiliteController;
	}
	
	private JsonNode generateNumeroBonCommande(Long totalNumber) throws JsonMappingException, JsonProcessingException {
		LocalDate localDate = LocalDate.now();
		Long x = totalNumber;
		return new ObjectMapper().readTree(x.toString());
	}

	@GetMapping(path = "/numpiece")
	public String genCode() throws JsonMappingException, JsonProcessingException {
		Long boncom = bonCommandeRepository.getTotalNumberBonCammande();
		return (boncom.toString());
	}

	@PostMapping(path = "/bon_commande")
	public ResponseEntity<HttpStatus> addNewBonCommande(@RequestBody UpdateBonCommandeRequest updateBonCommandeRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UpdateBonCommandeDTO updateBonCommandeDTO = modelMapper.map(updateBonCommandeRequest,UpdateBonCommandeDTO.class);
		achat.addNewBonCommande(updateBonCommandeDTO);
		tracabiliteController.addNewBonCommandeTracabilite(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/bon_commande")
	public ResponseEntity<HttpStatus> modifySelectedBonCommande(@RequestBody UpdateBonCommandeRequest updateBonCommandeRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UpdateBonCommandeDTO updateBonCommandeDTO = modelMapper.map(updateBonCommandeRequest,UpdateBonCommandeDTO.class);
		achat.modifySelectedBonCommande(updateBonCommandeDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/bon_commande/{id}")
	public ResponseEntity<HttpStatus> deleteSelectedBonCommande(@PathVariable Long id) {
		achat.deleteSelectedBonCommande(id);
		tracabiliteController.deleteSelectedBonCommandeTracabilite(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_bon_commande")
	public ResponseEntity<Long> getTotalItemBonCommandeList() {
		Long totalItem = achat.getTotalItemBonCommandeList();
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}

	@GetMapping(path = "/total_item_marche")
	public ResponseEntity<Long> getTotalItemMarcheList() {
		Long totalItem = achat.getTotalItemMarcheList();
		return new ResponseEntity<>(totalItem, HttpStatus.OK);
	}
	@GetMapping(path = "/pagination_bon_commande")
	public ResponseEntity<List<BonCommandeResponse>> getPaginationBonCommandeList3(
			@RequestParam(value = "status") String status, @RequestParam(value = "parc") String parc,
			@RequestParam(value = "fournisseur") String fournisseur, @RequestParam(value = "article") String article,
			@RequestParam(name = "page") String page, @RequestParam(name = "limit") String limit) {
		ModelMapper modelMapper = new ModelMapper();
		List<BonCommandeResponse> bonCommandeResponses = new ArrayList<>();
		List<BonCommandeDTO> bonCommandeDTOS = achat.getPaginationBonCommandeList2(status, parc, fournisseur, article,Integer.parseInt(page), Integer.parseInt(limit));
		if (!bonCommandeDTOS.isEmpty()) {
			bonCommandeDTOS.forEach(bonCommandeDTO -> {
				bonCommandeResponses.add(modelMapper.map(bonCommandeDTO, BonCommandeResponse.class));
			});
		}
		return new ResponseEntity<>(bonCommandeResponses, HttpStatus.OK);
	}

	@GetMapping(path = "/list_Parc")
	public Iterable<BonCommandeEntity> ListParc() {
		return bonCommandeRepository.findAll();
	}

	@GetMapping(path = "/list_Status")
	public Iterable<BonCommandeEntity> ListStatus() {
		return bonCommandeRepository.findAll();
	}

	@GetMapping(path = "/list_fournisseur")
	public Iterable<FournisseurEntity> listFournisseur() {
		return fournisseurRepository.findAll();
	}

	@GetMapping(path = "/list_Article")
	public Iterable<ArticleEntity> listContenantArticle() {
		return articleRepository.findAll();
	}

	@GetMapping(path = "/list_Parcs")
	public Iterable<UgpEntity> getAllBonTravail(){
		return achat.getAllParcs();
	}

}
