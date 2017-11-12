package com.greenfox.oraclechat.repositories;

import com.greenfox.oraclechat.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
}
