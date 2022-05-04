package com.gesparc.servicesImpl;

import com.gesparc.entities.administratif.*;
import com.gesparc.entities.carburant.AgilisFileData;
import com.gesparc.entities.carburant.PricesEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.referentiel.*;
import com.gesparc.repositories.*;
import com.gesparc.repositories.administratif.GpsRepository;
import com.gesparc.repositories.carburants.AgilisFileDataRepository;
import com.gesparc.repositories.carburants.PricesRepository;
import com.gesparc.repositories.referentiel.*;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.services.Administratif;
import com.gesparc.sharedDTO.administratif.Additionnel.*;
import com.gesparc.sharedDTO.carburant.additionnel.AgilisFileDataTableDTO;
import com.gesparc.sharedDTO.carburant.additionnel.AgilisPriceDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;

import com.gesparc.sharedDTO.*;

import com.gesparc.sharedDTO.administratif.BeneficiaireDTO;
import com.gesparc.sharedDTO.administratif.ListVisiteTechniqueDTO;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gesparc.entities.administratif.*;
import com.gesparc.entities.referentiel.*;
import com.gesparc.repositories.*;
import com.gesparc.repositories.administratif.GpsRepository;
import com.gesparc.repositories.referentiel.*;
import com.gesparc.security.entity.UserEntity;
import com.gesparc.services.Administratif;
import com.gesparc.sharedDTO.administratif.BeneficiaireDTO;
import com.gesparc.sharedDTO.administratif.Additionnel.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@EnableAutoConfiguration
@Service
public class AdministratifImpl implements Administratif {

    Logger logger = LoggerFactory.getLogger(AdministratifImpl.class);

    @Autowired
    VehiculeRepository vehiculeRepository;

    @Autowired
    BeneficiaireRepository beneficiaireRepository;

    @Autowired
    StructureRepository structureRepository;

    @Autowired
    EnergieRepository energieRepository;

    @Autowired
    LieuParkingRepository lieuParkingRepository;

    @Autowired
    GouvernoratRepository gouvernoratRepository;

    @Autowired
    FournisseurRepository fournisseurRepository;

    @Autowired
    TaxeCirculationRepository taxeCirculationRepository;

    @Autowired
    AssuranceRepository assuranceRepository;

    @Autowired
    VisiteTechniqueRepository visiteTechniqueRepository;

    @Autowired
    ReformeRepository reformeRepository;

    @Autowired
    GpsRepository gpsRepository;

    @Autowired
    DocumentRepository documentRepository;
    
    @Autowired
    private AgilisFileDataRepository agilisFileDataRepository;
    
    
    @Autowired
    private PricesRepository pricesRepository;

