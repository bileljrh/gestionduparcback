package com.gesparc.repositories.achat;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.achat.BonCommandeEntity;

@EnableAutoConfiguration
@Repository
public interface BonCommandeRepository extends PagingAndSortingRepository<BonCommandeEntity, Long> {

	Page<BonCommandeEntity> findAllByReceipt(boolean receipt, Pageable pageable);

	@Query(value = "select count (id) from bon_commande", nativeQuery = true)
	Long getTotalNumberBonCammande();

	// filtrage par parc
	@Query(value = "select * from bon_commande where (parc = ?1)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByParc(String parc, Pageable pageable);

	// filtrage par status
	@Query(value = "select * from bon_commande where (status = ?1)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByStatus(String status, Pageable pageable);

	// filtrage par fournisseur
	@Query(value = "SELECT * FROM bon_commande b , fournisseur f WHERE( (f.designation =:fournisseur)AND(b.fournisseur_id=f.id)) ", nativeQuery = true)
	Page<BonCommandeEntity> findAllByFournisseur(String fournisseur, Pageable pageable);

	// filtrage par contennant article
	@Query(value = "SELECT * FROM bon_commande b , demande_article d , article a  WHERE (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByArticle(String article, Pageable pageable);

	// filtrage par parc and status
	@Query(value = "select * from bon_commande where (parc = ?1) and (status = ?2)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByParcAndStatus(String parc, String status, Pageable pageable);

	// filtrage par parc and fournisseur
	@Query(value = "select * from bon_commande b, fournisseur f where (b.parc =:parc) and (b.fournisseur_id =f.id) and (f.designation =:fournisseur)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByParcAndFournisseur(String parc, String fournisseur, Pageable pageable);

	// filtrage par parc and contenant article
	@Query(value = "select * from bon_commande b, article a, demande_article d  where (b.parc =:parc) and (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByParcAndArticle(String parc, String article, Pageable pageable);

	// filtrage par status and fournisseur
	@Query(value = "select * from bon_commande b, fournisseur f where (b.status =:status) and (b.fournisseur_id =f.id) and (f.designation =:fournisseur)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByStatusAndFournisseur(String status, String fournisseur, Pageable pageable);

	// filtrage par status and contenant article
	@Query(value = "select * from bon_commande b, article a, demande_article d  where (b.status =:status) and (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByStatusAndArticle(String status, String article, Pageable pageable);

	// filtrage par fournisseur and contenant article
	@Query(value = "select * from bon_commande b, article a, demande_article d ,fournisseur f where  (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id)and (b.fournisseur_id =f.id) and (f.designation =:fournisseur)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByFournisseurAndArticle(String fournisseur, String article, Pageable pageable);

	// filtrage par parc - status - fournisseur
	@Query(value = "select * from bon_commande b , fournisseur f where (b.parc =:parc) and (b.status =:status)and (b.fournisseur_id =f.id) and (f.designation =:fournisseur)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByParcStatusFournisseur(String parc, String status, String fournisseur,Pageable pageable);

	// filtrage par parc - status - contenant article
	@Query(value = "select * from bon_commande b , demande_article d , article a  where (b.parc =:parc) and (b.status =:status)and (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByParcStatusArticle(String parc, String status, String article, Pageable pageable);

	// filtrage par parc - fournisseur - contenant article
	@Query(value = "select * from bon_commande b , demande_article d , article a ,fournisseur f where (b.parc =:parc) and (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id)and (b.fournisseur_id =f.id) and (f.designation =:fournisseur)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByParcFournisseurArticle(String parc, String fournisseur, String article,Pageable pageable);

	// filtrage par status - fournisseur - contenant article
	@Query(value = "select * from bon_commande b , demande_article d , article a ,fournisseur f where (b.status =:status) and (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id)and (b.fournisseur_id =f.id) and (f.designation =:fournisseur)", nativeQuery = true)
	Page<BonCommandeEntity> findAllByStatusFournisseurArticle(String status, String fournisseur, String article,Pageable pageable);

	// filtrage par status - fournisseur -article - parc
	@Query(value = "select * from bon_commande b , demande_article d , article a, fournisseur f where  (b.parc =:parc)and (b.status =:status) and  (a.designation =:article) AND(d.bon_commande_id=b.id)AND(d.article_id=a.id) and (b.fournisseur_id =f.id) and (f.designation =:fournisseur)", nativeQuery = true)
	Page<BonCommandeEntity> findAllBySFAP(String article, String parc, String status, String fournisseur,Pageable pageable);

}
