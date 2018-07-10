package org.ictlab.repository;

import org.ictlab.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT message.id, message.senddate, message.msg, message.state FROM message INNER JOIN appuser_message ON appuser_message.message_id = message.id WHERE appuser_message.user_id = :id")
    List<Message> findAllByUsers_Id(@Param("id") Long id);

}
