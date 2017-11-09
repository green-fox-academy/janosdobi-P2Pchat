package com.greenfox.oraclechat.repositories;

import com.greenfox.oraclechat.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}