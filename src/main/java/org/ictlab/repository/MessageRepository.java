package org.ictlab.repository;

import org.ictlab.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUsers_Id(Long id);

}
