package com.github.habiteria.security;

import com.github.habiteria.domain.model.User;
import org.springframework.security.core.Authentication;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UserAuthService {
    User signUp(User user);

    boolean isAuthorized(Authentication auth, String username);

    User update(UUID id, User user);

    User patch(UUID id, User user);
}