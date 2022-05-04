package com.gesparc.servicesImpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gesparc.entities.achat.BonCommandeEntity;
import com.gesparc.entities.achat.DemandeArticleEntity;
import com.gesparc.entities.achat.MarcheEntity;
import com.gesparc.entities.maintenance.BonTravailEntity;
import com.gesparc.entities.maintenance.DemandeMaintenanceEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.FournisseurEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import com.gesparc.repositories.achat.BonCommandeRepository;
import com.gesparc.repositories.achat.DemandeArticleRepository;
import com.gesparc.repositories.achat.MarcheRepository;
import com.gesparc.repositories.referentiel.*;
import com.gesparc.services.Achat;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.Achat.additional.UpdateBonCommandeDTO;
import com.gesparc.sharedDTO.maintenance.additional.BonTravailDataTableDTO;
import com.gesparc.sharedDTO.maintenance.additional.DemandeMaintenanceDataTableDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@EnableAutoConfiguration
@Service
public class AchatImpl implements Achat 
{

	@Autowired
	BonCommandeRepository bonCommandeRepository;

	@Autowired
	MarcheRepository marcheRepository;

	@Autowired
	DemandeArticleRepository demandeArticleRepository;

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	UgpRepository ugpRepository;
	
	public String dmin;
	
	public String dmax;

	Logger logger = LoggerFactory.getLogger(AchatImpl.class);

	Date d = new Date();

	private String generateNumeroBonCommande(Long totalNumber) 
	{
		LocalDate localDate = LocalDate.now();
		return String.format("%04d", totalNumber) + "-" + LocalDate.now().getDayOfMonth()+ LocalDate.now().getMonthValue() + LocalDate.now().getYear();
	}
	@Override
	public void addNewBonCommande(UpdateBonCommandeDTO updateBonCommandeDTO) 
	{
		BonCommandeEntity bonCommandeEntity = new BonCommandeEntity();
		bonCommandeEntity.setNumePiece(this.generateNumeroBonCommande(bonCommandeRepository.getTotalNumberBonCammande()));
		bonCommandeEntity.setParc(updateBonCommandeDTO.getParc());
		bonCommandeEntity.setStatus(updateBonCommandeDTO.getStatus());
		bonCommandeEntity.setDateDemande(d);
		bonCommandeEntity.setCommandeHTBrut(updateBonCommandeDTO.getCommandeHTBrut());
		bonCommandeEntity.setCommandeHTNet(updateBonCommandeDTO.getCommandeHTNet());
		bonCommandeEntity.setCommandeTVA(updateBonCommandeDTO.getCommandeTVA());
		bonCommandeEntity.setCommandeTimbreFix(updateBonCommandeDTO.getCommandeTimbreFix());
		bonCommandeEntity.setCommandeTTC(updateBonCommandeDTO.getCommandeTTC());
		bonCommandeEntity.setLivraisonHTBrut(updateBonCommandeDTO.getLivraisonHTBrut());
		bonCommandeEntity.setLivraisonHTNet(updateBonCommandeDTO.getLivraisonHTNet());
		bonCommandeEntity.setLivraisonTVA(updateBonCommandeDTO.getLivraisonTVA());
		bonCommandeEntity.setLivraisonTimbreFix(updateBonCommandeDTO.getLivraisonTimbreFix());
		bonCommandeEntity.setLivraisonTTC(updateBonCommandeDTO.getLivraisonTTC());
		bonCommandeEntity.setMontantLivre(updateBonCommandeDTO.getMontantLivre());
		bonCommandeEntity.setMontantFacture(updateBonCommandeDTO.getMontantFacture());
		bonCommandeEntity.setDateFacture(updateBonCommandeDTO.getDateFacture());
		bonCommandeEntity.setReferenceFacture(updateBonCommandeDTO.getReferenceFacture());
		bonCommandeEntity.setMontantReglement(updateBonCommandeDTO.getMontantReglement());
		bonCommandeEntity.setReferenceReglement(updateBonCommandeDTO.getReferenceReglement());
		bonCommandeEntity.setReceipt(false);
		FournisseurEntity fournisseurEntity = fournisseurRepository.findById(updateBonCommandeDTO.getFournisseur().getId()).get();
		if (fournisseurEntity.getBonCommandes() == null) 
		{
			fournisseurEntity.setBonCommandes(new ArrayList<>());
		}
		bonCommandeEntity.setFournisseur(fournisseurEntity);
		fournisseurEntity.getBonCommandes().add(bonCommandeEntity);
		bonCommandeEntity.setDateReglement(updateBonCommandeDTO.getDateReglement());
		bonCommandeEntity.setDemandesArticle(new ArrayList<>());
		if (updateBonCommandeDTO.getUpdateDemandesArticle() != null)
		{
			updateBonCommandeDTO.getUpdateDemandesArticle().forEach(updateDemandeArticleDTO -> 
			{
				DemandeArticleEntity demandeArticleEntity = new DemandeArticleEntity();
				demandeArticleEntity.setDateDemandeArticle(LocalDate.now());
				ArticleEntity articleEntity = articleRepository.findFirstByCodeArticle(updateDemandeArticleDTO.getUpdateArticle().getCodeArticle());
				demandeArticleEntity.setQuantiteCommandee(updateDemandeArticleDTO.getQuantiteCommandee());
				demandeArticleEntity.setQuantiteLivree(updateDemandeArticleDTO.getQuantiteLivree());
				demandeArticleEntity.setAccepted(false);
				demandeArticleEntity.setArticle(articleEntity);
				if (articleEntity.getDemandeArticles() == null)
				{
					articleEntity.setDemandeArticles(new ArrayList<>());
				}
				articleEntity.getDemandeArticles().add(demandeArticleEntity);
				demandeArticleEntity.setBonCommande(bonCommandeEntity);
				bonCommandeEntity.getDemandesArticle().add(demandeArticleEntity);
			});
		}
		bonCommandeRepository.save(bonCommandeEntity);
	}

