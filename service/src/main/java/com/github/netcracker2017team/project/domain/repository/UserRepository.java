package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UserRepository extends CrudRepository<User, UUID> {
    User findByUsername(String username);
}
