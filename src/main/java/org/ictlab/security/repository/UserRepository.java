package org.ictlab.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ictlab.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
