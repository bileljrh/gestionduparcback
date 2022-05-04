package com.gesparc.repositories.referentiel;

import java.util.List;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.carteAgilis.CarteAgilisCashEntity;
import com.gesparc.entities.carburant.carteJocker.CarteJockerEntity;
import com.gesparc.entities.referentiel.ArticleEntity;
import com.gesparc.entities.referentiel.GenreVehiculeEntity;
import com.gesparc.entities.referentiel.MarqueVehiculeEntity;
import com.gesparc.entities.referentiel.SousFamilleArticleEntity;
import com.gesparc.entities.referentiel.TypeVehiculeEntity;

@Repository
@EnableAutoConfiguration
public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, Long> {

	
	ArticleEntity findFirstByCodeArticle(String codeArticle);
    	
    @Query("select c from article c where c.id = :id ")
    ArticleEntity findArticleById(long  id);

    Page<ArticleEntity> findAllBySousFamille(SousFamilleArticleEntity sousFamilleArticleEntity, Pageable page);
    
    Page<ArticleEntity> findAllByMarqueVehicule(MarqueVehiculeEntity marqueVehicule, Pageable pageable); 
    
    @Query(value = "select count (id) from article", nativeQuery = true)
    Long getTotalNumberMarqueVehicule();

    @Query(value = "select count (id) from article where (marqueVehicule = ?1)", nativeQuery = true)
    Long getTotalNumberArticleByMarqueVehicule(MarqueVehiculeEntity marqueVehicule);
    
Page<ArticleEntity> findAllByGenreVehicule(GenreVehiculeEntity genreVehicule, Pageable pageable); 
    
    @Query(value = "select count (id) from article", nativeQuery = true)
    Long getTotalNumberGenreVehicule();

    @Query(value = "select count (id) from article where (genreVehicule = ?1)", nativeQuery = true)
    Long getTotalNumberArticleByGenreVehicule(GenreVehiculeEntity genreVehicule);
    
    Page<ArticleEntity> findAllByTypeVehicule(TypeVehiculeEntity typeVehicule, Pageable pageable); 
    
    @Query(value = "select count (id) from article", nativeQuery = true)
    Long getTotalNumberTypeVehicule();

    @Query(value = "select count (id) from article where (typeVehicule = ?1)", nativeQuery = true)
    Long getTotalNumberArticleByTypeVehicule(TypeVehiculeEntity typeVehicule);
    
	@Query(value = "SELECT * FROM article a WHERE a.id IN (SELECT mva.article_id FROM mv_article mva where mva.magasin_virtuel_id =:id)", nativeQuery = true)
	List<ArticleEntity> listArticleAtransferer(Long id);

	// Article by ugp
	@Query(value = "SELECT * FROM article a , unite_gestion_parc u  WHERE( (u.designation =:designation) AND(a.ugp_id=u.id)) ", nativeQuery = true)
	List<ArticleEntity> findArticleByUgp (String designation);
	
	List<ArticleEntity> findByUgpId(Long id);
	
	//Article by  sous famille:	
	@Query(value = "SELECT * FROM article a , sous_famille_article f WHERE( (f.sous_famille =:sousfamille)AND(a.sous_famille_id=f.id)) ", nativeQuery = true)
	List<ArticleEntity> findArticleBySousFamille (String sousfamille);
	
	//Article by Ugp et sous famille:	
	@Query(value = "SELECT * FROM article a , unite_gestion_parc u , sous_famille_article f WHERE( (u.designation =:designation) AND(a.ugp_id=u.id) and (f.sous_famille =:sousfamille)AND(a.sous_famille_id=f.id)) ", nativeQuery = true)
	List<ArticleEntity> findArticleByUgpSousFamille (String sousfamille,String designation );
	
	//Article by   famille:	
	@Query(value = "SELECT * FROM article a , sous_famille_article f,famille_article s WHERE( (s.famille =:famille)AND(a.sous_famille_id=f.id)AND(f.famille_id=s.id) )", nativeQuery = true)
	List<ArticleEntity> findArticleByFamille (String famille);
		
	// article by ugp et famille 
	
	@Query(value = "SELECT * FROM article a , unite_gestion_parc u ,sous_famille_article f,famille_article s WHERE( (u.designation =:designation) AND(a.ugp_id=u.id) and (s.famille =:famille)AND(a.sous_famille_id=f.id)AND(f.famille_id=s.id)) ", nativeQuery = true)
	List<ArticleEntity> findArticleByUgpFamille (String designation , String famille );
	
	//Article by   famille and sous Famille:	
	@Query(value = "SELECT * FROM article a , sous_famille_article f,famille_article s WHERE( (s.famille =:famille)AND(f.sous_famille=:sousfamille)AND(a.sous_famille_id=f.id)AND(f.famille_id=s.id) )", nativeQuery = true)
	List<ArticleEntity> findArticleByFamilleSousFamille (String famille,String sousfamille);
	
	//Article by  genre and famille:
	@Query(value = "SELECT * FROM article a , genre_vehicule g,famille_article s ,sous_famille_article f WHERE( (g.designation =:genre)AND(s.famille =:famille)AND(a.genre_id=g.id)AND(a.sous_famille_id=f.id)AND(s.famille_id=f.id) )", nativeQuery = true)
	List<ArticleEntity> findArticleByFamilleGenre (String genre,String famille);
	
	//Article by  Marque and famille:
	@Query(value = "SELECT * FROM article a , sous_famille_article s,marque_vehicule m ,famille_article f WHERE (m.designation =:marque) AND(f.famille =:famille)AND(s.id=a.sous_famille_id) AND(a.id=a.marque_id)AND(a.sous_famille_id=f.id)AND(f.famille_id=s.id)", nativeQuery = true)
	List<ArticleEntity> findArticleByFamilleMarque(String famille, String marque);
	
	
	//Article by  type and famille:
		@Query(value = "SELECT * FROM article a , sous_famille_article s,type_vehicule t ,famille_article f WHERE (t.designation =:type) AND(f.famille =:famille)AND(s.id=a.sous_famille_id) AND(g.id=a.type_id)AND(a.sous_famille_id=f.id)AND(f.famille_id=s.id)", nativeQuery = true)
		List<ArticleEntity> findArticleByFamilleType(String famille, String type); 
		
		//Article by ugp genre:
		@Query(value = "SELECT * FROM article a , unite_gestion_parc u, genre_vehicule g WHERE( (u.designation =:designation) AND(a.ugp_id=u.id)  and (g.designation =:genre)AND(a.genre_id=g.id) )", nativeQuery = true)
		List<ArticleEntity> findArticleByUgpGenre (String genre, String designation);
	
		//Article by ugp Marque:
		@Query(value = "SELECT * FROM article a ,unite_gestion_parc u, marque_vehicule m WHERE ((u.designation =:designation) AND(a.ugp_id=u.id)  and (m.designation =:marque) AND(a.marque_id=m.id))", nativeQuery = true)
		List<ArticleEntity> findArticleByUgpMarque (String marque,String designation);
		
		//Article by ugp  type:
		@Query(value = "SELECT * FROM article a , type_vehicule t WHERE((u.designation =:designation) AND(a.ugp_id=u.id)  and (t.designation =:type) AND(t.id=a.type_id))", nativeQuery = true)
		List<ArticleEntity> findArticleByUgpType (String type,String designation);
		
	//Article by  genre:
	@Query(value = "SELECT * FROM article a , genre_vehicule g WHERE( (g.designation =:genre)AND(a.genre_id=g.id) )", nativeQuery = true)
	List<ArticleEntity> findArticleByGenre (String genre);
	//Article by  Marque:
	@Query(value = "SELECT * FROM article a , marque_vehicule m WHERE ((m.designation =:marque) AND(a.marque_id=m.id))", nativeQuery = true)
	List<ArticleEntity> findArticleByMarque (String marque);
	//Article by  type:
	@Query(value = "SELECT * FROM article a , type_vehicule t WHERE( (t.designation =:type) AND(t.id=a.type_id))", nativeQuery = true)
	List<ArticleEntity> findArticleByType (String type);
    @Query(value = "SELECT * FROM article a , genre_vehicule g,sous_famille_article s WHERE (s.sous_famille =:sousFamille)AND(g.designation =:genre)AND(s.id=a.sous_famille_id) AND(g.id=a.genre_id)", nativeQuery = true)
    List<ArticleEntity> findArticleByGenreSousFamille(String genre, String sousFamille);
    
    @Query(value = "SELECT * FROM article a , genre_vehicule g,marque_vehicule m WHERE (m.designation =:marque) AND(g.designation =:genre)AND(m.id=a.sous_famille_id) AND(g.id=a.genre_id)AND(m.id=a.marque_id)", nativeQuery = true)
    List<ArticleEntity> findArticleByGenreMarque(String genre, String marque);

    @Query(value = "SELECT * FROM article a , genre_vehicule g,type_vehicule t WHERE (t.designation =:type) AND(g.designation =:genre)AND(t.id=a.type_id) AND(g.id=a.genre_id)AND(m.id=a.type_id)", nativeQuery = true)
    List<ArticleEntity> findArticleByGenreType(String genre, String type);

    @Query(value = "SELECT * FROM article a , sous_famille_article s,marque_vehicule m WHERE (m.designation =:marque) AND(s.sous_famille =:sousFamille)AND(s.id=a.sous_famille_id) AND(a.id=a.marque_id)", nativeQuery = true)
    List<ArticleEntity> findArticleBySousFamilleMarque(String sousFamille, String marque);

    @Query(value = "SELECT * FROM article a , sous_famille_article s,type_vehicule t WHERE (t.designation =:type) AND(s.sous_famille =:sousFamille)AND(s.id=a.sous_famille_id) AND(g.id=a.type_id)", nativeQuery = true)
    List<ArticleEntity> findArticleBySousFamilleType(String sousFamille, String type);

   @Query(value = "SELECT * FROM article a , type_vehicule t,marque_vehicule m WHERE (m.designation =:marque) AND(t.designation =:type)AND(t.id=a.type_id) AND(a.id=a.marque_id)", nativeQuery = true)
   List<ArticleEntity> findArticleByTypeMarque(String type, String marque);

   @Query(value = "SELECT * FROM article a ,sous_famille_article s, genre_vehicule g,marque_vehicule m WHERE (s.sous_famille =:sousFamille)AND (m.designation =:marque) AND(g.designation =:genre)AND(g.id=a.genre_id) AND(a.id=a.marque_id)AND(s.id=a.sous_famille_id)", nativeQuery = true)
   List<ArticleEntity> findArticleBySousFamilleMarqueGenre(String sousFamille, String marque, String genre);

   @Query(value = "SELECT * FROM article a ,sous_famille_article s, type_vehicule g,marque_vehicule m WHERE (s.sous_famille =:sousFamille)AND (m.designation =:marque) AND(g.designation =:type)AND(g.id=a.type_id) AND(a.id=a.marque_id)AND(s.id=a.sous_famille_id)", nativeQuery = true)
   List<ArticleEntity> findArticleBySousFamilleMarqueType(String sousFamille, String marque, String type);

   @Query(value = "SELECT * FROM article a ,sous_famille_article s, genre_vehicule g,type_vehicule m WHERE (s.sous_famille =:sousFamille)AND (m.designation =:type) AND(g.designation =:genre)AND(g.id=a.genre_id) AND(a.id=a.type_id)AND(s.id=a.sous_famille_id)", nativeQuery = true)
   List<ArticleEntity> findArticleBySousFamilleTypeGenre(String sousFamille, String type, String genre);

   @Query(value = "SELECT * FROM article a ,type_vehicule s, genre_vehicule g,marque_vehicule m WHERE (s.desination =:type)AND (m.designation =:marque) AND(g.designation =:genre)AND(g.id=a.genre_id) AND(a.id=a.marque_id)AND(s.id=a.type_id)", nativeQuery = true)
   List<ArticleEntity> findArticleByGenreMarqueType(String genre, String marque, String type);

   @Query(value = "SELECT * FROM article a , type_vehicule t , marque_vehicule m , genre_vehicule g , sous_famille_article s WHERE (s.sous_famille =:sousFamille) AND(s.id=a.sous_famille_id)AND(g.designation =:genre) AND(g.id=a.genre_id)AND(m.designation =:marque) AND(m.id=a.marque_id)AND(t.designation =:type)AND(t.id=a.type_id)", nativeQuery = true)
   List<ArticleEntity> findArticleByTouts(String sousFamille, String marque, String genre, String type);

   @Query("select c from article c where c.codeArticle = :codeArticle ")
   ArticleEntity findByCode(String  codeArticle);

   @Query(value = "select * from article c where c.alert_stock =quantite_stock", nativeQuery = true)
   Page<ArticleEntity> findArticleForAlerting(Pageable pageable);
   
   @Query(value = "select count (id) from article c where c.alert_stock >=quantite_stock", nativeQuery = true)
   Long findNombreArticleForAlerting();
	@Query(value = "SELECT id ,code_article,designation,quantite_stock, creation_date ,last_update_date FROM article a", nativeQuery = true)
	List<ArticleEntity> listArticle();
}
