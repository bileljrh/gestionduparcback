package com.gesparc.repositories.carburants;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gesparc.entities.carburant.PricesEntity;

public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

}
