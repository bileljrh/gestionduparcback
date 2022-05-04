package com.gesparc.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.OperationReparationEntity;
import com.gesparc.messages.ApiResponse;
import com.gesparc.repositories.maintenance.BonTravailRepository;
import com.gesparc.repositories.referentiel.ArticleRepository;
import com.gesparc.repositories.referentiel.OperationReparationRepository;
import com.gesparc.requests.Maintenance.BonTravailRequest;

@Service
public class MaintenanceServices 
{

	@Autowired
	private BonTravailRepository bonTravailRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private OperationReparationRepository operationReparationRepository;

	public MaintenanceServices() 
	{
		super();
    }
}
