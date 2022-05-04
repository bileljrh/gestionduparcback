package com.gesparc.repositories;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.carburant.cartePlafond.DemandeAnnulationCartePlafondEntity;

import java.util.List;

@EnableAutoConfiguration
@Repository
public interface DemandeAnnulationCartePlafondRepository extends CrudRepository<DemandeAnnulationCartePlafondEntity, Long> {

	public static final String FIND_IDCARD = "SELECT  d.id_card FROM demande_annulation_carte_plafond d";


    @Query(value = "select * from demande_annulation_carte_plafond where (confirmed = ?1)", nativeQuery = true)
    List<DemandeAnnulationCartePlafondEntity> getHistoriqueDemandeAnnulationCartePlafondByConfirmation(boolean confirmation);
    
    @Query(value = FIND_IDCARD, nativeQuery = true)
    public List<Long> listCard();
    


}
