package com.gesparc.repositories.stock;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.stock.InventaireStock;
import com.gesparc.entities.stock.MagasinRotationNull;

@EnableAutoConfiguration
@Repository
public interface MagasinRotationNullRepository extends PagingAndSortingRepository<MagasinRotationNull, Long> {


}