    @Override
    public Long addNewVehicule(NewVehiculeDTO newVehiculeDTO) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        VehiculeEntity vehiculeEntity = new VehiculeEntity();
        vehiculeEntity.setNumeroPlaque(newVehiculeDTO.getNumeroPlaque());
        vehiculeEntity.setGenre(newVehiculeDTO.getGenre());
        vehiculeEntity.setType(newVehiculeDTO.getType());
        vehiculeEntity.setMarque(newVehiculeDTO.getMarque());
        vehiculeEntity.setNatureAffectation(newVehiculeDTO.getNatureAffectation());
        if (newVehiculeDTO.getDatePremiereMiseEnCirculation() != null) 
        {
            vehiculeEntity.setDatePremiereMiseEnCirculation(LocalDate.parse(newVehiculeDTO.getDatePremiereMiseEnCirculation(), formatter));
        }
        vehiculeEntity.setNumeroChassis(newVehiculeDTO.getNumeroChassis());
        vehiculeEntity.setTypeTaxe(newVehiculeDTO.getTypeTaxe());
        if (newVehiculeDTO.getIdStructure() != null) 
        {
            StructureEntity structureEntity = structureRepository.findById(newVehiculeDTO.getIdStructure()).get();
            if (structureEntity.getVehicules().isEmpty()) 
            {
                structureEntity.setVehicules(new ArrayList<>());
            }
            structureEntity.getVehicules().add(vehiculeEntity);
            vehiculeEntity.setStructure(structureEntity);
        }
        if (newVehiculeDTO.getIdGouvernorat() != null) 
        {
            GouvernoratEntity gouvernoratEntity = gouvernoratRepository.findById(newVehiculeDTO.getIdGouvernorat()).get();
            if (gouvernoratEntity.getVehicules().isEmpty()) 
            {
                gouvernoratEntity.setVehicules(new ArrayList<>());
            }
            gouvernoratEntity.getVehicules().add(vehiculeEntity);
            vehiculeEntity.setGouvernorat(gouvernoratEntity);
        }
        if (newVehiculeDTO.getIdEnergie() != null) 
        {
            EnergieEntity energieEntity = energieRepository.findById(newVehiculeDTO.getIdEnergie()).get();
            if (energieEntity.getVehicules().isEmpty()) 
            {
                energieEntity.setVehicules(new ArrayList<>());
            }
            energieEntity.getVehicules().add(vehiculeEntity);
            vehiculeEntity.setEnergie(energieEntity);
        }
        vehiculeEntity.setEtat(newVehiculeDTO.getEtat());
        vehiculeEntity.setReferenceAffectation(newVehiculeDTO.getReferenceAffectation());
        vehiculeEntity.setNumeroImmatriculation(newVehiculeDTO.getNumeroImmatriculation());
        vehiculeEntity.setNumeroProprietaireEtat(newVehiculeDTO.getNumeroProprietaireEtat());
        vehiculeEntity.setNumeroCarteUtilisation(newVehiculeDTO.getNumeroCarteUtilisation());
        vehiculeEntity.setIndexKm(newVehiculeDTO.getIndexKm());
        if (newVehiculeDTO.getDateAffectation() != null) 
        {
            vehiculeEntity.setDateAffectation(LocalDate.parse(newVehiculeDTO.getDateAffectation(), formatter));
        }
        vehiculeEntity.setExploitationUsage(newVehiculeDTO.getExploitationUsage());
        vehiculeEntity.setTypeAssurance(newVehiculeDTO.getTypeAssurance());
        vehiculeEntity.setNomAssurance(newVehiculeDTO.getNomAssurance());
        vehiculeEntity.setReferenceBC(newVehiculeDTO.getReferenceBC());
        vehiculeEntity.setPrixAchat(newVehiculeDTO.getPrixAchat());
        vehiculeEntity.setSituationDouaniere(newVehiculeDTO.getSituationDouaniere());
        vehiculeEntity.setNumeroImmatriculationTemporaire(newVehiculeDTO.getNumeroImmatriculationTemporaire());
        vehiculeEntity.setCompagnieLeasing(newVehiculeDTO.getCompagnieLeasing());
        if (newVehiculeDTO.getDateEcheance() != null) 
        {
            vehiculeEntity.setDateEcheance(LocalDate.parse(newVehiculeDTO.getDateEcheance(), formatter));
        }
        if (newVehiculeDTO.getDateReception() != null) 
        {
            vehiculeEntity.setDateReception(LocalDate.parse(newVehiculeDTO.getDateReception(), formatter));
        }
        vehiculeEntity.setNumeroCarteGrise(newVehiculeDTO.getNumeroCarteGrise());
        vehiculeEntity.setTypeCarteGrise(newVehiculeDTO.getTypeCarteGrise());
        vehiculeEntity.setReferenceType(newVehiculeDTO.getReferenceType());
        vehiculeEntity.setNombreDePlaces(newVehiculeDTO.getNombreDePlaces());
        vehiculeEntity.setCarosserie(newVehiculeDTO.getCarosserie());
        vehiculeEntity.setPoidsTotalACharge(newVehiculeDTO.getPoidsTotalACharge());
        vehiculeEntity.setConsommationMoyenne(newVehiculeDTO.getConsommationMoyenne());
        vehiculeEntity.setChargeUtile(newVehiculeDTO.getChargeUtile());
        vehiculeEntity.setPoidsTotalSansCharge(newVehiculeDTO.getPoidsTotalSansCharge());
        vehiculeEntity.setDimensionsPneuAvant(newVehiculeDTO.getDimensionsPneuAvant());
        vehiculeEntity.setDimensionsPneuArriere(newVehiculeDTO.getDimensionsPneuArriere());
        vehiculeEntity.setPoidsAVide(newVehiculeDTO.getPoidsAVide());
        vehiculeEntity.setPuissanceFixale(newVehiculeDTO.getPuissanceFixale());
        vehiculeEntity.setPuissanceMoteur(newVehiculeDTO.getPuissanceMoteur());
        vehiculeEntity.setNombreEssieux(newVehiculeDTO.getNombreEssieux());
        vehiculeEntity.setVolumeCylindre(newVehiculeDTO.getVolumeCylindre());
        if (newVehiculeDTO.getIdBeneficiaires() != null) 
        {
            BeneficiaireEntity beneficiaireEntity = beneficiaireRepository.findById(newVehiculeDTO.getIdBeneficiaires()).get();
            if (beneficiaireEntity.getVehicules().isEmpty()) 
            {
                beneficiaireEntity.setVehicules(new ArrayList<>());
            }
            beneficiaireEntity.getVehicules().add(vehiculeEntity);
            vehiculeEntity.setBeneficiaire(beneficiaireEntity);
        }
        if (newVehiculeDTO.getIdLieuParking() != null) 
        {
            LieuParkingEntity lieuParkingEntity = lieuParkingRepository.findById(newVehiculeDTO.getIdLieuParking()).get();
            if (lieuParkingEntity.getVehicules().isEmpty()) 
            {
                lieuParkingEntity.setVehicules(new ArrayList<>());
            }
            lieuParkingEntity.getVehicules().add(vehiculeEntity);
            vehiculeEntity.setLieuParking(lieuParkingEntity);
        }
        if (newVehiculeDTO.getIdFournisseur() != null) 
        {
            FournisseurEntity fournisseurEntity = fournisseurRepository.findById(newVehiculeDTO.getIdFournisseur()).get();
            if (fournisseurEntity.getVehicules().isEmpty()) 
            {
                fournisseurEntity.setVehicules(new ArrayList<>());
            }
            fournisseurEntity.getVehicules().add(vehiculeEntity);
            vehiculeEntity.setFournisseur(fournisseurEntity);
        }
        vehiculeRepository.save(vehiculeEntity);
        return vehiculeEntity.getId();
    }

    @Override
    public Long modifySelectedVehicule(NewVehiculeDTO modifiedVehiculeDTO) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(modifiedVehiculeDTO.getId()).get();
        vehiculeEntity.setNumeroPlaque(modifiedVehiculeDTO.getNumeroPlaque());
        vehiculeEntity.setGenre(modifiedVehiculeDTO.getGenre());
        vehiculeEntity.setType(modifiedVehiculeDTO.getType());
        vehiculeEntity.setMarque(modifiedVehiculeDTO.getMarque());
        vehiculeEntity.setNatureAffectation(modifiedVehiculeDTO.getNatureAffectation());
        if (modifiedVehiculeDTO.getDatePremiereMiseEnCirculation() != null) 
        {
            vehiculeEntity.setDatePremiereMiseEnCirculation(LocalDate.parse(modifiedVehiculeDTO.getDatePremiereMiseEnCirculation(), formatter));
        }
        vehiculeEntity.setNumeroChassis(modifiedVehiculeDTO.getNumeroChassis());
        vehiculeEntity.setTypeTaxe(modifiedVehiculeDTO.getTypeTaxe());
        if (modifiedVehiculeDTO.getIdStructure() != null)
        {
            if (modifiedVehiculeDTO.getIdStructure() != vehiculeEntity.getStructure().getId()) 
            {
                StructureEntity ancienStructureEntity = vehiculeEntity.getStructure();
                ancienStructureEntity.getVehicules().remove(vehiculeEntity);
                structureRepository.save(ancienStructureEntity);
                StructureEntity newStructureEntity = structureRepository.findById(modifiedVehiculeDTO.getIdStructure()).get();
                if (newStructureEntity.getVehicules().isEmpty())
                {
                    newStructureEntity.setVehicules(new ArrayList<>());
                }
                newStructureEntity.getVehicules().add(vehiculeEntity);
                vehiculeEntity.setStructure(newStructureEntity);
                structureRepository.save(newStructureEntity);
            }
        }
        if (modifiedVehiculeDTO.getIdGouvernorat() != null) 
        {
            if (modifiedVehiculeDTO.getIdGouvernorat() != vehiculeEntity.getGouvernorat().getId()) 
            {
                GouvernoratEntity ancienGouvernoratEntity = vehiculeEntity.getGouvernorat();
                ancienGouvernoratEntity.getVehicules().remove(vehiculeEntity);
                gouvernoratRepository.save(ancienGouvernoratEntity);
                GouvernoratEntity newGouvernoratEntity = gouvernoratRepository.findById(modifiedVehiculeDTO.getIdGouvernorat()).get();
                if (newGouvernoratEntity.getVehicules().isEmpty()) 
                {
                    newGouvernoratEntity.setVehicules(new ArrayList<>());
                }
                newGouvernoratEntity.getVehicules().add(vehiculeEntity);
                vehiculeEntity.setGouvernorat(newGouvernoratEntity);
                gouvernoratRepository.save(newGouvernoratEntity);
            }
        }
        if (modifiedVehiculeDTO.getIdEnergie() != null) 
        {
            if (modifiedVehiculeDTO.getIdEnergie() != vehiculeEntity.getEnergie().getId()) 
            {
                EnergieEntity ancienEnergieEntity = vehiculeEntity.getEnergie();
                ancienEnergieEntity.getVehicules().remove(vehiculeEntity);
                energieRepository.save(ancienEnergieEntity);
                EnergieEntity newEnergieEntity = energieRepository.findById(modifiedVehiculeDTO.getIdEnergie()).get();
                if (newEnergieEntity.getVehicules().isEmpty()) 
                {
                    newEnergieEntity.setVehicules(new ArrayList<>());
                }
                newEnergieEntity.getVehicules().add(vehiculeEntity);
                vehiculeEntity.setEnergie(newEnergieEntity);
                energieRepository.save(newEnergieEntity);
            }
        }
        vehiculeEntity.setEtat(modifiedVehiculeDTO.getEtat());
        vehiculeEntity.setReferenceAffectation(modifiedVehiculeDTO.getReferenceAffectation());
        vehiculeEntity.setNumeroImmatriculation(modifiedVehiculeDTO.getNumeroImmatriculation());
        vehiculeEntity.setNumeroProprietaireEtat(modifiedVehiculeDTO.getNumeroProprietaireEtat());
        vehiculeEntity.setNumeroCarteUtilisation(modifiedVehiculeDTO.getNumeroCarteUtilisation());
        vehiculeEntity.setIndexKm(modifiedVehiculeDTO.getIndexKm());
        if (modifiedVehiculeDTO.getDateAffectation() != null) 
        {
            vehiculeEntity.setDateAffectation(LocalDate.parse(modifiedVehiculeDTO.getDateAffectation(), formatter));
        }
        vehiculeEntity.setExploitationUsage(modifiedVehiculeDTO.getExploitationUsage());
        vehiculeEntity.setTypeAssurance(modifiedVehiculeDTO.getTypeAssurance());
        vehiculeEntity.setNomAssurance(modifiedVehiculeDTO.getNomAssurance());
        vehiculeEntity.setReferenceBC(modifiedVehiculeDTO.getReferenceBC());
        vehiculeEntity.setPrixAchat(modifiedVehiculeDTO.getPrixAchat());
        vehiculeEntity.setSituationDouaniere(modifiedVehiculeDTO.getSituationDouaniere());
        vehiculeEntity.setNumeroImmatriculationTemporaire(modifiedVehiculeDTO.getNumeroImmatriculationTemporaire());
        vehiculeEntity.setCompagnieLeasing(modifiedVehiculeDTO.getCompagnieLeasing());
        if (modifiedVehiculeDTO.getDateEcheance() != null) {
            vehiculeEntity.setDateEcheance(LocalDate.parse(modifiedVehiculeDTO.getDateEcheance(), formatter));
        }
        if (modifiedVehiculeDTO.getDateReception() != null)
        {
            vehiculeEntity.setDateReception(LocalDate.parse(modifiedVehiculeDTO.getDateReception(), formatter));
        }
        vehiculeEntity.setNumeroCarteGrise(modifiedVehiculeDTO.getNumeroCarteGrise());
        vehiculeEntity.setTypeCarteGrise(modifiedVehiculeDTO.getTypeCarteGrise());
        vehiculeEntity.setReferenceType(modifiedVehiculeDTO.getReferenceType());
        vehiculeEntity.setNombreDePlaces(modifiedVehiculeDTO.getNombreDePlaces());
        vehiculeEntity.setCarosserie(modifiedVehiculeDTO.getCarosserie());
        vehiculeEntity.setPoidsTotalACharge(modifiedVehiculeDTO.getPoidsTotalACharge());
        vehiculeEntity.setConsommationMoyenne(modifiedVehiculeDTO.getConsommationMoyenne());
        vehiculeEntity.setChargeUtile(modifiedVehiculeDTO.getChargeUtile());
        vehiculeEntity.setPoidsTotalSansCharge(modifiedVehiculeDTO.getPoidsTotalSansCharge());
        vehiculeEntity.setDimensionsPneuAvant(modifiedVehiculeDTO.getDimensionsPneuAvant());
        vehiculeEntity.setDimensionsPneuArriere(modifiedVehiculeDTO.getDimensionsPneuArriere());
        vehiculeEntity.setPoidsAVide(modifiedVehiculeDTO.getPoidsAVide());
        vehiculeEntity.setPuissanceFixale(modifiedVehiculeDTO.getPuissanceFixale());
        vehiculeEntity.setPuissanceMoteur(modifiedVehiculeDTO.getPuissanceMoteur());
        vehiculeEntity.setNombreEssieux(modifiedVehiculeDTO.getNombreEssieux());
        vehiculeEntity.setVolumeCylindre(modifiedVehiculeDTO.getVolumeCylindre());
        if (modifiedVehiculeDTO.getIdBeneficiaires() != null) 
        {
            if (modifiedVehiculeDTO.getIdBeneficiaires() != vehiculeEntity.getBeneficiaire().getId())
            {
                BeneficiaireEntity ancienBeneficiaireEntity = vehiculeEntity.getBeneficiaire();
                ancienBeneficiaireEntity.getVehicules().remove(vehiculeEntity);
                beneficiaireRepository.save(ancienBeneficiaireEntity);
                BeneficiaireEntity newBeneficiaireEntity = beneficiaireRepository.findById(modifiedVehiculeDTO.getIdBeneficiaires()).get();
                if (newBeneficiaireEntity.getVehicules().isEmpty()) 
                {
                    newBeneficiaireEntity.setVehicules(new ArrayList<>());
                }
                newBeneficiaireEntity.getVehicules().add(vehiculeEntity);
                vehiculeEntity.setBeneficiaire(newBeneficiaireEntity);
                beneficiaireRepository.save(newBeneficiaireEntity);
            }
        }
        if (modifiedVehiculeDTO.getIdLieuParking() != null) 
        {
            if (modifiedVehiculeDTO.getIdLieuParking() != vehiculeEntity.getLieuParking().getId()) 
            {
                LieuParkingEntity ancienLieuParkingEntity = vehiculeEntity.getLieuParking();
                ancienLieuParkingEntity.getVehicules().remove(vehiculeEntity);
                lieuParkingRepository.save(ancienLieuParkingEntity);
                LieuParkingEntity newLieuParkingEntity = lieuParkingRepository.findById(modifiedVehiculeDTO.getIdLieuParking()).get();
                if (newLieuParkingEntity.getVehicules().isEmpty()) 
                {
                    newLieuParkingEntity.setVehicules(new ArrayList<>());
                }
                newLieuParkingEntity.getVehicules().add(vehiculeEntity);
                vehiculeEntity.setLieuParking(newLieuParkingEntity);
                lieuParkingRepository.save(newLieuParkingEntity);
            }
        }
        if (modifiedVehiculeDTO.getIdFournisseur() != null) 
        {
            if (modifiedVehiculeDTO.getIdFournisseur() != vehiculeEntity.getFournisseur().getId()) 
            {
                FournisseurEntity ancienFournisseurEntity = vehiculeEntity.getFournisseur();
                ancienFournisseurEntity.getVehicules().remove(vehiculeEntity);
                fournisseurRepository.save(ancienFournisseurEntity);
                FournisseurEntity newFournisseurEntity = fournisseurRepository.findById(modifiedVehiculeDTO.getIdFournisseur()).get();
                if (newFournisseurEntity.getVehicules().isEmpty()) 
                {
                    newFournisseurEntity.setVehicules(new ArrayList<>());
                }
                newFournisseurEntity.getVehicules().add(vehiculeEntity);
                vehiculeEntity.setFournisseur(newFournisseurEntity);
                fournisseurRepository.save(newFournisseurEntity);
            }
        }
        vehiculeRepository.save(vehiculeEntity);
        return vehiculeEntity.getId();
    }

    @Override
    public void deleteSelectedVehicule(Long id)
    {
        List<Long> idDocuments = new ArrayList<>();
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(id).get();
        BeneficiaireEntity beneficiaireEntity = vehiculeEntity.getBeneficiaire();
        if (beneficiaireEntity != null) 
        {
            beneficiaireEntity.getVehicules().remove(vehiculeEntity);
            beneficiaireRepository.save(beneficiaireEntity);
        }
        GouvernoratEntity gouvernoratEntity = vehiculeEntity.getGouvernorat();
        if (beneficiaireEntity != null) 
        {
            gouvernoratEntity.getVehicules().remove(vehiculeEntity);
            gouvernoratRepository.save(gouvernoratEntity);
        }
        LieuParkingEntity lieuParkingEntity = vehiculeEntity.getLieuParking();
        if (lieuParkingEntity != null)
        {
            lieuParkingEntity.getVehicules().remove(vehiculeEntity);
            lieuParkingRepository.save(lieuParkingEntity);
        }
        StructureEntity structureEntity = vehiculeEntity.getStructure();
        if (structureEntity != null) 
        {
            structureEntity.getVehicules().remove(vehiculeEntity);
            structureRepository.save(structureEntity);
        }
        FournisseurEntity fournisseurEntity = vehiculeEntity.getFournisseur();
        if (fournisseurEntity != null)
        {
            fournisseurEntity.getVehicules().remove(vehiculeEntity);
            fournisseurRepository.save(fournisseurEntity);
        }
        EnergieEntity energieEntity = vehiculeEntity.getEnergie();
        if (energieEntity != null) 
        {
            energieEntity.getVehicules().remove(vehiculeEntity);
            energieRepository.save(energieEntity);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public OneVehiculeDTO getSelectedVehicule(Long id) 
    {
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(id).get();
        return this.loadOneVehiculeData(vehiculeEntity);
    }

    @Override
    public List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForTaxeCirculation(UserEntity userEntity)
    {
        List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            vehiculeEntities = vehiculeRepository.findAllByTaxeCirculationNull();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            vehiculeEntities = vehiculeRepository.findAllByStructureAndTaxeCirculationIsNull(structureEntity);
        }
        return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
    }

    @Override
    public List<BeneficiaireEntity> getListBeneficiaire() 
    {
        List<BeneficiaireDTO> beneficiaireDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        List<BeneficiaireEntity> beneficiaireEntities = (List<BeneficiaireEntity>) beneficiaireRepository.findAll();
        return beneficiaireEntities;
    }

    @Override
    public List<VehiculeTableDataDTO> getPaginationListVehiculeTableData(int page, int limit, UserEntity userEntity) 
    {
        List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
        List<VehiculeTableDataDTO> VehiculeTableDataDTO = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, limit);
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<VehiculeEntity> vehiculeEntityPage = vehiculeRepository.findAll(pageable);
            vehiculeEntities = vehiculeEntityPage.getContent();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<VehiculeEntity> vehiculeEntityPage = vehiculeRepository.findAllByStructure(structureEntity, pageable);
            vehiculeEntities = vehiculeEntityPage.getContent();
        }
        if (!vehiculeEntities.isEmpty()) 
        {
            vehiculeEntities.forEach(vehiculeEntity ->
            {
                VehiculeTableDataDTO.add(this.loadVehiculeTableData(vehiculeEntity));
            });
        }
        return VehiculeTableDataDTO;
    }

    @Override
    public Long getTotalItemVehiculeTableDataList(UserEntity userEntity) 
    {
        Long totalItem;
        PageRequest pageable = PageRequest.of(0, 8);
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<VehiculeEntity> vehiculeEntityPage = vehiculeRepository.findAll(pageable);
            totalItem = vehiculeEntityPage.getTotalElements();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<VehiculeEntity> vehiculeEntityPage = vehiculeRepository.findAllByStructure(structureEntity, pageable);
            totalItem = vehiculeEntityPage.getTotalElements();
        }
        return totalItem;
    }

    @Override
    public void addNewTaxeCirculation(TaxeCirculationEntity taxeCirculationEntity, Long id) 
    {
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(id).get();
        taxeCirculationEntity.setVehicule(vehiculeEntity);
        vehiculeEntity.setTaxeCirculation(taxeCirculationEntity);
        taxeCirculationRepository.save(taxeCirculationEntity);
    }

    @Override
    public void modifySelectedTaxeCirculation(TaxeCirculationTableDataDTO taxeCirculationTableDataDTO) 
    {
        TaxeCirculationEntity taxeCirculationEntity = taxeCirculationRepository.findById(taxeCirculationTableDataDTO.getId()).get();
        taxeCirculationEntity.setNumeroQuittance(taxeCirculationTableDataDTO.getNumeroQuittance());
        taxeCirculationEntity.setParPoids(taxeCirculationTableDataDTO.isParPoids());
        taxeCirculationEntity.setPoids(taxeCirculationTableDataDTO.getPoids());
        taxeCirculationEntity.setParPlace(taxeCirculationTableDataDTO.isParPlace());
        taxeCirculationEntity.setNombrePlaces(taxeCirculationTableDataDTO.getNombrePlaces());
        taxeCirculationEntity.setDateDebutCirculation(taxeCirculationTableDataDTO.getDateDebutCirculation());
        taxeCirculationEntity.setDateFinCirculation(taxeCirculationTableDataDTO.getDateFinCirculation());
        taxeCirculationEntity.setDateFinValidite(taxeCirculationTableDataDTO.getDateFinValidite());
        taxeCirculationEntity.setMontant(taxeCirculationTableDataDTO.getMontant());
        taxeCirculationRepository.save(taxeCirculationEntity);
    }

    @Override
    public void deleteSelectedTaxeCirculation(Long id) 
    {
        TaxeCirculationEntity taxeCirculationEntity = taxeCirculationRepository.findById(id).get();
        VehiculeEntity vehiculeEntity = taxeCirculationEntity.getVehicule();
        vehiculeEntity.setTaxeCirculation(null);
        vehiculeRepository.save(vehiculeEntity);
        taxeCirculationRepository.deleteById(id);
    }

    @Override
    public List<TaxeCirculationTableDataDTO> getPaginationTaxeCirculationList(int page, int limit, UserEntity userEntity) 
    {
        PageRequest pageable = PageRequest.of(page, limit);
        List<TaxeCirculationEntity> taxeCirculationEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<TaxeCirculationEntity> taxeCirculationEntityPage = (Page<TaxeCirculationEntity>) taxeCirculationRepository.findAll(pageable);
            taxeCirculationEntities = taxeCirculationEntityPage.getContent();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<TaxeCirculationEntity> taxeCirculationEntityPage = taxeCirculationRepository.findAllByVehiculeStructure(structureEntity, pageable);
            taxeCirculationEntities = taxeCirculationEntityPage.getContent();
        }
        return this.loadTaxeCirculationTableDataDTO(taxeCirculationEntities);
    }

    @Override
    public Long getTotalItemTaxeCirculationList(UserEntity userEntity) 
    {
        Long totalItem;
        PageRequest pageable = PageRequest.of(0, 8);
        List<TaxeCirculationEntity> taxeCirculationEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<TaxeCirculationEntity> taxeCirculationEntityPage = (Page<TaxeCirculationEntity>) taxeCirculationRepository.findAll(pageable);
            totalItem = taxeCirculationEntityPage.getTotalElements();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<TaxeCirculationEntity> taxeCirculationEntityPage = taxeCirculationRepository.findAllByVehiculeStructure(structureEntity, pageable);
            totalItem = taxeCirculationEntityPage.getTotalElements();
        }
        return totalItem;
    }

    private VehiculeTableDataDTO loadVehiculeTableData(VehiculeEntity vehiculeEntity) 
    {
        VehiculeTableDataDTO vehiculeTableDataDTO = new VehiculeTableDataDTO();
        vehiculeTableDataDTO.setId(vehiculeEntity.getId());
        vehiculeTableDataDTO.setNumeroPlaque(vehiculeEntity.getNumeroPlaque());
        vehiculeTableDataDTO.setGenre(vehiculeEntity.getGenre());
        vehiculeTableDataDTO.setMarque(vehiculeEntity.getMarque());
        vehiculeTableDataDTO.setType(vehiculeEntity.getType());
        vehiculeTableDataDTO.setEnergie(vehiculeEntity.getEnergie().getDescription());
        vehiculeTableDataDTO.setStructure(vehiculeEntity.getStructure().getDesignation());
        vehiculeTableDataDTO.setNatureAffectation(vehiculeEntity.getNatureAffectation());
        vehiculeTableDataDTO.setEtatVehicule(vehiculeEntity.getEtat());
        if (vehiculeEntity.getBeneficiaire() != null) 
        {
            vehiculeTableDataDTO.setBeneficiaire(vehiculeEntity.getBeneficiaire().getNom());
        }
        return vehiculeTableDataDTO;
    }

    private OneVehiculeDTO loadOneVehiculeData(VehiculeEntity vehiculeEntity) 
    {
        OneVehiculeDTO oneVehiculeDTO = new OneVehiculeDTO();
        oneVehiculeDTO.setId(vehiculeEntity.getId());
        oneVehiculeDTO.setNumeroPlaque(vehiculeEntity.getNumeroPlaque());
        oneVehiculeDTO.setGenre(vehiculeEntity.getGenre());
        oneVehiculeDTO.setMarque(vehiculeEntity.getMarque());
        oneVehiculeDTO.setType(vehiculeEntity.getType());
        oneVehiculeDTO.setEtat(vehiculeEntity.getEtat());
        oneVehiculeDTO.setNatureAffectation(vehiculeEntity.getNatureAffectation());
        oneVehiculeDTO.setDateAffectation(vehiculeEntity.getDateAffectation());
        oneVehiculeDTO.setReferenceAffectation(vehiculeEntity.getReferenceAffectation());
        oneVehiculeDTO.setNumeroImmatriculation(vehiculeEntity.getNumeroImmatriculation());
        oneVehiculeDTO.setNumeroProprietaireEtat(vehiculeEntity.getNumeroProprietaireEtat());
        oneVehiculeDTO.setNumeroChassis(vehiculeEntity.getNumeroChassis());
        oneVehiculeDTO.setNumeroCarteUtilisation(vehiculeEntity.getNumeroCarteUtilisation());
        oneVehiculeDTO.setIndexKm(vehiculeEntity.getIndexKm());
        oneVehiculeDTO.setTypeTaxe(vehiculeEntity.getTypeTaxe());
        oneVehiculeDTO.setExploitationUsage(vehiculeEntity.getExploitationUsage());
        oneVehiculeDTO.setTypeAssurance(vehiculeEntity.getTypeAssurance());
        oneVehiculeDTO.setNomAssurance(vehiculeEntity.getNomAssurance());
        oneVehiculeDTO.setReferenceBC(vehiculeEntity.getReferenceBC());
        oneVehiculeDTO.setDateReception(vehiculeEntity.getDateReception());
        oneVehiculeDTO.setPrixAchat(vehiculeEntity.getPrixAchat());
        oneVehiculeDTO.setSituationDouaniere(vehiculeEntity.getSituationDouaniere());
        oneVehiculeDTO.setNumeroImmatriculationTemporaire(vehiculeEntity.getNumeroImmatriculationTemporaire());
        oneVehiculeDTO.setCompagnieLeasing(vehiculeEntity.getCompagnieLeasing());
        oneVehiculeDTO.setDateEcheance(vehiculeEntity.getDateEcheance());
        oneVehiculeDTO.setNumeroCarteGrise(vehiculeEntity.getNumeroCarteGrise());
        oneVehiculeDTO.setTypeCarteGrise(vehiculeEntity.getTypeCarteGrise());
        oneVehiculeDTO.setReferenceType(vehiculeEntity.getReferenceType());
        oneVehiculeDTO.setNombreDePlaces(vehiculeEntity.getNombreDePlaces());
        oneVehiculeDTO.setCarosserie(vehiculeEntity.getCarosserie());
        oneVehiculeDTO.setPoidsTotalACharge(vehiculeEntity.getPoidsTotalACharge());
        oneVehiculeDTO.setConsommationMoyenne(vehiculeEntity.getConsommationMoyenne());
        oneVehiculeDTO.setChargeUtile(vehiculeEntity.getChargeUtile());
        oneVehiculeDTO.setPoidsTotalSansCharge(vehiculeEntity.getPoidsTotalSansCharge());
        oneVehiculeDTO.setDimensionsPneuAvant(vehiculeEntity.getDimensionsPneuAvant());
        oneVehiculeDTO.setDimensionsPneuArriere(vehiculeEntity.getDimensionsPneuArriere());
        oneVehiculeDTO.setPoidsAVide(vehiculeEntity.getPoidsAVide());
        oneVehiculeDTO.setPuissanceFixale(vehiculeEntity.getPuissanceFixale());
        oneVehiculeDTO.setPuissanceMoteur(vehiculeEntity.getPuissanceMoteur());
        oneVehiculeDTO.setNombreEssieux(vehiculeEntity.getNombreEssieux());
        oneVehiculeDTO.setVolumeCylindre(vehiculeEntity.getVolumeCylindre());
        oneVehiculeDTO.setDatePremiereMiseEnCirculation(vehiculeEntity.getDatePremiereMiseEnCirculation());
        if (vehiculeEntity.getStructure() != null) 
        {
            oneVehiculeDTO.setIdStructure(vehiculeEntity.getStructure().getId());
        }
        if (vehiculeEntity.getGouvernorat() != null) 
        {
            oneVehiculeDTO.setIdGouvernorat(vehiculeEntity.getGouvernorat().getId());
        }
        if (vehiculeEntity.getBeneficiaire() != null) 
        {
            oneVehiculeDTO.setIdBeneficiaire(vehiculeEntity.getBeneficiaire().getId());
        }
        if (vehiculeEntity.getLieuParking() != null) 
        {
            oneVehiculeDTO.setIdLieuParking(vehiculeEntity.getLieuParking().getId());
        }
        if (vehiculeEntity.getEnergie() != null) 
        {
            oneVehiculeDTO.setIdEnergie(vehiculeEntity.getEnergie().getId());
        }
        if (vehiculeEntity.getFournisseur() != null)
        {
            oneVehiculeDTO.setIdFournisseur(vehiculeEntity.getFournisseur().getId());
        }
        if (vehiculeEntity.getUrlImageVehicule() != null) 
        {
            oneVehiculeDTO.setUrlImageVehicule("http://localhost:8080/api/image_car/" + vehiculeEntity.getUrlImageVehicule());
            oneVehiculeDTO.setNameImageVehicule(vehiculeEntity.getNameImageVehicule());
        }
        return oneVehiculeDTO;
    }

    private List<SelectVehiculeDTO> loadSelectVehiculeByStrucuture(List<VehiculeEntity> vehiculeEntities) 
    {
        List<SelectVehiculeDTO> selectVehiculeDTOS = new ArrayList<>();
        if (!vehiculeEntities.isEmpty()) 
        {
            vehiculeEntities.forEach(vehiculeEntity -> 
            {
                SelectVehiculeDTO selectVehiculeDTO = new SelectVehiculeDTO();
                selectVehiculeDTO.setId(vehiculeEntity.getId());
                selectVehiculeDTO.setNumeroPlaque(vehiculeEntity.getNumeroPlaque());
                selectVehiculeDTO.setCodeStructure(vehiculeEntity.getStructure().getCode());
                selectVehiculeDTO.setDesignationStructure(vehiculeEntity.getStructure().getDesignation());
                if (vehiculeEntity.getBeneficiaire() != null) 
                {
                    selectVehiculeDTO.setNomBeneficiaire(vehiculeEntity.getBeneficiaire().getNom());
                    selectVehiculeDTO.setMatriculeBeneficiaire(vehiculeEntity.getBeneficiaire().getMatricule());
                    selectVehiculeDTO.setAgePermis(vehiculeEntity.getBeneficiaire().getAgePermis());
                }
                selectVehiculeDTOS.add(selectVehiculeDTO);
            });
        }
        return selectVehiculeDTOS;
    }

    private List<TaxeCirculationTableDataDTO> loadTaxeCirculationTableDataDTO(List<TaxeCirculationEntity> taxeCirculationEntities) 
    {
        List<TaxeCirculationTableDataDTO> taxeCirculationTableDataDTOS = new ArrayList<>();
        if (!taxeCirculationEntities.isEmpty()) 
        {
            taxeCirculationEntities.forEach(taxeCirculationEntity -> 
            {
                TaxeCirculationTableDataDTO taxeCirculationTableDataDTO = new TaxeCirculationTableDataDTO();
                taxeCirculationTableDataDTO.setId(taxeCirculationEntity.getId());
                taxeCirculationTableDataDTO.setNumeroQuittance(taxeCirculationEntity.getNumeroQuittance());
                taxeCirculationTableDataDTO.setParPoids(taxeCirculationEntity.isParPoids());
                taxeCirculationTableDataDTO.setPoids(taxeCirculationEntity.getPoids());
                taxeCirculationTableDataDTO.setParPlace(taxeCirculationEntity.isParPlace());
                taxeCirculationTableDataDTO.setNombrePlaces(taxeCirculationEntity.getNombrePlaces());
                taxeCirculationTableDataDTO.setDateDebutCirculation(taxeCirculationEntity.getDateDebutCirculation());
                taxeCirculationTableDataDTO.setDateFinCirculation(taxeCirculationEntity.getDateFinCirculation());
                taxeCirculationTableDataDTO.setMontant(taxeCirculationEntity.getMontant());
                taxeCirculationTableDataDTO.setDateFinValidite(taxeCirculationEntity.getDateFinValidite());
                taxeCirculationTableDataDTO.setIdVehicule(taxeCirculationEntity.getVehicule().getId());
                taxeCirculationTableDataDTO.setNumeroPlaque(taxeCirculationEntity.getVehicule().getNumeroPlaque());
                taxeCirculationTableDataDTOS.add(taxeCirculationTableDataDTO);
            });
        }
        VehiculeTableDataDTO vehiculeTableDataDTO = new VehiculeTableDataDTO();
        return taxeCirculationTableDataDTOS;
    }

    private List<AssuranceTableDataDTO> loadAssuranceTableDataDTO(List<AssuranceEntity> assuranceEntities) 
    {
        List<AssuranceTableDataDTO> assuranceTableDataDTOS = new ArrayList<>();
        if (!assuranceEntities.isEmpty())
        {
            assuranceEntities.forEach(assuranceEntity -> 
            {
                AssuranceTableDataDTO assuranceTableDataDTO = new AssuranceTableDataDTO();
                assuranceTableDataDTO.setId(assuranceEntity.getId());
                assuranceTableDataDTO.setDatePMC(assuranceEntity.getDatePMC());
                assuranceTableDataDTO.setPuissanceFiscale(assuranceEntity.getPuissanceFiscale());
                assuranceTableDataDTO.setNombreplaces(assuranceEntity.getNombreplaces());
                assuranceTableDataDTO.setPuissanceFiscale(assuranceEntity.getPuissanceFiscale());
                assuranceTableDataDTO.setMontantAssurance(assuranceEntity.getMontantAssurance());
                assuranceTableDataDTO.setDateDebutValidite(assuranceEntity.getDateDebutValidite());
                assuranceTableDataDTO.setDateFinValidite(assuranceEntity.getDateFinValidite());
                assuranceTableDataDTO.setAssuranceSP(assuranceEntity.getAssuranceSP());
                assuranceTableDataDTO.setNumeroContrat(assuranceEntity.getNumeroContrat());
                assuranceTableDataDTO.setCompagnieAssurance(assuranceEntity.getCompagnieAssurance());
                assuranceTableDataDTO.setVehicules(new ArrayList<>());
                if (!assuranceEntity.getVehicules().isEmpty()) 
                {
                    assuranceEntity.getVehicules().forEach(vehiculeEntity -> 
                    {
                        SelectVehiculeDTO selectVehiculeDTO = new SelectVehiculeDTO();
                        selectVehiculeDTO.setId(vehiculeEntity.getId());
                        selectVehiculeDTO.setCodeStructure(vehiculeEntity.getStructure().getCode());
                        selectVehiculeDTO.setDesignationStructure(vehiculeEntity.getStructure().getDesignation());
                        assuranceTableDataDTO.getVehicules().add(selectVehiculeDTO);
                    });
                }
                assuranceTableDataDTOS.add(assuranceTableDataDTO);
            });
        }
        return assuranceTableDataDTOS;
    }

    private List<VisiteTechniqueTableDataDTO> loadVisiteTechniqueTableDataDTO(List<VisiteTechniqueEntity> visiteTechniqueEntities)
    {
        List<VisiteTechniqueTableDataDTO> visiteTechniqueTableDataDTOS = new ArrayList<>();
        if (!visiteTechniqueEntities.isEmpty()) 
        {
            visiteTechniqueEntities.forEach(visiteTechniqueEntity -> 
            {
                VisiteTechniqueTableDataDTO visiteTechniqueTableDataDTO = new VisiteTechniqueTableDataDTO();
                visiteTechniqueTableDataDTO.setId(visiteTechniqueEntity.getId());
                visiteTechniqueTableDataDTO.setDatePMC(visiteTechniqueEntity.getDatePMC());
                visiteTechniqueTableDataDTO.setNombrePlaces(visiteTechniqueEntity.getNombrePlaces());
                visiteTechniqueTableDataDTO.setPuissanceFiscale(visiteTechniqueEntity.getPuissanceFiscale());
                visiteTechniqueTableDataDTO.setMontantVisiteTechnique(visiteTechniqueEntity.getMontantVisiteTechnique());
                visiteTechniqueTableDataDTO.setDateDebutValidite(visiteTechniqueEntity.getDateDebutValidite());
                visiteTechniqueTableDataDTO.setDateFinValidite(visiteTechniqueEntity.getDateFinValidite());
                visiteTechniqueTableDataDTO.setIdVehicule(visiteTechniqueEntity.getVehicule().getId());
                visiteTechniqueTableDataDTO.setNumeroPlaque(visiteTechniqueEntity.getVehicule().getNumeroPlaque());
                visiteTechniqueTableDataDTO.setStructure(visiteTechniqueEntity.getVehicule().getStructure().getDesignation());
                logger.info(String.valueOf(visiteTechniqueEntity.getPrixAchat()));
                visiteTechniqueTableDataDTO.setPrixAchat(visiteTechniqueEntity.getPrixAchat());
                visiteTechniqueTableDataDTOS.add(visiteTechniqueTableDataDTO);
            });
        }
        return visiteTechniqueTableDataDTOS;
    }


    private List<GpsTableDataDTO> loadGpsTableDataDTO(List<GpsEntity> gpsEntities) 
    {
        List<GpsTableDataDTO> gpsTableDataDTOS = new ArrayList<>();
        if (!gpsEntities.isEmpty()) 
        {
            gpsEntities.forEach(gpsEntity -> 
            {
                GpsTableDataDTO gpsTableDataDTO = new GpsTableDataDTO();
                gpsTableDataDTO.setId(gpsEntity.getId());
                gpsTableDataDTO.setCodeIMEI(gpsEntity.getCodeIMEI());
                gpsTableDataDTO.setLien(gpsEntity.getLien());
                gpsTableDataDTO.setIdVehicule(gpsEntity.getVehicule().getId());
                gpsTableDataDTO.setNumeroPlaque(gpsEntity.getVehicule().getNumeroPlaque());
                gpsTableDataDTO.setStructure(gpsEntity.getVehicule().getStructure().getDesignation());
                gpsTableDataDTOS.add(gpsTableDataDTO);
            });
        }
        return gpsTableDataDTOS;
    }

    private List<ReformeTableDataDTO> loadReformeTableDataDTO(List<ReformeEntity> reformeEntities) 
    {
        List<ReformeTableDataDTO> reformeTableDataDTOS = new ArrayList<>();
        if (!reformeEntities.isEmpty()) 
        {
            reformeEntities.forEach(reformeEntity ->
            {
                ReformeTableDataDTO reformeTableDataDTO = new ReformeTableDataDTO();
                reformeTableDataDTO.setId(reformeEntity.getId());
                reformeTableDataDTO.setNom(reformeEntity.getNom());
                reformeTableDataDTO.setDate(reformeEntity.getDate());
                reformeTableDataDTO.setReference(reformeEntity.getReference());
                reformeTableDataDTO.setDateSortie(reformeEntity.getDateSortie());
                reformeTableDataDTO.setPrix(reformeEntity.getPrix());
                reformeTableDataDTO.setCause(reformeEntity.getCause());
                reformeTableDataDTO.setIdVehicule(reformeEntity.getVehicule().getId());
                reformeTableDataDTO.setNumeroPlaque(reformeEntity.getVehicule().getNumeroPlaque());
                reformeTableDataDTO.setStructure(reformeEntity.getVehicule().getStructure().getDesignation());
                reformeTableDataDTOS.add(reformeTableDataDTO);
            });
        }
        return reformeTableDataDTOS;
    }

    @Override
    public void addNewAssurance(NewAssuranceDTO newAssuranceDTO) 
    {
        AssuranceEntity assuranceEntity = new AssuranceEntity();
        assuranceEntity.setDatePMC(newAssuranceDTO.getDatePMC());
        assuranceEntity.setNombreplaces(newAssuranceDTO.getNombreplaces());
        assuranceEntity.setMontantAssurance(newAssuranceDTO.getMontantAssurance());
        assuranceEntity.setDateDebutValidite(newAssuranceDTO.getDateDebutValidite());
        assuranceEntity.setDateFinValidite(newAssuranceDTO.getDateFinValidite());
        assuranceEntity.setAssuranceSP(newAssuranceDTO.getAssuranceSP());
        assuranceEntity.setNumeroContrat(newAssuranceDTO.getNumeroContrat());
        assuranceEntity.setCompagnieAssurance(newAssuranceDTO.getCompagnieAssurance());
        assuranceEntity.setPuissanceFiscale(newAssuranceDTO.getPuissanceFiscale());
        assuranceRepository.save(assuranceEntity);
        assuranceEntity.setVehicules(new ArrayList<>());
        newAssuranceDTO.getIdVehicules().forEach(idVehicule -> 
        {
            VehiculeEntity vehiculeEntity = vehiculeRepository.findById(idVehicule).get();
            vehiculeEntity.setAssurance(assuranceEntity);
            assuranceEntity.getVehicules().add(vehiculeEntity);
            vehiculeRepository.save(vehiculeEntity);
        });
        assuranceRepository.save(assuranceEntity);
    }

    @Override
    public List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForAssurance(UserEntity userEntity)
    {
        List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            vehiculeEntities = vehiculeRepository.findAllByAssuranceNull();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            vehiculeEntities = vehiculeRepository.findAllByStructureAndAssuranceNull(structureEntity);
        }
        return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
    }

    @Override
    public void modifySelectedAssurance(NewAssuranceDTO newAssuranceDTO) 
    {
        AssuranceEntity assuranceEntity = assuranceRepository.findById(newAssuranceDTO.getId()).get();
        assuranceEntity.setDatePMC(newAssuranceDTO.getDatePMC());
        assuranceEntity.setNombreplaces(newAssuranceDTO.getNombreplaces());
        assuranceEntity.setMontantAssurance(newAssuranceDTO.getMontantAssurance());
        assuranceEntity.setDateDebutValidite(newAssuranceDTO.getDateDebutValidite());
        assuranceEntity.setDateFinValidite(newAssuranceDTO.getDateFinValidite());
        assuranceEntity.setAssuranceSP(newAssuranceDTO.getAssuranceSP());
        assuranceEntity.setNumeroContrat(newAssuranceDTO.getNumeroContrat());
        assuranceEntity.setPuissanceFiscale(newAssuranceDTO.getPuissanceFiscale());
        assuranceEntity.setCompagnieAssurance(newAssuranceDTO.getCompagnieAssurance());
        assuranceEntity.getVehicules().forEach(vehiculeEntity -> 
        {
            vehiculeEntity.setAssurance(null);
            vehiculeRepository.save(vehiculeEntity);
        });
        assuranceEntity.getVehicules().clear();
        assuranceRepository.save(assuranceEntity);
        newAssuranceDTO.getIdVehicules().forEach(idVehicule -> 
        {
            VehiculeEntity vehiculeEntity = vehiculeRepository.findById(idVehicule).get();
            vehiculeEntity.setAssurance(assuranceEntity);
            assuranceEntity.getVehicules().add(vehiculeEntity);
            vehiculeRepository.save(vehiculeEntity);
        });
        assuranceRepository.save(assuranceEntity);
    }

    @Override
    public void deleteSelectedAssurance(Long id) 
    {
        AssuranceEntity assuranceEntity = assuranceRepository.findById(id).get();
        assuranceEntity.getVehicules().forEach(vehiculeEntity ->
        {
            vehiculeEntity.setAssurance(null);
            vehiculeRepository.save(vehiculeEntity);
        });
        assuranceEntity.getVehicules().clear();
        assuranceRepository.save(assuranceEntity);
        assuranceRepository.deleteById(id);
    }

    @Override
    public List<AssuranceTableDataDTO> getPaginationAssuranceList(int page, int limit, UserEntity userEntity) 
    {
        PageRequest pageable = PageRequest.of(page, limit);
        List<AssuranceEntity> assuranceEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty())
        {
            Page<AssuranceEntity> assuranceEntityPage = assuranceRepository.findAll(pageable);
            assuranceEntities = assuranceEntityPage.getContent();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            List<VehiculeEntity> vehiculeEntities = vehiculeRepository.findAllByStructure(structureEntity);
            Page<AssuranceEntity> assuranceEntityPage = assuranceRepository.findAll(pageable);
            assuranceEntities = assuranceEntityPage.getContent();
        }
        return this.loadAssuranceTableDataDTO(assuranceEntities);
    }

    @Override
    public Long getTotalItemAssuranceList(UserEntity userEntity) 
    {
        PageRequest pageable = PageRequest.of(0, 8);
        Long totalItem;
        if (userEntity.getStructures().isEmpty())
        {
            Page<AssuranceEntity> taxeCirculationEntityPage = assuranceRepository.findAll(pageable);
            totalItem = taxeCirculationEntityPage.getTotalElements();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            List<VehiculeEntity> vehiculeEntities = vehiculeRepository.findAllByStructure(structureEntity);
            Page<AssuranceEntity> taxeCirculationEntityPage = assuranceRepository.findAll(pageable);
            totalItem = taxeCirculationEntityPage.getTotalElements();
        }
        return totalItem;
    }

    @Override
    public void addNewVisiteTechnique(VisiteTechniqueTableDataDTO visiteTechniqueTableDataDTO) 
    {
        VisiteTechniqueEntity visiteTechniqueEntity = new VisiteTechniqueEntity();
        visiteTechniqueEntity.setDateFinValidite(visiteTechniqueTableDataDTO.getDateFinValidite());
        visiteTechniqueEntity.setMontantVisiteTechnique(visiteTechniqueTableDataDTO.getMontantVisiteTechnique());
        visiteTechniqueEntity.setPuissanceFiscale(visiteTechniqueTableDataDTO.getPuissanceFiscale());
        visiteTechniqueEntity.setNombrePlaces(visiteTechniqueTableDataDTO.getNombrePlaces());
        visiteTechniqueEntity.setDatePMC(visiteTechniqueTableDataDTO.getDatePMC());
        visiteTechniqueEntity.setDateDebutValidite(visiteTechniqueTableDataDTO.getDateDebutValidite());
        visiteTechniqueEntity.setPrixAchat(visiteTechniqueTableDataDTO.getPrixAchat());
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(visiteTechniqueTableDataDTO.getIdVehicule()).get();
        visiteTechniqueEntity.setVehicule(vehiculeEntity);
       // vehiculeEntity.setVisiteTechnique(visiteTechniqueEntity);
        
        visiteTechniqueRepository.save(visiteTechniqueEntity);
    }

    @Override
    public List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForVisiteTechnique(UserEntity userEntity) 
    {
        List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            vehiculeEntities = (List<VehiculeEntity>) vehiculeRepository.findAll();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            vehiculeEntities = vehiculeRepository.findAllByStructure(structureEntity);
        }
        return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
    }


    @Override
    public List<VisiteTechniqueTableDataDTO> getPaginationVisiteTechniqueList(String structure,int page, int limit, UserEntity userEntity) {
        PageRequest pageable = PageRequest.of(page, limit);
        List<VisiteTechniqueEntity> visiteTechniqueEntities = new ArrayList<>();
        
        if ((structure.length()> 0)) {
			Page<VisiteTechniqueEntity> visiteTechniqueEntity = visiteTechniqueRepository
					.findAllByStructure(structure, pageable);
			visiteTechniqueEntities = visiteTechniqueEntity.getContent();
			
		}
        else if (userEntity.getStructures().isEmpty()) {
            Page<VisiteTechniqueEntity> visiteTechniqueEntityPage = visiteTechniqueRepository.findAll(pageable);
            visiteTechniqueEntities = visiteTechniqueEntityPage.getContent();
        }
        
        else  {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<VisiteTechniqueEntity> visiteTechniqueEntityPage = visiteTechniqueRepository.findAllByVehiculeStructure(structureEntity, pageable);
            visiteTechniqueEntities = visiteTechniqueEntityPage.getContent();
        }
        
        if ((structure.length()> 0)) {
			Page<VisiteTechniqueEntity> visiteTechniqueEntity = visiteTechniqueRepository
					.findAllByStructure(structure, pageable);
			visiteTechniqueEntities = visiteTechniqueEntity.getContent();
			
		}
        
        
        return this.loadVisiteTechniqueTableDataDTO(visiteTechniqueEntities);
    }

    @Override
    public Long getTotalItemVisiteTechniqueList(UserEntity userEntity) 
    {
        Long totalItem;
        PageRequest pageable = PageRequest.of(0, 1);
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<VisiteTechniqueEntity> visiteTechniqueEntityPage = visiteTechniqueRepository.findAll(pageable);
            totalItem = visiteTechniqueEntityPage.getTotalElements();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<VisiteTechniqueEntity> visiteTechniqueEntityPage = visiteTechniqueRepository.findAllByVehiculeStructure(structureEntity, pageable);
            totalItem = visiteTechniqueEntityPage.getTotalElements();
        }
        return totalItem;
    }

    @Override
    public void modifySelectedVisiteTechnique(VisiteTechniqueTableDataDTO visiteTechniqueTableDataDTO) 
    {
    	System.out.println(visiteTechniqueTableDataDTO);
        VisiteTechniqueEntity visiteTechniqueEntity = visiteTechniqueRepository.findById(visiteTechniqueTableDataDTO.getId()).get();
        visiteTechniqueEntity.setDatePMC(visiteTechniqueTableDataDTO.getDatePMC());
        visiteTechniqueEntity.setNombrePlaces(visiteTechniqueTableDataDTO.getNombrePlaces());
        visiteTechniqueEntity.setPuissanceFiscale(visiteTechniqueTableDataDTO.getPuissanceFiscale());
        visiteTechniqueEntity.setMontantVisiteTechnique(visiteTechniqueTableDataDTO.getMontantVisiteTechnique());
        visiteTechniqueEntity.setDateDebutValidite(visiteTechniqueTableDataDTO.getDateDebutValidite());
        visiteTechniqueEntity.setDateFinValidite(visiteTechniqueTableDataDTO.getDateFinValidite());
        visiteTechniqueEntity.setPrixAchat(visiteTechniqueTableDataDTO.getPrixAchat());
        visiteTechniqueRepository.save(visiteTechniqueEntity);
    }

    @Override
    public void deleteSelectedVisiteTechnique(Long id) 
    {
        VisiteTechniqueEntity visiteTechniqueEntity = visiteTechniqueRepository.findById(id).get();
        VehiculeEntity vehiculeEntity = visiteTechniqueEntity.getVehicule();
        vehiculeEntity.setVisiteTechnique(null);
        vehiculeRepository.save(vehiculeEntity);
        visiteTechniqueRepository.deleteById(id);
    }

    @Override
    public void addNewReforme(ReformeTableDataDTO reformeTableDataDTO) 
    {
        ReformeEntity reformeEntity = new ReformeEntity();
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(reformeTableDataDTO.getIdVehicule()).get();
        reformeEntity.setPrix(reformeTableDataDTO.getPrix());
        reformeEntity.setNom(reformeTableDataDTO.getNom());
        reformeEntity.setDateSortie(reformeTableDataDTO.getDateSortie());
        reformeEntity.setDate(reformeTableDataDTO.getDate());
        reformeEntity.setCause(reformeTableDataDTO.getCause());
        reformeEntity.setReference(reformeTableDataDTO.getReference());
        reformeEntity.setVehicule(vehiculeEntity);
        vehiculeEntity.setReforme(reformeEntity);
        reformeRepository.save(reformeEntity);
    }

    @Override
    public List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForReforme(UserEntity userEntity) 
    {
        List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            vehiculeEntities = vehiculeRepository.findAllByReformeNull();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            vehiculeEntities = vehiculeRepository.findAllByStructureAndReformeNull(structureEntity);
        }
        return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
    }

    @Override
    public List<ReformeTableDataDTO> getPaginationReformeList(int page, int limit, UserEntity userEntity) 
    {
        PageRequest pageable = PageRequest.of(page, limit);
        List<ReformeEntity> reformeEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<ReformeEntity> reformeEntityPage = reformeRepository.findAll(pageable);
            reformeEntities = reformeEntityPage.getContent();
        }
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<ReformeEntity> reformeEntityPage = reformeRepository.findAllByVehiculeStructure(structureEntity, pageable);
            reformeEntities = reformeEntityPage.getContent();
        }
        return this.loadReformeTableDataDTO(reformeEntities);
    }

    @Override
    public Long getTotalItemReformeList(UserEntity userEntity) 
    {
        PageRequest pageable = PageRequest.of(0, 8);
        Long totalItem;
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<ReformeEntity> reformeEntityPage = reformeRepository.findAll(pageable);
            totalItem = reformeEntityPage.getTotalElements();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<ReformeEntity> reformeEntityPage = reformeRepository.findAllByVehiculeStructure(structureEntity, pageable);
            totalItem = reformeEntityPage.getTotalElements();
        }
        return totalItem;
    }

    @Override
    public void modifySelectedReforme(ReformeTableDataDTO reformeTableDataDTO) 
    {
        ReformeEntity reformeEntity = reformeRepository.findById(reformeTableDataDTO.getId()).get();
        reformeEntity.setCause(reformeTableDataDTO.getCause());
        reformeEntity.setDate(reformeTableDataDTO.getDate());
        reformeEntity.setDateSortie(reformeTableDataDTO.getDateSortie());
        reformeEntity.setNom(reformeTableDataDTO.getNom());
        reformeEntity.setPrix(reformeTableDataDTO.getPrix());
        reformeEntity.setReference(reformeTableDataDTO.getReference());
        reformeRepository.save(reformeEntity);
    }

    @Override
    public void deleteSelectedReforme(Long id) 
    {
        reformeRepository.deleteById(id);
    }

    @Override
    public void addNewGPS(GpsTableDataDTO gpsTableDataDTO, Long idVehicule) 
    {
        GpsEntity gpsEntity = new GpsEntity();
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(idVehicule).get();
        gpsEntity.setCodeIMEI(gpsTableDataDTO.getCodeIMEI());
        gpsEntity.setLien(gpsTableDataDTO.getLien());
        gpsEntity.setVehicule(vehiculeEntity);
        vehiculeEntity.setGps(gpsEntity);
        gpsRepository.save(gpsEntity);
    }

    @Override
    public void modifySelectedGPS(GpsTableDataDTO gpsTableDataDTO) 
    {
        GpsEntity gpsEntity = gpsRepository.findById(gpsTableDataDTO.getId()).get();
        gpsEntity.setCodeIMEI(gpsTableDataDTO.getCodeIMEI());
        gpsEntity.setLien(gpsTableDataDTO.getLien());
        gpsRepository.save(gpsEntity);
    }

    @Override
    public void deleteSelectedGPS(Long id) 
    {
        GpsEntity gpsEntity = gpsRepository.findById(id).get();
        VehiculeEntity vehiculeEntity = gpsEntity.getVehicule();
        vehiculeEntity.setGps(null);
        vehiculeRepository.save(vehiculeEntity);
        gpsRepository.deleteById(id);
    }

    @Override
    public List<GpsTableDataDTO> getPaginationGPSList(int page, int limit, UserEntity userEntity) 
    {
        PageRequest pageable = PageRequest.of(page, limit);
        List<GpsEntity> gpsEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<GpsEntity> gpsEntityPage = gpsRepository.findAll(pageable);
            gpsEntities = gpsEntityPage.getContent();
        }
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<GpsEntity> gpsEntityPage = gpsRepository.findAllByVehiculeStructure(structureEntity, pageable);
            gpsEntities = gpsEntityPage.getContent();
        }
        return this.loadGpsTableDataDTO(gpsEntities);
    }

    @Override
    public Long getTotalItemGPSList(UserEntity userEntity) 
    {
        PageRequest pageable = PageRequest.of(0, 8);
        Long totalItem;
        if (userEntity.getStructures().isEmpty()) 
        {
            Page<GpsEntity> gpsEntityPage = gpsRepository.findAll(pageable);
            totalItem = gpsEntityPage.getTotalElements();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            Page<GpsEntity> gpsEntityPage = gpsRepository.findAllByVehiculeStructure(structureEntity, pageable);
            totalItem = gpsEntityPage.getTotalElements();
        }
        return totalItem;
    }

    @Override
    public List<SelectVehiculeDTO> getSelectVehiculeByStrucutureForGPS(UserEntity userEntity) 
    {
        List<VehiculeEntity> vehiculeEntities = new ArrayList<>();
        if (userEntity.getStructures().isEmpty()) 
        {
            vehiculeEntities = vehiculeRepository.findAllByGpsNull();
        } 
        else 
        {
            StructureEntity structureEntity = userEntity.getStructures().get(0);
            vehiculeEntities = vehiculeRepository.findAllByStructureAndGpsNull(structureEntity);
        }
        return this.loadSelectVehiculeByStrucuture(vehiculeEntities);
    }

    @Override
    public List<TaxeCirculationTableDataDTO> getPaginationTaxeCirculationListByCustomSearching(CustomSearchingTaxeCirculationDTO customSearchingTaxeCirculationDTO, int page, int limit) 
    {
        PageRequest pageable = PageRequest.of(page, limit);
        List<TaxeCirculationEntity> taxeCirculationEntities = new ArrayList<>();
        CustomSearchingTaxeCirculationDTO customSearching = this.loadCustomSearchingTaxeCirculation(customSearchingTaxeCirculationDTO);
        if (customSearching.isParPlace()) 
        {
            Page<TaxeCirculationEntity> taxeCirculationEntityPage = taxeCirculationRepository.findAllByDateDebutCirculationGreaterThanAndDateFinCirculationIsLessThanAndDateFinValiditeBetweenAndMontantBetweenAndNombrePlacesBetween(customSearching.getDateDebutCirculationMin(), customSearching.getDateFinCirculationMax(), customSearching.getDateFinValiditeMin(), customSearching.getDateFinValiditeMax(), customSearching.getMontantMin(), customSearching.getMontantMax(), customSearching.getNombrePlacesMin(), customSearching.getNombrePlacesMax(), pageable);
        } 
        else 
        {
            Page<TaxeCirculationEntity> taxeCirculationEntityPage = taxeCirculationRepository.findAllByDateDebutCirculationGreaterThanAndDateFinCirculationIsLessThanAndDateFinValiditeBetweenAndMontantBetweenAndPoidsBetween(customSearching.getDateDebutCirculationMin(), customSearching.getDateFinCirculationMax(), customSearching.getDateFinValiditeMin(), customSearching.getDateFinValiditeMax(), customSearching.getMontantMin(), customSearching.getMontantMax(), customSearching.getPoidsMin(), customSearching.getPoidsMax(), pageable);
        }
        return this.loadTaxeCirculationTableDataDTO(taxeCirculationEntities);
    }

    private CustomSearchingTaxeCirculationDTO loadCustomSearchingTaxeCirculation(CustomSearchingTaxeCirculationDTO customSearchingTaxeCirculationDTO) 
    {
        if (customSearchingTaxeCirculationDTO.getPoidsMin() == 0) 
        {
            customSearchingTaxeCirculationDTO.setPoidsMin(taxeCirculationRepository.getMinPoids());
        }
        if (customSearchingTaxeCirculationDTO.getPoidsMax() == 0) 
        {
            customSearchingTaxeCirculationDTO.setPoidsMax(taxeCirculationRepository.getMaxPoids());
        }
        if (customSearchingTaxeCirculationDTO.getNombrePlacesMin() == 0) {
            customSearchingTaxeCirculationDTO.setNombrePlacesMin(taxeCirculationRepository.getMinNombrePlaces());
        }
        if (customSearchingTaxeCirculationDTO.getNombrePlacesMax() == 0) 
        {
            customSearchingTaxeCirculationDTO.setNombrePlacesMax(taxeCirculationRepository.getMaxNombrePlaces());
        }
        if (customSearchingTaxeCirculationDTO.getDateDebutCirculationMin() == null)
        {
            customSearchingTaxeCirculationDTO.setDateDebutCirculationMin(taxeCirculationRepository.getMinDateDebutCirculation().minusDays(1));
        }
        if (customSearchingTaxeCirculationDTO.getDateFinCirculationMax() == null) 
        {
            customSearchingTaxeCirculationDTO.setDateFinCirculationMax(taxeCirculationRepository.getMaxDateFinCirculation().plusDays(1));
        }
        if (customSearchingTaxeCirculationDTO.getMontantMin() == 0) 
        {
            customSearchingTaxeCirculationDTO.setMontantMin(taxeCirculationRepository.getMinMontant());
        }
        if (customSearchingTaxeCirculationDTO.getMontantMax() == 0)
        {
            customSearchingTaxeCirculationDTO.setMontantMax(taxeCirculationRepository.getMaxMontant());
        }
        if (customSearchingTaxeCirculationDTO.getDateFinValiditeMin() == null)
        {
            customSearchingTaxeCirculationDTO.setDateFinValiditeMin(taxeCirculationRepository.getMinDateFinValidite().minusDays(1));
        }
        if (customSearchingTaxeCirculationDTO.getDateFinValiditeMax() == null)
        {
            customSearchingTaxeCirculationDTO.setDateFinValiditeMax(taxeCirculationRepository.getMaxDateFinValidite().plusDays(1));
        }
        return customSearchingTaxeCirculationDTO;
    }

    @Override
    public List<DocumentTableDataDTO> getListDocumentByVehicule(Long id) 
    {
        List<DocumentEntity> documentEntities = vehiculeRepository.findById(id).get().getDocuments();
        return this.loadDocumentsTableData(documentEntities);
    }

    private List<DocumentTableDataDTO> loadDocumentsTableData(List<DocumentEntity> documentEntities) 
    {
        List<DocumentTableDataDTO> documentTableDataDTOS = new ArrayList<>();
        if (!documentEntities.isEmpty()) 
        {
            documentEntities.forEach(documentEntity -> 
            {
                DocumentTableDataDTO documentTableDataDTO = new DocumentTableDataDTO();
                documentTableDataDTO.setId(documentEntity.getId());
                documentTableDataDTO.setAdresse("http://localhost:8080/api/document_car/" + documentEntity.getAdresse());
                documentTableDataDTO.setName(documentEntity.getNome());
                documentTableDataDTO.setNumeroPlaque(documentEntity.getVehicule().getNumeroPlaque());
                documentTableDataDTO.setIdVehicule(documentEntity.getVehicule().getId());
                documentTableDataDTOS.add(documentTableDataDTO);
            });
        }
        return documentTableDataDTOS;
    }

    @Override
    public void deleteSelectedDocument(Long id)
    {
        DocumentEntity documentEntity = documentRepository.findById(id).get();
        VehiculeEntity vehiculeEntity = documentEntity.getVehicule();
        vehiculeEntity.getDocuments().remove(documentEntity);
        vehiculeRepository.save(vehiculeEntity);
        documentRepository.delete(documentEntity);
    }

	@Override
	public List<AgilisFileDataTableDTO> getPaginationDonneeExcelList(String idFile, int page, int limit) {
		List<AgilisFileData> agilisFileDataEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		if (idFile.length()>1) {
			Page<AgilisFileData> agilisFileData = agilisFileDataRepository.findByIdFile(idFile,pageable);
			agilisFileDataEntities = agilisFileData.getContent();

			
		}else {
			Page<AgilisFileData> agilisFileData = agilisFileDataRepository.findAll(pageable);
			agilisFileDataEntities = agilisFileData.getContent();
		}
		return this.loadFileDataDTO(agilisFileDataEntities);
	
	}
	  private List<AgilisFileDataTableDTO> loadFileDataDTO(List<AgilisFileData> agilisFileDataEntities) {
	    	List<AgilisFileDataTableDTO> agilisFileDataTableDTOS = new ArrayList<>();
	    	if (!agilisFileDataEntities.isEmpty()) {
	    		agilisFileDataEntities.forEach(agilisFileDataEntity -> {
	    	AgilisFileDataTableDTO agilisFileDataTableDTO = new AgilisFileDataTableDTO();
	    	agilisFileDataTableDTO.setId(agilisFileDataEntity.getId());
	    	agilisFileDataTableDTO.setConsommation(agilisFileDataEntity.getConsommation());
	    	agilisFileDataTableDTO.setIdFile(agilisFileDataEntity.getIdFile());
	    	agilisFileDataTableDTO.setStation(agilisFileDataEntity.getStation());
	    	agilisFileDataTableDTO.setCrt_Porteur_Perso(agilisFileDataEntity.getCrt_Porteur_Perso());
	    	agilisFileDataTableDTO.setFacturation(agilisFileDataEntity.getFacturation());
	    	agilisFileDataTableDTO.setIndex_fin_mois(agilisFileDataEntity.getIndex_fin_mois());
	    	agilisFileDataTableDTO.setProduits(agilisFileDataEntity.getProduits());
	    	agilisFileDataTableDTO.setQte(agilisFileDataEntity.getQte());
	    	agilisFileDataTableDTO.setConfirm(agilisFileDataEntity.isConfirm());
	    	agilisFileDataTableDTO.setValidation(agilisFileDataEntity.isValidation());
	    	agilisFileDataTableDTO.setQte_reservoir(agilisFileDataEntity.getQte_reservoir());
	    	agilisFileDataTableDTO.setTransac_kilometrage(agilisFileDataEntity.getTransac_kilometrage());
	    	agilisFileDataTableDTO.setTransac_num_carte(agilisFileDataEntity.getTransac_num_carte());
	    	agilisFileDataTableDTO.setTransacDate(agilisFileDataEntity.getTransacDate());
	    	agilisFileDataTableDTOS.add(agilisFileDataTableDTO);
	    	});
	    	}
	    	return agilisFileDataTableDTOS;



	    	}

	@Override
	public Long getTotalItemDataList() {
					PageRequest pageable = PageRequest.of(0, 8);
			return agilisFileDataRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public List<AgilisPriceDataTableDTO> getPaginationPriceList(int page, int limit) {
		List<PricesEntity> pricesEntities = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		
			Page<PricesEntity> pricesEntity = pricesRepository.findAll(pageable);
			pricesEntities = pricesEntity.getContent();
		
		return this.loadPriceDataDTO(pricesEntities);	}
	
	  private List<AgilisPriceDataTableDTO> loadPriceDataDTO(List<PricesEntity> pricesEntities) {
	    	List<AgilisPriceDataTableDTO> agilisPriceDataTableDTOS = new ArrayList<>();
	    	if (!pricesEntities.isEmpty()) {
	    		pricesEntities.forEach(pricesEntity -> {
	    			AgilisPriceDataTableDTO agilisPriceDataTableDTO = new AgilisPriceDataTableDTO();
	    			agilisPriceDataTableDTO.setId(pricesEntity.getId());
	    			agilisPriceDataTableDTO.setDate(pricesEntity.getDate());
	    			agilisPriceDataTableDTO.setEssence(pricesEntity.getEssence());
	    			agilisPriceDataTableDTO.setGazoil(pricesEntity.getGazoil());
	    			agilisPriceDataTableDTO.setGazoilsanssoufre(pricesEntity.getGazoilsanssoufre());
	    			agilisPriceDataTableDTOS.add(agilisPriceDataTableDTO);
	    	});
	    	}
	    	return agilisPriceDataTableDTOS;



	    	}

	@Override
	public Long getTotalItemPricesList() {
		PageRequest pageable = PageRequest.of(0, 8);
return pricesRepository.findAll(pageable).getTotalElements();
	}
/*
	@Override
    public List<VisiteTechniqueTableDataDTO> getPaginationVisiteTechniqueFinList(int page, int limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        List<VisiteTechniqueEntity> visiteTechniqueEntities = new ArrayList<>();
         LocalDateTime d = LocalDateTime.now();
         
            Page<VisiteTechniqueEntity> visiteTechniqueEntityPage = visiteTechniqueRepository.findVisiteTechniqueVehicule( d ,pageable);
            visiteTechniqueEntities = visiteTechniqueEntityPage.getContent();
         
        return this.loadVisiteTechniqueFinTableDataDTO(visiteTechniqueEntities);
    }
    
    */
    private List<ListVisiteTechniqueDTO> loadVisiteTechniqueFinTableDataDTO(List<VisiteTechniqueEntity> visiteTechniqueEntities)
    {
        List<ListVisiteTechniqueDTO> visiteTechniqueTableDataDTOS = new ArrayList<>();
        if (!visiteTechniqueEntities.isEmpty()) 
        {
            visiteTechniqueEntities.forEach(visiteTechniqueEntity -> 
            {
            	ListVisiteTechniqueDTO visiteTechniqueTableDataDTO = new ListVisiteTechniqueDTO();
                visiteTechniqueTableDataDTO.setId(visiteTechniqueEntity.getId());
                visiteTechniqueTableDataDTO.setDatePMC(visiteTechniqueEntity.getDatePMC());
                visiteTechniqueTableDataDTO.setNombrePlaces(visiteTechniqueEntity.getNombrePlaces());
                visiteTechniqueTableDataDTO.setPuissanceFiscale(visiteTechniqueEntity.getPuissanceFiscale());
                visiteTechniqueTableDataDTO.setMontantVisiteTechnique(visiteTechniqueEntity.getMontantVisiteTechnique());
                visiteTechniqueTableDataDTO.setDateDebutValidite(visiteTechniqueEntity.getDateDebutValidite());
                visiteTechniqueTableDataDTO.setDateFinValidite(visiteTechniqueEntity.getDateFinValidite());
                visiteTechniqueTableDataDTO.setIdVehicule(visiteTechniqueEntity.getVehicule().getId());
                visiteTechniqueTableDataDTO.setNumeroPlaque(visiteTechniqueEntity.getVehicule().getNumeroPlaque());
                visiteTechniqueTableDataDTO.setStructure(visiteTechniqueEntity.getVehicule().getStructure().getDesignation());
                logger.info(String.valueOf(visiteTechniqueEntity.getPrixAchat()));
                visiteTechniqueTableDataDTO.setPrixAchat(visiteTechniqueEntity.getPrixAchat());
                visiteTechniqueTableDataDTO.setVehicule(visiteTechniqueEntity.getVehicule()) ;              
                visiteTechniqueTableDataDTOS.add(visiteTechniqueTableDataDTO);
            });
        }
        return visiteTechniqueTableDataDTOS;
    }

	@Override
	public List<VisiteTechniqueTableDataDTO> getPaginationVisiteTechniqueFinList(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<VisiteTechniqueEntity> VisiteTechniqueFinList() {
		List <VisiteTechniqueEntity> v=visiteTechniqueRepository.findVisiteTechniqueVehicule();
		List <VisiteTechniqueEntity> v2 =  new ArrayList();
		System.out.print("\n");
		LocalDateTime d = LocalDateTime.now();
		d.toString();
		String s3 = d.toString().substring(5,  7) ;
		String anne = d.toString().substring(0,  4) ;

		System.out.print(s3+"\n");
		System.out.print(d);
		System.out.print(anne);
		for (int i=0;i< v.size();i++)
		{
			System.out.println(v.get(i).getDateDebutValidite());
			String s4 = v.get(i).getDateFinValidite().toString().substring(5,  7) ;
			String anne2 = v.get(i).getDateFinValidite().toString().substring(0,  4) ;
			if((s3.equals(s4))&&(anne.equals(anne2))) 
			{
				v2.add(v.get(i));
			}
		}
		return v2;
	}
}