	private void dArticle(Long id) {
		System.out.println("ramla rupp 1 !");
		System.out.println(id);
		List<DemandeArticleEntity> d = demandeArticleRepository.findByBonCommandeId(id);



		for (Iterator iterator = d.iterator(); iterator.hasNext();) {
		DemandeArticleEntity demandeArticleEntity = (DemandeArticleEntity) iterator.next();



		System.out.println("hello Ramla");
		if (demandeArticleEntity != null) {
		System.out.println("Ramla suppression");
		System.out.println(demandeArticleEntity.getId());
		demandeArticleRepository.delete(demandeArticleEntity);
		}



		}



		}




		@Override
		public void modifySelectedBonCommande(UpdateBonCommandeDTO updateBonCommandeDTO)
		{
		BonCommandeEntity bonCommandeEntity = bonCommandeRepository.findById(updateBonCommandeDTO.getId()).get();
		bonCommandeEntity.setParc(updateBonCommandeDTO.getParc());
		bonCommandeEntity.setStatus(updateBonCommandeDTO.getStatus());
		bonCommandeEntity.setDateDemande(d);
		bonCommandeEntity.setCommandeHTBrut(updateBonCommandeDTO.getCommandeHTBrut());
		bonCommandeEntity.setCommandeHTNet(updateBonCommandeDTO.getCommandeHTNet());
		bonCommandeEntity.setCommandeTVA(updateBonCommandeDTO.getCommandeTVA());
		bonCommandeEntity.setCommandeTimbreFix(updateBonCommandeDTO.getCommandeTimbreFix());
		bonCommandeEntity.setCommandeTTC(updateBonCommandeDTO.getCommandeTTC());
		bonCommandeEntity.setLivraisonHTBrut(updateBonCommandeDTO.getLivraisonHTBrut());
		bonCommandeEntity.setLivraisonHTNet(updateBonCommandeDTO.getLivraisonHTNet());
		bonCommandeEntity.setLivraisonTVA(updateBonCommandeDTO.getLivraisonTVA());
		bonCommandeEntity.setLivraisonTimbreFix(updateBonCommandeDTO.getLivraisonTimbreFix());
		bonCommandeEntity.setLivraisonTTC(updateBonCommandeDTO.getLivraisonTTC());
		bonCommandeEntity.setMontantLivre(updateBonCommandeDTO.getMontantLivre());
		bonCommandeEntity.setMontantFacture(updateBonCommandeDTO.getMontantFacture());
		bonCommandeEntity.setReferenceFacture(updateBonCommandeDTO.getReferenceFacture());
		bonCommandeEntity.setMontantReglement(updateBonCommandeDTO.getMontantReglement());
		bonCommandeEntity.setReferenceReglement(updateBonCommandeDTO.getReferenceReglement());
		Iterator<DemandeArticleEntity> bonCommandeEntityIterator = bonCommandeEntity.getDemandesArticle().iterator();



		bonCommandeEntity.getDemandesArticle().clear();
		bonCommandeEntity.setDemandesArticle(new ArrayList<>());
		if (updateBonCommandeDTO.getUpdateDemandesArticle() != null)
		{
		this.dArticle(updateBonCommandeDTO.getId());
		updateBonCommandeDTO.getUpdateDemandesArticle().forEach(updateDemandeArticleDTO ->
		{
		DemandeArticleEntity demandeArticleEntity = new DemandeArticleEntity();
		ArticleEntity articleEntity = articleRepository.findFirstByCodeArticle(updateDemandeArticleDTO.getUpdateArticle().getCodeArticle());
		demandeArticleEntity.setQuantiteCommandee(updateDemandeArticleDTO.getQuantiteCommandee());
		demandeArticleEntity.setQuantiteLivree(updateDemandeArticleDTO.getQuantiteLivree());
		demandeArticleEntity.setAccepted(false);
		demandeArticleEntity.setArticle(articleEntity);
		if (articleEntity.getDemandeArticles() == null)
		{
		articleEntity.setDemandeArticles(new ArrayList<>());
		}
		articleEntity.getDemandeArticles().add(demandeArticleEntity);
		demandeArticleEntity.setBonCommande(bonCommandeEntity);
		bonCommandeEntity.getDemandesArticle().add(demandeArticleEntity);
		});
		}



		bonCommandeRepository.save(bonCommandeEntity);
		}
	@Override
	public void deleteSelectedBonCommande(Long id)
    {
		bonCommandeRepository.deleteById(id);
	}

