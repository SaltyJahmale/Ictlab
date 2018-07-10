package org.ictlab.security.repository;

import org.hibernate.validator.constraints.Range;
import org.ictlab.domain.Group;
import org.ictlab.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ictlab.domain.User;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByClassName(Group className);
//    @Query(nativeQuery = true, value = "SELECT message FROM appuser, message WHERE appuser.id = :id")
//    List<Message> findAllByUsers_Id(Long id);

}
