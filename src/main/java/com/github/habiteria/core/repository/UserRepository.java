package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * @author Alex Ivchenko
 */
public interface UserRepository extends Repository<User, String> {

    @Query("select u from User u where u.id = :id")
    User findOne(@Param("id") String id);

    User findByUsername(String username);

    User save(User s);
}
