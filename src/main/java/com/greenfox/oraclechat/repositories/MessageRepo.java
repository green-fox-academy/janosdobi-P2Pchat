package com.greenfox.oraclechat.repositories;

import com.greenfox.oraclechat.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

    @Query(value = "SELECT * FROM message ORDER BY created_at DESC LIMIT 10", nativeQuery = true)
    Iterable<Message> listTenMostRecent();
}