	@Override
	public List<BonCommandeDTO> getPaginationBonCommandeList2(String status, String parc,String fournisseur,String article, int page, int limit) 
	{
		ModelMapper modelMapper = new ModelMapper();
		List<BonCommandeDTO> bonCommandeDTOS = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		List<BonCommandeEntity> bonCommandeEntities = bonCommandeRepository.findAll(pageable).getContent();
		if ((status.length() > 0) && (parc.length() == 0) && (fournisseur.length() == 0)&& (article.length() == 0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByStatus(status, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();	
		}
		else if((status.length() == 0) && (parc.length() > 0) && (fournisseur.length() == 0)&& (article.length() == 0)) 
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByParc(parc, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		} 
		else if ((status.length() == 0) && (parc.length() == 0) && (fournisseur.length() > 0)&& (article.length() == 0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByFournisseur(fournisseur, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() == 0) && (parc.length() == 0) && (fournisseur.length() == 0)&& (article.length() > 0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByArticle(article, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		} 
		else if ((status.length() > 0) && (parc.length() > 0) && (fournisseur.length() == 0)&& (article.length() == 0)) 
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByParcAndStatus(parc,status, pageable);
		    bonCommandeEntities = bonCommandeEntity.getContent();
	    }
		else if ((status.length() == 0) && (parc.length() > 0) && (fournisseur.length() > 0)&& (article.length() == 0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByParcAndFournisseur(parc,fournisseur, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() == 0) && (parc.length() > 0) && (fournisseur.length() == 0)&& (article.length() >0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByParcAndArticle(parc,article, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() > 0) && (parc.length() == 0) && (fournisseur.length() > 0)&& (article.length() ==0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByStatusAndFournisseur(status,fournisseur, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() > 0) && (parc.length() == 0) && (fournisseur.length() == 0)&& (article.length() >0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByStatusAndArticle(status,article, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() == 0) && (parc.length() == 0) && (fournisseur.length() > 0)&& (article.length() >0)) 
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByFournisseurAndArticle(fournisseur,article, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() > 0) && (parc.length() >0) && (fournisseur.length() > 0)&& (article.length() ==0)) 
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByParcStatusFournisseur(parc,status,fournisseur, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();					
		}
		else if ((status.length() > 0) && (parc.length() >0) && (fournisseur.length() == 0)&& (article.length() >0)) 
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByParcStatusArticle(parc,status,article, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() == 0) && (parc.length() >0) && (fournisseur.length() > 0)&& (article.length() >0))
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByParcFournisseurArticle(parc,fournisseur,article, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
		else if ((status.length() > 0) && (parc.length() ==0) && (fournisseur.length() > 0)&& (article.length() >0)) 
		{
			Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllByStatusFournisseurArticle(status,fournisseur,article, pageable);
			bonCommandeEntities = bonCommandeEntity.getContent();
		}
	   else if ((status.length() > 0) && (parc.length() >0) && (fournisseur.length() > 0)&& (article.length() >0)) 
	   {
		    Page<BonCommandeEntity> bonCommandeEntity = bonCommandeRepository.findAllBySFAP(article,parc,status,fournisseur, pageable);
		    bonCommandeEntities = bonCommandeEntity.getContent();
	    }
	   else 
	   {
			Page<BonCommandeEntity> bonCommandeEntityPage = bonCommandeRepository.findAll(pageable);
			bonCommandeEntities = bonCommandeEntityPage.getContent();
	   }	
		if (!bonCommandeEntities.isEmpty()) 
		{
			bonCommandeEntities.forEach(bonCommandeEntity -> 
			{
			   bonCommandeDTOS.add(modelMapper.map(bonCommandeEntity, BonCommandeDTO.class));
			});
		}
		return bonCommandeDTOS;
	}

	@Override
	public Long getTotalItemBonCommandeList() 
	{
		PageRequest pageable = PageRequest.of(0, 8);
		return bonCommandeRepository.findAll(pageable).getTotalElements();
	}

	@Override
	public Long getTotalItemMarcheList() 
	{
		PageRequest pageable = PageRequest.of(0, 8);
		return marcheRepository.findAll(pageable).getTotalElements();
	}

	public Iterable<UgpEntity> getAllParcs()
	{
		return ugpRepository.findAll();
	}

}
