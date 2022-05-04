package com.gesparc.repositories.carburants;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import com.gesparc.entities.carburant.AgilisFileData;

public interface AgilisFileDataRepository extends JpaRepository<AgilisFileData, Long> {

	List<AgilisFileData> findByTransacDate(String y);

	AgilisFileData findByIdFile(String dd);

	Page<AgilisFileData> findByIdFile(String idFile, Pageable pageable);
	
	Page<AgilisFileData> findAll(Pageable pageable);
	

}
