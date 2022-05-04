package com.gesparc.repositories.administration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gesparc.entities.administration.MessageEntity;


@Repository
@EnableAutoConfiguration
public interface MessageRepository extends PagingAndSortingRepository<MessageEntity, Long> {

}
